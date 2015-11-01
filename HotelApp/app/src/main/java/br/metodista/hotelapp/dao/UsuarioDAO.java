package br.metodista.hotelapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.metodista.hotelapp.model.Usuario;

/**
 * Created by Gustavo Assalin on 04/09/2015.
 */
public class UsuarioDAO extends SQLiteOpenHelper {
    private static UsuarioDAO usuarioDAO;

    private Usuario usuario = new Usuario();

    private static final String DATABASE = "NomeDoBanco";
    private static final String TABELA = "NomeDaTabela";
    private static final int VERSAO = 1;

    private UsuarioDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    public static UsuarioDAO getInstance(Context context) {
        if(usuarioDAO == null) {
            return new UsuarioDAO(context);
        } else {
            return usuarioDAO;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE " + TABELA + " (" +
                        "login TEXT UNIQUE NOT NULL, " +
                        "senha TEXT UNIQUE NOT NULL" +
                        ");";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inserir(Usuario usuario) {
        if(buscar() == null) {
            ContentValues cv = new ContentValues();

            cv.put("login", usuario.getLogin());
            cv.put("senha", usuario.getSenha());

            getWritableDatabase().insert(TABELA, null, cv);
        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS " + TABELA);

            onCreate(db);

            ContentValues cv = new ContentValues();

            cv.put("login", usuario.getLogin());
            cv.put("senha", usuario.getSenha());

            getWritableDatabase().insert(TABELA, null, cv);
        }
    }

    public boolean usuarioNaTabela() {
        if(buscar() == null) {
            return false;
        } else {
            return true;
        }
    }

    public Usuario buscar() {
        String sql = "SELECT * FROM " + TABELA + " ;";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        Usuario usuario = new Usuario();
        while (cursor.moveToNext()) {
            usuario.setLogin(cursor.getString(cursor.getColumnIndex("login")));
            usuario.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
        }

        return usuario;
    }

    public void deletar(SQLiteDatabase db) {
        String sql = "DELETE * FROM " + TABELA + " ;";

        db.execSQL(sql);
    }
}
