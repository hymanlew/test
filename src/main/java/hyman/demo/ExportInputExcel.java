package hyman.demo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExportInputExcel {

    public void exportExcel(HttpServletResponse response, int num){

        List<String> title = new ArrayList<String>();
        title.add("姓名");
        title.add("年龄");
        title.add("地址");

        List<List<String>> strLists = new ArrayList<List<String>>();
        strLists.add(title);

        // 生成 6 行数据
        List<String> content = null;
        for(int i=0;i<10;i++){
            content = new ArrayList<String>();
            content.add("小李");
            content.add("25");
            content.add("123456");
            strLists.add(content);
        }
        List<String> content1 = null;
        for(int i=0;i<10;i++){
            content1 = new ArrayList<String>();
            content1.add("小王");
            content1.add("27");
            content1.add("000456");
            strLists.add(content1);
        }


        String filename = "Export测试文件";
        // 去除 EXCEL内的空白行
        response.reset();
        // 设置响应数据类型
        response.setContentType("application/msexcel;charset=utf-8");
        try {
            /**
             * 设置响应头：
             *
             * Content-disposition是 MIME 协议的扩展，MIME 协议指示 MIME 用户代理如何显示附加的文件。当 Internet Explorer 接收到头时，
             * 它会激活文件下载对话框，它的文件名框自动填充了头中指定的文件名。
             * （请注意，这是设计导致的；无法使用此功能将文档保存到用户的计算机上，而不向用户询问保存位置。）
             *
             * 声明为附件类型，filename 为字节数据，编码为服务器端编码
             */
            response.setHeader("Content-Disposition","attachment;filename="
                    + new String((filename+".xls").getBytes(),"iso-8859-1"));

            /**
             * PrintWriter与ServletOutputStream的区别：

             1,getOutputStream方法用于返回Servlet引擎创建的————字节输出流对象，Servlet程序可以按字节形式输出响应正文。

             2,getWriter方法用于返回Servlet引擎创建的————字符输出流对象，Servlet程序可以按字符形式输出响应正文。

             3,getOutputStream和getWriter这两个方法互相排斥，调用了其中的任何一个方法后，就不能再调用另一方法。

             4,getOutputStream方法返回的字节输出流对象的类型为ServletOutputStream，它可以直接输出字节数组中的二进制数据。

             5,getWriter方法将Servlet引擎的数据缓冲区包装成PrintWriter类型的字符输出流对象后返回，PrintWriter对象可以直接输出字符文本内容。

             6,Servlet程序向ServletOutputStream或PrintWriter对象中写入的数据将被Servlet引擎获取，Servlet引擎将这些数据当作响应消息的正文，然后再与响应状态行和各响应头组合后输出到客户端。

             7,Serlvet的service方法结束后，Servlet引擎将检查getWriter或getOutputStream方法返回的输出流对象是否已经调用过close方法，如果没有，Servlet引擎将调用close方法关闭该输出流对象。

             */
            ServletOutputStream out = response.getOutputStream();
            OutputStream os = out;
            if(num==0){
                poiDemo(strLists,os);
            }else {
                Workbook workbook = new XSSFWorkbook();
                List<String> titles = strLists.get(0);
                List<List<String>> d1 = strLists.subList(1,10);
                List<List<String>> d2 = strLists.subList(10,20);
                manySheets(workbook,0,"t1",titles,d1);
                manySheets(workbook,1,"t2",titles,d2);
                workbook.write(os);
                os.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void poiDemo(List<List<String>> data,OutputStream outputStream){

        int pages = 1;
        try {
            // 工作簿的高级类。它是阅读或编写工作簿时所构建的对象。它也是创建新表/等的顶级对象。
            XSSFWorkbook book = new XSSFWorkbook();

            // POI 的 jar包，尽量用低版本的，因为高版本 poi导出的文档也是低版本的格式
            //HSSFWorkbook book = new HSSFWorkbook();

            // 设置表头字体
            Font headfont = book.createFont();
            headfont.setFontName("宋体");
            headfont.setFontHeightInPoints((short) 12);// 字体大小
            headfont.setBold(true);// 加粗


            //CellStyle headStyle = book.createCellStyle(); // 表头第一列的样式
            //headStyle.setFillForegroundColor((short)1); // 前景色设置
            //headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 填充模式 设置
            //headStyle.setFont(headfont);
            //headStyle.setAlignment(HorizontalAlignment.CENTER);// 左右居中
            //headStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
            //headStyle.setWrapText(true);
            //headStyle.setBorderRight(BorderStyle.THIN);
            //headStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
            //headStyle.setBorderTop(BorderStyle.THIN);
            //headStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

            // 表格正文样式，背景色,边框
            //CellStyle style = book.createCellStyle();
            //style.setFillForegroundColor((short)2); // 前景色设置
            //style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 填充模式 设置
            //style.setBorderBottom(BorderStyle.THICK); // 设置单元格的边框为粗体
            //style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 设置单元格的边框颜色
            //style.setBorderRight(BorderStyle.THIN);
            //style.setRightBorderColor(IndexedColors.BLACK.getIndex());
            //style.setBorderTop(BorderStyle.THIN);
            //style.setTopBorderColor(IndexedColors.BLACK.getIndex());


            /**
             * HSSF是POI工程对Excel 97(-2007)文件操作的纯Java实现
             * XSSF是POI工程对Excel 2007 OOXML (.xlsx)文件操作的纯Java实现
             *
             * HSSF提供读写Microsoft Excel XLS格式档案的功能。
             * XSSF提供读写Microsoft Excel OOXML XLSX格式档案的功能。
             * HWPF提供读写Microsoft Word DOC格式档案的功能。
             * HSLF提供读写Microsoft PowerPoint格式档案的功能。
             * HDGF提供读Microsoft Visio格式档案的功能。
             * HPBF提供读Microsoft Publisher格式档案的功能。
             * HSMF提供读Microsoft Outlook格式档案的功能。
             *
             * 从POI 3.8版本开始，提供了一种基于XSSF的低内存占用的API----SXSSF
             *
             * SXSSF通过一个滑动窗口来限制访问Row的数量从而达到低内存占用的目录，XSSF可以访问所有行。旧的行数据不再出现在滑动窗口中并变得
             * 无法访问，与此同时写到磁盘上。
             * 在自动刷新的模式下，可以指定窗口中访问Row的数量，从而在内存中保持一定数量的Row。当达到这一数量时，在窗口中产生新的Row数据，
             * 并将低索引的数据从窗口中移动到磁盘中。或者，滑动窗口的行数可以设定成自动增长的。它可以根据需要周期的根据一次明确的flushRow
             * (int keepRows)调用来进行修改。
             *
             * 注意：针对 SXSSF Beta 3.8下，会有临时文件产生，比如： poi-sxssf-sheet4654655121378979321.xml
             * 文件位置：java.io.tmpdir这个环境变量下的位置：
             *              Windows 7下是 C:\Users\xxxxxAppData\Local\Temp
             *              Linux下是 /var/tmp/
             * 要根据实际情况，看是否删除这些临时文件
             *
             * 官方也提供了一些解决方式：https://issues.apache.org/bugzilla/show_bug.cgi?id=53493
             *
             * 与XSSF的对比：在一个时间点上，只可以访问一定数量的数据，不再支持Sheet.clone()，不再支持公式的求值
             */

            XSSFRow row = null;
            XSSFCell cell = null;

            XSSFSheet sheet = book.createSheet("sheet1");
            XSSFCellStyle cellStyle = book.createCellStyle();

            //HSSFSheet sheet = book.createSheet();
            //HSSFRow row = null;
            //HSSFCell cell = null;
            //HSSFCellStyle cellStyle = book.createCellStyle();

            for(int i=0;i<data.size();i++){

                // 获得一行数据
                // 行的高级类，参数为第几行，使用 short类型是因为：
                // 由于生成Excel文件中记录数行数超过6万多时会报错，所以要对数据量多的情况做了保护，超过5万条，再生成新的sheet
                row = sheet.createRow((short)i);

                // 设置列宽，单位毫米
                sheet.setColumnWidth(i,30*256);

                List<String> list1 = data.get(i);
                for(int j=0;j<list1.size();j++){

                    // 列的高级类，参数为第几列
                    cell = row.createCell((short)j);
                    cell.setCellValue(list1.get(j));

                    if(i==0){
                        setCellColorAndStyle(book, row, j, "宋体", 16, cell.getStringCellValue(),
                                IndexedColors.BLACK.getIndex(), true, false, false, false, 0,
                                1, 1);
                    }
                }
                if(i>1000){
                    //data.subList()
                    //当超过 5万行时，系统会报错，所以要采用分阶段（subList）进行生成表格，即生成多张表
                }
            }
            book.write(outputStream);
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 生成多张 sheet表
    public void manySheets(Workbook wb,int sheetNum,String sheetName,List<String> title,List<List<String>> data){

        try {
            Sheet sheet = wb.createSheet();

            // 设置工作表名字
            wb.setSheetName(sheetNum,sheetName);

            // 设置表格默认列宽度为20个字节
            sheet.setDefaultColumnWidth((short)20);
            // 设置单元格样式，字体，等等
            //CellStyle cellStyle = wb.createCellStyle();

            // 产生表格标题行
            Row row = sheet.createRow(0);
            for(int i=0;i<title.size();i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(title.get(i));
            }

            // 遍历集合数据，产生数据行
            if (data != null) {
                int index = 1;
                for (List<String> m : data) {
                    row = sheet.createRow(index);
                    int cellIndex = 0;
                    for (String str : m) {
                        Cell cell = row.createCell((short) cellIndex);
                        cell.setCellValue(str.toString());
                        cellIndex++;
                    }
                    index++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 设置表格样式
    public void setCellColorAndStyle(Workbook wb, Row row, int cellIndex,
                                     String fontName, int fontSize, String cellContent, int colorIndex,
                                     boolean isBold, boolean isItalic, boolean isStrike,
                                     boolean isUnderline, int underLineStyle, int hAlign, int vAlign) {
        // 字体
        Font font = wb.createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints((short) fontSize);
        if (colorIndex >= 0) {
            font.setColor((short) colorIndex);
        }
        if (isBold) {
            font.setBold(true);
        }
        if (isItalic) {
            font.setItalic(true);
        }
        if (isStrike) {
            font.setStrikeout(true);
        }
        if (isUnderline) {
            font.setUnderline((byte) underLineStyle);
        }
        CellStyle cellStyle = wb.createCellStyle();
        // 是否换行
        cellStyle.setWrapText(false);
        cellStyle.setFont(font);

        // 对齐方式
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);

        Cell cell = row.createCell(cellIndex);
        cell.setCellValue(cellContent);
        cell.setCellStyle(cellStyle);
    }

    // filepath，绝对路径
    public List<List<String>> importExcel(String filepath) throws Exception {

        Workbook wb = null;
        FileInputStream inputStream = null;

        //if(filepath.endsWith(".xls")){
        //    inputStream = new FileInputStream(filepath);
        //    wb = new HSSFWorkbook(inputStream);
        //}else if(filepath.endsWith(".xlsx")){
        //    wb = new XSSFWorkbook();
        //}
        wb = new XSSFWorkbook(filepath);

        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        // 存放所有的数据
        List<List<String>> data = new ArrayList<List<String>>();
        // 存放一行数据
        List<String> content = new ArrayList<String>();

        // 拿到所有的表
        int num = wb.getNumberOfSheets();
        for(int i=0;i<num;i++){
            sheet = wb.getSheetAt(i);

            for(int k=0;k<sheet.getLastRowNum();k++){
                row = sheet.getRow(k);

                for(int c=0;c<row.getLastCellNum();c++){
                    cell = row.getCell(c);
                    String value = cell.getStringCellValue();
                    content.add(value);
                }
                data.add(content);
            }
        }
        return data;
    }
}
