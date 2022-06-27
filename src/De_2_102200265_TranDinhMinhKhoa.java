import com.opencsv.CSVReader;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.io.FileReader;
import java.sql.*;
import java.util.*;
import java.util.List;

public class De_2_102200265_TranDinhMinhKhoa {
    private JTextField fileNameTextField;
    private JButton importFileButton;
    private JTextField keyWordTextField;
    private JButton rankingButton;
    private JButton searchButton;
    private JButton wonTeamsButton;
    private JTextArea outputTextArea;
    private JLabel label1;
    private JLabel label2;
    private JPanel mainPanel;
    private List<String[]> dataImported;
    private Connection conn;

    public De_2_102200265_TranDinhMinhKhoa() {
        importFileButton.addActionListener(e -> {
            String url = fileNameTextField.getText();
            try {
                FileReader inputFile = new FileReader(url);
                CSVReader reader = new CSVReader(inputFile);
                dataImported = reader.readAll();
                StringBuilder sb = new StringBuilder();
                for (String[] row : dataImported) {
                    sb.append(Arrays.toString(row));
                    sb.append("\n");
                }
                outputTextArea.setText(sb.toString());
                reader.close();
                inputFile.close();
                JOptionPane.showMessageDialog(null, "import success");
                String dbURL = "jdbc:postgresql://localhost:5432/cuoiky?user=postgres&password=140521";
                conn = DriverManager.getConnection(dbURL);
                String sql = "SELECT * FROM icpc";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                    return;
                }
                for (String[] row : dataImported) {
                    sql = "INSERT INTO icpc(teamname, universityname, problemid, time, result) VALUES(?,?,?,?,?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1, row[0]);
                    preparedStatement.setString(2, row[1]);
                    preparedStatement.setString(3, row[2]);
                    preparedStatement.setInt(4, Integer.parseInt(row[3]));
                    preparedStatement.setString(5, row[4]);
                    preparedStatement.executeUpdate();
                }
            } catch (Exception ex) {
                fileNameTextField.setText("");
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        rankingButton.addActionListener(e -> {
            String sql = "select teamname, universityname, count(problemId) as problems, sum(time) as time " +
                    " from icpc where result like\'AC\'  " +
                    "group by teamname, universityname " +
                    "order by problems desc, time asc";
            List<String[]> result = new ArrayList<>();
            try {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String[] row = new String[4];
                    row[0] = resultSet.getString("teamname");
                    row[1] = resultSet.getString("universityname");
                    row[2] = resultSet.getString("problems");
                    row[3] = resultSet.getString("time");
                    result.add(row);
                }
                //map result

                StringBuilder sb = new StringBuilder();
                for (String[] row : result) {
                    sb.append(Arrays.toString(row));
                    sb.append("\n");
                }
                outputTextArea.setText(sb.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        searchButton.addActionListener(e -> {
            var keyword = keyWordTextField.getText();
            //group by teamname and universityname, count problemId and sum time
            String sql = "select teamname, universityname, count(problemId) as problems, sum(time) as time " +
                    " from icpc where result like\'AC\' and universityname like '%" + keyword + "%' " +
                    "group by teamname, universityname";
            List<String[]> result = new ArrayList<>();
            try {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String[] row = new String[3];
                    row[0] = resultSet.getString("teamname") + "," + resultSet.getString("universityname");
                    row[1] = resultSet.getString("problems");
                    row[2] = Integer.toString(resultSet.getInt("time"));
                    result.add(row);
                }
                //set output
                StringBuilder sb = new StringBuilder();
                for (String[] row : result) {
                    sb.append(Arrays.toString(row));
                    sb.append("\n");
                }
                outputTextArea.setText(sb.toString());
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        });
        wonTeamsButton.addActionListener(e -> {

        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Quan ly ket qua ICPC");
        frame.setContentPane(new De_2_102200265_TranDinhMinhKhoa().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        String dbURL = "jdbc:postgresql://localhost:5432/cuoiky?user=postgres&password=140521";
        try {
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setMinimumSize(new Dimension(500, 300));
        mainPanel.setPreferredSize(new Dimension(500, 300));
        label1 = new JLabel();
        label1.setPreferredSize(new Dimension(100, 17));
        label1.setText("Import data");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 10, 0);
        mainPanel.add(label1, gbc);
        fileNameTextField = new JTextField();
        fileNameTextField.setName("fileNameTextField");
        fileNameTextField.setPreferredSize(new Dimension(100, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 0);
        mainPanel.add(fileNameTextField, gbc);
        importFileButton = new JButton();
        importFileButton.setPreferredSize(new Dimension(100, 30));
        importFileButton.setText("Import file");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 0);
        mainPanel.add(importFileButton, gbc);
        label2 = new JLabel();
        label2.setPreferredSize(new Dimension(100, 17));
        label2.setText("Keyword");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 10, 0);
        mainPanel.add(label2, gbc);
        keyWordTextField = new JTextField();
        keyWordTextField.setPreferredSize(new Dimension(200, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 0);
        mainPanel.add(keyWordTextField, gbc);
        rankingButton = new JButton();
        rankingButton.setPreferredSize(new Dimension(100, 30));
        rankingButton.setText("Ranking");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 0);
        mainPanel.add(rankingButton, gbc);
        searchButton = new JButton();
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.setText("Search");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 0);
        mainPanel.add(searchButton, gbc);
        wonTeamsButton = new JButton();
        wonTeamsButton.setPreferredSize(new Dimension(100, 30));
        wonTeamsButton.setText("Won teams");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 0);
        mainPanel.add(wonTeamsButton, gbc);
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setMinimumSize(new Dimension(100, 50));
        outputTextArea.setPreferredSize(new Dimension(300, 100));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 10, 0);
        mainPanel.add(outputTextArea, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
