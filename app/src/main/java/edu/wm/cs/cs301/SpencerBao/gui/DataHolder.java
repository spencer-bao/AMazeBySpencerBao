package edu.wm.cs.cs301.SpencerBao.gui;

import android.app.Application;

public class DataHolder extends Application {
    private int skillLevel;
    private String mazeAlgorithm;
    private Boolean roomsOrNoRooms;
    private String driverConfig;
    private String robotConfig;

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public void setMazeAlgorithm(String mazeAlgorithm){
        this.mazeAlgorithm= mazeAlgorithm;
    }

    public void setRoomsOrNoRooms(Boolean rooms){
        this.roomsOrNoRooms = rooms;
    }

    public void setDriverConfig(String driverConfig){
        this.driverConfig = driverConfig;
    }

    public void setRobotConfig(String robotConfig) {
        this.robotConfig = robotConfig;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public String getMazeAlgorithm() {
        return mazeAlgorithm;
    }

    public Boolean getRoomsOrNoRooms() {
        return roomsOrNoRooms;
    }

    public String getDriverConfig() {
        return driverConfig;
    }

    public String getRobotConfig() {
        return robotConfig;
    }
}
