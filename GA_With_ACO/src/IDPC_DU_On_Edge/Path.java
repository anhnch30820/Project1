package IDPC_DU_On_Edge;

import java.util.ArrayList;

public class Path {
	ArrayList<Edge> list = new ArrayList<Edge>();
	int cost = 0;

	public ArrayList<Edge> getList() {
		return list;
	}

	public void setList(ArrayList<Edge> list) {
		this.list = list;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Path() {

	}

	public Path(ArrayList<Edge> list, int cost) {
		super();
		this.list = list;
		this.cost = cost;
	}

	public void tinhCost(Path path) {
		for (int i = 0; i < path.list.size(); i++) {
			this.cost += path.list.get(i).getWeight();
		}
	}

}
