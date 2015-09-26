package br.metodista.hotelapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.metodista.hotelapp.R;
import br.metodista.hotelapp.adapter.ExpandableListAdapter;
import br.metodista.hotelapp.adapter.ExpandableListCarrinhoAdapter;

/**
 * Created by Gustavo Assalin on 24/09/2015.
 */
public class Carrinho extends AppCompatActivity {
    private ExpandableListCarrinhoAdapter adapter;
    private ExpandableListView expListView;
    private List<String> listGroup;
    private HashMap<String, List<String>> listChild;

//    private ImageView imgAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expListCarrinho);
//        imgAdd = (ImageView) findViewById(R.id.imgAdd);

        // preparing list data
        prepareListData();

        adapter = new ExpandableListCarrinhoAdapter(this, listGroup, listChild);

        // setting list adapter
        expListView.setAdapter(adapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listGroup.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listGroup.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listGroup.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child_servicos click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

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
        listChildPratos.add("Pirarucu " + " R$ 90,00");

        List<String> listChildBebidas = new ArrayList<String>();
        listChildBebidas.add("Caipirinha " + " R$ 15,00");
        listChildBebidas.add("Caipirinha " + " R$ 15,00");

        listChild.put(listGroup.get(0), listChildPratos);
        listChild.put(listGroup.get(1), listChildBebidas);
    }
}
