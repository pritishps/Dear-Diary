import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
// import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

public class ReadPage implements MouseListener, KeyListener{
    int backClicks = 0;
    JFrame readPageFrame;
    JPanel leftPanel, rightPanel;
    JLabel leftDateLabel, rightDatLabel, leftDayLabel, rightDayLabel, leftYearLabel,
           rightYearLabel, leftWeekLabel, rightWeekLabel, leftMonthLabel,
           rightMonthLabel, leftDiaryText, rightDiaryText, pageBackLabel,
           pageForwardLabel, backArrowLabel, closeLabel;
    JButton previousPageButton, nextPageButton;
    LocalDate leftDate, rightDate;
    String leftWeekDay, rightWeekDay, leftFormattedDate, rightFormattedDate, leftMemory, rightMemory;
    JTextArea leftDiaryTextArea, rightDiaryTextArea;
    JScrollPane leftViewScrollPane, rightViewScrollPane;
    Connection con;

    ReadPage(int backClicks, Connection con){
        this.con = con;
        this.backClicks = backClicks;
        // ADDING FRAME
        readPageFrame = new JFrame();
        readPageFrame.setUndecorated(true); 
        readPageFrame.setLayout(null);
        readPageFrame.getContentPane().setBackground(new Color(60,91,111));
        readPageFrame.setSize(1000, 700);
        readPageFrame.setLocation(300, 50);
        ImageIcon logoIcon = new ImageIcon("Image/icon.png");
        Image logo=logoIcon.getImage();
        readPageFrame.setIconImage(logo);
        readPageFrame.setShape(new RoundRectangle2D.Double(0, 0, readPageFrame.getWidth(), readPageFrame.getHeight(), 30, 30));
        readPageFrame.addKeyListener(this);

        // ADDING A NOTEBOOK SPIRAL
        ImageIcon getSpiralImage = new ImageIcon(ClassLoader.getSystemResource("Image/Spiral.png"));
        Image scaleSpiralImage = getSpiralImage.getImage().getScaledInstance(490, 700, Image.SCALE_DEFAULT);
        ImageIcon spiralImage = new ImageIcon(scaleSpiralImage);
        JLabel spiralLabel = new JLabel(spiralImage);
        spiralLabel.setBounds(450, 0, 100, 700);
        readPageFrame.add(spiralLabel);

        // ADDING BACK ARROW
        ImageIcon getLineImage = new ImageIcon(ClassLoader.getSystemResource("Image/line.png"));
        Image scaleLineImage = getLineImage.getImage().getScaledInstance(450, 50, Image.SCALE_SMOOTH);
        ImageIcon lineImage = new ImageIcon(scaleLineImage);
        JLabel rightLineLabel = new JLabel(lineImage);
        rightLineLabel.setBounds(20, 30, 450, 50);
        JLabel leftLineLabel = new JLabel(lineImage);
        leftLineLabel.setBounds(20, 30, 450, 50);
        
        // LEFT PANEL VIEWING
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(186,108,91));
        leftPanel.setBounds(0, 0, 490, 700);
        leftPanel.setLayout(null);
        readPageFrame.add(leftPanel);

        ImageIcon getBackArrowImage = new ImageIcon(ClassLoader.getSystemResource("Image/back_arrow.png"));
        backArrowLabel = new JLabel(getBackArrowImage);
        backArrowLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backArrowLabel.setBounds(5, 5, 25, 25);
        backArrowLabel.addMouseListener(this); // Add mouse listener to the image label
        leftPanel.add(backArrowLabel);
        
        leftPanel.add(leftLineLabel);

        leftMonthLabel = new JLabel();
        leftMonthLabel.setFont(new Font("Ariel", Font.BOLD, 20));
        leftMonthLabel.setBounds(420, 10, 40, 20);
        leftMonthLabel.setForeground(Color.WHITE);
        leftPanel.add(leftMonthLabel);
        
        leftYearLabel = new JLabel();
        leftYearLabel.setFont(new Font("Ariel", Font.BOLD, 15));
        leftYearLabel.setBounds(410, 35, 40, 20);
        leftYearLabel.setForeground(Color.WHITE);
        leftPanel.add(leftYearLabel);

