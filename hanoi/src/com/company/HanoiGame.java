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

    private int discCount;

    public HanoiGame() {
        setUp();
    }

    public void run() {

        System.out.println("HANOI GAME");
        System.out.println("ENTER INTEGAR NUMBER OF DISKS!!!!!!!!!!!!!!");
        this.discCount = new Scanner(System.in).nextInt();
        System.out.println("STARTING...");
        System.out.println();

        initializeTowers();
        doGame();

        System.out.println("WIN!");
    }

    private void doGame() {
        while (!isCompleted) {
            doTurn();
        }
    }

    private void doTurn() {

        printState();

        if (isWinner())
            isCompleted = true;
        else {
            int towerToMove = findTowerToMove();
            moveDisc(towerToMove, getMove(towerToMove));
        }
    }

    private void printState() {

        System.out.println("| MOVE # " + moves.size());

        for (int i = discCount; i >= 1; --i) {
            String str = "";
            for (Stack<Integer> tower : towers) {
                if (tower.size() >= i) {
                    str += tower.get(i - 1) + "  ";
                }
                else
                    str += "   ";
            }
            if (str.trim().length() > 0)
                System.out.println("| " + str);
        }
        System.out.println("| --------");
        System.out.println("| A  B  C");
        System.out.println();
        System.out.println();
    }

    private void moveDisc(int sourceTowerIndex, int destTowerIndex) {

        isMoveLegal(sourceTowerIndex, destTowerIndex);

        int discToMove = towers.get(sourceTowerIndex).pop();

        moves.add(discToMove);

        towers.get(destTowerIndex).push(discToMove);
    }

    private boolean isMoveLegal(int sourceTowerIndex, int destTowerIndex) {
        return getTopDisc(sourceTowerIndex) > getTopDisc(destTowerIndex);
    }

    private boolean isWinner() {
        return
            (towers.get(0).size() == 0
                && towers.get(1).size() == 0);
    }

    private void initializeTowers() {
        for (int i = 1; i <= discCount; ++i)
            towers.get(0).push(i);
    }

    private int getTopDisc(int towerIndex) {
        int result = -1;
        Stack<Integer> tower = towers.get(towerIndex);
        if (tower.size() > 0)
            result = tower.get(tower.size() - 1);
        return result;
    }

    private int findTowerToMove() {
        int result = -1;
        if (moves.size() == 0)
            result = 0;
        else {
            int lastDisc = moves.get(moves.size() - 1);
            boolean lastIsOdd = (lastDisc % 2 == 1);
            List<Integer> candidateMoves = getCandidateMoves();
            List<Integer> goodMoves = getOddMoves(candidateMoves, !lastIsOdd);
            List<Integer> badMoves = getOddMoves(candidateMoves, lastIsOdd);
            if (goodMoves.size() == 1)
                result = goodMoves.get(0);
            else if (goodMoves.size() == 2)
                result = getLeastRecentTower(goodMoves.get(0), goodMoves.get(1));
            else if (badMoves.size() == 1)
                result = badMoves.get(0);
            else if (badMoves.size() == 2)
                result  = getLeastRecentTower(badMoves.get(0), badMoves.get(1));
        }
        return result;
    }

    private List<Integer> getOddMoves(List<Integer> candidates, boolean odd) {
        List<Integer> results = new ArrayList<Integer>();
        for (int n : candidates)
            if (odd && isTowerOdd(n)) results.add(n);
            else if (!odd && !isTowerOdd(n)) results.add(n);
        return results;
    }

    private int getLeastRecentTower(int tower1, int tower2) {
        int result = -1;
        for (int i = moves.size() - 1; i >= 0 && result == -1; --i) {
            int move = moves.get(i);
            if (move == tower1) result = tower2;
            else if (move == tower2) result = tower1;
        }
        return  result;
    }

    private boolean isTowerOdd(int tower) {
        return getTopDisc(tower) % 2 == 1;
    }

    private int getMove(int currentTower) {
        if (isTowerOdd(currentTower))
            --currentTower;
        else
            ++currentTower;
        if (currentTower < 0) currentTower = 2;
        if (currentTower > 2) currentTower = 0;
        return currentTower;
    }

    private List<Integer> getCandidateMoves() {
        List<Integer> results = new ArrayList<Integer>();
        for (int i = 0; i < 3; ++i) {
            int destTower = getMove(i);
            if (isMoveLegal(i, destTower))
                results.add(i);
        }
        return results;
    }

    private void setUp() {

        towers = new ArrayList<Stack<Integer>>();
        towers.add(new Stack<Integer>());
        towers.add(new Stack<Integer>());
        towers.add(new Stack<Integer>());

        moves = new ArrayList<Integer>();
    }
}
