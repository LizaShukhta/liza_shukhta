package model;

public class UserTesttypeModel implements Model {
    public final static String[] COLUMNS_BY_RUS = {"ИД", "Пользователь", "ВидТестирования"};
    public final static String[] COLUMNS = {"user_id", "testtype_id" };
    public final static boolean[] IS_STR = { true, true};

    private int id;
    private String user_id;
    private String testtype_id;

    public String[] getConstColumnsRus(){return COLUMNS_BY_RUS;}
    public String[] getConstColumns(){return COLUMNS;}
    public boolean[] getConstIsSTR(){return IS_STR;}

    public UserTesttypeModel(int id, String user_id, String testtype_id){
        this.id = id;
        this.user_id = user_id;
        this.testtype_id = testtype_id;
    }

    public UserTesttypeModel(){}

    public int getId(){
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getTesttype_id() {
        return testtype_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setTesttype_id(String testtype_id) {
        this.testtype_id = testtype_id;
    }
}
