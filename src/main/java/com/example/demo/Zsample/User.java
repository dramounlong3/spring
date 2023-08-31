package com.example.demo.Zsample;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


// @JsonInclude 可使objectMapper 由字串和object 互轉時， 忽略掉 沒有設定 導致變成NULL的值
// @JsonIgnoreProperties: 若要轉換的json字串 出現 不存在於object的key時，可以設定此註釋忽略該key
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    Integer id;
    String name;

    // @JsonProperty 可以將object 在轉換後 變成 contact_email的 json key
    // 也可以將json 在轉換後 變成 contactEmail的 object variable
    @JsonProperty("contact_email")
    String contactEmail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
