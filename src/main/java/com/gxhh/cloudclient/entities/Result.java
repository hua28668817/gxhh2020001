package com.gxhh.cloudclient.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Result {

    boolean result;
    int status;
    Map<String,String> retInfo;
}
