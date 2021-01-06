package IDPC_DU_On_Edge;

import java.util.ArrayList;



public class Population {
	int num_Solution = 50;

	public Individual[] khoiTaoQuanThe(int num_Domain, ArrayList<Edge>[] g) {
		Individual[] ListIndividual = new Individual[num_Solution];

		for (int i = 0; i < ListIndividual.length; i++) {
			ListIndividual[i] = new Individual(num_Domain);
			ListIndividual[i].khoiTaoCaThe();
//			ListIndividual[i].khoiTaoCaThe1(g[0].get(0).getWeight(), g);
		}
		return ListIndividual;
	}
}
