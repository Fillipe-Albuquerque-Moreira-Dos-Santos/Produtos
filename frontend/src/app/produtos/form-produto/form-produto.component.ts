import { Component } from '@angular/core';
import { ProdutoService, Produto } from 'src/app/services/produto.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-produto',
  templateUrl: './form-produto.component.html',
  styleUrls: ['./form-produto.component.scss']
})
export class FormProdutoComponent {
  produto: Produto = {
    nome: '',
    preco: 0,
    descricao: ''
  };

  constructor(private produtoService: ProdutoService, private router: Router) {}

  salvar(): void {
    this.produtoService.salvar(this.produto).subscribe(() => {
      alert('Produto salvo com sucesso!');
      this.router.navigate(['/produtos']);
    });
  }
}
