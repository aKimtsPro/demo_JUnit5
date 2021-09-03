package bstorm.akimts;

public class Demo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Mes arguments : ");
        for (String arg : args) {
            System.out.println(arg);
        }

        System.out.println( System.getenv("Path") );


        System.out.println("Début de l'attente");
        Thread.sleep(2000); // attente de 2sec
        System.out.println("fin de l'attente");
    }

}
