package com.example.clientpayment.config;


import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticConfig extends AbstractElasticsearchConfiguration {

    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("https://mydeployment-5a87d1.es.us-central1.gcp.cloud.es.io:9243")
                .usingSsl()
                .withBasicAuth("elastic", "S6QNJO01tmD3Gek2Y2gVsV51")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}



