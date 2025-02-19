package com.vemser.rest.tests.functional.usuarios;

import com.vemser.rest.client.UsuarioClient;
import com.vemser.rest.data.factory.UsuarioDataFactory;
import com.vemser.rest.utils.constants.UsuariosConstants;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ExcluirUsuariosTest {

    private UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    @Tag("Funcional")
    public void testDeveExcluirUsuarioComSucesso() {

        usuarioClient.excluirUsuario(UsuarioDataFactory.idValido())
        .then()
                .statusCode(200)
                .body(UsuariosConstants.MESSAGE, equalToIgnoringCase(UsuariosConstants.MSG_EXCLUIR_COM_SUCESSO))
        ;
    }



    @Test
    @Tag("Funcional")
    public void testExcluirUsuarioComCarrinhoCadastrado () {
        String idUsuario = "0uxuPY0cbmQhpEz1";

        usuarioClient.excluirUsuario(idUsuario)
        .then()
                .statusCode(400)
                .body(UsuariosConstants.MESSAGE, equalToIgnoringCase(UsuariosConstants.MSG_EXCLUIR_USUARIO_COM_CARRINHO))
                .body("idCarrinho", notNullValue())
        ;
    }

    @Test
    @Tag("Funcional")
    public void testExcluirUsuarioInvalido() {

        usuarioClient.excluirUsuario(UsuarioDataFactory.idInvalido())
        .then()
                .statusCode(200)
                .body(UsuariosConstants.MESSAGE, equalToIgnoringCase(UsuariosConstants.MSG_EXCLUIR_ID_INVALIDO))
        ;
    }
}
