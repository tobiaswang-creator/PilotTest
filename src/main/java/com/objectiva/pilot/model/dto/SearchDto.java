package com.objectiva.pilot.model.dto;

public class SearchDto {

  private int accountId;
  private String searchStartDate;
  private String searchEndDate;
  private String searchStartAmount;
  private String searchEndAmount;

  public SearchDto(int accountId, String searchStartDate, String searchEndDate, String searchStartAmount,
      String searchEndAmount) {
    this.accountId = accountId;
    this.searchStartDate = searchStartDate;
    this.searchEndDate = searchEndDate;
    this.searchStartAmount = searchStartAmount;
    this.searchEndAmount = searchEndAmount;
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public String getSearchStartDate() {
    return searchStartDate;
  }

  public void setSearchStartDate(String searchStartDate) {
    this.searchStartDate = searchStartDate;
  }

  public String getSearchEndDate() {
    return searchEndDate;
  }

  public void setSearchEndDate(String searchEndDate) {
    this.searchEndDate = searchEndDate;
  }

  public String getSearchStartAmount() {
    return searchStartAmount;
  }

  public void setSearchStartAmount(String searchStartAmount) {
    this.searchStartAmount = searchStartAmount;
  }

  public String getSearchEndAmount() {
    return searchEndAmount;
  }

  public void setSearchEndAmount(String searchEndAmount) {
    this.searchEndAmount = searchEndAmount;
  }

}
