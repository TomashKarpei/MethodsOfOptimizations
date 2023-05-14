import java.util.Arrays;
import java.util.Collections;

public class Simplex2 {
	private static int iloscZmiennych = 2;
	// f(x) = x1 + 2*x2;
	
	public static double getMaxValue(double[][] numbers) {
        double maxValue = numbers[0][0];
        for (int j = 0; j < numbers.length; j++) {
            for (int i = 0; i < numbers[j].length; i++) {
                if (numbers[j][i] > maxValue) {
                    maxValue = numbers[j][i];
                }
            }
        }
        return maxValue;
    }

    public static double getMinValue(double[][] ogr) {
        double minValue = ogr[0][0];
        for (int j = 0; j < ogr.length; j++) {
            for (int i = 0; i < ogr[j].length; i++) {
                if (ogr[j][i] < minValue ) {
                    minValue = ogr[j][i];
                }
            }
        }
        return minValue ;
    }
    
	public static double f(double [] x, double s1, double m) {
		return 2*x[0] + 18*x[1] + m*s1;
	}
	
	private static void SimplexMin(double [] ci, double[] cj, double[][] ogr) {
		double  m = ci[0];
		double minBiAi = Math.abs(m*100), min = Math.abs(m*100), skrzyz = 0;
		double [] x = new double[iloscZmiennych];
		double [][] ogrTemp = new double [ci.length+2][1]; 
		int krytWejsc = 0, krytWyjsc = 0, iloscNiedod = 0;
		
		while(true) {
			minBiAi = Math.abs(m*100);
			min = Math.abs(m*100);
			//obliczamy zj:
			for (int j = 0; j < cj.length; j++) {
				ogr[ci.length][j] = ci[0]*ogr[0][j] + ci[1]*ogr[1][j] + ci[2]*ogr[2][j];
				//System.out.println(ogr[ci.length][j]);
			}	
			//obliczamy cj - zj oraz kryt wejscia:
			for (int j = 0; j < cj.length; j++) {
				ogr[ci.length+1][j] = cj[j]-ogr[ci.length][j];
				if (ogr[ci.length+1][j] < min) {
					min = ogr[ci.length+1][j];
					krytWejsc = j;
				}
				//System.out.println(ogr[ci.length+1][j]);
			}
			for (int j = 0; j < cj.length+1; j++) {
				if (ogr[ci.length+1][j] < 0) {
					iloscNiedod += 1;
				}
			}
			
			if (iloscNiedod == 0 ) {
				break;
			}
			else {
				iloscNiedod = 0;
				//okreslamy kryterium wyjscia:
				for (int i = 0; i < ci.length; i++) {
					ogr[i][cj.length+1] = ogr[i][cj.length]/ogr[i][krytWejsc];
					if(ogr[i][cj.length+1] < minBiAi && ogr[i][cj.length+1] >= 0) {
						minBiAi = ogr[i][cj.length+1];
						krytWyjsc = i;
					}
					//System.out.println("x["+i+"]["+(cj.length+1)+"] = " +ogr[i][cj.length+1]);
				}
				skrzyz = ogr[krytWyjsc][krytWejsc];
				System.out.println("Wartosc skrzyzowana: " +skrzyz);
				
				//k[krytWyjsc] = cj[krytWejsc];
				for (int i = 0; i < ci.length+2; i++) {
					 ogrTemp[i][0] = ogr[i][krytWejsc];
				}
				for (int i = 0; i < ci.length+2; i++) {
					for (int j = 0; j < cj.length+1; j++) {
						if (i == krytWyjsc){
							ogr[i][j] = ogr[i][j]/skrzyz;
							//System.out.println("SKRZ: x["+i+"]["+j+"] = " +ogr[i][j]);
						}
					}
				}
				for (int i = 0; i < ci.length+2; i++) {
					for (int j = 0; j < cj.length+1; j++) {
						if (i != krytWyjsc && i != ci.length && i != ci.length+1 ) {
							ogr[i][j] = ogr[i][j] - (ogrTemp[i][0] * ogr[krytWyjsc][j]);
							//System.out.println("x["+i+"]["+j+"] = " +ogr[i][j]);
						}
						else if (i == ci.length || i == ci.length+1) {
							ogr[i][j] = 0;
							//System.out.println("x["+i+"]["+j+"] = " +ogr[i][j]);
						}
					}
				}
				for (int i = 0; i < ci.length+2; i++) {
					 ogrTemp[i][0] = 0;
				}
				ci[krytWyjsc]=cj[krytWejsc];
				System.out.println("ci :"+ci[krytWyjsc]);
				
			}
		}
		for (int i = 0; i < iloscZmiennych; i++) {
			x[i] = ogr[i][cj.length];
		}
		for (int i = 0; i <= iloscZmiennych; i++) {
		System.out.println("b"+ (i+1) +" = " + ogr[i][cj.length]);
		}
		for (int i = 0; i < iloscZmiennych; i++) {
			if (ci[krytWyjsc] == cj[i])
				System.out.println("Wynik: x"+ (i+1) +" = " + ogr[krytWyjsc][cj.length]);
			else
				System.out.println("Wynik: x"+ (i+1) +" = " + 0);
		}
		
	}

