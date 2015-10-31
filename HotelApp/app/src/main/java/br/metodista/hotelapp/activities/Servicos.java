package br.metodista.hotelapp.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import br.metodista.hotelapp.R;
import br.metodista.hotelapp.model.Produto;
import br.metodista.hotelapp.webservice.ProdutoService;

public class Servicos extends AppCompatActivity {
    //URL to get JSON Array
    protected ProdutoService service = new ProdutoService();

    private Intent irPara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new CarregarProdutos(Servicos.this).execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_servicos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_financeiro:
                onDestroy();

                irPara = new Intent(this, Financeiro.class);
                startActivity(irPara);
                break;

            case R.id.action_checkout:
                onDestroy();

                irPara = new Intent(this, Checkout.class);
                startActivity(irPara);
                break;

            case R.id.action_contactar:
                onDestroy();

                irPara = new Intent(this, Contato.class);
                startActivity(irPara);
                break;

            case R.id.action_sair:
                AlertDialog.Builder builder = new AlertDialog.Builder(Servicos.this);
                builder.setTitle("Confirmação de Logoff");
                builder.setMessage("Deseja realmente realizar o logoff?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Usuario.encerrarSessao();

                        onDestroy();

                        irPara = new Intent(Servicos.this, Abertura.class);
                        startActivity(irPara);
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alerta = builder.create();
                alerta.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private class CarregarProdutos extends AsyncTask<String, Void, List<Produto>> {

        private ProgressDialog progressDialog;

        private List<Produto> listaBebidas = new ArrayList<>();
        private List<Produto> listaPratos = new ArrayList<>();

        private Activity activity;

        public CarregarProdutos(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(Servicos.this);
            progressDialog.setMessage("Buscando...");
            progressDialog.show();
        }

        @Override
        protected List<Produto> doInBackground(String... params) {
            return service.getAll();
        }

        @Override
        protected void onPostExecute(List<Produto> produtos) {
            super.onPostExecute(produtos);

            for (Produto produto : produtos) {
                switch (produto.getCategoria()) {
                    case BEBIDA:
                        listaBebidas.add(produto);
                        break;

                    case PRATO:
                        listaPratos.add(produto);
                        break;
                }
            }

            ScrollView scroll = new ScrollView(activity);

            LinearLayout layout = new LinearLayout(activity);
            layout.setOrientation(LinearLayout.VERTICAL);

            TextView textoListaBebidas = new TextView(activity);
            textoListaBebidas.setText("Bebidas Disponiveis:");
            textoListaBebidas.setTextSize(30);

            layout.addView(textoListaBebidas);

            for (Produto produto : listaBebidas) {
                TextView nome = new TextView(activity);
                nome.setText(produto.getNome());
                nome.setTextSize(17);

                TextView preco = new TextView(activity);
                preco.setText(String.valueOf(produto.getPreco()));
                preco.setTextSize(15);

                TextView separador = new TextView(activity);
                separador.setText("___________________________________________________________");

                layout.addView(nome);
                layout.addView(preco);
                layout.addView(separador);
            }

            TextView textoListaBebidas2 = new TextView(activity);
            textoListaBebidas2.setText("Pratos Disponiveis:");
            textoListaBebidas2.setTextSize(30);

            layout.addView(textoListaBebidas2);

            for (Produto produto : listaPratos) {
                TextView nome = new TextView(activity);
                nome.setText(produto.getNome());
                nome.setTextSize(17);

                TextView preco = new TextView(activity);
                preco.setText(String.valueOf(produto.getPreco()));
                preco.setTextSize(15);

                TextView separador = new TextView(activity);
                separador.setText("___________________________________________________________");

                layout.addView(nome);
                layout.addView(preco);
                layout.addView(separador);
            }

            scroll.addView(layout);

            activity.setContentView(scroll);

            progressDialog.dismiss();
        }
    }
}
