/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.luciernagasfx;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author pichon
 */
public class Programa {
    
    private char[] palabra1, palabra2, palabra3;
    private char[] variables;
    private int longVariables = 0;
    private int intentos = 15;
    private String operacion;
    private int cantLuciernagas = 10;
    private Luciernaga[] luciernagas;
    private int iteracionesEjecucion = 10;
    private int iteracionesAcercamiento = 8;
    private Luciernaga luciernagaAproximada;
    private Luciernaga luciernagaSolucion;
    
    private String promDistancia = "";
    private String promBrillo = "";
    private String bestBrillo = "";
    private String distanciaToBest = "";
    
    private List<Luciernaga[]> generaciones;
    private List<Luciernaga> mejores;

    public List<Luciernaga> getMejores() {
        return mejores;
    }

    public void setMejores(List<Luciernaga> mejores) {
        this.mejores = mejores;
    }

    public List<Luciernaga[]> getGeneraciones() {
        return generaciones;
    }

    public void setGeneraciones(List<Luciernaga[]> generaciones) {
        this.generaciones = generaciones;
    }

    public Programa() {
        generaciones = new ArrayList<>();
    }

    public char[] getVariables() {
        return variables;
    }

    public void setVariables(char[] variables) {
        this.variables = variables;
    }

    public int getLongVariables() {
        return longVariables;
    }

