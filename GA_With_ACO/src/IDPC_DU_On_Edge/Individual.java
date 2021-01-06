package IDPC_DU_On_Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;




public class Individual implements Comparable<Individual>{
	int domain[];
	int cost;
	static Random rd = new Random(1);
	ArrayList<Edge> list = new ArrayList<Edge>();
	public Individual(int num_Domain) {
		this.domain = new int[num_Domain];
	}
	public List<Integer> hoanVi(int length) {
		List<Integer> solution = new ArrayList<>();
		for (int i = 1; i <= length; i++) {
			solution.add(i);
		}
		Collections.shuffle(solution);
		return solution;
	}
	public void khoiTaoCaThe() {
		List<Integer> a = hoanVi(domain.length);
		for(int i=0; i<domain.length; i++)
			this.domain[i] = a.get(i);
		
	}
	public void khoiTaoCaThe1(int start, ArrayList<Edge>[] g) {
		
		
		ArrayList<Integer> select = new ArrayList<Integer>();
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		for(int i=0; i<g[start].size(); i++) {
			int a = g[start].get(i).getDomain();
			if(!tmp.contains(a)) {
				tmp.add(a);
			
				}
			
		}
		int a = rd.nextInt(tmp.size());
		select.add(tmp.get(a));
		while (select.size() < this.domain.length) {
			int random_Index = rd.nextInt(this.domain.length)+1;
			if (!select.contains(random_Index)) {
				select.add(random_Index);		
			}
		}
		for(int i=0; i<this.domain.length; i++)
			this.domain[i] = select.get(i);
        
		
		
	}
	public Individual() {
		
	}
	public Individual(Individual id) {
		this.domain = Arrays.copyOf(id.domain, id.domain.length);
		this.cost = id.cost;
	}
	@Override
	public int compareTo(Individual o) {
		if(this.cost<o.cost)
			return -1;
		if(this.cost>o.cost)
			return 1; 
		return 0;
	}
	
}
