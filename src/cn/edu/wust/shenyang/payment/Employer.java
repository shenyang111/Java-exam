package cn.edu.wust.shenyang.payment;

enum EmployerType {
    manager,
    techer,
    saler
}

abstract class Employer {
    static long id = 1000;

    long no;
    String name;
    EmployerType type;

    abstract double getPay();

    Employer(String name) {
        this.name = name;
        id = id + 1;
        this.no = id;
    }

    Employer(long no, String name) {
        this.no = no;
        this.name = name;
        if (this.no > id) {
            id = this.no;
        }
    }

    public void setName(String name) {
        this.name = name;
    }
}