package cn.edu.wust.shenyang.payment;

class Techer extends Employer {
    private double salaryHours;
    
    Techer(String name, double salaryHours) {
        super(name);
        no = id;

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
        return "name:" + name + " no:" + no + " pay:" + getPay();
    }
}
