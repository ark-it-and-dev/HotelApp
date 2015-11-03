package br.metodista.hotelapp.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.metodista.hotelapp.R;
import br.metodista.hotelapp.model.Produto;
import br.metodista.hotelapp.model.Usuario;
import br.metodista.hotelapp.model.UsuarioLogado;
import br.metodista.hotelapp.webservice.FinanceiroService;

public class Financeiro extends AppCompatActivity {
    private FinanceiroService service = new FinanceiroService();

    private Intent irPara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new CarregarConta().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_default, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_contactar:
                irPara = new Intent(Financeiro.this, Contato.class);
                startActivity(irPara);

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private class CarregarConta extends AsyncTask<String, Void, List<Usuario>> {
        private ProgressDialog dialog;

//        private Activity activity;

        private List<Produto> listaConsumo = new ArrayList<Produto>();
        private double valorTotal = 0.0;

//        public CarregarConta(Activity activity) {
//            this.activity = activity;
//        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(Financeiro.this);
            dialog.setMessage("Buscando...");
            dialog.show();
        }

        @Override
        protected List<Usuario> doInBackground(String... params) {
            service.getAll();

            return null;
        }

        @Override
        protected void onPostExecute(List<Usuario> usuarios) {
            super.onPostExecute(usuarios);

            for(Usuario usuario : usuarios) {
                if(usuario.getLogin().equals(UsuarioLogado.getInstance().getLogin()) && usuario.getSenha().equals(UsuarioLogado.getInstance().getSenha())) {
                    for(Produto p : usuario.getListaProduto()) {
                        valorTotal += p.getPreco();

                        listaConsumo.add(p);
                    }
                }

                return;
            }

            ScrollView scroll = new ScrollView(Financeiro.this);

            LinearLayout layoutGeral = new LinearLayout(Financeiro.this);
            layoutGeral.setOrientation(LinearLayout.VERTICAL);

            LinearLayout layout = new LinearLayout(Financeiro.this);
            layout.setOrientation(LinearLayout.HORIZONTAL);

            TextView txtValorTotal = new TextView(Financeiro.this);
            txtValorTotal.setText("Valor Total de Consumo:");
            txtValorTotal.setTextSize(17);

            TextView txtTotal = new TextView(Financeiro.this);
            txtTotal.setText("R$" + valorTotal);
            txtTotal.setTextSize(17);

            layout.addView(txtValorTotal);
            layout.addView(txtTotal);

            layoutGeral.addView(layout);

            TextView txtDetalhes = new TextView(Financeiro.this);
            txtValorTotal.setText("Detalhes:");
            txtValorTotal.setTextSize(17);

            layoutGeral.addView(txtDetalhes);

            if(listaConsumo != null) {
                for (Produto produto : listaConsumo) {
                    TextView nome = new TextView(Financeiro.this);
                    nome.setText(produto.getNome());
                    nome.setTextSize(17);

                    TextView preco = new TextView(Financeiro.this);
                    preco.setText("R$" + String.valueOf(produto.getPreco()));
                    preco.setTextSize(15);

                    TextView separador = new TextView(Financeiro.this);
                    separador.setText("___________________________________________________________");

                    layoutGeral.addView(nome);
                    layoutGeral.addView(preco);
                    layoutGeral.addView(separador);
                }
            } else {
                TextView msg = new TextView(Financeiro.this);
                msg.setText("Não há Consumo Registrado até o Momento");
                msg.setTextSize(25);

                layoutGeral.addView(msg);
            }

            scroll.addView(layoutGeral);

            Financeiro.this.setContentView(scroll);

            dialog.dismiss();
        }
    }

}
