package se228.richard.ebookstore;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DecodeClassLoader extends ClassLoader {

    public DecodeClassLoader(int k) {
        key = k;
    }

    protected Class findClass(String name) throws ClassNotFoundException {
        byte[] classByte = null;
        try {
            classByte = loadClassBytes(name);
        }
        catch (IOException e) {
            System.out.println("At DecodeClassLoader.loadClassByte");
            throw new ClassNotFoundException(name);
        }

        Class cl = defineClass(name, classByte, 0, classByte.length);
        if (cl == null) {
            System.out.println("At DecodeClassLoader.findClass");
            throw new ClassNotFoundException(name);
        }

        System.out.println("DecodeClassLoader: findClass job done!");
        return cl;
    }

    private byte[] loadClassBytes(String name) throws IOException {
        String cname = System.getProperty("user.dir") + "/target/classes/" + name.replace('.', '/') + ".cryptclass";
        FileInputStream in = null;
        in = new FileInputStream(cname);
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int ch;
            while ((ch = in.read()) != -1) {
                byte b = (byte)(ch - key);
                buffer.write(b);
            }
            in.close();
            System.out.println("DecodeClassLoader: loadClassByte job done!");
            return buffer.toByteArray();
        }
        finally {
            in.close();
        }
    }

    private int key;

}