    public void setLongVariables(int longVariables) {
        this.longVariables = longVariables;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public int getCantLuciernagas() {
        return cantLuciernagas;
    }

    public void setCantLuciernagas(int cantLuciernagas) {
        this.cantLuciernagas = cantLuciernagas;
    }

    public Luciernaga[] getLuciernagas() {
        return luciernagas;
    }

    public void setLuciernagas(Luciernaga[] luciernagas) {
        this.luciernagas = luciernagas;
    }

    public int getIteracionesAcercamiento() {
        return iteracionesAcercamiento;
    }

    public void setIteracionesAcercamiento(int iteracionesAcercamiento) {
        this.iteracionesAcercamiento = iteracionesAcercamiento;
    }

    public String getPromDistancia() {
        return promDistancia;
    }

    public void setPromDistancia(String promDistancia) {
        this.promDistancia = promDistancia;
    }

    public String getPromBrillo() {
        return promBrillo;
    }

    public void setPromBrillo(String promBrillo) {
        this.promBrillo = promBrillo;
    }

    public String getBestBrillo() {
        return bestBrillo;
    }

    public void setBestBrillo(String bestBrillo) {
        this.bestBrillo = bestBrillo;
    }

    public String getDistanciaToBest() {
        return distanciaToBest;
    }

    public void setDistanciaToBest(String distanciaToBest) {
        this.distanciaToBest = distanciaToBest;
    }
    
    private static final Logger LOG = Logger.getLogger(Programa.class.getName());

    public int getIteracionesEjecucion() {
        return iteracionesEjecucion;
    }

    public void setIteracionesEjecucion(int iteracionesEjecucion) {
        this.iteracionesEjecucion = iteracionesEjecucion;
    }
    
    public void setLongVariable(int longV){
        this.longVariables = longV;
    }
    
    public void setLuciernagaAproximada(Luciernaga luciernagaAproximada) {
        this.luciernagaAproximada = luciernagaAproximada;
    }

    public void setLuciernagaSolucion(Luciernaga luciernagaSolucion) {
        this.luciernagaSolucion = luciernagaSolucion;
    }

    public Luciernaga getLuciernagaAproximada() {
        return luciernagaAproximada;
    }

    public Luciernaga getLuciernagaSolucion() {
        return luciernagaSolucion;
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
    
    
    public boolean programa() throws IOException, URISyntaxException  {
        boolean continuar;
        boolean bandera = false;
        luciernagas = new Luciernaga[cantLuciernagas];
        int iteraciones, iteracionesGlobal;
        double distanciaAux;
        continuar = true;
        luciernagaSolucion = null;
        iteracionesGlobal = 0;
        inicializarBusqueda();
        luciernagaAproximada = null;
        Luciernaga mejorluciernaga = null;
        mejores = new ArrayList<>();
        
        while ((continuar) && (iteracionesGlobal < iteracionesEjecucion)){
            this.generaciones.add(luciernagas); //Salvo las generaciones para graficar luego
            luciernagaSolucion = null;
            iteraciones = 0;
            generarLuciernagas(mejorluciernaga);
//           System.out.print("promedio distancia: ");
//           System.out.println(promedioDistancia());
//           System.out.print("promedio brillo: ");
//           System.out.println(promedioBrillo());
            
            for (int i = 0; i < iteracionesAcercamiento; i++) {
                distanciaAux = promedioDistancia();
                buscarSolucion();
//                mostrarBrillosLuciernagas();
//                mostrarLuciernagas();
                
                promDistancia = promDistancia + promedioDistancia() + "\n";
                promBrillo = promBrillo + promedioBrillo() + "\n";
                bestBrillo = bestBrillo + mejorBrillo().obtenerValorFuncion(operacion) + "\n";
                distanciaToBest = distanciaToBest + distanciaMejorLuciernaga(mejorBrillo()) + "\n";
                
                if (promedioDistancia() == distanciaAux) {
                    break;
                }
                luciernagaSolucion = verificarLuciernagas();
                
                if ( luciernagaSolucion != null){
                    Util.info("**********Solucion encontrada**********");
                    Util.info(Arrays.toString(variables));
                    Util.info(Arrays.toString(luciernagaSolucion.getMapeo()));
                    Util.info(luciernagaSolucion.getValPalabra1());
                    Util.info(luciernagaSolucion.getValPalabra2());
                    Util.info(luciernagaSolucion.getValPalabra3());
                    continuar = false;
                    bandera = true;
                    break;
                }
                iteraciones++;
            }
            mejorluciernaga = mejorBrillo();
            mejores.add(mejorBrillo());
            iteracionesGlobal++;

        }
        
        if(luciernagaSolucion == null){
            Util.info("No se ha encontrado solucion");
            Util.info("Una solucion aproximada seria: ");
            Util.info(Arrays.toString(variables));
            Util.info(Arrays.toString(mejorluciernaga.getMapeo()));
            Util.info(mejorluciernaga.getValPalabra1());
            Util.info(mejorluciernaga.getValPalabra2());
            Util.info(mejorluciernaga.getValPalabra3());
            Util.info(" - - - - - - - - - - - - - - -");   
        }
        
        Util.info("Promedio distancia:\n" + promDistancia);
        Util.info("Promedio brillo:\n" + promBrillo);
        Util.info("Brillo mejor:\n" + bestBrillo);
        Util.info("Distancia a mejor:\n" + distanciaToBest);
        
//        graficar(promDistancia);
//        graficar(promBrillo);
//        graficar(bestBrillo);
//        graficar(distanciaToBest);

        pasarGarbageCollector();
        return bandera;
    }
    
    public void inicializarBusqueda(){
        obtenerVariables();
        Util.info("Variables guardadas");
        Util.info(Arrays.toString(variables));

    }
    
    public void obtenerVariables(){
        obtenerVariablePalabra(palabra1);
        obtenerVariablePalabra(palabra2);
        obtenerVariablePalabra(palabra3);
    }
    
    public void obtenerVariablePalabra(char[] palabra){
        int longitud = palabra.length ;
        for(int i = 0; i < longitud; i++){
            if(noExisteVariable(palabra[i])){
                addVariable(palabra[i]);
            } 
            
        }
    }
    
    public boolean noExisteVariable(char caracter){
        if(longVariables == 0){
            return true;
        }
        for(int i = 0; i < longVariables; i++){
            if(caracter == variables[i]){
                return false;
            }
        }
        return true;
    }
    
    public void addVariable(char caracter){
        int longitud = longVariables + 1;
        char[] newVariables = new char[longitud];
        for(int i = 0; i < longVariables; i++){
            newVariables[i] = variables[i];
        }
        newVariables[longitud - 1] = caracter;
        variables = newVariables;
        longVariables = longVariables + 1;
    }
    
    public void generarLuciernagas( Luciernaga mejorAnterior ){
         for(int i = 0; i < cantLuciernagas; i++){
            Luciernaga luciernagaX = new Luciernaga(palabra1, palabra2, palabra3, variables);
            luciernagaX.mapeoLuciernaga(longVariables);
            luciernagaX.encontrarValoresPalabras();
            luciernagas[i] = luciernagaX;
        }
        if (mejorAnterior != null) {
            luciernagas[1] = mejorAnterior;    
        }
    }
    
    public int diferencia(int a, int b){
        int resultado = 0;
        if(a>b){
            resultado = a-b;
        }
        if(a<b){
            resultado = b-a;
        }
        return resultado;
    }
    
    public int[] acercarValor(int[] primera, int[] segunda){
          for (int i = 0; i < primera.length -2; i++) {
              int dif = diferencia(primera[i], segunda[i]);
              int difNext = diferencia(primera[i], segunda[i+1]);
              if (difNext < dif) {
                  int aux = segunda[i];
                  segunda[i] = segunda[i+1];
                  segunda[i+1] = aux;
              }
          }
          int dif = diferencia(primera[primera.length-1], segunda[segunda.length - 1]);
          int difNext = diferencia(primera[primera.length - 1], segunda[0]);
          if (difNext < dif) {
                  int aux = segunda[segunda.length - 1];
                  segunda[segunda.length - 1] = segunda[0];
                  segunda[0] = aux;
              }
          return segunda;
      }
    
    public int[] acercarValor1(int[] primera, int[] segunda) {
//        boolean acercamiento = false;
        for (int i = 0; i < 10; i++) {
            int dif = diferencia(primera[i], segunda[i]);
            if(dif != 0){
                for (int j = 0; j < 10; j++) {
                    if (j!=i) {
                        int difNext = diferencia(primera[i], segunda[j]);
                        if (difNext == (dif - 1) ) {
                            int aux = segunda[i];
                            segunda[i] = segunda[j];
                            segunda[j] = aux;
//                            acercamiento = true;
                            break;
                        }
                    }
                }
            }
        }
        return segunda;
     }
    
    public int[] acercarValor2(int[] primera, int[] segunda){
        int[] vectorSiNO = new int[10];
        for (int i = 0; i < 10; i++) {
            if(primera[i] != segunda[i]){
                if(primera[i] < segunda[i]){
                    segunda[i] = segunda[i] - 1;
                }else{
                    segunda[i] = segunda[i] + 1;
                }
            }
            if (primera[i] == segunda[i]) {
                vectorSiNO[i] = 1;
            }
        }
        return segunda;
    }
    
    //Este metodo deberia disminuir la distancia entre todos 
    public int[] acercarValorUltimo(int[] primera, int[] segunda){
        int posicion, aux, numero = 0, num, iteracion;
        int[] lucAux;
        boolean bandera = true;
                 
        double distanciaAux, distancia;
        distancia = obtenerDistancia(primera, segunda);
//         System.out.println(distancia);
//         System.out.println(Arrays.toString(primera));
//         System.out.println(Arrays.toString(segunda));
         
            for (int i = 0; i < longVariables; i++) {
                iteracion = 0;
                lucAux = segunda;
                if(segunda[i] != primera[i]){
                    if(segunda[i] < primera[i]){
                        numero = 1;               
                    }
                    if(segunda[i] > primera[i]){
                        numero = -1; 
                    }
                    num = segunda[i] + numero;
                    while( (bandera) && (iteracion < intentos)){
                        posicion = buscarPos(num, segunda);
                        aux = segunda[i];
                        segunda[i] = segunda[posicion];
                        segunda[posicion] = aux;
 
                        distanciaAux = obtenerDistancia(primera, segunda);
                 
                        if(distancia < distanciaAux){
                            segunda = lucAux;
                            num = num + numero;
                        }
                        if(distancia > distanciaAux){
                            distancia = distanciaAux;
                            bandera = false;
                        }
                        iteracion++;
                    }
                    bandera = true;
                }
             }
         return segunda;
    }
    
    public void buscarSolucion(){
        double funcionLuciernagaI, funcionLuciernagaJ;
        
            for( int i = 0;  i < cantLuciernagas; i++){
                for( int j = 0;  j < cantLuciernagas; j++){
                    if( i != j ){
                        funcionLuciernagaI = luciernagas[i].obtenerValorFuncion(operacion);
                        funcionLuciernagaJ = luciernagas[j].obtenerValorFuncion(operacion);
                        
                        //recuerde que el valor brillo es mejor cuanto mas pequeño se hace
                        if (funcionLuciernagaI > funcionLuciernagaJ){
                            luciernagas[i].setMapeo(acercarValorUltimo(luciernagas[j].getMapeo(),luciernagas[i].getMapeo()));
                            luciernagas[i].encontrarValoresPalabras();
                        }
                    }
                }
            }
    
    }
    
    public Luciernaga verificarLuciernagas(){
        int op, resultado;
        for( int i = 0;  i < cantLuciernagas; i++){
            if (this.operacion.equals("+")) {
                op = luciernagas[i].getValPalabra1() + luciernagas[i].getValPalabra2();
            }
            else{
                op = luciernagas[i].getValPalabra1() - luciernagas[i].getValPalabra2();
            }
            resultado = luciernagas[i].getValPalabra3();
            if(op == resultado){
                    return luciernagas[i];
            }
        }
        return null;
    }
    
    public void pasarGarbageCollector(){
        Runtime garbage = Runtime.getRuntime();
        garbage.gc();
    }

    public void mostrarLuciernagas() {
        for (int i = 0; i < cantLuciernagas; i++) {
            Util.info(Arrays.toString(luciernagas[i].getMapeo()));
        }
    }

    public double promedioBrillo() {
        int acum = 0;
        double prom;
        for (int i = 0; i < cantLuciernagas; i++) {
            acum += luciernagas[i].obtenerValorFuncion("+");
        }
        prom = acum / cantLuciernagas;
        return prom;
    }

    public double promedioDistancia() {
        double distancia = 0;
        for (int i = 0; i < cantLuciernagas; i++) {
            for (int j = 0; j < cantLuciernagas; j++) {
                if (i!=j) {
                    distancia = distancia + obtenerDistancia(luciernagas[i].getMapeo(), luciernagas[j].getMapeo());
                }
            }
        }
        distancia = distancia/cantLuciernagas;
        return distancia;
    }
    
    public Luciernaga mejorBrillo() {
        Luciernaga mejor = luciernagas[0];
        for (int i = 1; i < cantLuciernagas; i++) {
            //la mejor es la mas pequeña
            if (mejor.obtenerValorFuncion(operacion) > luciernagas[i].obtenerValorFuncion(operacion)) {
                mejor = luciernagas[i];
            }
        }
        return mejor;
    }
    
    public double distanciaMejorLuciernaga( Luciernaga mejor) {
        double distancia = 0;
        for (int j = 0; j < cantLuciernagas; j++) {
            distancia = distancia + obtenerDistancia(mejor.getMapeo(), luciernagas[j].getMapeo());
        }
        distancia = distancia/cantLuciernagas;
        return distancia;
    }

    public double obtenerDistancia(int[] luc1, int[] luc2) {
        double distanciaLuciernagas = 0;
        
        for (int i = 0; i < longVariables; i++) { //ver bien si es logvariable o 10
            distanciaLuciernagas = distanciaLuciernagas + Math.pow(diferencia(luc1[i], luc2[i]),2);
        }
        distanciaLuciernagas = Math.sqrt(distanciaLuciernagas);
        return distanciaLuciernagas;
    }
    
    private int buscarPos(int num, int[] luc2) {
        int pos = 0;
        for (int j = 0; j < 10; j++) {
            if(luc2[j] == num){
                pos = j;
                break;
            }
        }
        return pos;
    }
    
    public void mostrarBrillosLuciernagas() {
        for (int i = 0; i < cantLuciernagas; i++) {
            Util.info(luciernagas[i].obtenerValorFuncion(operacion));
        }
    }      
    
    public void mostrarSolución(){
        if (this.luciernagaSolucion != null) {
            System.out.println(String.valueOf(palabra1) + "(" + this.luciernagaSolucion.getValPalabra1() + ")");
            System.out.println(String.valueOf(palabra2) + "(" + this.luciernagaSolucion.getValPalabra2() + ")");
            System.out.println(String.valueOf(palabra3) + "(" + this.luciernagaSolucion.getValPalabra3() + ")");
        }
        else{
            System.out.println("No se ha encontrado solución");
        }
    }
}