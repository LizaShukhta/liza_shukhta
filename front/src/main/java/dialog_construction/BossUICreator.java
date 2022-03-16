package dialog_construction;

import connection.DataPreparer;
import model.UserModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BossUICreator extends TesterUICreator {

    public BossUICreator(UserModel currentUser) {
        super(currentUser);
    }

    @Override
    public void prepareFrameDialog() {
        super.prepareFrameDialog();
    }

    @Override
    protected void createMenu(JMenuBar menuBar) {
        super.createMenu(menuBar);
        JMenu menuChange = new  JMenu("Руководитель");
        JMenuItem item1 = new JMenuItem("Управление пользователями");
        setEventUser(item1);
        menuChange.add(item1);

        menuBar.add(menuChange);
    }

    protected void setEventUser(JMenuItem item){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OneTableDialog testtypeDialog = new OneTableDialog(frameDialog, UserModel.class, "\"Пользователи\"");
                testtypeDialog.create();
            }
        };
        item.addActionListener(actionListener);
    }
}
