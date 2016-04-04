/**
 * 
 */
package io.github.pbremer.icecreammanager.utils;

import static io.github.pbremer.icecreammanager.utils.StringHelper.convertPenniesStringToDecimalString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

/**
 * @author Patrick Bremer
 */
public class StringHelperTest {

    @Test
    public void convertPenniesStringToDecimalStringTest() {
	assertThat("String was not the same",
	        convertPenniesStringToDecimalString("5479"), equalTo("54.79"));
	assertThat("String was not the same",
	        convertPenniesStringToDecimalString("0875"), equalTo("8.75"));
	assertThat("String was not the same",
	        convertPenniesStringToDecimalString("14"), equalTo("0.14"));
	assertThat("String was not the same",
	        convertPenniesStringToDecimalString("9"), equalTo("0.09"));
	assertThat("String was not the same",
	        convertPenniesStringToDecimalString("3200"), equalTo("32.00"));
	assertThat("String was not the same",
	        convertPenniesStringToDecimalString("-2309"),
	        equalTo("-23.09"));
	assertThat("String was not the same",
	        convertPenniesStringToDecimalString("-1"), equalTo("-0.01"));
	assertThat("String was not the same",
	        convertPenniesStringToDecimalString("0"), equalTo("0.00"));
	assertThat("String was not the same",
	        convertPenniesStringToDecimalString("45.19"), equalTo("0.45"));
	assertThat("String was not the same",
	        convertPenniesStringToDecimalString("45.89"), equalTo("0.46"));
	assertThat("String was not the same",
	        convertPenniesStringToDecimalString("-45.19"),
	        equalTo("-0.45"));
	assertThat("String was not the same",
	        convertPenniesStringToDecimalString("-45.89"),
	        equalTo("-0.46"));
	assertThat("String was not the same",
	        convertPenniesStringToDecimalString("4045.89"),
	        equalTo("40.46"));
    }

    @Test(expected = NumberFormatException.class)
    public void convertPenniesStringToDecimalStringEmptyStringEdgeCaseTest() {
	convertPenniesStringToDecimalString("");
    }

    @Test(expected = NumberFormatException.class)
    public void convertPenniesStringToDecimalStringAlphaStringEdgeCaseTest() {
	convertPenniesStringToDecimalString("234G5");
    }
}
