package com.navi;

public class SurgePricingStrategy implements PricingStrategy{

  @Override
  public int getPrice(int currentPrice) {
    return (int) Math.round(currentPrice*1.1);
  }
}
