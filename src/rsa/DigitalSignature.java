package src.rsa;

import java.io.*;
import java.security.*;

public class DigitalSignature{

    private Byte [] byteArray;



    DigitalSignature(){
        try {
            MessageDigest MD = MessageDigest.getInstance("MD5");
            try {

/*                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt"));
                byte array = ois.readByte();
                System.out.println(array);*/

                FileReader fileReader = new FileReader("test.txt");
                BufferedReader br = new BufferedReader(fileReader);
                System.out.println(br.readLine());
                System.out.println(br.readLine());
                System.out.println(br.readLine());
                System.out.println(br.readLine());
                System.out.println(br.readLine());
                /*while(br.readLine()!=null){
                    System.out.println(br.readLine());
                }*/


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
