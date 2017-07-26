/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import java.awt.Dimension;
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
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author Leo
 */

public class userImage extends JFrame {

    JButton button;
    JButton button2;
    JLabel label;
    JTextArea area;
    String s;
    double imageSizeMB, imageSizeKB;

    public userImage() {

        super("Upload user Image");

        button = new JButton("UPLOAD");
        button.setBounds(170, 115, 100, 40);

        button2 = new JButton("BROWSE");
        button2.setBounds(50, 115, 100, 40);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "jpeg", "gif", "png");
                fileChooser.addChoosableFileFilter(filter);
                int result = fileChooser.showSaveDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    s = path;
                } else if (result == JFileChooser.CANCEL_OPTION) {
                    System.out.println("No image choosen");
                }
            }
        });

        //button to insert image and some data into mysql database
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    User user = Bookfy.getUser();

                    Connection con = DriverManager.getConnection("jdbc:mysql://76.111.209.57:3306/bookfy?", "bookfy", "cen4010");
                    PreparedStatement ps = con.prepareStatement("UPDATE `bookfy`.`user` SET `userImage`=? WHERE `UserID`='" + user.getUserName() + "';");
                    //sql statement to upload the image based on the currently logged in user
                    if (s == null) //check to see if user selected a valid path
                    {

                        JOptionPane.showMessageDialog(null, "No Image selected");
                        //alerts user that no path was selecet with an image.

                    } else {
                        InputStream is = new FileInputStream(new File(s));
                        
                        imageSizeMB = imageSize(is); //calls the method to calculate image size
                        //System.out.println("this should be the image size:  " + "MB:" + imageSizeMB);
                        ps.setBlob(1, is);
                        if (imageSizeMB < 4.3) //check to see if image is less than the max allowed in longBLOB (4.3MB)
                        {
                            ps.executeUpdate();//executes the update query
                            JOptionPane.showMessageDialog(null, "Image uploaded!\n" + "Image Size: " + String.format("%.02f", imageSizeMB) + " MB");
                            //alerts user that the image has been uploaded
                        } else {
                            JOptionPane.showMessageDialog(null, "Image is to large, please select a different image that is less than 4.3MB.");
                            //alerts user that the image is to large.
                        }
                        
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //add(label);
        add(button);
        add(button2);
        setLayout(null);
        setSize(375, 250);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //method to calculate the image size
    public double imageSize(InputStream img) {
        double imgSizeMB, imgSizeKB;
        double bytes = 0;
        try {
            bytes = img.available();
        } catch (IOException ex) {
            Logger.getLogger(userImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        double kilobytes = (bytes / 1024);
        double megabytes = (kilobytes / 1024);
        imgSizeMB = megabytes;

        return imgSizeMB;

    }

}
