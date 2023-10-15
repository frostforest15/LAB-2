import java.awt.FileDialog;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;


public class FileSave extends JFrame {
    public FileSave(String str,DefaultTableModel table){
        FileDialog sava = new FileDialog(this, str, FileDialog.SAVE);
        sava.setFile("*.txt");
        sava.setVisible(true);

        String fileNameSave = sava.getDirectory() + sava.getFile();
        if (fileNameSave == null) return;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameSave));
            for (int i = 0; i < table.getRowCount(); i++){
                writer.write("\r\n");
                for (int j = 0; j < table.getColumnCount(); j++){
                    writer.write((String)table.getValueAt(i, j));
                    if (j < 4) writer.write("|");
                }
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
