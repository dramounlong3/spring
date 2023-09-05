package com.example.demo.Zsample.jdbc;

import com.example.demo.Zsample.commonModel.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Printer sonyPrinter;

    @Autowired
    private SonyPrinter sony;

    // 使用spring jdbc功能
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
    public ResponseEntity<?> test4(@RequestParam Integer id,
                                   @RequestParam(name = "testId") Integer sid,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(defaultValue = "99") Integer age) {
        System.out.println("id: " + id);
        System.out.println("sid: " + sid);
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        // console output
        // id: 123
        // sid: 456
        // name: null
        // age: 99

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        return ResponseEntity.status(200).body(student);
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
                        @RequestHeader(name = "Content-type") String contentType,
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

    // Spring預設只會回傳200和500的狀態碼
    // 測試當runtimeException時，ControllerAdvice內的ExceptionHandler是否可以抓到此錯誤
    // 最後回傳自定義狀態碼
    @RequestMapping("/test6")
    public ResponseEntity<?> test6() {
        throw new RuntimeException("test6 error.");
    }

    // 與test6交叉測試
    @RequestMapping("/test7")
    public ResponseEntity<?> test7() {
        throw new IllegalArgumentException("test7 error.");
    }

    // 測試interceptor(攔截器)
    @RequestMapping("/test8")
    public String test8() {
        System.out.println("執行test8 controller method");
        return "回傳test8 controller method";
    }


    // Spring會將回傳型別為自定義class的物件透過jackson套件自動轉為json格式
    @RequestMapping("/student")
    public ResponseEntity<?> student() {
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
        return ResponseEntity.status(200).body(student);
    }

    //spring jdbc insert
    @PostMapping("/sql/student")
    public ResponseEntity<?> insert(@RequestBody Student student) {

        //透過冒號變數的方式讓下面宣告的map代入
        String sql = "INSERT INTO student(id, name, courseList, todoList) VALUE(:studentId, :studentName, :studentCourseList, :studentTodoList)";

        Map<String, Object> map = new HashMap<>();
        //由key value方式組成, 透過request傳入的參數, 代入至sql語法執行
        map.put("studentId", student.getId());
        map.put("studentName", student.getName());
        map.put("studentCourseList", student.getCourseList().toString());
        map.put("studentTodoList", student.getTodoList().toString());

        //update 對應到 sql可執行 insert, update, delete
        namedParameterJdbcTemplate.update(sql, map);

        return ResponseEntity.status(201).body(student);
    }

    //取得自動產生的primary key
    @PostMapping("sql/threetier")
    public ResponseEntity<?> insert2(@RequestBody Student student) {
        String sql = "INSERT INTO threetier(name, courseList, todoList) VALUE(:studentName, :studentCourseList, :studentTodoList)";

        Map<String, Object> map = new HashMap<>();
        map.put("studentName", student.getName());
        map.put("studentCourseList", student.getCourseList().toString());
        map.put("studentTodoList", student.getTodoList().toString());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        //取得自動生成的id, value前面若是用long型態則須改為longValue
        int id = keyHolder.getKey().intValue();

        System.out.println("mysql自動生成的 id: " + id);
        return ResponseEntity.status(201).body(student);
    }

    @PostMapping("/sql/student/batch")
    public ResponseEntity<?> insertList(@RequestBody List<Student> studentList) {
        String sql = "INSERT INTO threetier (name, courseList, todoList) VALUE(:studentName, :studentCourseList, :studentTodoList)";

        //創建一個MapSqlParameterSources型態的陣列, 並且長度設為studentList的大小
        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[studentList.size()];

        for (int i = 0; i < studentList.size(); i++) {
            //取得list內的每一個student object
            Student student = studentList.get(i);

            //類似map的用法, 將每一個陣列中map的key value指定好
            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("studentName", student.getName());
            parameterSources[i].addValue("studentCourseList", student.getCourseList().toString());
            parameterSources[i].addValue("studentTodoList", student.getTodoList().toString());
        }

        KeyHolder[] keyHolder = new GeneratedKeyHolder[studentList.size()];


        // 一次將所有的student object insert到DB, 一次完成多筆ROW的INSERT
        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
        return ResponseEntity.status(201).body(studentList);
    }


    // jdbc delete
    @DeleteMapping("/sql/student/{studentId}")
    public ResponseEntity<?> delete(@PathVariable Integer studentId) {
        String sql = "DELETE FROM student WHERE id = :studentId";
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);

        namedParameterJdbcTemplate.update(sql, map);
        return new ResponseEntity("執行delete方法", HttpStatus.OK);
    }

    // 取得id > 此值的數據
    // 同時計算table的數量
    @GetMapping("sql/student/{studentId}")
    public ResponseEntity<?> select(@PathVariable Integer studentId) {
        // queryForObject只適合用在count(*)
        String countSql = "SELECT COUNT(1) FROM threetier";
        Map<String, Object> countMap = new HashMap<>();

        // 透過第三個參數將回傳的結果轉回Integer, 此方法會回傳object
        Integer count = namedParameterJdbcTemplate.queryForObject(countSql, countMap, Integer.class);
        System.out.println("threetier的數量有: " + count);

        // 欄位名稱和rowMapper需一致
        String sql = "SELECT id, name, courseList, todoList FROM threetier WHERE id = :studentId";

        // 創建一個空的map, 僅是為了放入query的參數
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);

        // threetierRowMapper將SQL object 轉為 java object 並回傳list
        List<Student> list = namedParameterJdbcTemplate.query(sql, map, new studentRowMapper());

        return ResponseEntity.status(200).body(list);
        // 若最後要回傳的方式如果要指定第一筆數據則必須判斷list是否有值, 否則會有outOfBound的錯誤
//        if(list.size() > 0) {
//            return ResponseEntity.status(200).body(list.get(0));
//        } else {
//            return null;
//        }
    }
}

