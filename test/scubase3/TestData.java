package scubase3;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
/**
* TestData class contains common variables and values to reduce duplicate
* code, also allows test data to be easily inserted into an array and
* iterated over.
*/
public class TestData {

   public double inputA;
   public double inputB;
   public String expectedResult;

   public TestData(double inputA, double inputB, String expectedResult) {
       this.inputA = inputA;
       this.inputB = inputB;
       this.expectedResult = expectedResult;
   }

   /**
    * Returns "Test passed!" or "Test failed!" based on whether the
    * expected result is equal to the actual result
    *
    * @param result
    * @return
    */
   public String getTestStatus(String result) {
       assertEquals(expectedResult, result);
       StringBuilder sb = new StringBuilder();
       if (result.equals(expectedResult)) {
           sb.append("Test passed!");
       } else {
           sb.append("Test failed!");
           sb.append("Expected: ").append(expectedResult)
                   .append(" Got: ").append(result);
       }

       return result.equals(expectedResult) ? "Test passed!" : "Test failed!";
   }
}