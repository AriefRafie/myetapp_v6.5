package ekptg.faraid;

import java.util.ArrayList;
import java.util.List;

public class Fraction 
{
	private int numer = 0;
	private int denom = 0;

	public Fraction(int numer, int denom) {
		if (denom == 0)
			throw new NumberFormatException("denominator is zero");
		this.numer = numer;
		this.denom = denom;
		this.reduce();
	}
	
	public Fraction (long numer,long denom) {
		if (denom == 0)
			throw new NumberFormatException("denominator is zero");
		this.numer = (int)numer;
		this.denom = (int)denom;
		//this.reduce();
	}

	public Fraction() {
		throw new IllegalArgumentException("no noarg constructor");
	}

	public Object clone() {
		return new Fraction(this.numer, this.denom);
	}

	public int getNumerator() {
		return this.numer;
	}

	public int getDenominator() {
		return this.denom;
	}

	public void reduce()
	{
		int	d;

		while (true) {
			d = this.gcd(this.numer, this.denom);
			if (d == 1)
				return;
			this.numer /= d;
			this.denom /= d;
		}
	}

	private static int gcd(int a, int b) {
		int	t;

		while (b != 0) {
			t = a;
			a = b;
			b = t%a;
		}
		return (a);
	}

	public String toString() {
		return ("(" + this.numer + "/" + this.denom + ")");
	}

	public double doubleValue() {
		return (1.0*this.numer/this.denom);
	}

	public int wholePortion() {
		return (int)this.doubleValue();
	}

	public Fraction fractionPortion() {
		Fraction f = new Fraction(this.wholePortion(), 1);
		return subtract(this, f);
	}

	public static Fraction add(Fraction f1, Fraction f2) {
		int	n,
			d;
		
		n = f1.numer*f2.denom + f2.numer*f1.denom;
		d = f1.denom*f2.denom;
		return new Fraction(n, d);
	}

	public static Fraction subtract(Fraction f1, Fraction f2) {
		int	n,
			d;

		n = f1.numer*f2.denom - f2.numer*f1.denom;
		d = f1.denom*f2.denom;
		return new Fraction(n, d);
	}


	public static Fraction addCoef(int c1, Fraction f1,
	                               int c2, Fraction f2) {
		int	n,
			d;

		n = c1*f1.numer*f2.denom + c2*f2.numer*f1.denom;
		d = f1.denom*f2.denom;
		return new Fraction(n, d);
	}

	public static Fraction mult(Fraction f1, Fraction f2) {
		return new Fraction(f1.numer*f2.numer, f1.denom*f2.denom);
	}

	public static Fraction div(Fraction f1, Fraction f2) {
		return new Fraction(f1.numer*f2.denom, f1.denom*f2.numer);
	}

	public static int compare(Fraction f1, Fraction f2) {
		double	delta;

		if (f1.numer == f2.numer && f1.denom == f2.denom)
			return (0);
		delta = f1.doubleValue() - f2.doubleValue();
		if (delta > 0)
			return 1;
		else if (delta < 0)
			return -1;
		else
			throw new IllegalStateException();
	}

	public static Fraction max(Fraction f1, Fraction f2) {
		if (f1.doubleValue() - f2.doubleValue() >= 0)
			return f1;
		else
			return f2;
	}

	public static Fraction min(Fraction f1, Fraction f2) {
		if (f1.doubleValue() - f2.doubleValue() <= 0)
			return f1;
		else
			return f2;
	}

	public static void reduce(Fraction f1,
			Fraction f2) {
		f1.reduce();
		f2.reduce();
	}
	
	public static void getTashiehFraction(Fraction f1,
			Fraction f2) {

		int pembawah = findPembawah(f1.denom,f2.denom,0);
		f1.numer = f1.numer * (pembawah/f1.denom);
		f2.numer = f2.numer * (pembawah/f2.denom);	
		f1.denom = pembawah;
		f2.denom = pembawah;
	}
	
	public static void getTashiehFraction(Fraction f1,
			Fraction f2,Fraction f3) {
	
		int pembawah = findPembawah(f1.denom,f2.denom,f3.denom);
		f1.numer = f1.numer * (pembawah/f1.denom);
		f2.numer = f2.numer * (pembawah/f2.denom);
		f3.numer = f3.numer * (pembawah/f3.denom);
		
		f1.denom = pembawah;
		f2.denom = pembawah;
		f3.denom = pembawah;
	}

	public static int findPembawah(int a,int b,int c) {
		int count=1;
		int output=0;
		List A = new ArrayList(); List B = new ArrayList(); List C = new ArrayList(); 
		boolean flag=true;
		while (flag == true) {
			A.add(a * count);B.add(b * count);
			if (c > 0) { 
				C.add(b * count);
				output = checkExist(A,B,C);
			} else {
				output = checkExist(A,B);
			}
			if (output > 0) {
				flag=false;
			}
			count++;
			if (count > 200) break;//something bad happen here...
		}
		return output;
	}

	public static int checkExist(List A,List B) {
		for (int x=0;x<A.size();x++) {
			for (int y=0;y<B.size();y++) {
					if (A.get(x) == B.get(y)) {
						return Integer.parseInt(""+A.get(x));
					}
			}
		}
		return 0;//not exist
	}
	
	public static int checkExist(List A,List B,List C) {
		for (int x=0;x<A.size();x++) {
			for (int y=0;y<B.size();y++) {
				for (int z=0;z<C.size();z++) {
					if (A.get(x) == B.get(y) && A.get(x) == C.get(z)) {
						return Integer.parseInt(""+A.get(x));
					}
				}
			}
		}
		return 0;//not exist
	}
	
	public static void main(String[] args) {
		Fraction	f1,
				f2,f3,
				s;
		
		int A[]={8,16,24,32,33,34};
		int B[]={6,12,18,24,35,36};
		int C[]={4,8,12,16,20,24};
		
		for (int x=0;x<A.length;x++) {
			for (int y=0;y<B.length;y++) {
				for (int z=0;z<C.length;z++) {
					if (A[x] == B[y] && A[x] == C[z]) {
						System.out.println("Match at position ("+x+","+y+","+z+")");
						System.out.println("A="+A[x]+",B="+B[y]+",C="+C[z]);
						break;
					}
				}
			}
		}
		
		/*
		f1 = new Fraction(1, 4);
		
		f2 = new Fraction(1, 2);
		getTashiehFraction(f1,f2);
		System.out.println("f1 baru:"+f1.toString());
		System.out.println("f2 baru:"+f2.toString());
		//f3 = new Fraction(1, 4);
		//s = Fraction.add(f1, f2);
		//System.out.println("result: " + s.toString());
		//s = Fraction.add(f1, f3);
		//System.out.println("result2: " + s.toString());
		
		//int x = Fraction.gcd(3,6);
		//System.out.println("gcd(3,6):"+x);
		/*
		System.out.println("duh");
		f1 = new Fraction(3, 7);
		System.out.println("f1: " + f1.toString());
		f2 = new Fraction(18, 4);
		System.out.println("f1: " + f1.toString());
		System.out.println("f2: " + f2.toString());
		s = Fraction.add(f1, f2);
		System.out.println("f1+f2: " + s.toString());
		s = Fraction.addCoef(3, f1, -1, f2);
		System.out.println("3*f1+f2: " + s.toString());
		f2 = new Fraction(18, 1);
		*/
	}
}

