package br.metodista.hotelapp.model;

import br.metodista.hotelapp.enumeration.CategoriaProduto;

/**
 * Created by Gustavo Assalin on 15/10/2015.
 */
public class Produto {
    private String _id;
    private String nome;
    private String descricao;
    private double preco;
    private CategoriaProduto categoria;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }
}
