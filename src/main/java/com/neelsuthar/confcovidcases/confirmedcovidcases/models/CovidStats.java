package com.neelsuthar.confcovidcases.confirmedcovidcases.models;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class CovidStats {

  private String state;
  private String country;
  private int currentCasesInt;
  private String currentCases;
  private int difFromPrevDayInt;
  private String difFromPrevDay;

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCurrentCases() {
    return currentCases;
  }

  public static String formatNumber(int number) {
    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
    DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

    symbols.setGroupingSeparator(',');
    formatter.setDecimalFormatSymbols(symbols);

    return formatter.format(number);
  }

  public void setCurrentCases(int currentCases) {
    this.setCurrentCasesInt(currentCases);
    this.currentCases = formatNumber(currentCases);
  }

  public int getCurrentCasesInt() {
    return currentCasesInt;
  }

  public void setCurrentCasesInt(int currentCasesInt) {
    this.currentCasesInt = currentCasesInt;
  }

  @Override
  public String toString() {
    return "CovidStats [country=" + country + ", currentCases=" + currentCases + ", currentCasesInt=" + currentCasesInt
        + ", state=" + state + "]";
  }

  public int getDifFromPrevDayInt() {
    return difFromPrevDayInt;
  }

  public void setDifFromPrevDayInt(int difFromPrevDayInt) {
    this.difFromPrevDayInt = difFromPrevDayInt;
  }

  public String getDifFromPrevDay() {
    return difFromPrevDay;
  }

  public void setDifFromPrevDay(int difFromPrevDay) {
    this.setDifFromPrevDayInt(difFromPrevDay);
    this.difFromPrevDay = formatNumber(difFromPrevDay);
  }

}
