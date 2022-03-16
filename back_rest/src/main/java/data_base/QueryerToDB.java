package data_base;

import model.*;

import java.util.ArrayList;

public class QueryerToDB {
    private ConnectorToDB connector =  ConnectorToDB.getInstance();
    public String lastError = "";

    public ArrayList<TesttypeModel> getAllTesttypes(String orderBy, String where, String param, String isInt){
        try {
            ArrayList<Model> models;
            ArrayList<TesttypeModel> testtypes = new ArrayList<>();
            String join[] = {
                    " `testtypes`.*, `c`.`name`",
                    " left join `testtypes` as `c` on `testtypes`.`parent_testtype_id` = `c`.`id`"
            };
            models = connector.select("testtypes", where, orderBy, param, isInt, join);

            for (int i = 0; i < models.size(); i++)
                testtypes.add((TesttypeModel) models.get(i));
            return testtypes;
        } catch (Exception e){
            lastError = "Query Error: \"" + e.getClass() + "\"" + connector.lastError;
            return null;
        }
    }

    public ArrayList<TestcaseModel> getAllTestcases(String orderBy, String where, String param, String isInt){
        try {
            ArrayList<Model> models;
            ArrayList<TestcaseModel> testcases = new ArrayList<>();

            orderBy = orderBy.replace("name", "testcases.name");
            where = where.replace("name", "testcases.name");
            String join[] = {
                    " `testcases`.*, `testtypes`.`name`, `testmachines`.`name` ",
                    " join `testtypes` on `testcases`.`testtype_id` = `testtypes`.`id` join `testmachines` on `testcases`.`testmachine_id` = `testmachines`.`id` "
            };
            models = connector.select("testcases", where, orderBy, param, isInt, join);
            for (int i = 0; i < models.size(); i++)
                testcases.add((TestcaseModel) models.get(i));
            return testcases;
        } catch (Exception e){
            lastError = "Query Error: \"" + e.getClass() + "\"" + connector.lastError;
            return null;
        }
    }



    public ArrayList<TesterModel> getAllTesters(String orderBy, String where, String param, String isInt){
        try {
            ArrayList<Model> models;
            ArrayList<TesterModel> testers = new ArrayList<>();
            models = connector.select("testers", where, orderBy, param, isInt, new String[] {"*", ""});
            for (int i = 0; i < models.size(); i++)
                testers.add((TesterModel) models.get(i));
            return testers;
        } catch (Exception e){
            lastError = "Query Error: \"" + e.getClass() + "\"" + connector.lastError;
            return null;
        }
    }

    public ArrayList<RequestModel> getAllRequests(String orderBy, String where, String param, String isInt){
        try {
            ArrayList<Model> models;
            ArrayList<RequestModel> requests = new ArrayList<>();
            String join[] = {
                    " `requests`.*, `testcases`.`name`, `users`.`login` ",
                    " join `testcases` on `requests`.`testcase_id` = `testcases`.`id` join `users` on `requests`.`user_id` = `users`.`id`"
            };
            models = connector.select("requests", where, orderBy, param, isInt, join);
            for (int i = 0; i < models.size(); i++)
                requests.add((RequestModel) models.get(i));
            return requests;
        } catch (Exception e){
            lastError = "Query Error: \"" + e.getClass() + "\"" + connector.lastError;
            return null;
        }
    }


    public ArrayList<UserModel> getAllUsers(String orderBy, String where, String param, String isInt){
        try {
            ArrayList<Model> models;
            ArrayList<UserModel> users = new ArrayList<>();
            models = connector.select("users", where, orderBy, param, isInt, new String[] {"*", ""});

            for (int i = 0; i < models.size(); i++)
                users.add((UserModel) models.get(i));
            return users;
        } catch (Exception e){
            lastError = "Query Error: \"" + e.getClass() + "\"" + connector.lastError;
            return null;
        }
    }


    public ArrayList<TestmachineModel> getAllTestmachines(String orderBy, String where, String param, String isInt){
        try {
            ArrayList<Model> models;
            ArrayList<TestmachineModel> testmachines = new ArrayList<>();
            models = connector.select("testmachines", where, orderBy, param, isInt, new String[] {"*", ""});
            for (int i = 0; i < models.size(); i++)
                testmachines.add((TestmachineModel) models.get(i));
            return testmachines;
        } catch (Exception e){
            lastError = "Query Error: \"" + e.getClass() + "\"" + connector.lastError;
            return null;
        }
    }

