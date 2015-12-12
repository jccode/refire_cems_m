package com.hongdingltd.config;


import com.hongdingltd.hello.HelloChatReceiver;
import com.hongdingltd.hello.HelloGenericReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by jcchen on 15-12-12.
 */
@Component
public class RedisConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory
            , MessageListenerAdapter genericListenerAdapter) { // , MessageListenerAdapter listenerAdapter
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

//        container.addMessageListener(chatListenerAdapter, new PatternTopic("chat"));
        container.addMessageListener(genericListenerAdapter, new PatternTopic("generic"));

//        MessageListenerAdapter chartListener = new MessageListenerAdapter(new HelloChatReceiver(), "receiveMessage");
//        container.addMessageListener(chartListener, new PatternTopic("chat"));

//        MessageListenerAdapter genericListener = new MessageListenerAdapter(new HelloGenericReceiver(), "receiveMessage");
//        container.addMessageListener(genericListener, new PatternTopic("generic"));

        return container;
    }

    @Bean
    public MessageListenerAdapter chatListenerAdapter() {
        return new MessageListenerAdapter(new HelloChatReceiver(), "receiveMessage");
    }

    @Bean MessageListenerAdapter genericListenerAdapter() {
        MessageListenerAdapter genricReceiver = new MessageListenerAdapter(new HelloGenericReceiver(), "receiveMessage");
        genricReceiver.setSerializer(jsonSerializer());
        return genricReceiver;
    }

    @Bean
    public StringRedisTemplate chatTemplate(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    @Bean
    public RedisTemplate<String, HashMap<String, String>> genericTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate<String, HashMap<String, String>>();
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        StringRedisSerializer strSerializer = new StringRedisSerializer();
        GenericToStringSerializer<Object> genericSerializer = new GenericToStringSerializer<Object>(Object.class);


        template.setKeySerializer(strSerializer);
        template.setHashKeySerializer(strSerializer);
        template.setValueSerializer(jsonSerializer());
        template.setHashValueSerializer(genericSerializer);

        return template;
    }

    @Bean
    public Jackson2JsonRedisSerializer jsonSerializer() {
        return new Jackson2JsonRedisSerializer(Object.class);
    }
}
