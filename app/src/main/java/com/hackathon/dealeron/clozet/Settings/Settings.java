package com.hackathon.dealeron.clozet.Settings;

public class Settings {

  public static TemperatureUnit UNIT;
  public static Gender GENDER;
  public static int ZIP_CODE;
  public static boolean IS_SET;

  public static void setSettings(TemperatureUnit unit, Gender gender, int zipCode) {
    UNIT = unit;
    GENDER = gender;
    ZIP_CODE = zipCode;
  }
}
