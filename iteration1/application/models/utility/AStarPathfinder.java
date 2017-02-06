package application.models.utility;

import application.models.tileState.Directions;
import application.models.tileState.TileState;

import java.util.*;

public class AStarPathfinder {
    private TileState start;
    private TileState goal;
    private node startNode;

    private Queue<node> openList;
    private Queue<node> closedList;

    public AStarPathfinder(TileState start, TileState goal) {
        this.start = start;
        this.goal = goal;
        openList = new LinkedList<>();
        closedList = new LinkedList<>();
        openList.add(new node(this.start, null, 0, null));
    }

    //COSTS: current.getTileInfo().getMovementCost();

    public String execute() {
        startNode = openList.peek();
        node current = openList.peek();
        while (!openList.isEmpty()) {
            current = openList.remove();
            if (current.innerState == goal) {
                //have reached the goal
                break;
            }
            closedList.add(current);

            //consider each neighbor
            for (Map.Entry<Directions, TileState> entry : current.innerState.getNeighbors().entrySet()) {
                if(entry.getValue().getTileInfo().getMovementCost() != Double.POSITIVE_INFINITY){
                    node n = new node(
                            entry.getValue(),
                            current,
                            (current.cost) + (entry.getValue().getTileInfo().getMovementCost()),
                            entry.getKey()
                    );
                    removeIfInClosedAndCostIsMinium(n);
                    removeIfInOpenAndCostIsMinium(n);
                    if (!inOpenList(n) && !inClosedList(n)){
                        openList.add(n);
                    }
                }
            }
        }
        return buildStringPath(current);
    }

    private String buildStringPath(node n){
        String path = "";
        while(n != startNode){
            path = ("_" + n.direction.getValue()) + path;
            n = n.parent;
        }
        path = path.replaceFirst("_", "");
        return path;
    }

    private boolean inOpenList(node n){
        for (node _n : openList) {
            if (_n.innerState == n.innerState) {
                return true;
            }
        }
        return false;
    }
    private boolean inClosedList(node n){
        for (node _n : closedList) {
            if (_n.innerState == n.innerState) {
                return true;
            }
        }
        return false;
    }

    private boolean removeIfInOpenAndCostIsMinium(node n) {
        for (node _n : openList) {
            if (_n.innerState == n.innerState) {
                if(n.cost < _n.cost){ //new cost is less!
                    openList.remove(_n);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean removeIfInClosedAndCostIsMinium(node n) {
        for (node _n : closedList) {
            if (_n.innerState == n.innerState) {
                if(n.cost < _n.cost){
                    closedList.remove(_n);
                    return true;
                }
            }
        }
        return false;
    }

    private class node {
        private node parent;
        private TileState innerState;
        private double cost;
        private Directions direction;

        node(TileState t, node parent, double cost, Directions d) {
            this.innerState = t;
            this.parent = parent;
            this.cost = cost;
            this.direction = d;
        }
    }
}
