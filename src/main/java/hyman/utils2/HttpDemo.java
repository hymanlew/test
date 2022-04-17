package hyman.utils2;


import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

//httpclient-4.3.6.jar
//import cz.msebera.android.httpclient.client.methods.HttpPost;
//import cz.msebera.android.httpclient.entity.StringEntity;
//import cz.msebera.android.httpclient.impl.client.CloseableHttpClient;
//import cz.msebera.android.httpclient.impl.client.HttpClients;
//import org.apache.commons.httpclient.methods.GetMethod;

/**
 *
 * 目前阶段，JAVA实现HTTP请求的方法用的最多的有两种：
 *  一种是通过 HTTPClient 这种第三方的开源框架去实现。HTTPClient对HTTP的封装性比较不错，通过它基本上能够满足我们大部分的需求。
 *  另一种则是通过 HttpURLConnection去 实现，HttpURLConnection是JAVA的标准类，是JAVA比较原生的一种实现方式。
 *
 *
 * service 编程调用的方式访问 webserviceDemoClient 服务：
 *   该种方式可以自定义关键元素，方便以后维护，是一种标准的开发方式；
 *   但这种方式同样需要wsimport生成客户端代码,只不过仅需要将服务接口类引入即可
 *
 * HttpURLConnection调用的方式访问 webserviceDemoClient 服务：
 * 开发步骤：
     第一步：创建服务地址
     第二步：打开一个通向服务地址的连接
     第三步：设置参数，设置POST，POST必须大写，如果不大写面积报异常。设置输入输出，否则报异常。
     第四步：组织SOAP数据，发送请求
     第五步：接收服务端响应，打印
 *
 * HttpClient：可以用来调用webservie服务，也可以抓取网页数据，此方式不需要wsimport命令，而是直接编写了客户端。
 *
 */
public class HttpDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpDemo.class);

    public static String doGet(String urls){

        return null;
    }

    public static void testConnection() throws IOException {

        OutputStream outputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        PrintWriter writer = null;
        BufferedReader reader = null;
        HttpURLConnection connection = null;
        try {
            // 创建 url连接
            URL url = new URL("http://test.com");

            // 打开连接，获取HttpURLConnection,url.openConnection()获取的是URLConnection,而HttpURLConnection继承URLConnection，所以需要强转
            connection = (HttpURLConnection) url.openConnection();

            // 设置是否向 connection 输出/输入，因为它是 post请求，参数要放在 http正文内，因此需要设为 true
            connection.setDoOutput(true);
            connection.setDoInput(true);

            //allowUserInteraction 如果为 true，则在允许用户交互（例如弹出一个验证对话框）的上下文中对此 URL 进行检查。
            connection.setAllowUserInteraction(false);

            // 设置请求方式，默认是 get
            connection.setRequestMethod("POST");
//        connection.setRequestMethod("GET");

            //设置连接超时时间
            connection.setConnectTimeout(10000);

            //设置连接之后响应的超时时间
            connection.setReadTimeout(20000);

            //让客户端与服务端保持连接
            connection.setRequestProperty("connection","Keep-Alive");

            // post请求不能使用缓存
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);

            // 配置本次连接的文件类型
            // application/x-www-form-urlencoded 是代表正文是经过 urlencoded编码过的form参数
            /**
             * Http Header里的Content-Type一般有这三种：
             *
             * application/x-www-form-urlencoded：数据被编码为名称/值对。这是标准的编码格式。
             * multipart/form-data：这种方式提交时，参数会被分割成多块，每一个参数块都有自己独立的content-type，此方式可用于提交普通表单和文件上传，并加上分割符(boundary)。
             * text/plain： 数据以纯文本形式（text/json/xml/html）进行编码（即 application/json 格式），其中不含任何控件或格式字符。postman软件里标的是RAW。
             *
             * form 的 enctype 属性为编码方式，常用有两种：application/x-www-form-urlencoded 和 multipart/form-data，默认为application/x-www-form-urlencoded。
             *
             * 当action为get时候，浏览器用x-www-form-urlencoded的编码方式把form数据转换成一个字串（name1=value1&name2=value2...），
             * 然后把这个字串追加到url后面，用?分割，加载这个新的url。
             *
             * 当action为post时候，浏览器把form数据封装到http body中，然后发送到server。如果没有type=file的控件，用默认的application/x-www-form-urlencoded就可以了。
             * 但是如果有type=file的话，就要用到multipart/form-data了。
             *
             * 当action为post，且Content-Type类型是multipart/form-data，浏览器会把整个表单以控件为单位分割，并为每个部分加上Content-Disposition(form-data或者file)，
             * Content-Type(默认为text/plain)，name(控件name)等信息，并加上分割符(boundary)。
             */
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            // 执行连接，需要注意在连接之前要配置完成所有的设置。并且 connection.getOutputStream会隐含的进行connect。
            connection.connect();

            // 数据输入/输出流允许应用程序以适当方式（可以指定格式）将基本 Java数据类型写入到时输入/输出流中。然后应用程序可以对数据进行操作。
            // 并且此类数据流与平台无关，只对数据操作。
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            // 请求正文，即参数列表
            String content = "account="+ URLEncoder.encode("测试","UTF-8");
            //发送请求参数即数据
            out.writeBytes(content);
            out.flush();
            out.close();


            //字符类型的打印输出流
            outputStream = connection.getOutputStream();
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            writer = new PrintWriter(bufferedOutputStream);

            //发送请求参数即数据
            writer.write(content);
            writer.flush();

            if(200 == connection.getResponseCode()){
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null){
                    System.out.println(line);
                    result.append(line + "\n");
                }
            }
        }catch (Exception e){

            LOGGER.error("http get request exception error",e);

           //将异常抛出的目的是：因为这种GET请求一般实在业务逻辑中使用，如果出现HTTP通信异常，将异常抛出，便于使用该方法的地方将异常捕获进行处理。
            throw new RuntimeException("HTTP通信异常！");

        }finally{
            try{
                if(writer != null) {
                    writer.close();
                }
                if(bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if(outputStream != null) {
                    outputStream.close();
                }
                if(connection != null) {
                    connection.disconnect();
                }
                if(reader!=null){
                    reader.close();
                }
                connection.disconnect();
            }catch (Exception e){
                throw new RuntimeException("HTTP关闭IO出现异常！");
            }
        }
    }


    // 通过apache.commons.httpclient 框架来模拟实现 Http请求--get
    public static String oldGetMethod(String url,String param) throws Exception {

        String result = "";
        org.apache.commons.httpclient.HttpClient client = null;
        GetMethod getMethod = new GetMethod(url+"?"+param);

        try{
            client = new org.apache.commons.httpclient.HttpClient();

            //设置请求头
            getMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");

            //设置连接超时时间，10秒
            client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);

            //设置30秒内没有收到数据的话，则强制断开客户端
            client.getHttpConnectionManager().getParams().setSoTimeout(30000);

            //执行请求
            client.executeMethod(getMethod);

            //获取返回报文，通过IO流进行读取（字节流，字符流，缓冲流）
            InputStream responseBody = getMethod.getResponseBodyAsStream();
            InputStreamReader in = new InputStreamReader(responseBody,"utf-8");
            BufferedReader br = new BufferedReader(in);

            StringBuffer stringBuffer = new StringBuffer();
            String line ="";
            while((line=br.readLine())!=null){
                stringBuffer.append(line);
            }
            in.close();

            result = stringBuffer.toString().trim();
        }catch (Exception e){
            LOGGER.error("error",e);
        }finally {
            if(getMethod!=null){
                getMethod.releaseConnection();
            }
        }
        return result;

