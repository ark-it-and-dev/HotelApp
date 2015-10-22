package br.metodista.hotelapp.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.metodista.hotelapp.R;
import br.metodista.hotelapp.dao.UsuarioDAO;
import br.metodista.hotelapp.helper.LoginHelper;
import br.metodista.hotelapp.model.Usuario;
import br.metodista.hotelapp.webservice.UsuarioService;

public class Login extends AppCompatActivity {

    private UsuarioService service = new UsuarioService();
    protected List<Usuario> listaUsuarios;

    private UsuarioDAO dao = new UsuarioDAO(Login.this);
    private AlertDialog.Builder builder;
    private AlertDialog alerta;

    private Usuario usuario;
    private LoginHelper helper;

    private Intent irPara;

    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        new CarregarUsuarios().execute();

        helper = new LoginHelper(this);

        usuario = dao.buscar();
        if(usuario != null) {
            helper.setUsuarioDoLogin(usuario.getLogin(), usuario.getSenha());
        }

        builder = new AlertDialog.Builder(Login.this);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usuario.getLogin().equals("") || usuario.getSenha().equals("")) {
                    builder.setTitle("");
                    builder.setMessage("Está faltando informação!");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    alerta = builder.create();
                    alerta.show();
                } else {
                    if (helper.getUsuarioDoLogin().getLogin().equals(usuario.getLogin()) && helper.getUsuarioDoLogin().getSenha().equals(usuario.getSenha())) {
                        irPara = new Intent(Login.this, Home.class);
                        startActivity(irPara);
                    } else {
                        for (Usuario usuarioAux : listaUsuarios) {
                            if(helper.getUsuarioDoLogin().getLogin().equals(usuarioAux.getLogin()) && helper.getUsuarioDoLogin().getSenha().equals(usuarioAux.getSenha())) {
                                builder.setTitle("");
                                builder.setMessage("Deseja manter os dados para conexão automática?");

                                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (dao.usuarioNaTabela()) {
                                            builder.setTitle("");
                                            builder.setMessage("Continuando esta ação, login automático do antigo usuário não será mais permitido.\nDeseja continuar?");

                                            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dao.inserir(usuario);

                                                    irPara = new Intent(Login.this, Home.class);
                                                    startActivity(irPara);
                                                }
                                            });

                                            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    irPara = new Intent(Login.this, Home.class);
                                                    startActivity(irPara);
                                                }
                                            });
                                            alerta = builder.create();
                                            alerta.show();
                                        } else {
                                            dao.inserir(usuario);
                                        }

                                        dao.close();
                                    }
                                });

                                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        irPara = new Intent(Login.this, Home.class);
                                        startActivity(irPara);
                                    }
                                });
                                alerta = builder.create();
                                alerta.show();
                            }
                        }
                    }
                }
            }
        });
    }

    private class CarregarUsuarios extends AsyncTask<String, Void, List<Usuario>> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(Login.this);
            progressDialog.show();
        }

        @Override
        protected List<Usuario> doInBackground(String... params) {
            return service.getAll();
        }

        @Override
        protected void onPostExecute(List<Usuario> usuarios) {
            super.onPostExecute(usuarios);

            listaUsuarios = usuarios;

            progressDialog.dismiss();
        }
    }
}
