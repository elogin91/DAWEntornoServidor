package vacantes.modelo.dto;

import java.util.List;

public class JwtResponse {
  private String accessToken;
  private String type = "Bearer";
  private String nombre;
  private String username;
  private String email;
  private List<String> perfil;

  public JwtResponse(String accessToken, String nombre, String username, String email, List<String> perfil) {
    this.accessToken = accessToken;
    this.nombre = nombre;
    this.username = username;
    this.email = email;
    this.perfil = perfil;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getPerfil() {
    return perfil;
  }
}