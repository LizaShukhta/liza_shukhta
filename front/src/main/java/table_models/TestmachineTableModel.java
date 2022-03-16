package table_models;

import model.TestmachineModel;
import model.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TestmachineTableModel extends AbstractTableModel {
    private ArrayList<TestmachineModel> testmachines;

    public ArrayList<TestmachineModel> getTestmachines() {
        return testmachines;
    }

    public TestmachineTableModel(ArrayList<TestmachineModel> testmachines) {
        super();
        this.testmachines = testmachines;
    }

    public TestmachineTableModel(ArrayList<Model> models, boolean isAbstract) {
        super();
        this.testmachines = Converter.modelToTestmachine(models);
    }

    @Override
    public int getRowCount() {
        return testmachines.size();
    }

    @Override
    public int getColumnCount() {
        return TestmachineModel.COLUMNS_BY_RUS.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c){
            default:case 0: return testmachines.get(r).getId();
            case 1: return testmachines.get(r).getName();
            case 2: return testmachines.get(r).getIpipaddress();
        }
    }

    @Override
    public String getColumnName(int c) {
        return TestmachineModel.COLUMNS_BY_RUS[c];
    }

}
