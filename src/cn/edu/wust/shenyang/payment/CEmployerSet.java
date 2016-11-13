package cn.edu.wust.shenyang.payment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CEmployerSet {
    private List<Employer> employers = new ArrayList<Employer>();

    public void addNewEmployer(EmployerType type, String name, double workload) {
        switch (type) {
            case manager :
                employers.add(new Manager(name));
                break;
            case techer :
                employers.add(new Techer(name, workload));
                break;
            case saler :
                employers.add(new Saler(name, workload));
                break;
        }
    }

    public void addNewEmployer(long no, EmployerType type, String name, double workload) {
        switch (type) {
            case manager :
                employers.add(new Manager(no, name));
                break;
            case techer :
                employers.add(new Techer(no, name, workload));
                break;
            case saler :
                employers.add(new Saler(no, name, workload));
                break;
        }
    }

    public Employer getEmployer(long no) {
        for (Employer employer : employers) {
            if (employer.no == no) {
                return employer;
            }
        }
        return null;
    }

    public Employer getEmployer(String name) {
        for (Employer employer : employers) {
            if (employer.name.equals(name)) {
                return employer;
            }
        }
        return null;
    }

    public void updateEmployer(long no, EmployerType type, String name, double workload) {
        Employer employer = getEmployer(no);
        if (employer.type == type) {
            employer.name = name;
            if (type == EmployerType.techer) {
                ((Techer)employer).setSalaryHours(workload);
            } else if (type == EmployerType.saler) {
                ((Saler)employer).setSaleNum(workload);
            }
        } else {
            Employer new_employer = null;
            switch (type) {
                case manager :
                    new_employer = new Manager(name);
                    break;
                case techer :
                    new_employer = new Techer(name, workload);
                    break;
                case saler :
                    new_employer = new Saler(name, workload);
                    break;
            }

            for (int i = 0; i < employers.size(); ++i) {
                if (employers.get(i).no == no) {
                    employers.set(i, new_employer);
                }
            }
        }
    }

    public void deleteEmployer(long no) {
        for (Iterator<Employer> it = employers.iterator(); it.hasNext(); ) {
            Employer employer = it.next();
            if (employer.no == no) {
                it.remove();
            }
        }
    }

    public List<Employer> listAllEmployer() {
        return employers;
    }
}
