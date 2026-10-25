package com.nickbern.vetturno.service;

import com.nickbern.vetturno.dto.AuthResponse;
import com.nickbern.vetturno.dto.LoginRequest;
import com.nickbern.vetturno.dto.RegistroRequest;
import com.nickbern.vetturno.exception.NegocioException;
import com.nickbern.vetturno.model.Rol;
import com.nickbern.vetturno.model.Usuario;
import com.nickbern.vetturno.repository.UsuarioRepository;
import com.nickbern.vetturno.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public AuthResponse registrar(RegistroRequest request) {

        // Regla de negocio: un email solo puede registrarse una vez
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new NegocioException("Ya existe un usuario registrado con ese email");
        }

        var usuario = Usuario.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))   // BCrypt
                .rol(Rol.USER)                                           // nunca el rol que envíe el cliente
                .build();

        usuarioRepository.save(usuario);

        return new AuthResponse(jwtService.generarToken(usuario.getEmail(), usuario.getRol().name()));
    }

    public AuthResponse login(LoginRequest request) {

        // Si las credenciales no coinciden, lanza BadCredentialsException
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new NegocioException("Usuario no encontrado"));

        return new AuthResponse(jwtService.generarToken(usuario.getEmail(), usuario.getRol().name()));
    }
}