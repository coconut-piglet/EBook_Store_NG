package se228.richard.ebookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.lang.reflect.Method;

@SpringBootApplication
public class EbookstoreApplication {

    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir") + "/target/classes/se228/richard/ebookstore/";
        EncodeClass encodeClass = new EncodeClass();
        String[] testArgs = new String[] {currentDir + "TestClass.class",currentDir + "TestClass.cryptclass","343"};
        encodeClass.main(testArgs);
        try {
            ClassLoader classLoader = new DecodeClassLoader(343);
            System.out.println("Main: create DecodeClassLoader successfully!");
            Class testClass = classLoader.loadClass("se228.richard.ebookstore.TestClass");
            System.out.println("Main: class loaded successfully!");
            //String[] newArgs = new String[] {"Hello world from Spring Boot main!"};
            //System.out.println("Main: construct args successfully!");
            Method method = testClass.getMethod("saysomething", String.class);
            System.out.println("Main: create method successfully!");
            Object obj = testClass.newInstance();
            method.invoke(obj, "finally, you succeeded in building custom class loader");
        }
        catch (Throwable e) {
            System.out.println("Main: error when loading class or invoking method!");
            System.out.println(e);
        }
        SpringApplication.run(EbookstoreApplication.class, args);
    }

}
