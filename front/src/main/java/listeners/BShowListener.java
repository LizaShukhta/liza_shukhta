package listeners;

import dialogs.CDialog;
import dialogs.CDialogNotFrame;
import dialogs.DialogComponentСreator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class BShowListener implements ActionListener {
    private CDialogNotFrame dialogShow;
    private CDialog frameDialog;
    private static int PHOTO_NUMBER = 1;

    public BShowListener(CDialog frameDialog){
        super();
        this.frameDialog = frameDialog;
    }

    public void actionPerformed(ActionEvent e) {
        prepareShowData();
        setEvents();
        dialogShow.setVisible(true);
    }


    private void prepareShowData(){
        dialogShow = new CDialogNotFrame("Наши преимущества", 600, 450, true);
        dialogShow.addLabel(200, 30, 50, 0, "Преимущества:");
        dialogShow.addPhoto(450, 350, 60, 50, BShowListener.PHOTO_NUMBER + ".jpg");
        dialogShow.addButton(50, 30,5, 210, "<");
        dialogShow.addButton(50, 30,530, 210, ">");
    }


    private void setEvents(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals(">")){
                    BShowListener.PHOTO_NUMBER = BShowListener.PHOTO_NUMBER == 5 ? 1 : BShowListener.PHOTO_NUMBER + 1;
                } else {
                    BShowListener.PHOTO_NUMBER = BShowListener.PHOTO_NUMBER == 1 ? 5 : BShowListener.PHOTO_NUMBER - 1;
                }
                URL url = DialogComponentСreator.setIcon(dialogShow.photos.get(0), BShowListener.PHOTO_NUMBER + ".jpg");
            }
        };
        dialogShow.buttons.get(0).addActionListener(actionListener);
        dialogShow.buttons.get(1).addActionListener(actionListener);
    }



}
