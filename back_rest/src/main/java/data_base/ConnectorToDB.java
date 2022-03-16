package data_base;

import model.*;
import validation.Validator;

import java.sql.*;
import java.util.ArrayList;

public class ConnectorToDB {
    private static ConnectorToDB ourInstance = new ConnectorToDB();
    private String userName;
    private String password;
    private String connectionURL;
    public String lastError = "";

    public static ConnectorToDB getInstance() {
        return ourInstance;
    }

    private ConnectorToDB() {
        lastError = "";
        try {
            userName = "root";
            password = "root";
            connectionURL = "jdbc:mysql://localhost:3306/graduate_work?useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            lastError = "MySQL Error: \"" + e.getClass() + "\"";
        }
    }

    private boolean queryNotReturn(String query, String[] params, boolean[] isString){
        try(
                Connection connection = DriverManager.getConnection(connectionURL, userName, password);
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            for (int i = 0; i < params.length; i++) {
                if(!isString[i]){
                    setSpecial(statement, i+1, params[i]);
                } else {
                    if(params[i].equals("NULL"))
                        statement.setNull(i+1, Types.NULL);
                    else
                        statement.setString(i+1, params[i]);
                }
            }
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            lastError = "MySQL Error: \"" + e.getClass() + "\"";
            return false;
        }
    }

    private void setSpecial(PreparedStatement statement, int id, String data) throws SQLException {
        if(!Validator.isInt(data)) {
            if (Validator.isDouble(data))
                statement.setDouble(id, Double.valueOf(data));
        } else statement.setInt(id, Integer.valueOf(data));
    }

    public boolean insert (String table, String columns, String values, String[] arrayData, boolean[] isStr){
        lastError = "";
        String query = "insert into `" + table + "`(" + columns + ") values (" + values + ")";
        return queryNotReturn(query, arrayData, isStr);
    }

    public boolean update (String table, int id, String values, String[] arrayData, boolean[] isStr){
        lastError = "";
        String query = "update `" + table + "` set " + values + " where id = ?";
        return queryNotReturn(query, getAllStr(arrayData, id), getAllBool(isStr, false));

    }

    private String[] getAllStr(String[] arrayData, int id){
        String[] array = new String[arrayData.length+1];
        for (int i = 0; i < arrayData.length; i++) {
            array[i] = arrayData[i];
        }
        array[array.length-1] = String.valueOf(id);
        return array;
    }

    private boolean[] getAllBool(boolean[] arrayData, boolean id){
        boolean[] array = new boolean[arrayData.length+1];
        for (int i = 0; i < arrayData.length; i++) {
            array[i] = arrayData[i];
        }
        array[array.length-1] = id;
        return array;
    }

    public boolean delete (String table, int id){
        lastError = "";
        String query = "delete from `" + table + "` where id = ?"; //+ id;
        return queryNotReturn(query, new String[]{String.valueOf(id)}, new boolean[]{false});
    }

    private void setSelectParam(PreparedStatement statement, String param, String isInt) throws SQLException {
        if(isInt.equals("true")){
            if(!Validator.isInt(param)) statement.setDouble( 1, Double.valueOf(param));
            else statement.setInt( 1, Integer.valueOf(param));
        } else {
            statement.setString(1, param);
        }
    }

    public ArrayList<Model> select(String table , String where, String orderBy, String param, String isInt, String[] join) {
        lastError = "";
        if(!("".equals(orderBy.trim()))) orderBy = "order by " + orderBy;
        if(!("".equals(where.trim()))) where = "where " + where;

        String query = "select " + join[0] + " from `" + table + "` " + join[1] + " " + where + " " + orderBy;
        System.out.println(query);
        try(
                Connection connection = DriverManager.getConnection(connectionURL, userName, password);
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            isInt = where.indexOf("LIKE") == -1 ? isInt : "false";
            if(!("".equals(where.trim()))) setSelectParam(statement, param, isInt);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Model> testtypes = new ArrayList<>();
            while (resultSet.next()) testtypes.add(resToModel(resultSet, table));
            statement.close();
            connection.close();
            return testtypes;
        } catch (Exception e){
            lastError = "MySQL Error: \"" + e.getClass() + "\"";
            return null;
        }
    }

