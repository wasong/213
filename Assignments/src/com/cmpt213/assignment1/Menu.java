package com.cmpt213.assignment1;

import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    private String title;
    private ArrayList<String> options;

    public Menu(String title, ArrayList<String> options) {
        this.title = title;
        this.options = options;
    }

    public void displayTitle() {
        // print title
        IOUtils.printAsterisks(title.length());
        System.out.println();
        System.out.println(title);
        IOUtils.printAsterisks(title.length());
        System.out.println("\n");
    }

    public void displayOptions() {
        // print Menu Options
        String menuText = "* Menu Options *";

        for (int i = 0; i < menuText.length(); i++) System.out.print("*");
        System.out.println();
        System.out.println(menuText);
        for (int i = 0; i < menuText.length(); i++) System.out.print("*");
        System.out.println("\n");


        // print options
        for (int i = 0; i < options.size(); i++){
            int listNum = i + 1;
            System.out.println(listNum + ". " + options.get(i));
        }
    }
}
