package com.example.demo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProdutoDTO {

    private Long id;
    private String descricao;
    private double preco;
    private String nome;

}
