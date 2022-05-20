import com.opencsv.CSVReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class TestForm {
    private JTextField fileNameTextField;
    private JButton importFileButton;
    private JTextField keyWordTextField;
    private JButton soLuongButton;
    private JButton tongTienButton;
    private JButton goiYButton;
    private JTextArea textArea1;
    private JLabel label1;
    private JLabel label2;
    private JPanel mainPanel;
    private List<String[]> dataImported;
    private XuLyDonHang xuLy;
    public TestForm() {
        importFileButton.addActionListener(e -> {
            String url=fileNameTextField.getText();
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
                fileNameTextField.setText("");
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        soLuongButton.addActionListener(e -> {
            String tenMatHang=keyWordTextField.getText();
            if(tenMatHang.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Thieu keyword");
                return;
            }
            int soLuong= xuLy.getSoLuong(tenMatHang);
            textArea1.setText(Integer.toString(soLuong));
        });
        tongTienButton.addActionListener(e -> {
            String tenNguoiMua=keyWordTextField.getText();
            if(tenNguoiMua.equals("")){
                JOptionPane.showMessageDialog(null,"Thieu keyword");
                return;
            }
            int tongTien= xuLy.getTongTien(tenNguoiMua);
            textArea1.setText(Integer.toString(tongTien));
        });
        goiYButton.addActionListener(e -> {
            String tenMatHang=keyWordTextField.getText();
            if(tenMatHang.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Thieu keyword");
                return;
            }
            List<String> result=xuLy.getGoiY(tenMatHang);
            StringBuilder text= new StringBuilder();
            for (String x : result) {
                text.append(x).append("\n");
            }
            textArea1.setText(text.toString());
        });
    }

    public static void run() {
        JFrame frame = new JFrame("TestForm");
        frame.setContentPane(new TestForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
