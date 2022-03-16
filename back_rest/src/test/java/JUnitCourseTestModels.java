import data_base.ConnectorToDB;
import model.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JUnitCourseTestModels {

    @Test
    public void testUsers() {
        UserModel user = new UserModel(1, "Ivan", "Ivanov", "admin");
        assertEquals(user.getId(), 1);
        assertEquals(user.getLogin(), "Ivan");
        assertEquals(user.getPassword(), "Ivanov");
        assertEquals(user.getRole(), "admin");
    }

    @Test
    public void testTestcases() {
        TestcaseModel user = new TestcaseModel(1, "First TC", "TC new", 20, 20, "full", "1", "1");
        assertEquals(user.getId(), 1);
        assertEquals(user.getName(), "First TC");
        assertEquals(user.getDescription(), "TC new");
        assertEquals(user.getDuration(), 20);
        assertEquals(user.getAurimatization(), "full");
        assertEquals(user.getTesttype_id(), "1");
        assertEquals(user.getTestmachine_id(), "1");

    }

    @Test
    public void testRequests() {
        RequestModel user = new RequestModel(1, "2000.01.01", "80215699221", "admin@amuk.ru", 1, "1", "1");
        assertEquals(user.getId(), 1);
        assertEquals(user.getDate(), "2000.01.01");
        assertEquals(user.getPhone(), "80215699221");
        assertEquals(user.getMail(), "admin@amuk.ru");
        assertEquals(user.getImportance(), 1);
        assertEquals(user.getTestcase_id(), "1");
        assertEquals(user.getUser_id(), "1");

    }
}
