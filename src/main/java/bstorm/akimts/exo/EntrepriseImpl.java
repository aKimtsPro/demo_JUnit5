package bstorm.akimts.exo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntrepriseImpl implements Entreprise{

    private String nom;
    private List<String> employes;
    private TypeEntreprise type;


    // region ctor

    public EntrepriseImpl(String nom) {

        if( nom == null )
            throw new IllegalArgumentException();

        this.nom = nom;
        this.type = TypeEntreprise.FICTIVE;
    }

    public EntrepriseImpl(String nom, String independant) {

        if( nom == null || independant == null )
            throw new IllegalArgumentException();

        this.nom = nom;
        this.employes = List.of(independant);
        this.type = TypeEntreprise.INDEPENDANT;

    }

    public EntrepriseImpl(String nom, List<String> employes, TypeEntreprise type) {

        if( nom == null )
            throw new IllegalArgumentException();

        if( (employes == null || employes.isEmpty()) && type != TypeEntreprise.FICTIVE )
            throw new TypeEntrepriseInconsistancyException("une societe societe doit être fictive pour avoir une liste d'employe vide ou null", type);

        this.nom = nom;

        switch (type){
            case MULTINAT:
                this.employes = new ArrayList<>(employes);
                break;
            case FICTIVE:
                this.employes = null;
                break;
            case PME:
            case INDEPENDANT:
                this.employes = employes.stream()
                        .limit(type.getNbrMaxEmploye())
                        .collect(Collectors.toList());
                break;
        }
        this.type = type;
    }

    // endregion

    @Override
    public void engager(String employe) {

        if( employe == null )
            throw new IllegalArgumentException();

        this.employes.add(employe);
    }

    @Override
    public boolean virer(String employe) {

        if( employe == null )
            throw new IllegalArgumentException();

        return employes.remove(employe);
    }


    // region get/set

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeEntreprise getType() {
        return type;
    }
    @Override
    public List<String> getEmployes() {
        return employes;
    }

    // endregion
}
