package com.example.tic_tac_toe_2.BXH;

class Model_BXH {
    private int img ;
    private String name ;
    private int point ;

    public Model_BXH() {
    }

    public Model_BXH(int img, String name, int point) {
        this.img = img;
        this.name = name;
        this.point = point;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
