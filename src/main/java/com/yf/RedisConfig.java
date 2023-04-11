package com.yf;

import io.lettuce.core.ReadFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {

    //使用StringRedisSerializer来序列化和反序列化redis的key值（默认使用JDK的序列化方式）
    private StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

    @Bean
    public LettuceClientConfigurationBuilderCustomizer lettuceClientConfigurationBuilderCustomizer() {
        //读取要落在从节点（这是一个读写分离）
        return builder -> builder.readFrom(ReadFrom.REPLICA_PREFERRED);
    }

    //@Bean
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer(){
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
       //Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

       /* ObjectMapper om = new ObjectMapper();
        //无public的属性和无public构造方法不缓存
        om.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // null值字段不缓存
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 指定可见的域：Constructor,field,get和set,以及修饰符范围，ANY是任意：default,private,public,protected
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 序列化JSON串时，在值上打印出对象类型
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance ,ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
        // 解决jackson2无法反序列化LocalDateTime的问题
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);*/

        //jackson2JsonRedisSerializer.setObjectMapper(om);

        return null;
    }

    /**
     * 用于操作Redis，执行Redis命令
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
       /* template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);*/
        template.afterPropertiesSet();
        return template;
    }


}
