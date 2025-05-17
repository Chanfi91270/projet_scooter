package Modele;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Type_Scooter {

    public String type_scoot;
    public int nbe_Cc;

    Scooter scooter;
    Permis permis;

    public Type_Scooter(String type, int cc) {
        type_scoot = type;
        nbe_Cc = cc;
    }

    public Type_Scooter(String type, int cc, Scooter scooter, Permis permis) {
        type_scoot = type;
        nbe_Cc = cc;
        this.scooter = scooter;
        this.permis = permis;
    }

    public void setTypeScooter(String newtype) {
        type_scoot = newtype;
    }

    public void setCc(int newcc) {
        nbe_Cc = newcc;
    }

    public void setScooter(Scooter newscooter) {
        scooter = newscooter;
    }

    public void setPermis(Permis newpermis) {
        permis = newpermis;
    }

    public String getTypeScooter() {
        return type_scoot;
    }

    public int getCc() {
        return nbe_Cc;
    }

    public Scooter getScooter() {
        return scooter;
    }

    public Permis getPermis() {
        return permis;
    }

    public String getPermisString() {
        if (permis != null) {
            return permis.getTypePermis();
        } else {
            return "Aucun permis associé";
        }
    }

    public boolean estCompatibleAvecPermis(Permis permis) {
        // Exemple basique : si le type de permis est "A" et que la cylindrée est
        // supérieure à 125 cm³, c'est compatible.
        if (permis.getTypePermis().equals("A") && nbe_Cc > 125) {
            return true;
        } else if (permis.getTypePermis().equals("B") && nbe_Cc <= 125) {
            return true;
        }
        return false;
    }

    public void afficherType() {
        System.out.println("Type de scooter: " + type_scoot);
        System.out.println("Cylindrée: " + nbe_Cc + " cm³");
    }

}