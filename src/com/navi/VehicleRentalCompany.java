package com.navi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleRentalCompany {

  private static VehicleRentalCompany vehicleRentalCompany;

  private final List<Branch> branches;

  /**
   * private constructor to make VehicleRentalCompany a Singleton class.
   */
  private VehicleRentalCompany() {

    //Prevent from the reflection api.
    if (vehicleRentalCompany != null) {
      throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
    }
    branches = new ArrayList<>();
  }

  /**
   * getInstance Method for the singleton class.
   */
  public static VehicleRentalCompany getInstance() {

    //Make VehicleRentalCompany singleton with int checked locking pattern.
    if (vehicleRentalCompany == null) { //Check for the first time
      synchronized (VehicleRentalCompany.class) {   //Take lock.
        //If there is no instance available.Create new one.
        if (vehicleRentalCompany == null) {
          vehicleRentalCompany = new VehicleRentalCompany();
        }
      }
    }
    return vehicleRentalCompany;
  }

  /**
   * Getter for branches.
   */
  public List<Branch> getBranches() {
    return branches;
  }

  /**
   * add a new branch to the VehicleRentalCompany
   */
  public boolean addBranch(final String branchName, final List<String> vehicleType) {
    if (getBranch(branchName) != null) {
      return false;
    }
    branches.add(new Branch(branchName, vehicleType));
    return true;
  }

  /**
   * add a new vehicle to the branch
   *
   * @param branchName  : branchName
   * @param vehicleType : type of the vehicle
   * @param vehicleId   : vehicleId
   * @param price       : price of the vehicle
   * @return true if added. false if cannot
   */
  public boolean addVehicle(final String branchName, final String vehicleType, final String vehicleId, final int price) {
    Branch branch = getBranch(branchName);
    return branch != null && branch.addVehicle(vehicleType, vehicleId, price);
  }

  /**
   * add a new vehicle to the branch
   *
   * @param branchName  : branchName
   * @param vehicleType : type of the vehicle
   * @param pickupTime  : pickupTime
   * @param returnTime  : returnTime
   * @return totalPrice if booked otherwise -1.
   */
  public int bookVehicle(final String branchName, final String vehicleType, final int pickupTime, final int returnTime) {
    Branch branch = getBranch(branchName);
    return branch == null ? -1 : branch.bookVehicle(vehicleType,pickupTime,returnTime);
  }

  /**
   * generate a list of all tha vehicles
   *
   * @param branchName : branch
   * @param pickupTime : starting time
   * @param returnTime : ending time
   * @return list of available vehicles
   */
  public List<String> listAvailableVehicles(
      final String branchName, final int pickupTime, final int returnTime,final AvailabilityType availabilityType) {
    Branch branch = getBranch(branchName);

    if(branch == null){
      return new ArrayList<>();
    }

    return branch.getAvailableVehicle(pickupTime,returnTime,availabilityType);
  }
  /**
   * helper method to get a branch
   * @param branchName  : branchName
   * @return branch if exists otherwise null.
   */
  private Branch getBranch(final String branchName){

    Optional<Branch> branch = branches.stream()
        .filter(branchTemp -> branchName.equals(branchTemp.getBranchName()))
        .findAny();

    return branch.orElse(null);
  }

  public static void setVehicleRentalCompany(){
    vehicleRentalCompany = null;
  }
}
