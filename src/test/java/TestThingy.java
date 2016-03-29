import java.text.DecimalFormat;

import org.junit.Test;

/**
 * 
 */

/**
 * @author Patrick Bremer
 */
public class TestThingy {

    @Test
    public void testThis() {
	DecimalFormat fmt = new DecimalFormat("0.00");
	System.out.println(fmt.format(Long.valueOf("0845")));
    }
}
