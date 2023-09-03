package com.example.demo.service;

import com.example.demo.dao.ThreeTierStudentDao;
import com.example.demo.dao.ThreeTierStudentDaoImp;
import com.example.demo.model.ThreeTierStudent;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ThreeTierStudentServiceImpMockTest {

    @Autowired
    private ThreeTierStudentService threeTierStudentService;

    //正常情況下@MockBean 和 @SpyBean 不能針對同一種類型的Bean去做設定, 因為當SpyBean沒使用到時, 最終也是使用到@MockBean, 並不會使用到真實的bean

    // @MockNBean 設定一個假的Bean 取代掉原先會使用的Dao bean, 並自訂回傳值, 避免和其他依賴的資源掛勾太深
    // 整個bean都是假的
    @MockBean
    private ThreeTierStudentDao threeTierStudentDao;

    // 當只想要在有設定Mockito的才回傳假的值時, 沒有設定Mockito就預設使用原先的Bean內的方法, 則可使用 @SpyBean, 此Bean半真半假
    @SpyBean
    private ThreeTierStudentDao threeTierStudentDaoSpy;

    // 讓每個單元測試時, 都可以拿到Mockito回傳的值
    @BeforeEach
    public void BeforeEach() {
        // new一個自訂一的ThreeTierStudent Object
        ThreeTierStudent mockStudent = new ThreeTierStudent();
        mockStudent.setId(3);
        mockStudent.setName("Mock");
        List<ThreeTierStudent> mockStudentList = new ArrayList<>();
        mockStudentList.add(mockStudent);

        // Mockito 不能 mock以下三種類型的方法 1.static ; 2.private ; 3. final
        // 用Mockito模擬當Service執行, call dao時透過假的Bean dao回傳上方設定好的內容, Mockito找不到就會回傳null
        // getById的參數可放any取代掉實際值, 讓方法更彈性
        Mockito.when(threeTierStudentDao.getById(Mockito.any())).thenReturn(mockStudentList);
        // Mockito回傳的方式還有下面這種寫法
        // Mockito.doReturn(mockStudentList).when(threeTierStudentDao).getById(Mockito.any());

        // Mockito模擬丟出exception
        // 第1種寫法
        //Mockito.when(threeTierStudentDao.insertStudent(Mockito.any())).thenThrow(new RuntimeException());
        // 第2種寫法
        //Mockito.doThrow(new RuntimeException()).when(threeTierStudentDao).deleteById(Mockito.any());

        // Mockito驗證 方法的使用次數和順序
        // Mockito.verify(threeTierStudentDao, Mockito.times(5)).getById(Mockito.any());
    }

    @Test
    public void getById () {
        List<ThreeTierStudent> student = threeTierStudentService.getById(3);
        assertNotNull(student);
        assertEquals(3,student.get(0).getId());
        assertEquals("Mock", student.get(0).getName());
    }


    @Test
    public void spy1 () {
        // 可將insertStudent控制回傳假的內容
        Mockito.doReturn(99).when(threeTierStudentDaoSpy).insertStudent(Mockito.any());

        ThreeTierStudent spyStudent = new ThreeTierStudent();
        spyStudent.setName("Mock");
        List<String> courseList = new ArrayList<>();
        courseList.add("course1");
        courseList.add("course2");
        spyStudent.setCourseList(courseList);

        Integer studentId = threeTierStudentService.insertStudent(spyStudent);
        assertEquals(99, studentId);
    }

    @Test
    public void spy2 () {
        // 因為這邊沒特別使用threeTierStudentDaoSpy設定假的內容, 故變成使用到BeforeEach內的MockBean內容
        List<ThreeTierStudent> studentList = threeTierStudentService.getById(80);
        assertEquals(3, studentList.get(0).getId());
    }


}