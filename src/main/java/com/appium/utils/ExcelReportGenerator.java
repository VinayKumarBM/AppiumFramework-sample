package com.appium.utils;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.jfree.chart.JFreeChart;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import java.io.OutputStream;
import org.jfree.chart.ChartUtils;
import java.io.ByteArrayOutputStream;
import java.awt.Paint;
import java.awt.Color;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.data.general.DefaultPieDataset;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;

public class ExcelReportGenerator
{
    private static FileOutputStream fos;
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static XSSFRow row;
    private static XSSFCell cell;
    private static XSSFFont font;
    private static XSSFFont headerFont;
    private static XSSFCellStyle style;
    private static XSSFCellStyle headerStyle;
    private static String summarySheetName;
    private static int colCount;
    
    static {
        ExcelReportGenerator.fos = null;
        ExcelReportGenerator.workbook = null;
        ExcelReportGenerator.sheet = null;
        ExcelReportGenerator.row = null;
        ExcelReportGenerator.cell = null;
        ExcelReportGenerator.font = null;
        ExcelReportGenerator.headerFont = null;
        ExcelReportGenerator.style = null;
        ExcelReportGenerator.headerStyle = null;
        ExcelReportGenerator.summarySheetName = "Summary";
        ExcelReportGenerator.colCount = 0;
    }
    
