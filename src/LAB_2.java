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
    private JDialog addRowDialog;
    private JPanel Header;
    private JPanel Footer;
    private JScrollPane scrollPane1;
    public LAB_2() {
        Color customColor = Color.decode("#D3E8CA");
        comboBox1.setBorder(null);

        formattedTextField1.setBorder(null);
        formattedTextField1.setMinimumSize(new Dimension(238, 37));

        comboBox1.setMinimumSize(new Dimension(238, 37));
        comboBox1.addItem("Название");
        comboBox1.addItem("Состав группы");
        comboBox1.addItem("Год образ.");
        comboBox1.addItem("Жанр");
        comboBox1.addItem("Положение в хит-параде");

        find_button.setMinimumSize(new Dimension(120, 37));

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

        delete_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowIndex = table1.getSelectedRow();
                if (selectedRowIndex != -1){
                    model.removeRow(selectedRowIndex);
                } else {
                    System.out.println("Selected data is null");
                }
            }
        });
        
        add_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRowDialog.setVisible(true);
            }
        });

        open_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOpen openFile = new FileOpen(model);
            }
        });

        edit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowIndex = table1.getSelectedRow();

                if (selectedRowIndex != -1) {
                    String nameData = (String) model.getValueAt(selectedRowIndex, 0);
                    String membersData = (String) model.getValueAt(selectedRowIndex, 1);
                    String yearData = (String) model.getValueAt(selectedRowIndex, 2);
                    String genreData = (String) model.getValueAt(selectedRowIndex, 3);
                    String topData = (String) model.getValueAt(selectedRowIndex, 4);

                    JDialog editRowDialog = new JDialog();
                    editRowDialog.setTitle("Редактирование записи");
                    editRowDialog.setLocation(715, 265);
                    editRowDialog.setLayout(new FlowLayout());
                    editRowDialog.setSize(500, 500);
                    editRowDialog.getContentPane().setBackground(new Color(179,221,161));
                    editRowDialog.setResizable(false);

                    JTextField nameField = new JTextField(nameData);
                    JTextField membersField = new JTextField(membersData);
                    JTextField yearField = new JTextField(yearData);
                    JTextField genreField = new JTextField(genreData);
                    JTextField topField = new JTextField(topData);
                    JButton saveButton = new JButton("Сохранить");
                    JButton cancelButton = new JButton("Отменить");

                    Font font = new Font("Verdana", Font.PLAIN, 14);

                    editRowDialog.add(new JLabel("Название:")).setFont(font);
                    editRowDialog.add(nameField).setFont(font);
                    editRowDialog.add(new JLabel("Состав группы:")).setFont(font);
                    editRowDialog.add(membersField).setFont(font);
                    editRowDialog.add(new JLabel("Год образования:")).setFont(font);
                    editRowDialog.add(yearField).setFont(font);
                    editRowDialog.add(new JLabel("Жанр:")).setFont(font);
                    editRowDialog.add(genreField).setFont(font);
                    editRowDialog.add(new JLabel("Положение в хит-параде:")).setFont(font);
                    editRowDialog.add(topField).setFont(font);
                    editRowDialog.add(saveButton).setFont(font);
                    editRowDialog.add(cancelButton).setFont(font);

                    saveButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            model.setValueAt(nameField.getText(), selectedRowIndex, 0);
                            model.setValueAt(membersField.getText(), selectedRowIndex, 1);
                            model.setValueAt(yearField.getText(), selectedRowIndex, 2);
                            model.setValueAt(genreField.getText(), selectedRowIndex, 3);
                            model.setValueAt(topField.getText(), selectedRowIndex, 4);

                            editRowDialog.dispose();
                        }
                    });

                    cancelButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            editRowDialog.dispose();
                        }
                    });
                    editRowDialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Выберите запись для редактирования");
                }
            }
        });

        SearchHandler searchHandler = new SearchHandler(model, comboBox1, formattedTextField1, table1);

        find_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchHandler.performSearch();
            }
        });
    }

    private void show() {
        JFrame frame = new JFrame("Музыкальные группы");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        addRowDialog =  new JDialog(frame, "Новая запись", true);
        addRowDialog.setLocation(frame.getWidth() / 2 + 170, frame.getHeight() / 2 - 55);
        addRowDialog.setLayout(new FlowLayout());
        addRowDialog.setSize(500, 500);
        addRowDialog.getContentPane().setBackground(new Color(179,221,161));
        addRowDialog.setResizable(false);

        JTextField nameField = new JTextField(10);
        JTextField membersField = new JTextField(10);
        JTextField yearField = new JTextField(10);
        JTextField genreField = new JTextField(10);
        JTextField topField = new JTextField(10);
        JButton addButton = new JButton("Добавить");
        JButton cancelButton = new JButton("Отменить");

        Font font = new Font("Verdana", Font.PLAIN, 14);

        addRowDialog.add(new JLabel("Название:")).setFont(font);
        addRowDialog.add(nameField).setFont(font);
        addRowDialog.add(new JLabel("Состав группы:")).setFont(font);
        addRowDialog.add(membersField).setFont(font);
        addRowDialog.add(new JLabel("Год образования:")).setFont(font);
        addRowDialog.add(yearField).setFont(font);
        addRowDialog.add(new JLabel("Жанр:")).setFont(font);
        addRowDialog.add(genreField).setFont(font);
        addRowDialog.add(new JLabel("Положение в хит-параде:")).setFont(font);
        addRowDialog.add(topField).setFont(font);
        addRowDialog.add(addButton).setFont(font);
        addRowDialog.add(cancelButton).setFont(font);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameData = nameField.getText();
                String mambersData = membersField.getText();
                String yearData = yearField.getText();
                String genreData = genreField.getText();
                String topData = topField.getText();

                if (!nameData.isEmpty() &&
                        !mambersData.isEmpty() &&
                        !yearData.isEmpty() &&
                        !genreData.isEmpty() &&
                        !topData.isEmpty()) {
                    model.addRow(new Object[]{nameData, mambersData, yearData, genreData, topData});
                    addRowDialog.dispose();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRowDialog.dispose();
            }
        });

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
        formattedTextField1 = new RoundedJTextField(35, "Поле для ввода");
        find_button = new RoundedJButton(35);
        comboBox1 = new RoundedJComboBox(35);
        scrollPane1 = new RoundedJScrollPane(35);
        table1 = new JTable();

        String[][] data = {};

        String[] columns = {"Название", "Состав группы", "Год образ.", "Жанр", "Положение в хит-параде"};

        model = new DefaultTableModel(data, columns){
            // Restrict direct table row editing
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

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