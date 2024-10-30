package com.gmaurya.ums.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StreamDto {

    private String streamId; // varchar
    private String streamName;
    private int duration;
}
