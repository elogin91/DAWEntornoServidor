package vacantes.modelo.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import vacantes.modelo.entidades.Usuario;

public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private String nombre;

  private String username;

  private String email;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(String nombre, String username, String email, String password,
      Collection<? extends GrantedAuthority> authorities) {
    this.nombre = nombre;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(Usuario usuario) {
    List<GrantedAuthority> authorities = usuario.getPerfiles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getNombre()))
        .collect(Collectors.toList());

    return new UserDetailsImpl(
    		usuario.getNombre(), 
    		usuario.getUsername(), 
    		usuario.getEmail(),
    		usuario.getPassword(), 
        authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public String getNombre() {
    return nombre;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(username, user.username);
  }
}