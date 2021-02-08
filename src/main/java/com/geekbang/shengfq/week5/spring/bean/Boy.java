package com.geekbang.shengfq.week5.spring.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Bean;

@Data
@NoArgsConstructor
@ToString
public class Boy {
    private int age;
    private String name;
}
