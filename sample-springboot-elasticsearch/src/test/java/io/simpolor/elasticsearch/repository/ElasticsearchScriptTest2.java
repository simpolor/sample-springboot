package io.simpolor.elasticsearch.repository;

import io.simpolor.elasticsearch.ElasticsearchApplication;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.*;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.reindex.*;
import org.elasticsearch.script.Script;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.idsQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchApplication.class , webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ElasticsearchScriptTest2 {

    @Autowired
    private Client client;

    @Test
    public void testElasticsearchScript() throws Exception {
        Map<String, Object> json = new HashMap<>();
        json.put("name", "parksy");
        json.put("grade", "3");
        json.put("age", 19);
        json.put("hobby", Arrays.asList("축구", "컴퓨터"));

        IndexResponse indexResponse = client.prepareIndex("student", "doc")
                .setSource(json)
                .get();

        System.out.println("response.getId() : " + indexResponse.getId());

        try {
            MultiGetRequestBuilder requestBuilder = client.prepareMultiGet();
            requestBuilder.add("student", "doc", indexResponse.getId());

            MultiGetResponse multiGetItemResponses = requestBuilder
                    .setRealtime(false)
                    .execute().actionGet();

            for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
                GetResponse response = itemResponse.getResponse();
                if (!response.isExists()) {
                    log.error("[findByIds] not exists query :: ID[{}], EXISTS[{}]", response.getId(), response.isExists());
                    continue;
                }

                System.out.println("response.getId() : "+response.getId());
                System.out.println("response.getSourceAsString() : "+response.getSourceAsString());;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}