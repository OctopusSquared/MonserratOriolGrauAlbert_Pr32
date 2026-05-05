package prog2.model;

public class Estudiant extends Usuari {

    // Atributs
    //------------
    
    private static final int MAX_PRESTECS_NORMALS = 2;
    private static final int MAX_PRESTECS_LLARGS = 1;
    private int prestecsNormals;
    private int prestecsLlargs;

    // Constructors
    //---------------

    /**
     * Constructor per paràmetres de la classe.
     * @param email
     * @param nom
     * @param adreca
     */
    public Estudiant(String email, String nom, String adreca) {
        super(email, nom, adreca);
        this.prestecsNormals = MAX_PRESTECS_NORMALS;
        this.prestecsLlargs = MAX_PRESTECS_LLARGS;
    }

    // Getters i Setters.
    //---------------------
    
    public String tipusClient() {
        return "Estudiant";
    }

    public void setNumPrestecsNormals(int numPrestecsNormals) {
        this.prestecsNormals = numPrestecsNormals;
    }

    public int getNumPrestecsNormals() {
        return prestecsNormals;
    }

    public void setNumPrestecsLlargs(int numPrestecstLlargs) {
        this.prestecsLlargs = numPrestecstLlargs;
    }

    public int getNumPrestecsLlargs() {
        return prestecsLlargs;
    }

    public int getMaxPrestecsNormals() {
        return MAX_PRESTECS_NORMALS;
    }

    public int getMaxPrestecsLlargs() {
        return MAX_PRESTECS_LLARGS;
    }

    // Mètodes
    //---------

    /**
     * Retorna un String amb la informació de l'Estudiant.
     * @return
     */
    @Override
    public String toString() {
        return "Tipus=" + tipusClient() +
                ", " + super.toString() +
                ", Num. prestecs normals=" + prestecsNormals +
                ", Num. prestecs llargs=" + prestecsLlargs;
    }
}
