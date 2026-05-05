package prog2.model;

import prog2.vista.BiblioException;
import java.util.ArrayList;
import java.util.Date;

public class Dades implements InDades {
    private LlistaExemplars llistaExemplars;
    private LlistaUsuaris llistaUsuaris;
    private LlistaPrestecs llistaPrestecs;

    public Dades() {
        llistaExemplars = new LlistaExemplars();
        llistaUsuaris = new LlistaUsuaris();
        llistaPrestecs = new LlistaPrestecs();

    }

    @Override
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        Exemplar exemplar = new Exemplar(id, titol, autor, admetPrestecLlarg);
        llistaExemplars.afegir(exemplar);
    }

    @Override
    public ArrayList<Exemplar>
    recuperaExemplars() {
        return llistaExemplars.getArrayList();
    }

    @Override
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        Usuari usuari;
        if (esEstudiant) {
            usuari = new Estudiant(email, nom, adreca);
        }
        else {
            usuari = new Professor(email, nom, adreca);
        }

        llistaUsuaris.afegir(usuari);
    }

    @Override
    public ArrayList<Usuari> recuperaUsuaris() {
        return llistaUsuaris.getArrayList();
    }

    @Override
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        Exemplar exemplar = llistaExemplars.getAt(exemplarPos);
        Usuari usuari = llistaUsuaris.getAt(usuariPos);

        if (!exemplar.isDisponible()) {
            throw new BiblioException("L'exemplar no està disponible.");
        }
        if (esLlarg && !exemplar.getAdmetPrestecLlarg()) {
            throw new BiblioException("L'exemplar no admet prestecs llargs.");
        }
        if (usuari.tePrestecsEndarrerits(usuari)){
            throw new BiblioException("L'usuari té prestecs endarrerits.");
        }
    }
}
