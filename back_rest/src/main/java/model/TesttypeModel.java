package model;

public class TesttypeModel implements Model {
    public final static String[] COLUMNS_BY_RUS = {"ИД", "Название", "Родительская"};
    public final static String[] COLUMNS = {"name", "parent_testtype_id"};
    public final static boolean[] IS_STR = {true, true};
    public final static String[] REQUIRED_COLUMN = {"name"};

    private int id;
    private String name;
    private String parent_testtype_id;

    public String[] getConstColumnsRus(){return COLUMNS_BY_RUS;}
    public String[] getConstColumns(){return COLUMNS;}
    public boolean[] getConstIsSTR(){return IS_STR;}

    public TesttypeModel(int id, String name, String parent_testtype_id){
        this.id = id;
        this.name = name;
        this.parent_testtype_id = parent_testtype_id;
    }

    public TesttypeModel(int id, String name){
        this.id = id;
        this.name = name;
    }
    public TesttypeModel(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParent_testtype_id() {
        return parent_testtype_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent_testtype_id(String parent_testtype_id) {
        this.parent_testtype_id = parent_testtype_id;
    }

}
