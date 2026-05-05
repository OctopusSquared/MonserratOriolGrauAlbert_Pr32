package prog2.model;

import java.util.Date;
import prog2.vista.BiblioException;

public class PrestecLlarg  extends Prestec {
    private static final long DURADA = 140000; // Temps en ms
    private Date dataCreacio;
    private Date dataLimitRetorn;
    private boolean retornat;

    public PrestecLlarg(Exemplar exemplar, Usuari usuari, Date data) {
        super(exemplar, usuari);
        dataCreacio = data;
        dataLimitRetorn = new Date(data.getTime() + DURADA);
        retornat = false;
    }

    public void setDataCreacio(Date data) { dataCreacio = data; }
    public Date getDataCreacio() { return dataCreacio; }
    public void setDataLimitRetorn(Date data) { dataLimitRetorn = data; }
    public Date getDataLimitRetorn() { return dataLimitRetorn; }
    public String tipusPrestec() { return "Llarg"; }
    public void setRetornat(boolean retornat) { this.retornat = retornat; }
    public boolean getRetornat() { return retornat; }

    /**
     * Retornar prestec.
     */
    public void retorna() throws BiblioException {
        retornat = true;
    }

    /**
     * Retornar durada prestec. La durada del prestec depen del tipus de prestec
     */
    public long duradaPrestec() { return DURADA; }

    /**
     * Retornar true si el prestec està endarrerit per a la data actual
     */
    public boolean prestecEndarrerit() {
        return dataLimitRetorn.after(dataCreacio);
    }

    @Override
    public String toString() {
        return "Tipus=" + tipusPrestec() +
                ", " + super.toString() +
                ", Data de creacio=" + dataCreacio.toString() +
                ", Data límit retorn" + dataLimitRetorn.toString() +
                ", Retornat=" + (retornat?"true":"false");
    }
}
