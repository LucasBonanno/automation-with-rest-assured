package demo.test.lucas.integration;

import demo.test.lucas.integration.controller.AddVehicleTest;
import demo.test.lucas.integration.controller.DeleteVechileTest;
import demo.test.lucas.integration.controller.GetVehicleTest;
import demo.test.lucas.integration.controller.UpdateVehicleTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses( {
        AddVehicleTest.class,
        GetVehicleTest.class,
        UpdateVehicleTest.class,
        DeleteVechileTest.class,
} )
public class TestSuite {
}
