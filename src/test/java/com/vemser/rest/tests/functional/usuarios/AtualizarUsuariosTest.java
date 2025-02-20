package com.vemser.rest.tests.functional.usuarios;

import com.vemser.rest.client.UsuarioClient;
import com.vemser.rest.data.factory.UsuarioDataFactory;
import com.vemser.rest.utils.constants.UsuariosConstants;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class AtualizarUsuariosTest {

    private UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    @Tag("Funcional")
    public void testDeveAtualizarUsuarioComSucesso() {

        usuarioClient.atualizarUsuario(UsuarioDataFactory.idValido(), UsuarioDataFactory.usuarioValido())
        .then()
                .statusCode(200)
                .body(UsuariosConstants.MESSAGE, equalToIgnoringCase(UsuariosConstants.MSG_ATUALIZAR_COM_SUCESSO))
        ;
    }



    @Test
    @Tag("Funcional")
    public void testAtualizarUsuarioInexistenteECriacaoDeNovoUsuario() {

        usuarioClient.atualizarUsuario(UsuarioDataFactory.idInvalido(), UsuarioDataFactory.usuarioValido())
        .then()
                .statusCode(201)
                .body(UsuariosConstants.MESSAGE, equalToIgnoringCase(UsuariosConstants.MSG_CADASTRO_COM_SUCESSO))
                .body(UsuariosConstants.ID, notNullValue())
        ;
    }

    @Test
    @Tag("Funcional")
    public void testAtualizarUsuarioInexistenteEComEmailJaCadastrado() {

        usuarioClient.atualizarUsuario(UsuarioDataFactory.idInvalido(), UsuarioDataFactory.usuarioComEmailJaCadastrado())
        .then()
                .statusCode(400)
                .body(UsuariosConstants.MESSAGE, equalToIgnoringCase(UsuariosConstants.MSG_EMAIL_JA_CADASTRADO))
        ;
    }
}
