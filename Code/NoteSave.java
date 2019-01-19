package NoteSave;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class NoteSave extends JPanel implements ActionListener{
    TextArea area;
    public NoteSave(){
        JFrame f = new JFrame();
        
        area = new TextArea(null);
        area.setBounds(10, 30, 300, 300);
        
        JButton but = new JButton("Button");
        but.setBounds(30, 30, 30, 30);
        but.addActionListener(this);
        
        f.add(area,BorderLayout.CENTER);
        f.add(but,BorderLayout.PAGE_START);
        
        f.setSize(400, 400);
        f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);
    }
    
    public void copyFile() throws IOException{
        FileWriter fw = new FileWriter("src/testproj/userStr.txt");
        String str;
        str = area.getText();
        fw.write(str);
        fw.close();
    }
    
    public static void main(String[] args){
        new NoteSave();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            copyFile();
        } catch (IOException em){
        }
    }
  
}
