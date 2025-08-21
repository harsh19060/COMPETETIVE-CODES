//code by HARSHVARDHAN TALOKAR

import java.util.*;

class Item {
    int value;
    int weight;
    double ratio;
    int index;

    Item(int index, int value, int weight) {
        this.index = index;
        this.value = value;
        this.weight = weight;
        this.ratio = (weight == 0) ? 0 : (double) value / weight;
    }
}

public class FractionalKnapsack {
    static final int CAPACITY = 850; 
    static final int N = 50;

    static void sortByRatio(Item[] items) {
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));
    }

    static void sortByProfit(Item[] items) {
        Arrays.sort(items, (a, b) -> Integer.compare(b.value, a.value));
    }

    static void sortByWeight(Item[] items) {
        Arrays.sort(items, (a, b) -> Integer.compare(a.weight, b.weight));
    }

    static double knapsack(int capacity, Item[] items) {
        double totalValue = 0.0;
        int currentWeight = 0;

        for (Item item : items) {
            if (item.weight == 0 && item.value > 0) {
                totalValue += item.value;
                continue;
            }
            if (item.weight == 0) continue;

            if (currentWeight + item.weight <= capacity) {
                currentWeight += item.weight;
                totalValue += item.value;
            } else {
                int remain = capacity - currentWeight;
                if (remain > 0) {
                    totalValue += item.ratio * remain; 
                }
                break;
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        int[] weights = {7, 0, 30, 22, 80, 94, 11, 81, 70, 64, 59, 18, 0, 36, 3,
                8, 15, 42, 9, 0, 42, 47, 52, 32, 26, 48, 55,
                6, 29, 84, 2, 4, 18, 56, 7, 29, 93, 44, 71, 3, 86, 66,
                31, 65, 0, 79, 20, 65, 52, 13};

        int[] profits = {360, 83, 59, 130, 431, 67, 230, 52, 93, 125, 670, 892,
                600, 38, 48, 147, 78, 256, 63, 17, 120,
                164, 432, 35, 92, 110, 22, 42, 50, 323, 514, 28, 87, 73,
                78, 15, 26, 78, 210, 36, 85, 189, 274,
                43, 33, 10, 19, 389, 276, 312};

        Item[] items = new Item[N];
        for (int i = 0; i < N; i++) {
            items[i] = new Item(i + 1, profits[i], weights[i]);
        }

        // Ratio method
        sortByRatio(items);
        long start = System.nanoTime();
        double profitRatio = knapsack(CAPACITY, items);
        long end = System.nanoTime();
        System.out.printf("\n[Ratio Method] Total Profit: %.2f | Time: %d ns\n", profitRatio, (end - start));

        // Profit method
        sortByProfit(items);
        start = System.nanoTime();
        double profitMax = knapsack(CAPACITY, items);
        end = System.nanoTime();
        System.out.printf("[Profit Method] Total Profit: %.2f | Time: %d ns\n", profitMax, (end - start));

        // Weight method
        sortByWeight(items);
        start = System.nanoTime();
        double profitMinWeight = knapsack(CAPACITY, items);
        end = System.nanoTime();
        System.out.printf("[Weight Method] Total Profit: %.2f | Time: %d ns\n", profitMinWeight, (end - start));
    }
}
