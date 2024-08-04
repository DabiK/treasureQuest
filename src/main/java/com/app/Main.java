package main.java.com.app;

import main.java.com.app.treasurequest.TreasureQuest;
import main.java.com.app.treasurequest.TreasureQuestParser;

public class Main {

    public static void main(String[] args) {
        String input =
                        "C - 3 - 4\n" +
                        "M - 1 - 0\n" +
                        "M - 2 - 1\n" +
                        "T - 0 - 3 - 2\n" +
                        "T - 1 - 3 - 3\n" +
                        "A - Lara - 1 - 1 - S - AADADAGGA";

        TreasureQuest treasureQuest = TreasureQuestParser.getTreasureQuestFromString(input);
        treasureQuest.runSimulation();

        String output = treasureQuest.toString();
        System.out.println(output);

    }
}
