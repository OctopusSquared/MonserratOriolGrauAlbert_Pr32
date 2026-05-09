package prog2.model;

import prog2.vista.BiblioException;
import java.util.Iterator;
import java.io.Serializable;

public class LlistaExemplars extends Llista<Exemplar> implements Serializable {

    @Override
    public void afegir(Exemplar t) throws BiblioException {
        String id_t = t.getId();
        Iterator<Exemplar> it = llista.iterator();
        while (it.hasNext()) {
            Exemplar e = it.next();
            if (e.getId().equals(id_t)) {
                throw new BiblioException("No es poden afegir dos exemplars amb el mateix identificador");
            }
        }
        llista.add(t);
    }

    /**
     * Retorna true si hi ha un exemplar amb l'id donat.
     */
    public boolean contains(String id) {
        for (Exemplar e : llista) {
            if (e.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
