package model;

public class RequestModel implements Model {
    public final static String[] COLUMNS_BY_RUS = {"ИД", "Дата", "Телефон", "Почта", "Приоритетность", "Тесткейс", "Пользователь"};
    public final static String[] COLUMNS = {"date", "phone", "mail", "importance", "testcase_id", "user_id"};
    public final static boolean[] IS_STR = {true, true, true, false, true, true};

    private int id;
    private String date;
    private String phone;
    private String mail;
    private int importance;
    private String testcase_id;
    private String user_id;

    public String[] getConstColumnsRus(){return COLUMNS_BY_RUS;}
    public String[] getConstColumns(){return COLUMNS;}
    public boolean[] getConstIsSTR(){return IS_STR;}

    public RequestModel(int id, String date, String phone, String mail, int importance, String testcase_id, String user_id){
        this.id = id;
        this.date = date;
        this.phone = phone;
        this.mail = mail;
        this.importance = importance;
        this.testcase_id = testcase_id;
        this.user_id = user_id;

    }

    public RequestModel(){}

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public String getTestcase_id() {
        return testcase_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public int getImportance() {
        return importance;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTestcase_id(String testcase_id) {
        this.testcase_id = testcase_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

}
