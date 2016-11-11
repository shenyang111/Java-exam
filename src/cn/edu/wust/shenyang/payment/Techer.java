package cn.edu.wust.shenyang.payment;

class Techer extends Employer {
    private long salaryHours;
    
    Techer(String name) {
        super(name);
        no = id;
    }

    public double getPay() {
        return salaryHours * 100;
    }

    public void setSalaryHours(long salaryHours) {
        this.salaryHours = salaryHours;
    }

    public String toString() {
        return "name:" + name + " no:" + no + " pay:" + getPay();
    }
}
