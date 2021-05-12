package search.api;

import java.util.ArrayList;

public class SearchStateAStarCost extends SearchState {
    public SearchStateAStarCost(int lcost, Coords point) {
        this.localCost = lcost;
        this.point = point;
    };
    public SearchStateAStarCost(int lcost, Coords point, int estRemCost) {
        this.localCost = lcost;
        this.point = point;
        this.estRemCost = estRemCost;
    };
    public boolean goalPredicate(Search searcher) {
        if (searcher.currentNode.sameState(searcher.goalNode)) {
            return true;
        } else {
            return false;
        }
    };

    public ArrayList<SearchState> getSuccessors(Search searcher) {
        ArrayList<SearchState> successorStates = new ArrayList<SearchState>();
        Coords curStatePoint = searcher.currentNode.get_State().point;
        Coords goalStatePoint = searcher.goalNode.get_State().point;
        String AStarHCostMethod = searcher.AStarHCost;

        int w = searcher.tmp.getWidth();
        int h = searcher.tmp.getDepth();
        int [][] tmpHeights = searcher.tmp.getTmap();

        int x = curStatePoint.getx();
        int y = curStatePoint.gety();
        int cost;
        int HCost;

        if (x < w - 1) {
            Coords tempCoord = new Coords(y, x + 1);
            cost = 1;
            if (tmpHeights[y][x + 1] > tmpHeights[y][x]) {
                cost += Math.abs(tmpHeights[y][x + 1] - tmpHeights[y][x]);
            }

            HCost = HCostCompute(tempCoord, goalStatePoint, tmpHeights, AStarHCostMethod);
            SearchState tmpState = new SearchStateAStarCost(cost, tempCoord, HCost);
            successorStates.add(tmpState);
        }

        if (y < h - 1) {
            Coords tempCoord = new Coords(y + 1, x);
            cost = 1;
            if (tmpHeights[y + 1][x] > tmpHeights[y][x]) {
                cost += Math.abs(tmpHeights[y][x + 1] - tmpHeights[y][x]);
            }

            HCost = HCostCompute(tempCoord, goalStatePoint, tmpHeights, AStarHCostMethod);
            SearchState tmpState = new SearchStateAStarCost(cost, tempCoord, HCost);
            successorStates.add(tmpState);
        }

        if (x > 0) {
            Coords tempCoord = new Coords(y, x - 1);
            cost = 1;
            if (tmpHeights[y][x - 1] > tmpHeights[y][x]) {
                cost += Math.abs(tmpHeights[y][x - 1] - tmpHeights[y][x]);
            }

            HCost = HCostCompute(tempCoord, goalStatePoint, tmpHeights, AStarHCostMethod);
            SearchState tmpState = new SearchStateAStarCost(cost, tempCoord, HCost);
            successorStates.add(tmpState);
        }

        if (y > 0) {
            cost = 1;
            Coords tempCoord = new Coords(y - 1 , x);
            if (tmpHeights[y - 1][x] > tmpHeights[y][x]) {
                cost += Math.abs(tmpHeights[y - 1][x] - tmpHeights[y][x]);
            }

            HCost = HCostCompute(tempCoord, goalStatePoint, tmpHeights, AStarHCostMethod);
            SearchState tmpState = new SearchStateAStarCost(cost, tempCoord, HCost);
            successorStates.add(tmpState);
        }

        return successorStates;
    };

    public boolean sameState(SearchState n2) {
        if (this.point.getx() == n2.point.getx() && this.point.gety() == this.point.gety()) {
            return true;
        } else {
            return false;
        }
    };

    // heuristics distances

    // Manhattan Distance
    public int ManhattanDistance(Coords curPoint, Coords goalPoint) {
        return Math.abs(curPoint.getx() - goalPoint.getx()) + Math.abs(curPoint.gety() - goalPoint.gety());
    }

    // Euclidean Distance
    public int EuclideanDistance(Coords curPoint, Coords goalPoint) {
        return (int)Math.ceil(Math.sqrt(Math.pow((curPoint.getx() - goalPoint.getx()), 2) +
                Math.pow((curPoint.gety() - goalPoint.gety()), 2)));
    }

    // Height Difference
    public int HeightDifference(Coords curPoint, Coords goalPoint, int[][] tmpHeights) {
        int curHeight = tmpHeights[curPoint.gety()][curPoint.getx()];
        int goalHeight = tmpHeights[goalPoint.gety()][goalPoint.getx()];
        int HCost = 1;
        if (curHeight < goalHeight) {
            HCost = goalHeight - curHeight;
        }
        return HCost;
    }

    // Manhattan Distance with Height Difference
    public int ManhattanWithHeight(Coords curPoint, Coords goalPoint, int[][] tmpHeights) {
        return ManhattanDistance(curPoint, goalPoint) + HeightDifference(curPoint, goalPoint, tmpHeights);
    }

    // Euclidean Distance with Height Difference
    public int EuclideanWithHeight(Coords curPoint, Coords goalPoint, int[][] tmpHeights) {
        return EuclideanDistance(curPoint, goalPoint) + HeightDifference(curPoint, goalPoint, tmpHeights);
    }

    public int HCostCompute(Coords curPoint, Coords goalPoint, int[][] tmpHeights, String HCostMethod) {
        if (HCostMethod == "Manhattan") {
            return ManhattanDistance(curPoint, goalPoint);
        } else if (HCostMethod == "Euclidean") {
            return EuclideanDistance(curPoint, goalPoint);
        } else if (HCostMethod == "HeightDifference") {
            return  HeightDifference(curPoint, goalPoint, tmpHeights);
        } else if (HCostMethod == "ManhattanWithHeight") {
            return ManhattanWithHeight(curPoint, goalPoint, tmpHeights);
        } else if (HCostMethod == "EuclideanWithHeight") {
            return EuclideanWithHeight(curPoint, goalPoint, tmpHeights);
        } else {
            return 0;
        }

    }

}
