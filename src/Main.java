import java.io.File;
import java.util.Scanner;

public class Main {
    static File fileToRead=new File("./linkabilityNetworksData/boredapeyachtclub.csv");
    static int columnFrom=4;
    static int columnTo=5;
    static File fileToWrite=new File("./linkabilityNetwork.csv");
    enum Mode{
        Sequential,
        Parallel,
        Distributed,
    }
    public static void main(String[] args) {
        System.out.println("TRANSACTION NETWORK ANALYSIS");
        System.out.println("Specify which mode you would prefer by typing in the corresponding number:");
        System.out.println("1 for sequential mode");
        System.out.println("2 for parallel mode");
        System.out.println("3 for distributed mode");
        Scanner s = new Scanner(System.in);
        int input = 0;
        try {
            input = s.nextInt();
        } catch (Exception e) {
            System.out.println("Unexpected error occurred.");
        }
        Mode currentMode;
        switch (input) {
            case 1 -> currentMode = Mode.Sequential;
            case 2, 3 -> {
                System.out.println("Sorry, this mode hasn't been implemented yet.");
                System.exit(0);}
            default -> {
                System.out.println("Please enter a valid mode.");
                System.exit(0);}
        }
        System.out.println("Specify maximum depth od ETN traversal:");
        int depth=0;
        try {
            depth = s.nextInt();
        } catch (Exception e) {
            System.out.println("Unexpected error occurred.");
        }

        long start=System.currentTimeMillis();
        GraphSequential ETN=new GraphSequential();
        ETN.readFromFile(fileToRead, columnFrom, columnTo);
        LinkabilityNetworkSequential l=new LinkabilityNetworkSequential(ETN, depth, fileToWrite);
        l.printWeights();
        long end=System.currentTimeMillis();
        System.out.println("Run-time: "+(end-start)+"ms");
    }
}
