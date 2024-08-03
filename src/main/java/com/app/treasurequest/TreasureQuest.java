package main.java.com.app.treasurequest;

import main.java.com.app.Board;
import main.java.com.app.adventurer.Adventurer;

import java.util.List;

public class TreasureQuest {

    private Board board;
    private List<Adventurer> adventurers;

    public TreasureQuest(Board board, List<Adventurer> adventurers){
        this.board = board;
        this.adventurers = adventurers;
    }

    public Board getBoard() {
        return board;
    }

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }
}
