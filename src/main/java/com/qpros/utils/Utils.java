package com.qpros.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utils {
    public static String getCurrentDateTime(){
        return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    }
    public static int getRandomNumber(int randomItemIndex){
        Random random = new Random();
        return random.nextInt(randomItemIndex);
    }
}
