package bstorm.akimts;

import bstorm.akimts.exo.TypeEntreprise;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class RepeatedTestClass {

    @RepeatedTest(value = 10, name = "rep: ({currentRepetition}/{totalRepetitions})")
    void test_incrementTo100_000(){
        MaClasse monObjet = new MaClasse();
        assertEquals(100_000, monObjet.incrementTo100_000());
    }


    @ParameterizedTest(name = "[{index}] - {arguments}")
    @NullAndEmptySource
    @ValueSource(strings = {"a","b","c"})
    void testParam1(String param){
        System.out.println(param);
    }

    @ParameterizedTest
    @EnumSource(
            value = TypeEntreprise.class,
            names = {"PME", "INDEPENDANT"},
            mode = EnumSource.Mode.INCLUDE
    )
    void testParam2(TypeEntreprise type){
        System.out.println(type);
    }

    @ParameterizedTest
    @CsvSource({
            "1,a",
            "2,b",
            "3,c"
    })
    void testParam3(int nbr, char carac){
        System.out.println(nbr+" - " + carac);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/file.csv", numLinesToSkip = 1)
    void testParam4(int nbr, char carac){
        System.out.println(nbr+" - " + carac);
    }

    @ParameterizedTest
    @MethodSource("argSupplier")
    void testParam5(int nbr, char carac){
        System.out.println(nbr+" - " + carac);
    }

    @ParameterizedTest
    @ArgumentsSource(ArgProvider.class)
    void testParam6(int nbr, char carac){
        System.out.println(nbr+" - " + carac);
    }

    static Stream<? extends Arguments> argSupplier(){
        return Stream.of(
                Arguments.of(1,'a'),
                Arguments.of(2,'b'),
                Arguments.of(3,'c')
        );
    }
}
