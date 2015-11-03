package br.metodista.hotelapp.model;

/**
 * Created by Gustavo Assalin on 02/11/2015.
 */
public class UsuarioLogado extends Usuario {
    private static UsuarioLogado logado;

    private UsuarioLogado() {
    }

    public static UsuarioLogado getInstance() {
        if(logado == null) {
            logado = new UsuarioLogado();
        }

        return logado;
    }
}