	private static void SimplexMax(double [] ci, double[] cj, double[][] ogr) {
		double  m = ci[0];
		double minBiAi = Math.abs(m*100), max = 0, skrzyz = 0;
		double [] x = new double[iloscZmiennych];
		double [][] ogrTemp = new double [ci.length+2][1]; 
		int krytWejsc = 0, krytWyjsc = 0, iloscNiedod = 0;
		
		while(true) {
			minBiAi = Math.abs(m*100);
			max = 0;
			//obliczamy zj:
			for (int j = 0; j < cj.length; j++) {
				ogr[ci.length][j] = ci[0]*ogr[0][j] + ci[1]*ogr[1][j] + ci[2]*ogr[2][j];
				//System.out.println(ogr[ci.length][j]);
			}	
			//obliczamy cj - zj oraz kryt wejscia:
			for (int j = 0; j < cj.length; j++) {
				ogr[ci.length+1][j] = cj[j]-ogr[ci.length][j];
				if (ogr[ci.length+1][j] > max) {
					max = ogr[ci.length+1][j];
					krytWejsc = j;
				}
				//System.out.println(ogr[ci.length+1][j]);
			}
			for (int j = 0; j < cj.length+1; j++) {
				if (ogr[ci.length+1][j] > 0) {
					iloscNiedod += 1;
				}
			}
			
			if (iloscNiedod == 0 ) {
				break;
			}
			else {
				iloscNiedod = 0;
				//okreslamy kryterium wyjscia:
				for (int i = 0; i < ci.length; i++) {
					ogr[i][cj.length+1] = ogr[i][cj.length]/ogr[i][krytWejsc];
					if(ogr[i][cj.length+1] < minBiAi && ogr[i][cj.length+1] >= 0) {
						minBiAi = ogr[i][cj.length+1];
						krytWyjsc = i;
					}
					//System.out.println("x["+i+"]["+(cj.length+1)+"] = " +ogr[i][cj.length+1]);
				}
				skrzyz = ogr[krytWyjsc][krytWejsc];
				System.out.println("Wartosc skrzyzowana: " +skrzyz);
				
				//k[krytWyjsc] = cj[krytWejsc];
				for (int i = 0; i < ci.length+2; i++) {
					 ogrTemp[i][0] = ogr[i][krytWejsc];
				}
				
				for (int i = 0; i < ci.length+2; i++) {
					for (int j = 0; j < cj.length+1; j++) {
						if (i == krytWyjsc){
							ogr[i][j] = ogr[i][j]/skrzyz;
							//System.out.println("SKRZ: x["+i+"]["+j+"] = " +ogr[i][j]);
						}
					}
				}
				for (int i = 0; i < ci.length+2; i++) {
					for (int j = 0; j < cj.length+1; j++) {
						if (i != krytWyjsc && i != ci.length && i != ci.length+1 ) {
							ogr[i][j] = ogr[i][j] - (ogrTemp[i][0] * ogr[krytWyjsc][j]);
							//System.out.println("x["+i+"]["+j+"] = " +ogr[i][j]);
						}
						else if (i == ci.length || i == ci.length+1) {
							ogr[i][j] = 0;
							//System.out.println("x["+i+"]["+j+"] = " +ogr[i][j]);
						}
					}
				}
				for (int i = 0; i < ci.length+2; i++) {
					 ogrTemp[i][0] = 0;
				}
				ci[krytWyjsc]=cj[krytWejsc];
				System.out.println("ci :"+ci[krytWyjsc]);
				
			}
		}
		for (int i = 0; i < iloscZmiennych; i++) {
			x[i] = ogr[i][cj.length];
		}
		for (int i = 0; i <= iloscZmiennych; i++) {
			System.out.println("b"+ (i+1) +" = " + ogr[i][cj.length]);
			}
		for (int i = 0; i < iloscZmiennych; i++) {
			if (ci[krytWyjsc] == cj[i])
				System.out.println("Wynik: x"+ (i+1) +" = " + ogr[krytWyjsc][cj.length]);
			else
				System.out.println("Wynik: x"+ (i+1) +" = " + 0);
		}
		
	}

	public static void main(String[] args) {
		boolean isMin = true;
		double m;
		
		double [][] ogr =  {{80, 100, -1, 0, 0, 1, 100, 0}, {20, 200, 0, 1, 0, 0, 300, 0}, {1,0,0,0,1,0,1.5,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}};

		if (isMin == true) {
			if (getMaxValue(ogr) == 0) {
				m = 1000;
			}
			else {
				m = 1000*Math.abs(getMaxValue(ogr));
			}
			double ci [] = {m,0,0};
			double cj [] = {2,18,0,0,0,m};
			SimplexMin(ci, cj, ogr);
		}
		else {
			if (getMinValue(ogr) == 0) {
				m = -1000;
			}
			else {
				m = -1000*Math.abs(getMinValue(ogr));
			}
			double ci [] = {m,0,0};
			double cj [] = {2,18,0,0,0,m};
			SimplexMax(ci, cj, ogr);
		}
	}
}
