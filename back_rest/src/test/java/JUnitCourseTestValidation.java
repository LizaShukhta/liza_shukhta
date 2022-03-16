import data_base.ConnectorToDB;
import dialogs.CDialog;
import model.Model;
import model.TestmachineModel;
import org.junit.Test;
import runing.ServerRunner;
import validation.Validator;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JUnitCourseTestValidation {

    @Test
    public void isHost() {
        String className = "127.0.0.1";
        boolean result = Validator.isHost(className);
        assertTrue(result);
    }

    @Test
    public void isPort() {
        String port = "2022";
        boolean result = Validator.isPort(port);
        assertTrue(result);
    }


    @Test
    public void isEMail() {
        String mail = "user@mail.ru";
        boolean result = Validator.isEMail(mail);
        assertTrue(result);
    }

}
