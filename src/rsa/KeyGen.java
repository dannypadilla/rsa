
package src.rsa;

/*
Todo List:
Just need the random prime number e between 1 and phi(n) -> initialize on line 32
After this is done, the rest of the initializing values can be uncommented (Lines 33, 34, and 35)
 */

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

// Write a program KeyGen.java to implement the key generation part of the RSA system.
public class KeyGen {

    private BigInteger p; // large prime p
    private BigInteger q; // large prime q
    private BigInteger n; // n = p * q
    private BigInteger phi; // phi(n) = (p - 1) * (q - 1)
    private BigInteger randomPrime; // 1 < e < phi and gcd(e, phi) = 1
    private BigInteger inverse; // inverse = e^-1 mod phi(n)
    private Map<String, BigInteger> publicKey = new HashMap<>(); // publicKey = (randomPrime, inverse)
    private BigInteger privateKey; // privateKey = n
    private Random rand;

    public KeyGen(BigInteger p, BigInteger q) { // p and q must be primes
        this.p = p;
        this.q = q;
        this.n = this.p.multiply(this.q);
        this.phi = this.totient(this.totient(this.p), this.totient(this.q) );
        // random prime initialize goes here
        //this.inverse = this.randomPrime.modInverse(this.phi); // d = e^-1 mod phi(n)
        //this.publicKey.put("e", this.randomPrime); // set publickey = (e, d)
        //this.publicKey.put("d", this.inverse);
        this.privateKey = this.n; // set privateKey = n
    }

    // returns totient of a prime number otherwise returns -1 meaning p isn't prime
    public BigInteger totient(BigInteger prime) {
        if(prime.isProbablePrime(100) ) {
            return prime.subtract(new BigInteger("1") );
        } else {
            return new BigInteger("-1");
        }
    }

    // returns totient of two primes otherwise returns -2 meaning one of the nums aren't relatively prime
    public BigInteger totient(BigInteger totientP, BigInteger totientQ) {
        if(p.isProbablePrime(100) && q.isProbablePrime(100) ) {
            return totientP.multiply(totientQ);
        } else {
            return new BigInteger("-2"); // need to do prime factorization
        }

    }

    public BigInteger phi() {
        BigInteger holder;
        BigInteger temp = this.p.subtract(BigInteger.ONE);

        BigInteger temp2 = this.q.subtract(BigInteger.ONE);
        holder = temp.multiply(temp2);

        return holder;
    }

    public BigInteger randNum(BigInteger phi){
        //BigInteger temp = BigInteger.valueOf(String.valueOf(Math.random() * phi) + 1);
        //BigInteger temp = BigInteger.valueOf(Math.random() * phi.doubleValue());

        /*BigDecimal value = new BigDecimal((Math.random() * phi) + 1);*/
//        BigInteger range = phi.subtract(BigInteger.ONE).add(BigInteger.ONE);
        //just use isprobbably to generate, abandon the math.rand.
        BigDecimal value = new BigDecimal(Math.random());

        System.out.println("phi double" + phi.doubleValue());
        //value.multiply(range);

        BigInteger temp = this.phi.multiply(value.toBigInteger()).add(BigInteger.ONE);


        System.out.println("value to big int mult phi");
        System.out.println(value.toBigInteger().multiply(phi));
        System.out.println("value " + value);
        System.out.println("phi" + phi);
        System.out.println("temp " + temp);

        return temp;
    }



    // picks a random prime number e between 1 < e < phi(p) such that gcd(e, phi(n) ) = 1


    // Pick e to be a random prime between 1 and ø(n), such that gcd(e, ø(n)) = . e should be similar in (bit) length to p and q, but does not have to be the same length.
     // Calculate  d = e-1 mod ø(n) :

    // In BigInteger the method used for this purpose is
     // public BigInteger modInverse(BigInteger m)

/*
When you execute this program, it should generate new public and private keys for your RSA cryptosystem,
where p, q and e as defined above are all 512-bit integers and n  should be ~1024 bits. Your program should output all
three values e, d and n to the console as it generates them.  The values e and n should also be saved to a file called
"pubkey.rsa" and the values d and n should be saved to a file "privkey.rsa".  To allow for nice access of these files,
you MUST output and input these keys to and from the files using a Java ObjectOutputStream and ObjectInputStream.
     */


}

