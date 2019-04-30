package com.abc.crictrackerbd;

public class Fixture {
    String opponent;
    String venue;
    String date;
    String time;
    String format;

    public Fixture(String opponent, String venue, String date, String time, String format, String key) {
        this.opponent = opponent;
        this.venue = venue;
        this.date = date;
        this.time = time;
        this.format = format;
        this.key = key;
    }

    String key;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
