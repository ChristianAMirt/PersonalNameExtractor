package edu.odu.cs.cs350;
import org.junit.jupiter.api.Test;

import weka.core.FastVector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.IOException;


@SuppressWarnings({ "deprecation", "rawtypes" })
public class TestTrainingDataInstances {
    

    /**
     * Tests the constructor for TrainingDataInstances, making sure that
     * the instantiation of the class as an object actually happens.
     * 
     * @throws IOException
     */
    @Test
    public void TestTrainingDataInstancesConstructor() throws IOException{
        TrainingDataInstances constructorTester = new TrainingDataInstances();
        assertNotNull(constructorTester);
    }


    /**
     * Tests the attributeAdding method within TrainingDataInstances
     * for the accuracy of the added values
     * 
     * @throws IOException
     */
    @Test
    public void testAttributeAdding() throws IOException {
        TrainingDataInstances attributeAddingTester = new TrainingDataInstances();
        assertEquals(0, attributeAddingTester.attributeInfo.size());

        attributeAddingTester.attributeAdding(attributeAddingTester.attributeInfo);
        assertEquals(14, attributeAddingTester.attributeInfo.size());

    }


    /**
     * Tests the getFileSize method within TrainingDataInstances
     * for the accuracy of the returned integer value.
     * 
     * @throws IOException
     */
    @Test
    public void testGetFileSize() throws IOException{
        TrainingDataInstances fileSizeTester = new TrainingDataInstances();
        int numberOfLines = fileSizeTester.getFileSize("src/main/data/trainingDataSmol.txt");

        assertEquals(44, numberOfLines);
    }

    /**
     * Tests the fastV method within TrainingDataInstances
     * for the accuracy of the returned fast vector.
     * 
     * @throws IOException
     */
    @Test
    public void testFastV() throws IOException{
        TrainingDataInstances fastVTester = new TrainingDataInstances();
        String[] testArray = {"Stand", "Proud"};
        FastVector myFastVector = fastVTester.fastV(testArray);

        assertEquals(2, myFastVector.size());
        assertEquals(0 , myFastVector.indexOf("Stand"));
        assertEquals(1 , myFastVector.indexOf("Proud"));

    }
}
