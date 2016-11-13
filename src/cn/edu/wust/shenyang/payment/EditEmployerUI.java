package cn.edu.wust.shenyang.payment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditEmployerUI extends JDialog {

    private CEmployerSet employerSet = null;
    private Employer editEmployer = null;

    private JLabel noLabel = null;
    private JTextField noTextField = null;

    private JLabel nameLabel = null;
    private JTextField nameTextField = null;

    private JLabel typeLabel = null;
    private JComboBox typeCombo = null;

    private JLabel workloadLabel = null;
    private JTextField workloadTextField = null;

    private JButton submitButton = null;
    private JButton cancelButton = null;

    public EditEmployerUI(JFrame parent, boolean modal, CEmployerSet employerSet, long no) {
        super(parent, modal);
        this.employerSet = employerSet;
        editEmployer = this.employerSet.getEmployer(no);

        JPanel p = new JPanel(new GridLayout(5,2,3,3));

        setLayout(new BorderLayout());     //定义窗体布局为边界布局

        noLabel = new JLabel("no");
        noTextField = new JTextField(5);
        noTextField.setText(String.valueOf(no));
        noTextField.setEditable(false);

        nameLabel = new JLabel("name");
        nameTextField = new JTextField(5);
        nameTextField.setText(editEmployer.name);

        typeLabel = new JLabel("type");
        String employerTypes[] = { "manager", "techer", "saler"};
        typeCombo = new JComboBox(employerTypes);
        switch (editEmployer.type) {
            case manager :
                typeCombo.setSelectedIndex(0);
                break;
            case techer :
                typeCombo.setSelectedIndex(1);
                break;
            case saler :
                typeCombo.setSelectedIndex(2);
                break;
        }

        workloadLabel = new JLabel("workload");
        workloadTextField = new JTextField(5);
        if (editEmployer.type == EmployerType.techer) {
            workloadTextField.setText(String.valueOf(((Techer)editEmployer).getSalaryHours()));
        } else if (editEmployer.type == EmployerType.saler) {
            workloadTextField.setText(String.valueOf(((Saler)editEmployer).getSaleNum()));
        }

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitActionListener());

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelActionListener());

        p.add(noLabel);
        p.add(noTextField);
        p.add(nameLabel);
        p.add(nameTextField);
        p.add(typeLabel);
        p.add(typeCombo);
        p.add(workloadLabel);
        p.add(workloadTextField);
        p.add(submitButton);
        p.add(cancelButton);


        //将文本框放置在窗体NORTH位置
        //getContentPane().add(t,BorderLayout.NORTH);

        //将面板放置在窗体CENTER位置
        getContentPane().add(p,BorderLayout.CENTER);

        setSize(250,200);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);               //让窗体居中显示

        setVisible(true);
    }

    class SubmitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            EditEmployerUI ui = EditEmployerUI.this;

            String name = ui.nameTextField.getText();
            int type_index = ui.typeCombo.getSelectedIndex();
            double workload = Double.valueOf(ui.workloadTextField.getText());

            EmployerType type = null;
            switch (type_index) {
                case 0 :
                    type = EmployerType.manager;
                    break;
                case 1 :
                    type = EmployerType.techer;
                    break;
                case 2 :
                    type = EmployerType.saler;
                    break;
            }

            ui.employerSet.updateEmployer(ui.editEmployer.no, type, name, workload);

            ui.dispose();
        }
    }

    class CancelActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            EditEmployerUI.this.dispose();
        }
    }
}
