import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LAB_2 {
    private JPanel panel1;
    private JFormattedTextField formattedTextField1;
    private DefaultTableModel model;
    private JComboBox comboBox1;
    private JButton find_button, save_button, delete_button, add_button, edit_button, open_button;
    private JTable table1;
    private JPanel Header;
    private JPanel Footer;
    private JScrollPane scrollPane1;
    public LAB_2() {
        comboBox1.setBorder(null);

        formattedTextField1.setBorder(null);
        formattedTextField1.setMinimumSize(new Dimension(238, 37));

        comboBox1.setMinimumSize(new Dimension(238, 37));
        comboBox1.addItem("Группа");
        comboBox1.addItem("Состав группы");
        comboBox1.addItem("Год образ.");
        comboBox1.addItem("Жанр");
        comboBox1.addItem("Положение в хит-параде");

        find_button.setMinimumSize(new Dimension(120, 37));

        Color customColor = Color.decode("#D3E8CA");
        scrollPane1.setBackground(customColor);

        scrollPane1.getViewport().setBackground(customColor);

        find_button.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 0, 0, 28), find_button.getBorder()));
        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getModel().getRowCount() != 0) {
                    FileSave file = new FileSave("Сохранение данных", model);
                }
            }
        });

    }

    private void show() {
        JFrame frame = new JFrame("Музыкальные группы");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        save_button.setToolTipText("Сохранить как");
        open_button.setToolTipText("Найти файл в проводнике");
        edit_button.setToolTipText("Редактировать запись");
        add_button.setToolTipText("Добавить запись");
        delete_button.setToolTipText("Удалить запись");
        find_button.setToolTipText("Найти данные");
    }

    public static void main(String[] args) {
        new LAB_2().show();
    }

    private void createUIComponents() {
        formattedTextField1 = new RoundedJTextField(35, "Наименование жанра");
        find_button = new RoundedJButton(35);
        comboBox1 = new RoundedJComboBox(35);
        scrollPane1 = new RoundedJScrollPane(35);
        table1 = new JTable();

        String[][] data = {};

        String[] columns = {"Название", "Состав группы", "Год образ.", "Жанр", "Положение в хит-параде"};

        model = new DefaultTableModel(data, columns);

        try (BufferedReader br = new BufferedReader(new FileReader("src/data/data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                model.addRow(parts);
            }
            table1.setModel(model);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DefaultTableCellRenderer centerRend = (DefaultTableCellRenderer) table1.getDefaultRenderer(String.class);
        centerRend.setHorizontalAlignment(JLabel.CENTER);

        Color tableColor = Color.decode("#BACEB2");

        JTableHeader header = table1.getTableHeader();
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(tableColor);

        headerRenderer.setFont(new Font("Verdana", Font.BOLD, 16));
        headerRenderer.setPreferredSize(new Dimension(header.getWidth(), 44));
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);


        for (int i = 0; i < table1.getModel().getColumnCount(); i++) {
            table1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

    }
}