package br.metodista.hotelapp.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.support.v7.app.AppCompatActivity;

import br.metodista.hotelapp.R;
import br.metodista.hotelapp.adapter.ExpandableListAdapter;
import br.metodista.hotelapp.model.Produto;
import br.metodista.hotelapp.model.Usuario;
import br.metodista.hotelapp.webservice.ProdutoService;

public class Servicos extends AppCompatActivity {
    //URL to get JSON Array

    protected ProdutoService service = new ProdutoService();
    protected List<Produto> listaProdutos;

    private ExpandableListAdapter adapter;
    private ExpandableListView expListView;
    private List<String> listGroup;
    private HashMap<String, List<String>> listChild;

    private Intent irPara;

    private void carregarDados() {
//        new CarregarProdutos().execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servivos);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expListServicos);

        // preparing list data
        prepareListData();

        adapter = new ExpandableListAdapter(this, listGroup, listChild);

        // setting list adapter
        expListView.setAdapter(adapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listGroup.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listGroup.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listGroup.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child_servicos click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
//                Toast.makeText(
//                        getApplicationContext(),
//                        listGroup.get(groupPosition) + " : " + listChild.get(listGroup.get(groupPosition)).get(childPosition),
//                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregarDados();
    }

    /*
     * Preparing the list data
    */
    private void prepareListData() {
        listGroup = new ArrayList<String>();
        listChild = new HashMap<String, List<String>>();

        List<String> listChildPratos = new ArrayList<String>();
        List<String> listChildBebidas = new ArrayList<String>();

        for(Produto produto : listaProdutos) {
            switch (produto.getCategoria().toString()) {
                case "PRATO":
                    listChildPratos.add(produto.getNome() + "\n R$" + produto.getPreco());
                    break;

                case "BEBIDA":
                    listChildBebidas.add(produto.getNome() + "\n R$" + produto.getPreco());
                    break;
            }
        }

        listChild.put(listGroup.get(0), listChildPratos);
        listChild.put(listGroup.get(1), listChildBebidas);
    }

    private void createListGroup(String grupo) {
        listGroup = new ArrayList<String>();

        if(grupo != null) {
            listGroup.add(grupo);
        }
    }

    private void createListChild(String child, String tituloGrupo) {
        listChild = new HashMap<String, List<String>>();

        List<String> listChildren;

        if(child != null) {
            listChildren = new ArrayList<String>();

            listChildren.add(child);

            listChild.put(listGroup.get(listGroup.indexOf(tituloGrupo)), listChildren);
        }
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
                        Usuario.encerrarSessao();

                        onDestroy();

                        irPara = new Intent(Servicos.this, Abertura.class);
                        startActivity(irPara);
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                AlertDialog alerta = builder.create();
                alerta.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private class CarregarProdutos extends AsyncTask<String, Void, List<Produto>> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(Servicos.this);
            progressDialog.show();
        }

        @Override
        protected List<Produto> doInBackground(String... params) {
            return service.getAll();
        }

        @Override
        protected void onPostExecute(List<Produto> produtos) {
            super.onPostExecute(produtos);

            listaProdutos = produtos;

            progressDialog.dismiss();
        }
    }
}
