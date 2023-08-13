package parallelImplementation;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class GridSearcher extends RecursiveTask<Integer[]> {
    private SearchParallel[] searches;
    private int numSearches;
    private int threshold = 3000;

    private int localMin = Integer.MAX_VALUE;
    private int min ;
    private int finder;

    public GridSearcher(SearchParallel[] searches, int numSearches) {
        this.searches = searches;
        this.numSearches = numSearches;
        this.min = Integer.MAX_VALUE;
            this.finder = -1;
    }

    @Override
    protected Integer[] compute() {
     
        if (numSearches < threshold) {
            for (int i = 0; i < numSearches; i++) {
                localMin = searches[i].find_valleys();
                if ((!searches[i].isStopped()) && (localMin < min)) {
                    min = localMin;
                    finder = i;
                }
            }

                        return new Integer[]{numSearches, min , finder};


        } else {
            int middle = searches.length / 2;

           SearchParallel[] leftSearch = Arrays.copyOfRange(searches, 0, middle);
           SearchParallel[]  rightSearch = Arrays.copyOfRange(searches, middle, searches.length);
           GridSearcher leftTask = new GridSearcher(leftSearch, leftSearch.length);
           GridSearcher  rightTask = new GridSearcher(rightSearch, rightSearch.length);

            invokeAll(leftTask, rightTask);

          if(leftTask.min < rightTask.min){
              min = leftTask.min;
              finder = leftTask.finder;
          }else{
              min = rightTask.min;
               finder = rightTask.finder;
          }

            return new Integer[]{numSearches, min , finder};

        }

    }

}
