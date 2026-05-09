package prog2.model;

import prog2.vista.BiblioException;
import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

public class Dades implements InDades, Serializable {
    private LlistaExemplars llistaExemplars;
    private LlistaUsuaris llistaUsuaris;
    private LlistaPrestecs llistaPrestecs;

    public Dades() {
        llistaExemplars = new LlistaExemplars();
        llistaUsuaris = new LlistaUsuaris();
        llistaPrestecs = new LlistaPrestecs();
    }

    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        Exemplar exemplar = new Exemplar(id, titol, autor, admetPrestecLlarg);
        llistaExemplars.afegir(exemplar);
    }

    public ArrayList<Exemplar> recuperaExemplars() {
        return llistaExemplars.getArrayList();
    }

    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        Usuari usuari;
        if (esEstudiant) {
            usuari = new Estudiant(email, nom, adreca);
        } else {
            usuari = new Professor(email, nom, adreca);
        }
        llistaUsuaris.afegir(usuari);
    }

    public ArrayList<Usuari> recuperaUsuaris() {
        return llistaUsuaris.getArrayList();
    }

    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        Exemplar exemplar = llistaExemplars.getAt(exemplarPos);
        Usuari usuari = llistaUsuaris.getAt(usuariPos);
        Date dataActual = new Date();
        Prestec prestec;

        if (!exemplar.isDisponible()) {
            throw new BiblioException("L'exemplar no està disponible.");
        }
        if (esLlarg) {
            if (!exemplar.getAdmetPrestecLlarg()) {
                throw new BiblioException("L'exemplar no admet prestecs llargs.");
            }
            if (usuari.getNumPrestecsLlargs() >= usuari.getMaxPrestecsLlargs()) {
                throw new BiblioException("L'usuari no pot excedir el seu límit de prestecs llargs.");
            }
        } else {
            if (usuari.getNumPrestecsNormals() >= usuari.getMaxPrestecsNormals()) {
                throw new BiblioException("L'usuari no pot excedir el seu límit de prestecs normals.");
            }
        }

        for (Prestec p : llistaPrestecs.getArrayList()) {
            if (p.getUsuari().equals(usuari) && !p.getRetornat() && p.getDataLimitRetorn().before(dataActual)) {
                throw new BiblioException("L'usuari té prestecs endarrerits.");
            }
        }

        if (esLlarg) {
            prestec = new PrestecLlarg(exemplar, usuari, dataActual);
            usuari.setNumPrestecsLlargs(usuari.getNumPrestecsLlargs() + 1);
        } else {
            prestec = new PrestecNormal(exemplar, usuari, dataActual);
            usuari.setNumPrestecsNormals(usuari.getNumPrestecsNormals() + 1);
        }

        llistaPrestecs.afegir(prestec);
        exemplar.setDisponible(false);
    }

    public void retornarPrestec(int position) throws BiblioException {
        if (position < 0 || position >= llistaPrestecs.getSize()) {
            throw new BiblioException("Posició de préstec no vàlida.");
        }
        Prestec prestec = llistaPrestecs.getAt(position);
        if (prestec.getRetornat()) {
            throw new BiblioException("El préstec ja ha estat retornat.");
        }

        prestec.retorna();
        prestec.getExemplar().setDisponible(true);

        // Decrementar comptador
        Usuari usuari = prestec.getUsuari();
        if (prestec instanceof PrestecLlarg) {
            usuari.setNumPrestecsLlargs(usuari.getNumPrestecsLlargs() - 1);
        } else {
            usuari.setNumPrestecsNormals(usuari.getNumPrestecsNormals() - 1);
        }
    }

    public ArrayList<Prestec> recuperaPrestecs() {
        return llistaPrestecs.getArrayList();
    }

    public ArrayList<Prestec> recuperaPrestecsNoRetornats() {
        ArrayList<Prestec> noRetornats = new ArrayList<>();
        for (Prestec p : llistaPrestecs.getArrayList()) {
            if (!p.getRetornat()) {
                noRetornats.add(p);
            }
        }
        return noRetornats;
    }
}
