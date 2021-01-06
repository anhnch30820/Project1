package IDPC_DU_On_Edge;

import java.util.ArrayList;
import java.util.Random;

import javax.naming.ldap.Rdn;

public class ACO {
	static double alpha = 1;
	static double beta = 2;
	static double p = 0.4;
//	static double b = 5;
	static double Q = 50;
	static double mui = 5;
	static int num_Ant = 10;
	static double min_Mui = 2;
	static double max_Mui = 15;
	static double CHIPHICODINH = 20;
	static Random rd = Main.rd;

	public static void khoiTaoMui(ArrayList<Edge>[] g) {

		for (int i = 1; i < g.length; i++)
			for (int j = 0; j < g[i].size(); j++) {
				g[i].get(j).mui = mui;
				g[i].get(j).dentaij = 0;
			}
	}

	public static void khoiTaoMuiToanBo(ArrayList<Edge>[] g, int cost) {

		for (int i = 1; i < g.length; i++)
			for (int j = 0; j < g[i].size(); j++) {
				g[i].get(j).mui = num_Ant * 1.0 / cost;
				g[i].get(j).dentaij = 0;
			}
	}

	public static void tinhXacSuat(ArrayList<Edge> g) {

		double F = 0;
		double q = 0;
		for (int j = 0; j < g.size(); j++) {
//			F += Math.pow(g.get(j).mui, alpha) * beta/ Math.log(1 +  g.get(j).getWeight());
			F += Math.pow(g.get(j).mui, alpha) * Math.pow(1.0 / (CHIPHICODINH+ g.get(j).getWeight()), beta);
		}
		for (int j = 0; j < g.size(); j++) {
//			q += (Math.pow(g.get(j).mui, alpha) * beta/ Math.log(1 +  g.get(j).getWeight())) / F;
			q += (Math.pow(g.get(j).mui, alpha) * Math.pow(1.0 / (CHIPHICODINH+ g.get(j).getWeight()), beta)) / F;
			g.get(j).p = q;
			if (j == g.size() - 1)
				g.get(j).p = 1;
		}
	}

//	public static Path[] tim_1_Duong(int Domain[], int start, int end, ArrayList<Edge>[] g) {
//		int tmp = start;
//		Path[] path = new Path[1];
//		for (int w = 0; w < path.length; w++) {
//			path[w] = new Path();
//			ArrayList<Integer> Visited_Domain = new ArrayList<Integer>();
//			boolean visited_Vertex[] = new boolean[g[0].get(0).getStart() + 1];
//			visited_Vertex[start] = true;
//			int dem = 0;
//			int viPham = 0;
//			for (int o = 0; o < Domain.length; o++) {
//				// xet tung mien
//				int chuyenMien = 0;
//				for (int i = 1; i < g.length; i++) {
//					// xet tung dinh
//					if (start == end)
//						break;
//					ArrayList<Edge> List_E_Out_1_Domain = new ArrayList<Edge>();
//					if (i == start) {
//						if (dem == 0 || o == Domain.length - 1) {
//							for (int a1 = 0; a1 < g[i].size(); a1++) {
//								if (g[i].get(a1).getDomain() == Domain[o]
//										&& visited_Vertex[g[i].get(a1).getEnd()] == false)
//									List_E_Out_1_Domain.add(g[i].get(a1));
//							}
//							dem = 1;
//						} else {
//							for (int a1 = 0; a1 < g[i].size(); a1++) {
//								if (g[i].get(a1).getDomain() == Domain[o]
//										&& visited_Vertex[g[i].get(a1).getEnd()] == false)
//									List_E_Out_1_Domain.add(g[i].get(a1));
//								if (g[i].get(a1).getDomain() == Domain[o + 1]
//										&& visited_Vertex[g[i].get(a1).getEnd()] == false)
//									List_E_Out_1_Domain.add(g[i].get(a1));
//							}
//						}
//						if (List_E_Out_1_Domain.size() == 0) {
//							viPham = 1;
//							break;
//						}
//						tinhXacSuat(List_E_Out_1_Domain);
//						Edge E_select = new Edge();
//						Random rd = new Random();
//						double p = rd.nextDouble();
//						for (int j = 0; j < List_E_Out_1_Domain.size() - 1; j++) {
//							if (p < List_E_Out_1_Domain.get(0).p) {
//								E_select = List_E_Out_1_Domain.get(0);
//								break;
//							} else if (p > List_E_Out_1_Domain.get(j).p && p <= List_E_Out_1_Domain.get(j + 1).p) {
//								E_select = List_E_Out_1_Domain.get(j + 1);
//								break;
//							}
//						}
//						if (E_select.getDomain() == Domain[o])
//							chuyenMien = 0;
//						else
//							chuyenMien = 1;
//						if (chuyenMien == 0) {
//							path[w].list.add(E_select);
//							start = E_select.getEnd();
//							visited_Vertex[start] = true;
//							i = 1;
//						} else {
//							path[w].list.add(E_select);
//							start = E_select.getEnd();
//							visited_Vertex[start] = true;
//							break;
//						}
//					}
//				}
//				if (viPham == 1) {
//					w = w - 1;
//					break;
//				}
//			}
//			start = tmp;
//		}
//		return path;
//	}
	public static Path[] tim_1_Duong(int Domain[], int start, int end, ArrayList<Edge>[] g) {
		int tmp = start;
		Path[] path = new Path[1];
		for (int w = 0; w < path.length; w++) {
			path[w] = new Path();
			ArrayList<Integer> Visited_Domain = new ArrayList<Integer>();
			boolean visited_Vertex[] = new boolean[g[0].get(0).getStart() + 1];
			visited_Vertex[start] = true;
			int dem = 0;
			int kt = 0;
			int max = Domain.length;
			for (int o = 0; o < Domain.length; o++) {
//				System.out.println(max +" gia tri cua priority");
				int viPham = 0;
				if (Domain[o] == max) {

					int chuyenMien = 0;
					for (int i = 1; i < g.length; i++) {
//						System.out.println(start +" gia tri dinh bAAT DAU");
						// xet tung dinh
						if (start == end) {

							kt = 1;
							break;
						}
						ArrayList<Edge> List_E_Out_1_Domain = new ArrayList<Edge>();
						if (i == start) {

							if (dem == 0 || max == 1) {
								for (int a1 = 0; a1 < g[i].size(); a1++) {
//									System.out.println((o+1)+"Gaga");
									if (g[i].get(a1).getDomain() == (o + 1)
											&& visited_Vertex[g[i].get(a1).getEnd()] == false)
										List_E_Out_1_Domain.add(g[i].get(a1));
								}
//								System.out.println(List_E_Out_1_Domain.size() + " so canh trong 1 mien");
								dem = 1;
							} else {

								for (int a1 = 0; a1 < g[i].size(); a1++) {
									if (g[i].get(a1).getDomain() == (o + 1)
											&& visited_Vertex[g[i].get(a1).getEnd()] == false)
										List_E_Out_1_Domain.add(g[i].get(a1));

								}
//								System.out.println(List_E_Out_1_Domain.size() + " so canh trong 1 mien");
								for (int a = 0; a < Domain.length; a++) {
									if (Domain[a] == (max - 1)) {
										for (int a1 = 0; a1 < g[i].size(); a1++) {
											if (g[i].get(a1).getDomain() == (a + 1)
													&& visited_Vertex[g[i].get(a1).getEnd()] == false) {
												List_E_Out_1_Domain.add(g[i].get(a1));
//												System.out.println("ga");
											}
										}
//										System.out.println(List_E_Out_1_Domain.size() + " so canh ung vien");
										break;
									}
								}
//								System.out.println(List_E_Out_1_Domain.size());

							}
//							for (int x = 0; x < List_E_Out_1_Domain.size(); x++)
//								System.out.print(List_E_Out_1_Domain.get(x).getDomain() + " ");
//							System.out.println();
							if (List_E_Out_1_Domain.size() == 0) {
								viPham = 1;
//								System.out.println("Ga");
								break;
							}
							tinhXacSuat(List_E_Out_1_Domain);
							Edge E_select = new Edge();
							
							double p = rd.nextDouble();
							for (int j = 0; j < List_E_Out_1_Domain.size() - 1; j++) {
								if (p < List_E_Out_1_Domain.get(0).p) {
									E_select = List_E_Out_1_Domain.get(0);
									break;
								} else if (p > List_E_Out_1_Domain.get(j).p && p < List_E_Out_1_Domain.get(j + 1).p) {
									E_select = List_E_Out_1_Domain.get(j + 1);
									break;
								}
							}
//							System.out.println(E_select.getEnd() +" gia tri dinh ket thuc");
							if (E_select.getDomain() == o + 1)
								chuyenMien = 0;
							else
								chuyenMien = 1;
							if (chuyenMien == 0) {
								path[w].list.add(E_select);
								start = E_select.getEnd();
								visited_Vertex[start] = true;
								i = 0;
							} else {

								path[w].list.add(E_select);
								start = E_select.getEnd();
								visited_Vertex[start] = true;

								break;
							}
						}
					}
					if (chuyenMien == 1 && max > 1) {
//						System.out.println("Chyen Mien khac");
						max--;
						o = -1;
					}
					if (viPham == 1 && max > 1) {
//						System.out.println("Vi pham ");
						max = max - 1;
						o = -1;
					}
				}
				if (kt == 1) {
//					System.out.println("ket thuc");
					break;
				}

			}

			start = tmp;
		}
		return path;
	}

