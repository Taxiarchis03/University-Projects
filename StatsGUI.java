import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class StatsGUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Get the input file name from the user.
        System.out.println("Enter the input file name:");
        String inputFileName = scanner.nextLine();
        // Get the output file name from the user.
        System.out.println("Enter the output file name:");
        String outputFileName = scanner.nextLine();
        // Read the data from the input file.
        ArrayList<Integer> data = readDataFromFile(inputFileName);
        // Sort the data in ascending order.
        Collections.sort(data);
        // Calculate the statistics for the data.
        int smallest = findSmallest(data);
        int largest = findLargest(data);
        int numberOfItems = data.size();
        double arithmeticMean = calculateArithmeticMean(data);
        int median = findMedian(data);
        // Print the statistics to the console.
        System.out.println("The smallest number is " + smallest);
        System.out.println("The largest number is " + largest);
        System.out.println("The number of items is " + numberOfItems);
        System.out.println("The arithmetic mean is " + arithmeticMean);
        System.out.println("The median is " + median);
        // Write the sorted data to the output file.
        writeDataToFile(data, outputFileName);
    }
    private static ArrayList<Integer> readDataFromFile(String fileName) {
        ArrayList<Integer> data = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int number = Integer.parseInt(line);
                data.add(number);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    private static int findSmallest(ArrayList<Integer> data) {
        int smallest = data.get(0);
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i) < smallest) {
                smallest = data.get(i);
            }
        }
        return smallest;
    }
    private static int findLargest(ArrayList<Integer> data) {
        int largest = data.get(0);
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i) > largest) {
                largest = data.get(i);
            }
        }
        return largest;
    }
    private static int findMedian(ArrayList<Integer> data) {
        int middleIndex = data.size() / 2;
        if (data.size() % 2 == 0) {
            return (data.get(middleIndex - 1) + data.get(middleIndex)) / 2;
        } else {
            return data.get(middleIndex);
        }
    }
    private static double calculateArithmeticMean(ArrayList<Integer> data) {
        int sum = 0;
        for (int i = 0; i < data.size(); i++) {
            sum += data.get(i);
        }
        return sum / data.size();
    }
    private static void writeDataToFile(ArrayList<Integer> data, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < data.size(); i++) {
                bufferedWriter.write(data.get(i) + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}