package br.metodista.hotelapp.StandBy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import br.metodista.hotelapp.R;
import br.metodista.hotelapp.activities.Abertura;
import br.metodista.hotelapp.activities.Contato;


public class Home extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private Intent irPara;

    private View view;
    private FrameLayout frameLayout;

    private Button btnSobre;
    private Button btnGuiaEntretenimento;
    private Button btnPacotes;
    private Button btnPromocoes;
    private Button btnLocomocao;
    private Button btnMapa;

    private Menu menu;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

//        btnSobre = (Button) findViewById(R.id.btnSobre);
//        btnSobre.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                irPara = new Intent(Home.this, Home_Sobre.class);
//                startActivity(irPara);
//            }
//        });
//        btnGuiaEntretenimento = (Button) findViewById(R.id.btnGuiaEntretenimento);
//        btnPacotes = (Button) findViewById(R.id.btnPacotes);
//        btnPromocoes = (Button) findViewById(R.id.btnPromocoes);
//        btnLocomocao = (Button) findViewById(R.id.btnLocomocao);
//        btnMapa = (Button) findViewById(R.id.btnMapa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_default, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_contactar:
                irPara = new Intent(this, Contato.class);
                startActivity(irPara);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();
    }

    public void onSectionAttached(int number) {
        frameLayout = (FrameLayout) findViewById(R.id.container);

        frameLayout.removeAllViews();
        frameLayout.invalidate();

        switch (number) {
            case 1: //Opcao Inicio
                Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show();
                view = LayoutInflater.from(this).inflate(R.layout.fragment_inicio, null);

                frameLayout.addView(view);

                break;

            case 2: //Opcao Financeiro
                Toast.makeText(this, "Financeiro", Toast.LENGTH_SHORT).show();
                view = LayoutInflater.from(this).inflate(R.layout.fragment_financeiro, null);

                frameLayout.addView(view);

                break;

            case 3: //Opcao Servico
                Toast.makeText(this, "Servico", Toast.LENGTH_SHORT).show();
                view = LayoutInflater.from(this).inflate(R.layout.fragment_servico, null);
                getMenuInflater().inflate(R.menu.menu_financeiro, menu);

                frameLayout.addView(view);

                break;

            case 4: //Opcao Sair
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Confirmação de Logoff");
                builder.setMessage("Deseja realmente realizar o logoff?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Home.this, "Saindo", Toast.LENGTH_SHORT).show();
                        irPara = new Intent(Home.this, Abertura.class);
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
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Home) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
