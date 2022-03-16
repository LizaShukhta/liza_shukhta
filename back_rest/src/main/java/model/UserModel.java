package model;

public class UserModel implements Model {
    public final static String[] COLUMNS_BY_RUS = {"ИД", "Логин", "Пароль", "Роль"};
    public final static String[] COLUMNS = {"login", "password", "role"};
    public final static boolean[] IS_STR = {true, true, true};

    private int id;
    private String login;
    private String password;
    private String role;

    public String[] getConstColumnsRus(){return COLUMNS_BY_RUS;}
    public String[] getConstColumns(){return COLUMNS;}
    public boolean[] getConstIsSTR(){return IS_STR;}

    public UserModel(int id, String login, String password, String role){
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public UserModel(){}

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
