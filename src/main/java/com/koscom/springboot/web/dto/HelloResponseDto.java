package com.koscom.springboot.web.dto;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Getter;


@Getter
@RequiredArgsConstructor
@ToString
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
