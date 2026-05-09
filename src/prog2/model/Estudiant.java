package prog2.model;

public class Estudiant extends Usuari {
    private static final int MAX_PRESTECS_NORMALS = 2;
    private static final int MAX_PRESTECS_LLARGS = 1;
    private int prestecsNormals;
    private int prestecsLlargs;

    public Estudiant(String email, String nom, String adreca) {
        super(email, nom, adreca);
        this.prestecsNormals = 0;
        this.prestecsLlargs = 0;
    }

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

    @Override
    public String toString() {
        return "Tipus=" + tipusClient() +
                ", " + super.toString() +
                ", Num. prestecs normals=" + prestecsNormals +
                ", Num. prestecs llargs=" + prestecsLlargs;
    }
}
