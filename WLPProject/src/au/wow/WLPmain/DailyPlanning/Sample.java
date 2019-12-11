/*package au.wow.WLPmain.DailyPlanning;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sample {
	//public static final String filepath = "C:\\Book1.xls";

	public static void main(String[] args) throws IOException, InvalidFormatException {

		String path="C:\\Book1.xlsx";
		try {

		        File f = new File( path );
		        Workbook wb = WorkbookFactory.create(f);
		        Sheet mySheet = wb.getSheetAt(0);
		        List<List<Object>> Excelvalue= new  ArrayList<List<Object>>();
		        //List<Object> col = new ArrayList<>();
		        Iterator<Row> rowIter = mySheet.rowIterator();
		        for ( Iterator<Row> rowIterator = mySheet.rowIterator() ;rowIterator.hasNext(); )
		        {
		        	List<Object> col = new ArrayList<>();
		            for (  Iterator<Cell> cellIterator = ((Row)rowIterator.next()).cellIterator() ; cellIterator.hasNext() ;  ) 
		            {   
		            	
		            	col.add(( (Cell)cellIterator.next() ).toString());
		            	
		            }
		            Excelvalue.add(col);
		            System.out.println ( Excelvalue + "\n");
		           // System.out.println( " **************************************************************** ");
		        }
		    } catch ( Exception e )
		    {
		        System.out.println( "exception" );
		        e.printStackTrace();
		    }
	}
}*/
/*package au.wow.WLPmain.DailyPlanning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sample {

    public static void main(String[] args) {

        String csvFile = "C:\\Book1.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                
                System.out.println(" [ " + country[0] + " ," + country[1] + "," + country[2] + "," + country[3] + "," + country[4] + "," + country[5] + "," + country[6] + "," + country[7] + "," + country[8] + "]");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}*/
/*package au.wow.WLPmain.DailyPlanning;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sample {

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    public static void main(String[] args) throws Exception {
String c;
        String csvFile = "C:\\Book1.csv";

        Scanner scanner = new Scanner(new File(csvFile));
        while (scanner.hasNext()) {
        	List<String> line = parseLine(scanner.nextLine());
        	String a= line.get(5);
        	String b =line.get(6);
          // int c=a/b;
            c=  a + b ;
           line.add(c);
            System.out.println(line);
        }
        scanner.close();

    }

    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }

}*/



package au.wow.WLPmain.DailyPlanning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sample {

   
    	public static void main(String[] args) throws FileNotFoundException {
    		 List<List<Object>> Excelvalue= new  ArrayList<List<Object>>();
    	    try (Scanner scanner = new Scanner(new File("C:\\Book1.csv"))) {
    	         //scanner.useDelimiter(",");
    	         while(scanner.hasNext()){
    	        	 List<Object> col = new ArrayList<>();
    	        	 col.add(scanner.next());
    	        	 Excelvalue.add(col);
    	        	     
    	         }
    	         System.out.print(Excelvalue+" \n "); 
    	    }
    	    }
    }


