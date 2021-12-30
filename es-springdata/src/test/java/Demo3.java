import com.ESClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Person;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

public class Demo3 {
    ObjectMapper mapper=new ObjectMapper();
    RestHighLevelClient client= ESClient.getClient();
    String index="person";
    String type="man";

    @Test
    public void createDoc() throws IOException {
        //准备json数据
        Person person=new Person(1,"张三",23,new Date());
        String json = mapper.writeValueAsString(person);
        //准备一个request对象(手动指定id)
        IndexRequest request=new IndexRequest(index,type,person.getId().toString());
        request.source(json, XContentType.JSON);

        //通过client对象执行添加
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);


        //输出返回结果
        System.out.println(response.getResult().toString());
    }
}
