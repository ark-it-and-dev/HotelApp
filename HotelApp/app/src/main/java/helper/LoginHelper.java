package helper;

import android.app.Activity;
import android.widget.EditText;

import br.metodista.hotelapp.R;
import model.Usuario;

/**
 * Created by Gustavo Assalin on 04/09/2015.
 */
public class LoginHelper {

    private Usuario usuario;

    private EditText txtLogin;
    private EditText txtSenha;

    public LoginHelper(Activity activity) {
        usuario = new Usuario();

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
}