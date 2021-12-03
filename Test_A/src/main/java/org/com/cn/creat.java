import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Paths;

public class creat {
    public void create(String title,String content) throws IOException {
        // 存放索引的路径
        String direct = "src/index";
        Directory directory = FSDirectory.open(Paths.get(direct));
        IndexWriterConfig iwConfig = new IndexWriterConfig((new IKAnalyzer()));
        // 设置创建索引模式(在原来的索引的基础上创建或新增)
        iwConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        /*
         * 添加索引，在之前的索引基础上追加
         * iwConfig.setOpenMode(OpenMode.APPEND);
         * 创建索引，删除之前的索引
         * iwConfig.setOpenMode(OpenMode.CREATE);
         */
        IndexWriter iwriter = new IndexWriter(directory, iwConfig);
        // 创建一个存储对象
        Document doc = new Document();
        // 添加字段

        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new TextField("content", content, Field.Store.YES));
        // 新添加一个doc对象
        iwriter.addDocument(doc);
        // 提交事务
        iwriter.commit();
        // 关闭事务
        iwriter.close();
    }
}
