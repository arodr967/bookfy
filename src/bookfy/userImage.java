/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
/**
 *
 * @author Leo
 */




public class userImage extends JFrame{
    JButton button ;
    JButton button2;
    JLabel label;
    JTextArea area;
    String s;
     
    public userImage(){
   
    super("Upload user Image");
    
    button = new JButton("UPLOAD");
    button.setBounds(320,300,100,40);
    
    button2 = new JButton("BROWSE");
    button2.setBounds(155, 300, 100, 40);
    
    label = new JLabel();
    label.setBounds(10,10,670,250);   
   
  
    
    button2.addActionListener(new ActionListener(){
        @Override
     public void actionPerformed(ActionEvent e){
         JFileChooser fileChooser = new JFileChooser();
         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","jpeg","gif","png");
         fileChooser.addChoosableFileFilter(filter);
         int result = fileChooser.showSaveDialog(null);
         if(result == JFileChooser.APPROVE_OPTION){
             File selectedFile = fileChooser.getSelectedFile();
             String path = selectedFile.getAbsolutePath();
             label.setIcon(ResizeImage(path));
             s = path;
              }
         else if(result == JFileChooser.CANCEL_OPTION){
             System.out.println("No image choosen");
         }
     }
    });

    //button to insert image and some data into mysql database
    button.addActionListener(new ActionListener(){
    
       @Override
       public void actionPerformed(ActionEvent e){
           try{
               User user = Bookfy.getUser();
               Connection con = DriverManager.getConnection("jdbc:mysql://76.111.209.57:3306/bookfy?","bookfy","cen4010");
               PreparedStatement ps = con.prepareStatement("UPDATE `bookfy`.`user` SET `userImage`=? WHERE `UserID`='"+ user.getUserName() +"';");
               InputStream is = new FileInputStream(new File(s));
//               ps.setString(3, area.getText());
               ps.setBlob(1,is);
               ps.executeUpdate();
               JOptionPane.showMessageDialog(null, "Image uploaded!");
           }catch(Exception ex){
               ex.printStackTrace();
           }
       }
    });

    add(label);
    add(button);
    add(button2);
    setLayout(null);
    setSize(600,400);
    setVisible(true);
    }
    
    //Methode To Resize The ImageIcon
    public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }
}

    

