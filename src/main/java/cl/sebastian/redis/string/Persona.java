package cl.sebastian.redis.string;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Sebastián Ávila A.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Persona {

  private int id;
  private String nombre;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

}
