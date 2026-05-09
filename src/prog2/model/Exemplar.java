package prog2.model;

public class Exemplar implements InExemplar {
    // Atributs
    //------------
    private String id;
    private String autor;
    private String titol;
    private boolean admetPrestecLlarg;
    private boolean disponible;

    // Constructors
    //---------------

    /**
     * Constructor per paràmetres de la classe.
     * @param id
     * @param autor
     * @param titol
     * @param admetPrestecLlarg
     */
    public Exemplar (String id, String autor, String titol, boolean admetPrestecLlarg){
        this.id = id;
        this.autor = autor;
        this.titol = titol;
        this.admetPrestecLlarg = admetPrestecLlarg;
        disponible = true;
    }

    // Getters i Setters.
    //---------------------

    public void setId(String id) { this.id = id; }
    public String getId() { return id; }

    public void setTitol(String titol) { this.titol = titol; }
    public String getTitol() { return titol; }

    public void setAutor(String autor) { this.autor = autor; }
    public String getAutor() { return autor; }

    public void setAdmetPrestecLlarg(boolean admetPrestecLlarg) { this.admetPrestecLlarg = admetPrestecLlarg; }
    public boolean getAdmetPrestecLlarg() { return admetPrestecLlarg; }

    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public boolean isDisponible() { return disponible; }

    // Mètodes
    //---------

    /**
     * Retorna un String amb la informació de l'Exemplar.
     * @return String
     */
    @Override
    public String toString() {
        return "Id=" + id +
                ", Titol=" + titol +
                ", Autor=" + autor +
                ", Admet Prestec Llarg=" + (admetPrestecLlarg? "true":"false") +
                ", Disponible=" + (disponible? "true":"false");
    }
}
