package com.design.models;

public class Snake implements BoardEntity{
    int start;
    int end;

    public Snake(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public void message() {
        System.out.println(this.getClass().getName() + " Going downward from " + start + " to " + end);
    }
}
