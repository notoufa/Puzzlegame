package com.hust.test;

import com.hust.tool.CodeTool;
import org.junit.Assert;
import org.junit.Test;

public class TestCodeTool {
    @Test
    public void testgetCode(){
        boolean flag=true;
        String code = CodeTool.getcode();
        if(code.length()!=5) flag=false;
        for (int i = 0; i < 5; i++) {
            char c=code.charAt(i);
            if(!((c>='0'&&c<='9')||(c>='a'&&c<='z')||(c>='A'||c<='Z'))) {flag=false;break;}
        }
        Assert.assertArrayEquals("验证码格式有误", new boolean[]{true}, new boolean[]{flag});
    }
}
