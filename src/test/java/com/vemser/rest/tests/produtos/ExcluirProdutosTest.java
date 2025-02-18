package com.vemser.rest.tests.produtos;

import com.vemser.rest.client.ProdutoClient;
import com.vemser.rest.data.factory.LoginDataFactory;
import com.vemser.rest.data.factory.ProdutoDataFactory;
import com.vemser.rest.utils.constants.ProdutoConstants;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ExcluirProdutosTest {

    private ProdutoClient produtoClient = new ProdutoClient();

    @Test
    public void testDeveExcluirProdutoComSucesso() {

        produtoClient.excluirProduto(LoginDataFactory.tokenAdministrador(), ProdutoDataFactory.idValido())
        .then()
                .statusCode(200)
                .body(ProdutoConstants.MESSAGE, equalToIgnoringCase(ProdutoConstants.MSG_EXCLUIDO_SUCESSO))
        ;
    }

    @Test
    public void testSchemaDeveExcluirProdutoComSucesso() {

        produtoClient.excluirProduto(LoginDataFactory.tokenAdministrador(), ProdutoDataFactory.idValido())
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/excluir_produto.json"))
        ;
    }

    @Test
    public void testTentarExcluirProdutoComTokenInvalido() {

        produtoClient.excluirProduto(LoginDataFactory.tokenInvalido(), ProdutoDataFactory.idValido())
        .then()
                .statusCode(401)
                .body(ProdutoConstants.MESSAGE, equalToIgnoringCase(ProdutoConstants.MSG_TOKEN_INVALIDO))
        ;
    }

    @Test
    public void testTentarExcluirProdutoComUsuarioSemPermissao() {

        produtoClient.excluirProduto(LoginDataFactory.tokenUsuarioComum(), ProdutoDataFactory.idValido())
        .then()
                .statusCode(403)
                .body(ProdutoConstants.MESSAGE, equalToIgnoringCase(ProdutoConstants.MSG_USUARIO_SEM_PERMISSAO))
        ;
    }

}
