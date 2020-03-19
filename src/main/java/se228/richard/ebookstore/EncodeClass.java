package se228.richard.ebookstore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EncodeClass {

    public EncodeClass() {
    }

    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Current Directory: " + System.getProperty("user.dir"));
            System.out.println("Encode Class Usage: java EncodeClass input_file output_file encode_key");
            return;
        }
        try {
            System.out.println("Start Encoding...");
            EncodeClassPermission encodeClassPermission = new EncodeClassPermission(args[0], "allow");
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                System.out.println("Detrect security manager, checking permission...");
                securityManager.checkPermission(encodeClassPermission);
            }
            else {
                System.out.println("No security manager detected, skipping permission check...");
            }
            FileInputStream in = new FileInputStream(args[0]);
            FileOutputStream out = new FileOutputStream(args[1]);
            int key = Integer.parseInt(args[2]);
            int ch;
            while ((ch = in.read()) != -1) {
                byte c = (byte) (ch + key);
                out.write(c);
            }
            in.close();
            out.close();
            System.out.println("Encoding finished");
        } catch (IOException e) {
            System.out.println("At EncodeClass");
            e.printStackTrace();
        }
    }

}
