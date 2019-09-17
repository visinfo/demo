package com.upday.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upday.demo.domain.port.ArticleService;
import com.upday.demo.repository.ArticleElastichSearchRepository;
import com.upday.demo.repository.ArticleRepository;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.io.IOException;
import java.net.InetAddress;


@Configuration()
@EnableElasticsearchRepositories(basePackages = "com.upday")
@ComponentScan(basePackages = { "com.upday" })
@EnableAutoConfiguration(exclude = ElasticsearchDataAutoConfiguration.class)
public class EsConfig {


    @Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private int EsPort;

    @Value("${elasticsearch.clustername}")
    private String EsClusterName;

    @Bean
    public Client client() throws Exception {

        Settings esSettings = Settings.settingsBuilder()
                .put("cluster.name", EsClusterName)
                .build();

        //https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
        return TransportClient.builder()
                .settings(esSettings)
                .build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort));
    }


    @Autowired
    public ArticleElastichSearchRepository articleElastichSearchRepository;

    @Bean
    public ArticleService articleService(ArticleElastichSearchRepository articleElastichSearchRepository) {
        return new ArticleRepository(articleElastichSearchRepository);
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client, ObjectMapper objectMapper) {
        EntityMapper mapper = new EntityMapper() {
            @Override
            public String mapToString(Object object) throws IOException {
                return objectMapper.writeValueAsString(object);
            }

            @Override
            public <T> T mapToObject(String source, Class<T> clazz) throws IOException {
                return objectMapper.readValue(source, clazz);
            }
        };

        return new ElasticsearchTemplate(client, mapper);
    }
}
