package br.metodista.hotelapp.iterator;

import br.metodista.hotelapp.model.Usuario;

/**
 * Created by Gustavo Assalin on 02/11/2015.
 */
public interface Iterator {
    public boolean hasNext();

    public Object next();

    public Object atual();
}
