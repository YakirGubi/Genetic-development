package com.company;

import javax.swing.*;
import java.awt.*;

public class Wall extends JLabel {

    Wall(int x , int y , int w , int h){
        this.setBounds(x,y,w,h);
        this.setBackground(Color.RED);
        this.setOpaque(true);
        this.setVisible(true);
    }
}
