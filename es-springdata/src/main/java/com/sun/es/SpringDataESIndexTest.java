package com.sun.es;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESIndexTest {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    //创建索引并增加映射配置
    @Test
    public void createIndex(){
        System.out.println("创建索引");
    }

    //删除
    @Test
    public void deleteIndex(){
        //创建索引，系统初始化会自动创建索引
        boolean flg = elasticsearchRestTemplate.deleteIndex(Product.class);
        System.out.println("删除索引 = " + flg);
    }

    //增加
    @Autowired
    private ProductDao productDao;
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
//修改数据
    @Test
    public void update(){
        Product product=new Product();
        product.setId(22L);
        product.setTitle("小米手机");
        product.setImages("com/photo/2021-08-03_10-01-55.png");
        product.setCategory("手机");
        product.setPrice(9999.0);
        productDao.save(product);

    }

    //查询指定id的数据
    @Test
    public void findById(){
        Product product=productDao.findById(22L).get();
        System.out.println(product);
    }

    @Test
    public void findAll(){
        Iterable<Product> products=productDao.findAll();
        for (Product product:products)
        {
            System.out.println(product);
        }
    }

    @Test
    public void delete(){
        Product product=new Product();
        product.setId(22L);
        productDao.delete(product);
    }
    //批量增加
    @Test
    public void saveAll(){
        List<Product> productList=new ArrayList<>();
        for (int i=0;i<10;i++){
            Product product=new Product();
            product.setId(Long.valueOf(i));
            product.setTitle("苹果8 plus");
            product.setImages("com/photo/2021-08-03_10-01-55.png");
            product.setCategory("手机");
            product.setPrice(2999.0);
            productDao.saveAll(productList);
        }
    }

    @Test
    public void findByPageable(){
        //设置排序（排序方式，正序还是倒序，排序的id）
        Sort sort=Sort.by(Sort.Direction.DESC,"id");
        int currentPage=0;//当前页，第一页从0开始，1表示从第二页开始
        int pageSize=5;//每页显示多少条
        //设置查询分页
        PageRequest pageRequest=PageRequest.of(currentPage,pageSize,sort);
        //分页查询
        Page<Product>productPage=productDao.findAll(pageRequest);
        for (Product product:productPage.getContent()){
            System.out.println(product);
        }
    }

}
