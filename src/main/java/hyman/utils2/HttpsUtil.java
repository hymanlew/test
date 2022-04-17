package hyman.utils2;

import org.apache.commons.codec.EncoderException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// 参考地址：https://www.cnblogs.com/yaowen/p/9238651.html
public class HttpsUtil {

    private static BasicCookieStore cookieStore = new BasicCookieStore();
    private static String JSESSIONID="DcT1hrPYmXQz33L453vCsNLLhbTgyZwTCl6wD7sJpySS6cxgr3jQ!1372965369";
    private static BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", JSESSIONID);
    private static CloseableHttpClient httpClient = createHttpsClient();


    public static SSLContext getSSLContext() {

        /**
         * 创建 SSLContext，发起https请求：
         * https是对链接加了安全证书SSL的，如果服务器中没有相关链接的SSL证书，它就不能够信任那个链接，也就不会访问到了。所以
         * 我们第一步是自定义一个信任管理器。要实现自带的 X509TrustManager 接口就可以了。（或者直接 new 一个匿名内部类）。
         *
         * 注：
         * 1，需要的包都是java自带的，所以不用引入额外的包。
         * 2，当其实现的方法为空时，是默认为所有的链接都为安全的，也就是所有的链接都能够访问到。当然这样有一定的安全风险，可以
         * 根据实际需要写入内容。
         */

        // 采用绕过验证的方式处理https请求
        X509TrustManager x509mgr = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] xcs, String string) {
            }
            @Override
            public void checkServerTrusted(X509Certificate[] xcs, String string) {
            }
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };


        //因为java客户端要进行安全证书的认证，这里我们设置ALLOW_ALL_HOSTNAME_VERIFIER来跳过认证，否则将报错
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            //初始化
            //sslContext.init(null, new TrustManager[]{x509mgr}, null);
            sslContext.init(null, new TrustManager[]{x509mgr}, new java.security.SecureRandom());
            return sslContext;

        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static CloseableHttpClient createHttpsClient(){

        // 这是在有相关密钥时调用，就不需要配置 SSLContext 了。
        //cookieStore.addCookie(cookie);
        //CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();

        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(getSSLContext()))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);

        // 创建自定义的httpclient对象
        //CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connManager).build();

        return  httpClient;
    }

    public static void useCertify(){
        /**
         *
         https 的另一种访问方式（导入服务端的安全证书）：
         1，下载需要访问的链接所需要的安全证书。https://kyfw.12306.cn/ 以这个网址为例。在浏览器上访问 https://kyfw.12306.cn/。
         2，点击地址栏中的打了× 的锁查看证书。
         3，点击查看证书，详情信息，点击选择复制到文件进行导出。我们把它导入到 java项目所使用的 jre的 lib文件下的 security文件夹中去。例如 D:\Program Files (x86)\Java\
         jre8\lib\security。
         选择导出文件格式为 DER 编码（默认的），下一步，导出路径为刚才的路径，并为文件命名。

         4，打开cmd,进入到 java项目所使用的 jre的 lib文件下的 security目录。在命令行输入：
         Keytool -import -alias 12306 -file 12306.cer -keystore cacerts

         5，回车后会让输入口令，一般默认是changeit，输入时不显示，输入完直接按回车，会让确认是否信任该证书，输入y，就会提示导入成功。
         6，导入成功后就能使用 http 请求请求 https 了。

         注：有时候这一步还是会出错，可能是jre的版本不对，右键run as——run configurations，选择证书所在的 jre之后再运行。

         证书的查看，确认是否已经导入：
         keytool -list -keystore cacerts -storepass changeit | findstr 12306

         删除证书的命令为：
         keytool -delete -alias 12306 -keystore cacerts -storepass changeit
         */
    }

    /*
     * 处理https GET/POST请求
     * 请求地址、请求方法、参数
     * */
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr){
        StringBuffer buffer=null;

        try{
            // 创建 SSLContext，发起https请求：
            SSLContext sslContext = getSSLContext();

            //获取SSLSocketFactory对象
            // 该方法已经过时。所以不推荐使用。
            //SSLConnectionSocketFactory ssf = new SSLConnectionSocketFactory(
            //        sslContext,
            //        SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            //获取SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url=new URL(requestUrl);
            HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);

            //设置当前实例使用的SSLSoctetFactory
            conn.setSSLSocketFactory(ssf);
            conn.connect();

            //往服务器端写内容
            if(null!=outputStr){
                OutputStream os=conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }

            //读取服务器端返回的内容
            InputStream is=conn.getInputStream();
            InputStreamReader isr=new InputStreamReader(is,"utf-8");
            BufferedReader br=new BufferedReader(isr);
            buffer=new StringBuffer();

            String line=null;
            while((line=br.readLine())!=null){
                buffer.append(line);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static String getData(String url) throws IOException, EncoderException {

        HttpGet httpGet = new HttpGet(url);

        //在header里添加认证的 token，可以进行权限认证，并执行get请求获取json数据
        //httpGet.addHeader("Authorization", "admin");

        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        return body;
    }

    /**
     * json 字符串post请求
     */
    public static String getPostData(String url,String postBody) throws IOException, EncoderException {

        HttpPost httpPost = new HttpPost(url);

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
        StringEntity se = new StringEntity(postBody,"UTF-8");
        se.setContentType("text/json;charset=UTF-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httpPost.setEntity(se);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        return body;
    }

    /**
     * 参数map post请求
     */
    public static String getPostData(String url,Map<String,String>paramMap) throws IOException {

        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder builder=MultipartEntityBuilder.create();
        for(String key: paramMap.keySet()){
            builder.addTextBody(key,paramMap.get(key));
        }
        builder.setCharset(Charset.forName("UTF-8"));
        httpPost.setEntity(builder.build());
        System.out.println(httpPost.getURI());
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);

        return body;
    }

    /**
     * 参数map post请求
     */
    public static String getPostData(String url,Map<String,String>paramMap,Map<String,String>headerMap) throws IOException, EncoderException {

        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder builder=MultipartEntityBuilder.create();
        for(String key: paramMap.keySet()){
            builder.addTextBody(key,paramMap.get(key));
        }
        builder.setCharset(Charset.forName("UTF-8"));

        httpPost.setEntity(builder.build());
        if(headerMap!=null&&headerMap.size()>0){
            for(String key: headerMap.keySet()){
                httpPost.addHeader(key,headerMap.get(key));
            }
        }
        CloseableHttpResponse response = httpClient.execute(httpPost);
        List<Cookie> cookies = cookieStore.getCookies();

        for (int i = 0; i < cookies.size(); i++) {
            System.out.println(cookies.get(i).getName()+":"+cookies.get(i).getValue());
        }
        Header[]headers= response.getAllHeaders();
        for(Header header:headers){
            System.out.println(header.getName()+":"+header.getValue());
        }
//        if(response.getFirstHeader("Set-Cookie")!=null){
//            String setCookie = response.getFirstHeader("Set-Cookie").getValue();
//            JSESSIONID = setCookie.substring("JSESSIONID=".length(), setCookie.indexOf(";"));
//            System.out.println("JSESSIONID:" + JSESSIONID);
//        }
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);

        return body;
    }


    /**
     * json 字符串post请求
     */
    public static String getPostData(Map<String,String> headerMap, String contentType, String url, String postBody) throws IOException, EncoderException {

        HttpPost httpPost = new HttpPost(url);
        if(headerMap!=null){
            Iterator<Map.Entry<String, String>> headers = headerMap.entrySet().iterator();
            while (headers.hasNext()){
                Map.Entry<String, String> header = headers.next();
                httpPost.setHeader(header.getKey(),header.getValue());
            }
        }
        StringEntity se = new StringEntity(postBody,"UTF-8");

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
        if(contentType!=null){
            se.setContentType(contentType);
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        }else {
            se.setContentType("text/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        }
        httpPost.setEntity(se);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        return body;
    }


    public static void downLoadWebPicture(String url,String path) {

        // 获取输入流
        InputStream inputStream = getInputStream(url);
        byte[] date = new byte[1024];
        int len = 0;
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(path);
            while ((len = inputStream.read(date)) != -1) {
                fileOutputStream.write(date, 0, len);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * @return
     */
    public static InputStream getInputStream(String urlPath) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(urlPath);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设置连接网络的超时时间
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                inputStream = httpURLConnection.getInputStream();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return inputStream;
    }


    @SuppressWarnings("rawtypes")
    public static String localIp(){
        String ip = null;
        Enumeration allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                List<InterfaceAddress> InterfaceAddress = netInterface.getInterfaceAddresses();
                for (InterfaceAddress add : InterfaceAddress) {
                    InetAddress Ip = add.getAddress();
                    if (Ip != null && Ip instanceof Inet4Address) {
                        ip = Ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            // TODO Auto-generated catch block
        }
        return ip;
    }

    public static  void main(String[] args){

        try {
            // json格式数据，在 controller中接收时，也必须是一个字符串然后再转为 json
            String data = getPostData("http://localhost:8080/site/findSite_allow","{\"name\":\"宝盛商业广场1\",\"num\":\"3205021806061121\"}");
            //String data = getData("http://localhost:8080/site/findSite_allow?name=宝盛商业广场1&num=3205021806061121");
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }
}
