package mainpackage.controller;

import mainpackage.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClientRestController {

    @Autowired
    @Qualifier("DBService")
    private DBService dbService;

    @RequestMapping(value = "/authorization", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String authorization(@RequestParam String data) {
        return dbService.runCommand("authorization", data);
    }

    @RequestMapping(value = "/getTesttypes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getTesttypes(@RequestParam String data) {
        return dbService.runCommand("getTesttypes", data);
    }

    @RequestMapping(value = "/getTestcases", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getTestcases(@RequestParam String data) {
        return dbService.runCommand("getTestcases", data);
    }

    @RequestMapping(value = "/getRequests", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getRequests(@RequestParam String data) {
        return dbService.runCommand("getRequests", data);
    }

    @RequestMapping(value = "/getTesters", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getTesters(@RequestParam String data) {
        return dbService.runCommand("getTesters", data);
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getUsers(@RequestParam String data) {
        return dbService.runCommand("getUsers", data);
    }

    @RequestMapping(value = "/getTestmachines", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getTestmachines(@RequestParam String data) {
        return dbService.runCommand("getTestmachines", data);
    }

    @RequestMapping(value = "/getTestersTestcases", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getTestersTestcases(@RequestParam String data) {
        return dbService.runCommand("getTestersTestcases", data);
    }

    @RequestMapping(value = "/getUserTesttypes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getUserTesttypes(@RequestParam String data) {
        return dbService.runCommand("getUserTesttypes", data);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String delete(@RequestParam String data) {
        return dbService.runCommand("delete", data);
    }

    @RequestMapping(value = "/insert_testtypes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String insert_testtypes(@RequestParam String data) {
        return dbService.runCommand("insert_testtypes", data);
    }

    @RequestMapping(value = "/insert_testcases", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String insert_testcases(@RequestParam String data) {
        return dbService.runCommand("insert_testcases", data);
    }

    @RequestMapping(value = "/insert_requests", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String insert_requests(@RequestParam String data) {
        return dbService.runCommand("insert_requests", data);
    }

    @RequestMapping(value = "/insert_testers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String insert_testers(@RequestParam String data) {
        System.out.println(data);
        return dbService.runCommand("insert_testers", data);
    }

    @RequestMapping(value = "/insert_users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String insert_users(@RequestParam String data) {
        return dbService.runCommand("insert_users", data);
    }

    @RequestMapping(value = "/insert_testmachines", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String insert_testmachines(@RequestParam String data) {
        return dbService.runCommand("insert_testmachines", data);
    }

    @RequestMapping(value = "/insert_tester_testcases", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String insert_tester_testcases(@RequestParam String data) {
        return dbService.runCommand("insert_tester_testcases", data);
    }

    @RequestMapping(value = "/insert_user_testtypes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String insert_user_testtypes(@RequestParam String data) {
        return dbService.runCommand("insert_user_testtypes", data);
    }

    @RequestMapping(value = "/update_testtypes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String update_testtypes(@RequestParam String data) {
        return dbService.runCommand("update_testtypes", data);
    }

    @RequestMapping(value = "/update_testcases", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String update_testcases(@RequestParam String data) {
        return dbService.runCommand("update_testcases", data);
    }

    @RequestMapping(value = "/update_requests", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String update_requests(@RequestParam String data) {
        return dbService.runCommand("update_requests", data);
    }

    @RequestMapping(value = "/update_testers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String update_testers(@RequestParam String data) {
        return dbService.runCommand("update_testers", data);
    }

    @RequestMapping(value = "/update_users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String update_users(@RequestParam String data) {
        return dbService.runCommand("update_users", data);
    }

    @RequestMapping(value = "/update_testmachines", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String update_testmachines(@RequestParam String data) {
        return dbService.runCommand("update_testmachines", data);
    }

    @RequestMapping(value = "/update_tester_testcases", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String update_tester_testcases(@RequestParam String data) {
        return dbService.runCommand("update_tester_testcases", data);
    }

    @RequestMapping(value = "/update_user_testtypes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String update_user_testtypes(@RequestParam String data) {
        return dbService.runCommand("update_user_testtypes", data);
    }

    @RequestMapping(value = "/exit_witchout_server", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String exit_witchout_server(@RequestParam String data) {
        return dbService.runCommand("exit_witchout_server", data);
    }
}
