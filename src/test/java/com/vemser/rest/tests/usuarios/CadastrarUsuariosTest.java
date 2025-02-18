package com.vemser.rest.tests.usuarios;

import com.vemser.rest.client.UsuarioClient;
import com.vemser.rest.data.factory.UsuarioDataFactory;
import com.vemser.rest.utils.constants.UsuariosConstants;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class CadastrarUsuariosTest {

    private UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    public void testDeveCadastrarUsuarioComDadosValidos() {

        usuarioClient.cadastrarUsuario(UsuarioDataFactory.usuarioValido())
        .then()
                .statusCode(201)
                .body(UsuariosConstants.MESSAGE, equalToIgnoringCase(UsuariosConstants.MSG_CADASTRO_COM_SUCESSO))
                .body(UsuariosConstants.ID, notNullValue())
        ;
    }



    @Test
    public void testTentarCadastrarUsuarioComCamposVazios() {

        usuarioClient.cadastrarUsuario(UsuarioDataFactory.usuarioComCamposVazios())
        .then()
                .statusCode(400)
                .body(UsuariosConstants.NOME, equalToIgnoringCase(UsuariosConstants.MSG_NOME_EM_BRANCO))
                .body(UsuariosConstants.EMAIL, equalToIgnoringCase(UsuariosConstants.MSG_EMAIL_EM_BRANCO))
                .body(UsuariosConstants.PASSWORD, equalToIgnoringCase(UsuariosConstants.MSG_PASSWORD_EM_BRANCO))
                .body(UsuariosConstants.IS_ADMIN, equalToIgnoringCase(UsuariosConstants.MSG_ISADMIN_EM_BRANCO))
        ;
    }

    @Test
    public void testTentarCadastrarUsuarioComEmailJaCadastrado() {

        usuarioClient.cadastrarUsuario(UsuarioDataFactory.usuarioComEmailJaCadastrado())
        .then()
                .statusCode(400)
                .body(UsuariosConstants.MESSAGE, equalToIgnoringCase(UsuariosConstants.MSG_EMAIL_JA_CADASTRADO))
        ;
    }

}
