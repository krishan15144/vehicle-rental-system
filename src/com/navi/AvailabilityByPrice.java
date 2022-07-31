package com.navi;

import java.util.Comparator;
import java.util.List;

public class AvailabilityByPrice implements IAvailabilityService {

  /**
   * getAvailableVehicles Method for to get available vehicles by price.
   */
  @Override
  public List<Vehicle> getAvailableVehicles(List<Vehicle> vehicles, int pickupTime, int returnTime) {
    List<Vehicle> availableVehicle = VehicleUtil.findAvailableVehicles(vehicles, pickupTime, returnTime);
    availableVehicle.sort(Comparator.comparingInt(Vehicle::getPrice));
    return availableVehicle;
  }
}
