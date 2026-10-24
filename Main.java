import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createWindow);
    }

    private static void createWindow() {
        JFrame frame = new JFrame("Student Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Name", "Email"}, 0);
        JTable table = new JTable(model);

        JTextField name = new JTextField(8);
        JTextField email = new JTextField(10);

        JButton add = new JButton("Add");
        JButton edit = new JButton("Edit");
        JButton delete = new JButton("Delete");

        add.addActionListener(e -> {
            if (name.getText().isBlank() || email.getText().isBlank()) {
                JOptionPane.showMessageDialog(frame, "Enter name and email");
                return;
            }
            model.addRow(new Object[]{
                    model.getRowCount() + 1,
                    name.getText(),
                    email.getText()
            });
            name.setText("");
            email.setText("");
        });

        edit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0 && !name.getText().isBlank()
                    && !email.getText().isBlank()) {
                model.setValueAt(name.getText(), row, 1);
                model.setValueAt(email.getText(), row, 2);
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Select a student and enter new details");
            }
        });

        delete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                model.removeRow(row);
            }
        });

        JPanel controls = new JPanel();
        controls.add(new JLabel("Name:"));
        controls.add(name);
        controls.add(new JLabel("Email:"));
        controls.add(email);
        controls.add(add);
        controls.add(edit);
        controls.add(delete);

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(controls, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}