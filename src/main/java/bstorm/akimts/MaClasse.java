package bstorm.akimts;

public class MaClasse implements MonInterface {

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

    private int a = 0;

    public int incrementTo100_000() {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 50_000; i++) {
                increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50_000; i++) {
                increment();
            }
        });

        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }


        return a;
    }

    private synchronized void increment(){
        a++;
    }

}
