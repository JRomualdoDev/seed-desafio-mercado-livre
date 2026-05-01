package com.desafio.mercado_livre.novousuario;

import com.desafio.mercado_livre.compartilhado.CustomMockMvc;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;
import net.jqwik.web.api.Email;
import org.junit.jupiter.api.Assumptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
public class NovoUsuarioControllerTest {

    private Set<String> emailsGerados = new HashSet<>();

    @Autowired
    private CustomMockMvc mvc;

    @Property(tries = 10)
    @Label("Fluxo de cadastro do novo usuario")
    void teste(
            @ForAll @Email String email,
            @ForAll @AlphaChars @StringLength(min = 6, max = 255) String senha
    ) throws Exception{

        Assumptions.assumeTrue(emailsGerados.add(email));

        mvc.post("/usuarios", Map.of(
            "email",email, "senha",senha
        ))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mvc.post("/usuarios", Map.of(
                "email",email, "senha",senha
        )).andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
