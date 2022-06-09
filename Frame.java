package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class Frame extends JFrame{

    public Random random = new Random();

    JPanel panel = new JPanel();

    JLabel End = new JLabel();
    JLabel Start = new JLabel();

    int num = 0;
    JLabel Generation = new JLabel("Generation "+num+"#");

    Wall[] walls = {new Wall(375,0,50,500),
                    new Wall(775,400,50,500),
                    new Wall(1225,0,50,500),
            //מיסגרת
                    new Wall(0,0,5,901),
                    new Wall(0,0,1601,5),
                    new Wall(0,896,1601,5),
                    new Wall(1596,0,5,901),};

    ai[] ais = new ai[1000];

    static int move = 0;
    double Mutation = 0.1;

    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if(move == ai.moves){

                move = 0;

                for (com.company.ai ai : ais) {
                    ai.setScore();
                }

                manager(ais);

                int dad;
                int mom;
                for(int i = ais.length/2 ; i < ais.length ; i++){
                    dad = random.nextInt(ais.length/2);
                    mom = random.nextInt(ais.length/2);
                    newArray(ais[dad].array,ais[mom].array,ais[i].array);
                }

                for(int i = 0 ; i < ai.moves*(ais.length/2)*Mutation ; i++){
                    mutation(ais[random.nextInt(ais.length/2)+(ais.length/2)].array);
                }

                for (com.company.ai ai : ais) {
                    ai.setLocation(100, 450);
                    ai.stack = false;
                    ai.win = false;
                }

                num++;
                Generation.setText("Generation "+num+"#");

            }else {
                for (com.company.ai ai : ais) {
                    ai.setStack(walls);
                    ai.setWin(End);
                    if (!ai.stack && !ai.win) {
                        ai.move(ai.array[move]);
                    }
                }
                move++;
            }
        }
    };


    Frame(){

        for(int i = 0 ; i < ais.length ; i++){
            ais[i] = new ai();
            newAi(ais[i]);
            panel.add(ais[i]);
        }

        panel.setBounds(0,0,1601,901);
        panel.setBackground(Color.black);
        panel.setLayout(null);

        End.setBackground(Color.GREEN);
        End.setBounds(1500,5,5,891);
        End.setOpaque(true);
        End.setVisible(true);

        Start.setBackground(Color.GREEN);
        Start.setBounds(100,5,5,891);
        Start.setOpaque(true);
        Start.setVisible(true);

        Generation.setFont(new Font("",Font.PLAIN,15));
        Generation.setForeground(Color.GREEN);
        Generation.setBackground(Color.black);
        Generation.setBounds(725,5,150,50);
        Generation.setOpaque(true);
        Generation.setVisible(true);

        panel.add(End);
        panel.add(Start);
        panel.add(Generation);
        for (Wall wall : walls) {
            panel.add(wall);
        }
        panel.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1616,938);
        this.setLocationRelativeTo(null);

        this.add(panel);

        this.setVisible(true);

        timer.scheduleAtFixedRate(task,0,1);
    }
    public void manager(ai[] ais){

        ArrayList<ai> ais1 = new ArrayList<>(Arrays.asList(ais));
        ArrayList<ai> ais2 = new ArrayList<>();

        int x;
        boolean flag;

        do {
            flag = false;
            for (int i = 0; i < ais1.size(); i++) {
                x = 0;
                for (int j = 0; j < ais1.size(); j++) {
                    if (ais1.get(i).score >= ais1.get(j).score) {
                        x++;
                    }
                    if (x == ais1.size()) {
                        ais2.add(ais1.get(i));
                        ais1.remove(i);
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
        }while(ais1.size() > 0);

        for (int i = 0; i < ais.length; i++) {
            ais[i] = ais2.get(i);
        }
    }
    public void newAi(ai ai){
        for(int i = 0 ; i < ai.moves ; i++) {
            ai.array[i] = random.nextInt(4);
        }
    }
    public void newArray(int[] dad , int[] mom , int[] san){

        for(int i = 0 ; i < ai.moves ; i++){
            if(i % 2 == 0){
                san[i] = dad[i];
            }else {
                san[i] = mom[i];
            }
        }
    }
    public void mutation(int[] x){

        x[random.nextInt(x.length)] = random.nextInt(4);
    }
}
