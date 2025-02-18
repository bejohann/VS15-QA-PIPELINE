package com.vemser.rest.utils.constants;

public class ProdutoConstants {

    public static final String MESSAGE = "message";
    public static final String ID = "_id";
    public static final String PRODUTOS = "produtos";
    public static final String NOME = "nome";
    public static final String PRECO = "preco";
    public static final String DESCRICAO = "descricao";
    public static final String QUANTIDADE = "quantidade";
    public static final String AUTHORIZATION = "authorization";
    public static final String ENDPOINT_PRODUTOS = "/produtos";
    public static final String ENDPOINT_PRODUTOS_POR_ID = "/produtos/{_id}";
    public static final String MSG_CADASTRO_SUCESSO = "Cadastro realizado com sucesso";
    public static final String MSG_NOME_PRODUTO_JA_CADASTRADO = "Já existe produto com esse nome";
    public static final String MSG_TOKEN_INVALIDO = "Token de acesso ausente, inválido, expirado ou usuário do token não existe mais";
    public static final String MSG_ALTERADO_SUCESSO = "Registro alterado com sucesso";
    public static final String MSG_USUARIO_SEM_PERMISSAO = "Rota exclusiva para administradores";
    public static final String MSG_EXCLUIDO_SUCESSO = "Registro excluído com sucesso";
    public static final String MSG_PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";
    public static final String MSG_NOME_EM_BRANCO = "nome não pode ficar em branco";
    public static final String MSG_DESCRICAO_EM_BRANCO = "descricao não pode ficar em branco";
    public static final String MSG_PRECO_NEGATIVO = "preco deve ser um número positivo";
    public static final String MSG_QUANTIDADE_NEGATIVA = "quantidade deve ser maior ou igual a 0";

}
