package model;

public class TesterModel implements Model {
    public final static String[] COLUMNS_BY_RUS = {"ИД", "ФИО", "Специализация", "ДатаРождения"};
    public final static String[] COLUMNS = {"name", "speciality", "date_of_birth"};
    public final static boolean[] IS_STR = {true, true, true};

    private int id;
    private String name;
    private String speciality;
    private String date_of_birth;

    public String[] getConstColumnsRus(){return COLUMNS_BY_RUS;}
    public String[] getConstColumns(){return COLUMNS;}
    public boolean[] getConstIsSTR(){return IS_STR;}

    public TesterModel(int id, String name, String speciality, String date_of_birth){
        this.id = id;
        this.name = name;
        this.speciality = speciality;
        this.date_of_birth = date_of_birth;
    }

    public TesterModel(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}
