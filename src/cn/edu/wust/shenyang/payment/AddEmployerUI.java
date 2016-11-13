package cn.edu.wust.shenyang.payment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployerUI extends JDialog {

    private CEmployerSet employerSet = null;

    private JLabel nameLabel = null;
    private JTextField nameTextField = null;

    private JLabel typeLabel = null;
    private JComboBox typeCombo = null;

    private JLabel workloadLabel = null;
    private JTextField workloadTextField = null;

    private JButton submitButton = null;
    private JButton cancelButton = null;

    public AddEmployerUI(JFrame parent, boolean modal, CEmployerSet employerSet) {
        super(parent, modal);
        this.employerSet = employerSet;

        JPanel p = new JPanel(new GridLayout(4,2,3,3));

        setLayout(new BorderLayout());     //定义窗体布局为边界布局

        nameLabel = new JLabel("name");
        nameTextField = new JTextField(5);

        typeLabel = new JLabel("type");
        String employerTypes[] = { "manager", "techer", "saler"};
        typeCombo = new JComboBox(employerTypes);

        workloadLabel = new JLabel("workload");
        workloadTextField = new JTextField(5);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitActionListener());

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelActionListener());

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
            AddEmployerUI ui = AddEmployerUI.this;

            String name = ui.nameTextField.getText();
            int type_index = ui.typeCombo.getSelectedIndex();
            double workload = 0;
            String workload_str = ui.workloadTextField.getText();
            if (!workload_str.isEmpty()) {
                workload = Double.valueOf(workload_str);
            }

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

            ui.employerSet.addNewEmployer(type, name, workload);

            ui.dispose();
        }
    }

    class CancelActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddEmployerUI.this.dispose();
        }
    }
}
