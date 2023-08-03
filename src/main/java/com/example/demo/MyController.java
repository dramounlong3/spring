package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

// RestController也會讓此class建立bean於Spring
@Scope("session") //配合HpPrinter設定
@RestController   //類似強化版的@Component, 為了讓class內的方法可以配上http method的requestMapping
@RequestMapping("/demo") //統一入口
public class MyController {

    // Autowired => DI: Dependency injection, 依賴注入
    // 讓MyController可以取得Spring中HpPrinter的實例hpPrinter
    // 想像MyController要依賴hpPrinter, 所以可以當成是將MyController的依賴注入到此class
    @Autowired
    //當有兩個bean屬於Printer類型的class，則需使用Qualified選擇需要的bean
    @Qualifier("myPrinter")
    private Printer printer;

    @Autowired
    @Qualifier("hpPrinter")
    private Printer hpPrinter;

    @Autowired
    @Qualifier("sonyPrinter")
    private  Printer sonyPrinter;

    @Autowired
    private SonyPrinter sony;

    // @RequestMapping會配對所有http method, 且class一定要@Controller or RestController
    @RequestMapping("/test1")
    public String test() {
        printer.print("hello myPrinter");
        return "myPrinter";
    }

    //測試bean variable initialize
    @RequestMapping("/test2")
    public String test2() {
        hpPrinter.print("hello HpPrinter");
        return "HpPrinter";
    }

    @RequestMapping("/test3")
    public String test3() {
        sonyPrinter.print("hello SonyPrinter");
        sony.myFunction();
        return "SonyPrinter";
    }

    // @RequestParam 取得URL問號後面的參數
    // 預設為必填, 且參數名稱需與程式內的變數名稱一致
    // name表示將url參數名稱testId的值帶入到程式內的變數sid, 很少用
    // required表示此參數是否為必要參數, 若設定false則程式內的變數有可能是null, 要避免發生NullPointerException的問題
    @RequestMapping("/test4")
    public  String test4(@RequestParam Integer id,
                         @RequestParam(name =  "testId") Integer sid,
                         @RequestParam(required = false) String name,
                         @RequestParam(defaultValue = "99") Integer age){
        System.out.println("id: " + id);
        System.out.println("sid: " + sid);
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        // console output
        // id: 123
        // sid: 456
        // name: null
        // age: 99
        return "Hi, your request is ok.";
    }

    // @RequestBody 取得request body的內容
    // 必須自定義class為變數的類型, Spring boot才會將request body的json格式自動轉換為java Object
    // 與@RequestParam 不一樣的是, 在沒有額外設定參數時, 預設參數是非必填
    // =================================================================================
    // @RequestHeader 取得request header的內容, 與RequestParam有相同的設定可使用
    // =================================================================================
    // @PathVariable 取得url路徑值, 且中間的參數值不可以省略
    @RequestMapping("/test5/{id}/{name}/{score}/{chinese}")
    public String test5(@RequestBody formatRequest student,
                        @RequestHeader(name= "Content-type") String contentType,
                        @RequestHeader(required = false) String age,
                        @RequestHeader(defaultValue = "100") Integer grade,
                        @PathVariable Integer id, //與上方路徑變數名稱一定要相同
                        @PathVariable String name,
                        @PathVariable Double score,
                        @PathVariable String chinese
                        ) {
        //格式化日期時間
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //Request Body
        System.out.println("id: " + student.id);
        System.out.println("name: " + student.name);
        System.out.println("score: " + student.score); //沒有給會是null, 若多給則捨棄參數, 給錯型態或無法自動轉型的值會報錯
        System.out.println("word: " + student.word); //測試中文
        System.out.println("currentTime: " + student.getCurrentTime()); //Thu Aug 03 12:31:00 GMT+00:00 2023
        System.out.println("sdFormat.format(student.currentTime): " + sdFormat.format(student.currentTime)); //測試日期 時間, 格式化後
        System.out.println("today: " + student.today); //測試日期

        //Request header
        System.out.println("contentType: " + contentType);
        System.out.println("age: " + age);
        System.out.println("grade: " + grade);

        //Path variable
        System.out.println("Path id: " + id);
        System.out.println("Path name: " + name); //沒給會是null
        System.out.println("Path score: " + score); //型態錯誤會報錯
        System.out.println("Path chinese: " + chinese); //測試中文


        return "Hi, your requestBody is ok. 測試中文";
    }




    // Spring會將回傳型別為自定義class的物件透過jackson套件自動轉為json格式
    @RequestMapping("/student")
    public Student student() {
        Student student = new Student();
        student.setName("kyle");
        student.courseList.add("Chinese"); //courseList沒有設定修飾子, 預設是同一個package底下的class都可以存取該成員
        student.courseList.add("Math");
        //以下介紹若將成員設定為private(只有自己的class內可以使用), 要如何設定List
        //student.todoList.add("繳費"); //無法在其他class直接存取todoList
        List<String> tempList = new ArrayList<>();
        tempList.add("繳費");
        tempList.add("讀書");
        tempList.add("逛街");
        student.setTodoList(tempList);
        return student;
    }
}
