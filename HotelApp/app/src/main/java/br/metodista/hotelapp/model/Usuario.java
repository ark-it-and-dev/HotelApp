package br.metodista.hotelapp.model;

public class Usuario {
    private static Usuario usuario;

    private Long id;
    private String login;
    private String senha;

    private Usuario() {}

    public static Usuario getInstance() {
        if(usuario == null) {
            return new Usuario();
        } else {
            return usuario;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return login;
    }
}
