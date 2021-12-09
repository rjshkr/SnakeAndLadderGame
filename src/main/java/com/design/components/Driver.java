package com.design.components;

import com.design.models.Ladder;
import com.design.models.Player;
import com.design.models.Snake;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Driver {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter board size(nxn) n value ");
        Board board = new Board(sc.nextInt());
        System.out.println("Please enter no of players ... ");

        int noOfPlayers = sc.nextInt();
        System.out.println("Please enter player details");
        LinkedList<Player> players = new LinkedList<>();
        for (int i = 0; i < noOfPlayers; i++) {
            Player p = new Player(sc.nextInt(), sc.next());
            players.add(p);
        }
        List<Snake> snakes = new ArrayList<Snake>();
        System.out.println("Enter no of snakes and details");
        int noOfSnakes = sc.nextInt();
        for (int i = 0; i < noOfSnakes; i++) {
            Snake s = new Snake(sc.nextInt(), sc.nextInt());
            snakes.add(s);
        }
        System.out.println("Enter no of ladders and details");
        int noOfLadders = sc.nextInt();
        List<Ladder> ladders = new ArrayList<Ladder>();
        for (int i = 0; i < noOfLadders; i++) {
            Ladder l = new Ladder(sc.nextInt(), sc.nextInt());
            ladders.add(l);
        }


        board.setPlayers(players);
        board.setLadderList(ladders);
        board.setSnakeList(snakes);

        board.launchGame();

    }
}
