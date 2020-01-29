import java.util.ArrayList;

import javax.swing.JFileChooser;

import java.io.*; //File,FileReader,FileNotFoundException,BufferedReader,IOException

public class ScotlandHistoricList {
	private ArrayList<ScotlandHistoric> historicList;

	public ScotlandHistoricList() {
		historicList = new ArrayList<ScotlandHistoric>();
	}

	public void add(ScotlandHistoric sb) {
		historicList.add(sb);
	}

	public void readFromCSV(JFileChooser fileopen) {

		File file = fileopen.getSelectedFile();
		FileReader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			BufferedReader infile = new BufferedReader(reader);
			String line;
			while ((line = infile.readLine()) != null) {
				while ((line = infile.readLine()) != null) {
					String[]tokens = line.replace(",", " ").split("\"");					
					String REFERENCE = tokens[1].trim();
					String ADDRESS = tokens[3].trim();
					String CATEGORY = tokens[5].trim();
					String LISTED = tokens[7].trim();
					String COUNCIL = tokens[9].trim();
					String PARISH = tokens[11].trim();
					ScotlandHistoric sb = new ScotlandHistoric(REFERENCE, ADDRESS, CATEGORY, LISTED, COUNCIL, PARISH);
					historicList.add(sb);
				}
			}
			infile.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}

	public Object[][] convert2Data() {
		Object[][] data = new Object[historicList.size()][6];
		for (int i = 0; i < historicList.size(); i++) {
			data[i][0] = historicList.get(i).getReference();
			data[i][1] = historicList.get(i).getAddress();
			data[i][2] = historicList.get(i).getCategory();
			data[i][3] = historicList.get(i).getListed();
			data[i][4] = historicList.get(i).getCouncil();
			data[i][5] = historicList.get(i).getParish();
		}
		return data;
	}
	
	
	
}