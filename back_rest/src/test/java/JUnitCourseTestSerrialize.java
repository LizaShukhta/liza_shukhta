import com.google.gson.reflect.TypeToken;
import data_base.ConnectorToDB;
import model.*;
import org.junit.Test;
import serialization.Serrializator;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JUnitCourseTestSerrialize {

    @Test
    public void testSerrialization() {
        ConnectorToDB connector = ConnectorToDB.getInstance();

        ArrayList<Model> models = connector.select("users", " id < ? ", " id","99",
                "true", new String[]{"*", ""});

        ArrayList<UserModel> modelsUser = new ArrayList<>();
        for (Model model :
                models) {
            modelsUser.add((UserModel) model);
        }

        ArrayList<Model> arrayModels = Serrializator.JSONToObjects(Serrializator.ObjectsToJSON(modelsUser), new TypeToken<ArrayList<UserModel>>(){}.getType());
        assertEquals(modelsUser.size(), arrayModels.size());

    }

}
