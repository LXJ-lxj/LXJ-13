package lucene;


import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.spans.SpanNearQuery;
import org.apache.lucene.search.spans.SpanQuery;
import org.apache.lucene.search.spans.SpanTermQuery;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

public class QueryTest {
    /**
     * 传统解析器-多默认字段
     */
    public void MultiFieldQueryParser() throws Exception {
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("/opt/index").toPath()));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        String[] multiDefaultFields = {"bookname", "booktype", "bookcontent"};
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(multiDefaultFields, new IKAnalyzer());
        // 设置默认的组合操作，默认是 OR
        multiFieldQueryParser.setDefaultOperator(QueryParser.Operator.OR);
        Query query = multiFieldQueryParser.parse("易架构lucene的全新例子");
        indexSearcher.search(query, 10);
    }

    /**
     * 布尔查询
     * 搜索的条件往往是多个的，如要查询名称包含“电脑” 或 “thinkpad”的商品，就需要两个词项查询做或合并。布尔查询就是用来组合多个子查询的。
     * 每个子查询称为布尔字句 BooleanClause，布尔字句自身也可以是组合的。 组合关系支持如下四种：
     * Occur.SHOULD 或
     * Occur.MUST 且
     * Occur.MUST_NOT 且非
     * Occur.FILTER 同 MUST，但该字句不参与评分
     * 布尔查询默认的最大字句数为1024，在将通配符查询这样的查询rewriter为布尔查询时，往往会产生很多的字句，可能抛出TooManyClauses 异常。
     * 可通过BooleanQuery.setMaxClauseCount(int)设置最大字句数。
     */
    public void booleanQuery() throws Exception {
// 创建一个indexsearcher对象
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("/opt/index").toPath()));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
// 书内容
        QueryParser queryParser = new QueryParser("bookcontent", new IKAnalyzer());
        Query query1 = queryParser.parse("易架构lucene例子");
        builder.add(query1, BooleanClause.Occur.MUST);
        // 书名称
        Query qymc = new FuzzyQuery(new Term("bookname", "易架构lucene例子"));
        builder.add(qymc, BooleanClause.Occur.MUST);
// 书类型
        Query gmsfhm = new TermQuery(new Term("booktype", "资料"));
        builder.add(gmsfhm, BooleanClause.Occur.MUST);
        BooleanQuery query = builder.build();
        indexSearcher.search(query, 10);
    }

    /**
     * 短语查询
     * 最常用的查询，匹配特定序列的多个词项。PhraserQuery使用一个位置移动因子（slop）来决定任意两个词项的位置可最大移动多少个位置来进行匹配，默认为0。
     * 有两种方式来构建对象：
     * 注意：所有加入的词项都匹配才算匹配（即使是你在同一位置加入多个词项）。如果需要在同一位置匹配多个同义词中的一个，适合用MultiPhraseQuery
     */

    public void phraseQuery() throws Exception {
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("/home/lxj/文档/index").toPath()));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        PhraseQuery phraseQuery1 = new PhraseQuery("bookcontent", "资料", "版本");
        PhraseQuery phraseQuery2 = new PhraseQuery(0, "bookcontent", "资料", "版本");
        PhraseQuery phraseQuery3 = new PhraseQuery("bookcontent", "程序例子", "例子");
        PhraseQuery phraseQuery4 = new PhraseQuery.Builder()
                .add(new Term("bookcontent", "资料"), 4)
                .add(new Term("bookcontent", "xxx"), 5).build();
