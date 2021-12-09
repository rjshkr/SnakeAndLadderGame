package com.design.components;

import com.design.models.Ladder;
import com.design.models.Player;
import com.design.models.Snake;
import com.design.service.DiceService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Board {

    private List<Snake> snakeList;
    private List<Ladder> ladderList;
    private LinkedList<Player> players;
    private List<Player> winners;
    private int endOfBoard;

    public Board(int n) {
        this.endOfBoard = n * n;
        this.winners = new ArrayList<>();
    }

    public List<Snake> getSnakeList() {
        return snakeList;
    }

    public void setSnakeList(List<Snake> snakeList) {
        this.snakeList = snakeList;
    }

    public List<Ladder> getLadderList() {
        return ladderList;
    }

    public void setLadderList(List<Ladder> ladderList) {
        this.ladderList = ladderList;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
    }

    public void launchGame() {
        Scanner sc = new Scanner(System.in);
        while (!isGameCompleted()) {
            Player curPlayer = players.poll();
            System.out.println(curPlayer.getName() + " (" + curPlayer.getCurPosition() + "): Please enter any key to roll dice...");
            sc.next();
            int diceVal = DiceService.roll();
            System.out.println(curPlayer.getName() + " : You have got " + diceVal);
            int prevPosition = curPlayer.getCurPosition();
            makeMove(curPlayer, diceVal);
            if (curPlayer.getCurPosition() == endOfBoard) {
                System.out.println(curPlayer.getName() + " has won the game....");
                winners.add(curPlayer);
            } else if (curPlayer.getCurPosition() > endOfBoard) {
                curPlayer.setCurPosition(prevPosition);
                players.addLast(curPlayer);
            } else {
                System.out.println(curPlayer.getName()+" : Moving from " + prevPosition + " to " + curPlayer.getCurPosition());
                if (diceVal == 6)
                    players.addFirst(curPlayer);
                else
                    players.addLast(curPlayer);
            }
        }
    }

    private void makeMove(Player curPlayer, int diceVal) {
        int newPosition = curPlayer.getCurPosition() + diceVal;
        for (int i = 0; i < snakeList.size(); i++) {
            Snake s = snakeList.get(i);
            if (s.getStart() == newPosition) {
                s.message();
                newPosition = s.getEnd();
            }
        }

        for (int i = 0; i < ladderList.size(); i++) {
            Ladder l = ladderList.get(i);
            if (l.getStart() == newPosition) {
                l.message();
                newPosition = l.getEnd();
            }
        }
        curPlayer.setCurPosition(newPosition);
    }

    private boolean isGameCompleted() {
        return players.isEmpty();
    }
}
