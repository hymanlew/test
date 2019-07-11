package hyman.demo;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


public class POIExcelUtil {
	/**
	 * 将数据组装成Excel文件的IO流
	 */
	public static void writerDataInExcelIo(List<List<String>> strsList, OutputStream os){
		HSSFWorkbook wb= new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");
		
		for(int i=0;i<strsList.size();i++){
			sheet.setColumnWidth(i, 30 * 256 ); 
			List<String> list = strsList.get(i);//得到一行数据
			HSSFRow row = sheet.createRow((short)i); 
			
			for(int j=0;j<list.size();j++){
				HSSFCell cell = row.createCell((short)j);
				
				cell.setCellValue(list.get(j));
			}
		}
		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * 导入
	 * @param
	 * @return
	 */
	

	public static String getCellValue(Cell cell){
		String value = null;
		if(cell!=null){
			switch(cell.getCellType())
			{
			case Cell.CELL_TYPE_STRING:
				value = cell.getRichStringCellValue().getString();  
				break;  
			case Cell.CELL_TYPE_NUMERIC:
				long dd = (long)cell.getNumericCellValue();  
				value = dd+"";  
				break;  
			case Cell.CELL_TYPE_BLANK:  
				value = "";  
				break;     
			case Cell.CELL_TYPE_FORMULA:  
				value = String.valueOf(cell.getCellFormula());  
				break;  
			case Cell.CELL_TYPE_BOOLEAN:
				value = String.valueOf(cell.getBooleanCellValue());  
				break;  
			case Cell.CELL_TYPE_ERROR:  
				value = String.valueOf(cell.getErrorCellValue());  
				break;  
			default:  
				break;  
			} 
		}
		return value;  
	}

//	public static List<Object>importEmployee(String url,CompanyService companyService,AreaService areaService,SiteService siteService){
//		List<Object> list = new ArrayList<Object>();
//		List<Employee>employees=new ArrayList<>();
//		Employee employee=null;
//		Workbook hwb = null;
//		InputStream stream = null;
//		try {
//			if (url.endsWith(".xls")){
//				stream = new FileInputStream(url);
//				hwb = (Workbook) new HSSFWorkbook(stream);
//			}
//			else if (url.endsWith(".xlsx")){
//				hwb = (Workbook) new XSSFWorkbook(url);
//			}
//			Sheet sheet = hwb.getSheetAt(0);
//			Row row = null;
//			for(int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
//				row = sheet.getRow(j);
//				employee = new Employee();
//				employee.setTime(new Date());
//				employee.setPhoto("images/tx.jpg");
//				employee.setBlame(0);
//				employee.setName( getCellValue(row.getCell(2)) );//姓名
//				employee.setPhone( getCellValue(row.getCell(8)) );//电话
//				employee.setHealth(0);
//				employee.setAreaId(areaService.getByName(getCellValue(row.getCell(5))).getId());//地区
//				String shenfen=getCellValue(row.getCell(4));//身份证
//				employee.setIdentity(shenfen);
//				employee.setBirthday(DateUtil.StrToDateWithPattern(shenfen.substring(6,14), "yyyyMMdd"));
//				employee.setUrgency(getCellValue(row.getCell(10)));//紧急联系人
//				employee.setUrgencyPhone(getCellValue(row.getCell(11)));//紧急联系人电话
//				/*String sitename=getCellValue(row.getCell(1));//工地
//				employee.setSiteId(siteService.getByName(sitename).getId());*/
//				employee.setSiteId(2);
//				String cname=getCellValue(row.getCell(7));//劳务公司
//				employee.setCompanyId(companyService.getByName(cname).getId());
//				String type=getCellValue(row.getCell(6));//工种
//				if(type.equals("钢筋工")){
//					employee.setType(0);
//				}else if(type.equals("混凝土工")){
//					employee.setType(1);
//				}else if(type.equals("砌筑工")){
//				employee.setType(2);
//				}else if(type.equals("木工")){
//					employee.setType(3);
//				}else if(type.equals("电工")){
//					employee.setType(4);
//				}
//				String sex=getCellValue(row.getCell(3));//性别
//				if(sex.equals("男")){
//					employee.setSex(1);
//				}else {
//					employee.setSex(0);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
	
	public static void main(String[] args) {
		String code="320502199208220252";
		System.out.println(DateUtil.StrToDateWithPattern(code.substring(6,14), "yyyyMMdd"));
	}


	//public static List<SalaryRecordResponce> importSalaryByPoi(String url){
	//	List<SalaryRecordResponce> list=new ArrayList<>();
	//	SalaryRecordResponce salaryRecordResponce=null;
	//	Workbook hwb = null;
	//	InputStream stream = null;
	//	try {
	//		if (url.endsWith(".xls")){
	//			stream = new FileInputStream(url);
	//			hwb = (Workbook) new HSSFWorkbook(stream);
	//		}
	//		else if (url.endsWith(".xlsx")){
	//			hwb = (Workbook) new XSSFWorkbook(url);
	//		}
	//		Sheet sheet = hwb.getSheetAt(0);
	//		Row row = null;
	//		for(int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
	//			row = sheet.getRow(j);
	//			salaryRecordResponce = new SalaryRecordResponce();
	//			//员工姓名、身份证号码 、卡号、金额
	//			String name = getCellValue(row.getCell(0));
	//			String identity = getCellValue(row.getCell(1));
	//			String bankCard = getCellValue(row.getCell(1));
	//			String money = getCellValue(row.getCell(2));
	//			salaryRecordResponce.setName(name);
	//			salaryRecordResponce.setIdentity(identity);
	//			salaryRecordResponce.setBankCard(bankCard);
	//			salaryRecordResponce.setMoney(money);
	//			if(TextUtil.notEmpty(name)){
	//				list.add(salaryRecordResponce);
	//			}
	//		}
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//	} finally {
	//		return list;
	//	}
	//}
}
