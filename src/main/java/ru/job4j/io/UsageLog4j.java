package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte btn = 8;
        short srt = 13;
        int age = 31;
        long length = 999888779999887899L;
        float sqrt = 17.25F;
        double rad = 3.15;
        boolean state = true;
        char name = 'h';
        LOG.debug("User info name : {}, age : {}, btn : {}, srt : {}, length : {}, sqrt : {}, rad : {}, state : {}",
                name, age, btn, srt, length, sqrt, rad, state);
    }
}
