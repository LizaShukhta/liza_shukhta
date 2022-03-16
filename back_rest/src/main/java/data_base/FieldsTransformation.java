package data_base;

import model.*;
import validation.Validator;

public class FieldsTransformation {


    public String[] newData(Model model){
        switch (model.getClass().getName()){
            case "model.TesttypeModel":               return newDataTesttype(model);
            case "model.TestcaseModel":                return newDataTestcase(model);
            case "model.RequestModel":               return newDataRequest(model);
            case "model.TesterModel":               return newDataTester(model);
            case "model.UserModel":                   return newDataUser(model);
            default: case "model.TestmachineModel":     return newDataTestmachine(model);
            case "model.TesterTestcaseModel":        return newDataTesterTestcase(model);
            case "model.UserTesttypeModel":           return newDataUserTesttype(model);
        }
    }

    private String[] newDataTesttype(Model model){
        TesttypeModel testtype = (TesttypeModel)model;
        String categor;
        int id = Validator.getIdFromKey(testtype.getParent_testtype_id());
        categor = id != 0 ? String.valueOf(id) : "NULL";
        String answer[] = new String[]{
                testtype.getName(),
                categor
        };
        return answer;
    }


    private String[] newDataTestcase(Model model){
        TestcaseModel testcase = (TestcaseModel)model;
        String answer[] = new String[]{
                testcase.getName(),
                testcase.getDescription(),
                String.valueOf(testcase.getPrice()),
                String.valueOf(testcase.getDuration()),
                testcase.getAurimatization(),
                String.valueOf(Validator.getIdFromKey(testcase.getTesttype_id())),
                String.valueOf(Validator.getIdFromKey(testcase.getTestmachine_id()))
        };
        return answer;
    }

    private String[] newDataRequest(Model model){
        RequestModel request = (RequestModel)model;
        String answer[] = new String[]{
                request.getDate(),
                request.getPhone(),
                request.getMail(),
                String.valueOf(request.getImportance()),
                String.valueOf(Validator.getIdFromKey(request.getTestcase_id())),
                String.valueOf(Validator.getIdFromKey(request.getUser_id()))
        };
        return answer;
    }

    private String[] newDataTester(Model model){
        TesterModel tester = (TesterModel)model;
        String answer[] = new String[]{
                tester.getName(),
                tester.getSpeciality(),
                tester.getDate_of_birth()
        };
        return answer;
    }

    private String[] newDataUser(Model model){
        UserModel user = (UserModel)model;
        String answer[] = new String[]{
                user.getLogin(),
                user.getPassword(),
                user.getRole()
        };
        return answer;
    }

    private String[] newDataTestmachine(Model model){
        TestmachineModel testmachine = (TestmachineModel)model;
        String answer[] = new String[]{
                testmachine.getName(),
                testmachine.getIpipaddress()
        };
        return answer;
    }

    private String[] newDataTesterTestcase(Model model){
        TesterTestcaseModel testerTestcase = (TesterTestcaseModel)model;
        String answer[] = new String[]{
                String.valueOf(Validator.getIdFromKey(testerTestcase.getTester_id())),
                String.valueOf(Validator.getIdFromKey(testerTestcase.getTestcase_id()))
        };
        return answer;
    }

    private String[] newDataUserTesttype(Model model){
        UserTesttypeModel userTesttype = (UserTesttypeModel)model;
        String answer[] = new String[]{
                String.valueOf(Validator.getIdFromKey(userTesttype.getUser_id())),
                String.valueOf(Validator.getIdFromKey(userTesttype.getTesttype_id()))
        };
        return answer;
    }

}
