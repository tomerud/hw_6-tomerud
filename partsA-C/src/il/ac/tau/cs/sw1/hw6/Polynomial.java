package il.ac.tau.cs.sw1.hw6;

import java.util.Arrays;

public class Polynomial {
	
	/*
	 * Creates the zero-polynomial with p(x) = 0 for all x.
	
	 */
	public double[] coeff;
	public Polynomial() {
		this.coeff = new double[]{0.0};
	} 
	/*
	 * Creates a new polynomial with the given coefficients.
	 */
	public Polynomial(double[] coefficients) {
		this.coeff = coefficients;
	}
	/*
	 * Addes this polynomial to the given one
	 *  and retruns the sum as a new polynomial.
	 */
	public Polynomial adds(Polynomial polynomial) {
		int n = this.coeff.length;
		int m = polynomial.coeff.length;
		int M = Math.max(n, m);
		double[] sumCoeff = Arrays.copyOf(this.coeff, M);
		for(int i = 0; i < m; i++) {
			sumCoeff[i] += polynomial.coeff[i];
		}
		return new Polynomial(sumCoeff);
	}
	/*
	 * Multiplies a to this polynomial and returns 
	 * the result as a new polynomial.
	 */
	public Polynomial multiply(double a) {
		int n = this.coeff.length;
		double[] multyPolynomial = Arrays.copyOf(this.coeff, n);
		for(int i = 0; i < n ; i++) {
			multyPolynomial[i] *= a;
		}
		return new Polynomial(multyPolynomial);
	}
	/*
	 * Returns the degree (the largest exponent) of this polynomial.
	 */
	public int getDegree() {
		int n = this.coeff.length;
		return n-1;
	}
	/*
	 * Returns the coefficient of the variable x 
	 * with degree n in this polynomial.
	 */
	public double getCoefficient(int n) {
		return this.coeff[n];
	}
	
	/*
	 * set the coefficient of the variable x 
	 * with degree n to c in this polynomial.
	 * If the degree of this polynomial < n, it means that that the coefficient of the variable x 
	 * with degree n was 0, and now it will change to c. 
	 */
	public void setCoefficient(int n, double c) {
		int deg = this.getDegree();
		if(deg < n) {
			double[] coeff1 = Arrays.copyOf(coeff, n+1);
			this.coeff = coeff1;
		}
		this.coeff[n] = c;
	}
	
	/*
	 * Returns the first derivation of this polynomial.
	 *  The first derivation of a polynomal a0x0 + ...  + anxn is defined as 1 * a1x0 + ... + n anxn-1.
	
	 */
	public Polynomial getFirstDerivation()
	{
		int deg = getDegree();
		double[] coeff1 = new double[deg];
		if(deg <= 0) {
			return new Polynomial();}
		for(int i = 0; i < coeff1.length; i++) {
			coeff1[i] = (i+1) * coeff[i+1];}

		return new Polynomial(coeff1);
	}
	
	/*
	 * given an assignment for the variable x,
	 * compute the polynomial value
	 */
	public double computePolynomial(double x) {
		int n = coeff.length;
		double sum = 0;
		for(int i = 0; i <n; i++ ) {
			double calc = Math.pow(x, i);
			sum += coeff[i] * calc;
		}
		return sum ;
	}
	
	/*
	 * given an assignment for the variable x,
	 * return true iff x is an extrema point (local minimum or local maximum of this polynomial)
	 * x is an extrema point if and only if The value of first derivation of a polynomal at x is 0
	 * and the second derivation of a polynomal value at x is not 0.
	 */
	public boolean isExtrema(double x)
	{
		Polynomial first = this.getFirstDerivation();
		Polynomial second = first.getFirstDerivation();
		return (first.computePolynomial(x) == 0 && second.computePolynomial(x)!=0);
	}

}
