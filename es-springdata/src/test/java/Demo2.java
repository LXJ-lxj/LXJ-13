import com.ESClient;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.junit.Test;

import java.io.IOException;

public class Demo2 {
    RestHighLevelClient client=ESClient.getClient();
    String index="person";
    String type="man";
    @Test
    public void createIndex() throws IOException {
        //准备关于索引的seetings
        Settings.Builder settings=Settings.builder()
                .put("number_of_shards",3)
                .put("number_of_replicas",1);

        //2准备关于索引的结构mappings
        XContentBuilder mappings= JsonXContent.contentBuilder()
                .startObject()
                   .startObject("properties")
                       .startObject("name")
                              .field("type","text")
                       .endObject()
                       .startObject("age")
                           .field("type","integer")
                       .endObject()
                       .startObject("birthday")
                            .field("type","date")
                            .field("format","yyyy-MM-dd")
                       .endObject()
                   .endObject()
                .endObject();


        //3、将settings和mappings封装到一个Request对象
        CreateIndexRequest request = new CreateIndexRequest(index)
                .settings(settings)
                .mapping(mappings);
        //4、通过client对象曲连杰ES并执行创建索引
        CreateIndexResponse response=client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }
@Test
    public void exists() throws IOException {
        //检查索引是否存在
        //1、准备request对象
    GetIndexRequest request=new GetIndexRequest();
        request.indices(index);

    //2、通过client对象执行
        boolean exisit=client.indices().exists(request,RequestOptions.DEFAULT);

        //3、输出
        System.out.println(exisit);
    }
    @Test
    public void delete() throws IOException {
        //1、准备request对象
        DeleteIndexRequest request=new DeleteIndexRequest();
        request.indices(index);
        //2、通过client对象执行
        AcknowledgedResponse delete=client.indices().delete(request,RequestOptions.DEFAULT);
        //3、输出
        System.out.println(delete.isAcknowledged());
    }
}
