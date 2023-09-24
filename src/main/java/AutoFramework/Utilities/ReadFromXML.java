package AutoFramework.Utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.*;

public class ReadFromXML {
    public static List<String> readFromXmlFile(String path, String email) throws IOException {
        List<String> result = new ArrayList<>();
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);

        //create a sheet object using the sheet name
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheetAt(0); //take the first sheet

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getPhysicalNumberOfCells();

        Iterator<Row> iterator = sheet.iterator();
        while(iterator.hasNext()){
            Row row = iterator.next();
            if(row.getCell(0).getStringCellValue().equals(email)){
                for(int i = 1; i < cols; i++){
                    try {
                        result.add(row.getCell(i).getStringCellValue());
                    }catch(RuntimeException e){
                        int num = (int)row.getCell(i).getNumericCellValue();
                        System.out.println(num);
                        result.add(String.valueOf(num));
                    }
                }
            }
        }
        if(result.size()==0){
            throw new InvalidObjectException("Personal details are null");
        }
        return result;
    }

    /*
    Need to solve the issue - returns only one array; how to concatenate arrays from String and Double?
    https://tutorial.techaltum.com/read-excel-data-with-Apache-POI.html
     */
    public static Collection readXml(String path) throws IOException {
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheetAt(0); //take the first sheet

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getPhysicalNumberOfCells();

        String[][] totalObj = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                try {
                    totalObj[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
                }catch(RuntimeException e){
                    int num = (int)sheet.getRow(i).getCell(j).getNumericCellValue();
                    totalObj[i][j] = Integer.toString(num);
                }
            }
        }
        return Arrays.asList(totalObj.clone());
    }
}
