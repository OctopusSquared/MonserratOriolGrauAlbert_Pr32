package prog2.adaptador;

import prog2.model.*;
import prog2.vista.BiblioException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Adaptador {
    private Dades dades;

    public Adaptador() {
        dades = new Dades();
    }

    public Adaptador(Dades dades) {
        this.dades = dades;
    }

    public Dades getDades() {
        return dades;
    }

    // Mètodes de conversió per a la vista
    public List<String> llistaExemplarsToLlistaString() {
        List<String> llista = new ArrayList<>();
        for (Exemplar e : dades.recuperaExemplars()) {
            llista.add(e.toString());
        }
        return llista;
    }

    public List<String> llistaUsuarisToLlistaString() {
        List<String> llista = new ArrayList<>();
        for (Usuari u : dades.recuperaUsuaris()) {
            llista.add(u.toString());
        }
        return llista;
    }

    public List<String> llistaPrestecsToLlistaString() {
        List<String> llista = new ArrayList<>();
        for (Prestec e : dades.recuperaPrestecs()) {
            llista.add(e.toString());
        }
        return llista;
    }

    public List<String> llistaPrestecsNoRetornatsToLlistaString() {
        List<String> llista = new ArrayList<>();
        for (Prestec e : dades.recuperaPrestecsNoRetornats()) {
            llista.add(e.toString());
        }
        return llista;
    }

    // Delegació d'operacions de negoci
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        dades.afegirExemplar(id, titol, autor, admetPrestecLlarg);
    }

    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        dades.afegirUsuari(email, nom, adreca, esEstudiant);
    }

    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        dades.afegirPrestec(exemplarPos, usuariPos, esLlarg);
    }

    public void retornarPrestec(int position) throws BiblioException {
        dades.retornarPrestec(position);
    }

    public void guardaDades(String camiDesti) throws BiblioException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(camiDesti))) {
            oos.writeObject(dades);
        } catch (IOException e) {
            throw new BiblioException("Error en guardar les dades");
        }
    }

    public void carregaDades(String camiOrigen) throws BiblioException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(camiOrigen))) {
            dades = (Dades) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new BiblioException("Error en carregar les dades");
        }
    }
}
