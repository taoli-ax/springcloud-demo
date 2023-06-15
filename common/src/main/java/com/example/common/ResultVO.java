package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultVO implements Serializable {
    private Integer status;
    private String message;
    private  Object  returnObj;
    public ResultVO(Integer status,String message){
        this.message=message;
        this.status=status;
    }
}
