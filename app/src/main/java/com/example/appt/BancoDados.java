package com.example.appt;


import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase; //Banco de dados
import android.database.Cursor; //Navegar entre os registros
import android.content.ContextWrapper;
import android.view.View;

public class BancoDados {
    static SQLiteDatabase db = null;
    static Cursor cursor;
    public static void abrirDB(Activity act) {

        try {
            ContextWrapper cw= new ContextWrapper(act);
            db =cw.openOrCreateDatabase("Banco agenda", MODE_PRIVATE, null);
        } catch (Exception ex) {
            CxMsg.mostrar("Erro ao abrir ou ao criar banco de dados", act);
        }
    }
    public static void fecharDB() {
        db.close();
    }
    public static void abrirOuCriarTabela(Activity act) {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS contatos(id INTEGER PRIMARY KEY, nome TEXT, fone TEXT)");
        } catch (Exception ex) {
            CxMsg.mostrar("Erro ao  criar tabela", act);
        }
    }

    public static void inserirRegistro(String nome, String fone,Activity act){
        abrirDB(act);
        try {
            db.execSQL("INSERT INTO contatos (nome,fone) VALUES('" + nome + "','" + fone + "')");

        }catch (Exception ex){
            CxMsg.mostrar("Erro ao inserir registro!", act);
        }finally {
            CxMsg.mostrar("Registro inserido com sucesso!", act);
        }
        fecharDB();
    }
    public static Cursor buscarDados(Activity act){
        abrirDB(act);
        cursor=db.query("contatos",
                new String[]{"nome","fone"}, null, null, null,null,null,null
        );
        cursor.moveToFirst();
        return cursor;

    }


}

