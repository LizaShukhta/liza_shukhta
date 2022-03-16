package table_models;

import model.*;

import java.util.ArrayList;

public class Converter {
    public static ArrayList<TesttypeModel> modelToTesttype(ArrayList<Model> models){
        if(models == null) return null;

        ArrayList<TesttypeModel> testtypes = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            testtypes.add((TesttypeModel) models.get(i));
        }

        return testtypes;
    }

    public static ArrayList<TestcaseModel> modelToTestcase(ArrayList<Model> models){
        if(models == null) return null;

        ArrayList<TestcaseModel> testcases = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            testcases.add((TestcaseModel) models.get(i));
        }

        return testcases;
    }

    public static ArrayList<RequestModel> modelToRequest(ArrayList<Model> models){
        if(models == null) return null;

        ArrayList<RequestModel> requests = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            requests.add((RequestModel) models.get(i));
        }

        return requests;
    }

    public static ArrayList<TesterModel> modelToTester(ArrayList<Model> models){
        if(models == null) return null;

        ArrayList<TesterModel> requests = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            requests.add((TesterModel) models.get(i));
        }

        return requests;
    }

    public static ArrayList<UserModel> modelToUser(ArrayList<Model> models){
        if(models == null) return null;

        ArrayList<UserModel> users = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            users.add((UserModel) models.get(i));
        }

        return users;
    }

    public static ArrayList<TestmachineModel> modelToTestmachine(ArrayList<Model> models){
        if(models == null) return null;

        ArrayList<TestmachineModel> testmachines = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            testmachines.add((TestmachineModel) models.get(i));
        }

        return testmachines;
    }

    public static ArrayList<TesterTestcaseModel> modelToTesterTestcase(ArrayList<Model> models){
        if(models == null) return null;

        ArrayList<TesterTestcaseModel> testmachines = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            testmachines.add((TesterTestcaseModel) models.get(i));
        }

        return testmachines;
    }

    public static ArrayList<UserTesttypeModel> modelToUserTesttype(ArrayList<Model> models){
        if(models == null) return null;

        ArrayList<UserTesttypeModel> testmachines = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            testmachines.add((UserTesttypeModel) models.get(i));
        }

        return testmachines;
    }
}
