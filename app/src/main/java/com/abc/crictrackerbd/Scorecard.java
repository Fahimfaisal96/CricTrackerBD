package com.abc.crictrackerbd;

public class Scorecard {
    String toss;
    String firstInnings;
    String secondInnings;
    String thirdInnings;
    String fourthInnings;
    String key;
    String format;
    String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;

    public Scorecard(){

    }

    public String getToss() {
        return toss;
    }

    public void setToss(String toss) {
        this.toss = toss;
    }

    public String getFirstInnings() {
        return firstInnings;
    }

    public void setFirstInnings(String firstInnings) {
        this.firstInnings = firstInnings;
    }

    public String getSecondInnings() {
        return secondInnings;
    }

    public void setSecondInnings(String secondInnings) {
        this.secondInnings = secondInnings;
    }

    public String getThirdInnings() {
        return thirdInnings;
    }

    public void setThirdInnings(String thirdInnings) {
        this.thirdInnings = thirdInnings;
    }

    public String getFourthInnings() {
        return this.fourthInnings;
    }

    public void setFourthInnings(String fourthInnings) {
        this.fourthInnings = fourthInnings;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Scorecard(String toss, String firstInnings, String secondInnings, String thirdInnings, String fourthInnings,  String format,String date,String link,String key) {
        this.toss = toss;
        this.firstInnings = firstInnings;
        this.secondInnings = secondInnings;
        this.thirdInnings = thirdInnings;
        this.fourthInnings = fourthInnings;
        this.key = key;
        this.format = format;
        this.date=date;
        this.link=link;
    }
}
