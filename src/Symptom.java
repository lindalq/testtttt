import java.io.*;

class Symptom {
	private String name;
	private float score = 0;
	private int relevance;
	private int present = 0;
	private int parent;
	private float sumattri = 0;
	private int attribegin;
	private int attriend;
	private int totalprimaryattri;

	public Symptom(String nameTemp, int relevanceTemp, int parentTemp,
			int attribeginTemp, int attriendTemp, int totalprimaryattriTemp,
			int presentTemp) {
		name = nameTemp;
		relevance = relevanceTemp;
		parent = parentTemp;
		attribegin = attribeginTemp;
		attriend = attriendTemp;
		totalprimaryattri = totalprimaryattriTemp;
		present = presentTemp;
	}

	public void printSym() {
		System.out.println("name: " + name);
		System.out.println("score: " + score);
		System.out.println("relevance: " + relevance);
		System.out.println("present: " + present);
		System.out.println("parent: " + parent);
		System.out.println("sumattri: " + sumattri);
		System.out.println("attribegin: " + attribegin);
		System.out.println("attriend: " + attriend);
		System.out.println("totalprimaryattri: " + totalprimaryattri);
	}

	public String getname() {
		return name;
	}

	public float getscore() {
		return score;
	}

	public int getrelevance() {
		return relevance;
	}

	public int getpresent() {
		return present;
	}

	public int getparent() {
		return parent;
	}

	public float getsumattri() {
		return sumattri;
	}

	public int getattribegin() {
		return attribegin;
	}

	public int getattriend() {
		return attriend;
	}

	public int gettotalprimaryattri() {
		return totalprimaryattri;
	}

	public void changescore(float newtotalscore){
		score = newtotalscore;
	}
	public void changepresent(int newpresent)
	{
		present = newpresent;
	}
	public void changesumattri(float newsumattri)
	{
		sumattri = newsumattri;
	}


}
