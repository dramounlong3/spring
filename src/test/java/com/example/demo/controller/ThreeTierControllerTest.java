package com.example.demo.controller;

import com.example.demo.model.ThreeTierStudent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// 測試controller, 使用MockMvc模擬前端的http請求
// 整體上約有三個層次的步驟 1.設定http要呼叫的參數 2.執行http請求 3.藉由回傳的內容來比對是否是預期的結果
@SpringBootTest
@AutoConfigureMockMvc
class ThreeTierControllerTest {

    //注入mockmvc
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void select() throws Exception {
        // 1.設定http要呼叫的參數
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/three/students/{studentId}",1) // get方法及url
                .header("myHeader", "testHeader")          // 設定header
                .param("myQueryParam", "testQueryParam");  // 設定query parameter, 即 ? 後面的參數
        // 2.執行http請求
        // MvcResult mvcResult 不一定要宣告, 通常在比較複雜的test case才會宣告變數, 並透過最後的.andReturn來取得回傳的body內容, 並再繼續處理
        MvcResult  mvcResult = mockMvc.perform(requestBuilder) //perform會丟出exception, 故此單元測試需throws exception
        // 3. 藉由回傳的內容來比對是否是預期的結果
                .andDo(print())
                .andExpect(status().is(200))  //Builder設計模式(建造者模式), 可以無限的點下去
                // .andExpect(jsonPath("$[0].id", equals(1))) //課程的寫法會造成jsonPath回傳的型態不正確, 故改為下面的寫法, 原因未知, 此參考: https://stackoverflow.com/questions/51937581/jsonpathresultmatchers-cannot-be-applied-to-resultmatcher
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name", notNullValue()))
                // .andExpect(header().stringValues("myHeader","testHeader")) // 這一段不會通過, 因為這裡的header只會取得response的header, myHeader是request的header
                .andExpect(header().stringValues("Content-Type","application/json"))
                .andExpect(header().stringValues("returnHeader","testReturnHeader"))
                // 關於jsonPath的字串寫法可以到 https://jsonpath.com/ 測試
                .andReturn();

        // 藉由andReturn的內容先取得body string
        // 再由objectMapper(此套件有被spring boot start web引入 來源是:jackson lib), 透過readValue或writeValueAsString 針對 兩個不同型態的物件做型態轉換
        String body = mvcResult.getResponse().getContentAsString();
        //正常情況若是返回單純的自定義物件, 應只需要這樣定義即可, 但因為返回的剛好是ThreeTierStudent的Array所以要寫成此自定義class型態的陣列
        // ThreeTierStudent result =  objectMapper.readValue(body, ThreeTierStudent.class);
        ThreeTierStudent[] result =  objectMapper.readValue(body, ThreeTierStudent[].class);
        System.out.println("MvcReult= " + result[0].getName());

        // 額外測試取得header
        List<String> header = mvcResult.getResponse().getHeaders("returnHeader");
        System.out.println("returnHeader= " + header);
    }

    @Test
    @Transactional
    void insert() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/three/students")
                .contentType(MediaType.APPLICATION_JSON) //表request body 是JSON格式, 一定要寫
                // content 先寫最外層的"", 然後就其他地方複製來的json, 貼上後就會自動代入\n...等
                .content("{\n" +
                        "  \"name\":\"Hank\",\n" +
                        "  \"courseList\":[\"course11\",\"course22\"],\n" +
                        "  \"todoList\":[\"todo11\",\"todo22\"],\n" +
                        "  \"score\":99.9,\n" +
                        "  \"graduate\":true,\n" +
                        "  \"createdate\":\"2020-02-20 20:20:20\"\n" +
                        "}");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));
    }

}