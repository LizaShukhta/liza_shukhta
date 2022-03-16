package connection;

import com.google.gson.reflect.TypeToken;
import model.*;
import serialization.Serrializator;

import java.util.ArrayList;


public class DataPreparer {
    private static HTTPQuerier connector;

    public static String startConnection(String host, int port){
        connector = new HTTPQuerier(host, port);
        return connector.isHaveError ? connector.errorMessage : "";
    }

    public static UserModel checkAuthorization(UserModel userModel) {
        String userJSON = Serrializator.ObjectToJSON(userModel), command = "authorization", answer;
        answer = connector.writeCommand(command, userJSON);
        return  !("".equals(answer)) ? (UserModel)Serrializator.JSONToObject(answer, UserModel.class) : null;
    }

    public static UserModel checkRegistry(UserModel userModel) {
        insert(userModel, "users");
        try {
            return (UserModel) getUsers("id desc", "").get(0);
        } catch (Exception ex){
            return new UserModel();
        }
    }

    public static ArrayList<Model> getTesttypes(String orderBy, String where){
        String answer = getModelsFromServer("getTesttypes", orderBy, where);
        return !("".equals(answer)) ? Serrializator.JSONToObjects(answer, new TypeToken<ArrayList<TesttypeModel>>(){}.getType()) : null;
    }

    public static ArrayList<Model> getTestcases(String orderBy, String where){
        String answer = getModelsFromServer("getTestcases", orderBy, where);
        return !("".equals(answer)) ? Serrializator.JSONToObjects(answer, new TypeToken<ArrayList<TestcaseModel>>(){}.getType()) : null;
    }

    public static ArrayList<Model> getRequests(String orderBy, String where){
        String answer = getModelsFromServer("getRequests", orderBy, where);
        return !("".equals(answer)) ? Serrializator.JSONToObjects(answer, new TypeToken<ArrayList<RequestModel>>(){}.getType()) : null;
    }

    public static ArrayList<Model> getTesters(String orderBy, String where){
        String answer = getModelsFromServer("getTesters", orderBy, where);
        return !("".equals(answer)) ? Serrializator.JSONToObjects(answer, new TypeToken<ArrayList<TesterModel>>(){}.getType()) : null;
    }

    public static ArrayList<Model> getUsers(String orderBy, String where){
        String answer = getModelsFromServer("getUsers", orderBy, where);
        return !("".equals(answer)) ? Serrializator.JSONToObjects(answer, new TypeToken<ArrayList<UserModel>>(){}.getType()) : null;
    }

    public static ArrayList<Model> getTestmachines(String orderBy, String where){
        String answer = getModelsFromServer("getTestmachines", orderBy, where);
        return !("".equals(answer)) ? Serrializator.JSONToObjects(answer, new TypeToken<ArrayList<TestmachineModel>>(){}.getType()) : null;
    }

    public static ArrayList<Model> getTestersTestcases(String orderBy, String where){
        String answer = getModelsFromServer("getTestersTestcases", orderBy, where);
        return !("".equals(answer)) ? Serrializator.JSONToObjects(answer, new TypeToken<ArrayList<TesterTestcaseModel>>(){}.getType()) : null;
    }

    public static ArrayList<Model> getUserTesttypes(String orderBy, String where){
        String answer = getModelsFromServer("getUserTesttypes", orderBy, where);
        return !("".equals(answer)) ? Serrializator.JSONToObjects(answer, new TypeToken<ArrayList<UserTesttypeModel>>(){}.getType()) : null;
    }

    private static String getModelsFromServer(String command, String orderBy, String where) {
        String data = "";
        if(!orderBy.trim().equals("") || !where.trim().equals("")) {
            data = orderBy + " @@@ " + where;
        }
        return connector.writeCommand(command, data);
    }


    public static String insert(Model model, String table){
        String command = "insert_", data;
        data = Serrializator.ObjectToJSON(model);
        return connector.writeCommand(command + table, data);
    }

    public static String update(Model model, String table, int id){
        String command = "update_", data;
        data = Serrializator.ObjectToJSON(model) + "@@@" + id;
        return connector.writeCommand(command + table, data);
    }

    public static String delete(int id, String table){
        String command = "delete", data;
        data = table + "@@@" + id;
        return connector.writeCommand(command, data);
    }

    public static String exit(String message){
        return connector.closeSocket(message);
    }

}
