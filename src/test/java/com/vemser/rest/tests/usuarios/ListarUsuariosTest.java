package com.vemser.rest.tests.usuarios;

import com.vemser.rest.client.UsuarioClient;
import com.vemser.rest.data.factory.UsuarioDataFactory;
import com.vemser.rest.model.UsuarioResponse;
import com.vemser.rest.utils.constants.UsuariosConstants;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ListarUsuariosTest {

    private UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    public void testListarTodosUsuariosComSucesso() {

        Response response = usuarioClient.listarUsuarios()
        .then()
                .statusCode(200)
                .extract().response()
        ;

        List<UsuarioResponse> usuarios = response.jsonPath().getList("usuarios", UsuarioResponse.class);
        int quantidade = response.jsonPath().getInt("quantidade");

        Assertions.assertAll("response",
                () -> Assertions.assertEquals(quantidade, usuarios.size()),
                () -> Assertions.assertNotNull(usuarios.get(0).getNome()),
                () -> Assertions.assertNotNull(usuarios.get(0).getEmail()),
                () -> Assertions.assertNotNull(usuarios.get(0).getPassword()),
                () -> Assertions.assertNotNull(usuarios.get(0).getAdministrador()),
                () -> Assertions.assertNotNull(usuarios.get(0).getId())
        );
    }



    @Test
    public void testListarUsuariosPorNomeInvalido() {

        usuarioClient.listarUsuarioPorNome(UsuarioDataFactory.nomeInvalido())
        .then()
                .statusCode(200)
                .body(UsuariosConstants.QUANTIDADE, is(0))
                .body(UsuariosConstants.USUARIOS, notNullValue())
        ;

    }

    @Test
    public void testListarUsuariosPorEmailInvalido() {

        usuarioClient.listarUsuarioPorNome(UsuarioDataFactory.emailInvalido())
        .then()
                .statusCode(200)
                .body(UsuariosConstants.QUANTIDADE, is(0))
                .body(UsuariosConstants.USUARIOS, notNullValue())
        ;
    }

    @Test
    public void testBuscarUsuariosPorIdComSucesso() {

        UsuarioResponse response = usuarioClient.listarUsuarioPorId(UsuarioDataFactory.idValido())
        .then()
                .statusCode(200)
                .extract().as(UsuarioResponse.class)
        ;

        Assertions.assertAll("response",
                () -> Assertions.assertNotNull(response.getNome()),
                () -> Assertions.assertNotNull(response.getEmail()),
                () -> Assertions.assertNotNull(response.getPassword()),
                () -> Assertions.assertNotNull(response.getAdministrador()),
                () -> Assertions.assertNotNull(response.getId())
        );
    }

    @Test
    public void testSchemaBuscarUsuariosPorId() {

        usuarioClient.listarUsuarioPorId(UsuarioDataFactory.idValido())
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/usuarios_por_id.json"))
        ;
    }

    @Test
    public void testBuscarUsuarioPorIdInvalido() {

        usuarioClient.listarUsuarioPorId(UsuarioDataFactory.idInvalido())
                .then()
                .statusCode(400)
                .body(UsuariosConstants.MESSAGE, equalToIgnoringCase(UsuariosConstants.MSG_USUARIO_NAO_ENCONTRADO))
        ;
    }
}
