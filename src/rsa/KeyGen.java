package rsa.src.rsa;

/*
Todo List:
Just need the random prime number e between 1 and phi(n) -> initialize on line 32
After this is done, the rest of the initializing values can be uncommented (Lines 33, 34, and 35)
 */

import sun.jvm.hotspot.memory.DefNewGeneration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private Map<String, BigInteger> publicKey = new HashMap<>(); // publicKey = (randomPrime, n)
    private BigInteger privateKey; // privateKey = d (inverse)
    private Random rand = new Random();

    public KeyGen(BigInteger p, BigInteger q) { // p and q must be primes
        this.p = p;
        this.q = q;
        this.n = this.p.multiply(this.q);
        //this.phi = this.totient(this.totient(this.p), this.totient(this.q) ); // phi(n)
        this.phi = this.totient(this.p.subtract(BigInteger.ONE), this.q.subtract(BigInteger.ONE) ); // phi(n)
        this.randomPrime = this.randNum(this.phi); // random prime e (public key)
        this.inverse = this.randomPrime.modInverse(this.phi); // d = e^-1 mod phi(n)
        this.publicKey.put("e", this.randomPrime); // set publickey = (e, n)
        this.publicKey.put("n", this.n); // private key
        this.privateKey = this.inverse; // set privateKey = d (inverse)
    }

    public KeyGen() {
        do {
            this.p = BigInteger.probablePrime(512, rand);
            this.q = BigInteger.probablePrime(512, rand);
        }
        while(!this.p.isProbablePrime(100) && !this.q.isProbablePrime(100));
        this.n = this.p.multiply(this.q);
        this.phi = this.totient(this.totient(this.p), this.totient(this.q));
        this.privateKey = this.n;
    }

    public void getAll(){
        System.out.println("Prime p: " + this.p);
        System.out.println("Prime q: " + this.q);
        System.out.println("n = (p x q): " + this.n);
        System.out.println("Phi/Totient: " + this.phi);
        System.out.println("Public Key (e): " + this.randomPrime);
        System.out.println("Private key (d): " + this.inverse);
    }

    // returns totient of a prime number otherwise returns -1 meaning p isn't prime
    public BigInteger totient(BigInteger prime) {
        if(prime.isProbablePrime(100) ) {
            return prime.subtract(BigInteger.ONE);
        } else {
            return new BigInteger("-1");
        }
    }

    // returns totient of TWO primes otherwise returns -2 meaning one of the nums aren't relatively prime
    public BigInteger totient(BigInteger totientP, BigInteger totientQ) {
        return totientP.multiply(totientQ);
    }

    // picks a random prime number e between 1 < e < phi(p) such that gcd(e, phi(n) ) = 1
    public BigInteger randNum(BigInteger phi){
        Random rand = new Random();
        //just use isprobbably to generate, abandon the math.rand.
        BigInteger value;
        int number = (int) ((Math.random() * 1024 - 2 + 1) + 2);
        do{
            value = BigInteger.probablePrime(number,rand);
        }
        while ((value.compareTo(phi) != -1) && value.gcd(phi).compareTo(BigInteger.ONE) != 0 && value.compareTo(BigInteger.ONE) != 1 && !value.isProbablePrime(100));
        return value;
    }

    public void pubkey() {

        try {
            FileOutputStream fos = new FileOutputStream("pubkey.rsa");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.randomPrime);
            oos.writeObject(this.n);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pubkey.rsa"));
            //System.out.println("works" + (BigInteger) ois.readObject());
            
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //d and n inside privkey.rsa
    public void privkey(){
        try{
            FileOutputStream fos = new FileOutputStream("privkey.rsa");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.inverse);
            oos.writeObject(this.n);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("privkey.rsa"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}

/*
                               _
                            _ooOoo_
                           o8888888o
                           88" . "88
                           (| -_- |)
                           O\  =  /O
                        ____/`---'\____
                      .'  \\|     |//  `.
                     /  \\|||  :  |||//  \
                    /  _||||| -:- |||||_  \
                    |   | \\\  -  /'| |   |
                    | \_|  `\`---'//  |_/ |
                    \  .-\__ `-. -'__/-.  /
                  ___`. .'  /--.--\  `. .'___
               ."" '<  `.___\_<|>_/___.' _> \"".
              | | :  `- \`. ;\_ _/; .'/ /  .' ; |
              \  \ `-.   \_\_`. _.'_/_/  -' _.' /
    ===========`-.`___`-.__\ \___/ /__.-'___'.-'===========
                            `=---='

  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
*/
