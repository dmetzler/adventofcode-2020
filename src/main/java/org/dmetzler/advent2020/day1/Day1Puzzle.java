/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day1;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class Day1Puzzle {

    private final LinkedList<Integer> expenses;

    private final int sum;

    /**
     * Initialize the puzzle
     *
     * @param url The URL of the input file
     * @param sum The sum to reach (2020)
     * @throws IOException
     */
    public Day1Puzzle(URL url, int sum) throws IOException {
        this.sum = sum;
        this.expenses = new LinkedList<>();
        this.expenses.addAll(ExpenseReportReader.readExpenses(url.openStream()));
    }

    public long solve(int neededNumbers) {
        return solve(0, 1, neededNumbers - 1, expenses);
    }

    /**
     * Solves recursively the puzzle
     *
     * @param sumAcc The accumulator for the sum (starts at 0)
     * @param prodAcc The accumulator for the product (starts at 1)
     * @param neededNumbers The number of number that are still needed
     * @param The report of expenses to run on.
     * @return the resulting product or -1 if nothing was found.
     */
    private long solve(int sumAcc, long prodAcc, int neededNumbers, List<Integer> expenses) {

        for (int i = 0; i < expenses.size(); i++) {
            Integer currentNumber = expenses.get(i);

            int newsumAcc = sumAcc + currentNumber;
            long newProdAcc = prodAcc * currentNumber;
            int newNeededNumbers = neededNumbers - 1;

            if (newsumAcc == sum && neededNumbers == 0) {
                return newProdAcc;
            } else {
                if (expenses.size() > 1 && neededNumbers > 0 && newsumAcc < sum) {
                    long result = solve(newsumAcc, newProdAcc, newNeededNumbers,
                            expenses.subList(i + 1, expenses.size()));
                    if (result != -1) {
                        return result;
                    }
                }
            }
        }
        return -1;

    }

    public static void main(String... arg) {
        try {
            Day1Puzzle puzzle = new Day1Puzzle(Day1Puzzle.class.getResource("/day1-expense-report.txt"), 2020);
            System.out.println(puzzle.solve(2));
            System.out.println(puzzle.solve(3));
            System.out.println("Done");
        } catch (IOException e) {
            System.out.println("Unable to open expense report");
        }
    }

}
