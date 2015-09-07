package br.metodista.hotelapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.metodista.hotelapp.R;
import helper.LoginHelper;
import model.Usuario;

public class Login extends AppCompatActivity {

    private Usuario usuario;
    private LoginHelper helper;

    private Intent irPara;

    private Button btnCancelar;
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        helper = new LoginHelper(this);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
//
//                builder.setTitle("");
//                builder.setMessage("Deseja manter os dados para conexão automática?");
//
//                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        usuario = helper.getUsuarioDoLogin();
//
//                        UsuarioDAO dao = new UsuarioDAO(Login.this);
//                        dao.inserir(usuario);
//                        dao.close();
//
//                        irPara = new Intent(Login.this, Home.class);
//                        startActivity(irPara);
//                    }
//                });
//
//                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        irPara = new Intent(Login.this, Home.class);
//                        startActivity(irPara);
//                    }
//                });
//                AlertDialog alerta = builder.create();
//                alerta.show();

                irPara = new Intent(Login.this, Home.class);
                startActivity(irPara);
            }
        });
    }

}
