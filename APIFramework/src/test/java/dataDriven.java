import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		FileInputStream fis = new FileInputStream("D://eclipse-workspace//demoData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets  = workbook.getNumberOfSheets();
		for (int i =0; i< sheets; i++) {
			//System.out.println("Test "+sheets);
			if ( workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				//Identify the test cases column by scanning the first now

				Iterator<Row> rows = sheet.iterator();//sheet is collections of rows
				Row firstRow = rows.next();
				Iterator <Cell> ce = firstRow.cellIterator(); //rows is collection of cells
				int k =0;
				int column;
				while(ce.hasNext()) {
					Cell value = ce.next();
					if(value.getStringCellValue().equalsIgnoreCase("Testcases")) {
						column  =  k;	
						System.out.println("pass");


					}
					k++;
				}
			//	while (rows.hasNext()){
				//	Rows r = rows.next();
			//	}


			}
		}
	}

}
