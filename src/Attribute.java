import java.io.*;

class Attribute {
	private String name;
	private String unit;
	private float relevance;
	private String type;
	private String value;
	private int present = 0;
	private int parent;

	public Attribute (String nameTemp, String unitTemp, float relevanceTemp, String typeTemp, String valueTemp, int parentTemp)
	{
	name= nameTemp;
	unit = unitTemp;
	relevance = relevanceTemp;
	type = typeTemp;
	value = valueTemp;
	parent = parentTemp;	
	}
	
	public void printAttri()
	{
		System.out.println("name: "+ name);
		System.out.println("unit: "+ unit);
		System.out.println("relevance: " + relevance);
		System.out.println("type: " + type);
		System.out.println("value: " + value);
		System.out.println ("present: " + present);
		System.out.println("parent: " + parent);
	}

	public String getname() {
		return name;
	}
	public String getunit() {
		return unit;
	}
	public float getrelevance() {
		return relevance;
	}
	public String gettype() {
		return type;
	}
	public String getvalue() {
		return value;
	}
	public int getpresent() {
		return present;
	}
	public int getparent() {
		return parent;
	}

	public void changepresent(int newpresent)
	{
		present = newpresent;
	}
}
