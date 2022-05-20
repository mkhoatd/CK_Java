import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import com.opencsv.CSVReader;
import java.util.List;
public class MainFrame extends JFrame {
    private List<String[]> dataImported= new ArrayList<>();
    private XuLyDonHang xuLy;
    public MainFrame(){
        this.setSize(512,256);
        JLabel label1=new JLabel("Import data");
        JTextField textFieldFileName=new JTextField();
        textFieldFileName.setPreferredSize(new Dimension(100,20));
        JButton buttonImport=new JButton("Import file");
        JLabel label2=new JLabel("Keyword");
        JTextField textFieldKeyword=new JTextField();
        JButton buttonSoLuong=new JButton("So luong");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel contentPanel=new JPanel(new GridBagLayout());
        this.setContentPane(contentPanel);
        GridBagConstraints c=new GridBagConstraints();
        c.fill=GridBagConstraints.HORIZONTAL;
        c.insets=new Insets(0,10,10,0);

        c.gridx=0;
        c.gridy=0;
        c.gridwidth=1;
        contentPanel.add(label1,c);

        c.gridx=1;
        c.gridy=0;
        c.gridwidth=1;

        contentPanel.add(textFieldFileName,c);

        buttonImport.addActionListener(e -> {
            String url=textFieldFileName.getText();
            try
            {
                FileReader inputFile=new FileReader(url);
                CSVReader reader =new CSVReader(inputFile);
                dataImported=reader.readAll();
                dataImported.forEach(x->System.out.println(Arrays.toString(x)));
                xuLy=new XuLyDonHang(dataImported);
            }
            catch (Exception ex)
            {
                textFieldFileName.setText("");
                JOptionPane.showMessageDialog(null, "File not found"+ex.getMessage());
            }
        });
        c.gridx=2;
        c.gridy=0;
        c.gridwidth=1;
        contentPanel.add(buttonImport,c);
        c.gridx=0;
        c.gridy=1;
        c.gridwidth=1;
        contentPanel.add(label2,c);
        c.gridx=1;
        c.gridy=1;
        c.gridwidth=2;
        contentPanel.add(textFieldKeyword,c);
        buttonSoLuong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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
}
