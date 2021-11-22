package algorithmtest;

import java.security.Security;
import java.util.Set;

/**
 * @author: RyuZUSUNC
 * @create: 2021-04-25 15:32
 **/

public class algorithmtest {
    public static void main(String[] args) {
        Set<String> messageDigest = Security.getAlgorithms("MessageDigest");
        messageDigest.forEach(System.out::println);
    }
}
