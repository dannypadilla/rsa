
package src.rsa;

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
    private Map<String, BigInteger> publicKey = new HashMap<>(); // publicKey = (randomPrime, inverse)
    private BigInteger privateKey; // privateKey = n
    private Random rand = new Random();

    public KeyGen(BigInteger p, BigInteger q) { // p and q must be primes
        this.p = p;
        this.q = q;
        this.n = this.p.multiply(this.q);
        this.phi = this.totient(this.totient(this.p), this.totient(this.q) );
        // random prime initialize goes here
        this.randomPrime = randNum(this.phi);
        this.inverse = this.randomPrime.modInverse(this.phi); // d = e^-1 mod phi(n)

        //this.publicKey.put("e", this.randomPrime); // set publickey = (e, d)
        //this.publicKey.put("d", this.inverse);
        this.privateKey = this.n; // set privateKey = n
    }

    public KeyGen(){
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
        System.out.println("p " + this.p);
        System.out.println("q " + this.q);
        System.out.println("n " + this.n);
        System.out.println("phi " + this.phi);
        System.out.println("random prime " + this.randomPrime);
        System.out.println("inverse " + this.inverse);


    }
    public BigInteger getP(){
        return this.p;
    }

    public BigInteger getQ(){
        return this.q;
    }
    // returns totient of a prime number otherwise returns -1 meaning p isn't prime
    public BigInteger totient(BigInteger prime) {
        if(prime.isProbablePrime(100) ) {
            return prime.subtract(BigInteger.ONE);
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


    // Pick e to be a random prime between 1 and ø(n), such that gcd(e, ø(n)) = . e should be similar in (bit) length to p and q, but does not have to be the same length.
// Calculate  d = e^-1 mod ø(n) :

    // In BigInteger the method used for this purpose is
     // public BigInteger modInverse(BigInteger m)

    public void pubkey(){
        // e and n should go in here

        try {
            FileOutputStream fos = new FileOutputStream("pubkey.rsa");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.randomPrime);
            oos.writeObject(this.n);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pubkey.rsa"));
            //System.out.println("works" + (BigInteger) ois.readObject());
            System.out.println("random Prime " + ois.readObject());
            System.out.println("n " + ois.readObject());
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
            System.out.println("inverse " + ois.readObject());
            System.out.println("n " + ois.readObject());


        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

/*
When you execute this program, it should generate new public and private keys for your RSA cryptosystem,
where p, q and e as defined above are all 512-bit integers and n  should be ~1024 bits. Your program should output all
three values e, d and n to the console as it generates them.  The values e and n should also be saved to a file called
"pubkey.rsa" and the values d and n should be saved to a file "privkey.rsa".  To allow for nice access of these files,
you MUST output and input these keys to and from the files using a Java ObjectOutputStream and ObjectInputStream.
     */


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
