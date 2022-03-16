package table_models;

import model.Model;
import model.TestcaseModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TestcaseTableModel extends AbstractTableModel {
    private ArrayList<TestcaseModel> testcases;

    public ArrayList<TestcaseModel> getTestcases(){
        return testcases;
    }

    public TestcaseTableModel(ArrayList<TestcaseModel> testcases) {
        super();
        this.testcases = testcases;
    }

    public TestcaseTableModel(ArrayList<Model> models, boolean isAbstract) {
        super();
        this.testcases = Converter.modelToTestcase(models);
    }

    @Override
    public int getRowCount() {
        return testcases.size();
    }

    @Override
    public int getColumnCount() {
        return TestcaseModel.COLUMNS_BY_RUS.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c){
            default:case 0: return testcases.get(r).getId();
            case 1: return testcases.get(r).getName();
            case 2: return testcases.get(r).getDescription();
            case 3: return testcases.get(r).getPrice();
            case 4: return testcases.get(r).getDuration();
            case 5: return testcases.get(r).getAurimatization();
            case 6: return testcases.get(r).getTesttype_id();
            case 7: return testcases.get(r).getTestmachine_id();
        }
    }

    @Override
    public String getColumnName(int c) {
        return TestcaseModel.COLUMNS_BY_RUS[c];
    }
}
