package listeners;

import connection.DataPreparer;
import dialog_construction.OneTableDialog;
import dialogs.CDialog;
import model.Model;
import validation.Validator;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

abstract class BAbstractEditListener implements ActionListener {
    protected CDialog frameDialog;
    protected Class<? extends Model> currentClass;
    protected ArrayList<Model> models;
    protected OneTableDialog parental;

    public BAbstractEditListener(CDialog frameDialog, Class<? extends Model> currentClass, ArrayList<Model> models, OneTableDialog parental){
        this.frameDialog = frameDialog;
        this.currentClass = currentClass;
        this.models = models;
        this.parental = parental;
    }

    protected boolean isIdCorrect(){
        models = parental.updateData();
        if(Validator.isInt(frameDialog.textFields.get(0).getText())){
            int myId = Integer.valueOf(frameDialog.textFields.get(0).getText());
            for (int i = 0; i < models.size(); i++){
                if(models.get(i).getId() == myId){
                    return true;
                }
            }
        }
        JOptionPane.showMessageDialog(frameDialog, "Введите правильный номер!", "Error!", JOptionPane.PLAIN_MESSAGE);
        return false;
    }



    protected String getQueryUpdate(Model model){
        String answer = DataPreparer.update(model, Validator.getCurrentTable(currentClass.getName()), Integer.valueOf(frameDialog.textFields.get(0).getText()));
        
        if(answer.trim().equals("@ok@")) return "";
        else return answer;
    }

    protected String getQueryInsert(Model model){
        String answer = DataPreparer.insert(model, Validator.getCurrentTable(currentClass.getName()));
        if(answer.trim().equals("@ok@")) return "";
        else return answer;
    }


    protected String getQueryDelete(int id){
        String answer = DataPreparer.delete(id, Validator.getCurrentTable(currentClass.getName()));
        if(answer.trim().equals("@ok@")) return "";
        else return answer;
    }

}
