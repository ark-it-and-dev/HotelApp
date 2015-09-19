package br.metodista.hotelapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import model.Usuario;

/**
 * Created by Gustavo Assalin on 04/09/2015.
 */
public class UsuarioDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "NomeDoBanco";
    private static final String TABELA = "NomeDaTabela";
    private static final int VERSAO = 1;

    public UsuarioDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE " + TABELA + " (" +
                        "id INTEGER PRYMARY KEY, " +
                        "login TEXT UNIQUE NOT NULL, " +
                        "senha TEXT UNIQUE NOT NULL" +
                        ");";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inserir(Usuario usuario) {
        ContentValues cv = new ContentValues();

        cv.put("login", usuario.getLogin());
        cv.put("senha", usuario.getSenha());

        getWritableDatabase().insert(TABELA, null, cv);
    }

    public Usuario buscar() {
        String sql = "SELECT * FROM " + TABELA + " ;";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        Usuario usuario = new Usuario();
        usuario.setLogin(cursor.getString(cursor.getColumnIndex("login")));
        usuario.setSenha(cursor.getString(cursor.getColumnIndex("senha")));

        return usuario;
    }

    public void deletar(SQLiteDatabase db) {
        String sql = "DELETE * FROM " + TABELA + " ;";

        db.execSQL(sql);
    }

}
