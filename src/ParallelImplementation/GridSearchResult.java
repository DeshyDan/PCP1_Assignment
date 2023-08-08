package parallelImplementation;

public class GridSearchResult {
    public  int numSearches;
    public  int localMin;
    public  int finder;

    public GridSearchResult(int numSearches, int localMin, int finder){
        this.numSearches = numSearches;
        this.localMin = localMin;
        this.finder = finder;
    }
}
