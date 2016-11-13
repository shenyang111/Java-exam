package cn.edu.wust.shenyang.payment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteEmployerUI extends JDialog {

    private CEmployerSet employerSet = null;
    private long no = -1;

    private JButton submitButton = null;
    private JButton cancelButton = null;

    public DeleteEmployerUI(JFrame parent, boolean modal, CEmployerSet employerSet, long no) {
        super(parent, modal);
        this.employerSet = employerSet;
        this.no = no;

        JPanel p = new JPanel(new GridLayout(1, 2, 10, 10));

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitActionListener());

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelActionListener());

        p.add(submitButton);
        p.add(cancelButton);


        //将文本框放置在窗体NORTH位置
        //getContentPane().add(t,BorderLayout.NORTH);

        //将面板放置在窗体CENTER位置
        getContentPane().add(p,BorderLayout.CENTER);

        setSize(250, 75);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);               //让窗体居中显示

        setVisible(true);
    }

    class SubmitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DeleteEmployerUI ui = DeleteEmployerUI.this;

            ui.employerSet.deleteEmployer(no);

            ui.dispose();
        }
    }

    class CancelActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DeleteEmployerUI.this.dispose();
        }
    }
}
