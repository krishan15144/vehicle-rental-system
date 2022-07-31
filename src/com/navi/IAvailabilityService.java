package com.navi;

import java.util.List;

public interface IAvailabilityService {

   List<Vehicle> getAvailableVehicles(List<Vehicle> vehicles,int pickupTime, int returnTime);
}
