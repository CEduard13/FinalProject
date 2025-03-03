package Session;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class HardSoftAssertions {
    public static void main(String[] args) {
        /*SoftAssert softAssertion = new SoftAssert();

        softAssertion.assertEquals(1, 2);
        softAssertion.assertEquals(2, 3);
        softAssertion.assertEquals("expected", "actual");

        softAssertion.assertAll();*/

        Assert.assertEquals(2, 5);
        Assert.assertEquals("expected", "actual");

    }
}
