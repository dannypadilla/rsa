package src.rsa;

import java.io.*;
import java.security.*;

public class DigitalSignature{

    private Byte [] byteArray;



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

                //array of messageDigests and set them all to MD5
                MessageDigest [] md = new MessageDigest[5];
                for(int i = 0; i < 5; i++){
                    md[i] = MessageDigest.getInstance("MD5");
                }

                //byte of strings
                byte[][] byteArray = new byte[][];
                for(int i = 0; i < 5; i++){
                    byteArray[i] = strings[i].getBytes();
                }

                //update
                for(int i = 0; i < 5; i++){
                    md[i].update(byteArray[i]);
                }

                byte[][] digest = new byte[][];
                for(int i = 0; i < 5; i++){
                    digest[i] = md[i].digest();
                }




                //it works
                for(int i = 0; i < 5; i++){
                    System.out.println(strings[i]);
                }
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
