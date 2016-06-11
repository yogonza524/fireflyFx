/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.luciernagasfx;

import com.core.util.Columns;
import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.glass.ui.Application;
import com.sun.javafx.application.HostServicesDelegate;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.joda.time.DateTime;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tooltip;
import javax.swing.event.HyperlinkEvent;

/**
 *
 * @author Gonza
 */
public class FireflyController implements Initializable {
    
    @FXML private Slider fireflies_slider;
    @FXML private Slider iterations_slider;
    @FXML private Label fireflies_label;
    @FXML private Label iterations_label;
    @FXML private Button new_button;
    @FXML private Button run_button;
    @FXML private TextField first_operatoor_text;
    @FXML private TextField second_operatoor_text;
    @FXML private TextField result_text;
    @FXML private ComboBox operation_combo;
    @FXML private TextArea result_textarea;
    @FXML private Hyperlink firefly_link;
    @FXML private LineChart<Number,Number> best_line_chart;
    
    private String operation;
    private String first;
    private String second;
    private String result;
    private int fireflies;
    private int iterations;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        init();
    }    
    
    private void init(){
        iterations = (int)iterations_slider.getValue();
        fireflies = (int)fireflies_slider.getValue();
        
        firefly_link.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    try {
                        Desktop.getDesktop().browse(new URI(firefly_link.getText()));
                    } catch (IOException ex) {
                        Logger.getLogger(FireflyController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(FireflyController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        fireflies_label.setText((int)fireflies_slider.getMin() + "");
        iterations_label.setText((int)iterations_slider.getMin() + "");
        fireflies_slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) {
                fireflies_label.setText(newVal.intValue() + "");
                fireflies = newVal.intValue();
            }    
        });
        iterations_slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) {
                iterations_label.setText(newVal.intValue() + "");
                iterations = newVal.intValue();
            }
            
        });
        new_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                first_operatoor_text.setText("");
                second_operatoor_text.setText("");
                result_text.setText("");
                result_textarea.setText("");
                first_operatoor_text.requestFocus();
                fireflies_slider.setValue(fireflies_slider.getMin());
                iterations_slider.setValue(iterations_slider.getMin());
                best_line_chart.getData().clear();
            }
        });
        operation_combo.getSelectionModel().selectedItemProperty().addListener(new  
            ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                if (newValue.equals("Addition (+)")) {
                    operation = "+";
                }
                else{
                    operation = "-";
                }
                System.out.println("Operacion seleccionada: " + operation);
            } 
        });
        run_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (first_operatoor_text.getText().isEmpty()) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Look, you must put a first operator");
                    alert.setContentText("This field is required, please try again");

                    alert.showAndWait();
                    first_operatoor_text.requestFocus();
                }
                else{
                    if (second_operatoor_text.getText().isEmpty()) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setHeaderText("You must put a second operator");
                        alert.setContentText("This field is required, please try again");

                        alert.showAndWait();
                        second_operatoor_text.requestFocus();
                    }
                    else{
                        if (result_text.getText().isEmpty()) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Error Dialog");
                            alert.setHeaderText("The result is important");
                            alert.setContentText("This field is required, please try again");

                            alert.showAndWait();
                            result_text.requestFocus();
                        }
                        else{
                            if (operation == null) {
                                Alert alert = new Alert(AlertType.ERROR);
                                alert.setTitle("Error Dialog");
                                alert.setHeaderText("Operation not selected");
                                alert.setContentText("Select an operation, please try again");

                                alert.showAndWait();
                            }
                            else{
                                first = first_operatoor_text.getText().toUpperCase();
                                second = second_operatoor_text.getText().toUpperCase();
                                result = result_text.getText().toUpperCase();
                                //Process the data
                                runFirefly(first, second, result, operation, fireflies, iterations);
                            }
                        }
                    }
                }
            }
        });
    }
    
    private void runFirefly(final String op1, final String op2, final String op3, final String operation, final int firefliesNumber, final int iterationNumber){
        Service<Void> service = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {           
                @Override
                protected Void call() throws Exception {
                    //Background work                       
                    final CountDownLatch latch = new CountDownLatch(1);
                    Platform.runLater(new Runnable() {                          
                        @Override
                        public void run() {
                            try{
                                DateTime begin = new DateTime();
                                
                                //FX Stuff done here
                                Programa p = new Programa();
                                p.setOperacion(operation);
                                p.setPalabra1(op1.toCharArray());
                                p.setPalabra2(op2.toCharArray());
                                p.setPalabra3(op3.toCharArray());
                                p.setCantLuciernagas(firefliesNumber);
                                p.setIteracionesEjecucion(iterationNumber);
                                
                                int i = 0;
                                boolean solucion = false;
                                
                                try {
                                    while(p.getLuciernagaSolucion() == null && i < iterationNumber){
                                        p.programa();
                                        i++;
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    Alert alert = new Alert(AlertType.ERROR);
                                    alert.setTitle("Too many values to search");
                                    alert.setHeaderText("Look, you just can put 10 distincts values");
                                    alert.setContentText("Try again changing your inputs words");

                                    alert.showAndWait();
                                }
                                
                                DateTime end = new DateTime();
                                
                                if (p.getLuciernagaSolucion() != null) {
                                    
                                    Formatter fmt = new Formatter();
                                    fmt.format("%1$-30.30s  %2$30s%n", "Operation: ",operation_combo.getSelectionModel().getSelectedItem().toString());
                                    fmt.format("%1$-30.30s  %2$30s%n", "First operator: ", p.getLuciernagaSolucion().getValPalabra1() + " (" + op1 + ")");
                                    fmt.format("%1$-30.30s  %2$30s%n", "Second operator: ", p.getLuciernagaSolucion().getValPalabra2() + " (" + op2 + ")");
                                    fmt.format("%1$-30.30s  %2$30s%n", "Result: ", p.getLuciernagaSolucion().getValPalabra3() + " (" + op3 + ")");
                                    fmt.format("%1$-30.30s  %2$30s%n", "Solution found at iteration: ", p.getMejores().size() - 1);
                                    fmt.format("%1$-30.30s%n", "--------------------------------------------------------------");
                                    fmt.format("%1$-30.30s  %2$30s%n", "Time to solve: ", (end.getMillis() - begin.getMillis()) + "ms");
                                    //Show results into textarea
                                    result_textarea.setText(fmt.toString());
                                    //Show grapfics into charts
                                    plotCharts(p);
                                }
                                else{
                                    result_textarea.setText("Solution not found \nTime searching: " + (end.getMillis() - begin.getMillis()) + "ms");
                                }
                                
                            } catch (IOException ex) {
                                Logger.getLogger(FireflyController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (URISyntaxException ex) {
                                Logger.getLogger(FireflyController.class.getName()).log(Level.SEVERE, null, ex);
                            }finally{
                                latch.countDown();
                            }
                        }
                    });
                    latch.await();                      
                    //Keep with the background work
                    return null;
                }
            };
        }
    };
    service.start();
    }

    private void plotCharts(final Programa p){
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Population");
        //creating the chart
        ObservableList<XYChart.Series<Number, Number>> lineChartData = FXCollections.observableArrayList();
                
        best_line_chart.setTitle("Best firefly");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Population");
        Collections.sort(p.getMejores(), new Comparator<Luciernaga>() {
            @Override
            public int compare(Luciernaga o1, Luciernaga o2) {
                return o2.obtenerValorFuncion(p.getOperacion()) - o1.obtenerValorFuncion(p.getOperacion());
            }
            
        });
        //populating the series with data
        for(int i = 0; i < p.getMejores().size(); i++){
            series.getData().add(new XYChart.Data(i, p.getMejores().get(i).obtenerValorFuncion(p.getOperacion())));
        }
        lineChartData.add(series);
        best_line_chart.setData(lineChartData);
        
        /**
         * Browsing through the Data and applying ToolTip
         * as well as the class on hover
         */
        for (XYChart.Series<Number, Number> s : best_line_chart.getData()) {
            for (final XYChart.Data<Number, Number> d : s.getData()) {
                Tooltip.install(d.getNode(), new Tooltip(
                        "Iteration : " + d.getXValue().toString() + "\n" +
                        "Brightness : " + d.getYValue() + "\n" +
                        this.first + " : " + p.getMejores().get(d.getXValue().intValue()).getValPalabra1() + "\n" +
                        this.second + " : " + p.getMejores().get(d.getXValue().intValue()).getValPalabra2() + "\n" + 
                        this.result + " : " + p.getMejores().get(d.getXValue().intValue()).getValPalabra3()
                ));

                //Adding class on hover
                d.getNode().setOnMouseEntered(new EventHandler(){
                    @Override
                    public void handle(Event event) {
                        d.getNode().setStyle("-fx-background-color: orange");
                    }
                });
                
                d.getNode().setOnMouseExited(new EventHandler(){
                    @Override
                    public void handle(Event event) {
                        d.getNode().setStyle("-fx-background-color: white; -fx-border-color: orange; -fx-border-radius:6px;"
                                + "-fx-border-width: 3px;");
                    }
                });
//
//                //Removing class on exit
//                d.getNode().addEventHandler(EventType.ROOT, new EventHandler() {
//
//                    @Override
//                    public void handle(Event event) {
//                        d.getNode().setStyle("-fx-background-color: white");
//                    }
//                });
            }
        }
    }
}
