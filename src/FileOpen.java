import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;

public class FileOpen extends JFrame {
    private DefaultTableModel tableModel;

    public FileOpen(DefaultTableModel tableModel) {
        this.tableModel = tableModel;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите XML-файл");
        fileChooser.setFileFilter(new FileNameExtensionFilter("XML files", "xml"));

        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            try {
                tableModel.setRowCount(0);

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(selectedFile);

                NodeList musicList = document.getElementsByTagName("music");

                for (int i = 0; i < musicList.getLength(); i++) {
                    Element musicElement = (Element) musicList.item(i);

                    String name = musicElement.getAttribute("name");
                    String members = musicElement.getAttribute("members");
                    String year = musicElement.getAttribute("year");
                    String genre = musicElement.getAttribute("genre");
                    String top = musicElement.getAttribute("top");

                    tableModel.addRow(new String[]{name, members, year, genre, top});
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}