package com.vemser.rest.client;

import com.vemser.rest.model.Produto;
import com.vemser.rest.utils.constants.ProdutoConstants;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ProdutoClient extends BaseClient{

    private final String PRODUTOS = ProdutoConstants.ENDPOINT_PRODUTOS;
    private final String PRODUTOS_POR_ID = ProdutoConstants.ENDPOINT_PRODUTOS_POR_ID;

    public Response listarProdutos() {

        return
                given()
                        .spec(super.set())
                .when()
                        .get(PRODUTOS)
                ;

    }

    public Response listarProdutosPorNome(String nome) {
        return
                given()
                        .spec(super.set())
                        .queryParam(ProdutoConstants.NOME, nome)
                .when()
                        .get(PRODUTOS)
                ;
    }

    public Response listarProdutosPorDescricao(String descricao) {
        return
                given()
                        .spec(super.set())
                        .queryParam(ProdutoConstants.DESCRICAO, descricao)
                .when()
                        .get(PRODUTOS)
                ;
    }

    public Response listarProdutoPorId(String id) {
        return
                given()
                        .spec(super.set())
                        .pathParams(ProdutoConstants.ID, id)
                .when()
                        .get(PRODUTOS_POR_ID)
                ;
    }

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

    public Response excluirProduto(String token, String id) {
        return
                given()
                        .spec(super.set())
                        .header(ProdutoConstants.AUTHORIZATION, token)
                        .pathParams(ProdutoConstants.ID, id)
                .when()
                        .delete(PRODUTOS_POR_ID)
                ;
    }

    public Response atualizarProduto(String token, String id, Produto produto) {
        return
                given()
                        .spec(super.set())
                        .header(ProdutoConstants.AUTHORIZATION, token)
                        .pathParams(ProdutoConstants.ID, id)
                        .body(produto)
                .when()
                        .put(PRODUTOS_POR_ID)
                ;
    }

}
