
/**
 * Source code for the T4 Security practical
 * 
 * @author nve 
 * @version 0.1
 */

import java.io.FileReader;
import java.io.Console; // for hidden password input
import java.util.Random;
import java.util.Scanner; // for clear text input
import java.security.MessageDigest; // for MD5
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter; // for Hex formatting of byte[]
import com.opencsv.CSVReader; // library for reading CSV files (jar in +libs folder)

public class LabSecurity {
	private static final char[] characters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
			'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&', '*', '(',
			')', '~', '`', ':', '\'', '_', '-', '+', '=', '{', '}', '[', ']', ';', '"', '|', '\\', '<', ',', '.', '>',
			'/', '?','1', '2', '3', '4', '5', '6', '7','8','9','0' };

	private static final Random RANDOM = new SecureRandom();
	private static final int HASH_ITERS = 10000;
	private static final int SALT_LENGTH = 32;
	private static final int HASH_LENGTH = 512;

	private boolean found;
	private String match;

	private MessageDigest md5; // implements the algorithm
	private byte[] byteRepresentation;
	private byte[] hash;
	private static byte []salt;
	private String strHexHash;

	private HexBinaryAdapter hba; // used to convert byte[] to String

	/**
	 * Constructor for objects of class LabSecurity
	 */
	public LabSecurity() {
		try {
			md5 = MessageDigest.getInstance("MD5");
			hba = new HexBinaryAdapter();
		} catch (NoSuchAlgorithmException nsae) {
		}
	}

	public void tryMD5() {
		try {
			String str;
			do {
				System.out.println("Input a string to generate its MD5 hash (press enter to quit)");
				Scanner terminalInput = new Scanner(System.in);
				str = terminalInput.nextLine();
				// Comment the 2 lines above and uncomment the 2 lines below to hide password
				// input (will raise exception in IDE)
				// Console console = System.console();
				// str = new String(console.readPassword("Please enter your password: "));
				if (!str.equals("")) {
					System.out.println(encodeMD5(str));
				}
			} while (!str.equals(""));
		} catch (Exception e) {
		}

	}

	private String encodeMD5(final String input) throws Exception {
		// convert the string to byte[] using the UFT-8 character encoding
		byteRepresentation = input.getBytes("UTF-8");
		// generate the hash
		hash = md5.digest(byteRepresentation);
		// HexBinaryAdapter's marshal method converts byte[] to String representation of
		// hexadeciaml characters
		strHexHash = hba.marshal(hash);
		return strHexHash;
	}

	public void bruteForce(final String input) {
		try {
			CSVReader reader = new CSVReader(new FileReader(input));
			String[] nextLine;
			reader.readNext(); // skip first line containing headers
			System.out.println("Brute force");

			while ((nextLine = reader.readNext()) != null) {
				// nextLine[] is an array of values from the line
				System.out.print(nextLine[0] + " " + nextLine[1] + " ");
				// TODO call bruteForceRecursive
				found = false;

				//bruteForceRecursive(5, 0, "", nextLine[1]);

				String password = "not found";
				if (found) {
					password = match;
				}
				System.out.println("password is: " + password);

			}
		} catch (Exception e) {
		}
	}

	/**
	 * Recursively explore all passwords shorter or equal to parameter <length>,
	 * made up of characters from the <characters> class variable to find the
	 * password which has the same MD5 hash as parameter <hash>. The <found> global
	 * variable needs to be initialised to false prior to calling the method.
	 * Results are stored in the <found> and <match> global variables.
	 * 
	 * bruteForceRecursive(10, 0, "", <hash>) will try passwords of 10 or fewer
	 * characters until it finds one that hashes to <hash> or all passwords have
	 * been tried unsuccessfully.
	 * 
	 */
	
	public static byte[]getNextSalt(){
		salt = new byte[32];
		RANDOM.nextBytes(salt);
		return salt;
	}
	
	public static char[]getNextPassword(){
		char[] password = System.console().readPassword();
		return password;
	}
	
	private void bruteForceRecursive(final int length, final int position, final String baseString, final String hash)
			throws Exception {
		// test whether baseString + any of the potential characters is the hash
		for (int i = 0; i < characters.length & !found; i++) {
			String attempt = baseString + characters[i];
			found = hash.equals(encodeMD5(attempt));
			if (found) {
				match = attempt;
			} else {
				// if the last attempt was unsuccessful
				// and the maximum password length is not reached
				// then recursively call the method to try a longer password
				if (position < length - 1) {
					bruteForceRecursive(length, position + 1, attempt, hash);
					System.out.println(attempt);
				}

			}
		}
	}

	public String generateHash(String password) {
		//hashPassword(characters, salt, HASH_ITERS, HASH_LENGTH);
		String result = "";
		return result;
	}
	
	

	private byte[] hashPassword(final char[] password, final byte[] salt, final int iterations, final int keyLength) {

		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
			SecretKey key = skf.generateSecret(spec);
			byte[] res = key.getEncoded();
			System.out.println(skf + " " + spec + " " + res);
			
			return res;
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
	}

}
