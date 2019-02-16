package com.mak.apps;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

class startNotepad extends JFrame {
    JTextArea textArea;
    Container cp;
    JMenuBar MB = new JMenuBar();
    JMenu fileMenu,editMenu,viewMenu,formatMenu,helpMenu,language;
    JMenuItem i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13,i18,i19,i21,i20;
    JCheckBoxMenuItem i15,i17;
    JLabel statusbar;
    startNotepad()
    {
        // Container obj
        cp = getContentPane();

        // event handler obj
        eventsHandlerClass handlerClass = new eventsHandlerClass(this);

        // JTextPane settings
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        cp.add(scrollPane, BorderLayout.CENTER);

        // Menu Bar and Menu Settings
        setJMenuBar(MB);
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        viewMenu = new JMenu("View");
        formatMenu = new JMenu("Format");
        helpMenu = new JMenu("Help");
        MB.add(fileMenu);MB.add(editMenu);MB.add(formatMenu);MB.add(viewMenu);MB.add(helpMenu);

        //Status Bar settings
        statusbar = new JLabel("Statusbar : Enabled");
        statusbar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        //File menu items
        i1 = new JMenuItem("New");
        i2 = new JMenuItem("Open");
        i3 = new JMenuItem("Save");
        i4 = new JMenuItem("Save as");
        i5 = new JMenuItem("Exit");
        fileMenu.add(i1);fileMenu.add(i2);fileMenu.add(i3);fileMenu.add(i4);fileMenu.addSeparator();fileMenu.add(i5);
        i1.addActionListener(handlerClass);
        i2.addActionListener(handlerClass);
        i3.addActionListener(handlerClass);
        i4.addActionListener(handlerClass);
        i5.addActionListener(handlerClass);


        //Edit menu
        i6 = new JMenuItem("Cut");
        i7 = new JMenuItem("Copy");
        i8 = new JMenuItem("Paste");
        i9 = new JMenuItem("Delete");
        i10 = new JMenuItem("Find");
        i11 = new JMenuItem("Replace");
        i12 = new JMenuItem("Select All");
        i13 = new JMenuItem("Time/Date");
        //i14 = new JMenuItem("Undo");
        //editMenu.add(i14);editMenu.addSeparator();
        editMenu.add(i6);editMenu.add(i7);editMenu.add(i8);editMenu.add(i9);
        editMenu.addSeparator();editMenu.add(i10);
        editMenu.add(i11);editMenu.addSeparator();editMenu.add(i12);editMenu.add(i13);
        i6.addActionListener(handlerClass);
        i7.addActionListener(handlerClass);
        i8.addActionListener(handlerClass);
        i9.addActionListener(handlerClass);
        i10.addActionListener(handlerClass);
        i11.addActionListener(handlerClass);
        i12.addActionListener(handlerClass);
        i13.addActionListener(handlerClass);
        //i14.addActionListener(handlerClass);


        //Format Menu
        i15 = new JCheckBoxMenuItem("Word Wrap");
        i20= new JMenuItem("Java");
        i21= new JMenuItem("HTML");
        language = new JMenu("Languages");
        language.add(i20);language.add(i21);
        formatMenu.add(i15);formatMenu.add(language);
        i15.addActionListener(handlerClass);
        i20.addActionListener(handlerClass);
        i21.addActionListener(handlerClass);

        //View Menu
        i17 =  new JCheckBoxMenuItem("Status Bar");
        i17.setState(true);
        viewMenu.add(i17);
        i17.addActionListener(handlerClass);
        textArea.getDocument().addDocumentListener(handlerClass);

        //Help Menu
        i18 = new JMenuItem("View Help");
        i19 = new JMenuItem("About Notepad");
        helpMenu.add(i18);helpMenu.addSeparator();helpMenu.add(i19);
        i18.addActionListener(handlerClass);
        i19.addActionListener(handlerClass);

        //Frame settings
        cp.add(statusbar, BorderLayout.SOUTH);
        setTitle("Untitled :: Notepad++");
        setVisible(true);
        setSize(800,600);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("res/iconnotepad.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
