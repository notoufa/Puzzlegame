package com.hust.ui;

import com.hust.tool.CodeTool;
import com.hust.tool.IO;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetAddress;

public class Text extends JFrame implements MouseListener {
    private JPanel jp = new JPanel();
    private JTextArea jta = new JTextArea();
    JButton submit = new JButton();

    public Text() {
        initText();
        submit.setBounds(205, 260, 186, 77);
        submit.setIcon(new ImageIcon("image\\submit.png"));
        submit.setBorderPainted(false);
        //去除按钮的背景
        submit.setContentAreaFilled(false);
        //给登录按钮绑定鼠标事件
        submit.addMouseListener(this);
        jp.add(submit);
        this.setVisible(true);
    }

    private void initText() {
        jp.setLayout(null);
        jta.setBounds(20, 20, 340, 200);
        jp.add(jta);
        jta.setLineWrap(true);
        this.add(jp);
        this.setTitle("游戏意见");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(600, 320, 400, 380);
        setAlwaysOnTop(true);

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        CodeTool.showJDialog("意见提交成功");
        String s = jta.getText();
        IO.ps.println("用户"+IO.u.getUserName()+"提交意见：" + s);
        this.setVisible(false);
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
