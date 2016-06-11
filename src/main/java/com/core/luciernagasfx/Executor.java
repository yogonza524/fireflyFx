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
public class Executor implements Runnable{

    private String operation;
    private String op1;
    private String op2;
    private String op3;
    private int firefliesNumber;
    private int iterationNumber;
    private Programa p;

    public Programa getP() {
        return p;
    }

    public void setP(Programa p) {
        this.p = p;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public int getFirefliesNumber() {
        return firefliesNumber;
    }

    public void setFirefliesNumber(int firefliesNumber) {
        this.firefliesNumber = firefliesNumber;
    }

    public int getIterationNumber() {
        return iterationNumber;
    }

    public void setIterationNumber(int iterationNumber) {
        this.iterationNumber = iterationNumber;
    }

    
    
    @Override
    public void run() {
        p = new Programa();
        p.setOperacion(operation);
        p.setPalabra1(op1.toCharArray());
        p.setPalabra2(op2.toCharArray());
        p.setPalabra3(op3.toCharArray());
        p.setCantLuciernagas(firefliesNumber);
        p.setIteracionesEjecucion(iterationNumber);
    }
    
}
