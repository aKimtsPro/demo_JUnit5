package bstorm.akimts;

public class MaClasse {

    public String maMethode(Object monParam){
        if( monParam == null )
            throw new IllegalArgumentException();

        return "mon retour : " + monParam;
    }

    public void pourTest(){
        System.out.println("pour le test");
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MaClasse;
//        return false;
    }

}
