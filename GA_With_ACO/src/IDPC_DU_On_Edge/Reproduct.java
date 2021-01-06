package IDPC_DU_On_Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;



public class Reproduct {
	static double Pm = 0.1;
	static double Pc = 0.65;
	static Random rd = Main.rd;

	public static double[] tinh_Xac_Suat(Individual[] list) {
		double F = 0;
		double q = 0;
		double p[] = new double[list.length];
		
		double tmp[] = new double[list.length];
		for (int i = 0; i < list.length; i++)
			tmp[i] = 1.0/list[i].cost;
		F = 0;
		for (int i = 0; i < list.length; i++)
			F += tmp[i];
		for (int i = 0; i < list.length; i++) {
			q += tmp[i] / F;
			p[i] = q;
			if (i == list.length - 1)
				p[i] = 1;
		}
		return p;
	}

	public static Individual select_Rulet(Individual[] list, double[] p, double p_rulet) {
		Individual id = new Individual();

		if (p_rulet < p[0])
			id = new Individual(list[0]);
		else
			for (int i = 0; i < p.length-1; i++)
				if (p_rulet > p[i] && p_rulet <= p[i + 1])
					id = new Individual(list[i+1]);

		return id;
	}

	public static Individual[] Reproduct(Individual[] list, double[] p) {
		Individual[] list_Off = new Individual[list.length];
		Individual[] list_Off_And_Parent = new Individual[list.length * 2];
		int dem = 0;
		while (dem != list.length) {
			double p_rulet = rd.nextDouble();
			list_Off[dem] = select_Rulet(list, p, p_rulet);
			dem++;
			int dem1 = 0;
			if (dem % 2 == 0) {
				
				while (list_Off[dem - 1].domain[dem1] == list_Off[dem - 2].domain[dem1]) {
					if (list_Off[dem - 1].domain[dem1] != list_Off[dem - 2].domain[dem1])
						break;
					dem1++;
					
					if (dem1 == list_Off[dem - 1].domain.length) {
						dem1 = 0;
						
						p_rulet = rd.nextDouble();
						list_Off[dem - 1] = select_Rulet(list, p, p_rulet);
					}
				}

				double pc = rd.nextDouble();
				if (pc > Pc) {
					dem = dem - 2;
					continue;
				}
				ArrayList<Individual> list_Save = Crossover(list_Off[dem - 2], list[dem - 1]);
				list_Off[dem - 2] = list_Save.get(0);
				list_Off[dem - 1] = list_Save.get(1);
				list_Off[dem - 2] = Mutation(list_Off[dem - 2]);
				list_Off[dem - 1] = Mutation(list_Off[dem - 1]);
			}
		}
		Main.cal_Cost(list_Off);
		
		int vitri =0;
		int cost = 9999;
		for(int i=0; i<list.length; i++)
			if(list[i].cost < cost) {
				vitri = i;
				cost = list[i].cost;
			}
		int vitri1 =0;
		cost = -9999;
		for(int i=0; i<list.length; i++)
			if(list[i].cost > cost) {
				vitri1 = i;
				cost = list[i].cost;
			}
		list_Off[vitri1] = list[vitri];
		
//		ArrayList<Individual> list_All = new ArrayList<Individual>();
//		for (int i = 0; i < list.length; i++)
//			list_All.add(list[i]);
//		for (int i = 0; i < list_Off.length; i++)
//			list_All.add(list_Off[i]);
//		
//
//		Collections.sort(list_All);
//		
//		
//		
//		Individual[] tmp1 = new Individual[list.length];
//
//		for (int i = 0; i < list.length; i++)
//			tmp1[i] = list_All.get(i);
//		
//		return tmp1;
		
		// rulet ALL
//		Individual[] list_All = new Individual[list.length*2];
//		for(int i=0; i<list.length; i++)
//			list_All[i] = list[i];
//		for(int i=list.length; i<list_All.length;i++)
//			list_All[i] = list_Off[i-list.length];
//		list = select_pop(list_All);
		
		return list_Off;
	}

	

	public static Individual[] select_pop(Individual[] list) {
		Individual[] list_reproduct = new Individual[list.length / 2];
		double[] p = tinh_Xac_Suat(list);
		for (int i = 0; i < list_reproduct.length; i++) {		
			double p_rulet = rd.nextDouble();
			list_reproduct[i] = select_Rulet(list, p, p_rulet);
		}
		return list_reproduct;
	}

	public static ArrayList<Individual> Crossover(Individual id1, Individual id2) {
		ArrayList<Individual> list = new ArrayList<Individual>();
		Individual off_Spring1 = new Individual(id2);
		Individual off_Spring2 = new Individual(id1);
		

		int point_1 = rd.nextInt(id1.domain.length);
		int point_2 = rd.nextInt(id2.domain.length);
		while (point_1 == point_2) {
			point_2 = rd.nextInt(id2.domain.length);
			
		}
		if (point_1 > point_2) {
			int tmp = point_1;
			point_1 = point_2;
			point_2 = tmp;
		}
		for (int i = point_1; i <= point_2; i++) {
			int tmp = off_Spring1.domain[i];
			off_Spring1.domain[i] = off_Spring2.domain[i];
			off_Spring2.domain[i] = tmp;
		}
		
		PMX(id1, off_Spring2, point_1, point_2);
		
		PMX(id2, off_Spring1, point_1, point_2);
		
		list.add(off_Spring1);
		list.add(off_Spring2);

		return list;
	}

	public static void PMX(Individual parent1, Individual o2, int point_1, int point_2) {
		for (int i = 0; i < parent1.domain.length; i++) {
			
			if (point_1 <= i && i <= point_2)
				continue;
			int vitri = -1;
			for (int j = point_1; j <= point_2; j++) {
				if (parent1.domain[i] == o2.domain[j]) {
					vitri = j;
					break;
				}
			}
			if (vitri == -1)
				continue;
			else {
				while (true) {
					int dem = 0;
					for (int j = point_1; j <= point_2; j++) {
						if (o2.domain[j] == parent1.domain[vitri]) {
							vitri = j;
							break;
							
						} else {
							dem++;
							}
						
					}
			
					if (dem ==( point_2 - point_1 + 1)) {
						o2.domain[i] = parent1.domain[vitri];
						break;
					}
					
				}
			}
			
		}
	}

	public static Individual Mutation(Individual id) {
		

		double pm = rd.nextDouble();
		if (pm > Pm)
			return id;

		int point1 = rd.nextInt(id.domain.length);
		int point2 = rd.nextInt(id.domain.length);
		while (point1 == point2)
			point2 = rd.nextInt(id.domain.length);

		int tmp = id.domain[point1];
		id.domain[point1] = id.domain[point2];
		id.domain[point2] = tmp;
		return id;
	}

}
