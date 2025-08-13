package com.tech.commons;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataContainer {
    private Object data;
    private String status;
    private Object errorObject;
    private String msg;
    private String msgToCheck;
}
