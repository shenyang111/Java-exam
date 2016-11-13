package cn.edu.wust.shenyang.payment;

class Techer extends Employer {
    private double salaryHours;
    
    Techer(String name, double salaryHours) {
        super(name);
        this.type = EmployerType.techer;
        this.salaryHours = salaryHours;
    }

    Techer(long no, String name, double salaryHours) {
        super(no, name);
        this.type = EmployerType.techer;
        this.salaryHours = salaryHours;
    }

    public double getPay() {
        return salaryHours * 100;
    }

    public void setSalaryHours(double salaryHours) {
        this.salaryHours = salaryHours;
    }

    public double getSalaryHours() {
        return salaryHours;
    }

    public String toString() {
        return "no:" + no + ", name:" + name + ", pay:" + getPay();
    }
}
