import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;

public class FileOpen extends JFrame {
    public FileOpen(DefaultTableModel table) {
        FileDialog fileDialog = new FileDialog(this, "Выберите файл", FileDialog.LOAD);
        String dataFileLocation = "C:/Users/darin/eclipse-workspace/Lab2/src/data/data.txt";
        File dataFile = new File(dataFileLocation);
        fileDialog.setDirectory(dataFile.getParent());
        fileDialog.setFile(dataFile.getName());
        fileDialog.setVisible(true);

        String directory = fileDialog.getDirectory();
        String fileName = fileDialog.getFile();

        if (directory != null && fileName != null) {
            String filePath = directory + fileName;
            try {
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                table.setRowCount(0);
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    table.addRow(parts);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}