import com.ESClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

public class Demo {
    @Test
    public void test(){
        RestHighLevelClient client= ESClient.getClient();
        System.out.println("ok");
    }
}
