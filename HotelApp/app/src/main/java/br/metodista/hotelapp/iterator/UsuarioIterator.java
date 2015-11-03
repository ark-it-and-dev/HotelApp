package br.metodista.hotelapp.iterator;

import java.util.List;

import br.metodista.hotelapp.model.Usuario;

/**
 * Created by Gustavo Assalin on 02/11/2015.
 */
public class UsuarioIterator implements Iterator {
    private List<Usuario> usuarios;
    private int posicao = 0;

    public UsuarioIterator(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public boolean hasNext() {
        if (posicao >= usuarios.size() || usuarios.get(posicao) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Usuario atual() {
        return usuarios.get(posicao);
    }

    @Override
    public Usuario next() {
        Usuario usuario = usuarios.get(posicao);

        posicao++;

        return usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
}
