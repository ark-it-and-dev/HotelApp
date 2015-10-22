package br.metodista.hotelapp.helper;

import android.app.Activity;
import android.widget.EditText;

import br.metodista.hotelapp.R;
import br.metodista.hotelapp.model.Usuario;

/**
 * Created by Gustavo Assalin on 04/09/2015.
 */
public class LoginHelper {

    private Usuario usuario = Usuario.getInstance();

    private EditText txtLogin;
    private EditText txtSenha;

    public LoginHelper(Activity activity) {
        txtLogin = (EditText) activity.findViewById(R.id.txtLogin);
        txtSenha = (EditText) activity.findViewById(R.id.txtSenha);
    }

    public Usuario getUsuarioDoLogin() {
        String login = txtLogin.getText().toString();
        String senha = txtSenha.getText().toString();

        usuario.setLogin(login);
        usuario.setSenha(senha);

        return usuario;
    }
    
    public void setUsuarioDoLogin(String login, String senha) {
        txtLogin.setText(login);
        txtSenha.setText(senha);
    }
}