//
        //执行，得到消息码，方法返回 int值
//        int code = client.executeMethod(getMethod);
//        System.out.println("code1:"+code);
//
//        if(code== HttpURLConnection.HTTP_OK){
//            //得到执行结果
//            result = getMethod.getResponseBodyAsString();
//            System.out.println("result:"+result);
//        }
//        return result;
    }


    // 通过apache.commons.httpclient 框架来模拟实现 Http请求--post
    public static String oldPostMethod(String url,String data) throws Exception {

        String result = "";
        org.apache.commons.httpclient.HttpClient client = null;
        PostMethod postMethod = new PostMethod();

        try{
            client = new org.apache.commons.httpclient.HttpClient();

            //设置请求头
            //postMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            postMethod.setRequestHeader("Content-Type","application/json;charset=UTF-8");

            //设置连接超时时间，10秒
            client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);

            //设置30秒内没有收到数据的话，则强制断开客户端
            client.getHttpConnectionManager().getParams().setSoTimeout(30000);


            //设置参数
            //postMethod.setParameter("key","value");
            HttpMethodParams params = new HttpMethodParams();
            params.setContentCharset("UTF-8");
            params.setParameter("json",data);
            postMethod.setParams(params);

            InputStream inputStream = new ByteArrayInputStream(data.getBytes("utf-8"));
            RequestEntity entity = new InputStreamRequestEntity(inputStream);
            postMethod.setRequestEntity(entity);


            //执行请求
            client.executeMethod(postMethod);

            //获取返回报文，通过IO流进行读取（字节流，字符流，缓冲流）
            //InputStream responseBody = postMethod.getResponseBodyAsStream();
            //            //InputStreamReader in = new InputStreamReader(responseBody,"utf-8");
            //            //BufferedReader br = new BufferedReader(in);
            //            //
            //            //StringBuffer stringBuffer = new StringBuffer();
            //            //String line ="";
            //            //while((line=br.readLine())!=null){
            //            //    stringBuffer.append(line);
            //            //}
            //            //in.close();

            //result = stringBuffer.toString().trim();

            byte[] bytes = postMethod.getResponseBody();
            result = new String(bytes,"utf-8");
        }catch (Exception e){
            LOGGER.error("error",e);
        }finally {
            if(postMethod!=null){
                postMethod.releaseConnection();
            }
        }
        return result;

//        GetMethod getMethod = new GetMethod("http://127.0.0.1/serviceTest?wsdl/transWords?words="+mobileCode);
        //执行，得到消息码，方法返回 int值
