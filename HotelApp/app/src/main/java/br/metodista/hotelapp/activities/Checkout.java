package br.metodista.hotelapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.metodista.hotelapp.R;

public class Checkout extends AppCompatActivity {

    private Intent irPara;

    private Button btnFinalizarCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        btnFinalizarCheckout = (Button) findViewById(R.id.btnFinalizarCheckout);

        btnFinalizarCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Checkout.this);
                builder.setTitle("Confirmação de Checkout");
                builder.setMessage("Deseja realmente realizar o checkout?\n\nObs.: Essa opção não poderá ser desfeita.");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Checkout.this, "Obrigado Pela Preferência", Toast.LENGTH_LONG).show();
                        irPara = new Intent(Checkout.this, Abertura.class);
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
                irPara = new Intent(Checkout.this, Contato.class);

                startActivity(irPara);

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
