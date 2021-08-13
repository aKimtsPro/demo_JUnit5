package bstorm.akimts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MaClasseTest {


    @Test
    void viewAsserts(){

        MaClasse m1 = new MaClasse();
        MaClasse m2 = new MaClasse();

        // ASSERT (not) EQUALS : verifie l'égalité (l'inégalité) avec la methode .equals(...)
//        assertEquals( m1, m2 ); // success // pas de message
//        assertEquals( m1, m2, "echec" ); // message généré dans tous les cas
//        assertEquals( m1, m2, () -> { return "message lourd à créer"; }); // ne génére le message que si le test echoue
//        assertNotEquals(m1, m2); // failure

        // ASSERT TRUE/FALSE : vérifie si le boolean est vrai/faux
//        MaClasse[] tab1 = new MaClasse[]{ m1, m1 };
//        MaClasse[] tab2 = new MaClasse[]{ m2, m2 };
//        assertTrue( tab1.length == 2, "c'est vrai" ); // success
//        assertTrue( () -> tab2.length != 2, "c'est pas vrai :)" ); // failure
//        assertFalse( tab1.length == 2, "c'est vrai" ); // failure
//        assertFalse( () -> tab2.length != 2, "c'est pas vrai :)" ); // success

        // ASSERT (not) SAME : vérifie si les deux params font référence au même Objet(adresse)
//        assertSame(m1, m2); // failure
//        assertNotSame(m1, m2); // success

        // ASSERT (not) NULL : vérifie si la valeur (n')est (pas) null
//        assertNull(m1); // failure
//        assertNotNull(m1); // success

        // ASSERT (does not) THROW(S) : vérifie si un lambda donnée lance une exception
        // verifie pour un type d'exception précis (Exception que cela fonctionne avec toutes exception)
//        assertThrows( IllegalArgumentException.class, () -> m1.maMethode(null)); // success
//        assertThrows( NullPointerException.class, () -> m1.maMethode(null) ); // failure
        // verifie qu'AUCUNE exception n'est lancée
//        assertDoesNotThrow( m1::pourTest ); // success // renvoie void
//        String rslt = assertDoesNotThrow(() -> m1.maMethode(m2)); // success // renvoie le rslt de la lambda
//        assertEquals("qqchose", rslt);
//        assertDoesNotThrow(() -> m1.maMethode(null)); // failure

        // ASSERT ARRAY EQUALS : vérifie l'égalité
        // ( si de même taille et contenant des mêmes éléments égaux (.equals(...)))
//        MaClasse[] tab1 = new MaClasse[]{ m1, m1 };
//        MaClasse[] tab2 = new MaClasse[]{ m2, m2 };
//        assertArrayEquals(tab1, tab2); // success
//        assertArrayNotEquals(tab1, tab2); // n'existe pas

        // ASSERT ITERABLE EQUALS : pareil que pour les tableaux mais pour les Iterables (Collections par ex)
//        MaClasse[] tab1 = new MaClasse[]{ m1, m1 };
//        MaClasse[] tab2 = new MaClasse[]{ m2, m2 };
//        assertIterableEquals(Arrays.asList(tab1), Arrays.asList(tab2));
//        assertIterableNotEquals(Arrays.asList(tab1), Arrays.asList(tab2)); // n'existe pas

        // ASSERT LINES MATCH : verifie si les lines sont les mêmes pour les 2 ensembles
//        assertLinesMatch(Arrays.asList(m1.maMethode(m2)), Arrays.asList(m2.maMethode(m1)));


        // ASSERT TIMEOUT (preemptively): verifie que le temps d'execution de l'executable
        // coupe après la Duration indiquée si preemptively

//        assertTimeout(Duration.ofMillis(2000), () -> {
//            System.out.println("debut attente (1s)");
//            Thread.sleep(1000);
//            System.out.println("fin attente");
//        }); // success
//        assertTimeout(Duration.ofMillis(2000), () -> {
//            System.out.println("debut attente (3s)");
//            Thread.sleep(3000);
//            System.out.println("fin attente");
//        }); // failure
//        assertTimeoutPreemptively(Duration.ofMillis(2000), () -> {
//            System.out.println("debut attente (1s)");
//            Thread.sleep(1000);
//            System.out.println("fin attente");
//        }); // success
//        assertTimeoutPreemptively(Duration.ofMillis(2000), () -> {
//            System.out.println("debut attente (3s)");
//            Thread.sleep(3000);
//            System.out.println("fin attente");
//        }); // failure
//        String finAttente = assertTimeoutPreemptively(Duration.ofMillis(2000), () -> {
//            System.out.println("debut attente (1s)");
//            Thread.sleep(1000);
//            return "fin attente";
//        }); // fonctionne avec la logique de ThrowingSupplier


        // ASSERT ALL : permet de verifier tout les asserts
//        assertAll(
//                "mon heading", // joue le rôle du message mais pour MultipleFailuresError
//                () -> assertEquals(m1,new Object()), // failure
//                () -> assertTimeout(Duration.ofMillis(50), m1::pourTest), // success
//                () -> assertTrue(m1.maMethode("taille").length() < 5 ) // failure
//        ); // success car pas d'echec parmis les asserts en param
        // fonctionne avec
        //      une collection d'executable,
        //      une serie d'executable,
        //      un tableau d'executable,
        //      un Stream d'executable.

        // FAIL - echoue le test

//        fail();
//        fail("message : echec arbitraire");
//        fail(() -> "message : echec arbitraire");
        try {
            throw new Exception("mon exception");
        }
        catch (Exception e){
//            fail(e);
            fail(e.getMessage(), e);
        }



        // des librairie contenant des assert supplémentaires peuvent être ajoutée,
        // comme par exemple hamcrest

    }


    @Test
    void maMethode_test1() {

        MaClasse monObjet = new MaClasse();
        String monResultat = monObjet.maMethode("maValeur");
        assertEquals("mon retour -> maValeur", monResultat);
        // assertEquals(Object,Object)
    }

    @Test
    void maMethode_test2() {

        MaClasse monObjet = new MaClasse();
        String monResultat = monObjet.maMethode("maValeur");
        assertEquals("mon retour : maValeur", monResultat);
        // assertEquals(Object,Object)
    }
}