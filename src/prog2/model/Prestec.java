package prog2.model;

public abstract class Prestec implements InPrestec {

    // Atributs
    //------------

    private Exemplar exemplar;
    private Usuari usuari;

    // Constructors
    //---------------

    /**
     * Constructor per paràmetres de la classe.
     * @param exemplar
     * @param usuari
     */
    public Prestec(Exemplar exemplar, Usuari usuari) {
        this.exemplar = exemplar;
        this.usuari = usuari;
    }

    // Getters i Setters.
    //---------------------

    public void setExemplar(Exemplar exemplar) { this.exemplar = exemplar; }
    public Exemplar getExemplar() { return exemplar; }
    public void setUsuari(Usuari usuari) { this.usuari = usuari; }
    public Usuari getUsuari() { return usuari; }

    // Mètodes
    //---------

    /**
     * Retorna un String amb la informació de l'Estudiant.
     * @return String
     */
    @Override
    public String toString() {
        return "Exemplar=" + exemplar.getTitol() +
                ", Usuari=" + usuari.getNom();
    }
}
