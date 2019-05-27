package cl.sebastian.redis.string;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisStringApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(RedisStringApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    // :)
  }

}
