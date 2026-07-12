package code.manager;

import org.junit.jupiter.api.Test;

import java.util.List;

public class CodeRetrieveTest {

    CodeRetrieve codeRetrieve = new CodeRetrieve();
    @Test
    public  void getHighest(){

        List<String> numberStrList = List.of("21","42","69","32","51");
        String highest = codeRetrieve.getHighersCodeFromCollection(numberStrList);
        assert ("69".equalsIgnoreCase(highest));

    }
}
