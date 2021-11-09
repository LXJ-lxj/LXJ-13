package com.example.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sun.com.didi.entity.Article;
@Repository
public interface ArticleRepository extends ElasticsearchRepository <Article,Integer>{
    void deleteById(Long articleId);

}