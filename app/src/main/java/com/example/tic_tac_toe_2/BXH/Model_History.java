package com.example.tic_tac_toe_2.BXH;

class Model_History {
    private  String nameOne ;
    private  String nameTwo ;
    private int pointOne;
    private int pointtwo;

    public Model_History() {
    }

    public Model_History(String nameOne, String nameTwo, int pointOne, int pointtwo) {
        this.nameOne = nameOne;
        this.nameTwo = nameTwo;
        this.pointOne = pointOne;
        this.pointtwo = pointtwo;
    }

    public String getNameOne() {
        return nameOne;
    }

    public void setNameOne(String nameOne) {
        this.nameOne = nameOne;
    }

    public String getNameTwo() {
        return nameTwo;
    }

    public void setNameTwo(String nameTwo) {
        this.nameTwo = nameTwo;
    }

    public int getPointOne() {
        return pointOne;
    }

    public void setPointOne(int pointOne) {
        this.pointOne = pointOne;
    }

    public int getPointtwo() {
        return pointtwo;
    }

    public void setPointtwo(int pointtwo) {
        this.pointtwo = pointtwo;
    }
}
