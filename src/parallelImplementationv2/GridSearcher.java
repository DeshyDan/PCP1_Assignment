package parallelImplementationv2;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class GridSearcher extends RecursiveTask<GridSearchResult> {
    private Search[] searches;
    private int numSearches;
    private int threshold = 6_125_000;

    private int localMin = Integer.MAX_VALUE;
    private int min ;
    private int finder;

    public GridSearcher(Search[] searches, int numSearches) {
        this.searches = searches;
        this.numSearches = numSearches;
        this.min = Integer.MAX_VALUE;
            this.finder = -1;
    }

    @Override
    protected GridSearchResult compute() {
     
        if (numSearches < threshold) {
            for (int i = 0; i < numSearches; i++) {
                localMin = searches[i].find_valleys();
                if ((!searches[i].isStopped()) && (localMin < min)) {
                    min = localMin;
                    finder = i;
                }
            }

            return new GridSearchResult(numSearches,min, finder);

        } else {
            int middle = searches.length / 2;

           Search[] leftSearch = Arrays.copyOfRange(searches, 0, middle);
           Search[]  rightSearch = Arrays.copyOfRange(searches, middle, searches.length);
           GridSearcher leftTask = new GridSearcher(leftSearch, leftSearch.length);
           GridSearcher  rightTask = new GridSearcher(rightSearch, rightSearch.length);

            invokeAll(leftTask, rightTask);

            min = Math.min(leftTask.min, rightTask.min);
            finder = leftTask.min < rightTask.min ? leftTask.finder : rightTask.finder;

            return new GridSearchResult(numSearches, min, finder);

        }
//         if (leftSearch != null && rightSearch != null && leftTask != null && rightTask != null) {
//             min = Math.min(leftTask.min, rightTask.min);
//                         finder = leftTask.min < rightTask.min ? leftTask.finder : rightTask.finder;

//             // if (leftTask.min < rightTask.min) {
//             //     finder = leftTask.finder;
//             // } else {
//             //     finder = rightTask.finder;
//             // }
 
//         }
// return new GridSearchResult(numSearches, min, finder);


    }

}