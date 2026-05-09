package prog2.model;

import prog2.vista.BiblioException;
import java.util.ArrayList;
import java.util.Date;

public class Dades implements InDades {
    // Atributs
    //------------
    private LlistaExemplars llistaExemplars;
    private LlistaUsuaris llistaUsuaris;
    private LlistaPrestecs llistaPrestecs;

    // Constructors
    //---------------
    /**
     * Constructor per defecte de la classe.
     */
    public Dades() {
        llistaExemplars = new LlistaExemplars();
        llistaUsuaris = new LlistaUsuaris();
        llistaPrestecs = new LlistaPrestecs();

    }

    // Mètodes
    //-------------
    /**
     * Afegeix un exemplar a la llista d'exemplars
     * @param id
     * @param titol
     * @param autor
     * @param admetPrestecLlarg
     */
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        Exemplar exemplar = new Exemplar(id, titol, autor, admetPrestecLlarg);
        llistaExemplars.afegir(exemplar);
    }

    /**
     * Retorna L'ArrayList d'exemplars
     * @return ArrayList<Exemplar>
     */
    public ArrayList<Exemplar> recuperaExemplars() {
        return llistaExemplars.getArrayList();
    }

    /**
     * Afegeix un usuari a la llista d'usuaris
     * @param email
     * @param nom
     * @param adreca
     * @param esEstudiant
     */
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

    /**
     * Retorna L'ArrayList d'usuaris
     * @return ArrayList<Usuari>
     */
    public ArrayList<Usuari> recuperaUsuaris() {
        return llistaUsuaris.getArrayList();
    }

    /**
     * Afegeix un prèstec a la llista de prèstecs
     * @param exemplarPos
     * @param usuariPos
     * @param esLlarg
     */
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        Exemplar exemplar = llistaExemplars.getAt(exemplarPos);
        Usuari usuari = llistaUsuaris.getAt(usuariPos);
        Date dataActual = new Date();
        Prestec prestec;

        // Llançar les excepcions necessaries
        //------------------------------------

        // Fer un prestec d’un exemplar no disponible.
        if (!exemplar.isDisponible()) {
            throw new BiblioException("L'exemplar no està disponible.");
        }
        if (esLlarg) {
            // Afegir un objecte de tipus PrestecLlarg per a un exemplar que no admet prestec llargs
            if (!exemplar.getAdmetPrestecLlarg()) {
                throw new BiblioException("L'exemplar no admet prestecs llargs.");
            }
            // Fer un prestec a un usuari que excedeix el seu limit de prestecs llargs
            if (usuari.getMaxPrestecsLlargs() <= usuari.getNumPrestecsLlargs()) {
                throw new BiblioException("l'usuari no pot excedeir el seu limit de prestecs llargs.");
            }
        }
        else {
            // Fer un prestec a un usuari que excedeix el seu limit de prestecs normals
            if (usuari.getMaxPrestecsNormals() <= usuari.getNumPrestecsNormals()) {
                throw new BiblioException("l'usuari no pot excedeir el seu limit de prestecs normals.");
            }
        }
        // Recorrem la llista de prestecs per esbrinar si l'usuari té prestecs endarrerits
        for (Prestec p : llistaPrestecs.getArrayList()) {
            // Fer un prestec a un usuari que té prestecs endarrerits.
            if (
                    p.getUsuari().equals(usuari) &&
                    p.getDataLimitRetorn().before(dataActual)
            ) {
                throw new BiblioException("L'usuari té prestecs endarrerits.");
            }
        }

        // Afegir el Prestec a la llista
        //-------------------------------
        if (esLlarg) prestec = new PrestecLlarg(exemplar, usuari, dataActual);
        else prestec = new PrestecNormal(exemplar, usuari, dataActual);

        llistaPrestecs.afegir(prestec);

        // Marcar l'exemplar com "No disponible"
        exemplar.setDisponible(false);
    }

    /**
     * Elimina un prestec de la llista, donada la seva posició.
     */
    public void retornarPrestec(int position) throws BiblioException {
        Prestec prestec;

        // Excepcions necessaries
        if (llistaPrestecs.getSize() < position) {
            throw new BiblioException("No es pot retornar un prestec ja retornat");
        }

        // Retornar el prestec
        prestec = llistaPrestecs.getAt(position);
        llistaPrestecs.esborrar(prestec);

        // Marcar l'exemplar com "Disponible"
        prestec.getExemplar().setDisponible(true);
    }

    /**
     * Retorna L'ArrayList de prèstecs
     * @return ArrayList<Prestec>
     */
    public ArrayList<Prestec> recuperaPrestecs() {
        return llistaPrestecs.getArrayList();
    }

    /**
     * Retorna L'ArrayList de prèstecs NO retornats
     * @return ArrayList<Prestec>
     */
    public ArrayList<Prestec> recuperaPrestecsNoRetornats() {
        ArrayList<Prestec> prestecs = new ArrayList<>();

        // Recorrem la llista de Prestecs i guardem aguels que estiguin endarrerits
        for (Prestec p : llistaPrestecs.getArrayList()) {
            if (p.getDataLimitRetorn().before(new Date())) {
                prestecs.add(p);
            }
        }

        return prestecs;
    }
}
