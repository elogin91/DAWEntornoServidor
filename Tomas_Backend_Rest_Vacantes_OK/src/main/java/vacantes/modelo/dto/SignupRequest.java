package vacantes.modelo.dto;



import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 45)
  private String username;

  @NotBlank
  @Size(min = 3, max = 100)
  private String apellido;
  
  @NotBlank
  @Size(min = 3, max = 45)
  private String nombre;
  
  @NotBlank
  @Size(max = 100)
  @Email
  private String email;

  @NotBlank
  @Size(min = 2, max = 100)
  private String password;
}