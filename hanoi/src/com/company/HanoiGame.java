package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by barrsmit1 on 6/23/2016.
 */
public class HanoiGame {

    private boolean isCompleted = false;

    private List<Stack<Integer>> towers;

    private List<Integer> moves;

    public HanoiGame() {
        towers = new ArrayList<Stack<Integer>>();
        towers.add(new Stack<Integer>());
        towers.add(new Stack<Integer>());
        towers.add(new Stack<Integer>());

        moves = new ArrayList<Integer>();
    }

    public void run() {
        System.out.println("HANOI GAME");
        System.out.println("ENTER INTEGAR NUMBER OF DISKS!!!!!!!!!!!!!!");
        int diskCount = new Scanner(System.in).nextInt();
        System.out.println("STARTING...");

        initializeTowers(diskCount);
        doGame(diskCount);

        System.out.println("WIN!");
    }

    private void doGame(int diskCount) {
        while (!isCompleted) {
            doTurn(diskCount);
        }
    }

    private void doTurn(int diskCount) {
        printState(diskCount);

        isCompleted = true;
    }

    private void printState(int diskCount) {
        System.out.println("A  B  C");

        for (int i = diskCount; i >= 1; --i) {
            String str = "";
            for (Stack<Integer> tower : towers) {
                if (tower.size() >= i) {
                    str += tower.get(i - 1) + "  ";
                }
                else
                    str += "   ";
            }
//            for (int j = 0; j < towers.size(); ++j) {
//                Stack<Integer> tower = towers.get(j);
//                if (tower.size() >= i) {
//                    str += tower.get(i - 1);
//                }
//                else
//                    str += "  ";
//            }
            System.out.println(str);
        }
    }

    private void initializeTowers(int diskCount) {
        for (int i = 1; i <= diskCount; ++i)
            towers.get(0).push(i);
    }
}
