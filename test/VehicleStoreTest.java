import com.navi.Vehicle;
import com.navi.VehicleStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class VehicleStoreTest {

  private List<String> vehicleTypes;

  @BeforeEach
  public void init() {
    vehicleTypes = new ArrayList<>();
  }

  @Test
  void test_addVehicle_success(){
    //Arrange
    vehicleTypes.add("CAR");
    VehicleStore vehicleStore = new VehicleStore(vehicleTypes);
    //Act
    boolean res = vehicleStore.addVehicle("CAR","V1",300);
    //Assert
    Assertions.assertTrue(res);
    Assertions.assertEquals(vehicleStore.getVehicles().size(),1);
  }

  @Test
  void test_addVehicle_cannotAdd(){
    //Arrange
    VehicleStore vehicleStore = new VehicleStore(vehicleTypes);
    //Act
    boolean res = vehicleStore.addVehicle("CAR","V1",300);
    //Assert
    Assertions.assertFalse(res);
    Assertions.assertEquals(vehicleStore.getVehicles().size(),0);
  }

  @Test
  void test_bookVehicle_success(){
    //Arrange
    vehicleTypes.add("CAR");
    VehicleStore vehicleStore = new VehicleStore(vehicleTypes);
    vehicleStore.addVehicle("CAR","V1",300);
    //Act
    int price = vehicleStore.bookVehicle("CAR",1,3);
    //Assert
    Assertions.assertEquals(price,600);
  }

  @Test
  void test_bookVehicle_cannotBook(){
    //Arrange
    vehicleTypes.add("CAR");
    VehicleStore vehicleStore = new VehicleStore(vehicleTypes);
    vehicleStore.addVehicle("CAR","V1",300);
    //Act
    int price = vehicleStore.bookVehicle("BUS",1,3);
    //Assert
    Assertions.assertEquals(price,-1);
  }

  @Test
  void test_Availability_success(){
    //Arrange
    vehicleTypes.add("CAR");
    VehicleStore vehicleStore = new VehicleStore(vehicleTypes);
    vehicleStore.addVehicle("CAR","V1",300);
    //Act
    List<Vehicle> vehicles = vehicleStore.getAvailableVehicle(1,3);
    //Assert
    Assertions.assertEquals(vehicles.size(),1);
  }

  @Test
  void test_Booking_priceSurge(){
    //Arrange
    vehicleTypes.add("CAR");
    vehicleTypes.add("BUS");
    vehicleTypes.add("VAN");
    VehicleStore vehicleStore = new VehicleStore(vehicleTypes);
    vehicleStore.addVehicle("CAR","V1",300);
    vehicleStore.addVehicle("BUS","V2",1000);
    vehicleStore.addVehicle("VAN","V3",500);
    vehicleStore.addVehicle("VAN","V4",600);
    vehicleStore.addVehicle("VAN","V5",700);
    //Act
    vehicleStore.bookVehicle("BUS",1,3);
    vehicleStore.bookVehicle("CAR",1,3);
    vehicleStore.bookVehicle("VAN",1,3);
    vehicleStore.bookVehicle("VAN",1,3);
    int price = vehicleStore.bookVehicle("VAN",1,3);
    //Assert
    Assertions.assertEquals(price,(int)(700*1.1*2));
  }
}
