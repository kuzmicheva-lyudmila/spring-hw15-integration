package ru.otus.hw15.promotion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.*;
import org.springframework.messaging.MessageChannel;
import ru.otus.hw15.model.Promotion;

import java.util.List;

@Configuration
public class IntegrationConfig {
    @Bean
    public MessageChannel promotionBegin() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel databaseDataSource() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel mlServiceDataSource() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel channelResult() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow promotionFlow() {
        return f -> f
                .channel("promotionBegin")
                .handle("promotionRepository", "findAllByLocation")
                .split()
                .<Promotion, Promotion.DataSource> route(
                    Promotion::getDataSource,
                        mapping -> mapping
                        .subFlowMapping(Promotion.DataSource.DATABASE, sf -> sf
                                .channel("databaseDataSource"))
                        .subFlowMapping(Promotion.DataSource.ML_SERVICE, sf -> sf
                                .channel("mlServiceDataSource"))
                );
    }

    @Bean
     public IntegrationFlow promotionResultFlow() {
        return IntegrationFlows.from("promotionResult")
                .aggregate()
                .transform(
                        Transformers.toJson()
                )
                .channel("channelResult")
                .get();
    }

    @Bean
    public IntegrationFlow dataBaseDataSourceFlow() {
        return IntegrationFlows.from("databaseDataSource")
                .handle("dataBaseService", "process")
                .channel("promotionResult")
                .get();
    }

    @Bean
    public IntegrationFlow mlServiceDataSourceFlow() {
        return IntegrationFlows.from("mlServiceDataSource")
                .handle("mlService", "process")
                .channel("promotionResult")
                .get();
    }
}
