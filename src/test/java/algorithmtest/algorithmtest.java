package algorithmtest;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.util.Set;

/**
 * @author: RyuZUSUNC
 * @create: 2021-04-25 15:32
 **/

public class algorithmtest {
    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());
        Set<String> messageDigest = Security.getAlgorithms("Mac");
        for (String s : messageDigest) {
            if (s.contains("AES")) {
                System.out.println(s);
            }
        }
//        messageDigest.forEach(System.out::println);
    }
}
