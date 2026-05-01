package com.desafio.mercado_livre.novousuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class NovoUsuarioController {

    @PersistenceContext
    private EntityManager entityManager;

    public NovoUsuarioController() {

    }

    @PostMapping
    @Transactional
    public ResponseEntity criarUsuario(@RequestBody @Valid NovoUsuarioRequest request) {

        System.out.println(request.toString());
         Usuario usuario = request.toModel();

         entityManager.persist(usuario);

         return ResponseEntity.ok().build();
    }
}