    public ArrayList<TesterTestcaseModel> getTesterTestcases(String where, String param, String isInt){
        try {
            ArrayList<Model> models;
            ArrayList<TesterTestcaseModel> testerTestcases = new ArrayList<>();
            where = where.replace("name", "testcases.name");
            param = param.replace("name", "testcases.name");
            isInt = isInt.replace("name", "testcases.name");
            String query = "select tester_testcases.id, tester_testcases.tester_id, tester_testcases.testcase_id, testers.name, testcases.name " +
                    "from (tester_testcases join testers on tester_testcases.tester_id = testers.id) join testcases " +
                    "on tester_testcases.testcase_id = testcases.id ";
            if(!("".equals(where.trim()))) query += " where " + where;
            query += " group by tester_testcases.id";
            models = connector.selectByQuery(query, "tester_testcases", param, isInt);
            for (int i = 0; i < models.size(); i++)
                testerTestcases.add((TesterTestcaseModel) models.get(i));
            return testerTestcases;
        } catch (Exception e){
            lastError = "Query Error: \"" + e.getClass() + "\"" + connector.lastError;
            return null;
        }
    }

    public ArrayList<UserTesttypeModel> getUserTesttypes(String where, String param, String isInt){
        try {
            ArrayList<Model> models;
            ArrayList<UserTesttypeModel> userTesttypes = new ArrayList<>();
            String query = "select user_testtypes.id, user_testtypes.user_id, user_testtypes.testtype_id, users.login, testtypes.name " +
                    "from (user_testtypes join users on user_testtypes.user_id = users.id) join testtypes " +
                    "on user_testtypes.testtype_id = testtypes.id ";
            if(!("".equals(where.trim()))) query += " where " + where;
            query += " group by user_testtypes.id";

            models = connector.selectByQuery(query, "user_testtypes", param, isInt);
            for (int i = 0; i < models.size(); i++)
                userTesttypes.add((UserTesttypeModel) models.get(i));
            return userTesttypes;
        } catch (Exception e){
            lastError = "Query Error: \"" + e.getClass() + "\"" + connector.lastError;
            return null;
        }
    }


    public boolean delete(String table, int id) {
        lastError = "";
        try {
            ConnectorToDB connector =  ConnectorToDB.getInstance();
            if (connector.delete(table,id)) {
                return true;
            } else {
                throw new Exception(connector.lastError);
            }
        } catch (Exception e){
            lastError = "Query Error: \"" + e.getClass() + "\"" + connector.lastError;
            return false;
        }
    }

    public boolean insert(Model model, String table) {
        lastError = "";
        try {
            FieldsTransformation transformator = new FieldsTransformation();
            String values = prepareQueryComponents(model);
            String columns = prepareQueryColumns(model);
            String[] valueByQuery = transformator.newData(model);

            if (connector.insert(table, columns, values, valueByQuery, isStrCurrent(model))) {
                return true;
            } else {
                lastError = connector.lastError;
                return false;
            }
        } catch (Exception e){
            if(e.getMessage().length() > 150) lastError = "Query Error: \"" + e.getClass() + "\"";
            else lastError = "Query Error: \"" + e.getMessage() + "\"";
            return false;
        }
    }

    private String prepareQueryComponents(Model model){
        if(model instanceof TesttypeModel)  return "?,?";
        if(model instanceof TestcaseModel)   return "?,?,?,?,?,?,?";
        if(model instanceof RequestModel)  return "?,?,?,?,?,?";
        if(model instanceof TesterModel)  return "?,?,?";
        if(model instanceof UserModel)      return "?,?,?";
        if(model instanceof TestmachineModel) return "?,?";
        if(model instanceof TesterTestcaseModel)   return "?,?";
        if(model instanceof UserTesttypeModel)      return "?,?";
        return null;
    }

