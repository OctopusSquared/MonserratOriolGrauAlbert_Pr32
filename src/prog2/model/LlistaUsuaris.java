package prog2.model;

import prog2.vista.BiblioException;
import java.util.Iterator;
import java.io.Serializable;

public class LlistaUsuaris extends Llista<Usuari> implements Serializable {

    @Override
    public void afegir(Usuari t) throws BiblioException {
        String email_t = t.getEmail();
        Iterator<Usuari> it = llista.iterator();
        while (it.hasNext()) {
            Usuari u = it.next();
            if (u.getEmail().equals(email_t)) {
                throw new BiblioException("No es poden afegir dos usuaris amb el mateix correu electrònic");
            }
        }
        llista.add(t);
    }

    /**
     * Retorna true si hi ha un usuari amb l'email donat.
     */
    public boolean contains(String email) {
        for (Usuari u : llista) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
