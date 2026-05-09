package prog2.model;

import java.io.Serializable;

public abstract class Prestec implements InPrestec, Serializable {
    protected Exemplar exemplar;
    protected Usuari usuari;

    public Prestec(Exemplar exemplar, Usuari usuari) {
        this.exemplar = exemplar;
        this.usuari = usuari;
    }

    public void setExemplar(Exemplar exemplar) { this.exemplar = exemplar; }
    public Exemplar getExemplar() { return exemplar; }
    public void setUsuari(Usuari usuari) { this.usuari = usuari; }
    public Usuari getUsuari() { return usuari; }

    @Override
    public String toString() {
        return "Exemplar=" + exemplar.getTitol() +
                ", Usuari=" + usuari.getNom();
    }
}
