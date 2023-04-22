package com.hust.tool;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class CodeTool {
    //得到验证码
    public static String getcode() {
        ArrayList<Character> list = new ArrayList<>();
        char[] code = new char[5];
        for (int i = 0; i < 26; i++) {
            list.add((char) ('a' + i));
            list.add((char) ('A' + i));
        }
        Random r = new Random();

        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(list.size());
            code[i] = list.get(index);
        }
        int index = r.nextInt(10);
        char tem = (char) ('0' + index);
        index = r.nextInt(5);
        code[4] = tem;
        char temp = code[index];
        code[index] = tem;
        code[4] = temp;
        return new String(code);
    }

    public static void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(400, 300);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 400, 300);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }
}