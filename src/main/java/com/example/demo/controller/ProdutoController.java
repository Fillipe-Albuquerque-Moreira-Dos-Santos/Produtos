package com.example.demo.controller;

import com.example.demo.ProdutoMapper.ProdutoMapper;
import com.example.demo.entities.Produto;
import com.example.demo.model.ProdutoDTO;
import com.example.demo.repository.ProdutoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    @Operation(summary = "Salvar um novo produto", description = "Recebe um ProdutoDTO e salva no banco de dados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PostMapping("/salvar-produtos")
    public ProdutoDTO salvar(@RequestBody ProdutoDTO produtoDTO) {
        Produto produto = produtoMapper.toEntity(produtoDTO);
        produto = produtoRepository.save(produto);
        return produtoMapper.toDTO(produto);
    }

    @Operation(summary = "Buscar produto por ID", description = "Retorna um produto baseado no ID fornecido.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/listar/{id}")
    public ProdutoDTO listar(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        return produtoMapper.toDTO(produto);
    }

    @Operation(summary = "Listar todos os produtos", description = "Retorna todos os produtos cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
    @GetMapping("/listarTodos")
    public ResponseEntity<List<Produto>> listarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        return ResponseEntity.ok(produtos);
    }

    @Operation(summary = "Deletar um produto", description = "Remove um produto pelo ID fornecido.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return ResponseEntity.ok("Produto deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
    }

    @Operation(summary = "Atualizar um produto", description = "Atualiza um produto pelo ID fornecido.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    public ProdutoDTO atualizar(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = produtoMapper.toEntity(produtoDTO);
        return produtoMapper.toDTO(produtoRepository.save(produto));
    }
}
