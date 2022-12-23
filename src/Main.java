import java.util.Arrays;
import java.util.Random;

public class Main {


    // Naive Matching Approach
    public static int findNaiveMatchingString(String sequence, String pattern) {
        // Implement the naive matching approach

        for (int i = 0; i < sequence.length() - pattern.length(); i++) {
            for (int j = 0; j < pattern.length(); j++) {
                if (sequence.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
                if (j == pattern.length() - 1) {
                    return i;
                }
            }
        }
        return -1;
    }

    // KMP Matching Approach
    public static int findKMPMatchingString(String sequence, String pattern) {
        // Create the prefix table

        int j;
        int[] prefixTable = createPrefixTable(pattern);
        // Implement the KMP matching approach
        int i = 0;
        j = 0;
        while (i < sequence.length()) {
            if (sequence.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = prefixTable[j - 1];
                } else {
                    i++;
                }
            }
            if (j == pattern.length()) {
                return i - j;
            }
        }
        return -1;
    }

    private static int[] createPrefixTable(String pattern) {
        int[] prefixTable = new int[pattern.length()];
        prefixTable[0] = 0;
        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                prefixTable[i] = j + 1;
                j++;
            } else {
                if (j != 0) {
                    j = prefixTable[j - 1];
                    i--;
                } else {
                    prefixTable[i] = 0;
                }
            }
        }
        return prefixTable;
    }

    // Boyer Moore Matching Approach
    public static int findBoyerMooreMatchingString(String sequence, String pattern) {
        // Create the bad character table
        int[] badCharacterTable = new int[256];
        Arrays.fill(badCharacterTable, -1);
        for (int i = 0; i < pattern.length(); i++) {
            badCharacterTable[pattern.charAt(i)] = i;
        }
        // Create the good suffix table
        int[] goodSuffixTable = new int[pattern.length()];
        int[] prefixTable = createPrefixTable(pattern);
        for (int i = 0; i < pattern.length(); i++) {
            goodSuffixTable[i] = pattern.length();
        }
        int k = 0;
        for (int i = pattern.length() - 1; i >= 0; i--) {
            if (prefixTable[i] == i + 1) {
                for (; k < pattern.length() - 1 - i; k++) {
                    if (goodSuffixTable[k] == pattern.length()) {
                        goodSuffixTable[k] = pattern.length() - 1 - i;
                    }
                }
            }
        }
        for (int i = 0; i < pattern.length() - 1; i++) {
            goodSuffixTable[pattern.length() - 1 - prefixTable[i]] = pattern.length() - 1 - i;
        }
        // Implement the Boyer Moore matching approach
        int i = 0;
        while (i <= sequence.length() - pattern.length()) {
            int j1 = pattern.length() - 1;
            while (j1 >= 0 && pattern.charAt(j1) == sequence.charAt(i + j1)) {
                j1--;
            }
            if (j1 < 0) {
                return i;
            }
            i += Math.max(j1 - badCharacterTable[sequence.charAt(i + j1)], goodSuffixTable[j1]);
        }

        return -1;
    }

    public static void main(String[] args) {
        // Create random sequence and pattern of capital letters, lowercase letters, and digits. Sequence should be 100000 characters long and pattern should be 20 characters long.
        StringBuilder sequence = new StringBuilder();
        StringBuilder pattern = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 1000000000; i++) {
            sequence.append((char) (random.nextInt(62) + 48));
        }
        // Pick a random substring of the sequence to be the pattern
        int start = random.nextInt(1000000000 - 20);
        pattern.append(sequence.substring(start, start + 20));
        // Test the three approaches and log each time it took and then at the end print print what approach was the fastest and by what percent it was faster (than each other one)
        long startTime = System.nanoTime();
        System.out.println(findNaiveMatchingString(sequence.toString(), pattern.toString()));
        long firstTime = System.nanoTime() - startTime;
        startTime = System.nanoTime();
        System.out.println(findKMPMatchingString(sequence.toString(), pattern.toString()));
        long secondTime = System.nanoTime() - startTime;
        startTime = System.nanoTime();
        System.out.println(findBoyerMooreMatchingString(sequence.toString(), pattern.toString()));
        long thirdTime = System.nanoTime() - startTime;
        System.out.println("Naive: " + firstTime);
        System.out.println("KMP: " + secondTime);
        System.out.println("Boyer Moore: " + thirdTime);
        // Find the fastest approach and by how many nanoseconds it was faster
        if (firstTime < secondTime && firstTime < thirdTime) {
            System.out.println("Naive is the fastest by " + (secondTime - firstTime) + " nanoseconds");
        } else if (secondTime < firstTime && secondTime < thirdTime) {
            System.out.println("KMP is the fastest by " + (firstTime - secondTime) + " nanoseconds");
        } else {
            System.out.println("Boyer Moore is the fastest by " + (firstTime - thirdTime) + " nanoseconds");
        }

    }

}