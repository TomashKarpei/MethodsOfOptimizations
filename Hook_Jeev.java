
public class Hook_Jeev {
	
	public static double f(double x, double y) {
		return x*x*x + 2*y*y - 4*x*y - y + 5;   //10*x*x + 12*x*y +10*y*y;
	}
	
//////////////////// MINIMUM
	public static void hj_min_krok1(double[][] x0, double x, double y, int n, double b, double e, int i, double eps, int j, double f0, double fb, double funk, double[][] ksi, double[][] xb0){
		i += 1;
		System.out.println("\nIteracja nr: "+ i);
		System.out.println("\nSprawdzenie blędów:["+ Math.abs((1.54858 - x0[0][0])/1.54858) * 100 + ", " + Math.abs((1.79858 - x0[0][1])/1.79858) * 100 + "]");
		System.out.println("Proponowany wynik: [ " + x0[0][0] + " , " + x0[0][1]  + " ]. " );
		j = 1;
		f0 = f(x0[0][0], x0[0][1]);
		fb = f(x, y);
		hj_min_krok2(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
	}
	
	public static void hj_min_krok2(double[][] x0, double x, double y, int n, double b, double e, int i, double eps, int j, double f0, double fb,double funk, double[][] ksi, double[][] xb0) {
		 x0[j][0] = x0[j - 1][0] + (e * ksi[j - 1][0]);
		 x0[j][1] = x0[j - 1][1] + (e * ksi[j - 1][1]);
		 funk = f(x0[j][0], x0[j][1]);
		 hj_min_krok3(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
	}
	
	public static void hj_min_krok3(double[][] x0, double x, double y, int n, double b, double e, int i, double eps, int j, double f0, double fb,double funk, double[][] ksi, double[][] xb0) {
		if(funk < f0) {
			f0 = funk;
			hj_min_krok5(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
		}
		else {
			x0[j][0] = x0[j][0] - (2*e * ksi[j - 1][0]);
			x0[j][1] = x0[j][1] - (2*e * ksi[j - 1][1]);
			funk = f(x0[j][0], x0[j][1]);
			hj_min_krok4(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
		}
	}
	
	public static void hj_min_krok4(double[][] x0, double x, double y, int n, double b, double e, int i, double eps, int j, double f0, double fb,double funk, double[][] ksi, double[][] xb0) {
		if(funk < f0) {
			f0 = funk;
			hj_min_krok5(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
		}
		else {
			x0[j][0] = x0[j][0] + (e * ksi[j - 1][0]);
			x0[j][1] = x0[j][1] + (e * ksi[j - 1][1]);
			funk = f(x0[j][0], x0[j][1]);
			hj_min_krok5(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
		}
	}
	
	public static void hj_min_krok5(double[][] x0, double x, double y, int n, double b, double e, int i, double eps, int j, double f0, double fb,double funk, double[][] ksi, double[][] xb0) {
		if (j != n) {
			j += 1;
			hj_min_krok2(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
		}
		else if (fb > f0){
			hj_min_krok6(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
		}
		else {
			hj_min_krok7(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
		}
	}
	
	public static void hj_min_krok6(double[][] x0, double x, double y, int n, double b, double e, int i, double eps, int j, double f0, double fb,double funk, double[][] ksi, double[][] xb0) {
		xb0[0][0] = x;
		xb0[0][1] = y;
		x = x0[j][0];
		y = x0[j][1];
		x0[0][0] = x + (x - xb0[0][0]);
		x0[0][1] = y + (y - xb0[0][1]);
		hj_min_krok1(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
	}
	
	public static void hj_min_krok7(double[][] x0, double x, double y, int n, double b, double e, int i, double eps, int j, double f0, double fb,double funk, double[][] ksi, double[][] xb0) {
		if (e > eps) {
			if (i == 0) {
				System.out.println("Zmień punkt startowy ");
			}
			else {
				e*= b;
				x0[0][0] = x;
				x0[0][1] = y;
				hj_min_krok1(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
			}
			
		}
		else {
			System.out.println("\nWynik: [ " + x0[0][0] + ", " + x0[0][1] + " ]. Ilość iteracji: " + i);
		}
	}
	
	
////////////////////////////////////MAXIMUM	
	public static void hj_max_krok1(double[][] x0, double x, double y, int n, double b, double e, int i, double eps, int j, double f0, double fb, double funk, double[][] ksi, double[][] xb0){
		i += 1;
		System.out.println("\nIteracja nr: "+ i);
		System.out.println("Proponowany wynik: [ " + x0[0][0] + " , " + x0[0][1]  + " ]. " );
		j = 1;
		f0 = f(x0[0][0], x0[0][1]);
		fb = f(x, y);
		hj_min_krok2(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
	}
	
	public static void hj_max_krok2(double[][] x0, double x, double y, int n, double b, double e, int i, double eps, int j, double f0, double fb,double funk, double[][] ksi, double[][] xb0) {
		 x0[j][0] = x0[j - 1][0] + (e * ksi[j - 1][0]);
		 x0[j][1] = x0[j - 1][1] + (e * ksi[j - 1][1]);
		 funk = f(x0[j][0], x0[j][1]);
		 hj_min_krok3(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
	}
	
	public static void hj_max_krok3(double[][] x0, double x, double y, int n, double b, double e, int i, double eps, int j, double f0, double fb,double funk, double[][] ksi, double[][] xb0) {
		if(funk > f0) {
			f0 = funk;
			hj_min_krok5(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
		}
		else {
			x0[j][0] = x0[j][0] - (2*e * ksi[j - 1][0]);
			x0[j][1] = x0[j][1] - (2*e * ksi[j - 1][1]);
			funk = f(x0[j][0], x0[j][1]);
			hj_min_krok4(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
		}
	}
	
	public static void hj_max_krok4(double[][] x0, double x, double y, int n, double b, double e, int i, double eps, int j, double f0, double fb,double funk, double[][] ksi, double[][] xb0) {
		if(funk > f0) {
			f0 = funk;
			hj_min_krok5(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
		}
		else {
			x0[j][0] = x0[j][0] + (e * ksi[j - 1][0]);
			x0[j][1] = x0[j][1] + (e * ksi[j - 1][1]);
			funk = f(x0[j][0], x0[j][1]);
			hj_min_krok5(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
		}
	}
	
	public static void hj_max_krok5(double[][] x0, double x, double y, int n, double b, double e, int i, double eps, int j, double f0, double fb,double funk, double[][] ksi, double[][] xb0) {
		if (j != n) {
			j += 1;
			hj_min_krok2(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
		}
		else if (fb < f0){
			hj_min_krok6(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
		}
		else {
			hj_min_krok7(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
		}
	}
	
	public static void hj_max_krok6(double[][] x0, double x, double y, int n, double b, double e, int i, double eps, int j, double f0, double fb,double funk, double[][] ksi, double[][] xb0) {
		xb0[0][0] = x;
		xb0[0][1] = y;
		x = x0[j][0];
		y = x0[j][1];
		x0[0][0] = x + (x - xb0[0][0]);
		x0[0][1] = y + (y - xb0[0][1]);
		hj_min_krok1(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
	}
	
	public static void hj_max_krok7(double[][] x0, double x, double y, int n, double b, double e, int i, double eps, int j, double f0, double fb,double funk, double[][] ksi, double[][] xb0) {
		if (e > eps) {
			if (i == 0) {
				System.out.println("Zmień punkt startowy ");
			}
			else {
				e*= b;
				x0[0][0] = x;
				x0[0][1] = y;
				hj_min_krok1(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
			}
			
		}
		else {
			System.out.println("\nWynik: [ " + x0[0][0] + ", " + x0[0][1] + " ]. Ilość iteracji: " + i);
		}
	}
	
	
	
	public static void main(String[] args) {
		double x = 1, y = 1, b = 0.5, e = 0.5, eps = 0.000001, f0 = 0, fb = 1, funk = 0;
		double [][] x0 = new double [100][2];
		double [][] xb0 = new double [100][2];
		double [][] xb = new double [100][2];
		double [][] ksi = new double [100][2];
		x0[0][0] = 1;
		x0[0][1] = 1;
		xb[0][0] = x;
		xb[0][1] = y;
		xb0[0][0] = x;
		xb0[0][1] = y;
		ksi[0][0] = 1;
		ksi[0][1] = 0;
		ksi[1][0] = 0;
		ksi[1][1] = 1;
		int i = 0, n = 2, j = 1, MIN = 1;
		if (MIN == 1) {
			hj_min_krok1(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
		}
		else if (MIN == 0) {
			hj_max_krok1(x0, x, y, n, b, e, i, eps, j, f0, fb, funk, ksi, xb0);
		}
		
	}
}
