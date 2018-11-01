package hyman.utils;


import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Controller
@RequestMapping("employee")
public class FileUpload {


	// 定义一个公有内存，用于存放图片地址列表
	public static List<String> imagelist = new ArrayList<>();

	 
    @RequestMapping("photoUpload_allow")
    @ResponseBody
    public Map<String,Object> springUpload(HttpServletRequest request) throws IllegalStateException, IOException
    {
    	Map<String, Object> resultMap = new HashMap<>();
         long  startTime=System.currentTimeMillis();

         ////创建一个通用的解析器，将当前上下文初始化给  CommonsMutipartResolver （多部分解析器用于多文件上传，并限制大小）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());

        //检查form中是否有enctype="multipart/form-data"
        String uploadPath = "uploadFile/"+startTime;  //定义的文件存放相对路径
        String path =request.getSession().getServletContext().getRealPath("/").replace("webapps\\building\\","");  //文件存放绝对路径
        String imgPath = "";

		//判断request是否有文件上传
        if(multipartResolver.isMultipart(request)) {

            //将request变成多部分request
            //MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
			MultipartHttpServletRequest multiRequest = multipartResolver.resolveMultipart(request);

           //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();

            while(iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if(file!=null) {

                	String fileName = file.getOriginalFilename();
                	if(!"".equals(fileName.trim())){
						imgPath = uploadPath +fileName.substring(fileName.lastIndexOf("."));
					}

					//将上传文件写到服务器上指定的文件
                    file.transferTo(new File(path+imgPath));
                }    
            }
        }
        
        if(!StringUtils.isEmpty(imgPath)){
        	resultMap.put("result", "success");
        	resultMap.put("image",imgPath);
        }else {
        	resultMap.put("result", "error");
		}
        return resultMap;  
    }

	@RequestMapping("uploadPic_allow")
	@ResponseBody
	public Map<String,Object> uploadPic(String base64,HttpServletRequest request){
		if(base64.indexOf(",")!= -1){
			base64 = base64.substring(base64.lastIndexOf(",")+1,base64.length());
		}
		Map<String, Object> obj = new HashMap<>();
		//对字节数组字符串进行Base64解码并生成图片
		BASE64Decoder decoder = new BASE64Decoder();
		try
		{
			//Base64解码
			byte[] b = decoder.decodeBuffer(base64);
			for(int i=0;i<b.length;++i)
			{
				if(b[i]<0)
				{//调整异常数据
					b[i]+=256;
				}
			}
			//生成jpeg图片
			String uploadPath = "uploadFile/";  //定义的文件存放相对路径
			String path =request.getSession().getServletContext().getRealPath("/").replace("webapps\\building\\","")+uploadPath;  //文件存放绝对路径
			File folder=new File(path);
			if(!folder.exists()){
				folder.mkdirs();
			}
			String temp = System.currentTimeMillis()+".png";
			path += temp;
			obj.put("result", "success");
			obj.put("image",uploadPath+temp);

			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return obj;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			obj.put("result", "failure");
			return obj;
		}
	}

	@RequestMapping("uploadManyPic_allow")
	@ResponseBody
	public Map<String,Object> uploadManyPic(@RequestParam(value="manyImages",required = false) MultipartFile[] files, HttpServletRequest request){

		Map<String, Object> obj = new HashMap<>();
		if(files!=null && files.length>0){

            imagelist.clear();
		    synchronized (imagelist){
                try {
                    String uploadPath = "uploadFile"+File.separator+"siteApply";  //定义的文件存放相对路径
                    String path =request.getSession().getServletContext().getRealPath("/").replace("webapps\\building\\","")+uploadPath;  //文件存放绝对路径
                    File folder=new File(path);
                    if(!folder.exists()){
                        folder.mkdirs();
                    }

                    for(int i=0;i<files.length;i++){
                        String name = files[i].getOriginalFilename();
                        String temp = System.currentTimeMillis()+"-"+name;
                        imagelist.add(uploadPath+File.separator+temp);

                        File file = new File(path+File.separator+temp);
                        files[i].transferTo(file);
                    }
                    //ImageMap.clear();
                    //ImageMap.put(siteId,imagelist);
                    obj.put("list",imagelist);
                }catch (Exception e){
                    e.printStackTrace();
                    obj.put("list", null);
                }finally {
                    return obj;
                }
            }
		}
		obj.put("list", null);
		return obj;
	}

}
