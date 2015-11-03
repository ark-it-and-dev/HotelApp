package br.metodista.hotelapp.model;

import java.util.List;

/**
 * Created by Gustavo Assalin on 26/10/2015.
 */
public class Usuario {
    private String _id;
    private List<Produto> listaProduto;
    private String login;
    private String senha;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<Produto> getListaProduto() {
        return listaProduto;
    }

    public void setListaProduto(List<Produto> listaProduto) {
        this.listaProduto = listaProduto;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