//        int code = client.executeMethod(getMethod);
//        System.out.println("code1:"+code);
//
//        if(code== HttpURLConnection.HTTP_OK){
//            //得到执行结果
//            result = getMethod.getResponseBodyAsString();
//            System.out.println("result:"+result);
//        }
//        return result;
    }


    // 通过apache.http.client 框架来模拟实现 Http请求--post
    public static String postMethod(String url,String data) throws IOException {
        /**
         * HttpClient是Apache Jakarta Common下的子项目，用来提供高效的、最新的、功能丰富的支持HTTP协议的客户端编程工具包，并且它支持
         * HTTP协议最新的版本和建议。
         * 相比传统 JDK自带的URLConnection，它增加了易用性和灵活性。
         *
         * 其特性有：
         1. 基于标准、纯净的java语言。实现了Http1.0和Http1.1

         2. 以可扩展的面向对象的结构实现了Http全部的方法（GET, POST, PUT, DELETE, HEAD, OPTIONS, and TRACE）。

         3. 支持HTTPS协议。  4.并通过Http代理建立透明的连接。

         5. 利用CONNECT方法通过Http代理建立隧道的https连接。

         6. Basic, Digest, NTLMv1, NTLMv2, NTLM2 Session, SNPNEGO/Kerberos认证方案。

         7. 插件式的自定义认证方案。

         8. 便携可靠的套接字工厂使它更容易的使用第三方解决方案。

         9. 连接管理器支持多线程应用。支持设置最大连接数，同时支持设置每个主机的最大连接数，发现并关闭过期的连接。

         10. 自动处理Set-Cookie中的Cookie。

         11. 插件式的自定义Cookie策略。

         12. Request的输出流可以避免流中内容直接缓冲到socket服务器。

         13. Response的输入流可以有效的从socket服务器直接读取相应内容。

         14. 在http1.0和http1.1中利用KeepAlive保持持久连接。

         15. 直接获取服务器发送的response code和 headers。

         16. 设置连接超时的能力。

         17. 实验性的支持http1.1 response caching。

         18. 源代码基于Apache License 可免费获取。
         */

        // 创建 Http-Client实例
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        String result = "";

        /**
         * List<NameValuePair> nvps = new ArrayList<NameValuePair>();
         * nvps.add(new BasicNameValuePair("KEY1", "VALUE1"));
         * httpPost.setEntity(new UrlEncodedFormEntity(nvps));
         *
         * 使用 UrlEncodedFormEntity 来设置 body，消息体内容类似于 “KEY1=VALUE1&KEY2=VALUE2&...” 这种形式，服务端接收以后
         * 也要依据这种协议形式做处理。
         *
         * 如果是想使用 json 格式来设置 body，就可以使用 StringEntity 类。
         *
         * 其实采用 StringEntity 形式更加自由，除了json，你也可以使用其它任意的字符串，只要服务端能做相应处理即可。
         */
        // 设置参数，data 必须是 json格式进行传输
        StringEntity postEntity = new StringEntity(data,"UTF-8");
        postEntity.setContentType("text/json;charset=UTF-8");
        postEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));

        post.addHeader("Content-Type","text/json");
        post.setHeader("Content-Type","text/json");
        post.setEntity(postEntity);


        //String soapRequestData = mobileCode;
        //byte[] b = soapRequestData.getBytes("utf-8");
        //InputStream is = new ByteArrayInputStream(b,0,b.length);
        //RequestEntity re = new InputStreamRequestEntity(is,b.length,"text/xml; charset=utf-8");
        //post.setRequestEntity(re);

        // 执行,返回一个结果类（即 HttpResponse 的子类 CloseableHttpResponse）
        CloseableHttpResponse response = client.execute(post);
        // 得到结果实体
        HttpEntity entity = response.getEntity();

        // 获取结果
       result = EntityUtils.toString(entity,"UTF-8");

        //释放连接
        post.releaseConnection();
        return result;
    }


    // 通过apache.http.client 框架来模拟实现 Http请求--get
    public static String GetMethod(String url){

        CloseableHttpClient client = null;
        HttpGet get = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String result = "";

        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(15000)
                .setConnectTimeout(15000)
                .setConnectionRequestTimeout(15000)
                .build();

        try {
            client = HttpClients.createDefault();
            get = new HttpGet(url);
            get.setConfig(config);

            response = client.execute(get);
            entity = response.getEntity();
            result = EntityUtils.toString(entity,"UTF-8");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public static void main(String[] args) throws Exception {

        //String data = postMethod("http://localhost:8080/site/findSite_allow","{\"name\":\"宝盛商业广场1\",\"num\":\"3205021806061121\"}");

        //http://61.177.28.52:9001/building/site/findSite_allow?name=新建高标准工业厂房&num=3205021807040941

        String param = "name="+URLEncoder.encode("新建高标准工业厂房", "utf-8")+"&num=3205021807040941";
        String url = "http://61.177.28.52:9001/building/site/findSite_allow";
        //String url = "http://localhost:8080/site/findSite_allow";
        String data = oldGetMethod(url,param);
        System.out.println(data);
    }
}
