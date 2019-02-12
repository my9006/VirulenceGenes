package upgma;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FormatUPGMA {

    private XSSFWorkbook wb;
    private ArrayList<String> values;
    private FileInputStream fis;
    private FileOutputStream fos;
    private Scanner sc;
    private BufferedWriter bw;

    public void formatUPGMA(String path) {
        System.out.println("???????????????????????????Please write the XLSX file name???????????????????????????");
        sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        try {
            fis = new FileInputStream(new File(path + "/" + fileName + ".xlsx"));
            wb = new XSSFWorkbook(fis);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        XSSFSheet sheet = wb.getSheet("Binar Values");
        try {
            bw = new BufferedWriter(new FileWriter(new File(path + "/UPGMA.txt")));

            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i+1);
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    String cValue = row.getCell(j).toString();
                    if(cValue.equals("1.0")||cValue.equals("0.0")){
                        cValue = cValue.substring(0, row.getCell(j).toString().indexOf("."));
                    }
                    bw.write(cValue + "; ");

                }
                bw.write(System.lineSeparator());
            }

            bw.flush();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
