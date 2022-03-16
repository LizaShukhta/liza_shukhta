package table_models;

import model.Model;
import model.TesterModel;
import model.TesterTestcaseModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TesterTestcaseTableModel extends AbstractTableModel {
    private ArrayList<TesterTestcaseModel> testerTestcases;

    public ArrayList<TesterTestcaseModel> getTesterTestcases() {
        return testerTestcases;
    }

    public TesterTestcaseTableModel(ArrayList<TesterTestcaseModel> testerTestcases) {
        super();
        this.testerTestcases = testerTestcases;
    }

    public TesterTestcaseTableModel(ArrayList<Model> models, boolean isAbstract) {
        super();
        this.testerTestcases = Converter.modelToTesterTestcase(models);
    }

    @Override
    public int getRowCount() {
        return testerTestcases.size();
    }

    @Override
    public int getColumnCount() {
        return TesterTestcaseModel.COLUMNS_BY_RUS.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c){
            default: case 0: return testerTestcases.get(r).getId();
            case 1: return testerTestcases.get(r).getTester_id();
            case 2: return testerTestcases.get(r).getTestcase_id();
        }
    }

    @Override
    public String getColumnName(int c) {
        return TesterTestcaseModel.COLUMNS_BY_RUS[c];
    }

}
