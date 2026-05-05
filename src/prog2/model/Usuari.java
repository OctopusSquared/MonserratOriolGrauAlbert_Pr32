package prog2.model;

public abstract class Usuari implements InUsuari {
    
    // Atributs
    //------------

    private String email;
    private String nom;
    private String adreca;

    // Constructors
    //---------------

    /**
     * Constructor per paràmetres de la classe.
     * @param email
     * @param nom
     * @param adreca
     */
    public Usuari(String email, String nom, String adreca) {
        this.email = email;
        this.nom = nom;
        this.adreca = adreca;
    }

    // Getters i Setters.
    //---------------------
    
    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return this.email; }
    public void setNom(String nom) { this.nom = nom; }
    public String getNom() { return this.nom; }
    public void setAdreca(String adreca) { this.adreca = adreca; }
    public String getAdreca() { return this.adreca; }

    // Mètodes
    //---------

    /**
     * Retorna un String amb la informació de l'Usuari.
     * @return
     */
    @Override
    public String toString() {
        return "Email=" + this.email +
                ", Nom=" + this.nom +
                ", Adreca=" + this.adreca;
    }
}
