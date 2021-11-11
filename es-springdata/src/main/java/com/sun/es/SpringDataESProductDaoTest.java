package com.sun.es;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESProductDaoTest {
    @Autowired
    private ProductDao productDao;
    //新增数据
    @Test
    public void save(){
        Product product=new Product();
        product.setId(22L);
        product.setTitle("苹果8 plus");
        product.setImages("com/photo/2021-08-03_10-01-55.png");
        product.setCategory("手机");
        product.setPrice(2999.0);
        productDao.save(product);

    }
}
