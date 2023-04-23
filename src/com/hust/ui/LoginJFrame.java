package com.hust.ui;

import com.hust.obj.User;
import com.hust.tool.CodeTool;
import com.hust.tool.IO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class LoginJFrame extends JFrame implements MouseListener {
    JButton login = new JButton();
    JButton register = new JButton();
    JLabel rightCode = new JLabel(CodeTool.getcode());
    JTextField userName = new JTextField();
    JPasswordField password = new JPasswordField();
    JTextField code = new JTextField();

    public LoginJFrame() {
        try {
            getAllUsers();
            //int a=10/0;
        } catch (Exception e) {
            //throw new RuntimeException(e);
            IO.LOGGER.error("异常：" + e);
        }
        setIconImage(Toolkit.getDefaultToolkit().getImage("image/tubiao2.png"));
        initJFrame();
        initlog();
        setVisible(true);
    }

    private void getAllUsers() throws Exception {
        User u=new User();
        String s;
        BufferedReader bf=new BufferedReader(new FileReader("src/User1.txt"));
        while ((s=bf.readLine()) != null) {
            String[] userinfoarr = s.split("&");
            String[] arr1 = userinfoarr[0].split("=");
            String[] arr2 = userinfoarr[1].split("=");
            u.setUserName(arr1[1]);
            u.setPassWord(arr2[1]);
            IO.allusers.put(u.getUserName(), u);
        }
    }

    private void initlog() {
        //按钮

        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        //去除按钮的边框
        login.setBorderPainted(false);
        //去除按钮的背景
        login.setContentAreaFilled(false);
        //给登录按钮绑定鼠标事件
        login.addMouseListener(this);
        this.getContentPane().add(login);

        //添加注册按钮
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        //去除按钮的边框
        register.setBorderPainted(false);
        //去除按钮的背景
        register.setContentAreaFilled(false);
        //给注册按钮绑定鼠标事件
        register.addMouseListener(this);
        this.getContentPane().add(register);
        JLabel userLabel = new JLabel(new ImageIcon("image\\login\\用户名.png"));
        userLabel.setBounds(116, 135, 47, 17);
        getContentPane().add(userLabel);

        userName.setBounds(195, 134, 200, 30);
        this.getContentPane().add(userName);
        JLabel passwordLabel = new JLabel(new ImageIcon("image\\login\\密码.png"));
        passwordLabel.setBounds(130, 195, 32, 16);
        getContentPane().add(passwordLabel);

        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);
        JLabel codeText = new JLabel(new ImageIcon("image\\login\\验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);
        //验证码

        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);

        rightCode.setBounds(300, 256, 50, 30);
        rightCode.addMouseListener(this);
        this.getContentPane().add(rightCode);
        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);

    }

    private void initJFrame() {
        setSize(488, 430);
        //设置标题
        setTitle("登录");
        //设置界面置顶
        setAlwaysOnTop(true);
        //设置界面居中
        setLocationRelativeTo(null);
        //设置关闭模式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);


    }

    public void showJDialog(String content) {
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == login) {
            String inputUserName = userName.getText();
            String inputPassword = password.getText();
            String inputCode = code.getText();
            if (inputCode.length() == 0) {
                showJDialog("猪头，快输验证码，别想偷懒呦！");
            } else if (!inputCode.equalsIgnoreCase(rightCode.getText())) {
                showJDialog("笨蛋，验证码不对");
            } else if (inputUserName.length() == 0 || inputPassword.length() == 0) {
                showJDialog("没账号也想玩？？？");
            } else if (!IO.allusers.containsKey(inputUserName)) {
                showJDialog("假号？！");
            } else {
                IO.u = IO.allusers.get(inputUserName);
                if (!inputPassword.equals(IO.u.getPassWord())) {
                    showJDialog("笨啊你，密码都能忘！");
                } else {
                    IO.ps.println("登录:用户" + inputUserName + "登录成功");
                    showJDialog("登录成功");

                    this.setVisible(false);
                    //打开游戏的主界面
                    //需要把当前登录的用户名传递给游戏界面
                    new GameJFrame();
                }
            }

        } else if (e.getSource() == rightCode) {
            //获取一个新的验证码
            String code = CodeTool.getcode();
            rightCode.setText(code);
        } else if (e.getSource() == register) {
            this.setVisible(false);
            new RegisterJFrame();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image\\login\\登录按下.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
