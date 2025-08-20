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
    static final int CAPACITY = 15; 
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

        System.out.println("\nItem\tValue\tWeight\tTaken\t\tProfit Added\tTotal Profit");

        for (Item item : items) {
            if (item.weight == 0 && item.value > 0) {
                totalValue += item.value;
                System.out.printf("%d\t%d\t%d\tFree\t\t%.2f\t\t%.2f\n",
                        item.index, item.value, item.weight, (double) item.value, totalValue);
                continue;
            }

            if (item.weight == 0) continue; 

            if (currentWeight + item.weight <= capacity) {
                currentWeight += item.weight;
                totalValue += item.value;
                System.out.printf("%d\t%d\t%d\tFull\t\t%.2f\t\t%.2f\n",
                        item.index, item.value, item.weight, (double) item.value, totalValue);
            } else {
                int remain = capacity - currentWeight;
                if (remain > 0) {
                    double partialProfit = item.ratio * remain;
                    totalValue += partialProfit;
                    System.out.printf("%d\t%d\t%d\tPartial(%d)\t%.2f\t\t%.2f\n",
                            item.index, item.value, item.weight, remain, partialProfit, totalValue);
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

        System.out.println("\n--- Sorted by Ratio ---");
        sortByRatio(items);
        System.out.printf("Total Profit: %.2f\n", knapsack(CAPACITY, items));

        System.out.println("\n--- Sorted by Profit ---");
        sortByProfit(items);
        System.out.printf("Total Profit: %.2f\n", knapsack(CAPACITY, items));

        System.out.println("\n--- Sorted by Weight ---");
        sortByWeight(items);
        System.out.printf("Total Profit: %.2f\n", knapsack(CAPACITY, items));
    }
}
