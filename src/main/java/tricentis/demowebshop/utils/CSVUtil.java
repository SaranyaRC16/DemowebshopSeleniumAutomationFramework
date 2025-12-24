package tricentis.demowebshop.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVUtil {
	
	private static String CSVFilePath = ".\\src\\test\\resources\\testdata\\";
	
	public static Object[][] readDataFromCSV(String fileName) {
		List<String[]> rows = null;
		String csvFile = CSVFilePath+fileName+".csv";
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(csvFile));
			rows = reader.readAll();
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object[][] data = new Object[rows.size()][rows.get(0).length];
		for(int i=0;i<rows.size();i++) {
			for(int j=0;j<rows.get(0).length;j++) {
				data[i][j] = rows.get(i)[j];
			}
			
		}
		
		return data;
	}

}
