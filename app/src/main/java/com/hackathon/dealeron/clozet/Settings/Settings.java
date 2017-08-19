package com.hackathon.dealeron.clozet.Settings;

public class Settings {

  public static TemperatureUnit UNIT;
  public static Gender GENDER;
  public static int ZIP_CODE;
  public static boolean INITIAL_SETUP_COMPLETE;

  public static void setSettings(TemperatureUnit unit, Gender gender, int zipCode) {
    UNIT = unit;
    GENDER = gender;
    ZIP_CODE = zipCode;
  }

  public static boolean isSet() {
    return UNIT != null && GENDER != null && ZIP_CODE != 0;
  }
}
