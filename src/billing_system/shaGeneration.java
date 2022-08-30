package billing_system;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class shaGeneration {
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        /* MessageDigest instance for hashing using SHA256 */
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        /* digest() method called to calculate message digest of an input and return array of byte */
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash)
    {
        /* Convert byte array of hash into digest */
        BigInteger number = new BigInteger(1, hash);
        /* Convert the digest into hex value */
        StringBuilder hexString = new StringBuilder(number.toString(16));
        /* Pad with leading zeros */
        while (hexString.length() < 32)
            hexString.insert(0, '0');
        return hexString.toString();
    }

    public static void main(String args[])
    {
        try
        {
            String string1 = "myPassword";
            System.out.println("\n" + string1 + " : " + toHexString(getSHA(string1)));
            System.out.println(string1.length());
            String string2;
            Scanner input = new Scanner(System.in);
            string2 = input.nextLine();
            input.close();

            if (toHexString(getSHA(string1)).equals(toHexString(getSHA(string2)))){
                System.out.println("Password Match");
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
    }
}
