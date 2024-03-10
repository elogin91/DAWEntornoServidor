package vacantes.restcontroller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vacantes.configuraciones.JwtUtils;
import vacantes.modelo.dto.JwtResponse;
import vacantes.modelo.dto.LoginRequest;
import vacantes.modelo.dto.MessageResponse;
import vacantes.modelo.dto.SignupRequest;
import vacantes.modelo.entidades.Perfil;
import vacantes.modelo.entidades.Usuario;
import vacantes.modelo.repository.PerfilRepository;
import vacantes.modelo.repository.UsuarioRepository;
import vacantes.modelo.service.UserDetailsImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {
private static final int DEFAULT_PROFILE_ID =2 ;	
	
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UsuarioRepository usuarioRepository;

  @Autowired
  PerfilRepository perfilRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getNombre(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (usuarioRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (usuarioRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    Usuario usuario = new Usuario();
    usuario.setUsername(signUpRequest.getUsername());
    usuario.setNombre(signUpRequest.getNombre());
    usuario.setApellidos(signUpRequest.getApellido());
    usuario.setEmail(signUpRequest.getEmail());
    usuario.setPassword(encoder.encode(signUpRequest.getPassword()));
    usuario.setEnabled(1);
    usuario.setFecha_Registro(new Date());
    
     Perfil perfil = perfilRepository.findById(DEFAULT_PROFILE_ID)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      
    usuario.setPerfiles(Arrays.asList(perfil));
    
    usuarioRepository.save(usuario);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
    
}