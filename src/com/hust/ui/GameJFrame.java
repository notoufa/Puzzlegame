package com.hust.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int[][] data = new int[4][4];
    int x, y;
    int count = 0;
    String path = "image\\animal\\animal8\\";
    int win[][] = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    JMenuItem regameItem = new JMenuItem("重新游戏");
    JMenuItem relogItem = new JMenuItem("重新登陆");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");
    JMenuItem beauty = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sports = new JMenuItem("运动");
    JMenuItem suggestions = new JMenuItem("意见");

    public GameJFrame() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("image/tubiao2.png"));
        initJFrame();

        initJMenuBar();
        //打乱图片顺序
        getMass();
        //初始化图片
        initImage();

        //让界面显示出来
        setVisible(true);
    }


    private void getMass() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int index = r.nextInt(arr.length);
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = arr[i];
        }
    }


    private void initImage() {
        //先清空
        getContentPane().removeAll();
        if (victory()) {
            JLabel vic = new JLabel(new ImageIcon("image\\win.png"));
            vic.setBounds(203, 283, 197, 73);
            getContentPane().add(vic);
        }
        JLabel countstep = new JLabel("步数" + count);
        countstep.setBounds(50, 30, 100, 20);
        getContentPane().add(countstep);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                JLabel jLabel = new JLabel(new ImageIcon(path + num + ".jpg"));
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                jLabel.setBorder(new BevelBorder(1));
                getContentPane().add(jLabel);

            }
        }
        //添加边框
        JLabel jLabel = new JLabel(new ImageIcon("image\\background.png"));
        jLabel.setBounds(40, 40, 508, 560);
        getContentPane().add(jLabel);
        //刷新
        getContentPane().repaint();
    }


    private void initJMenuBar() {
        //设置菜单栏
        JMenuBar jMenuBar = new JMenuBar();
        JMenu functionJMunu = new JMenu("功能");
        JMenu aboutJMunu = new JMenu("关于我们");
        JMenu changepic = new JMenu("更换图片");

        functionJMunu.add(changepic);
        functionJMunu.add(regameItem);
        functionJMunu.add(relogItem);
        functionJMunu.add(closeItem);
        changepic.add(beauty);
        changepic.add(animal);
        changepic.add(sports);
        aboutJMunu.add(accountItem);
        aboutJMunu.add(suggestions);
        jMenuBar.add(functionJMunu);
        jMenuBar.add(aboutJMunu);
        //绑定事件
        regameItem.addActionListener(this);
        relogItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        beauty.addActionListener(this);
        animal.addActionListener(this);
        sports.addActionListener(this);
        suggestions.addActionListener(this);

        setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面宽高
        setSize(603, 680);
        //设置标题
        setTitle("拼图KX限定版 v1.0");
        //设置界面置顶
        setAlwaysOnTop(true);
        //设置界面居中
        setLocationRelativeTo(null);
        //设置关闭模式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认位置
        setLayout(null);
        //设置键盘监听
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //x(88)查看原图
        if (code == 88) {
            getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            getContentPane().add(all);
            //添加边框
            JLabel jLabel = new JLabel(new ImageIcon("image\\background.png"));
            jLabel.setBounds(40, 40, 508, 560);
            getContentPane().add(jLabel);
            //刷新
            getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (victory())
            return;
        int code = e.getKeyCode();
        if (code == 37 && y != 3) {
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            count++;
            initImage();
            return;
        }
        if (code == 38 && x != 3) {
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            count++;
            initImage();
            return;
        }
        if (code == 39 && y != 0) {
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            count++;
            initImage();
            return;
        }
        if (code == 40 && x != 0) {
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            count++;
            initImage();
            return;
        }
        if (code == 88) {
            initImage();
        }
        //k(75)直接通关
        if (code == 75) {
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }
    }

    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j])
                    return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == regameItem) {
            System.out.println("重新游戏");
            count = 0;
            getMass();
            initImage();
        }
        if (source == relogItem) {
            System.out.println("重新登陆");
            setVisible(false);
            new LoginJFrame();
        }
        if (source == closeItem) {
            System.out.println("关闭游戏");
            System.exit(0);

        }
        if (source == accountItem) {
            System.out.println("关于我们");
            //创建弹窗
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("image\\img.png"));
            jLabel.setBounds(0, 0, 408, 403);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(500, 500);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setTitle("V我50，告诉你蟹堡王秘方");
            jDialog.setVisible(true);
        }
        Random r = new Random();
        if (source == suggestions) {
            new Text();
        }
        if (source == beauty) {
            int ram = r.nextInt(11) + 1;
            path = "image\\girl\\girl" + ram + "\\";
            count = 0;
            getMass();
            initImage();
        }
        if (source == animal) {
            int ram = r.nextInt(8) + 1;
            path = "image\\animal\\animal" + ram + "\\";
            count = 0;
            getMass();
            initImage();
        }
        if (source == sports) {
            int ram = r.nextInt(10) + 1;
            path = "image\\sport\\sport" + ram + "\\";
            count = 0;
            getMass();
            initImage();
        }
    }


}

