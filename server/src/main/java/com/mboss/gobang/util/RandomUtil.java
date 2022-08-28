package com.mboss.gobang.util;

import java.util.Random;

public class RandomUtil {
    public static String random9Num(){
        Random random=new Random();
        int num9=random.nextInt(900000000)+100000000;
        return String.valueOf(num9);
    }
}
