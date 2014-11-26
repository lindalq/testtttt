import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class CDSStest01 {

	static ArrayList<Condition> Condlist = new ArrayList<Condition>();
	static ArrayList<Symptom> Symlist = new ArrayList<Symptom>();
	static ArrayList<Attribute> Attrilist = new ArrayList<Attribute>();

	static ArrayList<String> presentingSym = new ArrayList(); // for storing
																// initial
																// presenting
																// symptoms
	static ArrayList<ArrayList<String>> displayAttriList = new ArrayList<ArrayList<String>>();
	static ArrayList<ArrayList<String>> chosenAttrlist = new ArrayList<ArrayList<String>>();

	static ArrayList<String> diagnosticToPerform = new ArrayList<String>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		completeSymList(); // print out possible list of Symptoms to choose from
		selectedSymList(); // receive input as to the selected
											// Sym
		matchSymandgenRAMDB(presentingSym); // System.out.println("successfully inputed DB");
		sumUpSymScore(); // sum up the symptoms and calculate score for each
							// condition
		Collections.sort(Condlist, new ConditionComparator()); // sort the
																// condition
																// based on
																// score

		for (String str : presentingSym) {// for each symptom of #1 Condition,
											// print out primary attributes of
											// this condition and each attribute
											// possible values
			System.out.println();
			System.out.println("possible Attributes of " + str
					+ ", and possible values");
			attriofPresentingSymptom(str, 1);
			outputPossAttriValuesforAttri(str);
			System.out.println();
			System.out.println("receiving choices from HA...");
			displayAttriList.clear();
		}
		
		//selectedAttriList();
		//chosenAttrilist = 
		
		
		System.out.println();
		System.out.println();
		System.out
				.println("////////////////////////////////////////////////////////////");
		System.out.println("in RAM: sorted Conditions");
		ramCondprint();

		// ramDBprint(); // Printing the current RAM DB
		// ramCondprint();
		// ramSymprint();
	}

	public static void completeSymList() {
		System.out.println("Possible Symptoms from Database: ");
		String CondDBFile = "ConditionDB.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(CondDBFile));

			while ((line = br.readLine()) != null) {
				String[] field = line.split(cvsSplitBy);
				if (!field[2].equals("")) {
					System.out.println(field[2]);
				}
			}
			br.close();
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void selectedSymList() {

		presentingSym.add("Significant weight loss");
		presentingSym.add("blah");

		System.out.println();
		System.out.println();

		System.out.println("user chose: ");
		for (String str : presentingSym) {
			System.out.print(str + ", ");
		}
		System.out.println();
	}

	public static void matchSymandgenRAMDB(ArrayList tempSym) {
		String CondDBFile = "ConditionDB.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		String tempCondline = "";
		String tempCondname;
		int tempCondrank;
		String tempSymname;
		int tempSymrelevance;

		int temptotalprimaryattri = 0;
		int temptotalprimarysym = 0;

		int attriCntLow = 0;
		int tempSympresent = 0;
		int symCntLow = 0;

		try {
			br = new BufferedReader(new FileReader(CondDBFile));
			line = br.readLine();
			// System.out.println(line);
			line = br.readLine();
			// System.out.println(line);

			while (line != null) {
				String[] field = line.split(cvsSplitBy);

				if (!field[0].equals("")) {
					br.mark(0);
					tempCondline = line;
				}
				// Marks the current position in this input stream. A subsequent
				// call to the reset method repositions this stream at the last
				// marked position so that subsequent reads re-read the same
				// bytes.
				// The readlimit argument tells this input stream to allow that
				// many bytes to be read before the mark position gets
				// invalidated.

				if (tempSym.contains(field[2])) {
					br.reset();
					field = tempCondline.split(cvsSplitBy);
					tempCondname = field[0];
					tempCondrank = Integer.parseInt(field[1]);
					tempSymname = field[2];
					tempSymrelevance = Integer.parseInt(field[3]);

					Attrilist.add(new Attribute(field[4], field[5], Float
							.parseFloat(field[6]), field[7], field[8], Symlist
							.size()));
					if (Float.parseFloat(field[6]) == 1
							|| Float.parseFloat(field[6]) == 1.1)
						temptotalprimaryattri++;
					line = br.readLine();
					if (line != null)
						field = line.split(cvsSplitBy);

					while (field[0].equals("") && line != null) {
						if (!field[2].equals("")) {
							if (tempSym.contains(tempSymname)) {
								// System.out.println("found a match!: " +
								// tempSymname);
								tempSympresent = 1;
							} else
								tempSympresent = 0;
							Symlist.add(new Symptom(tempSymname,
									tempSymrelevance, Condlist.size(),
									attriCntLow, Attrilist.size() - 1,
									temptotalprimaryattri, tempSympresent));
							if (tempSymrelevance == 1)
								temptotalprimarysym++;
							tempSymname = field[2];
							tempSymrelevance = Integer.parseInt(field[3]);
							attriCntLow = Attrilist.size();
							temptotalprimaryattri = 0;
						}
						Attrilist.add(new Attribute(field[4], field[5], Float
								.parseFloat(field[6]), field[7], field[8],
								Symlist.size()));
						if ((Float.parseFloat(field[6]) == 1)
								|| (Float.parseFloat(field[6]) == 1.1))
							temptotalprimaryattri++;
						line = br.readLine();
						if (line != null)
							field = line.split(cvsSplitBy);
					}
					if (tempSym.contains(tempSymname)) {
						// System.out.println("found a match!: " + tempSymname);
						tempSympresent = 1;
					} else
						tempSympresent = 0;
					Symlist.add(new Symptom(tempSymname, tempSymrelevance,
							Condlist.size(), attriCntLow, Attrilist.size() - 1,
							temptotalprimaryattri, tempSympresent));
					if (tempSymrelevance == 1)
						temptotalprimarysym++;
					attriCntLow = Attrilist.size();
					temptotalprimaryattri = 0;

					Condlist.add(new Condition(tempCondname, tempCondrank,
							symCntLow, Symlist.size() - 1, temptotalprimarysym));
					symCntLow = Symlist.size();
					temptotalprimarysym = 0;
					br.mark(0);
					tempCondline = line;
				}
				line = br.readLine();
			}
			br.close();
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void ramDBprint() {
		System.out.println("number of Condition nodes:" + Condlist.size());
		System.out.println("number of Symptom nodes:" + Symlist.size());
		System.out.println("number of Attribute nodes:" + Attrilist.size());
		for (int i = 1; i <= Condlist.size(); i++) {
			System.out.println();
			System.out.println("Conditions rank: " + i);
			System.out.println();
			Condition temp = Condlist.get(i - 1);
			temp.printCond();
		}
		for (int j = 1; j <= Symlist.size(); j++) {
			System.out.println();
			System.out.println("Symptoms array: " + j);
			System.out.println();
			Symptom tempsym = Symlist.get(j - 1);
			tempsym.printSym();
		}
		for (int k = 1; k <= Attrilist.size(); k++) {
			System.out.println();
			System.out.println("Attributes array: " + k);
			System.out.println();
			Attribute tempattri = Attrilist.get(k - 1);
			tempattri.printAttri();
		}

	}

	public static void ramCondprint() {
		System.out.println("number of Condition nodes:" + Condlist.size());
		System.out.println("number of Symptom nodes:" + Symlist.size());
		System.out.println("number of Attribute nodes:" + Attrilist.size());
		for (int i = 1; i <= Condlist.size(); i++) {
			System.out.println();
			System.out.println("Conditions rank: " + i);
			System.out.println();
			Condition temp = Condlist.get(i - 1);
			temp.printCond();
		}
	}

	public static void ramSymprint() {
		System.out.println("number of Condition nodes:" + Condlist.size());
		System.out.println("number of Symptom nodes:" + Symlist.size());
		System.out.println("number of Attribute nodes:" + Attrilist.size());

		for (int j = 1; j <= Symlist.size(); j++) {
			System.out.println();
			System.out.println("Symptoms array: " + j);
			System.out.println();
			Symptom tempsym = Symlist.get(j - 1);
			tempsym.printSym();
		}

	}

	public static void ramAttriprint() {
		System.out.println("number of Condition nodes:" + Condlist.size());
		System.out.println("number of Symptom nodes:" + Symlist.size());
		System.out.println("number of Attribute nodes:" + Attrilist.size());
		for (int k = 1; k <= Attrilist.size(); k++) {
			System.out.println();
			System.out.println("Attributes array: " + k);
			System.out.println();
			Attribute tempattri = Attrilist.get(k - 1);
			tempattri.printAttri();
		}

	}

	public static void sumUpSymScore() {
		String tempCondname;
		String tempSymname;
		float tempCondScore = 0;
		float tempCondSymSum = 0;
		int tempsympresent;
		float tempsymrelevance;
		float tempCondrisk = 0;

		for (int j = 1; j <= Symlist.size(); j++) {
			Symptom tempsym = Symlist.get(j - 1);
			tempSymname = tempsym.getname();
			Condition tempCond = Condlist.get(tempsym.getparent());
			tempCondname = tempCond.getname();

			tempCondSymSum = tempCond.getsumsym();
			tempsympresent = tempsym.getpresent();
			tempsymrelevance = tempsym.getrelevance();
			tempCondSymSum += tempsympresent * (1 / tempsymrelevance);
			tempCond.changesumsym(tempCondSymSum);

			tempCondScore = tempCond.getscore();
			tempCondrisk = tempCond.getrisk();
			tempCondScore += tempCondSymSum * (1 / tempCondrisk);
			tempCond.changescore(tempCondScore);
		}

		System.out.println();
	}

	public static void attriofPresentingSymptom(String currentPresentSym,
			float tempwantattrirelevance)
	// index of first symptom, from condition, then match it up.
	{
		Condition tempCond = Condlist.get(0);
		int tempsymbegin = tempCond.getsymbegin();
		int tempsymend = tempCond.getsymend();

		Symptom tempsym;
		String tempsymname;
		int tempattribegin;
		int tempattriend;

		Attribute tempattri;
		String tempattriname;
		String tempattrivalue;
		float tempattrirelevance;

		ArrayList<String> tempattrilist = new ArrayList<String>();
		String tempattrilistname;

		for (int j = tempsymbegin; j <= tempsymend; j++) {
			// System.out.println("looking at Symptom " + tempsymbegin
			// + " of Conditing 1. Index: " + j);
			tempsym = Symlist.get(j);
			tempsymname = tempsym.getname();
			if (tempsymname.equals(currentPresentSym)) {
				// System.out.println("found the presenting symptom match");
				tempattribegin = tempsym.getattribegin();
				tempattriend = tempsym.getattriend();
				// System.out
				// .println("the attributes that belong to this symptom are"
				// + tempattribegin + " and " + tempattriend);
				for (int k = tempattribegin; k <= tempattriend; k++) {
					tempattri = Attrilist.get(k);
					tempattrirelevance = tempattri.getrelevance();
					// System.out
					// .println("we're looking at this attribute with its relevance: "
					// + tempattrirelevance);
					if ((float) Math.ceil(tempattrirelevance) == tempwantattrirelevance) {
						// System.out
						// .println("yay! we have an attribute that match the relevancy we wanted!");
						tempattriname = tempattri.getname();
						if (tempattrirelevance == 1.2
								|| tempattrirelevance == 1.3) {
							diagnosticToPerform.add(tempattriname);
							// System.out
							// .println("need to do this diagnostic later:"
							// + tempattriname);
						} else {
							tempattrilist.add(tempattriname);
							// System.out
							// .println("this attribute name have been added to the list: "
							// + tempattriname);
						}
					}
				}
			}
		}
		ArrayList<String>[] tempattrivaluelists = (ArrayList<String>[]) new ArrayList[tempattrilist
				.size()];
		// System.out.println(tempattrilist.size());

		for (int m = 0; m < tempattrilist.size(); m++) {
			tempattrilistname = tempattrilist.get(m);
			tempattrivaluelists[m] = new ArrayList<String>();
			tempattrivaluelists[m].add(tempattrilistname);
			// System.out.println("the index of " + tempattrilistname + "is" +
			// m);
		}

		for (int l = 0; l < Attrilist.size(); l++) {
			tempattri = Attrilist.get(l);
			tempattriname = tempattri.getname();
			// System.out.println("looking at attri" + tempattriname);

			for (int m = 0; m < tempattrilist.size(); m++) {
				tempattrilistname = tempattrilist.get(m);
				// System.out.println("trying to match: " + tempattrilistname);
				if (tempattriname.equals(tempattrilistname)) {
					tempattrivalue = tempattri.getvalue();
					tempattrivaluelists[m].add(tempattrivalue);
					// System.out.println("matched! and it's stored at array" +
					// m);
				}
			}
		}

		for (int m = 0; m < tempattrilist.size(); m++) {
			displayAttriList.add(tempattrivaluelists[m]);
			// System.out.println("just added values of "
			// + (tempattrivaluelists[m].get(0)).toString()
			// + " to the array");
		}
	}

	public static void outputPossAttriValuesforAttri(String currentPresentSym) {

		if (displayAttriList.isEmpty()) {
			System.out.println("no primary attribute for this symptom");
		} else {
			for (int i = 0; i < displayAttriList.size(); i++) {
				ArrayList<String> tempattri = displayAttriList.get(i);
				System.out.print((tempattri.get(i)).toString() + ": ");
				for (int j = 1; j < tempattri.size(); j++) {
					System.out.print((tempattri.get(j)).toString() + ", ");
				}
				System.out.println();
			}
		}
	}

	public static void sumUpAttriScore(){
		String tempCondname;
		String tempSymname;
		float tempCondScore = 0;
		float tempCondSymSum = 0;
		int tempsympresent;
		float tempsymrelevance;
		float tempCondrisk = 0;

		for (int j = 1; j <= Symlist.size(); j++) {
			Symptom tempsym = Symlist.get(j - 1);
			tempSymname = tempsym.getname();
			Condition tempCond = Condlist.get(tempsym.getparent());
			tempCondname = tempCond.getname();

			tempCondSymSum = tempCond.getsumsym();
			tempsympresent = tempsym.getpresent();
			tempsymrelevance = tempsym.getrelevance();
			tempCondSymSum += tempsympresent * (1 / tempsymrelevance);
			tempCond.changesumsym(tempCondSymSum);

			tempCondScore = tempCond.getscore();
			tempCondrisk = tempCond.getrisk();
			tempCondScore += tempCondSymSum * (1 / tempCondrisk);
			tempCond.changescore(tempCondScore);
		}

		System.out.println();
		
		
	}
	
}