	public static Path[] timDuong(int Domain[], int start, int end, ArrayList<Edge>[] g) {
		int tmp = start;
		Path[] path = new Path[num_Ant];
		for (int w = 0; w < path.length; w++) {
			path[w] = new Path();
			int kienMu = 0;
			if (rd.nextDouble() <= 0.2) {
				kienMu = 1;
			}
			ArrayList<Integer> Visited_Domain = new ArrayList<Integer>();
			boolean visited_Vertex[] = new boolean[g[0].get(0).getStart() + 1];
			visited_Vertex[start] = true;
			int dem = 0;
			int kt = 0;
			int max = Domain.length;
			for (int o = 0; o < Domain.length; o++) {
//				System.out.println(max +" gia tri cua priority");
				int viPham = 0;
				if (Domain[o] == max) {

					int chuyenMien = 0;
					for (int i = 1; i < g.length; i++) {
//						System.out.println(start +" gia tri dinh bAAT DAU");
						// xet tung dinh
						if (start == end) {

							kt = 1;
							break;
						}
						ArrayList<Edge> List_E_Out_1_Domain = new ArrayList<Edge>();
						if (i == start) {

							if (dem == 0 || max == 1) {
								for (int a1 = 0; a1 < g[i].size(); a1++) {
//									System.out.println((o+1)+"Gaga");
									if (g[i].get(a1).getDomain() == (o + 1)
											&& visited_Vertex[g[i].get(a1).getEnd()] == false)
										List_E_Out_1_Domain.add(g[i].get(a1));
								}
//								System.out.println(List_E_Out_1_Domain.size() + " so canh trong 1 mien");
								dem = 1;
							} else {

								for (int a1 = 0; a1 < g[i].size(); a1++) {
									if (g[i].get(a1).getDomain() == (o + 1)
											&& visited_Vertex[g[i].get(a1).getEnd()] == false)
										List_E_Out_1_Domain.add(g[i].get(a1));

								}
//								System.out.println(List_E_Out_1_Domain.size() + " so canh trong 1 mien");
								for (int a = 0; a < Domain.length; a++) {
									if (Domain[a] == (max - 1)) {
										for (int a1 = 0; a1 < g[i].size(); a1++) {
											if (g[i].get(a1).getDomain() == (a + 1)
													&& visited_Vertex[g[i].get(a1).getEnd()] == false) {
												List_E_Out_1_Domain.add(g[i].get(a1));
//												System.out.println("ga");
											}
										}
//										System.out.println(List_E_Out_1_Domain.size() + " so canh ung vien");
										break;
									}
								}
//								System.out.println(List_E_Out_1_Domain.size());

							}
//							for (int x = 0; x < List_E_Out_1_Domain.size(); x++)
//								System.out.print(List_E_Out_1_Domain.get(x).getDomain() + " ");
//							System.out.println();
							if (List_E_Out_1_Domain.size() == 0) {
								viPham = 1;
//								System.out.println("Ga");
								break;
							}
							if (kienMu == 1) {
								int chon = rd.nextInt(List_E_Out_1_Domain.size());

								Edge E_select = new Edge();

								E_select = List_E_Out_1_Domain.get(chon);

//							System.out.println(E_select.getEnd() +" gia tri dinh ket thuc");
								if (E_select.getDomain() == o + 1)
									chuyenMien = 0;
								else
									chuyenMien = 1;
								if (chuyenMien == 0) {
									path[w].list.add(E_select);
									start = E_select.getEnd();
									visited_Vertex[start] = true;
									i = 0;
								} else {

									path[w].list.add(E_select);
									start = E_select.getEnd();
									visited_Vertex[start] = true;

									break;
								}
								
							} else {
								
								tinhXacSuat(List_E_Out_1_Domain);
								Edge E_select = new Edge();

								double p = rd.nextDouble();
								for (int j = 0; j < List_E_Out_1_Domain.size() - 1; j++) {
									if (p < List_E_Out_1_Domain.get(0).p) {
										E_select = List_E_Out_1_Domain.get(0);
										break;
									} else if (p > List_E_Out_1_Domain.get(j).p
											&& p < List_E_Out_1_Domain.get(j + 1).p) {
										E_select = List_E_Out_1_Domain.get(j + 1);
										break;
									}
								}
//							System.out.println(E_select.getEnd() +" gia tri dinh ket thuc");
								if (E_select.getDomain() == o + 1)
									chuyenMien = 0;
								else
									chuyenMien = 1;
								if (chuyenMien == 0) {
									path[w].list.add(E_select);
									start = E_select.getEnd();
									visited_Vertex[start] = true;
									i = 0;
								} else {

									path[w].list.add(E_select);
									start = E_select.getEnd();
									visited_Vertex[start] = true;

									break;
								}
							}
						}
					}
					if (chuyenMien == 1 && max > 1) {
//						System.out.println("Chyen Mien khac");
						max--;
						o = -1;
					}
					if (viPham == 1 && max > 1) {
//						System.out.println("Vi pham ");
						max = max - 1;
						o = -1;
					}
				}
				if (kt == 1) {
//					System.out.println("ket thuc");
					break;
				}

			}
			
			start = tmp;
		}
		return path;
	}

