import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LAB_2 {
    private JPanel panel1;
    private JFormattedTextField formattedTextField1;
    private DefaultTableModel model;
    private JComboBox comboBox1;
    private JButton button_find, button_save, button_delete, button_add, button_edit, button_open;
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

        button_find.setMinimumSize(new Dimension(120, 37));

        Color customColor = Color.decode("#D3E8CA");
        scrollPane1.setBackground(customColor);

        scrollPane1.getViewport().setBackground(customColor);

        button_find.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 0, 0, 28), button_find.getBorder()));
        button_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileSave file = new FileSave("Сохранение данных", model);
                }
        });

    }

    private void show() {
        JFrame frame = new JFrame("Музыкальные группы");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        button_save.setToolTipText("Сохранить как");
        button_open.setToolTipText("Найти файл в проводнике");
        button_edit.setToolTipText("Редактировать запись");
        button_add.setToolTipText("Добавить запись");
        button_delete.setToolTipText("Удалить запись");
        button_find.setToolTipText("Найти данные");
    }

    public static void main(String[] args) {
        new LAB_2().show();
    }

    private void createUIComponents() {
        formattedTextField1 = new RoundedJTextField(35, "Наименование жанра");
        button_find = new RoundedJButton(35);
        comboBox1 = new RoundedJComboBox(35);
        scrollPane1 = new RoundedJScrollPane(35);

        String[] columns = {"Название", "Состав группы", "Год образ.", "Жанр", "Положение в хит-параде"};
        String[][] data = {
                {"Metallica", "Джеймс Хэтфилд, Ларс Ульрих", "1981", "Метал", "2"},
                {"Linkin Park", "Честер Бенингтон, Майк Шинода", "1996", "Альтернативный метал", "16"},
                {"Imagine Dragons", "Дэн Рейнольдс, Уэйн Сермон", "2008", "Поп-рок", "36"},
                {"Manowar", "Эрик Адамс, Скотт Коламбус", "1980", "Пауэр-метал", "1"},
                {"Nickelback", "Чед Крюгер, Райан Пик", "1995", " Альтернативный метал", "7"}
        };

        model = new DefaultTableModel(data, columns);
        table1 = new JTable(data, columns);

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