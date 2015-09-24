package br.metodista.hotelapp.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

import br.metodista.hotelapp.R;
import br.metodista.hotelapp.adapter.ExpandableListAdapter;

public class Servicos extends AppCompatActivity {
    private ExpandableListAdapter adapter;
    private ExpandableListView expListView;
    private List<String> listGroup;
    private HashMap<String, List<String>> listChild;

    private Intent irPara;
    private Button btnCarrinho;
//    private ImageView imgAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servivos);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expList);
        btnCarrinho = (Button) findViewById(R.id.btnListaCarrinho);
        btnCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPara = new Intent(Servicos.this, Carrinho.class);
                startActivity(irPara);
            }
        });
//        imgAdd = (ImageView) findViewById(R.id.imgAdd);

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
                Toast.makeText(getApplicationContext(),
                        listGroup.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listGroup.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listGroup.get(groupPosition) + " : " + listChild.get(listGroup.get(groupPosition)).get(childPosition),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listGroup = new ArrayList<String>();
        listChild = new HashMap<String, List<String>>();

        listGroup.add("Pratos");
        listGroup.add("Bebidas");

        List<String> listChildPratos = new ArrayList<String>();
        listChildPratos.add("Ribeye 380g " + " R$ 250,00");
        listChildPratos.add("Conflit de Pimentões " + " R$ 70,00");
        listChildPratos.add("Trop Kobe Beef-Baby beef c batata soufflés " + " R$ 240,00");
        listChildPratos.add("The Burguer Lab Star " + " R$ 200,00");
        listChildPratos.add("Moqueca de Camarão " + " R$ 80,00");
        listChildPratos.add("Lagosta ao Thermidor " + " R$ 190,00");
        listChildPratos.add("Camarão a Provençal " + " R$ 150,00");
        listChildPratos.add("Musseline de Mandioquinha " + " R$ 100,00");
        listChildPratos.add("Green Curry de Camarões " + " R$ 120,00");
        listChildPratos.add("Pirarucu " + " R$ 90,00");

        List<String> listChildBebidas = new ArrayList<String>();
        listChildBebidas.add("Coca-Cola (lata) " + " R$ 4,00");
        listChildBebidas.add("Guaraná (lata) " + " R$ 4,00");
        listChildBebidas.add("Caipirinha " + " R$ 15,00");
        listChildBebidas.add("Mojito " + " R$ 7,00");
        listChildBebidas.add("Vinho Tinto " + " R$ 35,00");
        listChildBebidas.add("Whisky Red Label 8 anos " + " R$ 100,00");

        listChild.put(listGroup.get(0), listChildPratos);
        listChild.put(listGroup.get(1), listChildBebidas);
    }
}
