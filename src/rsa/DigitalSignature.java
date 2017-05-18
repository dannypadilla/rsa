package src.rsa;

import java.io.*;
import java.math.BigInteger;
import java.security.*;

public class DigitalSignature{

    private byte [] digest;
    private BigInteger sigMag;



    DigitalSignature(){
        try {
            MessageDigest MD = MessageDigest.getInstance("MD5");
            try {

                FileReader fileReader = new FileReader("test.txt");
                BufferedReader br = new BufferedReader(fileReader);
                String [] strings = new String[5];

                //read in everything and store to strings
                for(int i = 0; i < 5; i++){
                    strings[i] = br.readLine();
                }
                String temp = "";
                for(int i = 0; i < 5; i++){
                    temp = temp.concat(strings[i]);
                }

                //array of messageDigests and set them all to MD5

                MessageDigest md = MessageDigest.getInstance("MD5");

                //byte of strings
                byte[] byteArray = temp.getBytes();

                //update
                md.update(byteArray);

                //digest
                this.digest = md.digest();


            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        }
        catch(NoSuchAlgorithmException x){
            System.err.println("Broken");
        }
    }

    public BigInteger signMag(byte[]byteArray){
        BigInteger value = new BigInteger(1, byteArray);
        this.sigMag = value;
        System.out.println(value);
        return value;
    }

    public byte[] getBytes(){
        return this.digest;
    }

    //public


}
