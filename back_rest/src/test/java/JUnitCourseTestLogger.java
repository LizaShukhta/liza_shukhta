import data_base.ConnectorToDB;
import logging.LoggerSaver;
import model.Model;
import model.TesterModel;
import model.TestmachineModel;
import model.UserModel;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JUnitCourseTestLogger {

    @Test
    public void testLogger() {
        Logger logger = LoggerSaver.logger;
        String name = "CourseLogger";
        assertEquals(logger.getName(), name);
    }
}
