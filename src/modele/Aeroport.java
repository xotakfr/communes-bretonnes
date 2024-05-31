/**
 * Une instance de cette classe permet de représenter un aeroport
 * 
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class Aeroport {
    private String nom;
    private String adresse;

    /**
     * Obtient le nom de l'aeroport
     * 
     * @return Le nom de l'aeroport
     */
    public String getNom() {
        return this.nom;
    }
    
    /**
     * Obtient l'addresse de l'aeroport
     * 
     * @return L'addresse de l'aeroport
     */
    public String getAdresse(){
        return this.adresse;
    }
    
    /**
     * Définit le nom de l'aeroport
     * 
     * @param codeGare Le nouveau nom de l'aeroport
     */
    public void setNom(String nom){
        if (nom == null) {
            throw new NullPointerException("Aeroport.java : paramètre nom invalide");
        }
        this.nom = nom;
    }

    /**
     * Définit l'addresse de l'aeroport
     * 
     * @param codeGare La nouvelle addresse de l'aeroport
     */
    public void setAdresse(String adresse){
        if (adresse == null) {
            throw new NullPointerException("Aeroport.java : paramètre adresse invalide");
        }
        this.adresse = adresse;
    }
}

