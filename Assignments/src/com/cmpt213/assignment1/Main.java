package com.cmpt213.assignment1;

import java.util.ArrayList;

public class Main {
    private static final int optionSize = 6;
    private static Boolean running = true;
    private static ArrayList<Minion> minions = new ArrayList<>();

    private static void listMinions() {
        String listText = "List of minions:";
        System.out.println(listText);
        IOUtils.printAsterisks(listText.length());
        for (int i = 0; i < minions.size(); i++) {
            System.out.println((i + 1) + ". " + minions.get(i).printFormattedInfo());
        }
    }

    private static void addMinion() {
        System.out.print("Enter minion's name: ");
        String name = IOUtils.readInput();

        System.out.print("Enter minion's height: ");
        double height;
        try {
            height = Double.parseDouble(IOUtils.readInput());
        } catch (NumberFormatException e) {
            System.out.println("Invalid height!");
            return;
        }

        minions.add(new Minion(name, height, 0));
    }

    private static int selectMinion() {
        if (minions.size() < 1) {
            System.out.println("No minions to remove. Please add some!");
            return -1;
        }
        int option;
        while (true) {
            listMinions();
            System.out.println("(Enter 0 to cancel)");
            try {
                option = Integer.parseInt(IOUtils.readInput());
                if (option >= 1 && option <= minions.size()) {
                    return option - 1;
                } else {
                    System.out.println("Invalid option! Please select between 1 and " + minions.size());
                    return -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid option!");
            }
        }
    }

    private static void removeMinion() {
        int index = selectMinion();
        if (index == -1) return;
        minions.remove(index);
    }

    private static void addEvilDeed() {
        int index = selectMinion();
        if (index == -1) return;
        minions.get(index).incrementEvilDeed();
    }

    private static void debug() {
        System.out.println("All minion objects:");
        for (int i = 0; i < minions.size(); i++) {
            System.out.println((i + 1) + ". " + minions.toString());
        }
    }

    private static void handleOptions(int option) {
        switch (option) {
            case 1:
                listMinions();
                return;
            case 2:
                addMinion();
                return;
            case 3:
                removeMinion();
                return;
            case 4:
                addEvilDeed();
                return;
            case 5:
                debug();
                return;
            case 6:
                running = false;
                return;
            default:
                System.out.println("Error: Please enter a selection between 1 and 6");
        }
    }

    public static void main(String[] args) {
	// write your code here
        ArrayList<String> options = new ArrayList<>(optionSize);

        options.add("List minions");
        options.add("Add a new minion");
        options.add("Remove a minion");
        options.add("Attribute evil deed to a minion");
        options.add("DEBUG: Dump objects (toString)");
        options.add("Exit");

        Menu menu = new Menu("Welcome to the Evil Minion Tracker by Andrew", options);

        menu.displayTitle();

        while (running) {
            menu.displayOptions();
            int option = Integer.parseInt(IOUtils.readInput());

            handleOptions(option);
        }
    }
}
