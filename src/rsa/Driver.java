package rsa.src.rsa;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.*;


public class Driver {

    public static void main(String[] args) {
        Random rand = new Random(); // create a random object; passed to random prime creation function

        BigInteger p = BigInteger.probablePrime(512, rand); // create a prime p
        BigInteger q = BigInteger.probablePrime(512, rand); // create a prime q

        KeyGen rsa = new KeyGen(p, q); // create KeyGen from primes p and q

        System.out.println("RSA Crypto Values:");
        rsa.getAll();
        System.out.println();

        DigitalSignature ds = new DigitalSignature();
        ds.setSignMag(ds.getBytes()); // Message Digest before signing - array
        ds.initialSigning(); //



    }
}
