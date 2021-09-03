package bstorm.akimts.exo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class EntrepriseImplTest {

    @Test
    @DisplayName("EntrepriseImpl(String nom) : IllArg when null input")
    @Tag("ctor1") @Tag("except")
    void testCtor1_nomNull_thenException(){
        assertThrows(IllegalArgumentException.class, () -> new EntrepriseImpl(null));
    }

    @Test
    @Tag("ctor1")
    void testCtor1_valueOk_resultOk(){
        EntrepriseImpl entreprise = new EntrepriseImpl("value");

        assertAll(
                () -> assertEquals("value", entreprise.getNom()),
                () -> assertNull(entreprise.getEmployes()),
                () -> assertEquals(TypeEntreprise.FICTIVE, entreprise.getType())
        );
    }

    @Test
    @Tag("except")
    void testCtor2_paramNull_thenIllegalArgument(){
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new EntrepriseImpl("value", null)),
                () -> assertThrows(IllegalArgumentException.class, () -> new EntrepriseImpl(null,"value")),
                () -> assertThrows(IllegalArgumentException.class, () -> new EntrepriseImpl(null, null))
        );
    }

    @Test
    void testCtor2_paramOk_resultOk(){

        String nomEntreprise = "nom";
        String nomEmploye = "employe";

        EntrepriseImpl entreprise = new EntrepriseImpl(nomEntreprise, nomEmploye);

        assertAll(
                () -> assertEquals(nomEntreprise, entreprise.getNom()),
                () -> assertTrue( entreprise.getEmployes().size() == 1 && entreprise.getEmployes().get(0).equals(nomEmploye) ),
                () -> assertEquals( TypeEntreprise.INDEPENDANT, entreprise.getType() )
        );

    }

    @Test
    @Tag("except")
    void testCtor3_nomNull_thenException(){
        assertThrows(IllegalArgumentException.class, () -> new EntrepriseImpl(null, null, TypeEntreprise.FICTIVE));
    }


    @Tag("except")
    @ParameterizedTest
    @EnumSource(
            value = TypeEntreprise.class,
            names = "FICTIVE",
            mode = EnumSource.Mode.EXCLUDE
    )
    void testCtor3_inconsistancy(TypeEntreprise type){

        List<String> listVide = new ArrayList<>();

        assertAll(
                () -> assertThrows(TypeEntrepriseInconsistancyException.class, ()-> new EntrepriseImpl("value", null, type)) ,
                () -> assertThrows(TypeEntrepriseInconsistancyException.class, ()-> new EntrepriseImpl("value", null, type))
        );
    }

    @Test
    void testCtor3_valueOk_resultOk(){

        int nbrEmploye = 200;
        String inputNom ="nom entreprise";
        List<String> inputList = Stream.generate(()-> "nom d'employe")
                .limit(nbrEmploye)
                .collect(Collectors.toList());

        assertAll(
                () -> {
                    EntrepriseImpl entreprise = new EntrepriseImpl(inputNom, inputList, TypeEntreprise.MULTINAT);
                    assertEquals(nbrEmploye, entreprise.getEmployes().size());
                },
                () -> {
                    EntrepriseImpl entreprise = new EntrepriseImpl(inputNom, inputList, TypeEntreprise.PME);
                    assertEquals(TypeEntreprise.PME.getNbrMaxEmploye(), entreprise.getEmployes().size());
                },
                () -> {
                    EntrepriseImpl entreprise = new EntrepriseImpl(inputNom, inputList, TypeEntreprise.INDEPENDANT);
                    assertEquals(TypeEntreprise.INDEPENDANT.getNbrMaxEmploye(), entreprise.getEmployes().size());
                },
                () -> {
                    EntrepriseImpl entreprise = new EntrepriseImpl(inputNom, inputList, TypeEntreprise.FICTIVE);
                    assertNull(entreprise.getEmployes());
                }
        );
    }


    @Test
    @Tag("except")
    void engager_whenToAddNull_thenException() {
        EntrepriseImpl entreprise = new EntrepriseImpl("entreprise", List.of("mon employe"), TypeEntreprise.MULTINAT);
        assertThrows(IllegalArgumentException.class, () -> entreprise.engager(null));
    }

    @Test
    void engager_whenValueOk_thenResultOk(){
        EntrepriseImpl entreprise = new EntrepriseImpl("entreprise", List.of("mon employe"), TypeEntreprise.MULTINAT);
        String toAdd = "employe ajoute";
        entreprise.engager(toAdd);
        assertTrue( entreprise.getEmployes().size() == 2 && entreprise.getEmployes().contains(toAdd) );
    }

    @Test
    @Tag("except")
    void virer_whenToRemoveNull_thenException() {
        EntrepriseImpl entreprise = new EntrepriseImpl("entreprise", List.of("mon employe"), TypeEntreprise.MULTINAT);
        assertThrows(IllegalArgumentException.class, () -> entreprise.virer(null));
    }

    @Test
    @Tag("lifecycle")
    void virer_whenEmployePresent_thenRemove(){
        String toFire = "to fire";
        EntrepriseImpl entreprise = new EntrepriseImpl("entreprise", List.of("mon employe", toFire), TypeEntreprise.MULTINAT);
        assertTrue( entreprise.virer(toFire) && entreprise.getEmployes().size() == 1 );
    }

    @Test
    void virer_whenEmployeAbsent_thenFalse(){
        String toFire = "to fire";
        EntrepriseImpl entreprise = new EntrepriseImpl("entreprise", List.of("mon employe"), TypeEntreprise.MULTINAT);
        assertFalse( entreprise.virer(toFire) || entreprise.getEmployes().size() != 1 );
    }
}
