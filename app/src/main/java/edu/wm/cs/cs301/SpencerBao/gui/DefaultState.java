package edu.wm.cs.cs301.SpencerBao.gui;

import edu.wm.cs.cs301.SpencerBao.gui.Constants.UserInput;
import edu.wm.cs.cs301.SpencerBao.generation.Maze;
import edu.wm.cs.cs301.SpencerBao.generation.Order.Builder;
import edu.wm.cs.cs301.SpencerBao.gui.Controller;
import edu.wm.cs.cs301.SpencerBao.gui.MazePanel;

/**
 * This is a default implementation of the State interface
 * with methods that do nothing but providing runtime exceptions
 * such that subclasses of this class can selectively override
 * those methods that are truly needed.
 *
 * @author Peter Kemper
 *
 */
public class DefaultState implements State {

    @Override
    public void start(Controller controller, MazePanel panel) {
        throw new RuntimeException("DefaultState:using unimplemented method");
    }

    @Override
    public void setFileName(String filename) {
        throw new RuntimeException("DefaultState:using unimplemented method");
    }

    @Override
    public void setSkillLevel(int skillLevel) {
        throw new RuntimeException("DefaultState:using unimplemented method");
    }

    @Override
    public void setSeed(int seed) {
        throw new RuntimeException("DefaultState:using unimplemented method");
    }
    @Override
    public void setPerfect(boolean isPerfect) {
        throw new RuntimeException("DefaultState:using unimplemented method");
    }

    @Override
    public void setMazeConfiguration(Maze config) {
        throw new RuntimeException("DefaultState:using unimplemented method");
    }

    @Override
    public void setPathLength(int pathLength) {
        throw new RuntimeException("DefaultState:using unimplemented method");
    }

    @Override
    public boolean keyDown(UserInput key, int value) {
        throw new RuntimeException("DefaultState:using unimplemented method");
    }

    @Override
    public void setBuilder(Builder dfs) {
        throw new RuntimeException("DefaultState:using unimplemented method");
    }
}
