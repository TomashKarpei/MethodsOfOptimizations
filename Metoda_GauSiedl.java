import java.lang.Math;

public class Metoda_GauSiedl {	
	
	public static double f(double x, double y) {
		return x*x*x + 2*y*y - 4*x*y - y + 5;   //10*x*x + 12*x*y +10*y*y;
	}
	
	public static double pochodna1x(double x, double y, double h) {
        double fx = (f(x + h, y) - f(x, y)) / h;
        return fx;
    }

    public static double pochodna1y(double x, double y, double h) {
        double fx = (f(x, y + h) - f(x, y)) / h;
        return fx;
    }
    
    public static double pochodna2x(double x, double y, double h) {
        double fx = (f(x + 2*h, y) - 2*f(x + h, y) + f(x, y)) / (h*h);
        return fx;
    }

    public static double pochodna2y(double x, double y, double h) {
        double fx = (f(x, y + 2*h) - 2*f(x, y + h) + f(x, y)) / (h*h);
        return fx;
    }

    public static double pochodna3x(double x, double y, double h) {
        double fx = 6;
        return fx;
    }
    public static double pochodna3y(double x, double y, double h) {
        double fx = 0;
        return fx;
    }
    /*
    public static double x(double a, double b, double e, int ii, int n, double h, double y) {
		if (n == 0) {
			if ((pochodna3x(a, y, h) == 0 && pochodna1x(a, y, h) == 0) || (pochodna3x(a, y, h) * pochodna1x(a, y, h) > 0)){
				return a;
			}
			else {
				return b;
			}
		}
		else {
			return x(a,b,e,ii,n-1,h,y) - (pochodna1x(x(a,b,e,ii,n-1,h,y), y, h) / pochodna2x(x(a,b,e,ii,n-1, h, y), y, h));
		}
	}
    
    public static double stycznychx(double a, double b, double e, int ii, int n, double h, double y, double x) {
    	ii += 1;
		if (pochodna1x(a, y, h) * pochodna1x(b, y, h) >= 0) {
			System.out.println("WARUNEK KONIECZNY NIE ZOSTA£ SPE£NIONY");
		}
		else {
			if (pochodna2x(a, y, h) * pochodna2x(b, y, h) < 0 && pochodna3x(a, y, h) * pochodna3x(b, y, h) < 0) {
				System.out.println("WARUNEK KONIECZNY NIE ZOSTA£ SPE£NIONY");
			}
			else {
				System.out.println("WARUNKI ZOSTA£Y SPE£NIONE");
				if ((pochodna3x(a, y, h) == 0 && pochodna1x(a, y, h) == 0) || (pochodna3x(a, y, h) * pochodna1x(a, y, h) > 0)) {
					System.out.println("Punkt startowy a = " + a);
				}
				else {
					System.out.println("Punkt startowy b = " + (b));
				}
				System.out.println("\nITERACJA NR " + (ii));
				System.out.println("x(" + (1) + ") = " + (x(a,b,e,ii,1,y,h)));
				System.out.println("f'(" + (1) + ") = " + (pochodna1x(x(a,b,e,ii,n,h,y), y, h)));
				if((Math.abs(pochodna1x(x(a,b,e,ii,1,h,y), y, h)) < e) || ((Math.abs(x(a,b,e,ii,1,h,y) - x(a,b,e,ii,0,h,y))) < e)) {
					System.out.println("\nRozwi¹zanie:  " + (x(a,b,e,ii,n,h,y)) + ". Iloœæ iteracji: " + ii);
				}
				else {
					n = 1;
					while(true) {
						ii += 1;
						System.out.println("\nITERACJA NR " + (ii));
						System.out.println("x(" + (n+1) + ") = " + (x(a,b,e,ii,n+1,h,y)));
						System.out.println("f'(" + (n+1) + ") = " + (pochodna1x(x(a,b,e,ii,n+1,h,y), y, h)));
						if((Math.abs(pochodna1x(x(a,b,e,ii,n+1,h,y), y, h)) < e) || ((Math.abs(x(a,b,e,ii,n+1,h,y) - x(a,b,e,ii,n,h,y))) < e)) {
							System.out.println("\nRozwi¹zanie X:  " + (x(a,b,e,ii,n+1,h,y)) + ". Iloœæ iteracji: " + ii);
							break;
						}
						else {
							n += 1;
						}
					}
				}
			}
		}
		return (x(a,b,e,ii,n+1,h,y));
    }
    
    public static double y(double a, double b, double e, int ii, int n, double h, double x) {
		if (n == 0) {
			if ((pochodna3y(x, a, h) == 0 && pochodna1y(x, a, h) == 0) || (pochodna3y(x, a, h) * pochodna1y(x, a, h) > 0)){
				return a;
			}
			else {
				return b;
			}
		}
		else {
			return y(a,b,e,ii,n-1,h,x) - (pochodna1y(x, y(a,b,e,ii,n-1,h,x), h) / pochodna2y(x, y(a,b,e,ii,n-1,h,x), h));
		}
	}
    
    public static double stycznychy(double a, double b, double e, int ii, int n, double h, double x, double y) {
    	ii += 1;
		if (pochodna1y(x, a, h) * pochodna1y(x, b, h) >= 0) {
			System.out.println("WARUNEK KONIECZNY NIE ZOSTA£ SPE£NIONY");
		}
		else {
			if (pochodna2y(x, a, h) * pochodna2y(x, b, h) < 0 && pochodna3y(x, a, h) * pochodna3y(x, b, h) < 0) {
				System.out.println("WARUNEK KONIECZNY NIE ZOSTA£ SPE£NIONY");
			}
			else {
				System.out.println("WARUNKI ZOSTA£Y SPE£NIONE");
				if ((pochodna3y(x, a, h) == 0 && pochodna1y(x, a, h) == 0) || (pochodna3y(x, a, h) * pochodna1y(x, a, h) > 0)) {
					System.out.println("Punkt startowy a = " + a);
				}
				else {
					System.out.println("Punkt startowy b = " + (b));
				}
				System.out.println("\nITERACJA NR " + (ii));
				System.out.println("x(" + (1) + ") = " + (y(a,b,e,ii,1,x,h)));
				System.out.println("f'(" + (1) + ") = " + (pochodna1y(x, y(a,b,e,ii,n,h,x), h)));
				if((Math.abs(pochodna1y(x, y(a,b,e,ii,1,h,x), h)) < e) || ((Math.abs( y(a,b,e,ii,1,h,x) - y(a,b,e,ii,0,h,x))) < e)) {
					System.out.println("\nRozwi¹zanie:  " + (y(a,b,e,ii,n,h,x)) + ". Iloœæ iteracji: " + ii);
				}
				else {
					n = 1;
					while(true) {
						ii += 1;
						System.out.println("\nITERACJA NR " + (ii));
						System.out.println("x(" + (n+1) + ") = " + (y(a,b,e,ii,n+1,h,x)));
						System.out.println("f'(" + (n+1) + ") = " + (pochodna1y(x, y(a,b,e,ii,n+1,h,x), h)));
						if((Math.abs(pochodna1y(x, y(a,b,e,ii,n+1,h,x), h)) < e) || ((Math.abs(y(a,b,e,ii,n+1,h,x) - y(a,b,e,ii,n,h,x))) < e)) {
							System.out.println("\nRozwi¹zanie Y:  " + (y(a,b,e,ii,n+1,h,x)) + ". Iloœæ iteracji: " + ii);
							break;
						}
						else {
							n += 1;
						}
					}
				}
			}
		}
		return (y(a,b,e,ii,n+1,h,x));
    }
    */
    public static double stycznychx(double a, double b, double e, double h, double y, double x) {
    	double xn = 0, x0 = 0;
    	if((pochodna1x(a, y, h) * pochodna1x(b, y, h) < 0)) {
    		if ((pochodna2x(a, y, h) * pochodna2x(b, y, h) >= 0) && (pochodna3x(a, y, h)*pochodna3x(b, y, h) >= 0)) {
    			if ((pochodna3x(a, y, h)*pochodna1x(a, y, h) > 0)) {
    				x0 = a;
    			}
    			else {
    				x0 = b;
    			}
    			while(true) {
    				xn = x0 - (pochodna1x(x0, y, h) / pochodna2x(x0, y, h));
    				if ((Math.abs(pochodna1x(xn, y, h))) < e || (Math.abs(xn-x0) < e )){
    					return xn;
    				}
    				x0 = xn;
    			}
    		}
    	}
    	else {
    		System.out.println("Warunek nie zostal spelniony." );
    		return x;
    	}
		return x;
		
    }
    
