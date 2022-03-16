import data_base.ConnectorToDB;
import dialogs.CDialog;
import logging.LoggerSaver;
import model.*;
import org.junit.Test;
import runing.ServerRunner;
import validation.Validator;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JUnitCourseTestDB {

    @Test
    public void databaseTestTestmachines() {
        ConnectorToDB connector = ConnectorToDB.getInstance();

        ArrayList<Model> models = connector.select("testmachines", " id < ? ", " id","99",
                "true", new String[]{"*", ""});

        assertTrue(((TestmachineModel)models.get(0)).getId()!= 0);
        assertNotNull(models.get(0));
    }

    @Test
    public void databaseTestTesters() {
        ConnectorToDB connector = ConnectorToDB.getInstance();

        ArrayList<Model> models = connector.select("testers", " id < ? ", " id","99",
                "true", new String[]{"*", ""});

        assertTrue(((TesterModel)models.get(0)).getId()!= 0);
        assertNotNull(models.get(0));
    }

    @Test
    public void databaseTestUsers() {
        ConnectorToDB connector = ConnectorToDB.getInstance();

        ArrayList<Model> models = connector.select("users", " id < ? ", " id","99",
                "true", new String[]{"*", ""});

        assertTrue(((UserModel)models.get(0)).getId() != 0);
        assertNotNull(models.get(0));
    }
}
