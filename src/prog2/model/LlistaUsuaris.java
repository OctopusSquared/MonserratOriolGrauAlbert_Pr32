package prog2.model;

import prog2.vista.BiblioException;
import java.util.Iterator;
import java.io.Serializable;

public class LlistaUsuaris extends Llista<Usuari> implements Serializable {
    @Override
    /**
     * Afegir element a la llista. Afegeix l'Usuari t a la llista
     */
    public void afegir(Usuari t) throws BiblioException {
        String email_t = t.getEmail();

        // Recorrem la llista amb iteradors
        for (Iterator<Usuari> it = llista.iterator(); it.hasNext(); ) {
                Usuari u = it.next();
                if (u.getEmail().equals(email_t)) {
                    throw new BiblioException("No es poden afegir dos  usuaris amb el mateix correu electrònic");
                }
            }

        // Si no s'han trobat dos exemplars amb el mateix correu, es pot afegir el nou usuari
        llista.add(t);
    }
}