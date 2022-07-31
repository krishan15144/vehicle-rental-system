package com.navi;

public class AvailabilityFactory {
  public IAvailabilityService getAvailibiltyService(AvailabilityType availabilityType) {
    switch (availabilityType){
      case AVAILABILITY_BY_VEHICLE_TYPE:
        return new AvailabilityByVehicleType();
      case AVAILABILITY_BY_PRICE:
        return new AvailabilityByPrice();
      default:
        return null;
    }
  }
}
