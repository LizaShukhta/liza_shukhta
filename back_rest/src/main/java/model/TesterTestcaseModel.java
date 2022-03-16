package model;

public class TesterTestcaseModel implements Model {
    public final static String[] COLUMNS_BY_RUS = {"ИД", "Тестировщик", "Тесткейс"};
    public final static String[] COLUMNS = {"tester_id", "testcase_id" };
    public final static boolean[] IS_STR = {true, true};

    private int id;
    private String tester_id;
    private String testcase_id;

    public String[] getConstColumnsRus(){return COLUMNS_BY_RUS;}
    public String[] getConstColumns(){return COLUMNS;}
    public boolean[] getConstIsSTR(){return IS_STR;}

    public TesterTestcaseModel(int id, String tester_id, String testcase_id){
        this.id = id;
        this.tester_id = tester_id;
        this.testcase_id = testcase_id;
    }

    public TesterTestcaseModel(){}

    public int getId(){
        return id;
    }


    public String getTester_id() {
        return tester_id;
    }

    public String getTestcase_id() {
        return testcase_id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTester_id(String tester_id) {
        this.tester_id = tester_id;
    }

    public void setTestcase_id(String testcase_id) {
        this.testcase_id = testcase_id;
    }

}
