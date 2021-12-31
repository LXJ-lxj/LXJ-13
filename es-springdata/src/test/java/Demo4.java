import com.ESClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

//term查询
public class Demo4 {
    ObjectMapper mapper=new ObjectMapper();
    RestHighLevelClient client= ESClient.getClient();
    String index="person";
    String type="man";
    @Test
    public void termQuery() throws IOException {
      //创建对象
        SearchRequest request =new SearchRequest(index);
        request.types(type);

        //指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.from(0);
        builder.size(5);
        builder.query(QueryBuilders.termQuery("province","北京"));

        request.source(builder);

        //执行查询
        SearchResponse resp = client.search(request, RequestOptions.DEFAULT);

        //获取到_source中的数据，并展示
        for (SearchHit hit:resp.getHits().getHits()){
            Map<String,Object>result= hit.getSourceAsMap();
            System.out.println(result);
        }
    }
}
