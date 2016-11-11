package cn.edu.wust.shenyang.payment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CEmployerSet {
    private List<Employer> employers = new ArrayList<Employer>();

    public void addNewEmployer(EmployerType type, String name) {
        switch (type) {
            case manager :
                employers.add(new Manager(name));
                break;
            case techer :
                employers.add(new Techer(name));
                break;
            case saler :
                employers.add(new Saler(name));
                break;
        }
    }

    public Employer getEmployer(String name) {
        for (Employer employer : employers) {
            if (employer.name.equals(name)) {
                return employer;
            }
        }
        return null;
    }

    public void updateEmployer() {

    }

    public void deleteEmployer(long no) {
        for (Iterator<Employer> it = employers.iterator(); it.hasNext(); ) {
            Employer employer = it.next();
            if (employer.no == no) {
                it.remove();
            }
        }
    }

    public void deleteEmployer(String name) {
        for (Iterator<Employer> it = employers.iterator(); it.hasNext(); ) {
            Employer employer = it.next();
            if (employer.name.equals(name)) {
                it.remove();
            }
        }
    }

    public void listAllEmployer() {

    }
}
