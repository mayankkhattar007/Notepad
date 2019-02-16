package com.mak.apps;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class eventsHandlerClass implements ActionListener,DocumentListener {

    private startNotepad obj;
    eventsHandlerClass(startNotepad obj1)
    {
        this.obj = obj1;
    }
    private void removeHighlights(JTextArea epane){
        Highlighter high = epane.getHighlighter();
        Highlighter.Highlight[] highs = high.getHighlights();
        for (Highlighter.Highlight high1 : highs) {
            high.removeHighlight(high1);
        }
    }
    private Highlighter.HighlightPainter hp =new MyHighlightPainter(Color.red);
    private void hightlighttext(JTextArea epane, String item)
    {
        try {
            Highlighter high = epane.getHighlighter();
            Document doc = epane.getDocument();
            String text = doc.getText(0,doc.getLength());
            int pos =0;
            while((pos=text.toUpperCase().indexOf(item.toUpperCase(),pos))>=0){
                high.addHighlight(pos, pos+item.length(), hp);
                pos+=item.length();
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== obj.i1)
        {
            // New file
            if (obj.textArea.getText().equals(""))
            {
                discardCurrentBuffer();
            }
            if(!obj.textArea.getText().equals(""))
            {
                int a = JOptionPane.showConfirmDialog(obj.cp,"Do you want to save the current file?","Save current file",JOptionPane.YES_NO_CANCEL_OPTION);
                if(a == JOptionPane.YES_OPTION)
                {
                    SaveCurrentBuffer(obj.textArea.getText());
                }
                if(a== JOptionPane.NO_OPTION)
                {
                    discardCurrentBuffer();
                }
                if(a==JOptionPane.CANCEL_OPTION)
                {
                    return;
                }
            }
        }
        if(e.getSource()== obj.i2)
        {
            // Open File
            if(obj.textArea.getText().equals(""))
            {
                JFileChooser jfo = new JFileChooser();
                int i = jfo.showOpenDialog(obj.cp);
                if(i == JFileChooser.APPROVE_OPTION)
                {
                    File f = jfo.getSelectedFile();
                    String filepath= f.getPath();
                    String filename = f.getName();
                    obj.setTitle(filename);
                    obj.textArea.setText("");
                    try {
                        BufferedReader br=new BufferedReader(new FileReader(filepath));
                        String s1;
                        StringBuilder s2 = new StringBuilder();
                        while((s1=br.readLine())!=null){
                            s2.append(s1).append("\n");
                        }
                        obj.textArea.setText(s2.toString());
                        br.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                else if(i == JFileChooser.CANCEL_OPTION){
                    jfo.remove(jfo);
                }
            }
            else {
                int a = JOptionPane.showConfirmDialog(obj.cp,"Do you want to save the current file?","Save current file",JOptionPane.YES_NO_CANCEL_OPTION);
                if(a == JOptionPane.YES_OPTION)
                {
                    SaveCurrentBuffer(obj.textArea.getText());
                }
                if(a == JOptionPane.NO_OPTION)
                {
                    obj.textArea.setText("");
                    JFileChooser jfo = new JFileChooser();
                    int i = jfo.showOpenDialog(obj.cp);
                    if(i == JFileChooser.APPROVE_OPTION)
                    {
                        File f = jfo.getSelectedFile();
                        String filepath= f.getPath();
                        String filename = f.getName();
                        obj.setTitle(filename+":: Notepad++");
                        obj.textArea.setText("");
                        try {
                            BufferedReader br=new BufferedReader(new FileReader(filepath));
                            String s1;
                            StringBuilder s2 = new StringBuilder();
                            while((s1=br.readLine())!=null){
                                s2.append(s1).append("\n");
                            }
                            obj.textArea.setText(s2.toString());
                            br.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else if(i == JFileChooser.CANCEL_OPTION){
                        jfo.remove(jfo);
                    }
                }
                if(a == JOptionPane.CANCEL_OPTION)
                {
                    return;
                }
            }
        }
        if(e.getSource()== obj.i3 || e.getSource()== obj.i4)
        {
            //Save file
            //Save as file
            SaveCurrentBuffer(obj.textArea.getText());
        }
        if(e.getSource()== obj.i5)
        {
            System.exit(0);
        }
        if(e.getSource()== obj.i6)
        {
            // Cut the text and replace it by null and transfer it to clipboard by making it transferable
            String text = obj.textArea.getSelectedText();
            obj.textArea.replaceSelection("");
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable tText = new StringSelection(text);
            clip.setContents(tText, null);
        }
        if(e.getSource()== obj.i7)
        {
            // Copy the text and transfer it to clipboard and make it transferable
            String text = obj.textArea.getSelectedText();
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable tText = new StringSelection(text);
            clip.setContents(tText, null);
        }
        if(e.getSource()== obj.i8)
        {
            // get system clipboard and gather its contents in a transferable obj , check the data flavor of string and typecast it into string then set it
            String ret="";
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable clipTf = clip.getContents(null);
            if (clipTf != null) {

                if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    try {
                        ret = (String) clipTf
                                .getTransferData(DataFlavor.stringFlavor);
                    } catch (UnsupportedFlavorException | IOException ie) {
                        ie.printStackTrace();
                    }
                }
            }
            int g =obj.textArea.getCaretPosition();
            String temp = obj.textArea.getText();
            temp = temp.substring(0,g) + ret + temp.substring(g,temp.length());
            obj.textArea.setText(temp);
            obj.textArea.setCaretPosition(temp.lastIndexOf(ret));
        }
        if(e.getSource()== obj.i9)
        {
            //Delete by replacing selected text by ""
            try {
                obj.textArea.setText(obj.textArea.getText().replace(obj.textArea.getSelectedText(),""));
            }
            catch (Exception ignored)
            { }
        }
        if(e.getSource()== obj.i10)
        {
            // Find text
            String find = "";
            try {
                find = JOptionPane.showInputDialog(obj.cp, "Enter keyword to search:", "Find", JOptionPane.QUESTION_MESSAGE);
            }catch (Exception ignored) {}
            this.removeHighlights(obj.textArea);
            this.hightlighttext(obj.textArea,find);
            obj.textArea.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    eventsHandlerClass.this.removeHighlights(obj.textArea);
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    //eventsHandlerClass.this.removeHighlights(obj.textArea);
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    //eventsHandlerClass.this.removeHighlights(obj.textArea);
                }
            });
        }
        if(e.getSource()== obj.i11)
        {
            //Replace
            String key = JOptionPane.showInputDialog(obj.cp,"Enter word to replace","Replace",JOptionPane.QUESTION_MESSAGE);
            String replaceby = JOptionPane.showInputDialog(obj.cp,"Enter the replacement","Replace By",JOptionPane.QUESTION_MESSAGE);
            String s1 = obj.textArea.getText();
            String replaceString=s1.replaceAll(key,replaceby);
            obj.textArea.setText(replaceString);
        }
        if(e.getSource()== obj.i12)
        {
            //Select All
            obj.textArea.selectAll();
        }
        if(e.getSource()== obj.i13)
        {
            // Time/Date
            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String ff = sdf.format(date);
            String epane_text = obj.textArea.getText();
            epane_text = epane_text + " " +ff;
            obj.textArea.setText(epane_text);
        }
        if(e.getSource()==obj.i15)
        {
            // Word Wrap
            boolean b = obj.i15.getState();
            if(b) obj.textArea.setLineWrap(true);
            if(!b)
                obj.textArea.setLineWrap(false);
        }
        if(e.getSource() == obj.i17)
        {
            // Status bar
            if(obj.i17.getState())
            {
                obj.statusbar.setText("Statusbar Enabled");
            }
            if(!obj.i17.getState())
            {
                obj.statusbar.setText("Statusbar Disabled");
            }
        }
        if(e.getSource()==obj.i18)
        {
            //Help menu
            JOptionPane.showMessageDialog(obj.cp,"If you are trying to get help in a notepad, you probably need some medical help :) ");
        }
        if(e.getSource()==obj.i19)
        {
            // About
            JOptionPane.showMessageDialog(obj.cp,"Text-Pad v1.0 \nCreated by Mayank Khattar");
        }
        if(e.getSource()== obj.i20)
        {
            // Load Java File
        }
        if(e.getSource()== obj.i21)
        {
            // Load HTML File
        }
    }

    private void discardCurrentBuffer() {
        obj.textArea.setText("");
        obj.setTitle("Untitled :: Notepad++");
    }

    private void SaveCurrentBuffer(String text) {
        JFileChooser jfo = new JFileChooser();
        int temp = jfo.showSaveDialog(obj.cp);
        if(temp == JFileChooser.APPROVE_OPTION)
        {
            try {
                FileOutputStream fout=new FileOutputStream(jfo.getSelectedFile());
                byte b[]=text.getBytes();
                fout.write(b);
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insertUpdate(DocumentEvent de) {
        if(obj.i17.getState()){
            String s = obj.textArea.getText();
            String s1 = docheck(s);
            obj.statusbar.setText(s1);
        }
    }

    private String docheck(String s) {
        int words = countwords(s);
        int alpha = countalphabets(s);
        return "Words :" + words + " Alphabets: "+ alpha;
    }

    private int countwords(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        String[] words = s.split("\\s+");
        return words.length;
    }

    private int countalphabets(String s) {
        int count =0;
        if (s == null || s.isEmpty()) {
            return 0;
        }

        String[] words = s.split("\\s+");
        for (String word : words) {
            count = count + word.length();
        }
        return count;

    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        if(obj.i17.getState()){
        String s = obj.textArea.getText();
        String s1 = docheck(s);
        obj.statusbar.setText(s1);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent de) {
        if(obj.i17.getState()){
            String s = obj.textArea.getText();
            String s1 = docheck(s);
            obj.statusbar.setText(s1);
        }
    }
}
class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter{
    MyHighlightPainter(Color color)
    {
        super(color);
    }
}
