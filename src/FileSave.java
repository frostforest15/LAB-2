import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FileSave {
    private static final String[] FIXED_ATTRIBUTES = {"name", "members", "year", "genre", "top"};

    public FileSave(DefaultTableModel table) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Сохранить файл");
        fileChooser.setSelectedFile(new File("data.xml"));

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File dataFile = fileChooser.getSelectedFile();
            try {
                if (dataFile.createNewFile()) {
                    saveTableData(table, dataFile);
                    JOptionPane.showMessageDialog(null, "Данные успешно сохранены в " + dataFile.getAbsolutePath());
                } else {
                    int result = JOptionPane.showConfirmDialog(null,
                            "Файл уже существует. Перезаписать его?", "Файл уже существует",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        saveTableData(table, dataFile);
                        JOptionPane.showMessageDialog(null, "Данные успешно перезаписаны в " + dataFile.getAbsolutePath());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Произошла ошибка при сохранении данных: " + e.getMessage(),
                        "Ошибка сохранения", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveTableData(DefaultTableModel table, File dataFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile.getAbsolutePath()))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
            writer.write("<musiclist>\n");

            int tableRowCount = table.getRowCount();
            int tableColumnCount = table.getColumnCount();
            for (int i = 0; i < tableRowCount; i++) {
                writer.write("    <music ");

                for (String attribute : FIXED_ATTRIBUTES) {
                    writer.write(attribute + "=\"" + table.getValueAt(i, Arrays.asList(FIXED_ATTRIBUTES).indexOf(attribute)) + "\" ");
                }

                writer.write("/>\n");
            }
            writer.write("</musiclist>");
        }
    }
}