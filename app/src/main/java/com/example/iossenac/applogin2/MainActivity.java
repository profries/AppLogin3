package com.example.iossenac.applogin2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iossenac.applogin2.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final static int LOGIN_REQUEST = 10;

    private List<Usuario> listaUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciaUsuarios();
    }


    public void realizarLogin(View view){
        EditText textoNome = (EditText) findViewById(R.id.textoNome);
        String nome = textoNome.getText().toString();
        EditText textoSenha = (EditText) findViewById(R.id.textoSenha);
        String senha = textoSenha.getText().toString();

        boolean loginSucesso = false;

        for(Usuario u: listaUsuarios){
            if(u.verificaLogin(nome,senha)){
                loginSucesso = true;
                abrirTelaBemVindo(u);
            }
        }

        if(!loginSucesso){
            Toast.makeText(this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
            zeraCampos();
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        zeraCampos();

    }

    public void zeraCampos(){
        EditText textoNome = (EditText) findViewById(R.id.textoNome);
        EditText textoSenha = (EditText) findViewById(R.id.textoSenha);

        textoNome.setText("");
        textoNome.requestFocus();
        textoSenha.setText("");
    }

    private void abrirTelaBemVindo(Usuario u) {
        Intent it = new Intent(this, BemVindoActivity.class);
        it.putExtra("usuario",u);
        it.putExtra("indice",listaUsuarios.indexOf(u));
        startActivityForResult(it,LOGIN_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == LOGIN_REQUEST){

            if(resultCode == RESULT_OK){
                //Vem da Activity BemVindoActivity
                Usuario user = (Usuario) data.getSerializableExtra("user");
                int index = data.getIntExtra("index",0);
                listaUsuarios.set(index,user);
            }

        }

    }

    private void iniciaUsuarios() {
        listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("teste1","123","Teste1"));
        listaUsuarios.add(new Usuario("teste2","123","Teste2"));
        listaUsuarios.add(new Usuario("teste3","123","Teste3"));
    }
}
