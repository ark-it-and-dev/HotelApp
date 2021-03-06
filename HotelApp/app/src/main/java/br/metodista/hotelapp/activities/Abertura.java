package br.metodista.hotelapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import br.metodista.hotelapp.R;
import br.metodista.hotelapp.dao.UsuarioDAO;
import br.metodista.hotelapp.model.UsuarioLogado;

public class Abertura extends AppCompatActivity {

    private UsuarioDAO dao = UsuarioDAO.getInstance(Abertura.this);

    private Intent irPara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abertura);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_abertura, menu);
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
            case R.id.action_login:
                if(UsuarioLogado.getInstance().getLogin() == null || UsuarioLogado.getInstance().getSenha() == null || UsuarioLogado.getInstance().getLogin().equals("") || UsuarioLogado.getInstance().getSenha().equals("")) {
                    irPara = new Intent(this, Login.class);
                    startActivity(irPara);
                } else {
                    irPara = new Intent(this, Servicos.class);
                    startActivity(irPara);
                }
                break;

            case R.id.action_contactar:
                irPara = new Intent(this, Contato.class);
                startActivity(irPara);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