    public static double stycznychy(double a, double b, double e, double h, double x, double y) {
    	double yn = 0, y0 = 0;
    	if((pochodna1y(x, a, h) * pochodna1y(x, b, h) < 0)) {
    		if ((pochodna2y(x, a, h) * pochodna2y(x, b, h) >= 0) && (pochodna3y(x, a, h) * pochodna3y(x, b, h) >= 0)) {
    			if ((pochodna3y(x, a, h)*pochodna1y(x, a, h) > 0)) {
    				y0 = a;
    			}
    			else {
    				y0 = b;
    			}
    			while(true) {
    				yn = y0 - (pochodna1y(x, y0, h) / pochodna2y(x, y0, h));
    				if (((Math.abs(pochodna1y(x, yn, h))) < e) || ((Math.abs(yn-y0) < e) )){
    					return yn;
    				}
    				y0 =yn;
    			}
    		}
    	}
    	else {
    		System.out.println("Warunek nie zostal spelniony." );
    		return y;
    	}
		return y;
		
    }
    
	public static void GauSiedl(double x0, double y0, int i, double e) {
		double x = x0, y = y0, h = 0.00001;
		int a = 0, b = 200;
		while (true) {
			i += 1;
			System.out.println("\nIteracja nr: "+ i);
			//System.out.println("\nSprawdzenie blędów:["+ Math.abs((1.54858 - x)/1.54858) * 100 + "% , " + Math.abs((1.79858 - y)/1.79858) * 100 + "% ]");
			x = stycznychx(a, b, e/10, h, y, x);
			y = stycznychy(a, b, e/10, h, x, y);
			System.out.println("Proponowany wynik: [ " + x + " , " + y  + " ]. " );
			if((Math.abs(pochodna1x(x,y,h))<= e && Math.abs(pochodna1y(x,y,h))<= e)) {
				break;
			}
		}
		System.out.println("\nRozwiązanie: ["+ x + ", " + y + "]");	    
	}


	public static void main(String[] args) {
		double x = 1, y = 1, e = 0.00001;
		int i = 0;
		GauSiedl(x, y, i, e);
	}
}
