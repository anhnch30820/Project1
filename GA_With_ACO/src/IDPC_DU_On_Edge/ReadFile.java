package IDPC_DU_On_Edge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;




public class ReadFile {
	

	static ArrayList<Edge>[] g = null;

	public static void reaFile(String path) {
		int soDinh = 0;
		int soDomain = 0;
		int src = 0;
		int des = 0;
		int dem = 0;
		File file = new File(path);
		try {

			Scanner sc = new Scanner(file);

			while (sc.hasNextLine()) {
				int start, end, weight, domain;
				if (dem == 0) {
					soDinh = sc.nextInt();
					g = new ArrayList[soDinh+1];
					for (int i = 0; i <= soDinh; i++) {
						g[i] = new ArrayList<Edge>();
					}
					dem++;
				}
				if (dem == 1) {
					soDomain = sc.nextInt();
					dem++;
				}
				if (dem == 2) {
					src = sc.nextInt();
					dem++;
				}
				if (dem == 3) {
					des = sc.nextInt();
					g[0].add(new Edge(soDinh, soDomain, src, des));
					dem++;
				} else {
					start = sc.nextInt();
					end = sc.nextInt();
					weight = sc.nextInt();
					domain = sc.nextInt();
					Edge e = new Edge(start, end, weight, domain);
					g[start].add(e);
				}

			}

			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR 404: File not found!");
			e.printStackTrace();
		}

	}
	

	public ArrayList<Edge>[] getGraph() {
		return g;
	}
}
