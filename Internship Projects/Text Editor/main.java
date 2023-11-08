import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

import java.io.*;
import java.awt.event.*;
import javax.swing.text.*;




public class main extends JFrame implements ActionListener{

    JTextArea textArea = new JTextArea();
    JFrame window;
    int window_size_x = 1000;
    int window_size_y = 1000;
    
    public static void main(String[] args) {
        main m = new main();
    }

    main(){
        window = new JFrame("Text Editor");
	
	try{
		UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        MetalLookAndFeel.setCurrentTheme(new OceanTheme());
	}catch(Exception e){
        System.out.println(e);
    }

    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem item1 = new JMenuItem("New");
    JMenuItem item2 = new JMenuItem("Open");
    JMenuItem item3 = new JMenuItem("Save");
    JMenuItem item4 = new JMenuItem("Print");
    item1.addActionListener(this);
    item2.addActionListener(this);
    item3.addActionListener(this);
    item4.addActionListener(this);
    fileMenu.add(item1);
    fileMenu.add(item2);
    fileMenu.add(item3);
    fileMenu.add(item4);

    JMenu editMenu = new JMenu("Edit");
    JMenuItem item5 = new JMenuItem("Cut");
    JMenuItem item6 = new JMenuItem("Copy");
    JMenuItem item7 = new JMenuItem("Paste");
    item5.addActionListener(this);
    item6.addActionListener(this);
    item7.addActionListener(this);
    editMenu.add(item5);
    editMenu.add(item6);
    editMenu.add(item7);

    JMenu exitMenu = new JMenu("Exit");

    menuBar.add(fileMenu);
    menuBar.add(editMenu);
    menuBar.add(exitMenu);

    window.setJMenuBar(menuBar);
    window.add(textArea);
    window.setSize(window_size_x, window_size_y);
    textArea.setSize(window_size_x, window_size_y);
    window.setLayout(null);
    window.setVisible(true);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "Cut"){
            textArea.cut();
        }
        else if(e.getActionCommand() == "Copy"){
            textArea.copy();
        }
        else if(e.getActionCommand() == "Paste"){
            textArea.paste();
        }
        else if(e.getActionCommand() == "New"){
            textArea.setText("");
        }
        else if(e.getActionCommand() == "Exit"){
            window.setVisible(false);
        }
        else if(e.getActionCommand() == "Open"){
            JFileChooser fileChooser = new JFileChooser("C:");
            int fileChooserDialogBox = fileChooser.showOpenDialog(null);

            if (fileChooserDialogBox == JFileChooser.APPROVE_OPTION){
                File f1 = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try{
                    String s1 = "", stringList = "";
                    FileReader fr = new FileReader(f1);
                    BufferedReader br = new BufferedReader(fr);
                    stringList = br.readLine();

                    while((stringList = br.readLine()) != null){
                        stringList = stringList + "/n" + s1;

                    }
                    textArea.setText(stringList);
                }catch(Exception evt){
                    JOptionPane.showMessageDialog(fileChooser, evt.getMessage());
                }
                
            }
            else{
                JOptionPane.showMessageDialog(fileChooser, "User has cancelled the operation");
            }
        }
        else if(e.getActionCommand() == "Save"){
            JFileChooser fileChooser = new JFileChooser("C:");
            int fileChooserDialogBox = fileChooser.showOpenDialog(null);

            if (fileChooserDialogBox == JFileChooser.APPROVE_OPTION){
                File f1 = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try{
                    FileWriter wr = new FileWriter(f1, false);
                    BufferedWriter bw = new BufferedWriter(wr);
                    bw.write(textArea.getText());
                    bw.flush();
                    bw.close();
                }catch(Exception evt){
                    JOptionPane.showMessageDialog(fileChooser, "User has cancelled the operation");
                }
            
        }
    }
    else if(e.getActionCommand() == "Print"){
        try{
            textArea.print();
        }catch(Exception evt){
            
        }
    }
}
}