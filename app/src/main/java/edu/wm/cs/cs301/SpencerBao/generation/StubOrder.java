package edu.wm.cs.cs301.SpencerBao.generation;

public class StubOrder implements Order{
    private int skillLevel;
    private Builder builder;
    private boolean isPerfect;
    private int seed;
    private Maze mazeConfig;
    private int progress;

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public void setMazeConfig(Maze mazeConfig) {
        this.mazeConfig = mazeConfig;
    }

    public void setPerfect(boolean perfect) {
        isPerfect = perfect;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    @Override
    public int getSkillLevel() {
        return skillLevel;
    }

    @Override
    public Builder getBuilder() {
        return builder;
    }

    @Override
    public boolean isPerfect() {
        return isPerfect;
    }

    @Override
    public int getSeed() {
        return seed;
    }

    @Override
    public void deliver(Maze mazeConfig) {
        this.mazeConfig = mazeConfig;
    }

    public Maze getMazeConfig() {
        return mazeConfig;
    }

    @Override
    public void updateProgress(int percentage) {
        this.progress = percentage;
    }
}
