package parallelImplementation;

public class GridSearchResult {
    public static int numSearches;
    public static int localMin;
    public static int finder;

    public GridSearchResult(int numSearches, int localMin, int finder){
        this.numSearches = numSearches;
        this.localMin = localMin;
        this.finder = finder;
    }
}
