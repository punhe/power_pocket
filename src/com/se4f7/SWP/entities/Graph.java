package com.se4f7.SWP.entities;

import java.util.Date;

public class Graph {
    private Date createdDate;
    private int amountStatus1;
    private int amountStatus2;

    public Graph(Date createdDate, int amountStatus1, int amountStatus2) {
        this.createdDate = createdDate;
        this.amountStatus1 = amountStatus1;
        this.amountStatus2 = amountStatus2;
    }
    public Graph(){}

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getAmountStatus1() {
        return amountStatus1;
    }

    public void setAmountStatus1(int amountStatus1) {
        this.amountStatus1 = amountStatus1;
    }

    public int getAmountStatus2() {
        return amountStatus2;
    }

    public void setAmountStatus2(int amountStatus2) {
        this.amountStatus2 = amountStatus2;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "createdDate=" + createdDate +
                ", amountStatus1=" + amountStatus1 +
                ", amountStatus2=" + amountStatus2 +
                '}';
    }
}
