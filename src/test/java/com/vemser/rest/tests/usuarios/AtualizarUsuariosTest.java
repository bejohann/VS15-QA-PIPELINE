package com.vemser.rest.tests.usuarios;

import com.vemser.rest.client.UsuarioClient;
import com.vemser.rest.data.factory.UsuarioDataFactory;
import com.vemser.rest.utils.constants.UsuariosConstants;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class AtualizarUsuariosTest {

    private UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    public void testDeveAtualizarUsuarioComSucesso() {

        usuarioClient.atualizarUsuario(UsuarioDataFactory.idValido(), UsuarioDataFactory.usuarioValido())
        .then()
                .statusCode(200)
                .body(UsuariosConstants.MESSAGE, equalToIgnoringCase(UsuariosConstants.MSG_ATUALIZAR_COM_SUCESSO))
        ;
    }

    @Test
    public void testSchemaAtualizarUsuarioComSucesso() {

        usuarioClient.atualizarUsuario(UsuarioDataFactory.idValido(), UsuarioDataFactory.usuarioValido())
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/atualizar_usuario.json"))
        ;
    }

    @Test
    public void testAtualizarUsuarioInexistenteECriacaoDeNovoUsuario() {

        usuarioClient.atualizarUsuario(UsuarioDataFactory.idInvalido(), UsuarioDataFactory.usuarioValido())
        .then()
                .statusCode(201)
                .body(UsuariosConstants.MESSAGE, equalToIgnoringCase(UsuariosConstants.MSG_CADASTRO_COM_SUCESSO))
                .body(UsuariosConstants.ID, notNullValue())
        ;
    }

    @Test
    public void testAtualizarUsuarioInexistenteEComEmailJaCadastrado() {

        usuarioClient.atualizarUsuario(UsuarioDataFactory.idInvalido(), UsuarioDataFactory.usuarioComEmailJaCadastrado())
        .then()
                .statusCode(400)
                .body(UsuariosConstants.MESSAGE, equalToIgnoringCase(UsuariosConstants.MSG_EMAIL_JA_CADASTRADO))
        ;
    }
}
