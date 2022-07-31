package com.navi;

import java.util.Comparator;
import java.util.List;

public class AvailabilityByVehicleType implements IAvailabilityService {

  /**
   * getAvailableVehicles Method for to get available vehicles by vehicle type.
   */
  @Override
  public List<Vehicle> getAvailableVehicles(List<Vehicle> vehicles,int pickupTime, int returnTime) {
    List<Vehicle> availableVehicle = VehicleUtil.findAvailableVehicles(vehicles, pickupTime, returnTime);
    availableVehicle.sort(Comparator.comparing(Vehicle::getVehicleTye));
    return availableVehicle;
  }
}