    public static void generateReport(final String xlFileName) throws Exception {
        final String path = String.valueOf(System.getProperty("user.dir")) + "/test-output/";
        final File xmlFile = new File(String.valueOf(path) + "testng-results.xml");
        final DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        final DocumentBuilder build = fact.newDocumentBuilder();
        final Document doc = build.parse(xmlFile);
        doc.getDocumentElement().normalize();
        ExcelReportGenerator.workbook = new XSSFWorkbook();
        ExcelReportGenerator.font = ExcelReportGenerator.workbook.createFont();
        ExcelReportGenerator.headerFont = ExcelReportGenerator.workbook.createFont();
        ExcelReportGenerator.style = ExcelReportGenerator.workbook.createCellStyle();
        ExcelReportGenerator.headerStyle = ExcelReportGenerator.workbook.createCellStyle();
        final NodeList test_results = doc.getElementsByTagName("testng-results");
        final Node test_results_node = test_results.item(0);
        final String total = ((Element)test_results_node).getAttribute("total");
        final String passed = ((Element)test_results_node).getAttribute("passed");
        final String failed = ((Element)test_results_node).getAttribute("failed");
        final String skipped = ((Element)test_results_node).getAttribute("skipped");
        final NodeList test_suite = doc.getElementsByTagName("suite");
        final Node test_suite_node = test_suite.item(0);
        final String test_suite_startTime = ((Element)test_suite_node).getAttribute("started-at");
        final String test_suite_endTime = ((Element)test_suite_node).getAttribute("finished-at");
        final String test_suite_duration = ((Element)test_suite_node).getAttribute("duration-ms");
        addSheet(ExcelReportGenerator.summarySheetName);
        setCellHeaderData(ExcelReportGenerator.summarySheetName, 0, 0, "Category", (short)13);
        setCellHeaderData(ExcelReportGenerator.summarySheetName, 1, 0, "Count", (short)13);
        setCellData(ExcelReportGenerator.summarySheetName, 0, 1, "Passed", (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 1, 1, passed, (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 0, 2, "Failed", (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 1, 2, failed, (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 0, 3, "Skipped", (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 1, 3, skipped, (short)9);
        setCellHeaderData(ExcelReportGenerator.summarySheetName, 0, 4, "Total", (short)49);
        setCellHeaderData(ExcelReportGenerator.summarySheetName, 1, 4, total, (short)49);
        setCellData(ExcelReportGenerator.summarySheetName, 0, 5, "Start Time", (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 1, 5, test_suite_startTime, (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 0, 6, "End Time", (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 1, 6, test_suite_endTime, (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 0, 7, "Duration", (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 1, 7, String.valueOf(test_suite_duration) + " ms", (short)9);
        ExcelReportGenerator.colCount = getColumnCount(ExcelReportGenerator.summarySheetName);
        for (int colPosition = 0; colPosition < ExcelReportGenerator.colCount; ++colPosition) {
            ExcelReportGenerator.sheet.autoSizeColumn((int)(short)colPosition);
        }
        final DefaultPieDataset my_pie_chart_data = new DefaultPieDataset();
        my_pie_chart_data.setValue((Comparable)"Passed", (double)Integer.parseInt(passed));
        my_pie_chart_data.setValue((Comparable)"Failed", (double)Integer.parseInt(failed));
        my_pie_chart_data.setValue((Comparable)"Skipped", (double)Integer.parseInt(skipped));
        final JFreeChart myPieChart = ChartFactory.createPieChart("Execution Status in PIE Chart", (PieDataset)my_pie_chart_data, true, true, false);
        final PiePlot plot = (PiePlot)myPieChart.getPlot();
        plot.setSectionPaint(1, (Paint)new Color(255, 0, 0));
        plot.setSectionPaint(0, (Paint)new Color(0, 128, 0));
        plot.setSectionPaint(2, (Paint)new Color(255, 255, 0));
        final int width = 500;
        final int height = 500;
        final float quality = 1.0f;
        final ByteArrayOutputStream chart_out = new ByteArrayOutputStream();
        ChartUtils.writeChartAsJPEG((OutputStream)chart_out, quality, myPieChart, width, height);
        final int my_picture_id = ExcelReportGenerator.workbook.addPicture(chart_out.toByteArray(), 5);
        chart_out.close();
        final XSSFDrawing drawing = ExcelReportGenerator.sheet.createDrawingPatriarch();
        final ClientAnchor my_anchor = (ClientAnchor)new XSSFClientAnchor();
        my_anchor.setCol1(4);
        my_anchor.setRow1(5);
        final XSSFPicture my_picture = drawing.createPicture(my_anchor, my_picture_id);
        my_picture.resize();
        final NodeList test_list = doc.getElementsByTagName("test");
        for (int i = 0; i < test_list.getLength(); ++i) {
            int r = 0;
            final Node test_node = test_list.item(i);
            final String test_name = ((Element)test_node).getAttribute("name");
            addSheet(test_name);
            setCellHeaderData(test_name, 0, 0, "TestCase Name", (short)13);
            setCellHeaderData(test_name, 1, 0, "Status", (short)13);
            setCellHeaderData(test_name, 2, 0, "Exception", (short)13);
            setCellHeaderData(test_name, 3, 0, "Start Time", (short)13);
            setCellHeaderData(test_name, 4, 0, "End Time", (short)13);
            setCellHeaderData(test_name, 5, 0, "Duration", (short)13);
            final NodeList class_list = ((Element)test_node).getElementsByTagName("class");
            for (int j = 0; j < class_list.getLength(); ++j) {
                final Node class_node = class_list.item(j);
                final String class_name = ((Element)class_node).getAttribute("name");
                final NodeList test_method_list = ((Element)class_node).getElementsByTagName("test-method");
                for (int k = 0; k < test_method_list.getLength(); ++k) {
                    final Node test_method_node = test_method_list.item(k);
                    final String test_method_name = ((Element)test_method_node).getAttribute("name");
                    final String test_method_status = ((Element)test_method_node).getAttribute("status");
                    final String test_method_startTime = ((Element)test_method_node).getAttribute("started-at");
                    final String test_method_endTime = ((Element)test_method_node).getAttribute("finished-at");
                    final String test_method_duration = ((Element)test_method_node).getAttribute("duration-ms");
                    final String test_method_isConfig = ((Element)test_method_node).getAttribute("is-config");
                    if (test_method_isConfig == "") {
                        ++r;
                    }
                    if (test_method_isConfig == "") {
                        setCellData(test_name, 0, r, String.valueOf(class_name) + "." + test_method_name, (short)9);
                    }
                    if (test_method_status.equalsIgnoreCase("pass") && test_method_isConfig == "") {
                        setCellData(test_name, 1, r, test_method_status, (short)11);
                        setCellData(test_name, 2, r, "", (short)9);
                    }
                    if (test_method_status.equalsIgnoreCase("fail") && test_method_isConfig == "") {
                        setCellData(test_name, 1, r, test_method_status, (short)10);
                        final NodeList exp_list = ((Element)test_method_node).getElementsByTagName("exception");
                        for (int a = 0; a < exp_list.getLength(); ++a) {
                            final Node err_node = exp_list.item(a);
                            final NodeList test_err_list = ((Element)err_node).getElementsByTagName("message");
                            for (int b = 0; b < test_err_list.getLength(); ++b) {
                                final Node err_msg_node = test_err_list.item(b);
                                final String err_msg = ((Element)err_msg_node).getTextContent().trim();
                                setCellData(test_name, 2, r, err_msg, (short)9);
                            }
                        }
                    }
                    if (test_method_status.equalsIgnoreCase("skip") && test_method_isConfig == "") {
                        setCellData(test_name, 1, r, test_method_status, (short)13);
                        final NodeList exp_list = ((Element)test_method_node).getElementsByTagName("exception");
                        for (int a = 0; a < exp_list.getLength(); ++a) {
                            final Node err_node = exp_list.item(a);
                            final NodeList test_err_list = ((Element)err_node).getElementsByTagName("message");
                            for (int b = 0; b < test_err_list.getLength(); ++b) {
                                final Node err_msg_node = test_err_list.item(b);
                                final String err_msg = ((Element)err_msg_node).getTextContent().trim();
                                setCellData(test_name, 2, r, err_msg, (short)9);
                            }
                        }
                    }
                    if (test_method_isConfig == "") {
                        setCellData(test_name, 3, r, test_method_startTime, (short)9);
                        setCellData(test_name, 4, r, test_method_endTime, (short)9);
                        setCellData(test_name, 5, r, String.valueOf(test_method_duration) + " ms", (short)9);
                    }
                }
                ExcelReportGenerator.colCount = getColumnCount(test_name);
                for (int colPosition2 = 0; colPosition2 < ExcelReportGenerator.colCount; ++colPosition2) {
                    ExcelReportGenerator.sheet.autoSizeColumn((int)(short)colPosition2);
                }
            }
        }
        ExcelReportGenerator.fos = new FileOutputStream(String.valueOf(System.getProperty("user.dir")) + "/test-output/" + xlFileName);
        ExcelReportGenerator.workbook.write((OutputStream)ExcelReportGenerator.fos);
        ExcelReportGenerator.workbook.close();
        ExcelReportGenerator.fos.close();
        System.out.println("Excel Report Generated");
    }
    
    public static void generateReport(final String folderLocation, final String xlFileName) throws Exception {
        final String path = String.valueOf(System.getProperty("user.dir")) + "/test-output/";
        final File xmlFile = new File(String.valueOf(path) + "testng-results.xml");
        final DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        final DocumentBuilder build = fact.newDocumentBuilder();
        final Document doc = build.parse(xmlFile);
        doc.getDocumentElement().normalize();
        ExcelReportGenerator.workbook = new XSSFWorkbook();
        ExcelReportGenerator.font = ExcelReportGenerator.workbook.createFont();
        ExcelReportGenerator.headerFont = ExcelReportGenerator.workbook.createFont();
        ExcelReportGenerator.style = ExcelReportGenerator.workbook.createCellStyle();
        ExcelReportGenerator.headerStyle = ExcelReportGenerator.workbook.createCellStyle();
        final NodeList test_results = doc.getElementsByTagName("testng-results");
        final Node test_results_node = test_results.item(0);
        final String total = ((Element)test_results_node).getAttribute("total");
        final String passed = ((Element)test_results_node).getAttribute("passed");
        final String failed = ((Element)test_results_node).getAttribute("failed");
        final String skipped = ((Element)test_results_node).getAttribute("skipped");
        final NodeList test_suite = doc.getElementsByTagName("suite");
        final Node test_suite_node = test_suite.item(0);
        final String test_suite_startTime = ((Element)test_suite_node).getAttribute("started-at");
        final String test_suite_endTime = ((Element)test_suite_node).getAttribute("finished-at");
        final String test_suite_duration = ((Element)test_suite_node).getAttribute("duration-ms");
        addSheet(ExcelReportGenerator.summarySheetName);
        setCellHeaderData(ExcelReportGenerator.summarySheetName, 0, 0, "Category", (short)13);
        setCellHeaderData(ExcelReportGenerator.summarySheetName, 1, 0, "Count", (short)13);
        setCellData(ExcelReportGenerator.summarySheetName, 0, 1, "Passed", (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 1, 1, passed, (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 0, 2, "Failed", (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 1, 2, failed, (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 0, 3, "Skipped", (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 1, 3, skipped, (short)9);
        setCellHeaderData(ExcelReportGenerator.summarySheetName, 0, 4, "Total", (short)49);
        setCellHeaderData(ExcelReportGenerator.summarySheetName, 1, 4, total, (short)49);
        setCellData(ExcelReportGenerator.summarySheetName, 0, 5, "Start Time", (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 1, 5, test_suite_startTime, (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 0, 6, "End Time", (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 1, 6, test_suite_endTime, (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 0, 7, "Duration", (short)9);
        setCellData(ExcelReportGenerator.summarySheetName, 1, 7, String.valueOf(test_suite_duration) + " ms", (short)9);
        ExcelReportGenerator.colCount = getColumnCount(ExcelReportGenerator.summarySheetName);
        for (int colPosition = 0; colPosition < ExcelReportGenerator.colCount; ++colPosition) {
            ExcelReportGenerator.sheet.autoSizeColumn((int)(short)colPosition);
        }
        final DefaultPieDataset my_pie_chart_data = new DefaultPieDataset();
        my_pie_chart_data.setValue((Comparable)"Passed", (double)Integer.parseInt(passed));
        my_pie_chart_data.setValue((Comparable)"Failed", (double)Integer.parseInt(failed));
        my_pie_chart_data.setValue((Comparable)"Skipped", (double)Integer.parseInt(skipped));
        final JFreeChart myPieChart = ChartFactory.createPieChart("Execution Status in PIE Chart", (PieDataset)my_pie_chart_data, true, true, false);
        final PiePlot plot = (PiePlot)myPieChart.getPlot();
        plot.setSectionPaint(1, (Paint)new Color(255, 0, 0));
        plot.setSectionPaint(0, (Paint)new Color(0, 128, 0));
        plot.setSectionPaint(2, (Paint)new Color(255, 255, 0));
        final int width = 500;
        final int height = 500;
        final float quality = 5.0f;
        final ByteArrayOutputStream chart_out = new ByteArrayOutputStream();
        ChartUtils.writeChartAsJPEG((OutputStream)chart_out, quality, myPieChart, width, height);
        final int my_picture_id = ExcelReportGenerator.workbook.addPicture(chart_out.toByteArray(), 5);
        chart_out.close();
        final XSSFDrawing drawing = ExcelReportGenerator.sheet.createDrawingPatriarch();
        final ClientAnchor my_anchor = (ClientAnchor)new XSSFClientAnchor();
        my_anchor.setCol1(4);
        my_anchor.setRow1(5);
        final XSSFPicture my_picture = drawing.createPicture(my_anchor, my_picture_id);
        my_picture.resize();
        final NodeList test_list = doc.getElementsByTagName("test");
        for (int i = 0; i < test_list.getLength(); ++i) {
            int r = 0;
            final Node test_node = test_list.item(i);
            final String test_name = ((Element)test_node).getAttribute("name");
            addSheet(test_name);
            setCellHeaderData(test_name, 0, 0, "TestCase Name", (short)13);
            setCellHeaderData(test_name, 1, 0, "Status", (short)13);
            setCellHeaderData(test_name, 2, 0, "Exception", (short)13);
            setCellHeaderData(test_name, 3, 0, "Start Time", (short)13);
            setCellHeaderData(test_name, 4, 0, "End Time", (short)13);
            setCellHeaderData(test_name, 5, 0, "Duration", (short)13);
            final NodeList class_list = ((Element)test_node).getElementsByTagName("class");
            for (int j = 0; j < class_list.getLength(); ++j) {
                final Node class_node = class_list.item(j);
                final String class_name = ((Element)class_node).getAttribute("name");
                final NodeList test_method_list = ((Element)class_node).getElementsByTagName("test-method");
                for (int k = 0; k < test_method_list.getLength(); ++k) {
                    final Node test_method_node = test_method_list.item(k);
                    final String test_method_name = ((Element)test_method_node).getAttribute("name");
                    final String test_method_status = ((Element)test_method_node).getAttribute("status");
                    final String test_method_startTime = ((Element)test_method_node).getAttribute("started-at");
                    final String test_method_endTime = ((Element)test_method_node).getAttribute("finished-at");
                    final String test_method_duration = ((Element)test_method_node).getAttribute("duration-ms");
                    final String test_method_isConfig = ((Element)test_method_node).getAttribute("is-config");
                    if (test_method_isConfig == "") {
                        ++r;
                    }
                    if (test_method_isConfig == "") {
                        setCellData(test_name, 0, r, String.valueOf(class_name) + "." + test_method_name, (short)9);
                    }
                    if (test_method_status.equalsIgnoreCase("pass") && test_method_isConfig == "") {
                        setCellData(test_name, 1, r, test_method_status, (short)11);
                        setCellData(test_name, 2, r, "", (short)9);
                    }
                    if (test_method_status.equalsIgnoreCase("fail") && test_method_isConfig == "") {
                        setCellData(test_name, 1, r, test_method_status, (short)10);
                        final NodeList exp_list = ((Element)test_method_node).getElementsByTagName("exception");
                        for (int a = 0; a < exp_list.getLength(); ++a) {
                            final Node err_node = exp_list.item(a);
                            final NodeList test_err_list = ((Element)err_node).getElementsByTagName("message");
                            for (int b = 0; b < test_err_list.getLength(); ++b) {
                                final Node err_msg_node = test_err_list.item(b);
                                final String err_msg = ((Element)err_msg_node).getTextContent().trim();
                                setCellData(test_name, 2, r, err_msg, (short)9);
                            }
                        }
                    }
                    if (test_method_status.equalsIgnoreCase("skip") && test_method_isConfig == "") {
                        setCellData(test_name, 1, r, test_method_status, (short)13);
                        final NodeList exp_list = ((Element)test_method_node).getElementsByTagName("exception");
                        for (int a = 0; a < exp_list.getLength(); ++a) {
                            final Node err_node = exp_list.item(a);
                            final NodeList test_err_list = ((Element)err_node).getElementsByTagName("message");
                            for (int b = 0; b < test_err_list.getLength(); ++b) {
                                final Node err_msg_node = test_err_list.item(b);
                                final String err_msg = ((Element)err_msg_node).getTextContent().trim();
                                setCellData(test_name, 2, r, err_msg, (short)9);
                            }
                        }
                    }
                    if (test_method_isConfig == "") {
                        setCellData(test_name, 3, r, test_method_startTime, (short)9);
                        setCellData(test_name, 4, r, test_method_endTime, (short)9);
                        setCellData(test_name, 5, r, String.valueOf(test_method_duration) + " ms", (short)9);
                    }
                }
                ExcelReportGenerator.colCount = getColumnCount(test_name);
                for (int colPosition2 = 0; colPosition2 < ExcelReportGenerator.colCount; ++colPosition2) {
                    ExcelReportGenerator.sheet.autoSizeColumn((int)(short)colPosition2);
                }
            }
        }
        ExcelReportGenerator.fos = new FileOutputStream(String.valueOf(folderLocation) + "/" + xlFileName);
        ExcelReportGenerator.workbook.write((OutputStream)ExcelReportGenerator.fos);
        ExcelReportGenerator.workbook.close();
        ExcelReportGenerator.fos.close();
        System.out.println("Excel Report Generated");
    }
    
    private static boolean setCellData(final String sheetName, final int colNumber, final int rowNum, final String value, final short index) {
        try {
            ExcelReportGenerator.sheet = ExcelReportGenerator.workbook.getSheet(sheetName);
            ExcelReportGenerator.row = ExcelReportGenerator.sheet.getRow(rowNum);
            if (ExcelReportGenerator.row == null) {
                ExcelReportGenerator.row = ExcelReportGenerator.sheet.createRow(rowNum);
            }
            ExcelReportGenerator.cell = ExcelReportGenerator.row.getCell(colNumber);
            if (ExcelReportGenerator.cell == null) {
                ExcelReportGenerator.cell = ExcelReportGenerator.row.createCell(colNumber);
            }
            applyCellStyle(index);
            ExcelReportGenerator.cell.setCellValue(value);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    private static boolean setCellHeaderData(final String sheetName, final int colNumber, final int rowNum, final String value, final short index) {
        try {
            ExcelReportGenerator.sheet = ExcelReportGenerator.workbook.getSheet(sheetName);
            ExcelReportGenerator.row = ExcelReportGenerator.sheet.getRow(rowNum);
            if (ExcelReportGenerator.row == null) {
                ExcelReportGenerator.row = ExcelReportGenerator.sheet.createRow(rowNum);
            }
            ExcelReportGenerator.cell = ExcelReportGenerator.row.getCell(colNumber);
            if (ExcelReportGenerator.cell == null) {
                ExcelReportGenerator.cell = ExcelReportGenerator.row.createCell(colNumber);
            }
            applyCellHeaderStyle(index);
            ExcelReportGenerator.cell.setCellValue(value);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    private static String addSheet(final String sheetname) {
        (ExcelReportGenerator.sheet = ExcelReportGenerator.workbook.createSheet(sheetname)).setDisplayGridlines(true);
        ExcelReportGenerator.sheet.setPrintGridlines(true);
        return sheetname;
    }
    
    private static void applyCellStyle(final short index) {
        ExcelReportGenerator.style = ExcelReportGenerator.workbook.createCellStyle();
        ExcelReportGenerator.font.setFontName("Comic Sans MS");
        ExcelReportGenerator.font.setFontHeight(12.0);
        ExcelReportGenerator.style.setFont((Font)ExcelReportGenerator.font);
        ExcelReportGenerator.style.setFillForegroundColor(index);
        ExcelReportGenerator.style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        ExcelReportGenerator.style.setBorderBottom(BorderStyle.MEDIUM);
        ExcelReportGenerator.style.setBorderTop(BorderStyle.MEDIUM);
        ExcelReportGenerator.style.setBorderRight(BorderStyle.MEDIUM);
        ExcelReportGenerator.style.setBorderLeft(BorderStyle.MEDIUM);
        ExcelReportGenerator.cell.setCellStyle((CellStyle)ExcelReportGenerator.style);
    }
    
    private static void applyCellHeaderStyle(final short index) {
        ExcelReportGenerator.headerStyle = ExcelReportGenerator.workbook.createCellStyle();
        ExcelReportGenerator.headerFont.setFontName("Comic Sans MS");
        ExcelReportGenerator.headerFont.setFontHeight(14.0);
        ExcelReportGenerator.headerFont.setBold(true);
        ExcelReportGenerator.headerStyle.setFont((Font)ExcelReportGenerator.headerFont);
        ExcelReportGenerator.headerStyle.setFillForegroundColor(index);
        ExcelReportGenerator.headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        ExcelReportGenerator.headerStyle.setBorderBottom(BorderStyle.THICK);
        ExcelReportGenerator.headerStyle.setBorderTop(BorderStyle.THICK);
        ExcelReportGenerator.headerStyle.setBorderRight(BorderStyle.THICK);
        ExcelReportGenerator.headerStyle.setBorderLeft(BorderStyle.THICK);
        ExcelReportGenerator.cell.setCellStyle((CellStyle)ExcelReportGenerator.headerStyle);
    }
    
    private static int getColumnCount(final String sheetName) {
        ExcelReportGenerator.sheet = ExcelReportGenerator.workbook.getSheet(sheetName);
        ExcelReportGenerator.row = ExcelReportGenerator.sheet.getRow(0);
        final int colCount = ExcelReportGenerator.row.getLastCellNum();
        return colCount;
    }
}