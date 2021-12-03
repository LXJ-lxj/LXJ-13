import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class select {
    public List<Map<String, Object>> search(String text) throws IOException, ParseException, InvalidTokenOffsetsException {
        String direct = "src/index";
        // 分词器
     //   MMSegAnalyzer analyzer = new MMSegAnalyzer();
        // 得到存放索引的位置
        Directory directory = FSDirectory.open(Paths.get(direct));
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(ireader);
        // 在content中进行搜索
        QueryParser parser = new QueryParser("content", (new IKAnalyzer()));
        // 搜索含有text的内容
        Query query = parser.parse(text);
        // 搜索标题和显示条数(10)
        TopDocs tds = searcher.search(query, 10);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;

        // 在内容中查获找
        for (ScoreDoc sd : tds.scoreDocs) {
            // 获取title
            String title = searcher.doc(sd.doc).get("title");
            // 获取content
            String content = searcher.doc(sd.doc).get("content");
            // 内容添加高亮
            QueryParser qp = new QueryParser("content", (new IKAnalyzer()));
            // 将匹配到的text添加高亮处理
            Query q = qp.parse(text);
            String html_content = displayHtmlHighlight(q, "content", content);

            map = new HashMap<String, Object>();
            map.put("title", title);
            map.put("content", html_content);
            list.add(map);
        }

        return list;
    }

    private String displayHtmlHighlight(Query q, String content, String content1) throws InvalidTokenOffsetsException, IOException {
        // 设置高亮标签,可以自定义,这里我用html将其显示为红色
        SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
        // 评分
        QueryScorer scorer = new QueryScorer(q);
        // 创建Fragmenter
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
        // 高亮分析器
        Highlighter highlight = new Highlighter(formatter, scorer);
        highlight.setTextFragmenter(fragmenter);
        // 分词器
       // Analyzer analyzer = new MMSegAnalyzer();
        // 调用高亮方法
        String str = highlight.getBestFragment((new IKAnalyzer()), content, content1);
        return str;
    }
}
