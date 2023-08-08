package parallelImplementation;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class GridSearcher extends RecursiveTask<GridSearchResult> {
    private Search[] searches;
    private int numSearches;
    private int threshold;

    private int localMin = Integer.MAX_VALUE;
    private int min = Integer.MAX_VALUE;
    private int finder = -1;

    public GridSearcher(Search[] searches, int numSearches) {
        this.searches = searches;
        this.numSearches = numSearches;

    }

    @Override
    protected GridSearchResult compute() {

        if (numSearches > threshold) {
            for (int i = 0; i < numSearches; i++) {
                localMin = searches[i].find_valleys();
                if ((!searches[i].isStopped()) && (localMin < min)) {
                    min = localMin;
                    finder = i;
                }
            }

            return new GridSearchResult(numSearches, localMin, finder);

        } else {
            int middle = searches.length / 2;

            Search[] leftSearch = Arrays.copyOfRange(searches, 0, middle);
            Search[] rightSearch = Arrays.copyOfRange(searches, middle, searches.length);
            GridSearcher leftTask = new GridSearcher(leftSearch, leftSearch.length);
            GridSearcher rightTask = new GridSearcher(rightSearch, rightSearch.length);

            invokeAll(leftTask, rightTask);

            min = Math.min(leftTask.min, rightTask.min);
            finder = leftTask.min < rightTask.min ? leftTask.finder : rightTask.finder;

            return new GridSearchResult(numSearches, localMin, finder);

        }

    }

}
