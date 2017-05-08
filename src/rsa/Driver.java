package src.rsa;

import java.math.BigInteger;
import java.util.*;


public class Driver {

    public static void main(String[] args) {
        Random rand = new Random(); // create a random object; passed to random prime creation function

        BigInteger p = BigInteger.probablePrime(512, rand); // create a prime p
        BigInteger q = BigInteger.probablePrime(512, rand); // create a prime q

<<<<<<< HEAD:src/rsa/Driver.java

        System.out.println(p);
        System.out.println();
        System.out.println(q);
        System.out.println(p.isProbablePrime(100) );
        System.out.println(q.isProbablePrime(100) );


        KeyGen rsa = new KeyGen(p,q);
        //KeyGen rsa = new KeyGen(p, q);

        System.out.println("phi");
        System.out.println(rsa.phi());
=======
        System.out.println("Prime p = " + p); // print out p
        System.out.println("Prime q = " + q); // print out q

        //System.out.println(p.isProbablePrime(100) ); // checks to see if p is prime
        //System.out.println(q.isProbablePrime(100) ); // checks to see if q is prime

        KeyGen rsa = new KeyGen(p, q); // create KeyGen from primes p and q

        System.out.println("phi(n) = " + rsa.phi() ); // prints out the phi function (totient) of n

        //System.out.println(num2.modInverse(new BigInteger("17") )); // multiplicative inverse of 8 is 15 mod 17. (8^-1 mod 17 = 15)
>>>>>>> 0266e949ba0a5c1cde53ab99061297554a03a8db:Driver.java

    }

}
