package com.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;   
import java.util.Optional;


public class ControllerFather {
    public ControllerFather(){}

    private Ecommerce ecommerce;
    
    public void setEcommerce(Ecommerce ecommerce){ this.ecommerce = ecommerce; }
    public Ecommerce getEcommerce(){ return this.ecommerce; }
}
