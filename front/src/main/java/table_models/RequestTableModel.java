package table_models;

import model.Model;
import model.RequestModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class RequestTableModel extends AbstractTableModel {
    private ArrayList<RequestModel> requests;

    public ArrayList<RequestModel> getRequests() {
        return requests;
    }

    public RequestTableModel(ArrayList<RequestModel> requests) {
        super();
        this.requests = requests;
    }

    public RequestTableModel(ArrayList<Model> models, boolean isAbstract) {
        super();
        this.requests = Converter.modelToRequest(models);
    }

    @Override
    public int getRowCount() {
        return requests.size();
    }

    @Override
    public int getColumnCount() {
        return RequestModel.COLUMNS_BY_RUS.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c){
            default:case 0: return requests.get(r).getId();
            case 1: return requests.get(r).getDate();
            case 2: return requests.get(r).getPhone();
            case 3: return requests.get(r).getMail();
            case 4: return requests.get(r).getImportance();
            case 5: return requests.get(r).getTestcase_id();
            case 6: return requests.get(r).getUser_id();
        }
    }

    @Override
    public String getColumnName(int c) {
        return RequestModel.COLUMNS_BY_RUS[c];
    }

}
