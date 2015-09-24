package br.metodista.hotelapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.metodista.hotelapp.R;

/**
 * Created by Gustavo Assalin on 22/09/2015.
 */
public class Home extends AppCompatActivity {

    private Intent irPara;

    private Button btnFinanceiro;
    private Button btnServicos;
    private Button btnCheckout;
    private Button btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnFinanceiro = (Button) findViewById(R.id.btnFinanceiro);
        btnServicos = (Button) findViewById(R.id.btnServicos);
        btnCheckout = (Button) findViewById(R.id.btnCheckout);
        btnSair = (Button) findViewById(R.id.btnSair);

        btnFinanceiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPara = new Intent(Home.this, Financeiro.class);
                startActivity(irPara);
            }
        });

        btnServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPara = new Intent(Home.this, Servicos.class);
                startActivity(irPara);
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPara = new Intent(Home.this, Checkout.class);
                startActivity(irPara);
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
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
            }
        });
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

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_contactar:
                irPara = new Intent(this, Contato.class);
                startActivity(irPara);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
