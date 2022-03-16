package table_models;

import model.TesttypeModel;
import model.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TesttypeTableModel extends AbstractTableModel {
    private ArrayList<TesttypeModel> testtypes;

    public ArrayList<TesttypeModel> getTesttypes() {
        return testtypes;
    }

    public TesttypeTableModel(ArrayList<TesttypeModel> testtypes) {
        super();
        this.testtypes = testtypes;
    }

    public TesttypeTableModel(ArrayList<Model> models, boolean isAbstract) {
        super();
        this.testtypes = Converter.modelToTesttype(models);
    }

    @Override
    public int getRowCount() {
        return testtypes.size();
    }

    @Override
    public int getColumnCount() {
        return TesttypeModel.COLUMNS_BY_RUS.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c){
            default:case 0: return testtypes.get(r).getId();
            case 1: return testtypes.get(r).getName();
            case 2: return testtypes.get(r).getParent_testtype_id();
        }
    }

    @Override
    public String getColumnName(int c) {
        return TesttypeModel.COLUMNS_BY_RUS[c];
    }

}
