import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainFrame extends JFrame {
    public MainFrame(){
        this.setSize(512,256);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel contentPanel=new JPanel(new GridBagLayout());
        this.setContentPane(contentPanel);
        GridBagConstraints c=new GridBagConstraints();
        c.fill=GridBagConstraints.HORIZONTAL;
        c.insets=new Insets(0,10,10,0);
        JLabel label1=new JLabel("Import data");
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=1;
        contentPanel.add(label1,c);
        JTextField textFieldFileName=new JTextField();
        c.gridx=1;
        c.gridy=0;
        c.gridwidth=1;
        textFieldFileName.setPreferredSize(new Dimension(100,20));
        contentPanel.add(textFieldFileName,c);
        JButton buttonImport=new JButton("Import file");
        buttonImport.addActionListener(e -> {
            String url=textFieldFileName.getText();
            File inputFile=new File(url);
            Scanner fileScanner=null;
            try {
                fileScanner=new Scanner(inputFile);
            }
            catch (FileNotFoundException ex)
            {
                textFieldFileName.setText("");
                JOptionPane.showMessageDialog(null, "File not found");
            }
            while(fileScanner.hasNextLine()){
                String line=fileScanner.nextLine();
                System.out.println(line);
            }
        });
        c.gridx=2;
        c.gridy=0;
        c.gridwidth=1;
        contentPanel.add(buttonImport,c);
        JLabel label2=new JLabel("Keyword");
        c.gridx=0;
        c.gridy=1;
        c.gridwidth=1;
        contentPanel.add(label2,c);
        JTextField textFieldKeyword=new JTextField();
        c.gridx=1;
        c.gridy=1;
        c.gridwidth=2;
        contentPanel.add(textFieldKeyword,c);
        JButton buttonSoLuong=new JButton("So luong");
        c.gridx=0;
        c.gridy=2;
        c.gridwidth=1;
        contentPanel.add(buttonSoLuong,c);
        JButton buttonTongTien=new JButton("Tong tien");
        c.gridx=1;
        c.gridy=2;
        c.gridwidth=1;
        contentPanel.add(buttonTongTien,c);
        JButton buttonGoiY=new JButton("Goi y");
        c.gridx=2;
        c.gridy=2;
        c.gridwidth=1;
        contentPanel.add(buttonGoiY,c);
        JTextArea textArea=new JTextArea();
        textArea.setPreferredSize(new Dimension(250,100));
        c.gridx=0;
        c.gridy=3;
        c.gridheight=3;
        c.gridwidth=3;
        contentPanel.add(textArea,c);
        this.setVisible(true);

    }
//    public MainFrame(){
//        this.setLayout(new GridLayout(4,1));
//        JPanel pan1=new JPanel();
//        pan1.setLayout(new GridLayout(1,3));
//        JLabel label1=new JLabel("Import data");
//        JTextField textFieldFileName=new JTextField("input.txt");
//        JButton buttonImport=new JButton();
//        pan1.add(label1);
//        pan1.add(textFieldFileName);
//        pan1.add(buttonImport);
//        this.add(pan1);
//        JPanel pan2=new JPanel();
//        pan2.setLayout(new GridLayout(1,2));
//        JLabel label2=new JLabel("Keyword");
//        JTextField textFieldKeyword=new JTextField();
//        pan2.add(label2);
//        pan2.add(textFieldKeyword);
//        this.add(pan2);
//        JPanel pan3=new JPanel();
//        pan3.setLayout(new GridLayout(1,3));
//        JButton buttonSoLuong=new JButton("So luong");
//        JButton buttonTongTien=new JButton("Tong tien");
//        JButton buttonGoiY=new JButton("Goi y");
//        pan3.add(buttonSoLuong);
//        pan3.add(buttonTongTien);
//        pan3.add(buttonGoiY);
//        this.add(pan3);
//        JPanel pan4=new JPanel();
//        pan4.setLayout(new GridLayout(1,1));
//        JTextArea textArea=new JTextArea();
//        pan4.add(textArea);
//        this.add(pan4);
//        this.pack();
//        this.setVisible(true);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//    }
}
