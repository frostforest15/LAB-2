import java.awt.FileDialog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class FileSave extends JFrame {
    public FileSave(String str, DefaultTableModel table) {
        FileDialog fileDialog = new FileDialog(this, str, FileDialog.SAVE);

        String fileName = "data.txt";

        fileDialog.setFile(fileName);
        fileDialog.setVisible(true);

        if (fileDialog.getFile() != null) {
            File dataFile = new File(fileDialog.getDirectory(), fileDialog.getFile());
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile.getAbsolutePath()));
                int tableRowCount = table.getRowCount();
                int tableColumnCount = table.getColumnCount();
                for (int i = 0; i < tableRowCount; i++) {
                    for (int j = 0; j < tableColumnCount; j++) {
                        Object cellValue = table.getValueAt(i, j);
                        if (cellValue != null) {
                            writer.write(cellValue.toString());
                        } else {
                            writer.write("");
                        }

                        if (j < tableColumnCount - 1) {
                            writer.write("|");
                        }
                    }
                    if (i < tableRowCount - 1) {
                        writer.write("\r\n");
                    }
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}