package com.example.elasticsearch.xxx;

import java.io.IOException;

import java.util.List;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilders;

import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import sun.com.didi.entity.Auth;


@Service
public class ES_QueryImpl implements ES_Query {
    private static final Logger LOGGER = LoggerFactory.getLogger(ES_QueryImpl.class);
    @Autowired
    private JestClient jestClient;


    @Override

    public void saveEntity(Auth entity) {

        Index index = new Index.Builder(entity).index(Auth.INDEX_NAME).type(Auth.TYPE).build();

        try {

            jestClient.execute(index);

            LOGGER.info("ES 插入完成");

        } catch (IOException e) {

            e.printStackTrace();

            LOGGER.error(e.getMessage());

        }

    }




    /**

     * 批量保存内容到ES

     */

    @Override

    public void saveEntity(List<Auth> entityList) {

        Bulk.Builder bulk = new Bulk.Builder();

        for(Auth entity : entityList) {

            Index index = new Index.Builder(entity).index(Auth.INDEX_NAME).type(Auth.TYPE).build();

            bulk.addAction(index);

        }

        try {

            jestClient.execute(bulk.build());

            LOGGER.info("ES 插入完成");

        } catch (IOException e) {

            e.printStackTrace();

            LOGGER.error(e.getMessage());

        }

    }



    /**

     * 在ES中搜索内容

     */

    @Override

    public List<Auth> searchEntity(String searchContent){

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();


        searchSourceBuilder.query(QueryBuilders.matchQuery("name",searchContent));

        Search search = new Search.Builder(searchSourceBuilder.toString())

                .addIndex(Auth.INDEX_NAME).addType(Auth.TYPE).build();

        try {

            JestResult result = jestClient.execute(search);

            return result.getSourceAsObjectList(Auth.class);

        } catch (IOException e) {

            LOGGER.error(e.getMessage());

            e.printStackTrace();

        }

        return null;

    }

}
