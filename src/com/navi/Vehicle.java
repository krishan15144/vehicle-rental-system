package com.navi;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {

  private final String vehicleId;
  private final VehicleType vehicleTye;
  private int price;
  private final List<Booking> bookings;

  public Vehicle(VehicleType vehicleTye, int price, String vehicleId) {
    this.vehicleTye = vehicleTye;
    this.vehicleId = vehicleId;
    this.price = price;
    this.bookings = new ArrayList<>();
  }

  public String getVehicleId() {
    return vehicleId;
  }

  public VehicleType getVehicleTye() {
    return vehicleTye;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public List<Booking> getBookings() {
    return bookings;
  }

  int createBooking(int pickupTime,int returnTime){
    bookings.add(new Booking(pickupTime,returnTime));
    return price*(returnTime-pickupTime);
  }

}
