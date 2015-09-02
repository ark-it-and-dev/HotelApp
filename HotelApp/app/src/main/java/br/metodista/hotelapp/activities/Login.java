package br.metodista.hotelapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.metodista.hotelapp.R;

public class Login extends Activity {

    private Intent irPara;

    private EditText txtLogin;
    private EditText txtSenha;
    private Button btnCancelar;
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtLogin = (EditText) findViewById(R.id.txtLogin);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPara = new Intent(Login.this, Termos.class);
                startActivity(irPara);
            }
        });
    }

}
