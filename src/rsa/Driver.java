package src.rsa;

import java.math.BigInteger;
import java.util.Random;


public class Driver {

    public static void main(String[] args) {
        Random rand = new Random();

        BigInteger p = BigInteger.probablePrime(512, rand);
        BigInteger q = BigInteger.probablePrime(512, rand);
        BigInteger num1 = new BigInteger("1");
        BigInteger num2 = new BigInteger("9");


        System.out.println(p);
        System.out.println();
        System.out.println(q);
        System.out.println(p.isProbablePrime(100) );
        System.out.println(q.isProbablePrime(100) );


        KeyGen rsa = new KeyGen(p,q);
        //KeyGen rsa = new KeyGen(p, q);

        System.out.println("phi");
        System.out.println(rsa.phi());

    }

}
