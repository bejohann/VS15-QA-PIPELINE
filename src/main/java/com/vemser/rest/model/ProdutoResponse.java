package com.vemser.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponse {
    private String nome;
    private int preco;
    private String descricao;
    private int quantidade;
    @JsonProperty("_id")
    private String id;
}
