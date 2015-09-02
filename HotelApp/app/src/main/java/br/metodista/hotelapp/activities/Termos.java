package br.metodista.hotelapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import android.support.v7.appcompat.R;
import br.metodista.hotelapp.R;

public class Termos extends Activity {

    private Intent irPara;

    private Button btnAceitar;
    private Button btnNaoAceitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termos);

        btnAceitar = (Button) findViewById(R.id.btnConcordo);
        btnNaoAceitar = (Button) findViewById(R.id.btnNaoConcordo);

        btnAceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPara = new Intent(Termos.this, Home.class);
                startActivity(irPara);
            }
        });

        btnNaoAceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPara = new Intent(Termos.this, Login.class);
                startActivity(irPara);
            }

        });
    }

}
