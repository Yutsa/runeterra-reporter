package com.runeterrareporter.utils;

public class MathUtils {

  private MathUtils() {
    throw new InstantiationError();
  }

  public static double roundToTwoDigits(double number) {
    number *= 100;
    number = Math.round(number);
    return number / 100;
  }
}
