package sankar.learn;

import org.junit.Test;

public class BytesToChar {
	/**
	 * How bytes are converted to character in java
 	 */

	@Test
	public void byteToChar() {
		/**
		 * How bytes are converted to character
		 * bytes are 8 bit in java [-128 to 128]
		 * character is 16 bit in java. So how is 8 bit byte can supply enougf information for 16 bit char
		 * from the java language spec: byte to char conversion
		 * byte is first widened to int
		 * and then int is narrowed to char
		 */

		byte b = 100;
		char c = (char)b;
		System.out.println(c);
	}

	@Test
	public void allCharRepresentingBytes() {
		byte b = -128; //range of byte is -128 to 127
		while(b != 127) {
			System.out.println((char)b); //0 to 127 is ascii characters
			++b;
		}
		System.out.println((char)b);
	}

	@Test
	public void allCharactersJavaSupport() {
		/**
		 * Code points:A coe points an atomic unit of information which represents a character. texts are sequence of code points
		 */
		int charLimit = (int)Math.pow(2, 16);
		System.out.println(charLimit);
		for (int i = 0; i <= charLimit; i++) {
			System.out.print((char)i);
		}
	}

	@Test
	public void allUnicodePointsCharCanSupport() {
		/**
		 * Java char is 16bit. its range is from 0 to 2^16 (65536). But unicode characters has grown to 143,859,
		 * java has additional support for new characters https://www.oracle.com/technical-resources/articles/javase/supplementary.html
		 */
		char c = '\u0000';
		for(int i=0; i < 10000; i++) {
			System.out.print((char)++c);
		}
	}
}
