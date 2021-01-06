package IDPC_DU_On_Edge;


public class Edge {
	private int start;
	private int end;
	private int weight;
	private int domain;
	double mui;
	double p;
	double dentaij = 0;
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getDomain() {
		return domain;
	}

	public void setDomain(int domain) {
		this.domain = domain;
	}

	public Edge() {
		super();
	}

	public Edge(int start, int end, int weight, int domain) {
		super();
		this.start = start;
		this.end = end;
		this.weight = weight;
		this.domain = domain;
	}
	public Edge(Edge e) {
		this.start = e.getStart();
		this.end = e.getEnd();
		this.weight = e.getWeight();
		this.domain = e.getDomain();
	}
}

