package com.example.essample;

import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.HashMap;

import static org.elasticsearch.action.admin.indices.stats.CommonStatsFlags.Flag.Search;

@SpringBootTest
class EssampleApplicationTests {

    @Resource
    RestClient restClient;

    @Test
    public void testEs() throws IOException {
        String query = "{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"actorList.name\": \"张译\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        Search search = new Search.Builder(query).addIndex("movie_chn").addType("movie_type_chn").build();

        SearchResult result = restClient.execute(search);

        List<SearchResult.Hit<HashMap, Void>> hits = result.getHits(HashMap.class);

        for (SearchResult.Hit<HashMap, Void> hit : hits) {
            HashMap source = hit.source;
            System.err.println("source = " + source);

        }

    }

    @Test
    void contextLoads() {
    }

}
