import java.io.*;
import java.util.*;

import static java.lang.Double.parseDouble;

public class Proj2 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        // create initial arrayList to take in values
        ArrayList<NBAPlayer> origList = new ArrayList<>();
        int counter = 0;
        while (inputFileNameScanner.hasNextLine() && counter < numLines) {
            String line = inputFileNameScanner.nextLine();
            String [] values = line.split(",");

            // creating NBAPlayer object out of string input
            try {
                if (values.length == 17){
                    int playerID = Integer.parseInt(values[0].trim());
                    int gp = Integer.parseInt(values[1].trim());
                    int gs = Integer.parseInt(values[2].trim());
                    double mpg = parseDouble(values[3].trim());
                    double ppg = parseDouble(values[4].trim());
                    int fgm = Integer.parseInt(values[5].trim());
                    int fga = Integer.parseInt(values[6].trim());
                    double fgp = parseDouble(values[7].trim());
                    int threesMade = Integer.parseInt(values[8].trim());
                    int threesAttempted = Integer.parseInt(values[9].trim());
                    double threePercentage = parseDouble(values[10].trim());
                    int ftm = Integer.parseInt(values[11].trim());
                    int fta = Integer.parseInt(values[12].trim());
                    double ftp = parseDouble(values[13].trim());
                    String player = values[14].trim();
                    String position = values[15].trim();
                    String team = values[16].trim();

                    NBAPlayer newPlayer = new NBAPlayer(playerID, gp, gs, mpg,ppg,fgm, fga, fgp, threesMade,
                            threesAttempted, threePercentage, ftm, fta, ftp, player, position, team);

                    origList.add(newPlayer);
                 }

            }
            catch (Exception e) {
                e.printStackTrace();
            }

            // incrementing so we stop inserting at numLines argument
            counter++;
        }

        // insertions and searches, timed for each type
        if (!origList.isEmpty()) {
            // sorted AVL
            Collections.sort(origList);
            double t1 = System.nanoTime();
            AvlTree sortedAvlTree = new AvlTree();
            for (NBAPlayer player : origList) {
                sortedAvlTree.insert(player);
            }
            System.out.println("Sorted List AVL Tree: ");
            sortedAvlTree.printTree();
            double t2 = System.nanoTime();
            double sortedAVLinsertTime = t2 - t1;

            double t3 = System.nanoTime();
            for (NBAPlayer player : origList) {
                sortedAvlTree.contains(player);
            }
            double t4 = System.nanoTime();
            double sortedAVLsearchTime = t4 - t3;


            // sorted BST
            Collections.sort(origList);
            double t5 = System.nanoTime();
            BST<NBAPlayer> sortedBST = new BST<>();
            for (NBAPlayer player : origList) {
              sortedBST.insert(player);
            }
            System.out.println("Sorted List BST: " + sortedBST.print());
            double t6 = System.nanoTime();
            double sortedBSTInsertTime = t6 - t5;

            double t7 = System.nanoTime();
            for (NBAPlayer player : origList) {
                sortedBST.search(player);
            }
            double t8 = System.nanoTime();
            double sortedBSTSearchTime = t8 - t7;

            // shuffled AVL
            Collections.shuffle(origList);
            double t9 = System.nanoTime();
            AvlTree randAvlTree = new AvlTree();
            for (NBAPlayer player : origList) {
                randAvlTree.insert(player);
            }
//            randAvlTree.printTree();
            double t10 = System.nanoTime();
            double randAVLinsertTime = t10 - t9;

            double t11 = System.nanoTime();
            for (NBAPlayer player : origList) {
                randAvlTree.contains(player);
            }
            double t12 = System.nanoTime();
            double randAVLsearchTime = t12 - t11;

            // shuffled BST
            Collections.shuffle(origList);
            double t13 = System.nanoTime();
            BST<NBAPlayer> randBST = new BST<>();
            for (NBAPlayer player : origList) {
                randBST.insert(player);
            }
            System.out.println("Randomized List BST: " + randBST.print());
            double t14 = System.nanoTime();
            double randBSTInsertTime = t14 - t13;

            double t15 = System.nanoTime();
            for (NBAPlayer player : origList) {
                randBST.search(player);
            }
            double t16 = System.nanoTime();
            double randBSTSearchTime = t16 - t15;

            // outputting scores in CSV format
            try {

                FileWriter writer1 = new FileWriter("output.txt", true);
                BufferedWriter br1 = new BufferedWriter(writer1);

                br1.write(sortedAVLinsertTime + ", " + args[1]);
                br1.newLine();
                br1.write(sortedAVLsearchTime + ", " + args[1]);
                br1.newLine();
                br1.write(sortedBSTInsertTime + ", " +args[1]);
                br1.newLine();
                br1.write(sortedBSTSearchTime + ", " +args[1]);
                br1.newLine();
                br1.write(randAVLinsertTime + ", " + args[1]);
                br1.newLine();
                br1.write(randAVLsearchTime +  ", " +args[1]);
                br1.newLine();
                br1.write(randBSTInsertTime + ", " +args[1]);
                br1.newLine();
                br1.write(randBSTSearchTime + ", " +args[1]);
                br1.newLine();
                br1.close();
                writer1.close();

            }
            catch (IOException e) {
                // error message if can't read to file
                System.out.println("Could not write to file.");
            }

        }
        else {
            System.out.println("No players found");
        }




	// FINISH ME

    }
}
