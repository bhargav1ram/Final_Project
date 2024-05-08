import javax.swing.*;

import javafx.event.ActionEvent;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

 

public class Project_UI{

    private static JFrame mainFrame;
    private static JPanel currentPane;
    public static JPanel lastPane;

    public static void switchPanel(JPanel jp){
        if(currentPane!=null){
            mainFrame.remove(currentPane);
        }

        lastPane = currentPane;
        currentPane = jp;
        mainFrame.getContentPane().add(BorderLayout.CENTER,currentPane);
        mainFrame.revalidate();
        mainFrame.repaint();

    }

    

    public static void main(String[] args){
        mainFrame = new JFrame("Login Frame");
        
        mainFrame.setLayout(new BorderLayout());
        
        mainFrame.setSize(1920,1280);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        


        JPanel loginUIPane = new LoginUIPanel();
        currentPane = loginUIPane;
        

        mainFrame.getContentPane().add(BorderLayout.CENTER,currentPane);
        mainFrame.setVisible(true);

    }   
    
}


