package search.api;

import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;

public class SearchStateBBCost extends SearchState {
    public SearchStateBBCost(int lcost, Coords point) {
        this.localCost = lcost;
        this.point = point;
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
        int w = searcher.tmp.getWidth();
        int h = searcher.tmp.getDepth();
        int [][] tmpHeights = searcher.tmp.getTmap();

        int x = curStatePoint.getx();
        int y = curStatePoint.gety();
        int cost;

        if (x < w - 1) {
            Coords tempCoord = new Coords(y, x + 1);
            cost = 1;
            if (tmpHeights[y][x + 1] > tmpHeights[y][x]) {
                cost += Math.abs(tmpHeights[y][x + 1] - tmpHeights[y][x]);
            }
            SearchState tmpState = new SearchStateBBCost(cost, tempCoord);
            successorStates.add(tmpState);
        }

        if (y < h - 1) {
            Coords tempCoord = new Coords(y + 1, x);
            cost = 1;
            if (tmpHeights[y + 1][x] > tmpHeights[y][x]) {
                cost += Math.abs(tmpHeights[y][x + 1] - tmpHeights[y][x]);
            }

            SearchState tmpState = new SearchStateBBCost(cost, tempCoord);
            successorStates.add(tmpState);
        }

        if (x > 0) {
            Coords tempCoord = new Coords(y, x - 1);
            cost = 1;
            if (tmpHeights[y][x - 1] > tmpHeights[y][x]) {
                cost += Math.abs(tmpHeights[y][x - 1] - tmpHeights[y][x]);
            }

            SearchState tmpState = new SearchStateBBCost(cost, tempCoord);
            successorStates.add(tmpState);
        }

        if (y > 0) {
            cost = 1;
            Coords tempCoord = new Coords(y - 1 , x);
            if (tmpHeights[y - 1][x] > tmpHeights[y][x]) {
                cost += Math.abs(tmpHeights[y - 1][x] - tmpHeights[y][x]);
            }

            SearchState tmpState = new SearchStateBBCost(cost, tempCoord);
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

}

