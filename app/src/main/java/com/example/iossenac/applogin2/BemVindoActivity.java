package com.example.iossenac.applogin2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BemVindoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);

        TextView labelBemVindo = (TextView) findViewById(R.id.labelBemVindo);

        Intent it = getIntent();
        String nomeCompleto = it.getStringExtra("nomeCompleto");

        labelBemVindo.setText("Seja bem vindo, "+nomeCompleto+"!");

    }

    public void realizarLogoff(View view){
        finish();
    }
}
