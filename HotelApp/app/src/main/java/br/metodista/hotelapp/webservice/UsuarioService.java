package br.metodista.hotelapp.webservice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.metodista.hotelapp.model.Usuario;

/**
 * Created by Gustavo Assalin on 22/10/2015.
 */
public class UsuarioService {
    private URL url;

    private void setUrl() throws Exception {
        url = new URL("https://openws.herokuapp.com/usuarios?apiKey=ab8647942e1d0a115b28891b9c07a2b7");
    }

    public List<Usuario> getAll() {
        List<Usuario> usuarios = new ArrayList<>();
        HttpURLConnection urlConnection = null;

        try {
            setUrl();

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in =  new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            usuarios = gson.fromJson(conteudo, new TypeToken<List<Usuario>>(){}.getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }

        return usuarios;
    }
}
