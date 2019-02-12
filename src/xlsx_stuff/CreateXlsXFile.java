package xlsx_stuff;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tools.AllInfo;
import tools.CurrentDateTime;

public class CreateXlsXFile {

    private AllInfo ai;
    private String xlsxFileName;
    private String sheetName;
    private XSSFWorkbook wb;
    private CurrentDateTime cdt;
    private FileOutputStream fos;
    private XSSFSheet sheet;
    private XSSFSheet binarSheet;
    private Scanner sc;
    private HashSet<String> uniqueInfoSet;
    private ArrayList<String> uniqueInfoList;

    public void collectInExcel(String path) throws IOException {
        cdt = new CurrentDateTime();

        xlsxFileName = path + "Virulence genes_" + cdt.getDateTime() + ".xlsx";// name
        // of
        // excel
        // file

        System.out.println("/////////////////////////Write Sheet Name\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");

        sc = new Scanner(System.in);
        sheetName = sc.nextLine();// name of sheet

        wb = new XSSFWorkbook();

        sheet = wb.createSheet(sheetName);

        ai = new AllInfo();

        ai.collectAllInfo(path);

        // iterating r number of rows

        LinkedList<LinkedList<String>> values = new LinkedList<>(ai.getAllInfo().values());
        LinkedList<LinkedList<String>> valuesNoDoubleList = new LinkedList<>();

        for (int i = 0; i < values.size(); i++) {
            HashSet<String> valuesNoDublSet = new HashSet<>();
            LinkedList<String> temp = new LinkedList<>();
            for (String a : values.get(i)) {

                valuesNoDublSet.add(a);

            }
            for (String a : valuesNoDublSet) {

                temp.add(a);

            }

            valuesNoDoubleList.add(temp);

        }

        ArrayList<String> keys = new ArrayList<>(ai.getAllInfo().keySet());

        uniqueInfoSet = new HashSet<>();
        XSSFCellStyle keysStyle = wb.createCellStyle();
        keysStyle.setFillForegroundColor(IndexedColors.CORAL.getIndex());
        keysStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        for (int r = 0; r < ai.getAllInfo().size(); r++) {
            XSSFRow row = sheet.createRow(r);

            // iterating c number of columns

            XSSFCell cell = row.createCell(0);

            try {
                cell.setCellValue(Integer.parseInt(keys.get(r)));
                row.getCell(0).setCellStyle(keysStyle);
            } catch (NumberFormatException e) {
                // TODO: handle exception
                cell.setCellValue(keys.get(r));
                row.getCell(0).setCellStyle(keysStyle);
            }
            for (int i = 0; i < values.get(r).size(); i++) {

                /*
                 * Without cellNumber first item of the values will orverride
                 * the key, which is written in the cell 1 cellNumber corrects
                 * and shifts to second cell.
                 */
                // int cellNumber = i + 1;
                cell = row.createCell(i + 1);
                cell.setCellValue(values.get(r).get(i));

                // cellNumber++;

                uniqueInfoSet.add(values.get(r).get(i));
            }

        }

        /*********************************
         * PART OF MAKING INFO BINAR
         *********************************/

        uniqueInfoList = new ArrayList<>();

        for (String a : uniqueInfoSet) {

            uniqueInfoList.add(a);

        }
        /* Sort all values in the arrayListin Alphabetical order */
        uniqueInfoList.sort(String::compareToIgnoreCase);

        binarSheet = wb.createSheet("Binar Values");
        XSSFRow binRow = binarSheet.createRow(0);
        XSSFCellStyle headerStyle = wb.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        for (int i = 0; i < uniqueInfoSet.size(); i++) {
            XSSFCell binCell = binRow.createCell(i + 1);
            binRow.getCell(i + 1).setCellStyle(headerStyle);
            binCell.setCellValue(uniqueInfoList.get(i));
        }

        /*************************
         * Start Check and write Binar *
         *************************/
        for (int indexOfFile = 0; indexOfFile < valuesNoDoubleList.size(); indexOfFile++) {
            valuesNoDoubleList.get(indexOfFile).sort(String::compareToIgnoreCase);
            binRow = binarSheet.createRow(indexOfFile + 1);
            XSSFCell binCell = binRow.createCell(0);
            try {
                binCell.setCellValue(Integer.parseInt(keys.get(indexOfFile)));
            } catch (NumberFormatException e) {

                binCell.setCellValue(keys.get(indexOfFile));

            }
            int i = 0;
            for (String a : uniqueInfoList) {

                if (i < valuesNoDoubleList.get(indexOfFile).size()) {
                    if (a.equals(valuesNoDoubleList.get(indexOfFile).get(i))) {
                        XSSFCell cell1 = binRow.createCell(uniqueInfoList.indexOf(a) + 1);
                        cell1.setCellValue(1);
                        i++;

                    } else {

                        XSSFCell cell1 = binRow.createCell(uniqueInfoList.indexOf(a) + 1);
                        cell1.setCellValue(0);

                    }
                } else {

                    XSSFCell cell1 = binRow.createCell(uniqueInfoList.indexOf(a) + 1);
                    cell1.setCellValue(0);

                }

            }
        }

        /*************************
         * End Check and write Binar *
         *************************/

        /*********************************
         * Start Write into excel file
         *********************************/

        fos = new FileOutputStream(new File(xlsxFileName));

        // write this workbook to an Outputstream.

        wb.write(fos);
        fos.flush();
        fos.close();

    }

    /*********************************
     * End Write into excel file
     *********************************/

    public XSSFWorkbook getWb() {
        return wb;
    }

}
