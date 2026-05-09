package prog2.model;

import java.io.Serializable;

public class Exemplar implements InExemplar, Serializable {
    private String id;
    private String autor;
    private String titol;
    private boolean admetPrestecLlarg;
    private boolean disponible;

    /**
     * Constructor per paràmetres de la classe.
     * @param id identificador numèric
     * @param titol títol de l'exemplar
     * @param autor autor de l'exemplar
     * @param admetPrestecLlarg indica si admet préstec de llarg termini
     */
    public Exemplar(String id, String titol, String autor, boolean admetPrestecLlarg) {
        this.id = id;
        this.titol = titol;
        this.autor = autor;
        this.admetPrestecLlarg = admetPrestecLlarg;
        disponible = true;
    }

    // Getters i Setters
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

    @Override
    public String toString() {
        return "Id=" + id +
                ", Titol=" + titol +
                ", Autor=" + autor +
                ", Admet Prestec Llarg=" + (admetPrestecLlarg ? "true" : "false") +
                ", Disponible=" + (disponible ? "true" : "false");
    }
}
