package IDPC_DU_On_Edge;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Main {
	public static void locCanh(ArrayList<Edge>[] g) {

		for (int i = 1; i < g.length; i++) {

			for (int j = 0; j < g[i].size() - 1; j++) {
				int end = g[i].get(j).getEnd();
				int Domain = g[i].get(j).getDomain();
				for (int k = j + 1; k < g[i].size(); k++) {

					if (g[i].get(k).getEnd() == end && g[i].get(k).getDomain() == Domain) {
						if (g[i].get(j).getWeight() > g[i].get(k).getWeight())
							g[i].remove(j);
						else
							g[i].remove(k);
						j = j - 1;
						break;
					}
				}
			}
		}

	}
	static Random rd;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final long startTime = System.currentTimeMillis();
		ReadFile r1 = new ReadFile();
		String path = "C:\\Users\\Hoang Anh\\Downloads\\Compressed\\IDPC-DU\\set1\\";

//		r1.reaFile(path + args[0]);
//		r1.reaFile(path + "idpc_10x10x1000.idpc");

//		
//		r1.reaFile(path + "idpc_10x20x2713.idpc");
//		r1.reaFile(path + "idpc_90x45x260195.idpc");
		r1.reaFile(path + "idpc_10x5x425.idpc");
//	
//		r1.reaFile(path + "idpc_15x15x3375.idpc");
//		r1.reaFile(path + "idpc_15x30x12111.idpc");
//		r1.reaFile(path + "idpc_15x7x1504.idpc");
//		r1.reaFile(path + "idpc_20x10x2492.idpc");
//		r1.reaFile(path + "idpc_20x20x8000.idpc");
//		r1.reaFile(path + "idpc_20x40x26104.idpc");
//		r1.reaFile(path + "idpc_25x12x4817.idpc");
//		r1.reaFile(path + "idpc_25x25x15625.idpc");
//		r1.reaFile(path + "idpc_25x50x57147.idpc");
//		r1.reaFile(path + "idpc_30x15x10025.idpc");
//		r1.reaFile(path + "idpc_30x30x27000.idpc");
//		r1.reaFile(path + "idpc_30x60x89772.idpc");
//		r1.reaFile(path + "idpc_35x17x13934.idpc");
//		r1.reaFile(path + "idpc_35x35x42875.idpc");
//		r1.reaFile(path + "idpc_35x70x123585.idpc");
//		r1.reaFile(path + "idpc_40x20x18485.idpc");
//		r1.reaFile(path + "idpc_40x40x64000.idpc");
//		r1.reaFile(path + "idpc_40x80x130681.idpc");
//		r1.reaFile(path + "idpc_45x22x43769.idpc");
//		r1.reaFile(path + "idpc_45x45x91125.idpc");
//		r1.reaFile(path + "idpc_45x90x322081.idpc");

//		r1.reaFile(path +"idpc_100x100x1000000.idpc");

		System.out.println("Hello");

		g = r1.getGraph();
		locCanh(g);
		int sum = 0;
		for (int seed = 1; seed <= 30; seed++) {
			rd = new Random(seed);
			xuly();
			
			try {
				File file = new File("C:\\Users\\Hoang Anh\\Downloads\\Compressed\\hello.txt");
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(bw);
				// This will add a new line to the file content
				pw.println("");
				/*
				 * Below three statements would add three mentioned Strings to the file in new
				 * lines.
				 */
				sum += ketqua;
				String a = "ket qua la cua seed " + seed + " la: " + ketqua;
				pw.println(a);

				pw.close();

				System.out.println("Data successfully appended at the end of file");

			} catch (IOException ioe) {
				System.out.println("Exception occurred:");
				ioe.printStackTrace();
			}

		}
		
		try {
			File file = new File("C:\\Users\\Hoang Anh\\Downloads\\Compressed\\hello.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			// This will add a new line to the file content
			pw.println("");
			/*
			 * Below three statements would add three mentioned Strings to the file in new
			 * lines.
			 */
			String a = "ket qua Avg la: " + (sum*1.0/30);
			pw.println(a);

			pw.close();

			System.out.println("Data successfully appended at the end of file");

		} catch (IOException ioe) {
			System.out.println("Exception occurred:");
			ioe.printStackTrace();
		}

		//
//		for (int w = 0; w < p.length; w++) {
//			for (int i = 0; i < p[w].list.size(); i++)
//				System.out.println(p[w].list.get(i).getStart() + "-->" + p[w].list.get(i).getEnd() + " Weight: "
//						+ p[w].list.get(i).getWeight() + " Domain: " + p[w].list.get(i).getDomain());
//			System.out.println();

//		ACO.khoiTaoMui(g);
//		for (int o = 0; o < 100; o++) {
//			Path[] p = ACO.timDuong(list[1].domain, 1, 5, g);
//			for (int w = 0; w < p.length; w++)
//				p[w].tinhCost(p[w]);
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime));

	}

	static ArrayList<Edge>[] g = null;
	static int num_Interation = 20;
	static int num_ACO = 5;
	static double p1 = 0.6;
	static int ketqua = 0;

	public static void xuly() {
		Population pop = new Population();
		Individual[] list = pop.khoiTaoQuanThe(g[0].get(0).getEnd(), g);
//		Individual []list = new Individual[100];
//		for (int i = 0; i < list.length; i++) {
//			for (int j = 0; j < list[i].domain.length; j++)
//				System.out.print(list[i].domain[j] + " ");
//			System.out.println();
//		}

//		for (int i = 0; i < list.length; i++) {
//			for (int j = 0; j < list[i].domain.length; j++)
//				System.out.print(list[i].domain[j] + " ");
//			System.out.println();
//		}

		for (int i = 0; i < list.length; i++) {
			ACO.khoiTaoMui(g);
			Path[] p = null;
			for (int o = 0; o < 1; o++) {
				// ACO sau 100 the he
				p = ACO.tim_1_Duong(list[i].domain, g[0].get(0).getWeight(), g[0].get(0).getDomain(), g);
//				System.out.println(o);
				for (int w = 0; w < p.length; w++) {
					p[w].tinhCost(p[w]);
//					System.out.print(p[w].cost+" ");
					ACO.khoiTaoMuiToanBo(g, p[w].cost);
				}
//				System.out.println();			
			}
			ArrayList<Edge> list_Edge[] = new ArrayList[num_ACO];
			for (int o = 0; o < num_ACO; o++) {
				// ACO sau 100 the he
				list_Edge[o] = new ArrayList<Edge>();
				p = null;
				p = ACO.timDuong(list[i].domain, g[0].get(0).getWeight(), g[0].get(0).getDomain(), g);
//				System.out.println(o);
				for (int w = 0; w < p.length; w++) {
					p[w].tinhCost(p[w]);
//					System.out.print(p[w].cost+" ");
//					System.out.println();

				}

				// fix
				int cost = 999999;
				int vitri = 0;

				for (int q = 0; q < p.length; q++) {
					if (cost > p[q].cost) {
						cost = p[q].cost;
						vitri = q;
					}

				}
				list_Edge[o] = p[vitri].list;

//				for (int w = 0; w < p.length; w++) {
//					for (int i1 = 0; i1 < p[w].list.size(); i1++)
//						System.out.println(p[w].list.get(i1).getStart() + "-->" + p[w].list.get(i1).getEnd()
//								+ " Weight: " + p[w].list.get(i1).getWeight() + " Domain: "
//								+ p[w].list.get(i1).getDomain());
//					System.out.println();}
//				System.out.println();

//				double[] xs = null;
//				if (o > 0) {
//					xs = new double[list_Edge[o - 1].size()];
//					for (int c = 0; c < list_Edge[o-1].size(); c++)
//						xs[c] = list_Edge[o - 1].get(c).mui;
//				}
//				
				ACO.capNhatMui(p, g);

//				int dem = 0;
//
//				if (o > 0) {
//					
//					if (list_Edge[o].size() == list_Edge[o - 1].size())
//						while (list_Edge[o].get(dem).getStart() == list_Edge[o - 1].get(dem).getStart()
//								&& list_Edge[o].get(dem).getEnd() == list_Edge[o - 1].get(dem).getEnd()
//								&& list_Edge[o].get(dem).getWeight() == list_Edge[o - 1].get(dem).getWeight()
//								&& list_Edge[o].get(dem).getDomain() == list_Edge[o - 1].get(dem).getDomain()) {
//							if (list_Edge[o].get(dem).getStart() != list_Edge[o - 1].get(dem).getStart()
//									|| list_Edge[o].get(dem).getEnd() != list_Edge[o - 1].get(dem).getEnd()
//									|| list_Edge[o].get(dem).getWeight() != list_Edge[o - 1].get(dem).getWeight()
//									|| list_Edge[o].get(dem).getDomain() != list_Edge[o - 1].get(dem).getDomain()) {
//
//								break;
//							}
//							dem++;
//							if (dem == list_Edge[o].size()) {
//								for (int c = 0; c < list_Edge[o].size(); c++)
//									list_Edge[o].get(c).mui = (1-p1)*xs[c];
//								break;
//							}
//						}
//				}
			}
			int cost = 999999;
//			int vitri = 0;

			for (int q = 0; q < p.length; q++) {
				if (cost > p[q].cost) {
					cost = p[q].cost;
//					vitri = q;
				}

			}
			list[i].cost = cost;
//			list[i].list = p[vitri].list;

		}
		// Reproduct
		for (int k = 0; k < num_Interation; k++) {
			double[] p = Reproduct.tinh_Xac_Suat(list);
			list = Reproduct.Reproduct(list, p);

		}
		int co = 9999999;
		for (int i = 0; i < list.length; i++) {
			if (list[i].cost < co)
				co = list[i].cost;
//			System.out.println(list[i].cost);
		}
//		for (int w = 0; w < list.length; w++) {
//			for (int i1 = 0; i1 < list[w].list.size(); i1++)
//				System.out.println(list[w].list.get(i1).getStart() + "-->" + list[w].list.get(i1).getEnd()
//						+ " Weight: " + list[w].list.get(i1).getWeight() + " Domain: "
//						+ list[w].list.get(i1).getDomain());
//			System.out.println();}

		ketqua = co;

	}

	public static void cal_Cost(Individual[] list) {

		for (int i = 0; i < list.length; i++) {
			ACO.khoiTaoMui(g);
			Path[] p = null;
			for (int o = 0; o < 1; o++) {
				// ACO sau 100 the he
				p = ACO.tim_1_Duong(list[i].domain, g[0].get(0).getWeight(), g[0].get(0).getDomain(), g);
//				System.out.println(o);
				for (int w = 0; w < p.length; w++) {
					p[w].tinhCost(p[w]);
//					System.out.print(p[w].cost+" ");
					ACO.khoiTaoMuiToanBo(g, p[w].cost);
				}
//				System.out.println();			
			}
			ArrayList<Edge> list_Edge[] = new ArrayList[num_ACO];
			for (int o = 0; o < num_ACO; o++) {
				// ACO sau 100 the he
				list_Edge[o] = new ArrayList<Edge>();
				p = null;
				p = ACO.timDuong(list[i].domain, g[0].get(0).getWeight(), g[0].get(0).getDomain(), g);
//				System.out.println(o);
				for (int w = 0; w < p.length; w++) {
					p[w].tinhCost(p[w]);
//					System.out.print(p[w].cost+" ");
//					System.out.println();

				}

				// fix
				int cost = 999999;
				int vitri = 0;

				for (int q = 0; q < p.length; q++) {
					if (cost > p[q].cost) {
						cost = p[q].cost;
						vitri = q;
					}

				}
				list_Edge[o] = p[vitri].list;

//				for (int w = 0; w < p.length; w++) {
//					for (int i1 = 0; i1 < p[w].list.size(); i1++)
//						System.out.println(p[w].list.get(i1).getStart() + "-->" + p[w].list.get(i1).getEnd()
//								+ " Weight: " + p[w].list.get(i1).getWeight() + " Domain: "
//								+ p[w].list.get(i1).getDomain());
//					System.out.println();}
//				System.out.println();

//				double[] xs = null;
//				if (o > 0) {
//					xs = new double[list_Edge[o - 1].size()];
//					for (int c = 0; c < list_Edge[o-1].size(); c++)
//						xs[c] = list_Edge[o - 1].get(c).mui;
//				}

				ACO.capNhatMui(p, g);

//				int dem = 0;
//
//				if (o > 0) {
//					
//					if (list_Edge[o].size() == list_Edge[o - 1].size())
//						while (list_Edge[o].get(dem).getStart() == list_Edge[o - 1].get(dem).getStart()
//								&& list_Edge[o].get(dem).getEnd() == list_Edge[o - 1].get(dem).getEnd()
//								&& list_Edge[o].get(dem).getWeight() == list_Edge[o - 1].get(dem).getWeight()
//								&& list_Edge[o].get(dem).getDomain() == list_Edge[o - 1].get(dem).getDomain()) {
//							if (list_Edge[o].get(dem).getStart() != list_Edge[o - 1].get(dem).getStart()
//									|| list_Edge[o].get(dem).getEnd() != list_Edge[o - 1].get(dem).getEnd()
//									|| list_Edge[o].get(dem).getWeight() != list_Edge[o - 1].get(dem).getWeight()
//									|| list_Edge[o].get(dem).getDomain() != list_Edge[o - 1].get(dem).getDomain()) {
//
//								break;
//							}
//							dem++;
//							if (dem == list_Edge[o].size()) {
//								for (int c = 0; c < list_Edge[o].size(); c++)
//									list_Edge[o].get(c).mui = (1-p1)*xs[c];
//								break;
//							}
//						}
//				}
			}
			int cost = 999999;
//			int vitri = 0;

			for (int q = 0; q < p.length; q++) {
				if (cost > p[q].cost) {
					cost = p[q].cost;
//					vitri = q;
				}

			}
			list[i].cost = cost;
//			list[i].list = p[vitri].list;

		}
	}
}
