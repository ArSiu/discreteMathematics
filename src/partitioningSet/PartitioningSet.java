package partitioningSet;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Partitioning Set Algorithm Implementation
 * @author ArSiu
 * @version 1.0
 */

public class PartitioningSet {
    private int n;
    private int[] block;
    private int[] nBlock;
    private int[] pBlock;
    private boolean[] moveForward;

    public PartitioningSet() {
        this.n = scanInt("n");
        this.block = new int[n];
        this.nBlock = new int[n];
        this.pBlock = new int[n];
        this.moveForward = new boolean[n];
        fillArrays();
        partitionsOfSet();
    }

    private int scanInt(final String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input " + name +":");
        while (!scanner.hasNextInt()) {
            System.out.println("That's not a number!Try again");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void fillArrays() {
        for (int i = 0; i < n; i++) {
            block[i] = 0;
            moveForward[i] = true;
        }
        nBlock[0] = 0;
    }

    private void printPartition(List<Integer> seq) {
        List<List<Integer>> blocks =  new ArrayList<>();
        for (int i = 0; i < seq.size(); i++) {
            blocks.add(new ArrayList<>());
        }
        for (int i = 0; i < seq.size(); i++) {
            blocks.get(seq.get(i)).add(i);
        }

        for (var part : blocks) {
            if (!part.isEmpty()) {
                System.out.print(" { ");
                for (int j = 0; j < part.size(); j++) {
                    if (j != 0) {
                        System.out.print(" ");
                    }
                    System.out.print(part.get(j) + 1);
                }
                System.out.print(" } ");
            }
        }
        System.out.println();
    }

    public void partitionsOfSet() {
        printPartition(Arrays.stream(block).boxed().collect(Collectors.toList()));
        int j = n - 1;
        while (j > 0) {
            int k = block[j];
            if (moveForward[j]) {
                if (nBlock[k] == 0) {
                    nBlock[k] = j;
                    pBlock[j] = k;
                    nBlock[j] = 0;
                }
                if (nBlock[k] > j) {
                    pBlock[j] = k;
                    nBlock[j] = nBlock[k];
                    pBlock[nBlock[j]] = j;
                    nBlock[k] = j;
                }
                block[j] = nBlock[k];
            } else {
                block[j] = pBlock[k];
                if (k == j) {
                    if (nBlock[k] == 0) {
                        nBlock[pBlock[k]] = 0;
                    } else {
                        nBlock[pBlock[k]] = nBlock[k];
                        pBlock[nBlock[k]] = pBlock[k];
                    }
                }
            }
            printPartition(Arrays.stream(block).boxed().collect(Collectors.toList()));
            j = n - 1;
            while ((j > 0) && (((moveForward[j] && block[j] == j) || (!moveForward[j] && block[j] == 0)))) {
                moveForward[j] = !moveForward[j];
                j = j - 1;
            }
        }
    }

}
