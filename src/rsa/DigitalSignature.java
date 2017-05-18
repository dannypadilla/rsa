package rsa.src.rsa;

import java.io.*;
import java.math.BigInteger;
import java.security.*;

public class DigitalSignature{

    private byte [] digest;
    private BigInteger sigMag;
    private BigInteger d, n;

    DigitalSignature(){
        try {
            MessageDigest MD = MessageDigest.getInstance("MD5");
            try {

                FileReader fileReader = new FileReader("/Users/dannypadilla/Workspace/Java/intelliJ/out/production/intelliJ/rsa/test.txt");
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

    public void setSignMag(byte[] byteArray) {
        BigInteger value = new BigInteger(1, byteArray);
        this.sigMag = value;
     }

     // digest before signing
    public byte[] getBytes(){
        return this.digest;
    }

    // signs the digest (fixed length of file for now ;) )
    public void initialSigning(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("privkey.rsa"));

            this.d = (BigInteger)ois.readObject();
            this.n = (BigInteger)ois.readObject();

            BigInteger answer = sigMag.modPow(d,n); // (signMag)^d mod n SIGNATURE

            System.out.println("Signature: " + answer); // signature
            FileReader temp = new FileReader("/Users/dannypadilla/Workspace/Java/intelliJ/out/production/intelliJ/rsa/test.txt");
            BufferedReader br = new BufferedReader(temp);

            FileOutputStream fos = new FileOutputStream("test.txt.signed");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(answer);
            for(int i = 0; i < 5; i++) {
                oos.writeObject(br.readLine());
            }

            oos.close();
            ois.close();
            fos.close();
            temp.close();
            br.close();

            //pw.close();

            // prints text from file
            ObjectInputStream iis = new ObjectInputStream(new FileInputStream("test.txt.signed"));
            System.out.print("Original message");
            System.out.println(iis.readObject());
            System.out.println(iis.readObject());
            System.out.println(iis.readObject());
            System.out.println(iis.readObject());
            System.out.println(iis.readObject());
            System.out.println(iis.readObject());
            iis.close();
        }
        catch(Exception x){
            x.printStackTrace();
        }

    }

    public void receiver(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt.signed"));
            BigInteger bi = (BigInteger)ois.readObject();
        }catch(Exception x){
            x.printStackTrace();
        }

    }

    


}
