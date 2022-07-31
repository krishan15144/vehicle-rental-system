package com.navi;
import java.util.List;
import java.util.stream.Collectors;

public class Branch {

  private final String branchName;

  private final VehicleStore vehicleStore;

  public Branch(final String branchName, final List<String> vehicleTypes) {
    this.branchName = branchName;
    this.vehicleStore = new VehicleStore(vehicleTypes);
  }

  public String getBranchName() {
    return this.branchName;
  }

  public VehicleStore getVehicleStore() {
    return vehicleStore;
  }

  public boolean addVehicle(final String vehicleType, final String vehicleId, final int price) {
    return vehicleStore.addVehicle(vehicleType,vehicleId,price);
  }

  public int bookVehicle(final String vehicleType, final int pickupTime, final int returnTime) {
    return vehicleStore.bookVehicle(vehicleType,pickupTime,returnTime);
  }

  public List<String> getAvailableVehicle(final int pickupTime, final int returnTime,final AvailabilityType availabilityType) {
    List<Vehicle> vehicles = vehicleStore.getAvailableVehicle(pickupTime,returnTime,availabilityType);
    return vehicles.stream().map(Vehicle::getVehicleId).collect(Collectors.toList());
  }
}
