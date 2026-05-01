package com.desafio.mercado_livre.novousuario;

import com.desafio.mercado_livre.compartilhado.UniqueValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NovoUsuarioRequest {

    @NotBlank
    @Email(message = "Deve conter o formato de um e-mail válido")
    @UniqueValue(domainClass = Usuario.class, fieldName = "email")
    private String email;

    @Size(min = 6)
    @NotBlank
    private String senha;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel() {

        Usuario usuario = new Usuario(
                this.email,
                this.senha
        );

        return usuario;
    }
}
