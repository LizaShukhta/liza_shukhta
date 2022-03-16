package model;

public class TestmachineModel implements Model {
    public final static String[] COLUMNS = {"name", "ipaddress"};
    public final static String[] COLUMNS_BY_RUS = {"ИД", "Название", "IP-Адрес"};
    public final static boolean[] IS_STR = {true, true};

    private int id;
    private String name;
    private String ipaddress;

    public String[] getConstColumnsRus(){return COLUMNS_BY_RUS;}
    public String[] getConstColumns(){return COLUMNS;}
    public boolean[] getConstIsSTR(){return IS_STR;}

    public TestmachineModel(int id, String name, String ipaddress){
        this.id = id;
        this.name = name;
        this.ipaddress = ipaddress;
    }

    public TestmachineModel(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIpipaddress() {
        return ipaddress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIpipaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
}
