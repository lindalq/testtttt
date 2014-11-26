import java.io.*;
import java.util.*;

class Condition {
	private String name;
	private float score = 0;
	private int risk;
	private float sumsym = 0;
	private int symbegin;
	private int symend;
	private int totalprimarysym;

	public Condition(String nameTemp, int riskTemp, int symbeginTemp,
			int symendTemp, int totalprimarysymTemp) {
		name = nameTemp;
		risk = riskTemp;
		symbegin = symbeginTemp;
		symend = symendTemp;
		totalprimarysym = totalprimarysymTemp;
	}

	public void printCond() {
		System.out.println("name: " + name);
		System.out.println("score: " + score);
		System.out.println("risk: " + risk);
		System.out.println("sumsym: " + sumsym);
		System.out.println("symbegin: " + symbegin);
		System.out.println("symend: " + symend);
		System.out.println("totalprimarysym: " + totalprimarysym);
	}

	public String getname() {
		return name;
	}

	public float getscore() {
		return score;
	}

	public int getrisk() {
		return risk;
	}

	public float getsumsym() {
		return sumsym;
	}

	public int getsymbegin() {
		return symbegin;
	}

	public int getsymend() {
		return symend;
	}

	public int gettotalprimarysym() {
		return totalprimarysym;
	}

	public void changescore(float newtotalscore) {
		score = newtotalscore;
	}

	public void changesumsym(float newsumsym) {
		sumsym = newsumsym;
	}

}

class ConditionComparator implements Comparator<Condition> {
	public int compare(Condition cond1, Condition cond2) {
		int result = Float.compare(cond2.getscore(), cond1.getscore());
		return result;
	}
}