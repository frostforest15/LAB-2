import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LAB_2 {
    private JPanel panel1;
    private JButton button1;
    private JFormattedTextField formattedTextField1;
    private DefaultTableModel model;
    private JComboBox comboBox1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JTable table1;
    private JPanel Header;
    private JPanel Footer;

    public LAB_2() {
        /*-----------------Начинка таблицы----------------*/
        String [] columns = {"Название", "Состав группы", "Год образ.", "Жанр", "Положение в хит-параде"};
        String [][] data = {
                {"Metallica", "Джеймс Хэтфилд, Ларс Ульрих", "1981", "Метал", "2"},
                {"Linkin Park", "Честер Бенингтон, Майк Шинода", "1996", "Альтернативный метал", "16"},
                {"Imagine Dragons", "Дэн Рейнольдс, Уэйн Сермон", "2008", "поп-рок", "36"},
                {"Manowar", "Эрик Адамс, Скотт Коламбус", "1980", "Пауэр-метал", "1"},
                {"Nickelback", "Чед Крюгер, Райан Пик", "1995", " Альтернативный метал", "7"}
        };

        model =  new DefaultTableModel(data, columns);
        table1.setModel(model);

        comboBox1.setBorder(null);
        formattedTextField1.setBorder(null);
    }

    private void show() {
        JFrame frame = new JFrame("Музыкальные группы");
        frame.setContentPane(this.panel1);   // Use 'this' instead of creating a new instance
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // TODO: вернуть textfield, настроить table1
    }

    public static void main(String[] args) {
        new LAB_2().show();
    }
}