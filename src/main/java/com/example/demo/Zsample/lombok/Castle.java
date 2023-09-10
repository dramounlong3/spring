package com.example.demo.Zsample.lombok;

import lombok.Data;

// lombok的@Data = @Getter + @Setter, 讓Model內的程式碼不會一堆getter和setter, 也節省開發時間
@Data
public class Castle {
    Integer id;
    String name;
}
