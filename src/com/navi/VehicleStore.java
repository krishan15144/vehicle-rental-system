package com.navi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VehicleStore {

  private List<Vehicle> vehicles;
  private List<VehicleType> vehicleTypes;
  private AvailabilityFactory availabilityFactory;
  public VehicleStore(List<String> vehicleTypes) {
    this.vehicleTypes = vehicleTypes.stream()
        .map(VehicleType::valueOf)
        .collect(Collectors.toList());
    availabilityFactory = new AvailabilityFactory();
    vehicles = new ArrayList<>();
  }

  public boolean addVehicle(String vehicleType, String vehicleId, int price) {
    if(!vehicleTypes.contains(VehicleType.valueOf(vehicleType)) || vehicles.stream().anyMatch(vehicle -> vehicleId
        .equals(vehicle.getVehicleId()))){
      return false;
    }
    vehicles.add(new Vehicle(VehicleType.valueOf(vehicleType),price,vehicleId));
    return true;
  }

  public int bookVehicle(String vehicleType, int pickupTime, int returnTime) {
    Optional<Vehicle> vehicle = getAvailableVehicle(pickupTime,returnTime)
        .stream()
        .filter(vehicle1 -> vehicleType.equals(vehicle1.getVehicleTye().name()))
        .findFirst();
    if(vehicle.isEmpty()){
      return -1;
    }
    PricingStrategy pricingStrategy = getPricingStrategy(pickupTime,returnTime);
    vehicle.get().setPrice(pricingStrategy.getPrice(vehicle.get().getPrice()));
    return vehicle.get().createBooking(pickupTime,returnTime);
  }


  public List<Vehicle> getAvailableVehicle(int pickupTime, int returnTime, AvailabilityType availabilityType) {
    return availabilityFactory.getAvailibiltyService(availabilityType)
        .getAvailableVehicles(vehicles,pickupTime,returnTime);
  }

  public List<Vehicle> getAvailableVehicle(int pickupTime, int returnTime) {
    return availabilityFactory.getAvailibiltyService(AvailabilityType.AVAILABILITY_BY_PRICE)
        .getAvailableVehicles(vehicles,pickupTime,returnTime);
  }


  private PricingStrategy getPricingStrategy(int pickupTime, int returnTime) {
    int availableVehicles = getAvailableVehicle(pickupTime,returnTime).size();
    int totalVehicles = this.vehicles.size();
    if(availableVehicles<=0.2*totalVehicles){
      return new SurgePricingStrategy();
    }
    return new BasicPricingStrategy();
  }

  public List<Vehicle> getVehicles() {
    return vehicles;
  }

}
