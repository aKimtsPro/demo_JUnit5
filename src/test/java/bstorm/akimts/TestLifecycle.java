package bstorm.akimts;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class TestLifecycle {

    int a = 0;

    @BeforeEach
    void setup(){
        System.out.println("Mise en place d'une situation de test commune " +
                "\nsetup : " + this );
        a++;
    }

    @AfterEach
    void tearDown(){
        System.out.println("Restauration");
    }

    static Connection co;

    @BeforeAll
    static void setupAll(){
//        co = DriverManager.getConnection("");
        System.out.println("Avant la serie de test de TestLifecycle");
    }

    @AfterAll
    static void tearDownAll(){
//        co.close();
        System.out.println("Après la serie de test de TestLifecycle");
    }

    @Test
    @Order(1)
    void test1(){
        assertEquals(a, 1);
    }

    @Test
    @Order(210)
    void test2(){
        assertEquals(a, 2);
    }

    @Test
    @Disabled  // le before/after each pas executé
    @Order(36468)
    @Tag("lifecycle")
    void test3(){
//        assumeTrue(false); // le before/after each executé
    }

}
