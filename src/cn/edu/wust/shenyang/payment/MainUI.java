package cn.edu.wust.shenyang.payment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI extends JFrame implements ActionListener {

    private static final String[] SEARCH_RESULT_COLUMNS = {"no", "name", "type", "salaryHours/saleNum", "payment"};

    private JPanel jp1 = null;

    private JLabel name_label = null;
    private JTextField name_text_field = null;

    private JLabel eid_label = null;
    private JTextField eid_text_field = null;

    private JButton search_button = null;

    private JTable search_result_table = null;
    private JScrollPane jsp = null;

    private CEmployerSet employerSet = null;

    public MainUI() {
        jp1 = new JPanel();

        name_label = new JLabel("name");
        name_text_field = new JTextField(10);

        eid_label = new JLabel("id");
        eid_text_field = new JTextField(10);

        search_button = new JButton("search");
        search_button.addActionListener(this);

        jp1.add(eid_label);
        jp1.add(eid_text_field);

        jp1.add(name_label);
        jp1.add(name_text_field);

        jp1.add(search_button);

        this.add(jp1);

        this.search_result_table = new JTable(new DefaultTableModel(SEARCH_RESULT_COLUMNS, 3));
        this.jsp = new JScrollPane(this.search_result_table);
        jp1.add(this.jsp);

        this.employerSet = new CEmployerSet();
        this.employerSet.addNewEmployer(EmployerType.manager, "m1");
        this.employerSet.addNewEmployer(EmployerType.techer, "t1");
        this.employerSet.addNewEmployer(EmployerType.saler, "s1");
    }

    public void start() {
        this.setSize(600, 500);
        this.setLocation(200, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("search")) {
            String name = name_text_field.getText();
            Employer employer = employerSet.getEmployer(name);
            if (employer != null) {
                search_result_table.setValueAt(employer.no, 0, 0);
                search_result_table.setValueAt(employer.name, 0, 1);
            }
        }
    }

    public static void main(String[] args) {
        MainUI main_ui = new MainUI();
        main_ui.start();
    }
}
