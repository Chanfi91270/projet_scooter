package Modele;
import java.io.*;
import java.util.*;
/**
 * 
 */
public class Permis {

    public String Type_permis;

    Vector<Type_Scooter> liste_type_scooter = new Vector<Type_Scooter>();
    Vector<Client> liste_client = new Vector<Client>();

    public Permis(String type_permis) {
        this.Type_permis = type_permis;
    }

    public void setTypePermis(String newtype) {
        Type_permis = newtype;
    }

    public void settype(Vector<Type_Scooter> type_scooter) {
        liste_type_scooter = type_scooter;
    }

    public void setclient(Vector<Client> client) {
        liste_client = client;
    }

    public void ajouterTypeScooter(Type_Scooter type) {
        liste_type_scooter.add(type);
    }

    public void ajouterClient(Client client) {
        liste_client.add(client);
    }

    public void supprimerTypeScooter(Type_Scooter type) {
        liste_type_scooter.remove(type);
    }

    public void supprimerClient(Client client) {
        liste_client.remove(client);
    }

    public String getTypePermis() {
        return Type_permis;
    }

    public Vector<Type_Scooter> gettype() {
        return liste_type_scooter;
    }

    public Vector<Client> getclient() {
        return liste_client;
    }

    public boolean peutConduire(Type_Scooter type) {
        for (int i = 0; i < liste_type_scooter.size(); i++) {
            Type_Scooter t = liste_type_scooter.get(i);
            if (t.getTypeScooter().equals(type.getTypeScooter()) && t.getCc() == type.getCc()) {
                return true;
            }
        }
        return false;
    }

}