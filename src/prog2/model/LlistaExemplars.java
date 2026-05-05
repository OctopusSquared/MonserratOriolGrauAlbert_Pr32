package prog2.model;

import prog2.vista.BiblioException;
import java.util.Iterator;
import java.io.Serializable;

public class LlistaExemplars extends Llista<Exemplar> implements Serializable {
    @Override
    /**
     * Afegir element a la llista. Afegeix l'Exemlar t a la llista
     */
    public void afegir(Exemplar t) throws BiblioException {
        String id_t = t.getId();

        // Recorrem la llista amb iteradors
        Iterator<Exemplar> it = llista.iterator();
        while (it.hasNext()) {
            Exemplar e = it.next();
            if (e.getId() == id_t) {
                throw new BiblioException("No es poden afegir dos exemplars amb el mateix identificador");
            }
        }

        // Si no s'han trobat dos exemplars amb el mateix id, es pot afegir el nou exemplar
        llista.add(t);
    }
}