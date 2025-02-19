package com.vemser.rest.tests.healthcheck;

import com.vemser.rest.client.UsuarioClient;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


public class HealthCheckTest {

    UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    @Tag("Health-Check")
    public void testListarTodosUsuariosComSucesso() {

        usuarioClient.listarUsuarios()
        .then()
        .statusCode(200)
        ;
    }
}
