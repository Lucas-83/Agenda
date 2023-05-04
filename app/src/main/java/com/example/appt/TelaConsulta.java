package com.example.appt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaConsulta extends AppCompatActivity {

    EditText et_nome, et_telefone;
    Button btn_anterior, btn_proximo, btn_voltar;

    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consulta);

        et_nome=(EditText) findViewById(R.id.et_nome_consulta);
        et_telefone= (EditText) findViewById(R.id.et_telefone_consulta);
        btn_anterior = (Button) findViewById(R.id.btn_anterior);
        btn_proximo = (Button) findViewById(R.id.btn_proximo);
        btn_voltar = (Button) findViewById(R.id.btn_voltar_consulta);

        cursor=BancoDados.buscarDados(this);
        if(cursor.getCount()!=0){
            mostrarDados();
        }else{
            CxMsg.mostrar("Nenhum registro encontrado",this);
        }
    }

    public void fechar_tela(View v) {this.finish();}

    public void  proximoRegistro(View v){
        try{
            cursor.moveToNext();
            mostrarDados();
        }catch(Exception ex){
            if(cursor.isAfterLast()){
                CxMsg.mostrar("Não existem mais registros",this);
            }else{
                CxMsg.mostrar("Erro ao navegar pelos registros",this);
            }
        }
    }
    public void  registroAnterior(View v){
        try{
            cursor.moveToPrevious();
            mostrarDados();
        }catch(Exception ex){
            if(cursor.isBeforeFirst()){
                CxMsg.mostrar("Não existem mais registros",this);
            }else{
                CxMsg.mostrar("Erro ao navegar pelos registros", this);
            }
        }
    }


    @SuppressLint("Range")
    public void mostrarDados(){
        et_nome.setText(cursor.getString(cursor.getColumnIndex("nome")));
        et_telefone.setText(cursor.getString(cursor.getColumnIndex("fone")));
    }


}

