package com.hust.ui;

import com.hust.obj.User;
import com.hust.tool.CodeTool;
import com.hust.tool.IO;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterJFrame extends JFrame implements MouseListener {
    JButton reset = new JButton();
    JButton register = new JButton();

    JTextField userName = new JTextField();
    JPasswordField password = new JPasswordField();
    JPasswordField password2 = new JPasswordField();

    public RegisterJFrame() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("image/tubiao2.png"));
        initFrame();
        initregist();
        setVisible(true);
    }

    private void initregist() {
        reset.setBounds(256, 310, 128, 47);
        reset.setIcon(new ImageIcon("image\\register\\重置按钮.png"));
        //去除按钮的边框
        reset.setBorderPainted(false);
        //去除按钮的背景
        reset.setContentAreaFilled(false);
        //给登录按钮绑定鼠标事件
        reset.addMouseListener(this);
        this.getContentPane().add(reset);

        //添加注册按钮
        register.setBounds(130, 310, 128, 47);
        register.setIcon(new ImageIcon("image\\register\\注册按钮.png"));
        //去除按钮的边框
        register.setBorderPainted(false);
        //去除按钮的背景
        register.setContentAreaFilled(false);
        //给注册按钮绑定鼠标事件
        register.addMouseListener(this);
        this.getContentPane().add(register);
        JLabel userLabel = new JLabel(new ImageIcon("image\\register\\注册用户名.png"));
        userLabel.setBounds(95, 135, 79, 17);
        getContentPane().add(userLabel);
        userName.setBounds(195, 133, 200, 30);
        this.getContentPane().add(userName);

        JLabel passwordLabel = new JLabel(new ImageIcon("image\\register\\注册密码.png"));
        passwordLabel.setBounds(105, 195, 64, 16);
        getContentPane().add(passwordLabel);
        password.setBounds(195, 193, 200, 30);
        this.getContentPane().add(password);

        JLabel passwordLabel2 = new JLabel(new ImageIcon("image\\register\\再次输入密码.png"));
        passwordLabel2.setBounds(95, 256, 96, 17);
        this.getContentPane().add(passwordLabel2);
        password2.setBounds(195, 254, 200, 30);
        this.getContentPane().add(password2);

        JLabel background = new JLabel(new ImageIcon("image\\register\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    private void initFrame() {
        setSize(488, 430);
        //设置标题
        setTitle("注册");
        //设置界面置顶
        setAlwaysOnTop(true);
        //设置界面居中
        setLocationRelativeTo(null);
        //设置关闭模式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == register) {
            String inputUserName = userName.getText();
            String inputPassword = password.getText();
            String inputPassword2 = password2.getText();
            if (inputUserName.length() == 0 || inputPassword.length() == 0 || inputPassword2.length() == 0) {
                CodeTool.showJDialog("用户名或密码不能为空");
            } else if ((!inputUserName.matches("[a-zA-Z0-9_-]{4,16}")) ||
                    (!inputPassword.matches("\\S*(?=\\S{6,})(?=\\S*[\\da-z])\\S*"))) {
                //用户名格式：4——16位字母或数字
                //密码格式：6位以上
                CodeTool.showJDialog("用户名格式有误");
            } else if (!inputPassword.equals(inputPassword2)) {
                CodeTool.showJDialog("两次密码输入不一致");
            } else if (containsUserName(inputUserName)) {
                CodeTool.showJDialog("用户名已被注册");
            } else {
                User u = new User(inputUserName, inputPassword);

                CodeTool.showJDialog("注册成功");

                try {
                    IO.ps.println("注册:用户" + inputUserName + "注册成功" );
                    IO.prs2.println(u);

                } catch (Exception ex) {
                    //throw new RuntimeException(ex);
                    IO.LOGGER.error("异常："+ex);
                }
                this.setVisible(false);
                //打开游戏的主界面
                //需要把当前登录的用户名传递给游戏界面
                new LoginJFrame();
            }
        } else if (e.getSource() == reset) {
            userName.setText("");
            password.setText("");
            password2.setText("");
        }
    }


    private boolean containsUserName(String username) {
        if(IO.allusers.containsKey(username))
        return true;
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
