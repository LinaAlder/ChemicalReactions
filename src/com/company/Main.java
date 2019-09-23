package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("C:\\Users\\lina_\\Desktop\\tests-easy\\3");
        Scanner in = new Scanner(file);

        //Sorted TreeSet for all available substrates
        SortedSet<Integer> subs = new TreeSet<>();

        //Read 1 line with substrates and add them into the TeeSet subs
        String substrates = in.nextLine();
        String[] substratesArray = substrates.split(" ");
        for (int i = 0; i < substratesArray.length; i++) {
            subs.add(Integer.parseInt(substratesArray[i]));
        }

        //Output initial set of reagents
        System.out.print("Исходные реагенты:");
        for (int s : subs) {
            System.out.print(" " + s);
        }

        int j = 1; //Reaction number
        int n = 1; //Check for new reagents

        while (n > 0) {
            n = 0;

            in = new Scanner(file);
            in.nextLine();

            while (in.hasNext()) {

                System.out.print("\n\n=====| Реакция " + j + " |=====");

                //Line break to substrates and products
                String reaction = in.nextLine();
                String[] reactions = reaction.split("->");

                //Line break substrates one by one
                String[] subNeed = reactions[0].split("\\+");

                SortedSet<Integer> subReactCollection = new TreeSet<>();
                for (int i = 0; i < subNeed.length; i++) {
                    subReactCollection.add(Integer.parseInt(subNeed[i]));
                }

                System.out.print("\nРеагенты необходимые для реакции: ");
                for (int s : subReactCollection) {
                    System.out.print(" " + s);
                }

                /*
                If contains all substrates, then add reaction products to TreeSet for all available substrates,
                and rise n - has new products.
                */
                if (subs.containsAll(subReactCollection)) {
                    System.out.println("\nСодержит все реагенты.");

                    String[] reactAfterReact = reactions[1].split("\\+");

                    SortedSet<Integer> afterReact = new TreeSet<>();
                    for (int i = 0; i < reactAfterReact.length; i++) {
                        afterReact.add(Integer.parseInt(reactAfterReact[i]));
                    }

                    System.out.print("Продукты после реакции: ");
                    for (int s : afterReact) {
                        System.out.print("  " + s);
                    }

                    int oldSize = subs.size();
                    subs.addAll(afterReact);
                    int newSize = subs.size();
                    if (newSize - oldSize > 0) n++;

                } else {
                    System.out.println("\nСодержит не все реагенты.");
                    n = 0;
                }

                j++;
            }
        }

        System.out.print("\nВсе продукты полученные после всех реакций:");
        for (int s : subs) {
            System.out.print(" " + s);
        }
    }
}
