package prog2.model;

import java.io.Serializable;

public abstract class Usuari implements InUsuari, Serializable {
    private String email;
    private String nom;
    private String adreca;

    public Usuari(String email, String nom, String adreca) {
        this.email = email;
        this.nom = nom;
        this.adreca = adreca;
    }

    // Getters i Setters (sense canvis)
    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return this.email; }
    public void setNom(String nom) { this.nom = nom; }
    public String getNom() { return this.nom; }
    public void setAdreca(String adreca) { this.adreca = adreca; }
    public String getAdreca() { return this.adreca; }

    @Override
    public String toString() {
        return "Email=" + this.email +
                ", Nom=" + this.nom +
                ", Adreca=" + this.adreca;
    }
}
