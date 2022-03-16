package table_models;

import model.Model;
import model.TesterModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TesterTableModel extends AbstractTableModel {
    private ArrayList<TesterModel> testers;

    public ArrayList<TesterModel> getTesters() {
        return testers;
    }

    public TesterTableModel(ArrayList<TesterModel> testers) {
        super();
        this.testers = testers;
    }

    public TesterTableModel(ArrayList<Model> models, boolean isAbstract) {
        super();
        this.testers = Converter.modelToTester(models);
    }

    @Override
    public int getRowCount() {
        return testers.size();
    }

    @Override
    public int getColumnCount() {
        return TesterModel.COLUMNS_BY_RUS.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c){
            default:case 0: return testers.get(r).getId();
            case 1: return testers.get(r).getName();
            case 2: return testers.get(r).getSpeciality();
            case 3: return testers.get(r).getDate_of_birth();
        }
    }

    @Override
    public String getColumnName(int c) {
        return TesterModel.COLUMNS_BY_RUS[c];
    }

}
