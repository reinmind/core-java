package org.zx.simpleframe;

import org.zx.simpleframe.frame.SimpleFrame;

import javax.swing.*;
import java.awt.*;

/**
 * @author xiang.zhang
 */
public class SimpleFrameTest {

    private static JTextArea logArea;
    private static JTextArea msgArea;
    private static JButton echoButton;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public static void main(String[] args) {
                    SimpleFrame frame = new SimpleFrame();
                    frame.setVisible(true);
        int screenWidth = 800;
        int screenHeight = 600;
        frame.setLayout(null);
        frame.setBounds(screenWidth/4,screenHeight/4,screenWidth/2,   screenHeight/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


//        JPanel panel = new JPanel();
        echoButton = new JButton("echo");
        echoButton.setBounds(50,100,95,30);

        //logArea  = new JTextArea("chat log",screenWidth/8,screenHeight/8);
        //msgArea = new JTextArea("write something",screenWidth/8,screenHeight/8);


        //panel.add(logArea);
        //panel.add(msgArea);
//        panel.add(echoButton);

        frame.getContentPane().add(echoButton);
    }

}


