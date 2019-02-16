package com.mak.apps;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main extends JFrame implements PathInfo{

    // Creating File objects for creation of Notepad++ folders in C drive.
    // paths are are declared in the PathInfo.java
    private File MainFolder = new File(path1Main);
    private File ResFolder = new File(path5res);
    private File LogFolder = new File(path2Log);
    private File HTMLTemplate = new File(path3htmlScripts);
    private File JavaTemplate = new File(path4Java);
    private JProgressBar jb;
    private int i=0;
    private Main()
    {
        //Container settings
        Color blue = new Color(213, 178,255);
        Container c = getContentPane();
        c.setBackground(blue);
        c.setLayout(null);

        // WELCOME text Label
        JLabel lb = new JLabel("WELCOME");
        lb.setForeground(Color.RED);
        Font f = new Font("serif",Font.BOLD,20);
        lb.setFont(f);
        lb.setBounds(190,20,200,50);
        c.add(lb);

        // Progress Bar
        jb=new JProgressBar(0,2000);
        jb.setBounds(90,75,300,15);
        jb.setValue(0);
        jb.setStringPainted(true);
        c.add(jb);

        //Frame settings
        setTitle("Initializing Notepad");
        setResizable(false);
        setVisible(true);
        setBounds(400,200,500,200);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("res/iconnotepad.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void iterate(){
        /*
        * Iterates the progress bar at 30 millis speed.
        * Interrupted Exception is ignored in this case.
        * To increase or decrease the speed just change the sleep time or int VariableSum
        * */
        while(i<=2000){
            jb.setValue(i);
            int VariableSum =20;
            i=i+VariableSum;
            try {
                Thread.sleep(30);
            } catch (InterruptedException ignored) {
            }
        }
    }
    public static void main(String[] args) {
        // Creates Main class object and initializes the creation of folders for working of the Notepad.
        Main m = new Main();
        //iterate function call
        m.iterate();
        // if the directories already exists
        if(m.check_if_dir_exists())
        {
            // Destroying the Current class JFrame
            m.dispose();
            // Anonymous startNotepad object to initiate the Notepad.
            new startNotepad();
        }
        if(m.startInitializing().equals("Done"))
        {
            // Destroying the Current class JFrame
            m.dispose();
            // Anonymous startNotepad object to initiate the Notepad.
            new startNotepad();
        }
    }

    private boolean check_if_dir_exists() {
        // Checking if the folders exists
        boolean b1=false,b2=false,b3=false,b4=false,b5=false;
        if (MainFolder.exists()) {
            b1 = true;
        }
        if (ResFolder.exists()) {
            b2 = true;
        }
        if (LogFolder.exists()) {
            b3 = true;
        }
        if (JavaTemplate.exists()) {
            b4 = true;
        }
        if (HTMLTemplate.exists()) {
            b5 = true;
        }
        // returns true if exists
        return b1 && b2 && b3 && b4 && b5;
    }

    private String startInitializing() {
        // Creating Directories using .mkdirs() method.
        boolean successful1 = MainFolder.mkdirs();
        boolean successful2 = LogFolder.mkdirs();
        boolean successful3 = ResFolder.mkdirs();
        boolean successful4 = HTMLTemplate.mkdirs();
        boolean successful5 = JavaTemplate.mkdirs();

        // returns "done" if the the Directories area created , else "not".
        if(successful1 && successful2 && successful3 && successful4 && successful5)
        return "Done";
        return "not";
    }
}
