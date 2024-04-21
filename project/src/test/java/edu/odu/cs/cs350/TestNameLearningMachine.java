package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import weka.core.FastVector;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.IOException;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeAll;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SuppressWarnings({ "deprecation", "rawtypes" })
public class TestNameLearningMachine {

    /**
     * NameLearningMachine object that will be used for testing.
     */
    private NameLearningMachine myLearningMachine;

    /**
     * Calls the constructor of NameLearningMachine only
     * once. This allows for the same NameLearningMachine
     * object to be used for every test here.
     * 
     * @throws IOException
     */
    @BeforeAll
    public void callConstructor() throws IOException{
        myLearningMachine = new NameLearningMachine();
    }

    /**
     * Tests the constructor for NameLearningMachine, making sure that
     * the instantiation of the class as an object actually happens.
     * 
     * @throws IOException
     */
    @Test
    public void TestNameLearningMachineConstructor() throws IOException{
        assertNotNull(myLearningMachine);
    }

    /**
     * Tests the attributeAdding method within NameLearningMachine
     * for the accuracy of the added values
     * 
     * @throws IOException
     */
    @Test
    public void testAttributeAdding() throws IOException {
        assertEquals(14, myLearningMachine.attributeInfo.size());
    }

    /**
     * Tests the getFileSize method within NameLearningMachine
     * for the accuracy of the returned integer value.
     * 
     * @throws IOException
     */
    @Test
    public void testGetFileSize() throws IOException{
        int numberOfLines = myLearningMachine.getFileSize("src/main/data/trainingDataSmol.txt");
        assertEquals(101, numberOfLines);
    }

    /**
     * Tests the fastV method within NameLearningMachine
     * for the accuracy of the returned fast vector.
     * 
     * @throws IOException
     */
    @Test
    public void testFastV() throws IOException{
        String[] testArray = {"Stand", "Proud"};
        FastVector myFastVector = myLearningMachine.fastV(testArray);

        assertEquals(2, myFastVector.size());
        assertEquals(0 , myFastVector.indexOf("Stand"));
        assertEquals(1 , myFastVector.indexOf("Proud"));
    }
}
