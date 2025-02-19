package com.vemser.rest.client;

import com.vemser.rest.model.Produto;
import com.vemser.rest.utils.constants.ProdutoConstants;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ProdutoClient extends BaseClient{

    private final String PRODUTOS = ProdutoConstants.ENDPOINT_PRODUTOS;

    public Response cadastrarProduto(String token, Produto produto) {
        return
                given()
                        .spec(super.set())
                        .header(ProdutoConstants.AUTHORIZATION, token)
                        .body(produto)
                .when()
                        .post(PRODUTOS)
                ;
    }
}
