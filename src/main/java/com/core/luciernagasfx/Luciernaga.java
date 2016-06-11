/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.luciernagasfx;

import java.util.Random;

/**
 *
 * @author pichon
 */
public class Luciernaga {
    public static int numLuciernagasAgrupadas = 0;
    private char[] palabra1, palabra2, palabra3, variables;
    private Random numAleatorio = new Random();
    private int[] numeros= {0,1,2,3,4,5,6,7,8,9};
    private int[] mapeo;
    private int valPalabra1;
    private int valPalabra2;
    private int valPalabra3;
    private int longP1, longP2, longP3, longVar;

    public static int getNumLuciernagasAgrupadas() {
        return numLuciernagasAgrupadas;
    }

    public static void setNumLuciernagasAgrupadas(int numLuciernagasAgrupadas) {
        Luciernaga.numLuciernagasAgrupadas = numLuciernagasAgrupadas;
    }

    public char[] getPalabra1() {
        return palabra1;
    }

    public void setPalabra1(char[] palabra1) {
        this.palabra1 = palabra1;
    }

    public char[] getPalabra2() {
        return palabra2;
    }

    public void setPalabra2(char[] palabra2) {
        this.palabra2 = palabra2;
    }

    public char[] getPalabra3() {
        return palabra3;
    }

    public void setPalabra3(char[] palabra3) {
        this.palabra3 = palabra3;
    }

    public char[] getVariables() {
        return variables;
    }

    public void setVariables(char[] variables) {
        this.variables = variables;
    }

    public Random getNumAleatorio() {
        return numAleatorio;
    }

    public void setNumAleatorio(Random numAleatorio) {
        this.numAleatorio = numAleatorio;
    }

    public int[] getNumeros() {
        return numeros;
    }

    public void setNumeros(int[] numeros) {
        this.numeros = numeros;
    }

    public int[] getMapeo() {
        return mapeo;
    }

    public void setMapeo(int[] mapeo) {
        this.mapeo = mapeo;
    }

    public int getLongP1() {
        return longP1;
    }

    public void setLongP1(int longP1) {
        this.longP1 = longP1;
    }

    public int getLongP2() {
        return longP2;
    }

    public void setLongP2(int longP2) {
        this.longP2 = longP2;
    }

    public int getLongP3() {
        return longP3;
    }

    public void setLongP3(int longP3) {
        this.longP3 = longP3;
    }

    public int getLongVar() {
        return longVar;
    }

    public void setLongVar(int longVar) {
        this.longVar = longVar;
    }

    public int getValPalabra1() {
        return valPalabra1;
    }

    public int getValPalabra2() {
        return valPalabra2;
    }

    public int getValPalabra3() {
        return valPalabra3;
    }

    public void setValPalabra1(int valPalabra1) {
        this.valPalabra1 = valPalabra1;
    }

    public void setValPalabra2(int valPalabra2) {
        this.valPalabra2 = valPalabra2;
    }

    public void setValPalabra3(int valPalabra3) {
        this.valPalabra3 = valPalabra3;
    }
    
    Luciernaga(char[] p1, char[] p2, char[] p3, char[] var){
        palabra1 = p1;
        palabra2 = p2;
        palabra3 = p3;
        variables = var;
        longP1 = palabra1.length;
        longP2 = palabra2.length;
        longP3 = palabra3.length;
        longVar = variables.length;
    }
    
    public void mapeoLuciernaga(int cantVar){
        /*mapeo = new int[cantVar];
        for(int i = 0; i < cantVar; i++){
            mapeo[i] = numAleatorio.nextInt(10); 
        }
        encontrarValoresPalabras();*/
        cantVar = 10;
        int k=cantVar;
        int[] resultado=new int[cantVar];
        int res;
        
        for(int i=0;i<cantVar;i++){
            res=numAleatorio.nextInt(k);            
            resultado[i]=numeros[res];
            numeros[res]=numeros[k-1];
            k--;
            
        }
        mapeo = resultado;
        encontrarValoresPalabras();
    }
    
    public void encontrarValoresPalabras(){
            int peso;
            valPalabra1 =0;
            valPalabra2 =0;
            valPalabra3 =0;            
            
            peso = longP1 - 1;
            for(int i = 0; i < longP1; i++){
                for(int j = 0; j < longVar; j++){
                    if(palabra1[i] == variables[j]){
                        valPalabra1 = (int) (valPalabra1 + mapeo[j]*Math.pow(10, peso));
                        break;
                    } 
                }
                peso = peso - 1;
            }
            
            peso = longP2 - 1;
            for(int i = 0; i < longP2; i++){
                for(int j = 0; j < longVar; j++){
                    if(palabra2[i] == variables[j]){
                        valPalabra2 = (int) (valPalabra2 + mapeo[j]*Math.pow(10, peso));
                        break;
                    } 
                }
                peso = peso - 1;
            }
            
            peso = longP3 - 1;
            for(int i = 0; i < longP3; i++){
                for(int j = 0; j < longVar; j++){
                    if(palabra3[i] == variables[j]){
                        valPalabra3 = (int) (valPalabra3 + mapeo[j]*Math.pow(10, peso));
                        break;
                    } 
                }
                peso = peso - 1;
            }
            
    }
    
    public int obtenerValorFuncion(String operacion ){
        int resultado = 0, resultadoInv, palabra3Inv, funcion = 0;
        
        if(operacion.equals("+")){
            resultado = valPalabra1 + valPalabra2; 
        }
        if(operacion.equals("-")){
            resultado = valPalabra1 - valPalabra2; 
        }
        resultadoInv = invertir(resultado);
        palabra3Inv = invertir(valPalabra3);
        
        if(longP1 < longP3){
            if(longP2 < longP3){
                if(longP1 < longP2){
                    resultadoInv = (int) (resultadoInv * Math.pow(10,(longP3-longP2-1)));
                } else {
                    resultadoInv = (int) (resultadoInv * Math.pow(10,(longP3-longP1-1)));
                }
            }
        }
        
        if(resultadoInv > palabra3Inv){
        funcion = resultadoInv - palabra3Inv;
        }
        if(resultadoInv < palabra3Inv){
        funcion = palabra3Inv - resultadoInv;
        }
        if(resultadoInv == palabra3Inv){
        funcion = 0;
        }
        return funcion;
    }
    
    int invertir(int numero){
         
        int num_inv , div_entera , resto_div;  
        num_inv = 0; 
        div_entera = numero; 
        while (div_entera != 0) { 
            resto_div = div_entera % 10; 
            div_entera = div_entera / 10; 
            num_inv = num_inv * 10 + resto_div; 
        }
        return num_inv;
    }
    
    
}

