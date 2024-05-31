import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;

public class Options implements ActionListener,MouseListener{
    Connection con=new Database().getConnection();
    JFrame optionsFrame;
    JPanel leftPanel, rightPanel;
    JLabel revisitQuotLabel, newPageQuotLabel,closeLabel;
    JButton writeButton, readButton;
    Options(){
        // ADDING FRAME
        optionsFrame = new JFrame();
        optionsFrame.setUndecorated(true); 
        optionsFrame.setLayout(null);
        optionsFrame.setSize(1000, 700);
        optionsFrame.setLocation(300, 50);
        ImageIcon logoIcon = new ImageIcon("Image/icon.png");
        Image logo=logoIcon.getImage();
        optionsFrame.setIconImage(logo);
        optionsFrame.setShape(new RoundRectangle2D.Double(0, 0, optionsFrame.getWidth(), optionsFrame.getHeight(), 30, 30));

        //ADDING A NOTEBOOK SPIRAL
        ImageIcon getSpiralImage = new ImageIcon(ClassLoader.getSystemResource("Image/Spiral.png"));
        Image scaleSpiralImage = getSpiralImage.getImage().getScaledInstance(490,700, Image.SCALE_DEFAULT);
        ImageIcon spiralImage = new ImageIcon(scaleSpiralImage);
        JLabel spiralLabel = new JLabel(spiralImage);
        spiralLabel.setBounds(450,0,100,700);
        optionsFrame.add(spiralLabel);


        //LEFT PANEL FOR REVISIT YOUR MEMORIES
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(118,60,56));
        leftPanel.setBounds(0, 0, 490,700);
        leftPanel.setLayout(null);
        optionsFrame.add(leftPanel);
        
        
        revisitQuotLabel = new JLabel("<html>A diary stores the memories that the mind cannot...<html>");
        revisitQuotLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        revisitQuotLabel.setBounds(50,30,400,200);
        revisitQuotLabel.setForeground(Color.WHITE);
        leftPanel.add(revisitQuotLabel);

        readButton = new JButton("READ YOUR MEMORIES");
        readButton.setBounds(40, 550, 200, 50);
        readButton.setFont(new Font("Bitstream Vera Sans",Font.BOLD,15));
        readButton.setBackground(new Color(161, 162, 166));
        readButton.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),2));
        readButton.setFocusable(false);
        readButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        readButton.addActionListener(this);
        leftPanel.add(readButton);



        

        // RIGHT PANEL FOR WRITING A NEW PAGE INTO DIARY
        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(186,108,91));
        rightPanel.setBounds(510, 0, 490,700);
        rightPanel.setLayout(null);
        optionsFrame.add(rightPanel);
        
        ImageIcon closeImage = new ImageIcon(ClassLoader.getSystemResource("Image//close.png"));
        Image scaleCloseImage= closeImage.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        closeImage = new ImageIcon(scaleCloseImage);
        closeLabel = new JLabel(closeImage);
        closeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeLabel.setBounds(450, 5, 25, 25);
        closeLabel.addMouseListener(this); // Add mouse listener to the image label
        rightPanel.add(closeLabel);

        newPageQuotLabel = new JLabel("<html>Continue your story by adding a new chapter...<html>");
        newPageQuotLabel.setForeground(new Color(0,0,0));
        newPageQuotLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        newPageQuotLabel.setForeground(Color.WHITE);
        newPageQuotLabel.setBounds(50,30,400,200);
        rightPanel.add(newPageQuotLabel);
        
        
        writeButton = new JButton("WRITE A PAGE");
        writeButton.setFont(new Font("Bitstream Vera Sans",Font.BOLD,15));
        writeButton.setBounds(250, 550, 200, 50);
        writeButton.setBackground(new Color(216, 174, 126));
        writeButton.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),2));
        writeButton.setFocusable(false);
        writeButton.addActionListener(this);
        writeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rightPanel.add(writeButton);
        

        
        
        optionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        optionsFrame.setVisible(true);
    }


    public void actionPerformed(ActionEvent ae){
        // System.out.println("Btn is clicked");
        if(ae.getSource().equals(writeButton)){
            // System.out.println("WRITE BUTTON");
            optionsFrame.setVisible(false);
            new WritePage(con);
        }
        else if(ae.getSource().equals(readButton)){
            optionsFrame.setVisible(false);
            new ReadPage(0,con);
        }
    }
    public void mouseClicked(MouseEvent e) {
        optionsFrame.setVisible(false);
        new Back(con);
    }
    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        closeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    public void mouseExited(MouseEvent e) {
    }
}