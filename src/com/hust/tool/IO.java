package com.hust.tool;

import com.hust.obj.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class IO {
    public static PrintStream ps;
    public static PrintStream prs;
    public static PrintStream prs2;
    public static final Logger LOGGER = LoggerFactory.getLogger("APP.class");
    public static Map<String, User> allusers=new HashMap<>();
    public static User u=new User();
    static {
        try {
            prs = new PrintStream(new FileOutputStream("data/loginregister.txt", true));
            prs2 = new PrintStream(new FileOutputStream("src/User1.txt", true));
        } catch (Exception e) {
            //throw new RuntimeException(e);
            IO.LOGGER.error("异常："+e);
        }
    }
}