    private String prepareQueryColumns(Model model){
        if(model instanceof TesttypeModel)  return prepareColumns(TesttypeModel.COLUMNS);
        if(model instanceof TestcaseModel)   return prepareColumns(TestcaseModel.COLUMNS);
        if(model instanceof RequestModel)  return prepareColumns(RequestModel.COLUMNS);
        if(model instanceof TesterModel)  return prepareColumns(TesterModel.COLUMNS);
        if(model instanceof UserModel)      return prepareColumns(UserModel.COLUMNS);
        if(model instanceof TestmachineModel) return prepareColumns(TestmachineModel.COLUMNS);
        if(model instanceof TesterTestcaseModel)   return prepareColumns(TesterTestcaseModel.COLUMNS);
        if(model instanceof UserTesttypeModel)      return prepareColumns(UserTesttypeModel.COLUMNS);
        return null;
    }

    private boolean[] isStrCurrent(Model model){
        if(model instanceof TesttypeModel)  return TesttypeModel.IS_STR;
        if(model instanceof TestcaseModel)   return TestcaseModel.IS_STR;
        if(model instanceof RequestModel)  return RequestModel.IS_STR;
        if(model instanceof TesterModel)  return TesterModel.IS_STR;
        if(model instanceof UserModel)      return UserModel.IS_STR;
        if(model instanceof TestmachineModel) return TestmachineModel.IS_STR;
        if(model instanceof TesterTestcaseModel)   return TesterTestcaseModel.IS_STR;
        if(model instanceof UserTesttypeModel)      return UserTesttypeModel.IS_STR;
        return null;
    }

    private String prepareColumns(String columns[]){
        String str = "";
        for (int i = 0; i < columns.length; i++) {
            str += columns[i];
            if(i != columns.length - 1) str +=",";
        }
        return str;
    }


    public boolean update(Model model, String table, int id) {
        lastError = "";
        try {
            FieldsTransformation transformator = new FieldsTransformation();
            String[] valueByQuery = transformator.newData(model);

            String values = prepareQueryValue(model);
            if (connector.update(table, id, values, valueByQuery, isStrCurrent(model))) {
                return true;
            } else {
                lastError = connector.lastError;
                return false;
            }
        } catch (Exception e){
            if(e.getMessage().length() > 150) lastError = "Query Error: \"" + e.getClass() + "\"";
            else lastError = "Query Error: \"" + e.getMessage() + "\"";
            return false;
        }
    }

    private String prepareQueryValue(Model model){

        if(model instanceof TesttypeModel){
            return prepareTesttypeValue();
        }
        if(model instanceof TestcaseModel){
            return prepareTestcaseValue();
        }
        if(model instanceof RequestModel){
            return prepareRequestValue();
        }
        if(model instanceof TesterModel){
            return prepareTesterValue();
        }
        if(model instanceof UserModel){
            return prepareUserValue();
        }
        if(model instanceof TestmachineModel){
            return prepareTestmachineValue();
        }
        if(model instanceof TesterTestcaseModel){
            return prepareTesterTestcaseValue();
        }
        if(model instanceof UserTesttypeModel){
            return prepareUserTesttypeValue();
        }
        return "";
    }

    private String prepareTesttypeValue(){
        String str;
        str = "name = ? , parent_testtype_id = ?";
        return str;
    }

    private String prepareTestcaseValue(){
        String str;
        str = "name = ?, description = ?, price = ?, duration = ? ,aurimatization = ? " +
                ",testtype_id = ?, testmachine_id = ?";
        return str;
    }

    private String prepareRequestValue(){
        String str;
        str = "date = ?, phone = ?, mail = ?, importance = ?, testcase_id = ?, user_id = ?";
        return str;
    }

    private String prepareTesterValue( ){
        String str;
        str = "name = ?, speciality = ?, date_of_birth = ?";
        return str;
    }

    private String prepareUserValue(){
        String str;
        str = "login = ?, password = ?, role = ?";
        return str;
    }

    private String prepareTestmachineValue(){
        String str;
        str = "name = ?, ipaddress = ?";
        return str;
    }

    private String prepareTesterTestcaseValue(){
        String str;
        str = "tester_id = ?, testcase_id = ?";
        return str;
    }

    private String prepareUserTesttypeValue(){
        String str;
        str = "user_id = ?, testtype_id = ?";
        return str;
    }
}
