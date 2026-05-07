package prog2.adaptador;

import prog2.model.*;
import prog2.vista.BiblioException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Adaptador {
    // Atributs
    //------------
    private Dades dades;

    // Constructors
    //---------------
    /**
     * Constructor per defecte de la classe.
     */
    public Adaptador() {
        dades = new Dades();
    }

    /**
     * Constructor per paràmetres de la classe.
     * @param dades
     */
    public Adaptador(Dades dades) {
        this.dades = dades;
    }

    // Getters i Setters.
    //---------------------
    public Dades getDades() {
        return dades;
    }

    // Mètodes
    //-------------
    /**
     * Converteix una llista d'Exemplar en una llista de String.
     * @return List<String>
     */
    public List<String> llistaExemplarsToLlistaString() {
        List<String> llista = new ArrayList<>();

        for (Exemplar e : dades.recuperaExemplars()) {
            llista.add(e.toString());
        }

        return llista;
    }

    /**
     * Converteix una llista d'Usuari en una llista de String.
     * @return List<String>
     */
    public List<String> llistaUsuarisToLlistaString() {
        List<String> llista = new ArrayList<>();

        for (Usuari u : dades.recuperaUsuaris()) {
            llista.add(u.toString());
        }

        return llista;
    }

    /**
     * Converteix una llista de Prestecs en una llista de String.
     * @return List<String>
     */
    public List<String> llistaPrestecsToLlistaString() {
        List<String> llista = new ArrayList<>();

        for (Prestec e : dades.recuperaPrestecs()) {
            llista.add(e.toString());
        }

        return llista;
    }

    /**
     * Guarda les dades del model en un fitxer.
     */
    public void guardaDades(String camiDesti) throws BiblioException {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(camiDesti)))
        {
            oos.writeObject(dades);
        }
        catch (IOException e) {
            throw new BiblioException("Error en guardar les dades");
        }
    }

    /**
     * Carrega les dades del model des d'un fitxer.
     */
    public void carregaDades(String camiOrigen) throws BiblioException {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(camiOrigen)))
        {
            dades = (Dades) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            throw new BiblioException("Error en carregar les dades");
        }
    }
}