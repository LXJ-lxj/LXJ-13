
import com.demo.mapper.UserMapper;
import com.demo.pojo.User;
import com.demo.utils.MybatisUtils;
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

    @Test
    public void getUserById(){
        SqlSession sqlSession= MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.getUserById(1);
        System.out.println(userById);

        sqlSession.close();
    }
//    方式一：
    @Test
    public void addUser(){
        //        获取sqlSession对象
        SqlSession sqlSession= MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int res = mapper.addUser(new User(4, "哈哈哈", "5555"));

        if(res>0){
            System.out.println("插入成功！！");
        }
//        提交事务
        sqlSession.commit();
        sqlSession.close();
    }
//方式二：
    @Test
    public void addUser2(){
        //        获取sqlSession对象
        SqlSession sqlSession= MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userid",6);
        map.put("username","张三");
        mapper.addUser2(map);
//        提交事务
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void update(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.update(new User(3,"呵呵呵","6666"));

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.deleteUser(4);

        sqlSession.commit();

        sqlSession.close();
    }

    /*模糊查询*/
    @Test
    public void getUserLike(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.getUserLike("李");
        /*也可以使用这个 List<User> userList = mapper.getUserLike("%李%");*/
        for (User user : userList) {
            System.out.println(user);
        }


        sqlSession.close();
    }
}