        leftDayLabel = new JLabel();
        leftDayLabel.setFont(new Font("Ariel", Font.BOLD, 35));
        leftDayLabel.setBounds(40, 15, 60, 40);
        leftDayLabel.setForeground(Color.WHITE);
        leftPanel.add(leftDayLabel);

        leftWeekLabel = new JLabel();
        leftWeekLabel.setFont(new Font("Ariel", Font.BOLD, 15));
        leftWeekLabel.setBounds(90, 30, 100, 20);
        leftWeekLabel.setForeground(Color.WHITE);
        leftPanel.add(leftWeekLabel);

        leftDiaryTextArea = new JTextArea();
        leftDiaryTextArea.setOpaque(false);
        leftDiaryTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        leftDiaryTextArea.setEditable(false);
        leftDiaryTextArea.setLineWrap(true);
        leftDiaryTextArea.setWrapStyleWord(true);
        leftDiaryTextArea.setFocusable(false);
        leftDiaryTextArea.setForeground(Color.WHITE);

        leftViewScrollPane = new JScrollPane(leftDiaryTextArea);
        leftViewScrollPane.getViewport().setOpaque(false);
        leftViewScrollPane.setBackground(new Color(186,108,91));
        leftViewScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        leftViewScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        leftViewScrollPane.setBorder(BorderFactory.createLineBorder(new Color(186,108,91), 2));
        leftViewScrollPane.setBounds(35, 74, 430, 580);
        leftPanel.add(leftViewScrollPane);

        // LEFT ARROW
        ImageIcon getPageBackImage = new ImageIcon(ClassLoader.getSystemResource("Image/PageBack.png"));
        Image scalePageBackImage = getPageBackImage.getImage().getScaledInstance(30, 70, Image.SCALE_SMOOTH);
        ImageIcon leftTextImage = new ImageIcon(scalePageBackImage);
        pageBackLabel = new JLabel(leftTextImage);
        pageBackLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pageBackLabel.setBounds(0, 50, 30, 650);
        pageBackLabel.addMouseListener(this); // Add mouse listener to the image label
        leftPanel.add(pageBackLabel);

