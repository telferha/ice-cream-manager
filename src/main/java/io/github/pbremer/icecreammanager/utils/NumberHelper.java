/**
 * 
 */
package io.github.pbremer.icecreammanager.utils;

import java.math.BigDecimal;

/**
 * @author Patrick Bremer
 */
public class NumberHelper {

    /**
     * Converts the string value of pennies to the string values of dollars. If
     * the input string is a decimal (parts of a cent) the resulting cent will
     * be rounded to the nearest whole.
     * <p>
     * Example: <blockquote>
     * 
     * <pre>
     * 5479 = 54.79
     * 0875 = 8.75
     * 14 = 0.14
     * 9 = 0.09
     * 3200 = 32.00
     * -2309 = -23.09
     * 45.19 = 0.45
     * 45.89 = 0.46
     * -45.19 = -0.45
     * -45.89 = -0.46
     * </pre>
     * 
     * </blockquote>
     * 
     * @param pennies
     *            string representation of how many cents something is
     * @return the string of the conversion of pennies to dollars
     * @throws NumberFormatException
     *             if the string cannot be parsed into an integer
     */
    public static BigDecimal convertPenniesStringToDecimal(String pennies) {
	return new BigDecimal(pennies).movePointLeft(2);
    }
}
