package com.navi;

import java.util.ArrayList;
import java.util.List;

public class VehicleUtil {

  private VehicleUtil(){ };

  static List<Vehicle> findAvailableVehicles(List<Vehicle> vehicles, int pickupTime, int returnTime){
    List<Vehicle> availableVehicle = new ArrayList<>();
    for(Vehicle vehicle:vehicles){
      boolean isBooked=false;
      for(Booking booking: vehicle.getBookings()){
        if(!(booking.getPickupTime()<pickupTime &&  booking.getReturnTime()<returnTime ||
            booking.getPickupTime()>pickupTime &&  booking.getReturnTime()>returnTime)){
          isBooked=true;
          break;
        }
      }
      if(!isBooked){
        availableVehicle.add(vehicle);
      }
    }
    return availableVehicle;
  }
}
