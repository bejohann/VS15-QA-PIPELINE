package com.vemser.rest.tests.contrato;

import com.vemser.rest.client.LoginClient;
import com.vemser.rest.client.UsuarioClient;
import com.vemser.rest.data.factory.LoginDataFactory;
import com.vemser.rest.data.factory.UsuarioDataFactory;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ContratoTest {

    LoginClient loginClient = new LoginClient();
    UsuarioClient usuarioClient = new UsuarioClient();


    @Test
    public void testSchemaRealizarLoginComSucesso() {

        loginClient.realizarLogin(LoginDataFactory.loginValido())
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/realizar_login.json"))
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
    public void testSchemaDeveCadastrarUsuarioComDadosValidos() {

        usuarioClient.cadastrarUsuario(UsuarioDataFactory.usuarioValido())
        .then()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schemas/cadastrar_usuario.json"))
        ;
    }

    @Test
    public void testSchemaExcluirUsuarioComSucesso() {

        usuarioClient.excluirUsuario(UsuarioDataFactory.idValido())
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/excluir_usuario.json"))
        ;
    }

    @Test
    public void testSchemaListarTodosUsuariosComSucesso() {

        usuarioClient.listarUsuarios()
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/listar_todos_usuarios.json"))
        ;
    }

}
