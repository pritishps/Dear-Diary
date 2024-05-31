import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

class Login implements ActionListener,MouseListener,KeyListener {
    JFrame loginFrame;
    JButton enterButton;
    JLabel lDiaryName,lname,lPassword,lwarning,closeLabel;
    JPasswordField passwordField;
    JPanel leftPanel, rightPanel;
    Login(){
        loginFrame=new JFrame();
        loginFrame.setUndecorated(true); 
        loginFrame.setLayout(null);
        loginFrame.getContentPane().setBackground(new Color(60,91,111));
        loginFrame.setSize(1000, 700);
        loginFrame.setLocation(300, 50);
        ImageIcon logoIcon = new ImageIcon("Image/icon.png");
        Image logo=logoIcon.getImage();
        loginFrame.setIconImage(logo);
        loginFrame.setShape(new RoundRectangle2D.Double(0, 0, loginFrame.getWidth(), loginFrame.getHeight(), 30, 30));
        //ADDING A NOTEBOOK SPIRAL
        ImageIcon getSpiralImage = new ImageIcon(ClassLoader.getSystemResource("Image/Spiral.png"));
        Image scaleSpiralImage = getSpiralImage.getImage().getScaledInstance(490,700, Image.SCALE_DEFAULT);
        ImageIcon spiralImage = new ImageIcon(scaleSpiralImage);
        JLabel spiralLabel = new JLabel(spiralImage);
        spiralLabel.setBounds(450,0,100,700);
        loginFrame.add(spiralLabel);
        
        leftPanel=new JPanel();
        leftPanel.setBackground(new Color(51,13,14));
        leftPanel.setBounds(0, 0, 490,700);
        leftPanel.setLayout(null);
        loginFrame.add(leftPanel);

        //Adding Back Button
        

        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(118,60,56));
        rightPanel.setBounds(510, 0, 490,700);
        rightPanel.setLayout(null);
        loginFrame.add(rightPanel);

      
        ImageIcon getMainImag = new ImageIcon(ClassLoader.getSystemResource("image/MyDiary.png"));
        Image scaleMainImag = getMainImag.getImage().getScaledInstance(440,280, Image.SCALE_SMOOTH);
        ImageIcon mainImag = new ImageIcon(scaleMainImag);
        JLabel mainlLabe = new JLabel(mainImag);
        mainlLabe.setBounds(20,150,440,300);
        leftPanel.add(mainlLabe);
        
        
        ImageIcon closeImage = new ImageIcon(ClassLoader.getSystemResource("Image/close.png"));
        Image scaleCloseImage= closeImage.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        closeImage = new ImageIcon(scaleCloseImage);
        closeLabel = new JLabel(closeImage);
        closeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeLabel.setBounds(450, 5, 25, 25);
        closeLabel.addMouseListener(this); // Add mouse listener to the image label
        rightPanel.add(closeLabel);

        lname=new JLabel("Hey Ranjit!");
        lname.setBounds(130,90,300,80);
        lname.setForeground(Color.WHITE);
        lname.setFont(new Font("Bell Mt",Font.PLAIN,50));
        rightPanel.add(lname);

        // lPassword=new JLabel("Enter Your Password.");
        // lPassword.setBounds(60,260,200,30);
        // lPassword.setFont(new Font("Bell Mt",Font.PLAIN,20));
        // rightPanel.add(lPassword);

       

        passwordField= new JPasswordField();
        passwordField.setBounds(60,292,250,50);
        // passwordField.setBorder(BorderFactory.createLineBorder(new Color(216, 174, 126),2,true));
        Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Password", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Bell Mt", Font.PLAIN, 16), Color.WHITE);
        passwordField.setBorder(border);
        passwordField.setBackground(new Color(118,60,56));
        passwordField.setForeground(Color.WHITE);
        passwordField.addKeyListener(this);
        passwordField.setFont(new Font("Bell Mt",Font.PLAIN,20));

        rightPanel.add(passwordField);

        

        enterButton=new JButton("Enter");
        enterButton.setBounds(330,300,90,40);
        enterButton.setBackground(new Color(248,199,148));
        enterButton.setBorder(BorderFactory.createLineBorder(new Color(216, 174, 126),2));
        enterButton.setFont(new Font("Bell Mt",Font.BOLD,20));
        enterButton.setFocusable(false);
        enterButton.addActionListener(this);
        enterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rightPanel.add(enterButton);

        lwarning=new JLabel("");
        lwarning.setBounds(60,360,300,30);
        lwarning.setFont(new Font("Bell Mt",Font.PLAIN,20));
        lwarning.setForeground(Color.WHITE);
        rightPanel.add(lwarning);

        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);

       
    }
    public void actionPerformed(ActionEvent ae){
        char[] enteredPassword = passwordField.getPassword();
        String pass=new String(enteredPassword);
        if(pass.equals("1245")){
            loginFrame.setVisible(false);
            new Options();
        }
        else{
            lwarning.setText("Wrong Password!!");
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            actionPerformed(null);
        }

    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        loginFrame.dispose();
        
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