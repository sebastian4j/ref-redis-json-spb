package cl.sebastian.redis.string;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 *
 * @author Sebastián Ávila A.
 */
@Configuration
public class RedisConfig {

  @Autowired
  private ConfigurableApplicationContext context;

  @Bean
  @Primary
  public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory conn) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(conn);
    template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
    template.setKeySerializer(new StringRedisSerializer());
    template.setHashKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    return template;
  }

  @Bean
  public JedisConnectionFactory redisConnectionFactory(@Value("${redis.host}") String host,
          @Value("${redis.puerto}") int puerto) {
    System.out.println("puerto: " + puerto);
    System.out.println("host: " + host);
    RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, puerto);
    return new JedisConnectionFactory(config);
  }
}
