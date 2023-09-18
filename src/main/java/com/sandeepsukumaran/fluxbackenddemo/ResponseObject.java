package com.sandeepsukumaran.fluxbackenddemo;

import java.util.List;


public class ResponseObject {
    private int n;
    private List<Boolean> status;
    private boolean completed;

    // getters and setters
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public List<Boolean> getStatus() {
        return status;
    }

    public void setStatus(List<Boolean> status) {
        this.status = status;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
