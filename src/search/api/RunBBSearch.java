package search.api;

public class RunBBSearch {
    public static void main(String[] arg) {
//        TerrainMap tm = new TerrainMap("C:\\Users\\weiyaowu\\IdeaProjects\\hello\\src\\com\\company\\diablo.pgm");
        TerrainMap tm = new TerrainMap("G:\\ml\\SearchAlgorithm3\\src\\search\\api\\tmc.pgm");
        int[][] tmap = tm.getTmap();


        Coords start = new Coords(7, 0);
        Coords end = new Coords(tm.getDepth() - 1, tm.getWidth() - 1);

        Coords end1 = new Coords(5, 8);
        System.out.println(tm.getHeight() - 1);
        System.out.println(tm.getWidth() - 1);

        SearchState initState = new SearchStateBBCost(0, start);
        SearchState goalState = new SearchStateBBCost(0, end);

        BBSearch bbSearch = new BBSearch();
        String result = bbSearch.runSearch(initState, goalState, tm, "branchAndBound");
        System.out.println(result);
    }
}
