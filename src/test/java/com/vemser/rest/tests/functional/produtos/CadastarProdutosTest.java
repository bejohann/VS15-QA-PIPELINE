package com.vemser.rest.tests.functional.produtos;

import com.vemser.rest.client.ProdutoClient;
import com.vemser.rest.data.factory.LoginDataFactory;
import com.vemser.rest.data.factory.ProdutoDataFactory;
import com.vemser.rest.model.Produto;
import com.vemser.rest.utils.constants.ProdutoConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class CadastarProdutosTest {

    private ProdutoClient produtoClient = new ProdutoClient();

    @Test
    public void testDeveCadastrarProdutoComSucesso() {

        produtoClient.cadastrarProduto(LoginDataFactory.tokenAdministrador(), ProdutoDataFactory.produtoValido())
        .then()
                .statusCode(201)
                .body(ProdutoConstants.MESSAGE, equalToIgnoringCase(ProdutoConstants.MSG_CADASTRO_SUCESSO))
                .body(ProdutoConstants.ID, notNullValue())
        ;
    }
}
