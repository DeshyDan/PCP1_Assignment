package parallelImplementation;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class MonteCarloMinimization {
    private static long startTime = 0;
    private static long endTime = 0;

    public static void main(String[] args) throws Exception {
        if (args.length != 7) {
            System.out.println("Incorrect number of command line arguments provided.");
            System.exit(0);
        }
        int rows = Integer.parseInt(args[0]);
        int columns = Integer.parseInt(args[1]);
        double xmin = Double.parseDouble(args[2]);
        double xmax = Double.parseDouble(args[3]);
        double ymin = Double.parseDouble(args[4]);
        double ymax = Double.parseDouble(args[5]);
        double searches_density = Double.parseDouble(args[6]);

        Random rand = new Random();

        // initialize
        TerrainArea terrain = new TerrainArea(rows, columns, xmin, xmax, ymin, ymax);
        int numSearches = (int) (rows * columns * searches_density);
        Search[] searches = new Search[numSearches];
        for (int i = 0; i < numSearches; i++)
            searches[i] = new Search(i + 1, rand.nextInt(rows), rand.nextInt(columns), terrain);

        tick();
        // int min = Integer.MAX_VALUE;
        // int localMin = Integer.MAX_VALUE;
        int finder = -1;

        GridSearcher gridSearch = new GridSearcher(searches, numSearches );
        ForkJoinPool pool = new ForkJoinPool();
        var result= pool.invoke(gridSearch);
        tock();

        numSearches = result[0];
        int min = result[1];
        finder = result[2];

        System.out.printf("Run parameters\n");
        System.out.printf("\t Rows: %d, Columns: %d\n", rows, columns);
        System.out.printf("\t x: [%f, %f], y: [%f, %f]\n", xmin, xmax, ymin, ymax);
        System.out.printf("\t Search density: %f (%d searches)\n", searches_density, numSearches);

        /* Total computation time */
        System.out.printf("Time: %d ms\n", endTime - startTime);
        int tmp = terrain.getGrid_points_visited();
        System.out.printf("Grid points visited: %d  (%2.0f%s)\n", tmp, (tmp / (rows * columns * 1.0)) * 100.0, "%");
        tmp = terrain.getGrid_points_evaluated();
        System.out.printf("Grid points evaluated: %d  (%2.0f%s)\n", tmp, (tmp / (rows * columns * 1.0)) * 100.0, "%");

        /* Results */
        System.out.printf("Global minimum: %d at x=%.1f y=%.1f\n\n", min,
                terrain.getXcoord(searches[finder].getPos_row()), terrain.getYcoord(searches[finder].getPos_col()));

    }

    private static void tick() {
        startTime = System.currentTimeMillis();
    }

    private static void tock() {
        endTime = System.currentTimeMillis();
    }
}