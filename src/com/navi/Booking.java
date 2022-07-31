package com.navi;

public class Booking {

  private final int pickupTime;
  private final int returnTime;

  public Booking(int pickupTime, int returnTime) {
    this.pickupTime = pickupTime;
    this.returnTime = returnTime;
  }

  public int getPickupTime() {
    return pickupTime;
  }

  public int getReturnTime() {
    return returnTime;
  }
}
