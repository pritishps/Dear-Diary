import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

public class WritePage implements ActionListener,MouseListener{
    JFrame writingFrame;
    JPanel leftPanel, rightPanel;
    JLabel writePageLabel, newPageQuotLabel,dateLabel,dayLabel,yearLabel,weakLabel,monthLabel,backArrowLabel;
    JTextArea writeArea,diaryTextArea;
    JButton exitButton, saveButton;
    JScrollPane writeScrollPane,viewScrollPane;
    String weekDay,formattedDate,memory;
    Connection con;
    WritePage(Connection con){
        // ADDING FRAME
        this.con=con;
        writingFrame = new JFrame();
        writingFrame.setUndecorated(true); 
        writingFrame.setLayout(null);
        writingFrame.getContentPane().setBackground(new Color(60,91,111));
        writingFrame.setSize(1000, 700);
        writingFrame.setLocation(300, 50);
        ImageIcon logoIcon = new ImageIcon("Image/icon.png");
        Image logo=logoIcon.getImage();
        writingFrame.setIconImage(logo);
        writingFrame.setShape(new RoundRectangle2D.Double(0, 0, writingFrame.getWidth(), writingFrame.getHeight(), 30, 30));
        // writingFrame.addKeyListener(this);
        

        //ADDING A NOTEBOOK SPIRAL
        ImageIcon getSpiralImage = new ImageIcon(ClassLoader.getSystemResource("Image/Spiral.png"));
        Image scaleSpiralImage = getSpiralImage.getImage().getScaledInstance(490,700, Image.SCALE_DEFAULT);
        ImageIcon spiralImage = new ImageIcon(scaleSpiralImage);
        JLabel spiralLabel = new JLabel(spiralImage);
        spiralLabel.setBounds(450,0,100,700);
        writingFrame.add(spiralLabel);


        //LEFT PANEL FOR WRITING
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(118,60,56));
        leftPanel.setBounds(0, 0, 490,700);
        leftPanel.setLayout(null);
        writingFrame.add(leftPanel);
        

        //ADDING BACK ARROW
        ImageIcon getBackArrowImage = new ImageIcon(ClassLoader.getSystemResource("Image/back_arrow.png"));
        backArrowLabel = new JLabel(getBackArrowImage);
        backArrowLabel.setBounds(5, 20, 30, 30);
        backArrowLabel.addMouseListener(this); // Add mouse listener to the image label
        leftPanel.add(backArrowLabel);
        

        // WRITING PAGE MESSAGE
        writePageLabel = new JLabel("<html>Enter your thoughts..<html>");
        writePageLabel.setForeground(Color.WHITE);
        writePageLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        writePageLabel.setBounds(50,30,350,100);
        leftPanel.add(writePageLabel);



        // WRITE AREA
        writeArea = new JTextArea(28, 1);
        writeArea.setLineWrap(true);
        writeArea.setWrapStyleWord(true);
        writeArea.setOpaque(false);
        writeArea.setForeground(Color.WHITE);

        writeArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        writeArea.requestFocusInWindow();
        writeScrollPane = new JScrollPane(writeArea);
        writeScrollPane.setBounds(30, 130, 400, 400);
        writeScrollPane.getViewport().setOpaque(false);
        writeScrollPane.setBackground(new Color(186,108,91));
        writeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        // writeScrollPane.setBorder(BorderFactory.createLineBorder(new Color(216, 174, 126),2));
        Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(255, 224, 181)), "Memory", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Bell Mt", Font.PLAIN, 20), new Color(255, 224, 181));
        writeScrollPane.setBorder(border);
        leftPanel.add(writeScrollPane);

        // SAVE BUTTON
        saveButton = new JButton("SAVE YOUR MEMORY");
        saveButton.setBounds(270, 550, 160, 50);
        saveButton.setBackground(new Color(186,108,91));
        saveButton.setBorder(BorderFactory.createLineBorder(new Color(255, 224, 181),2,false));
        saveButton.setFocusable(false);
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(this);
        leftPanel.add(saveButton);


        // EXIT BUTTON
        exitButton = new JButton("CLOSE DIARY");
        exitButton.setBounds(30, 550, 160, 50);
        exitButton.setBackground(new Color(186,108,91));
        exitButton.setBorder(BorderFactory.createLineBorder(new Color(255, 224, 181),2,false));
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftPanel.add(exitButton);


        // RIGHT PANEL FOR PREVIEW
        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(186,108,91));
        rightPanel.setBounds(510, 0, 490,700);
        rightPanel.setLayout(null);
        writingFrame.add(rightPanel);
        
        
        ImageIcon getLineImage = new ImageIcon(ClassLoader.getSystemResource("Image/line.png"));
        Image scaleLineImage = getLineImage.getImage().getScaledInstance(450,50, Image.SCALE_SMOOTH);
        ImageIcon lineImage = new ImageIcon(scaleLineImage);
        JLabel lineLabel = new JLabel(lineImage);
        lineLabel.setBounds(20, 30, 450, 50);
        rightPanel.add(lineLabel);
       
        // GETTING DATE
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        weekDay=dayOfWeek.toString();
        formattedDate = currentDate.format(formatter);
        String day=formattedDate.substring(0,2);
        String month=formattedDate.substring(3,5);
        String year=formattedDate.substring(6, 10);

        // CREATING LABES FOR DATES
        monthLabel = new JLabel(month);
        monthLabel.setFont(new Font("Ariel", Font.BOLD, 20));
        monthLabel.setForeground(Color.WHITE);
        monthLabel.setBounds(420,10,40,20);
        rightPanel.add(monthLabel);

        yearLabel = new JLabel(year);
        yearLabel.setFont(new Font("Ariel", Font.BOLD, 15));
        yearLabel.setForeground(Color.WHITE);
        yearLabel.setBounds(410,35,40,20);
        rightPanel.add(yearLabel);

        dayLabel = new JLabel(day);
        dayLabel.setFont(new Font("Ariel", Font.BOLD, 35));
        dayLabel.setForeground(Color.WHITE);
        dayLabel.setBounds(30,15,60,40);
        rightPanel.add(dayLabel);

        weakLabel = new JLabel(weekDay);
        weakLabel.setFont(new Font("Ariel", Font.BOLD, 15));
        weakLabel.setForeground(Color.WHITE);
        weakLabel.setBounds(80,30,100,20);
        rightPanel.add(weakLabel);

        // Create a JTextArea instead of a JLabel
        memory=new Database().getMemory(formattedDate,con);
        diaryTextArea = new JTextArea();
        diaryTextArea.setOpaque(false);
        diaryTextArea.setText(memory);
        diaryTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        diaryTextArea.setEditable(false);
        diaryTextArea.setLineWrap(true);
        diaryTextArea.setForeground(Color.WHITE);
        diaryTextArea.setWrapStyleWord(true);
        diaryTextArea.setFocusable(false);

        viewScrollPane = new JScrollPane(diaryTextArea);
        viewScrollPane.getViewport().setOpaque(false);
        viewScrollPane.setBackground(new Color(186,108,91));
        viewScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        viewScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        viewScrollPane.setBorder(BorderFactory.createLineBorder(new Color(186,108,91), 2));
        viewScrollPane.setBounds(35, 74, 440, 580);
        rightPanel.add(viewScrollPane);

        writingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        writingFrame.setVisible(true);
        writingFrame.requestFocusInWindow();
    }
  
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource().equals(exitButton)){
                writingFrame.setVisible(false);
                new Back(con);
        }
        else if(ae.getSource().equals(saveButton)) {
            try{
                Statement statement = con.createStatement();
                String query="";
                memory=new Database().getMemory(formattedDate,con);
                if(writeArea.getText().length()>0){
                    if(memory==""){
                        memory=writeArea.getText();
                        query = "Insert into personaldiary values('" + formattedDate + "','"+ weekDay +"','"+ writeArea.getText() +"')";
                    }
                    else{
                        memory=memory+"\n"+writeArea.getText();
  
                        query = "Update personaldiary set memory='"+ memory +"' where edate='" + formattedDate + "'";
                    }
                    statement.executeUpdate(query);
                }
               
                memory=new Database().getMemory(formattedDate,con);
                diaryTextArea.setText(memory);
                writeArea.setText("");
                statement.close();
            }
            catch(SQLException se){
                System.out.println(se);
            }
            
        }

    }
    public void mouseClicked(MouseEvent e) {
        writingFrame.setVisible(false);
        new Database().closeConnection(con);
        new Options();
    }
    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        backArrowLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));  
    }

    public void mouseExited(MouseEvent e) {
    }
}