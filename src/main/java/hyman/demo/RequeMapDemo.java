package hyman.demo;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.acl.Owner;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class RequeMapDemo {

    /**
     * autowired 与resource 区别：
     *   @Autowired：是默认按照类型装配的，默认情况下必须要求依赖对象必须存在，如果要允许null值，可以设置它的required属性为false，如：
     *   @Autowired(required=false) ，如果我们想使用名称装配可以结合 @Qualifier注解进行使用，如下：@Autowired() @Qualifier("baseDao")；
     *
     *   @Resource：默认是按照名称byName 自动装配的，当找不到与名称匹配的 bean时才按照类型进行装配。但是需要注意的是，如果name属性一旦指定，就
     *   只会按照名称进行装配。
     *
     *   byName： 通过参数名 自动装配，如果一个bean的name 和另外一个bean的 property 相同，就自动装配。
     *   byType： 通过参数的数据类型自动自动装配，如果一个bean的数据类型和另外一个bean的property属性的数据类型兼容，就自动装配
     *
     *   我们可以通过 @Autowired 或 @Resource 在 Bean 类中使用自动注入功能，但是 Bean 还是在 XML 文件中通过 <bean> 进行定义 —— 也就是说，
     *   在 XML 配置文件中定义 Bean，通过@Autowired 或 @Resource 为 Bean 的成员变量、方法入参或构造函数入参提供自动注入的功能。
     *
     */


    /**
     * RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
     * RequestMapping注解有六个属性，下面我们把她分成三类进行说明。
     *
     * 1、 value， method；
     *     value：   指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；
     *     method：  指定请求的method类型， GET、POST、PUT、DELETE等；
     */

    @RequestMapping(value="/new", method = RequestMethod.GET)
    public Map<String,String> get() {
        return new HashMap<String, String>();
    }

    /**
     * spring通过 @PathVariable注解来获得请求url中的动态参数的，并且支持动态url访问，可以从url中直接提取参数而不需要采用?q=q&d=d的形式
     *
     * 同时变量url中的PathVariable还支持正则表达式校验，但下面最后一个方法不支持。
     */

    @RequestMapping("/id/{id}")
    public String id(@PathVariable("id") String id ) {
        return id;
    }

    //localhost:8080/test/id/123:hello
    @RequestMapping("/number/{number}:hello")
    public int number(@PathVariable("number") int number ) {
        return number;
    }

    @RequestMapping("/number/{number:[a-z-]+}{other:\\d{3}}/t")
    public String regular(
            @PathVariable("number") String number ,
            @PathVariable("other")String other) {
        return number+other;
    }

//    但不支持这一种，@PathVariable 注解只能接受字母，数字，或者加后缀三种方式的访问
//    @RequestMapping("/date/{date}")
//    public Date date(@PathVariable("date") Date date ) {
//        return date;
//    }

    @RequestMapping(value="/{day}", method = RequestMethod.GET)
    public Map<String, String> getForDay(@PathVariable @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date day, Model model) {
        return new HashMap<String, String>();
    }

    /**
     * value的uri值为以下三类：
     *
     *  A） 可以指定为普通的具体值；
     *  B)  可以指定为含有某变量的一类值(URI Template Patterns with Path Variables)；
     *  C) 可以指定为含正则表达式的一类值( URI Template Patterns with Regular Expressions);
     */
    @RequestMapping(value="/owners/{ownerId}", method= RequestMethod.GET)
    public String findOwner(@PathVariable String ownerId, Model model) {
        Owner owner = null;
        model.addAttribute("owner", owner);
        return "displayOwner";
    }

    @RequestMapping(value = "/spring-web/{version:\\d.\\d.\\d}.{extension:.[a-z]}")
    public void handle(@PathVariable String version, @PathVariable String extension) {
        // ...
    }

    @RequestMapping("/spring-web/{symbolicName:[a-z-]+}")
    public void handle1(@PathVariable String version, @PathVariable String extension) {
        // ...
    }


//    @RequestMapping(method = RequestMethod.POST)
//    public String add(@Valid AppointmentForm appointment, BindingResult result) {
//        if (result.hasErrors()) {
//            return "appointments/new";
//        }
//        return "redirect:/appointments";
//    }


    /**
     * 2、 consumes，produces；
     *    consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
     *    produces:    指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；
     */
    @RequestMapping(value = "/pets", method = RequestMethod.POST, consumes="application/json")
    public void addPet(@RequestBody Object object, Model model) {
        // 该方法仅处理request Content-Type为“application/json”类型的请求。
    }

    @RequestMapping(value = "/pets/{petId}", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public Object getPet(@PathVariable String petId, Model model) {
        return null;
        // 该方法仅处理request请求中 Accept头中包含了"application/json"的请求，同时暗示了返回的内容类型为application/json;
    }

    @RequestMapping(value = "importSalaryPoi",produces="text/html;charset=UTF-8")
    @ResponseBody
    public String importSalaryPoi(Integer siteId,HttpServletRequest request){
        return "";
    }


    /**
     * 3、 params，headers；
     *    params： 指定request中必须包含某些参数值是，才让该方法处理。
     *    headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求。
     */
    @RequestMapping(value = "/pets/{petId}", method = RequestMethod.GET, params="myParam=myValue")
    public void findPet(@PathVariable String ownerId, @PathVariable String petId, Model model) {

        // 仅处理请求中包含了名为“myParam”，值为“myValue”的请求；
    }

    @RequestMapping(value = "/pets", method = RequestMethod.GET, headers="Referer=http://www.ifeng.com/")
    public void findPet1(@PathVariable String ownerId, @PathVariable String petId, Model model) {

        // 仅处理request的header中包含了指定“Refer”请求头和对应值为“http://www.ifeng.com/”的请求；
    }
}
