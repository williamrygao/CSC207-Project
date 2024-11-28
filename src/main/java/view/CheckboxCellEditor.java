package view;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 * Custom Checkbox.
 */
public class CheckboxCellEditor extends AbstractCellEditor implements TableCellEditor {
    private final JCheckBox checkBox;
    private ActionListener actionListener;

    public CheckboxCellEditor() {
        checkBox = new JCheckBox();
        checkBox.addActionListener(evt -> {
            if (actionListener != null) {
                actionListener.actionPerformed(evt);
            }
            fireEditingStopped();
        });
    }

    public void addActionListener(ActionListener listener) {
        this.actionListener = listener;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof Boolean) {
            checkBox.setSelected((Boolean) value);
        }
        return checkBox;
    }

    @Override
    public Object getCellEditorValue() {
        return checkBox.isSelected();
    }
}
