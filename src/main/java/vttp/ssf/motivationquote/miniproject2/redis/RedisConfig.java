package vttp.ssf.motivationquote.miniproject2.redis;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import vttp.ssf.motivationquote.miniproject2.model.Quote;

@Configuration
public class RedisConfig {
    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    //initialising all the value for the redis properties
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Optional<Integer> redisPort;
    
    @Value("${spring.redis.password}")
    private String redisPasswrod;

    @Value("${spring.redis.database}")
    private String redisDatabse;

    @Bean(name= "Quote")
    @Scope("singleton")
    public RedisTemplate<String, Object> redisTemplate(){
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(); 
        config.setHostName(redisHost);
        config.setPort(redisPort.get());
        config.setPassword(redisPasswrod);

        final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);

        jedisFac.afterPropertiesSet();
        logger.info("redis host and port >" + redisHost + redisPort);

        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisFac);
        template.setKeySerializer(new StringRedisSerializer() );

        //With the new serializer, you do not need to provide type information when deserializing
        //but the disadvantage is need to implement the Serializable interface and it havve a large serialized result
        //about five times the JSON format 
        RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer(getClass().getClassLoader());
        template.setValueSerializer(serializer);

        logger.info("RedisTemplate set up");
        return template;
    }
}