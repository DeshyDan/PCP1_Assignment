package parallelImplementation;

import java.util.concurrent.RecursiveAction;

public class GridSearcher extends RecursiveAction {
    private Search[] searches;
    private int numSearches;
    private int threshold;
    private int start;
    private int end;

    public GridSearcher(Search[] searches, int numSearches, int threshold, int start, int end) {
        this.searches = searches;
        this.numSearches = numSearches;
        this.start = start;
        this.end = end;

    }

    @Override
    protected void compute() {
        if (numSearches < threshold) {

        } else {
            int middle = searches.length / 2;

            GridSearcher grid1 = new GridSearcher(searches, numSearches, threshold, start, middle);
            GridSearcher grid2 = new GridSearcher(searches, numSearches, threshold, middle, end);
            invokeAll(grid1, grid2);

        }
    }

}
