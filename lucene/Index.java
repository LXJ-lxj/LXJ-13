package lucene;

import lucene.jdbc.MysqlConnectionUtil;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Index {
    public static void main(String[] args) throws Exception{
        create();
        addDocument();
//        deleteAllDocument();
//        updateDocument();
    }
    private static void create()throws Exception {
        //1、创建一个Director对象，指定索引库保存的位置。

        //1.1把索引库保存在内存中
        //Directory directory = new RAMDirectory();

        //1.2把索引库保存在磁盘
        Directory directory = FSDirectory.open(new File("/home/lxj/文档/index").toPath());

        //2、基于Directory对象创建一个IndexWriter对象
        IndexWriterConfig config = new IndexWriterConfig();
        //当使用IKAnalyzer分词时，是如下写法。
        //IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory, config);

        //3、读取磁盘上的文件，对应每个文件创建一个文档对象。
        File dir = new File("/home/lxj/文档/index/document");
        File[] files = dir.listFiles();
        for (File f : files) {
            //取文件名
            String fileName = f.getName();
            //文件的路径
            String filePath = f.getPath();
            //文件的内容
            String fileContent = FileUtils.readFileToString(f, "utf-8");
            //文件的大小
            long fileSize = FileUtils.sizeOf(f);
            //创建Field
            //参数1：域的名称，参数2：域的内容，参数3：是否存储
            Field fieldName = new TextField("name", fileName, Field.Store.YES);
            Field fieldPath = new TextField("path", filePath, Field.Store.YES);
            Field fieldContent = new TextField("content", fileContent, Field.Store.YES);
            Field fieldSize = new TextField("size", fileSize + "", Field.Store.YES);

            //创建文档对象
            Document document = new Document();
            //向文档对象中添加域
            document.add(fieldName);
            document.add(fieldPath);
            document.add(fieldContent);
            document.add(fieldSize);

            //5、把文档对象写入索引库
            indexWriter.addDocument(document);
        }

        //6、关闭indexwriter对象
        indexWriter.close();
    }
    public static void addDocument() throws Exception {
        //1.创建一个Director对象，指定索引库保存的位置。
        //把索引库保存在内存中
        //Directory directory = new RAMDirectory();
        //把索引库保存在磁盘
        Directory directory = FSDirectory.open(new File("/home/lxj/文档/index").toPath());

        //2.基于Directory对象创建一个IndexWriter对象,默认使用StandardAnalyzer分析器
        //IndexWriterConfig config = new IndexWriterConfig();
        IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory, config);

        //创建索引文件操作数据库
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        connection= MysqlConnectionUtil.getInstance().getConnection();
        ps=connection.prepareStatement("SELECT * FROM recruitmentrequirements");
        rs=ps.executeQuery();

        while (rs.next()){
            int id=rs.getInt("id");
            String company=rs.getString("company");
            String OccupationalCategory=rs.getString("OccupationalCategory");
            int  RemainingPositions=rs.getInt(" RemainingPositions");
            String  SalaryRequirements=rs.getString(" SalaryRequirements");
            int Number=rs.getInt("Number");
            //3.创建一个Document对象
            Document document = new Document();
            //向document对象中添加域
            document.add(new TextField("id", id+":"+"2", Field.Store.YES));
            document.add(new TextField("company", company+":"+"2", Field.Store.YES));
            document.add(new TextField("OccupationalCategory", OccupationalCategory+":"+"2", Field.Store.YES));
            document.add(new TextField("RemainingPositions", RemainingPositions+":"+"2", Field.Store.YES));
            document.add(new TextField("SalaryRequirements", SalaryRequirements+":"+"2", Field.Store.YES));
            document.add(new TextField("Number", Number+":"+"2", Field.Store.YES));

            //4.把文档写入索引库
            indexWriter.addDocument(document);
            //5.关闭索引库
            indexWriter.close();

        }

     /*   //3.创建一个Document对象
        Document document = new Document();
        //向document对象中添加域
            document.add(new TextField("name", "lucene", Field.Store.YES));
        document.add(new TextField("content", "content", Field.Store.NO));
        //不需要创建索引使用StoreField存储
        document.add(new StoredField("path", "path"));
        ///LongPoint创建索引
        document.add(new LongPoint("size", 10));
        //StoreField存储数据
        document.add(new StoredField("size", 100));
        //4.把文档写入索引库
        indexWriter.addDocument(document);
        //5.关闭索引库
        indexWriter.close();*/
    }
    public static void deleteAllDocument() throws Exception {
        //1.创建一个Director对象，指定索引库保存的位置。
        //把索引库保存在内存中
        //Directory directory = new RAMDirectory();
        //把索引库保存在磁盘
        Directory directory = FSDirectory.open(new File("/opt/index").toPath());

        //2.基于Directory对象创建一个IndexWriter对象,默认使用StandardAnalyzer分析器
        //IndexWriterConfig config = new IndexWriterConfig();
        IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory, config);

        //3.删除文档

        //3.1删除全部文档
        // indexWriter.deleteAll();

        //3.2根据关键词删除文档
        //indexWriter.deleteDocuments(new Term("name", "lucene"));

        //3.3根据查询条件删除文档
        //创建一个查询条件
        Query query = new TermQuery(new Term("name", "lucene"));
        //根据查询条件删除
        indexWriter.deleteDocuments(query);
        //4.关闭索引库
        indexWriter.close();
    }
    public static void updateDocument() throws Exception {
        //1.创建一个Director对象，指定索引库保存的位置。
        //把索引库保存在内存中
        //Directory directory = new RAMDirectory();
        //把索引库保存在磁盘
        Directory directory = FSDirectory.open(new File("/opt/index").toPath());

        //2.基于Directory对象创建一个IndexWriter对象,默认使用StandardAnalyzer分析器
        //IndexWriterConfig config = new IndexWriterConfig();
        IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory, config);

        //创建一个新的文档对象
        Document document = new Document();
        //向document对象中添加域
        document.add(new TextField("name", "lucene2", Field.Store.YES));
        document.add(new TextField("content", "content2", Field.Store.NO));
        //不需要创建索引使用StoreField存储
        document.add(new StoredField("path", "path2"));
        ///LongPoint创建索引
        document.add(new LongPoint("size", 10));
        //StoreField存储数据
        document.add(new StoredField("size", 100));
        //更新操作
        indexWriter.updateDocument(new Term("name", "lucene"), document);
        //关闭索引库
        indexWriter.close();
    }
}
