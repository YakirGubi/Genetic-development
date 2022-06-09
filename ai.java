package com.company;

import javax.swing.*;
import java.awt.*;

public class ai extends JLabel {

    public static int moves = 6000;

    public int[] array = new int[moves];

    public int score = 0;

    public boolean stack = false;
    public boolean win = false;

    ai(){
        this.setBounds(100,450,5,5);
        this.setBackground(Color.white);
        this.setOpaque(true);
        this.setVisible(true);
    }
    public void move(int x){
        switch (x) {
            case 0 -> this.setLocation(this.getX(), this.getY() + 2);
            case 1 -> this.setLocation(this.getX(), this.getY() - 2);
            case 2 -> this.setLocation(this.getX() + 2, this.getY());
            case 3 -> this.setLocation(this.getX() - 2, this.getY());
        }
    }
    public void setScore(){
        if(this.getY() <= 451){
            score = this.getX() - (451 - this.getY())/2;
        }else {
            score = this.getX() - (this.getY() -451)/2;
        }
        if(stack){
            score -= 200;
        }
        if(win){
            score += moves - Frame.move * 10;
        }
    }
    public void setStack(Wall[] walls){
        for (Wall wall : walls) {
            if (this.getX() > wall.getX() - this.getWidth() && this.getX() < wall.getX() + wall.getWidth()
                    && this.getY() > wall.getY() - this.getHeight() && this.getY() < wall.getY() + wall.getHeight()) {
                this.stack = true;
            }
        }
    }
    public void setWin(JLabel End){
        if(this.getX() > End.getX() - this.getWidth() && this.getX() < End.getX()+End.getWidth()
                && this.getY() > End.getY() - this.getHeight() && this.getY() < End.getY()+End.getHeight()){
            this.win = true;
        }
    }
}