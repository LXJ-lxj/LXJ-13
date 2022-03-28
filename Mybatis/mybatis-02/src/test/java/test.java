
import cn.sum.mapper.UserMapper;
import cn.sum.pojo.User;
import cn.sum.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class test {
    @Test
    public void test(){
//        获取sqlSession对象
        SqlSession sqlSession= MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.getUserList();
        for (User user :userList) {
            System.out.println(user);

        }
        sqlSession.close();
    }
}
