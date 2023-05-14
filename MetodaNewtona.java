import java.lang.Math;

public class MetodaNewtona {	
	
	public static double f(double x, double y) {
		return x*x*x + 2*y*y - 4*x*y - y + 5;   //10*x*x + 12*x*y +10*y*y;
	}

	public static double pochodna1X(double x, double y, double h) {
		double fx = (f(x + h, y) - f(x, y)) / h;
		return fx;
	}
	
	public static double pochodna1Y(double x, double y, double h) {
		double fx = (f(x, y + h) - f(x, y)) / h;
		return fx;
	}
	
	public static double pochodna2X(double x, double y, double h) {
		double fx = (f(x + 2*h, y) - 2*f(x + h, y) + f(x, y)) / (h*h);
		return fx;
	}
	
	public static double pochodna2Y(double x, double y, double h) {
		double fx = (f(x, y + 2*h) - 2*f(x, y + h) + f(x, y)) / (h*h);
		return fx;
	}
	
	public static double pochodna2XY(double x, double y, double h) {
		double fx = (f(x + h, y + h) - f(x + h, y) - f(x, y + h) + f(x, y)) / (h*h);
		return fx;
	}
	
	public static double pochodna2YX(double x, double y, double h) {
		double fx = (f(x + h, y + h) - f(x, y + h) - f(x + h, y) + f(x, y)) / (h*h);
		return fx;
	}
	
	
	public static void newton(double x0, double y0, double h, int i, double e) {
		double x = x0, y = y0, pX = 0, pY = 0;
		double [][] gradient = new double [50][3];
		double [][] macierz = new double [50][5];
		double [][] macierzOdwr = new double [50][5];
		double Odwr = 0;
		while(true) {
			i += 1;
			System.out.println("\nIteracja nr: "+ i);
			gradient[i][0] = pochodna1X(x, y, h);
			System.out.println("Pochodna1X: "+ gradient[i][0]);
			gradient[i][1] = pochodna1Y(x, y, h);
			System.out.println("Pochodna1Y: "+ gradient[i][1]);
			macierz[i][0] = pochodna2X(x, y, h); 
			System.out.println("Pochodna2X: "+ macierz[i][0]);
			macierz[i][1] = pochodna2XY(x, y, h);
			System.out.println("Pochodna2XY: "+ macierz[i][1]);
			macierz[i][2] = pochodna2YX(x, y, h);
			System.out.println("Pochodna2YX: "+ macierz[i][2]);
			macierz[i][3] = pochodna2Y(x, y, h);
			System.out.println("Pochodna2Y: "+ macierz[i][3]);
			Odwr = 1/(macierz[i][0]*macierz[i][3]-macierz[i][1]*macierz[i][2]);
			System.out.println("Odwr: "+ Odwr);
			macierzOdwr[i][0] = macierz[i][3]*Odwr;
			System.out.println("Pochodna2X Odwr: "+ macierzOdwr[i][0]);
			macierzOdwr[i][1] = -(macierz[i][1])*Odwr;
			System.out.println("Pochodna2XY Odwr: "+ macierzOdwr[i][1]);
			macierzOdwr[i][2] = -(macierz[i][2])*Odwr;
			System.out.println("Pochodna2YX Odwr: "+ macierzOdwr[i][2]);
			macierzOdwr[i][3] = macierz[i][0]*Odwr;
			System.out.println("Pochodna2Y Odwr: "+ macierzOdwr[i][3]);
			System.out.println("x - : " + macierzOdwr[i][0]*gradient[i][0] + " + " + macierzOdwr[i][1]*gradient[i][1] + " = " + (macierzOdwr[i][0]*gradient[i][0]+macierzOdwr[i][1]*gradient[i][1]));
			pX = x - (macierzOdwr[i][0]*gradient[i][0]+macierzOdwr[i][1]*gradient[i][1]);
			System.out.println("y - : " + macierzOdwr[i][2]*gradient[i][0] + " + " + macierzOdwr[i][3]*gradient[i][1] + " = " + (macierzOdwr[i][2]*gradient[i][0]+macierzOdwr[i][3]*gradient[i][1]));
			pY = y - (macierzOdwr[i][2]*gradient[i][0]+macierzOdwr[i][3]*gradient[i][1]);
			System.out.println("["+ pX + ", " + pY + "]");
			System.out.println("\nSprawdzenie blędów:["+ Math.abs((1.54858 - pX)/1.54858) * 100 + ", " + Math.abs((1.79858 - pY)/1.79858) * 100 + "]");
			System.out.println("\nSprawdzenie warunku stopu dla: ["+ (Math.abs(pochodna1X(pX, pY, h))) + ", " + Math.abs(pochodna1Y(pX, pY, h)) + "]");
			if((Math.abs(pochodna1X(pX, pY, h))<= e && Math.abs(pochodna1Y(pX, pY, h))<= e) || (Math.abs(pX - x) <= e && Math.abs(pY - y) <= e)) {
				break;
			}
			else {
				x = pX;
			    y = pY;
			}
		}
		System.out.println("Przykładowa funkcja ekstremum osiąga w punkcie: ["+ pX + ", " + pY + "]");
	}
	
	public static void main(String[] args) {
		double x0 = 1, y0 = 1, h = 0.00001, e = 0.000001;
		int i = 0;
		newton(x0, y0, h, i, e);
	}
}
