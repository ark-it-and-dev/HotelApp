package br.metodista.hotelapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.metodista.hotelapp.R;

public class Abertura extends AppCompatActivity {

    private Intent irPara;

    private Button btnReservar;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abertura);

        btnReservar = (Button) findViewById(R.id.btnRealizarReserva);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPara = new Intent(Abertura.this, ReservarQuarto.class);
                startActivity(irPara);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPara = new Intent(Abertura.this, Login.class);
                startActivity(irPara);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_abertura, menu);
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

            case R.id.action_localizacao:
                Toast.makeText(this, "Função em desenvolvimento", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
