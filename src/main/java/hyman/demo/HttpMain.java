package hyman.demo;

import com.google.gson.annotations.Since;
import com.sun.tools.internal.xjc.Language;

public class HttpMain {

    /**
     HttpUrlConnection 与 HttpClient 的区别：

     HttpUrlConnection是基于Java的网络请求的，原理是IO流流机制。
     Httpclient是Android开发平台封装的网络请求框架，也是基于HttpUrlCollection的封装的，HttpClient封装的相当丰富和完善，用起
     来十分方便，但是如用到比较特别的网络请求情况，用起来反而不方便，从效率上讲 HttpClient 没有 HttpUrlConnection 高。

     功能用法对比：
     HttpURLConnection 比 HttpClient 库要丰富很多，提供了很多工具，封装了http的请求头，参数，内容体，响应，还有一些高级功能，
     代理、COOKIE、鉴权、压缩、连接池的处理。
     HttpClient 高级功能代码写起来比较复杂，对开发人员的要求会高一些。而HttpURLConnection对大部分工作进行了包装，屏蔽了不需要
     的细节，适合开发人员直接调用。

     性能对比：
     HttpUrlConnection直接支持GZIP压缩；HttpClient也支持，但要自己写代码处理。
     HttpUrlConnection直接支持系统级连接池，即打开的连接不会直接关闭，在一段时间内所有程序可共用；HttpClient当然也能做到，但
     毕竟不如官方直接系统底层支持好。
     HttpUrlConnection直接在系统层面做了缓存策略处理（4.0版本以上），加快了重复请求的速度。在2.3版本增加了一些HTTPS方面的改进，
     4.0版本增加一些响应的缓存。

     速度对比：
     做法是两个类都使用默认的方法去请求百度的网页内容，测试结果是使用 httpurlconnection耗时47ms，使用 httpclient耗时641ms。
     httpURLConnection 在速度有比较明显的优势，当然这跟压缩内容和缓存都有直接关系。

     未来发展：
     HttpClient 适用于web browsers, 他们是可扩展的，并且拥有大量的稳定APIs。但是，在不破坏其兼容性的前提下很难对如此多的APIs做修改。
     HttpURLConnect 是一个通用的、适合大多数应用的轻量级组件。但是在Android 2.2及以下版本上存在一些令人厌烦的bug，尤其是在读取
     InputStream时调用 close()方法，就有可能会导致连接池失效了。
     HttpURLConnection 的API简单，体积较小，因而非常适用于Android项目。压缩和缓存机制可以有效地减少网络访问的流量，在提升速度
     和省电方面也起到了较大的作用。

     选用建议：
     如果一个Android应用需要向指定页面发送请求，但该页面并不是一个简单的页面，只有当用户已经登录，而且登录用户的用户名有效时才
     可访问该页面。如果使用 HttpURLConnection 来访问这个被保护的页面，那么需要处理的细节就太复杂了。这种情况建议使用HttpClient。
     Android2.3及以上版本建议选用HttpURLConnection，2.2及以下版本建议选用HttpClient。新的应用都建议使用HttpURLConnection。


     Controller中有三种方式获取表单数据（只适用于 key=value 格式的请求参数）：
     在接收方法中，添加HttpServletRequst类型入参，通过HttpServletRequst.getParameter()获取请求数据。
     在接收方法中，添加对应表单字段name的参数，有几个表单字段就添加多少个对应的入参。
     在接收方法中，添加自定义Java类型的入参，并添加@ModelAttribute注解（实际上，可以不添加@ModelAttribute注解），由这个入参对
     象接收表单提交的数据。

     参数请求的格式，服务器默认的解析就是依据 key=value 的格式，所以无论 post，get 请求，采用默认模式时，参数都需要设置为此格式。
     即使用req.getParameter获取POST请求的数据，必须x-www-form-urlencode。

     常见的post提交数据的方式：application/x-www-form-urlencoded、multipart/form-data、application/json、text/xml。
     application/json是标准写法。text/json没有官方规定支持，但是很多各种框架都有对它的支持，相当于一个广泛山寨版本。


     @RequestParam，即 url传参你不指定请求类型，默认get请求。
     @Requestbody，传 json 格式的对象，不指定请求类型默认为 post请求，get请求是没法用 @Requestbody 注解的。


     ajax 发送 json 数据时设置 contentType: "application/json” 和不设置时到底有什么区别？
     首先明确一点，这也是一种文本类型（和text/json一样），表示json格式的字符串，如果数据设置为该类型，则发送的json对象必须要使
     用 JSON.stringify 进行序列化成字符串才能和设定的这个类型匹配。
     同时，对应的后端如果使用了Spring，接收时需要使用 @RequestBody 来注解，这样才能将发送过来的json字符串解析绑定到对应的 pojo
     属性上。
     另外需注意一点，json字符串在书写时名称部分需要加上“”双引号，以免一些json解析器无法识别。

     如果 ajax 请求时不设置任何 contentType，则默认将使用 contentType: "application/json”application/x-www-form-urlencoded，
     这种格式的特点就是按照 name=value&name=value 的模式进行传递参数。与 get 请求的传递模式相同。而 post 请求则是使用请求体，
     参数不在 url 中，在请求体中的参数表现形式也是: user=username&pass=password的形式。
     所以在使用这种contentType时，对于简单的json对象类型，如：{“a”:1,"b":2,"c":3} 这种，也会被转成 user=username&pass=password
     这种形式发送到服务端。而服务端接收时就按照正常从from表单中接收参数那样接收即可，不需设置 @RequestBody 之类的注解。
     并且这种转换时只能识别 json 对象类型，不能识别 json 字符串类型。

     但对于复杂的json 结构数据，这种方式处理起来就相对要困难，服务端解析时也难以解析，所以就有了application/json 这种类型，这
     是一种数据格式的申明，明确告诉服务端是什么格式的数据，服务端只需要根据这种格式的特点来解析数据即可。
     即 ajax 发送的是json字符串，服务端接收时必须要使用 @RequestBody注解。始终记住，json字符串，"application/json”，@RequestBody
     这三者之间是一一对应的，要有都有，要没有都没有。并且 get请求会失败。


     application/x-www-form-urlencoded 和 application/json 两种类型的数据在后端如何接收并解析？
     application/x-www-form-urlencoded 这种类型的参数提交方式有get和post两种，其区别是前者把编码后的 user=username&pass=password
     这种形式的参数放在url上进行提交，后者是放在请求报文的请求体部分进行发送，只是发送数据时数据放的位置不一样。服务端收到
     user=username&pass=password 这种形式的参数后，原生的Servlet使用request.getParameter(“user”)的这种形式即可获取参数，spring
     mvc 中框架可自动根据参数名进行匹配，即表单元素的name属性和接收参数的名称一样时即可自动匹配，如果不一样，还可以使用 @RequestParam
     的方式匹配。

     application/json 字符串数据原生的Servlet中可以使用request.getParameterMap()来获取，并且是具有 name=value(json) 的格式
     ，否则就接收不到。即只能获取Get方式传入的数据。
     post传入的需要使用输入流的方式来读取。在spring mvc中通过@RequestBody来解析并绑定json字符串参数到方法入参。

     使用时机，GET、POST方式提时， 根据request header Content-Type的值来判断:
     1）application/x-www-form-urlencoded， 可选（即非必须，因为这种情况的数据@RequestParam, @ModelAttribute也可以处理，
        当然@RequestBody也能处理）；
     2）multipart/form-data, 不能处理，使用 @RequestBody 也不能处理这种格式的数据。只能用于 POST UPLOAD 文件上传。
     3）其他格式， 包括application/json, application/xml等。这些格式的字符串数据，必须使用 @RequestBody来处理。

     PUT方式提交时， 根据request header Content-Type的值来判断:
     请求头必须为 application/x-www-form-urlencoded，且不能处理 multipart/form-data 或其他格式， 必须固定是前者。

     conn.addRequestProperty / conn.setRequestProperty（add是追加， set是覆盖设置）


     类型转换：
     clazz.isArray()
     Map.class.isAssignableFrom(clazz);
     Collection.class.isAssignableFrom(clazz);

     GET:
     全部都使用默认的（即 Content-Type=application/x-www-form-urlencoded;charset=utf-8），并参数放在 url 中，它会自动编码。
     注意请求体中不能有数据，即使写入也会无效。

     POST:
     header 头（Content-Type=application/x-www-form-urlencoded; charset=utf-8，Content-Length=整个参数的 byte 长度）。
     参数内容（=格式，并只对 value 进行 utf-8 编码），写入到请求体中。

     PUT：
     header 头（Content-Type=application/x-www-form-urlencoded; charset=utf-8，Content-Length=整个参数的 byte 长度）。
     参数内容（=格式，并只对 value 进行 utf-8 编码），写入到请求体中。
     PUT请求时，请求体中的实体如果存在于服务器，则该封装实体作为原始服务器上的修改版本。如果不存在，则原始服务器可用此 URI 创
     建新的资源。即该请求主要用于更新资源，但要注意并不是上传（upload），上传必须用 post。

     DELETE：
     header 头（Content-Type=application/x-www-form-urlencoded; charset=utf-8），也可以不指定，因为它是默认的。无参数内容。

     UPLOAD：
     请求方式（POST 且必须是这个），
     设置（conn.setUseCaches(false)，conn.setInstanceFollowRedirects(true)）
     header 头（Connection=Keep-Alive，Charset=UTF-8，Content-Type=multipart/form-data;boundary=*****，表示分界线），
     在发送数据时，首先要先写入内容类型（"Content-Disposition: form-data;name=xx;filename=xx" + \r\n），
     第次读到流数据写入时，都要先写入分界线，并且以换行或者回车结束（\r\n），


     HTTP 请求的 header 头解析：
     Accept：
     作用：浏览器端可以接受的媒体类型。例如 Accept: text/html 代表浏览器可以接受服务器回发的类型为 text/html 也就是我们常说的
     html文档，如果服务器无法返回text/html类型的数据,服务器应该返回一个406错误(non acceptable)。
     通配符 * 代表任意类型，例如 Accept: * / * 则代表浏览器可以处理所有类型，(一般浏览器发给服务器都是发这个)。

    Accept-Encoding：
    作用：浏览器申明自己接收的编码方法，通常指定压缩方法，是否支持压缩，支持什么压缩方法（gzip，deflate），注意这里不是只字符
     编码。例如：Accept-Encoding: zh-CN,zh;q=0.8。

    Accept-Language：
    作用：浏览器申明自己接收的语言。语言跟字符集的区别：中文是语言，中文有多种字符集，比如big5，gb2312，gbk等等。例如：Accept-Language: en-us。

    Connection：
    例如：Connection:keep-alive 当一个网页打开完成后，客户端和服务器之间用于传输HTTP数据的TCP连接不会关闭，如果客户端再次访问
     这个服务器上的网页，会继续使用这一条已经建立的连接。
    例如：Connection: close 代表一个Request完成后，客户端和服务器之间用于传输HTTP数据的TCP连接会关闭， 当客户端再次发送Request，
     需要重新建立TCP连接。

    Host（发送请求时，该报头域是必需的）：
    作用：请求报头域主要用于指定被请求资源的Internet主机和端口号，它通常从HTTP URL中提取出来的。
    例如: http://www.hzau.edu.cn，则浏览器发送的请求消息中，Host请求报头域如下：Host：www.hzau.edu.cn。此处使用缺省端口号80，
     若指定了端口号，则变成：Host：指定端口号。

    Referer：
    当浏览器向web服务器发送请求的时候，一般会带上Referer，告诉服务器我是从哪个页面链接过来的，服务器籍此可以获得一些信息用于处
     理。比如从我主页上链接到一个朋友那里，他的服务器就能够从HTTP Referer 中统计出每天有多少用户点击我主页上的链接访问他的网站。

    User-Agent：作用是告诉HTTP服务器，客户端使用的操作系统和浏览器的名称和版本。

    Cache-Control：
    网页的缓存控制是由HTTP头中的“Cache-control”来实现的，常见值有private、no-cache、max-age、must-revalidate等，默认为private。
     这几种值的作用是根据重新查看某一页面时不同的方式来区分的：

     1，打开新窗口时，值为private、no-cache、must-revalidate，那么打开新窗口都会重新访问服务器。而如果指定了max-age值（单位为秒），
     那么在此值内的时间里就不会重新访问服务器，例如：Cache-control: max-age=5 (表示当访问此网页后的5秒内再次访问不会去服务器)。

     2，在地址栏回车，值为 private 或 must-revalidate，则只有第一次访问时会访问服务器，以后就不再访问。值为no-cache，那么每次
     都会访问。值为max-age，则在过期之前不会重复访问。

     3，按后退按扭，值为private、must-revalidate、max-age，则不会重访问，值为no-cache，则每次都重复访问。

     4，按刷新按扭，无论为何值，都会重复访问。


    Cookie，是用来存储一些用户信息以便让服务器辨别用户身份的，比如cookie会存储一些用户的用户名和密码，sessionid等。

    If-Modified-Since，是把浏览器端缓存页面的最后修改时间发送到服务器去，服务器会把这个时间与服务器上实际文件的最后修改时间进
     行对比。如果时间一致，那么返回304，客户端就直接使用本地缓存文件。
    如果时间不一致，就会返回200和新的文件内容。客户端接到之后，会丢弃旧文件，把新文件缓存起来，并显示在浏览器中.

     If-None-Match，作用是 If-None-Match和ETag 一起工作，工作原理是在 HTTP Response中添加ETag信息。当用户再次请求该资源时，
     将在HTTP Request 中加入If-None-Match信息(ETag的值)。如果服务器验证资源的ETag没有改变（该资源没有更新），将返回一个304状
     态告诉客户端使用本地缓存文件。否则将返回200状态和新的资源和Etag.  使用这样的机制将提高网站的性能。

     */
}
