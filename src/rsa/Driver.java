package src.rsa;

import java.math.BigInteger;
import java.util.*;


public class Driver {

    public static void main(String[] args) {
        Random rand = new Random(); // create a random object; passed to random prime creation function

        BigInteger p = BigInteger.probablePrime(512, rand); // create a prime p
        BigInteger q = BigInteger.probablePrime(512, rand); // create a prime q


        System.out.println("Prime p = " + p); // print out p
        System.out.println("Prime q = " + q); // print out q

        //System.out.println(p.isProbablePrime(100) ); // checks to see if p is prime
        //System.out.println(q.isProbablePrime(100) ); // checks to see if q is prime

        KeyGen rsa = new KeyGen(p, q); // create KeyGen from primes p and q

        System.out.println("phi(n) = " + rsa.phi() ); // prints out the phi function (totient) of n

        System.out.println("random");
        System.out.println(rsa.randNum(rsa.phi()));

        /*KeyGen test = new KeyGen();
        System.out.println("this.p " + test.getP());
        System.out.println("this.q " + test.getQ());*/


        //System.out.println(rsa.getD(rsa.randNum(rsa.phi()), rsa.phi()));
        //System.out.println(rsa.randNum(rsa.phi()));
        //System.out.println(num2.modInverse(new BigInteger("17") )); // multiplicative inverse of 8 is 15 mod 17. (8^-1 mod 17 = 15)
        System.out.println("get all");
        rsa.getAll();
    }
}
