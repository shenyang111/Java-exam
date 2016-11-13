package cn.edu.wust.shenyang.payment;

class Saler extends Employer {
    private double saleNum;
    
    Saler(String name, double saleNum) {
        super(name);
        this.type = EmployerType.saler;
        this.saleNum = saleNum;
    }

    Saler(long no, String name, double saleNum) {
        super(no, name);
        this.type = EmployerType.saler;
        this.saleNum = saleNum;
    }

    public double getPay() {
        return saleNum * 0.04;
    }

    public void setSaleNum(double saleNum) {
        this.saleNum = saleNum;
    }

    public double getSaleNum() {
        return saleNum;
    }

    public String toString() {
        return "no:" + no + ", name:" + name + ", pay:" + getPay();
    }
}
