//package hyman.utils;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpRequest.BodyPublishers;
//import java.net.http.HttpResponse;
//import java.net.http.HttpResponse.BodyHandlers;
//import java.nio.charset.Charset;
//
///**
// * 企业微信机器人调用
// * https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=e206b7f6-0e5c-494e-876b-d56c0a08597c
// * 保密[KEY]
// */
//public class HttpUtilJDK12 {
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        // 创建一个Http 客户端
//        HttpClient httpClient = HttpClient.newHttpClient();
//        // 定义请求的URL和参数
//        String url ="https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=e206b7f6-0e5c-494e-876b-d56c0a08597c";
//        // 定义请求
//        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(url))
//                .header("Content-Type","application/json")
//                .POST(BodyPublishers.ofString("{\n"
//                        + "        \"msgtype\": \"text\",\n"
//                        + "        \"text\": {\n"
//                        + "            \"content\": \"服务器挂掉了\"\n"
//                        + "        }\n"
//                        + "   }"))
//                .build();
//
//        //BodyPublishers.ofString("", Charset.forName("UTF-8")
//        HttpResponse<String> send = httpClient.send(httpRequest, BodyHandlers.ofString());
//        System.out.println(send);
//    }
//}
