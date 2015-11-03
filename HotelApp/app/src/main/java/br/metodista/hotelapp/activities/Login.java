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
import br.metodista.hotelapp.iterator.UsuarioIterator;
import br.metodista.hotelapp.model.Usuario;
import br.metodista.hotelapp.model.UsuarioLogado;
import br.metodista.hotelapp.webservice.UsuarioService;

public class Login extends AppCompatActivity {

    private UsuarioService service = new UsuarioService();
//    protected List<Usuario> listaUsuarios;

    private UsuarioIterator usuIterator;

    private UsuarioDAO dao = UsuarioDAO.getInstance(Login.this);
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

        helper = new LoginHelper(this);

        new CarregarUsuarios().execute();


//        String msg = "null";
//        if(!UsuarioLogado.getInstance().getLogin().equals("") || UsuarioLogado.getInstance().getLogin() != null) {
//            msg = "nulo";
//        }
//        builder.setMessage(msg);
//
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        });
//
//        alerta = builder.create();
//        alerta.show();

        builder = new AlertDialog.Builder(Login.this);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!helper.validaCampos()) {
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
                    usuario = helper.getUsuarioDoLogin();
                    boolean alertaMsg = true;

                    while(usuIterator.hasNext()) {
                        if (usuario.getLogin().equals(usuIterator.atual().getLogin()) && usuario.getSenha().equals(usuIterator.atual().getSenha())) {
                            builder.setMessage("Deseja manter os dados para conexão automática?");

                            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dao.inserir(usuario);

                                    irPara = new Intent(Login.this, Servicos.class);
                                    startActivity(irPara);
                                }
                            });

                            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    irPara = new Intent(Login.this, Servicos.class);
                                    startActivity(irPara);
                                }
                            });

                            UsuarioLogado.getInstance().setLogin(usuario.getLogin());
                            UsuarioLogado.getInstance().setSenha(usuario.getSenha());
                            UsuarioLogado.getInstance().setListaProduto(usuario.getListaProduto());

                            alerta = builder.create();
                            alerta.show();

                            alertaMsg = false;

                            return;
                        }

                        usuIterator.next();
                    }

                    if(alertaMsg) {
                        builder.setTitle("");
                        builder.setMessage("Dados Invalidos");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta = builder.create();
                        alerta.show();
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

            usuIterator = new UsuarioIterator(usuarios);

            progressDialog.dismiss();
        }
    }
}
