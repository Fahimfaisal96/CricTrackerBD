package com.abc.crictrackerbd;

public class Player {
    String name;
    String role;
    String match;
    String innings;
    String runs;
    String average;
    String strikeRate;
    String hundreds;
    String fifties;
    String notOuts;
    String bestScore;
    String balls;
    String runsConceded;
    String wickets;
    String economy;
    String bowlingAverage;
    String bowlingStrikeRate;
    String fifers;
    String bestFigure;
    String format;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getBestFigure() {
        return bestFigure;
    }

    public void setBestFigure(String bestFigure) {
        this.bestFigure = bestFigure;
    }

    String key;
    String dpUrl;



    public Player(){
    }

    public Player(String name, String role, String match, String innings, String runs, String average, String strikeRate, String hundreds, String fifties, String notOuts, String bestScore, String balls, String runsConceded, String wickets, String economy, String bowlingAverage, String bowlingStrikeRate, String fifers, String bestFigure, String dpUrl, String format,String key) {
        this.name = name;
        this.role = role;
        this.match = match;
        this.innings = innings;
        this.runs = runs;
        this.average = average;
        this.strikeRate = strikeRate;
        this.hundreds = hundreds;
        this.fifties = fifties;
        this.notOuts = notOuts;
        this.bestScore = bestScore;
        this.balls = balls;
        this.runsConceded = runsConceded;
        this.wickets = wickets;
        this.economy = economy;
        this.bowlingAverage = bowlingAverage;
        this.bowlingStrikeRate = bowlingStrikeRate;
        this.fifers = fifers;
        this.bestFigure = bestFigure;
        this.dpUrl = dpUrl;
        this.key = key;
        this.format = format;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getInnings() {
        return innings;
    }

    public void setInnings(String innings) {
        this.innings = innings;
    }

    public String getRuns() {
        return runs;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(String strikeRate) {
        this.strikeRate = strikeRate;
    }

    public String getHundreds() {
        return hundreds;
    }

    public void setHundreds(String hundreds) {
        this.hundreds = hundreds;
    }

    public String getFifties() {
        return fifties;
    }

    public void setFifties(String fifties) {
        this.fifties = fifties;
    }

    public String getNotOuts() {
        return notOuts;
    }

    public void setNotOuts(String notOuts) {
        this.notOuts = notOuts;
    }

    public String getBestScore() {
        return bestScore;
    }

    public void setBestScore(String bestScore) {
        this.bestScore = bestScore;
    }

    public String getBalls() {
        return balls;
    }

    public void setBalls(String balls) {
        this.balls = balls;
    }

    public String getRunsConceded() {
        return runsConceded;
    }

    public void setRunsConceded(String runsConceded) {
        this.runsConceded = runsConceded;
    }

    public String getWickets() {
        return wickets;
    }

    public void setWickets(String wickets) {
        this.wickets = wickets;
    }

    public String getEconomy() {
        return economy;
    }

    public void setEconomy(String economy) {
        this.economy = economy;
    }

    public String getBowlingAverage() {
        return bowlingAverage;
    }

    public void setBowlingAverage(String bowlingAverage) {
        this.bowlingAverage = bowlingAverage;
    }

    public String getBowlingStrikeRate() {
        return bowlingStrikeRate;
    }

    public void setBowlingStrikeRate(String bowlingStrikeRate) {
        this.bowlingStrikeRate = bowlingStrikeRate;
    }

    public String getFifers() {
        return fifers;
    }

    public void setFifers(String fifers) {
        this.fifers = fifers;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDpUrl() {
        return dpUrl;
    }

    public void setDpUrl(String dpUrl) {
        this.dpUrl = dpUrl;
    }
}
