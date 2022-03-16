package model;

public interface Model {
    void setId(int id);
    int getId();
    String[] getConstColumnsRus();
    String[] getConstColumns();
    boolean[] getConstIsSTR();
}
