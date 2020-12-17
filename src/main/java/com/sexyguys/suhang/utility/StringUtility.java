package com.sexyguys.suhang.utility;

import java.util.Random;
import java.util.Random;


// side-effect 가 발생하지 않는 pure utility method
public class StringUtility {

    public static String generateString(int length) {
        Random rand = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(3);
            switch (index) {
                case 0 -> sb.append((char) (rand.nextInt(26) + 97));
                case 1 -> sb.append((char) (rand.nextInt(26) + 65));
                case 2 -> sb.append(rand.nextInt(10));
            }
        }
        return sb.toString();
    }
}
