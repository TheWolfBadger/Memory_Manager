package com.jaredscarito.memory_manager.api.spaces;

import com.jaredscarito.memory_manager.main.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import javax.swing.*;

/**
 * @author Jared Scarito
 */

public class ProcessBlock extends HBox {

    private double size;
    private double startY;
    private double endY;

    private String pid;

    private int displaySize;

    private Label sizeLabel;
    private Label processLabel;

    public ProcessBlock(String pid, double size, double startY) {
        double sizeCalc = ( (580 / Double.parseDouble(Main.getTotalMemField().getText())) * size );
        if(sizeCalc >= 40) {
            this.size = sizeCalc;
            this.pid = pid;
            this.displaySize = (int) size;
            this.startY = startY;
            this.endY = this.size + this.startY; // Extra digit is for border px
            setLayoutY(this.startY);
            //this.setPrefHeight(this.size);
            this.setPrefWidth(100);

            Label processLabel = new Label(pid);
            processLabel.getStyleClass().add("process-label");
            this.processLabel = processLabel;
            this.processLabel.setPrefWidth(100);
            processLabel.setAlignment(Pos.CENTER);
            this.processLabel.setPrefHeight(this.size);
            if(pid.equalsIgnoreCase("OS")) this.processLabel.setStyle("-fx-background-color: CORNFLOWERBLUE;");
            processLabel.setLayoutY(startY);

            Label sizeLabel = new Label(this.displaySize + " KB");
            sizeLabel.getStyleClass().add("size-label");
            this.sizeLabel = sizeLabel;
            this.sizeLabel.setLayoutY(this.endY - 15);

            Main.getSizeLayoutPane().getChildren().add(sizeLabel);
            Main.getBlockLayoutPane().getChildren().add(processLabel);
        } else {
            double minSize = ( (580 / Double.parseDouble(Main.getTotalMemField().getText())) * size );
            double adder = 1;
            while (minSize < 40) {
                minSize = ( (580 / Double.parseDouble(Main.getTotalMemField().getText())) * (size + adder));
                adder++;
            }
            JOptionPane.showMessageDialog(null, "Cannot perform operation - Minimum Size required: " + (size + adder) + " KB");
        }
    }

    public void destroy() {
        this.sizeLabel.setVisible(false);
        this.processLabel.setVisible(false);
        this.setVisible(false);
    }

    public String getPID() {
        return this.pid;
    }

    public int getDisplaySize() {
        return displaySize;
    }
    public void setDisplaySize(int displaySize) {
        this.displaySize = displaySize;
    }
    public double getSize() {
        return this.size;
    }
    public void setSize(double size) {
        this.size = size;
    }
    public double getStartY() {
        return startY;
    }
    public void setStartY(double startY) {
        this.startY = startY;
    }
    public double getEndY() {
        return endY;
    }
    public void setEndY(double endY) {
        this.endY = endY;
    }
}