// 这两句等同
        PhraseQuery phraseQuery5 = new PhraseQuery.Builder()
                .add(new Term("bookcontent", "程序例子"), 0)
                .add(new Term("bookcontent", "例子"), 1).build();
        indexSearcher.search(phraseQuery1, 10);
        indexSearcher.search(phraseQuery2, 10);
        indexSearcher.search(phraseQuery3, 10);
        indexSearcher.search(phraseQuery4, 10);
        indexSearcher.search(phraseQuery5, 10);
    }

    /**
     * 多重短语查询
     * 短语查询的一种更通用的用法，支持同位置多个词的OR匹配。通过里面的Builder来构建MultiPhraseQuery：
     */
    public void multiPhraseQuery() throws Exception {
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("/opt/index").toPath()));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        // 4 MultiPhraseQuery 多重短语查询
        Term[] terms = new Term[2];
        terms[0] = new Term("bookcontent", "资料");
        terms[1] = new Term("bookcontent", "资料版本");
        Term t = new Term("bookcontent", "xxx");
        MultiPhraseQuery multiPhraseQuery = new MultiPhraseQuery.Builder()
                .add(terms).add(t).build();
        // 对比 PhraseQuery在同位置加入多个词 ，同位置的多个词都需匹配，所以查不出。
        PhraseQuery query = new PhraseQuery.Builder().add(terms[0], 0)
                .add(terms[1], 0).add(t, 1).build();
        indexSearcher.search(query, 10);
    }

    /**
     * 临近查询（跨度查询）
     * 用于更复杂的短语查询，可以指定词间位置的最大间隔跨度。通过组合一系列的SpanQuery 实例来进行查询，可以指定是否按顺序匹配、slop、gap
     */
    public void spanNearQuery() throws Exception {
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("/opt/index").toPath()));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        // SpanNearQuery 临近查询
        SpanTermQuery tq1 = new SpanTermQuery(new Term("bookcontent", "查询"));
        SpanTermQuery tq2 = new SpanTermQuery(new Term("bookcontent", "分析"));
        SpanNearQuery spanNearQuery = new SpanNearQuery(
                new SpanQuery[]{tq1, tq2}, 0, true);
        // SpanNearQuery 临近查询 gap slop 使用
        SpanNearQuery.Builder spanNearQueryBuilder = SpanNearQuery
                .newOrderedNearQuery("bookcontent");
        spanNearQueryBuilder.addClause(tq1).addGap(0).setSlop(1)
                .addClause(tq2);
        SpanNearQuery spanNearQuery2 = spanNearQueryBuilder.build();
        indexSearcher.search(spanNearQuery, 10);
        indexSearcher.search(spanNearQuery2, 10);
    }

    /**
     * 词项范围查询
     * 用于查询包含某个范围内的词项的文档，如以字母开头a到c的词项。词项在反向索引中是排序的，只需指定的开始词项、结束词项，就可以查询该范围的词项。
     * 如果是做数值的范围查询则用 PointRangeQuery
     * 参数说明:
     * 第1个参数：要查询的字段-field
     * 第2个参数:：下边界词-lowerTerm
     * 第3个参数：上边界词-upperTerm
     * 第4个参数：是否包含下边界-includeLower
     * 第5个参数：是否包含上边界 includeUpper
     */
    public void termRangeQuery() throws Exception {
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("/opt/index").toPath()));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        // TermRangeQuery 词项范围查询
        TermRangeQuery termRangeQuery = TermRangeQuery.newStringRange("bookcontent",
                "a", "z", false, true);
        indexSearcher.search(termRangeQuery, 10);
    }

    /**
     * 前缀查询
     * PrefixQuery：前缀查询，查询包含以xxx为前缀的词项的文档，是通配符查询，如 app，实际是 app*
     */

    public void prefixQuery() throws Exception {
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("/opt/index").toPath()));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        // PrefixQuery 前缀查询
        PrefixQuery prefixQuery = new PrefixQuery(new Term("bookcontent", "易架构"));
        indexSearcher.search(prefixQuery, 10);
    }

    /**
     * 通配符查询
     * WildcardQuery：通配符查询， *表示0个或多个字符，?表示1个字符，\是转义符。通配符查询可能会比较慢，不可以通配符开头（那样就是所有词项了）
     */


    public void wildcardQuery() throws Exception {
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("/opt/index").toPath()));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        // WildcardQuery 通配符查询
        WildcardQuery wildcardQuery = new WildcardQuery(
                new Term("bookcontent", "易架构*"));
        indexSearcher.search(wildcardQuery, 10);

    }

    /**
     * 正则表达式查询
     * RegexpQuery：正则表达式查询，词项符合某正则表达式
     */

    public void regexpQuery() throws Exception {
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("/opt/index").toPath()));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        // RegexpQuery 正则表达式查询
        RegexpQuery regexpQuery = new RegexpQuery(new Term("bookcontent", "易.{4}"));
        indexSearcher.search(regexpQuery, 10);
    }

    /**
     * 模糊查询
     * 简单地与索引词项进行相近匹配，允许最大2个不同字符。常用于拼写错误的容错：如把 “thinkpad” 拼成 “thinkppd”或 “thinkd”，使用FuzzyQuery 仍可搜索到正确的结果。
     */

    public void fuzzyQuery() throws Exception {
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("/home/lxj/文档/index").toPath()));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        // FuzzyQuery 模糊查询
        FuzzyQuery fuzzyQuery = new FuzzyQuery(new Term("bookcontent", "易架构"));
        FuzzyQuery fuzzyQuery2 = new FuzzyQuery(new Term("bookcontent", "易架构"), 2);
        indexSearcher.search(fuzzyQuery, 10);
        indexSearcher.search(fuzzyQuery2, 10);
    }

    /**
     * 排序查询
     */
    private void sortSearch(Query query) throws Exception {
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("/opt/index").toPath()));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            //true表示降序
            //SortField.Type.SCORE  资料相关度进行排序(默认)
            //SortField.Type.DOC    资料文档编号或者说是索引顺序
            //SortField.Type.FLOAT(Long等),资料fieldName的数值类型进行排序
            SortField sortField = new SortField("bookcontent", SortField.Type.SCORE, false);
            Sort sort = new Sort(sortField);
            TopDocs topDocs = indexSearcher.search(query, 10, sort);
            System.out.println("数字查询");
            System.out.println("命中结果数为: " + topDocs.totalHits);
            // 返回查询结果。遍历查询结果并输出。
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (ScoreDoc scoreDoc : scoreDocs) {
                int doc = scoreDoc.doc;
                Document document = indexSearcher.doc(doc);
                // 打印content字段的值
                System.out.println("bookid: " + document.get("bookid"));
                System.out.println("bookname: " + document.get("bookname"));
                System.out.println("booktype: " + document.get("booktype"));
                System.out.println("bookprice: " + document.get("bookprice"));
                System.out.println("bookcontent: " + document.get("bookcontent"));
                System.out.println("查询得分是: " + scoreDoc.score);
            }
    }

    /**
     * 查询所有文档
     */
    public void testMatchAllDocsQuery() throws Exception {
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("/opt/index").toPath()));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        Query query = new MatchAllDocsQuery();
        TopDocs topDocs = indexSearcher.search(query, 10);
        // 返回查询结果。遍历查询结果并输出。
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            // 打印content字段的值
            System.out.println("bookid: " + document.get("bookid"));
            System.out.println("bookname: " + document.get("bookname"));
            System.out.println("booktype: " + document.get("booktype"));
            System.out.println("bookcontent: " + document.get("bookcontent"));
        }
    }
    /**
     * 不匹配任何文档
     */
    public void testMatchNoDocsQuery() throws Exception {
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("/opt/index").toPath()));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        Query query = new MatchNoDocsQuery();
        TopDocs topDocs = indexSearcher.search(query, 10);
        // 返回查询结果。遍历查询结果并输出。
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            // 打印content字段的值
            System.out.println("bookid: " + document.get("bookid"));
            System.out.println("bookname: " + document.get("bookname"));
            System.out.println("booktype: " + document.get("booktype"));
            System.out.println("bookcontent: " + document.get("bookcontent"));
        }
    }
}
