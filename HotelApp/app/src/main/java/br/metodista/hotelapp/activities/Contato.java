package br.metodista.hotelapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.metodista.hotelapp.R;

public class Contato extends AppCompatActivity {

    private Intent irPara;

    private EditText txtEmail;
    private EditText txtMensagem;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtMensagem = (EditText) findViewById(R.id.txtMensagem);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String mensagem = txtMensagem.getText().toString();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contato, menu);
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
            case R.id.action_ligar:
                irPara = new Intent(Intent.ACTION_CALL);
                String numero = "+5511959410310";
                Uri telefoneHotel = Uri.parse("tel:" + numero);
                irPara.setData(telefoneHotel);
                startActivity(irPara);

                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
