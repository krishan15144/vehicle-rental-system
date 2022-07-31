package com.navi;

public class BasicPricingStrategy implements PricingStrategy{

  @Override
  public int getPrice(int currentPrice) {
    return currentPrice;
  }
}
