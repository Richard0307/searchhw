package search.api;

public class RunAStarSearch {
    public static void main(String[] arg) {
//        TerrainMap tm = new TerrainMap("C:\\Users\\weiyaowu\\IdeaProjects\\hello\\src\\com\\company\\diablo.pgm");
        TerrainMap tm = new TerrainMap("G:\\ml\\SearchAlgorithm3\\src\\search\\api\\tmc.pgm");
        int[][] tmap = tm.getTmap();


        Coords start = new Coords(7, 0);
        Coords end = new Coords(tm.getDepth() - 1, tm.getWidth() - 1);

        Coords end1 = new Coords(5, 8);
        System.out.println(tm.getHeight() - 1);
        System.out.println(tm.getWidth() - 1);

        SearchState initState = new SearchStateAStarCost(0, start);
        SearchState goalState = new SearchStateAStarCost(0, end);

        AStarSearch AStarSearch = new AStarSearch();
        AStarSearch.runSearchAStar(initState, goalState, tm, "AStar", "Manhattan");
        double efficiency_MD = AStarSearch.efficiency;

        AStarSearch.runSearchAStar(initState, goalState, tm, "AStar", "Euclidean");
        double efficiency_ED = AStarSearch.efficiency;

        AStarSearch.runSearchAStar(initState, goalState, tm, "AStar", "HeightDifference");
        double efficiency_HD = AStarSearch.efficiency;

        AStarSearch.runSearchAStar(initState, goalState, tm, "AStar", "ManhattanWithHeight");
        double efficiency_MDHD = AStarSearch.efficiency;

        AStarSearch.runSearchAStar(initState, goalState, tm, "AStar", "EuclideanWithHeight");
        double efficiency_EDHD = AStarSearch.efficiency;

        System.out.println("efficiency_MD " + efficiency_MD);
        System.out.println("efficiency_ED " + efficiency_ED);
        System.out.println("efficiency_HD " + efficiency_HD);
        System.out.println("efficiency_MDHD " + efficiency_MDHD);
        System.out.println("efficiency_EDHD " + efficiency_EDHD);
    }
}
