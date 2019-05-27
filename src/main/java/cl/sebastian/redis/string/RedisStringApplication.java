package cl.sebastian.redis.string;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class RedisStringApplication implements CommandLineRunner {

  @Autowired
  private RedisTemplate<String, Object> redis;

  public static void main(String[] args) {
    SpringApplication.run(RedisStringApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
  }

}
