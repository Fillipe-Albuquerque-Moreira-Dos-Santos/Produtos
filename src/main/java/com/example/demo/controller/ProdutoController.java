package com.example.demo.controller;

import com.example.demo.ProdutoMapper.ProdutoMapper;
import com.example.demo.entities.Produto;
import com.example.demo.model.ProdutoDTO;
import com.example.demo.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    @PostMapping("/salvar-produtos")
    public ProdutoDTO salvar(@RequestBody ProdutoDTO produtoDTO) {
        Produto produto = produtoMapper.toEntity(produtoDTO);
        produto = produtoRepository.save(produto);
        return produtoMapper.toDTO(produto);
    }

    @GetMapping("/listar/{id}")
    public ProdutoDTO listar(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return produtoMapper.toDTO(produto);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoDTO>> listarTodos() {
        List<Produto> produtos = produtoRepository.findAll();

        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ProdutoDTO> produtoDTOs = produtos.stream().map(produtoMapper::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok(produtoDTOs);
    }


    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return ResponseEntity.ok("Produto deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
    }


    @PutMapping("/atualizar/{id}")
    public ProdutoDTO atualizar(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = produtoMapper.toEntity(produtoDTO);
        return produtoMapper.toDTO(produtoRepository.save(produto));
    }

}