    public ArrayList<Model> selectByQuery(String query, String table, String param, String isInt) {
        lastError = "";
        try(
                Connection connection = DriverManager.getConnection(connectionURL, userName, password);
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            if(!("".equals(param.trim()))) setSelectParam(statement,  param,  isInt);

            ArrayList<Model> currentModels = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                currentModels.add(resToModel(resultSet, table));
            }
            return currentModels;
        } catch (Exception e){
            e.printStackTrace();
            lastError = "MySQL Error: \"" + e.getClass() + "\"";
            return null;
        }
    }

    private Model resToModel(ResultSet resultSet, String table){
        try {
            switch (table) {
                case "testtypes":               return resToTesttypeModel(resultSet);
                case "testcases":                return resToTestcaseModel(resultSet);
                case "requests":               return resToRequestModel(resultSet);
                case "testers":               return resToTesterModel(resultSet);
                case "users":                   return resToUserModel(resultSet);
                default: case "testmachines":     return resToTestmachineModel(resultSet);
                case "tester_testcases":        return resToTesterTestcaseModel(resultSet);
                case "user_testtypes":         return restToUserTesttype(resultSet);
            }
        } catch (Exception e){
            lastError = "MySQL Error: \"" + e.getClass() + "\"";
            return null;
        }
    }

    private TesttypeModel resToTesttypeModel(ResultSet resultSet) throws Exception {
        TesttypeModel testtype = new TesttypeModel(
            resultSet.getInt(1),
            resultSet.getString(2),
            this.createParentTesttypeId(resultSet.getInt(3), resultSet.getString(4))
        );
        return testtype;
    }

    private String createParentTesttypeId(int id, String name){
        if(id == 0){
            return id + ") Верхоуровневая";
        } else {
            return id + ") " + name;
        }
    }

    private TestcaseModel resToTestcaseModel(ResultSet resultSet) throws Exception {
        TestcaseModel testcase = new TestcaseModel(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getDouble(4),
            resultSet.getInt(5),
            resultSet.getString(6),
            resultSet.getInt(7) + ") " + resultSet.getString(9),
            resultSet.getInt(8) + ") " + resultSet.getString(10)
        );
        return testcase;
    }

    private RequestModel resToRequestModel(ResultSet resultSet) throws Exception {
        RequestModel request = new RequestModel(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4),
            resultSet.getInt(5),
            resultSet.getInt(6) + ") " + resultSet.getString(8),
            resultSet.getInt(7) + ") " + resultSet.getString(9)
        );
        return request;
    }

    private TesterModel resToTesterModel(ResultSet resultSet) throws Exception {
        TesterModel tester = new TesterModel(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4)
        );
        return tester;
    }

    private UserModel resToUserModel(ResultSet resultSet) throws Exception {
        UserModel user = new UserModel(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4)
        );
        return user;
    }

    private TestmachineModel resToTestmachineModel(ResultSet resultSet) throws Exception {
        TestmachineModel testmachine = new TestmachineModel(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3)
        );
        return testmachine;
    }

    private TesterTestcaseModel resToTesterTestcaseModel(ResultSet resultSet) throws Exception {
        TesterTestcaseModel testerTestcase = new TesterTestcaseModel(
                resultSet.getInt(1),
                resultSet.getInt(2) + ") " + resultSet.getString(4),
                resultSet.getInt(3) + ") " + resultSet.getString(5)
        );
        return testerTestcase;
    }

    private UserTesttypeModel restToUserTesttype(ResultSet resultSet) throws Exception {
        UserTesttypeModel userTesttype = new UserTesttypeModel(
                resultSet.getInt(1),
                resultSet.getInt(2) + ") " + resultSet.getString(4),
                resultSet.getInt(3) + ") " + resultSet.getString(5)
        );
        return userTesttype;
    }

}
