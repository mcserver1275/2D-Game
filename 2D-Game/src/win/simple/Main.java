package win.simple;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends Game {
    private Image offScreenImage;

    public Main() {
        this.setTitle("Islands Test");
        this.setSize(world.SizeW, world.SizeH);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);

        new Thread(new updateThread()).start();
    }

    public static void main(String[] args) {
        Main main = new Main();
        GameEnable();       //加载游戏
        main.addKeyListener(new Control());     //监听玩家按键事件
    }

    @Override
    public void paint(Graphics graphics) {
        world.draw(graphics);
    }

    @Override
    public void update(Graphics graphics) {
        if(offScreenImage == null)
        offScreenImage = this.createImage(this.getWidth(), this.getHeight());

        Graphics gImage = offScreenImage.getGraphics();
        paint(gImage);
        graphics.drawImage(offScreenImage, 0, 0, null);
    }

    private class updateThread implements Runnable {
        @Override
        public void run() {
            while(true) {
                repaint();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

