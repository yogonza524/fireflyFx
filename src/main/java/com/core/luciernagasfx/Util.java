/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.luciernagasfx;

/**
 *
 * @author Gonza
 */
public class Util {
    
    private static boolean enableInfo;

    public static boolean isEnableInfo() {
        return enableInfo;
    }

    public static void setEnableInfo(boolean enableInfo) {
        Util.enableInfo = enableInfo;
    }
    
    public static void info(Object value){
        if (Util.enableInfo) {
            System.out.println(value);
        }
    }
    
}
