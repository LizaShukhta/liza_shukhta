package dialogs;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CDialogNotFrame extends JDialog{
    private JPanel panel;
    public ArrayList<JButton> buttons = new ArrayList<>();
    public ArrayList<JTextField> textFields = new ArrayList<>();
    public ArrayList<JPasswordField> passwordFields = new ArrayList<>();
    public ArrayList<JLabel> labels = new ArrayList<>();
    public ArrayList<JLabel> photos = new ArrayList<>();
    public ArrayList<JComboBox> comboBoxes = new ArrayList<>();
    public ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
    public ArrayList<JRadioButton[]> radioBoxGroups = new ArrayList<>();
    public ArrayList<JTextArea> textAreas = new ArrayList<>();
    public ArrayList<JTable> tables = new ArrayList<>();

    public CDialogNotFrame(String title, int width, int height, boolean modal){
        super();
        setTitle(title);
        setModal(modal);
        setSize(width, height);
        panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
    }


    public JButton addButton(int w, int h, int x, int y, String title){
        JButton button = DialogComponentСreator.addButton(panel, w, h, x, y, title);
        setContentPane(panel);
        buttons.add(button);
        return button;
    }


    public void addTextField(int w, int h, int x, int y){
        JTextField textField = DialogComponentСreator.addTextField(panel, w, h, x, y);
        setContentPane(panel);
        textFields.add(textField);
    }

    public void addPasswordField(int w, int h, int x, int y){
        JPasswordField passwordField = DialogComponentСreator.addPasswordField(panel, w, h, x, y);
        setContentPane(panel);
        passwordFields.add(passwordField);
    }


    public JLabel addLabel(int w, int h, int x, int y, String title){
        JLabel label = DialogComponentСreator.addLabel(panel, w, h, x, y, title);
        setContentPane(panel);
        label.setForeground(Color.WHITE);
        labels.add(label);
        return  label;
    }

    public void addPhoto(int w, int h, int x, int y, String title){
        JLabel label = DialogComponentСreator.addPhoto(panel, w, h, x, y, title);
        setContentPane(panel);
        photos.add(label);
    }

    public void addCheckBox(int w, int h, int x, int y, String title, boolean slected){
        JCheckBox checkBox = DialogComponentСreator.addCheckBox(panel, w, h, x, y, title, slected);
        setContentPane(panel);
        checkBoxes.add(checkBox);
    }

    public void addTextArea(int w, int h, int x, int y){
        JTextArea textArea = DialogComponentСreator.addTextArea(panel, w, h, x, y);
        setContentPane(panel);
        textAreas.add(textArea);
    }


    public void addRadioButtonGroupe(int w, int h, int x, int y, String[] titles){
        JRadioButton[] radioButtons = DialogComponentСreator.addRadioButtonGroupe(panel, w, h, x, y, titles);
        for (JRadioButton b :
                radioButtons) {
            b.setBackground(Color.BLACK);
            b.setForeground(Color.WHITE);
        }
        setContentPane(panel);
        radioBoxGroups.add(radioButtons);
    }

    public void addComboBox(boolean isEditable, int w, int h, int x, int y){
        JComboBox comboBox = DialogComponentСreator.addComboBox(panel, isEditable, w, h, x, y);
        setContentPane(panel);
        comboBoxes.add(comboBox);
    }


   public void addTable(int w, int h, int x, int y, AbstractTableModel model){
       JTable table = DialogComponentСreator.addTable(panel, w, h, x, y, model);          //new UserTableModel(models)

       setContentPane(panel);
       tables.add(table);
    }


}




