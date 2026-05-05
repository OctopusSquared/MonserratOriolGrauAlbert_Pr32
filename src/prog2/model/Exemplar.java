package prog2.model;

public class Exemplar implements InExemplar {
    private String id;
    private String autor;
    private String titol;
    private boolean admetPrestecLlarg;
    private boolean disponible;


public Exemplar (String id, String autor, String titol, boolean admetPrestecLlarg){
    this.id = id;
    this.autor = autor;
    this.titol = titol;
    this.admetPrestecLlarg = admetPrestecLlarg;
    disponible = true;
}

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
                ", Admet Prestec Llarg=" + (admetPrestecLlarg? "true":"false") +
                ", Disponible=" + (disponible? "true":"false");
    }
}
