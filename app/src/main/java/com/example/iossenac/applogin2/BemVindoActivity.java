package com.example.iossenac.applogin2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.iossenac.applogin2.model.Usuario;

public class BemVindoActivity extends AppCompatActivity {
    private Usuario usuario;
    private int indice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);

        TextView labelBemVindo = (TextView) findViewById(R.id.labelBemVindo);

        Intent it = getIntent();
        this.usuario = (Usuario) it.getSerializableExtra("usuario");
        this.indice = it.getIntExtra("indice",0);

        labelBemVindo.setText("Seja bem vindo, "+usuario.getNomeCompleto()+"!");

        EditText textoSenhaBemVindo = (EditText) findViewById(R.id.textoSenhaBemVindo);
        textoSenhaBemVindo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Button botaoAlterarSenha = (Button) findViewById(R.id.botaoAlterarSenha);
                if(s.length() >= 3)
                    botaoAlterarSenha.setEnabled(true);
                else
                    botaoAlterarSenha.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

    public void alterarSenha(View view){
        EditText textoSenhaBemVindo = (EditText) findViewById(R.id.textoSenhaBemVindo);
        usuario.setSenha(textoSenhaBemVindo.getText().toString());
        Intent it = new Intent();
        it.putExtra("user", this.usuario);
        it.putExtra("index",this.indice);
        setResult(RESULT_OK, it);
        finish();

    }

    public void realizarLogout(View view){
        finish();
    }


}
