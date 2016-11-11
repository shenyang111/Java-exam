package cn.edu.wust.shenyang.payment;

class Techer extends Employer {
    long salaryHours;
    
    double getPay() {
        return salaryHours*100;
    }
    
    Techer(String name, long salaryHours) {
        super(name);
        no = id;
        this.salaryHours = salaryHours;
    }
    
    public String toString() {
        return "name:" + name + " no:" + no + " pay:" + getPay();
    }
}
