package model;

public class TestcaseModel implements Model {
    public final static String[] COLUMNS_BY_RUS = {"ИД", "Название", "Описание", "Трудозатраты", "Продолжительность",
            "Автоматизация", "ВидТестирования", "Тестовая машина"};
    public final static String[] COLUMNS = {"name", "description", "price", "duration", "aurimatization",
            "testtype_id", "testmachine_id"};
    public final static boolean[] IS_STR = {true, true, false, false, true, true, true};

    private int id;
    private String name;
    private String description;
    private double price;
    private int duration;
    private String aurimatization;
    private String testtype_id;
    private String testmachine_id;

    public String[] getConstColumnsRus(){return COLUMNS_BY_RUS;}
    public String[] getConstColumns(){return COLUMNS;}
    public boolean[] getConstIsSTR(){return IS_STR;}

    public TestcaseModel(int id, String name, String description, double pricce, int duration, String aurimatization,
                         String testtype_id, String testmachine_id){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = pricce;
        this.duration = duration;
        this.aurimatization = aurimatization;
        this.testtype_id = testtype_id;
        this.testmachine_id = testmachine_id;
    }

    public TestcaseModel(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    public String getAurimatization() {
        return aurimatization;
    }

    public String getTesttype_id() {
        return testtype_id;
    }

    public String getTestmachine_id() {
        return testmachine_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setAurimatization(String aurimatization) {
        this.aurimatization = aurimatization;
    }

    public void setTesttype_id(String testtype_id) {
        this.testtype_id = testtype_id;
    }

    public void setTestmachine_id(String testmachine_id) {
        this.testmachine_id = testmachine_id;
    }
}
