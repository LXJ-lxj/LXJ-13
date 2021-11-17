package lucene.Service;

import lucene.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class serviceImpl {
    @Autowired
    private dao dao;
    public User selectWork(int uid){
        return dao.SelectRecruit(uid);
    }
}
