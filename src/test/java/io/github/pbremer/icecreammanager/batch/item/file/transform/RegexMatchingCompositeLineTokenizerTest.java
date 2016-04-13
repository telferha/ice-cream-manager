/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.item.file.transform;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;

/**
 * @author Patrick Bremer
 */
public class RegexMatchingCompositeLineTokenizerTest {

    private RegexMatchingCompositeLineTokenizer tokenizer;

    @Before
    public void setup() {
	Map<String, LineTokenizer> matcher =
	        new HashMap<String, LineTokenizer>();

	FixedLengthTokenizer fixedLengthTokenizer1 = new FixedLengthTokenizer();
	Range[] columns1 = new Range[2];
	columns1[0] = new Range(1, 1);
	columns1[1] = new Range(2, 8);
	fixedLengthTokenizer1.setColumns(columns1);
	fixedLengthTokenizer1.setStrict(false);
	matcher.put("[0-9].*", fixedLengthTokenizer1);

	FixedLengthTokenizer fixedLengthTokenizer2 = new FixedLengthTokenizer();
	Range[] columns2 = new Range[2];
	columns2[0] = new Range(1, 5);
	columns2[1] = new Range(6, 13);
	fixedLengthTokenizer2.setColumns(columns2);
	fixedLengthTokenizer2.setStrict(false);
	matcher.put("[A-Z].*", fixedLengthTokenizer2);

	tokenizer = new RegexMatchingCompositeLineTokenizer();
	tokenizer.setTokenizers(matcher);
    }

    @Test
    public void matchTest1() {
	FieldSet fs = tokenizer.tokenize("0ttttttt");
	assertThat("Pattern matching did not map to correct fieldmapper",
	        fs.readString(0), equalTo("0"));
	assertThat("Pattern matching did not map to correct fieldmapper",
	        fs.readString(1), equalTo("ttttttt"));
    }

    @Test
    public void matchTest2() {
	FieldSet fs = tokenizer.tokenize("AAAAA++++++++");
	assertThat("Pattern matching did not map to correct fieldmapper",
	        fs.readString(0), equalTo("AAAAA"));
	assertThat("Pattern matching did not map to correct fieldmapper",
	        fs.readString(1), equalTo("++++++++"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void returnDefaultTokenizer() {
	FieldSet fs = tokenizer.tokenize("....++++++++");
	assertThat("Pattern matching did not map to correct fieldmapper",
	        fs.readString(0), equalTo("."));
	fs.readString(1);
    }
}
