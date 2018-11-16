package com.ibm.agenda.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Resposta {
    private int code;
    private String error;
    private Object body;
}
