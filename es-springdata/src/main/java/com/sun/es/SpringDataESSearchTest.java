package com.sun.es;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESSearchTest {
    @Autowired
    private ProductDao productDao;

public void termQuery(){
    TermQueryBuilder termQueryBuilder= QueryBuilders.termQuery("category","手机");
    Iterable<Product> products=productDao.search(termQueryBuilder);
    for (Product product:products)
    {
        System.out.println(product);
    }
}
}
