package cl.sebastian.redis.string;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import redis.clients.jedis.Jedis;

@SpringBootTest
@ContextConfiguration(initializers = {RedisStringJsonTests.Initializer.class})
public class RedisStringJsonTests {
  private static String redisHost;
  private static int redisPuerto;
  
  @Rule
  public static GenericContainer cont = new GenericContainer<>("redis:5.0.3-alpine")
          .withExposedPorts(6379);

  @AfterAll
  static void detenerRedis() {
    cont.stop();
  }

  @BeforeAll
  static void iniciarRedis() {
    cont.start();
  }

  static class Initializer
          implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext ctx) {
      redisHost = cont.getContainerIpAddress();
      redisPuerto = cont.getFirstMappedPort();
      TestPropertyValues.of(
              "redis.puerto=" + redisPuerto,
              "redis.host=" + redisHost
      ).applyTo(ctx.getEnvironment());
    }
  }

  @Autowired
  private RedisTemplate<String, Object> redis;

  @Test
  public void validarRegistroJson() throws IOException {
    assertThat(redis).isNotNull();
    var hash = redis.opsForHash();
    final Persona p = new Persona();
    final var claveRedis = "redis-clave";
    final var claveRegistro = "clave";
    p.setId(1);
    p.setNombre("nombre");
    hash.put(claveRedis, claveRegistro, p);
    var x = (Persona) hash.get(claveRedis, claveRegistro);
    assertThat(x.getId()).isEqualTo(1);
    assertThat(x.getNombre()).isEqualTo("nombre");
    
    Jedis jedis = new Jedis(redisHost, redisPuerto);
    final var recuperado = jedis.hget(claveRedis, claveRegistro);
    var personaRecuperada = new ObjectMapper().readValue(recuperado, Persona.class);
    assertThat(personaRecuperada).isEqualToComparingFieldByField(p);
  }
}