	public static void capNhatMui(Path[] path, ArrayList<Edge>[] g) {
		for (int i = 0; i < path.length; i++)
			for (int j = 0; j < path[i].list.size(); j++)
				path[i].list.get(j).dentaij += Q / path[i].cost;

//		int vitri = 0;
//		int max = 999999;
//		for(int i = 0; i<path.length; i++)
//			if(path[i].cost < max)
//			{
//				vitri = i;
//				max = path[i].cost;
//			}
//		for(int i=0; i<path[vitri].list.size(); i++)
//			path[vitri].list.get(i).dentaij += b*Q/path[vitri].cost;

		for (int i = 1; i < g.length; i++)
			for (int j = 0; j < g[i].size(); j++) {
				if (g[i].get(j).dentaij == 0) {
					g[i].get(j).mui = (1 - p) * g[i].get(j).mui;
				} else {
					g[i].get(j).mui = (1 - p) * g[i].get(j).mui + g[i].get(j).dentaij;
//					g[i].get(j).mui = (1 - p) * g[i].get(j).mui + Math.log(1 + g[i].get(j).dentaij);
				}
			}
//		for (int i = 1; i < g.length; i++)
//			for (int j = 0; j < g[i].size(); j++) {
//				if (g[i].get(j).dentaij == 0) {
//					if (g[i].get(j).mui < min_Mui)
//						continue;
//					else
//						g[i].get(j).mui = (1 - p) * g[i].get(j).mui;
//				} else {
//					if (g[i].get(j).mui >= max_Mui)
//						continue;
//					else
////					g[i].get(j).mui = (1 - p) * g[i].get(j).mui + g[i].get(j).dentaij;
//						g[i].get(j).mui = (1 - p) * g[i].get(j).mui + Math.log10(1 + g[i].get(j).dentaij);
//				}
//			}
	}
}
