package src.rsa;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.*;

public class DigitalSignature{

    private Byte [] byteArray;



    DigitalSignature(){
        try {
            MessageDigest MD = MessageDigest.getInstance("MD5");
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt"));
                
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        }
        catch(NoSuchAlgorithmException x){
            System.err.println("Broken");
        }
    }







}
