import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Back {
    JFrame backFrame;
    Back(Connection con){
        new Database().closeConnection(con);
        // HALF FRAME
        backFrame=new JFrame();
        backFrame.setUndecorated(true);
        backFrame.setLayout(null);
        backFrame.getContentPane().setBackground(new Color(60,91,111));
        backFrame.setSize(510, 700);
        backFrame.setShape(new RoundRectangle2D.Double(0, 0, 510, 700, 20, 30));
        backFrame.setLocation(600, 50);

        ImageIcon getSpiralImage = new ImageIcon(ClassLoader.getSystemResource("Image/backSpiral.png"));
        Image scaleSpiralImage = getSpiralImage.getImage().getScaledInstance(270,700, Image.SCALE_DEFAULT);
        ImageIcon spiralImage = new ImageIcon(scaleSpiralImage);
        JLabel spiralLabel = new JLabel(spiralImage);
        spiralLabel.setBounds(240,0,270,700);
        backFrame.add(spiralLabel);

        ImageIcon getMainImag = new ImageIcon(ClassLoader.getSystemResource("Image/backPage.png"));
        Image scaleMainImag = getMainImag.getImage().getScaledInstance(510,700, Image.SCALE_DEFAULT);
        ImageIcon mainImag = new ImageIcon(scaleMainImag);
        JLabel mainlLabe = new JLabel(mainImag);
        mainlLabe.setBounds(0,0,510,700);
        backFrame.add(mainlLabe);

        closing();
        backFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backFrame.setVisible(true);
       
    }
    public void closing() {
        new Thread(() -> {
            try {

                Thread.sleep(2000);
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        
    }
}
