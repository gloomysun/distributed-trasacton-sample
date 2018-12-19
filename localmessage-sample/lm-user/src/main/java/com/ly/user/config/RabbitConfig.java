package com.ly.user.config;

import com.ly.common.base.Constants;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue(){
        return new Queue(Constants.LOCAL_REGISTRY_QUEUE);
    }
}
