package rsa;

import java.math.BigInteger;
import java.util.Random;

/* ToDo
check that p != q
 */

// Write a program KeyGen.java to implement the key generation part of the RSA system.
public class KeyGen {

    private BigInteger p; // large prime p
    private BigInteger q; // large prime q
    private BigInteger n; // n = p * q
    private BigInteger phi; // phi(n) = (p - 1) * (q - 1)
    private BigInteger randomPrime; // 1 < e < phi and gcd(e, phi) = 1
    private BigInteger inverse;
    private BigInteger privateKey;
    private BigInteger publicKey;
    private Random rand;

    public KeyGen(BigInteger p, BigInteger q) { // p and q must be primes
        this.p = p;
        this.q = q;
        this.n = this.p.multiply(this.q);
        this.phi = this.totient(this.totient(this.p), this.totient(this.q) );

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
        return this.phi;
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

