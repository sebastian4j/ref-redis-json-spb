package cl.sebastian.redis.string;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisStringApplicationTests {

  @Autowired
  private RedisTemplate<String, Object> redis;

  @Test
  public void validarRegistroJson() {
    assertThat(redis).isNotNull();
    var hash = redis.opsForHash();
    final Persona p = new Persona();
    final var claveRedis = "redis-clave";
    final var claveRegistro = "clave";
    p.setId(1);
    p.setNombre("nombre");
    hash.put(claveRedis, claveRedis, p);
    var x = (Persona) hash.get(claveRedis, claveRegistro);
    assertThat(x.getId()).isEqualTo(1);
    assertThat(x.getNombre()).isEqualTo("nombre");
  }

}
