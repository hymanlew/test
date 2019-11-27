package hyman.demo;

import org.springframework.http.HttpRequest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpJava {

    /**
     * Java 11 新特性HttpClient
     */
    public static void test() throws IOException, InterruptedException {
        // 定义一个HTTP 请求
        HttpRequest request = HttpRequest.newBuilder(
                URI.create("http://oa.zhenghongwy.com:8080/dongdong?deviceId=1006104&id=eRvjYc87XslZjf5teM4h5g==&time=1568611031701")
        ).build();
        // 创建一个客户端（可以是浏览器，IOS，Android，小程序）
        HttpClient client = HttpClient.newBuilder().build();
        // 发送请求 并接受响应信息
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // 读取响应信息（转换成）
        String body = response.body();
        // 打印信息
        System.out.println(body);
    }

    /**
     * Java 11 新特性HttpClient，示例
     */
    public static void main(String[] a) throws Exception{
        new HelloJava11().demoHttp();
    }

    public void demoHttp() throws Exception{
        httpSync();
        httpAsync();
        postFile();
        postMultipart();
    }

    public void postFile() throws FileNotFoundException {
        HttpRequest request = HttpRequest.newBuilder()
                .version(Version.HTTP_1_1)
                .uri(URI.create("http://httpbin.org/post"))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "octet-stream")
                .POST(BodyPublishers.ofFile(Paths.get("HelloJava11.java")))
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }

    public void postMultipart() throws FileNotFoundException {
        String boundary = "B-0-u-n-d-a-r-y";
        String data = "Hello Yang!";
        HttpRequest request = HttpRequest.newBuilder()
                .version(Version.HTTP_1_1)
                .uri(URI.create("http://httpbin.org/post"))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "multipart/form-data, boundary=" + boundary)
                .POST(BodyPublishers.ofString("--" + boundary + "\r\n"
                        + "content-disposition: form-data; filename=\"text\"\r\n"
                        + "Content-Type: text/plain\r\n"
                        + "\r\n"
                        + data + "\r\n"
                        + "--" + boundary + "--\r\n"))
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }

    public void httpSync() throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .version(Version.HTTP_1_1)
                .uri(URI.create("http://httpbin.org/get"))
                .GET().build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    public void httpAsync(){
        HttpRequest request = HttpRequest.newBuilder()
                .version(Version.HTTP_1_1)
                .uri(URI.create("http://httpbin.org/get"))
                .GET().build();
        HttpClient client = HttpClient.newBuilder().build();
        client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(response -> {
                    System.out.println(response.statusCode());
                    return response;
                }).thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }
}
