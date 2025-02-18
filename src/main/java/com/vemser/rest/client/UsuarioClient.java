package com.vemser.rest.client;

import com.vemser.rest.model.Usuario;
import com.vemser.rest.utils.constants.UsuariosConstants;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UsuarioClient extends BaseClient {

    private final String USUARIOS = UsuariosConstants.ENDPOINT_USUARIOS;
    private final String USUARIOS_POR_ID = UsuariosConstants.ENDPOINT_USUARIOS_ID;

    public Response cadastrarUsuario(Usuario usuario) {

        return
                given()
                        .spec(super.set())
                        .body(usuario)
                .when()
                        .post(USUARIOS)
                ;
    }

    public Response listarUsuarios() {

        return
                given()
                        .spec(super.set())
                .when()
                        .get(USUARIOS)
                ;
    }

    public Response listarUsuarioPorNome(String nome) {
        return
                given()
                        .spec(super.set())
                        .queryParam(UsuariosConstants.NOME, nome)
                .when()
                        .get(USUARIOS)
                ;
    }

    public Response listarUsuarioPorId(String id) {
        return
                given()
                        .spec(super.set())
                        .pathParams(UsuariosConstants.ID, id)
                .when()
                        .get(USUARIOS_POR_ID)
                ;
    }

    public Response excluirUsuario(String id) {
        return
                given()
                        .spec(super.set())
                        .pathParams(UsuariosConstants.ID, id)
                .when()
                        .delete(USUARIOS_POR_ID)
                ;
    }

    public Response atualizarUsuario(String id, Usuario usuario) {
        return
                given()
                        .spec(super.set())
                        .pathParams(UsuariosConstants.ID, id)
                        .body(usuario)
                .when()
                        .put(USUARIOS_POR_ID)
                ;
    }

}
