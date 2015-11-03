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
 * Created by Gustavo Assalin on 02/11/2015.
 */
public class FinanceiroService {
    private URL url;

    private void setUrl() throws Exception {
        url = new URL("https://openws.herokuapp.com/outroteste?apiKey=b4111c92d8a0f3d61f3cfd87e9a4eb75");
    }

    public List<Usuario> getAll() {
        List<Usuario> produtos = new ArrayList<>();
        HttpURLConnection urlConnection = null;

        try {
            setUrl();

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in =  new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            produtos = gson.fromJson(conteudo, new TypeToken<List<Usuario>>(){}.getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }

        return produtos;
    }
}
