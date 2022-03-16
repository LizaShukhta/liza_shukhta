package table_models;

import model.Model;
import model.TesterTestcaseModel;
import model.UserTesttypeModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class UserTesttypeTableModel extends AbstractTableModel {
    private ArrayList<UserTesttypeModel> userTesttypes;

    public ArrayList<UserTesttypeModel> getUserTesttypes() {
        return userTesttypes;
    }

    public UserTesttypeTableModel(ArrayList<UserTesttypeModel> userTesttypes) {
        super();
        this.userTesttypes = userTesttypes;
    }

    public UserTesttypeTableModel(ArrayList<Model> models, boolean isAbstract) {
        super();
        this.userTesttypes = Converter.modelToUserTesttype(models);
    }

    @Override
    public int getRowCount() {
        return userTesttypes.size();
    }

    @Override
    public int getColumnCount() {
        return UserTesttypeModel.COLUMNS_BY_RUS.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c){
            default: case 0: return userTesttypes.get(r).getId();
            case 1: return userTesttypes.get(r).getUser_id();
            case 2: return userTesttypes.get(r).getTesttype_id();
        }
    }

    @Override
    public String getColumnName(int c) {
        return UserTesttypeModel.COLUMNS_BY_RUS[c];
    }

}
