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

import br.metodista.hotelapp.model.Produto;

/**
 * Created by Gustavo Assalin on 13/10/2015.
 */
public class ProdutoService {
    private static final String URL = "https://openws.herokuapp.com/hotelapp-produtos";
    private static final String API_KEY = "b4111c92d8a0f3d61f3cfd87e9a4eb75";

    public List<Produto> getAll() {
        List<Produto> produtos = new ArrayList<>();
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(URL + API_KEY);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in =  new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            produtos = gson.fromJson(conteudo, new TypeToken<List<Produto>>(){}.getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }

        return produtos;
    }
}