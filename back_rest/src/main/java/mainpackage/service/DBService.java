package mainpackage.service;

import model.*;
import org.springframework.stereotype.Service;

@Service("DBService")
public class DBService extends Thread {
    public String runCommand(String command, String data){
        switch (command){
            case "authorization": return OrderPerformer.doAuthorization(data);
            case "getTesttypes": return OrderPerformer.getAll(data, TesttypeModel.class);
            case "getTestcases": return OrderPerformer.getAll(data, TestcaseModel.class);
            case "getRequests": return OrderPerformer.getAll(data, RequestModel.class);
            case "getTesters": return OrderPerformer.getAll(data, TesterModel.class);
            case "getUsers": return OrderPerformer.getAll(data, UserModel.class);
            case "getTestmachines": return OrderPerformer.getAll(data, TestmachineModel.class);
            case "getTestersTestcases": return OrderPerformer.getAll(data, TesterTestcaseModel.class);
            case "getUserTesttypes": return OrderPerformer.getAll(data, UserTesttypeModel.class);
            case "delete": return OrderPerformer.delete(data);
            case "insert_testtypes": return OrderPerformer.insert(data, "testtypes", TesttypeModel.class);
            case "insert_testcases": return OrderPerformer.insert(data, "testcases", TestcaseModel.class);
            case "insert_requests": return OrderPerformer.insert(data, "requests", RequestModel.class);
            case "insert_testers": return OrderPerformer.insert(data, "testers", TesterModel.class);
            case "insert_users": return OrderPerformer.insert(data, "users", UserModel.class);
            case "insert_testmachines": return OrderPerformer.insert(data, "testmachines", TestmachineModel.class);
            case "insert_tester_testcases": return OrderPerformer.insert(data, "tester_testcases", TesterTestcaseModel.class);
            case "insert_user_testtypes": return OrderPerformer.insert(data, "user_testtypes", UserTesttypeModel.class);
            case "update_testtypes": return OrderPerformer.update(data, "testtypes", TesttypeModel.class);
            case "update_testcases": return OrderPerformer.update(data, "testcases", TestcaseModel.class);
            case "update_requests": return OrderPerformer.update(data, "requests", RequestModel.class);
            case "update_testers": return OrderPerformer.update(data, "testers", TesterModel.class);
            case "update_users": return OrderPerformer.update(data, "users", UserModel.class);
            case "update_testmachines": return OrderPerformer.update(data, "testmachines", TestmachineModel.class);
            case "update_tester_testcases": return OrderPerformer.update(data, "tester_testcases", TesterTestcaseModel.class);
            case "update_user_testtypes": return OrderPerformer.update(data, "user_testtypes", UserTesttypeModel.class);
            case "exit_witchout_server":
            case "exit_witch_server":
                return "exit";
            default: return "";
        }
    }

}
