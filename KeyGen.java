package rsa;

/**
 * Created by dannypadilla on 4/16/17.
 */
public class KeyGen {


    /*

    1. Write a program KeyGen.java to implement the key generation part of the RSA system.

·         Pick p and q to be random primes of some specified length using the appropriate BigInteger constructor for Java.

·         Calculate n =  p x q

·         Calculate ø(n) = ( p-1)x(q-1)

·         Pick e to be a random prime between 1 and ø(n), such that gcd(e, ø(n)) = . e should be similar in (bit) length
          to p and q, but does not have to be the same length.

·         Calculate  d = e-1 mod ø(n) :

In BigInteger the method used for this purpose is

public BigInteger modInverse(BigInteger m)

When you execute this program, it should generate new public and private keys for your RSA cryptosystem,
where p, q and e as defined above are all 512-bit integers and n  should be ~1024 bits. Your program should output all
three values e, d and n to the console as it generates them.  The values e and n should also be saved to a file called
"pubkey.rsa" and the values d and n should be saved to a file "privkey.rsa".  To allow for nice access of these files,
you MUST output and input these keys to and from the files using a Java ObjectOutputStream and ObjectInputStream.

     */


}