        // RIGHT PANEL VIEWING
        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(186,108,91));
        rightPanel.setBounds(510, 0, 490, 700);
        rightPanel.setLayout(null);
        readPageFrame.add(rightPanel);
        rightPanel.add(rightLineLabel);

        ImageIcon closeImage = new ImageIcon(ClassLoader.getSystemResource("Image/close.png"));
        Image scaleCloseImage = closeImage.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        closeImage = new ImageIcon(scaleCloseImage);
        closeLabel = new JLabel(closeImage);
        closeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeLabel.setBounds(450, 5, 25, 25);
        closeLabel.addMouseListener(this); // Add mouse listener to the image label
        rightPanel.add(closeLabel);

        rightMonthLabel = new JLabel();
        rightMonthLabel.setFont(new Font("Ariel", Font.BOLD, 20));
        rightMonthLabel.setBounds(400, 10, 40, 20);
        rightMonthLabel.setForeground(Color.WHITE);
        rightPanel.add(rightMonthLabel);

        rightYearLabel = new JLabel();
        rightYearLabel.setFont(new Font("Ariel", Font.BOLD, 15));
        rightYearLabel.setBounds(390, 35, 40, 20);
        rightYearLabel.setForeground(Color.WHITE);
        rightPanel.add(rightYearLabel);

        rightDayLabel = new JLabel();
        rightDayLabel.setFont(new Font("Ariel", Font.BOLD, 35));
        rightDayLabel.setBounds(30, 15, 60, 40);
        rightDayLabel.setForeground(Color.WHITE);
        rightPanel.add(rightDayLabel);

        rightWeekLabel = new JLabel();
        rightWeekLabel.setFont(new Font("Ariel", Font.BOLD, 15));
        rightWeekLabel.setBounds(80, 30, 100, 20);
        rightWeekLabel.setForeground(Color.WHITE);
        rightPanel.add(rightWeekLabel);

        rightDiaryTextArea = new JTextArea();
        rightDiaryTextArea.setOpaque(false);
        rightDiaryTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        rightDiaryTextArea.setEditable(false);
        rightDiaryTextArea.setLineWrap(true);
        rightDiaryTextArea.setWrapStyleWord(true);
        rightDiaryTextArea.setFocusable(false);
        rightDiaryTextArea.setForeground(Color.WHITE);

        rightViewScrollPane = new JScrollPane(rightDiaryTextArea);
        rightViewScrollPane.getViewport().setOpaque(false);
        rightViewScrollPane.setBackground(new Color(186,108,91));
        rightViewScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        rightViewScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rightViewScrollPane.setBorder(BorderFactory.createLineBorder(new Color(186,108,91), 2));
        rightViewScrollPane.setBounds(35, 74, 430, 580);
        rightPanel.add(rightViewScrollPane);

        // ADDING FORWARD ARROW
        ImageIcon getPageForwardImage = new ImageIcon(ClassLoader.getSystemResource("Image/PageForward.png"));
        Image scalePageForwardImage = getPageForwardImage.getImage().getScaledInstance(30, 70, Image.SCALE_SMOOTH);
        ImageIcon rightTextImage = new ImageIcon(scalePageForwardImage);
        pageForwardLabel = new JLabel(rightTextImage);
        pageForwardLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pageForwardLabel.setBounds(460, 50, 30, 650);
        pageForwardLabel.addMouseListener(this); // Add mouse listener to the image label
        rightPanel.add(pageForwardLabel);

        readPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        readPageFrame.setVisible(true);

        // Initial update
        updateContent();
    }

    private void updateContent() {
        // Update left side
        leftDate = LocalDate.now().minusDays(this.backClicks + 1);
        DateTimeFormatter leftFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DayOfWeek leftDayOfWeek = leftDate.getDayOfWeek();
        leftWeekDay = leftDayOfWeek.toString();
        leftFormattedDate = leftDate.format(leftFormatter);
        String leftDay = leftFormattedDate.substring(0, 2);
        String leftMonth = leftFormattedDate.substring(3, 5);
        String leftYear = leftFormattedDate.substring(6, 10);

        leftDayLabel.setText(leftDay);
        leftMonthLabel.setText(leftMonth);
        leftYearLabel.setText(leftYear);
        leftWeekLabel.setText(leftWeekDay);

        leftMemory = new Database().getMemory(leftFormattedDate, this.con);
        leftDiaryTextArea.setText(leftMemory);

        // Update right side
        rightDate = LocalDate.now().minusDays(this.backClicks);
        DateTimeFormatter rightFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DayOfWeek rightDayOfWeek = rightDate.getDayOfWeek();
        rightWeekDay = rightDayOfWeek.toString();
        rightFormattedDate = rightDate.format(rightFormatter);
        String rightDay = rightFormattedDate.substring(0, 2);
        String rightMonth = rightFormattedDate.substring(3, 5);
        String rightYear = rightFormattedDate.substring(6, 10);

        rightDayLabel.setText(rightDay);
        rightMonthLabel.setText(rightMonth);
        rightYearLabel.setText(rightYear);
        rightWeekLabel.setText(rightWeekDay);

        rightMemory = new Database().getMemory(rightFormattedDate, this.con);
        rightDiaryTextArea.setText(rightMemory);
    }

    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(pageForwardLabel)){
            if(this.backClicks == 0){
                readPageFrame.setVisible(false);
                new WritePage(con);
            }
            else{
                this.backClicks -= 2;
                updateContent();
            }
        }
        else if(e.getSource().equals(pageBackLabel)){
            this.backClicks += 2;
            updateContent();
        }
        else if(e.getSource().equals(backArrowLabel)){
            new Database().closeConnection(con);
            readPageFrame.dispose();
            new Options();
        }
        else if (e.getSource().equals(closeLabel)) {
            readPageFrame.dispose();
            new Back(con);
        }
    }
    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        pageBackLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pageForwardLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backArrowLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));     
    }

    public void mouseExited(MouseEvent e) {
    }


    // KEY LISTNERS
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(this.backClicks == 0){
                readPageFrame.setVisible(false);
                new WritePage(con);
            }
            else{
                this.backClicks -= 2;
                updateContent();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            this.backClicks += 2;
            updateContent();
        }

    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    
}
