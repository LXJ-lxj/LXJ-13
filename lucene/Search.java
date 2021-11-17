package lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.mortbay.util.ajax.JSON;
import org.wltea.analyzer.lucene.IKAnalyzer;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class Search {
    public static void main(String[] args)throws Exception {
        search();
        //standardAnalyzer();
//        ikAnalyzer();
//        testTermQuery();
//        testQueryParser();
         // testHighlighter();
    }

    public static void search()throws Exception {
        //1、创建一个Director对象，指定索引库的位置
        Directory directory = FSDirectory.open(new File("/home/lxj/文档/index").toPath());
        //2、创建一个IndexReader对象
        IndexReader indexReader = DirectoryReader.open(directory);
        //3、创建一个IndexSearcher对象，构造方法中的参数indexReader对象。
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //4、创建一个Query对象，TermQuery
            Query query = new TermQuery(new Term("recruitmentrequirements", "百度"));
        //5、执行查询，得到一个TopDocs对象
        //参数1：查询对象 参数2：查询结果返回的最大记录数
        TopDocs topDocs = indexSearcher.search(query, 10);
        //6、取查询结果的总记录数
        System.out.println("查询总记录数：" + topDocs.totalHits);
        //7、取文档列表
        ScoreDoc[] scoreDocs = ((TopDocs) topDocs).scoreDocs;
        //8、打印文档中的内容
        for (ScoreDoc doc : scoreDocs) {
            //取文档id
            int docId = doc.doc;
            //根据id取文档对象
            Document document = indexSearcher.doc(docId);
            System.out.println(document.get("id"));
            System.out.println(document.get("company"));
            System.out.println(document.get("OccupationalCategory"));
            System.out.println(document.get("RemainingPositions"));
            System.out.println(document.get("SalaryRequirements"));
            System.out.println(document.get("Number"));
        }
        //9、关闭IndexReader对象
        indexReader.close();
    }
    public static void standardAnalyzer() throws Exception {
        //1.创建一个Analyzer对象，StandardAnalyzer对象
        Analyzer analyzer = new StandardAnalyzer();

        //2.使用分析器对象的tokenStream方法获得一个TokenStream对象
        String text1="Please see:  Luke become an Apache Lucene module (as of Lucene 8.1)!";
        String text2="Lucene是apache软件基金会4 jakarta项目组的一个子项目，是一个开放源代码的全文检索引擎工具包，但它不是一个完整的全文检索引擎，" +
                "而是一个全文检索引擎的架构，提供了完整的查询引擎和索引引擎，部分文本分析引擎。";
        TokenStream tokenStream = analyzer.tokenStream("", text1);
        //3.向TokenStream对象中设置一个引用，相当于数一个指针
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        //4.调用TokenStream对象的rest方法。如果不调用抛异常
        tokenStream.reset();
        //5.使用while循环遍历TokenStream对象
        while (tokenStream.incrementToken()) {
            System.out.println(charTermAttribute.toString());
        }
        //6.关闭TokenStream对象
        tokenStream.close();
    }
    public static void ikAnalyzer() throws Exception {
        //1.创建一个Analyzer对象，StandardAnalyzer对象
        Analyzer analyzer = new IKAnalyzer();
        //2.使用分析器对象的tokenStream方法获得一个TokenStream对象
        String text1="Please see:  Luke become an Apache Lucene module (as of Lucene 8.1)!";
        String text2="Lucene是apache软件基金会4 jakarta项目组的一个子项目，是一个开放源代码的全文检索引擎工具包，但它不是一个完整的全文检索引擎，" +
                "而是一个全文检索引擎的架构，提供了完整的查询引擎和索引引擎，部分文本分析引擎。";
        TokenStream tokenStream = analyzer.tokenStream("", text1);
        //3.向TokenStream对象中设置一个引用，相当于数一个指针
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        //4.调用TokenStream对象的rest方法。如果不调用抛异常
        tokenStream.reset();
        //5.使用while循环遍历TokenStream对象
        while (tokenStream.incrementToken()) {
            System.out.println(charTermAttribute.toString());
        }
        //6.关闭TokenStream对象
        tokenStream.close();
    }
    public static void testTermQuery() throws Exception {
        //创建IndexReader对象，指定索引库保存的位置。
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("/opt/index").toPath()));
        //创建IndexSearcher对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        //创建一个Query对象

        //1.指定域的范围查询
        Query query = LongPoint.newRangeQuery("size", 0l, 100l);

        //2.指定Field域的关键词查询
        //Query query =new TermQuery( new Term("name", "lucene.txt"));

        //执行查询
        TopDocs topDocs = indexSearcher.search(query, 10);

        System.out.println("查询总记录数：" + topDocs.totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        //遍历查询结果
        for (ScoreDoc doc:scoreDocs){
            //取文档id
            int docId = doc.doc;
            //根据id取文档对象
            Document document = indexSearcher.doc(docId);
            System.out.println(document.get("name"));
            System.out.println(document.get("content"));
            System.out.println(document.get("path"));
            System.out.println(document.get("size"));
        }
        indexReader.close();
    }
    public static void testQueryParser() throws Exception {
        //创建IndexReader对象，指定索引库保存的位置。
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("/opt/index").toPath()));
        //创建IndexSearcher对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        //创建QueryPaser对象，两个参数
        QueryParser queryParser = new QueryParser("name", new IKAnalyzer());

        //使用QueryPaser对象创建一个Query对象,参数1：默认搜索域，参数2：分析器对象
        Query query = queryParser.parse("lucene.txt");

        //执行查询
        TopDocs topDocs = indexSearcher.search(query, 10);
        System.out.println("总记录数：" + topDocs.totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        //遍历查询结果
        for (ScoreDoc doc:scoreDocs){
            //取文档id
            int docId = doc.doc;
            //根据id取文档对象
            Document document = indexSearcher.doc(docId);
            System.out.println(document.get("name"));
            System.out.println(document.get("content"));
            System.out.println(document.get("path"));
            System.out.println(document.get("size"));
        }
        indexReader.close();
    }
    public static void testHighlighter() throws Exception {
        //创建IndexReader对象，指定索引库保存的位置。
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("/home/lxj/文档/index").toPath()));
        //创建IndexSearcher对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //创建QueryPaser对象，两个参数
        QueryParser queryParser = new QueryParser("name", new IKAnalyzer());

        //使用QueryPaser对象创建一个Query对象,参数1：默认搜索域，参数2：分析器对象
        Query query = queryParser.parse("lucene.txt");

        //格式化对象，设置前缀和后缀
        SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color='red'>","</font>");
        //关键词对象
        QueryScorer scorer = new QueryScorer(query);
        //高亮对象
        Highlighter highlighter = new Highlighter(formatter, scorer);

        //执行查询
        TopDocs topDocs = indexSearcher.search(query, 10);
        System.out.println("总记录数：" + topDocs.totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        //遍历查询结果
        for (ScoreDoc doc:scoreDocs){
            System.out.println("----------开始------------");
            //取文档id
            int docId = doc.doc;
            //根据id取文档对象
            Document document = indexSearcher.doc(docId);

            //设置内容高亮
            String highlighterContent = highlighter.getBestFragment(new StandardAnalyzer(),"name",document.get("name"));
            System.out.println(highlighterContent);

            document.getField("content");
            System.out.println(document.get("name"));
            System.out.println(document.get("content"));
            System.out.println(document.get("path"));
            System.out.println(document.get("size"));
            System.out.println("----------结束------------");
        }
        indexReader.close();
    }
}
