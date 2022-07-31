import com.navi.VehicleRentalCompany;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class VehicleRentalCompanyTest {

  @BeforeEach
  public void init() {
    VehicleRentalCompany.setVehicleRentalCompany();
  }

  @Test
  public void test_addBranch_canBeAdded(){
    //Arrange
    VehicleRentalCompany vehicleRentalCompany = VehicleRentalCompany.getInstance();
    //Act
    boolean result = vehicleRentalCompany.addBranch("B1", Arrays.asList("CAR","BIKE"));
    //Assert
    Assertions.assertTrue(result);
    Assertions.assertEquals(vehicleRentalCompany.getBranches().size(),1);
    Assertions.assertEquals(vehicleRentalCompany.getBranches().get(0).getBranchName(),"B1");
  }

  @Test
  public void test_addBranch_cannotBeAdded(){
    //Arrange
    VehicleRentalCompany vehicleRentalCompany = VehicleRentalCompany.getInstance();
    //Act
    boolean result = vehicleRentalCompany.addBranch("B1", Arrays.asList("CAR","BIKE"));
    boolean result2 = vehicleRentalCompany.addBranch("B1", Arrays.asList("CAR","BIKE"));
    //Assert
    Assertions.assertTrue(result);
    Assertions.assertFalse(result2);
    Assertions.assertEquals(vehicleRentalCompany.getBranches().size(),1);
    Assertions.assertEquals(vehicleRentalCompany.getBranches().get(0).getBranchName(),"B1");
  }

  @Test
  public void test_addVehicle_canBeAdded(){
    //Arrange
    VehicleRentalCompany vehicleRentalCompany = VehicleRentalCompany.getInstance();
    //Act
    vehicleRentalCompany.addBranch("B1", Arrays.asList("CAR","BIKE"));
    boolean result = vehicleRentalCompany.addVehicle("B1","CAR","V1",300);
    //Assert
    Assertions.assertTrue(result);
  }

  @Test
  public void test_addVehicle_cannotBeAdded(){
    //Arrange
    VehicleRentalCompany vehicleRentalCompany = VehicleRentalCompany.getInstance();
    //Act
    vehicleRentalCompany.addBranch("B1", Arrays.asList("BIKE"));
    boolean result = vehicleRentalCompany.addVehicle("B1","CAR","V1",300);
    //Assert
    Assertions.assertFalse(result);
  }

  @Test
  public void test_bookVehicle_noVehicle(){
    //Arrange
    VehicleRentalCompany vehicleRentalCompany = VehicleRentalCompany.getInstance();
    //Act
    vehicleRentalCompany.addBranch("B1", Arrays.asList("CAR","BIKE"));
    vehicleRentalCompany.addVehicle("B1","CAR","V1",300);
    int result = vehicleRentalCompany.bookVehicle("B1","Bike",1,3);
    //Assert
    Assertions.assertEquals(result,-1);
  }

  @Test
  public void test_bookVehicle_success(){
    //Arrange
    VehicleRentalCompany vehicleRentalCompany = VehicleRentalCompany.getInstance();
    //Act
    vehicleRentalCompany.addBranch("B1", Arrays.asList("CAR","BIKE"));
    vehicleRentalCompany.addVehicle("B1","CAR","V1",300);
    int result = vehicleRentalCompany.bookVehicle("B1","CAR",1,3);
    //Assert
    Assertions.assertEquals(result,600);
  }

  @Test
  public void test_getAvailable_success(){
    //Arrange
    VehicleRentalCompany vehicleRentalCompany = VehicleRentalCompany.getInstance();
    //Act
    vehicleRentalCompany.addBranch("B1", Arrays.asList("CAR","BIKE"));
    vehicleRentalCompany.addVehicle("B1","CAR","V1",300);
    //Assert
    Assertions.assertEquals(vehicleRentalCompany.getBranches().get(0).getVehicleStore().getAvailableVehicle(1,3).size(),1);
  }
}
