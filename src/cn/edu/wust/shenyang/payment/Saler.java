package cn.edu.wust.shenyang.payment;

class Saler extends Employer {
    long saleNum;
    
    double getPay() {
        return saleNum*0.4;
    }
    
    Saler(String name, long saleNum) {
        super(name);
        no = id;
        this.saleNum = saleNum;
    }
    
    public String toString() {
        return "name:" + name + " no:" + no + " pay:" + getPay();
    }
}
