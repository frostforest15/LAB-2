import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class SearchHandler {
    private DefaultTableModel model;
    private JComboBox comboBox;
    private JTextField textField;
    private JTable table;

    public SearchHandler(DefaultTableModel model, JComboBox comboBox, JTextField textField, JTable table) {
        this.model = model;
        this.comboBox = comboBox;
        this.textField = textField;
        this.table = table;
    }

    public void performSearch() {
        String searchField = comboBox.getSelectedItem().toString();
        String searchText = textField.getText().toLowerCase();

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        if (!searchText.isEmpty()) {
            RowFilter<DefaultTableModel, Object> rowFilter = new RowFilter<DefaultTableModel, Object>() {
                @Override
                public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                    int col = getColumnIndex(searchField);
                    String value = entry.getStringValue(col).toLowerCase();
                    return value.contains(searchText);
                }
            };
            sorter.setRowFilter(rowFilter);
        } else {
            sorter.setRowFilter(null);
        }
    }

    private int getColumnIndex(String columnName) {
        for (int i = 0; i < model.getColumnCount(); i++) {
            if (model.getColumnName(i).equals(columnName)) {
                return i;
            }
        }
        return -1;
    }
}