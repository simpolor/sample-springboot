package io.simpolor.elasticsearch.repository;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.*;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.*;
import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.allegro.tech.embeddedelasticsearch.PopularProperties;

import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.elasticsearch.index.query.QueryBuilders.idsQuery;

public class EmbeddedElasticsearchScriptTest {

    static EmbeddedElastic embeddedElastic;

    static TransportClient client;

    @BeforeClass
    public static void beforeClass() throws Exception{
        embeddedElastic = EmbeddedElastic.builder()
                .withElasticVersion("5.6.3")
                .withSetting(PopularProperties.HTTP_PORT, 9200)
                .withSetting(PopularProperties.TRANSPORT_TCP_PORT, 9300)
                .withSetting(PopularProperties.CLUSTER_NAME, "elasticsearch")
                .withEsJavaOpts("-Xms128m -Xmx512m")
                .withIndex("student")
                .withStartTimeout(1, TimeUnit.MINUTES)
                .build();

        embeddedElastic.start();

        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch").build();

        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
    }
    @AfterClass
    public static void after(){
        client.close();
        embeddedElastic.stop();
    }

    @Test
    public void testEmbeddedElasticsearchScript() throws Exception{
        Map<String, Object> json = new HashMap<>();
        json.put("name", "parksy");
        json.put("grade", "3");
        json.put("age", 19);
        json.put("hobby", Arrays.asList("축구", "컴퓨터"));

        IndexResponse indexResponse = client.prepareIndex("student", "doc")
                .setSource(json)
                .get();

        System.out.println("response.getId() : "+indexResponse.getId());

        StringBuilder sbScript = new StringBuilder();
        sbScript.append("ctx._source.age = 20;");

        /*client.prepareUpdate("student", "doc", indexResponse.getId())
                .setScript(new Script(sbScript.toString()))
                .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)
                .execute();*/

        UpdateByQueryRequestBuilder updateByQuery = UpdateByQueryAction.INSTANCE.newRequestBuilder(client);
        updateByQuery.source("student")
                .filter(QueryBuilders.termQuery("name", "parksy"))
                .size(1000)
                .script(new Script(sbScript.toString()));
        BulkByScrollResponse response = updateByQuery.get();


        for(int i=0; i<=100;i++){
            GetResponse getResponse = client.prepareGet("student","doc", indexResponse.getId()).get();
            System.out.println("sourceAsString : "+getResponse.getSourceAsString());
            Thread.sleep(1000);
        }

    }

}

