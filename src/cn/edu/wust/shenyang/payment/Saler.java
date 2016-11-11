package cn.edu.wust.shenyang.payment;

class Saler extends Employer {
    private double saleNum;
    
    Saler(String name) {
        super(name);
        no = id;
    }

    public double getPay() {
        return saleNum * 0.04;
    }

    public void setSaleNum(float saleNum) {
        this.saleNum = saleNum;
    }

    public String toString() {
        return "name:" + name + " no:" + no + " pay:" + getPay();
    }
}
