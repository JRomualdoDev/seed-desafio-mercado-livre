package com.desafio.mercado_livre.novousuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email(message = "Por favor, informe um e-mail válido!")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @NotNull
    @PastOrPresent(message = "A data de cadastro não pode ser no futuro")
    private Instant instantCadastro =  Instant.now();

    public Usuario(
            @Email @NotBlank String email,
            @NotBlank @Size(min = 6) String senha
    ) {
        this.email = email;
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    public Usuario() {

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
