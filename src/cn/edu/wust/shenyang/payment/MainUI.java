package cn.edu.wust.shenyang.payment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

@SuppressWarnings("serial")
public class MainUI extends JFrame implements ActionListener {

    private static final String[] SEARCH_RESULT_COLUMNS = {"no", "name", "type", "salaryHours/saleNum", "payment"};

    private JPanel jp1 = null;

    private JLabel name_label = null;
    private JTextField name_text_field = null;

    private JLabel eid_label = null;
    private JTextField eid_text_field = null;

    private JButton searchNobutton = null;
    private JButton searchNamebutton = null;
    private JButton search_all_button = null;

    private JButton addButton = null;
    private JButton editButton = null;
    private JButton deleteButton = null;
    private JButton saveButton = null;
    private JButton loadButton = null;
    private JButton saveFileButton = null;

    private DefaultTableModel searchResultTableModel = null;
    private JTable searchResultTable = null;
    private JScrollPane jsp = null;

    private CEmployerSet employerSet = null;
    private CDataAccess dataAccess = null;

    public MainUI() {
        jp1 = new JPanel();

        name_label = new JLabel("name");
        name_text_field = new JTextField(10);

        eid_label = new JLabel("id");
        eid_text_field = new JTextField(10);

        searchNobutton = new JButton("Search");
        searchNobutton.addActionListener(new SearchNoActionListener());

        searchNamebutton = new JButton("Search");
        searchNamebutton.addActionListener(new SearchNameActionListener());

        search_all_button = new JButton("Search All");
        search_all_button.addActionListener(new SearchAllActionListener());

        addButton = new JButton("Add");
        addButton.addActionListener(new AddActionListener());

        editButton = new JButton("Edit");
        editButton.addActionListener(new EditActionListener());

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new DeleteActionListener());

        saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveActionListener());
        
        loadButton = new JButton("Load");
        loadButton.addActionListener(new LoadActionListener());

        saveFileButton = new JButton("Save to file");
        saveFileButton.addActionListener(new SaveFileActionListener());

        jp1.add(eid_label);
        jp1.add(eid_text_field);
        jp1.add(searchNobutton);

        jp1.add(name_label);
        jp1.add(name_text_field);

        jp1.add(searchNamebutton);
        jp1.add(search_all_button);
        jp1.add(addButton);
        jp1.add(editButton);
        jp1.add(deleteButton);
        jp1.add(saveButton);
        jp1.add(loadButton);
        jp1.add(saveFileButton);

        this.add(jp1);

        searchResultTableModel = new DefaultTableModel(SEARCH_RESULT_COLUMNS, 3);
        searchResultTable = new JTable(searchResultTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        searchResultTable.addMouseListener(new SearchResultTableMouseAdapter());
        this.jsp = new JScrollPane(searchResultTable);
        jp1.add(this.jsp);

        this.employerSet = new CEmployerSet();
        //this.employerSet.addNewEmployer(EmployerType.manager, "m1", 0);
        //this.employerSet.addNewEmployer(EmployerType.techer, "t1", 100);
        //this.employerSet.addNewEmployer(EmployerType.saler, "s1", 10000);

        this.dataAccess = new CDataAccess();
    }

    public void start() {
        this.setSize(650, 500);
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
                searchResultTable.setValueAt(employer.no, 0, 0);
                searchResultTable.setValueAt(employer.name, 0, 1);
            }
        }
    }

    public static void main(String[] args) {
        MainUI main_ui = new MainUI();
        main_ui.start();
    }

    class SearchNoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainUI ui = MainUI.this;
            long no = Long.valueOf(ui.eid_text_field.getText());

            Employer employer = ui.employerSet.getEmployer(no);
            ui.searchResultTableModel.setRowCount(1);

            ui.searchResultTable.setValueAt(employer.no, 0, 0);
            ui.searchResultTable.setValueAt(employer.name, 0, 1);
            ui.searchResultTable.setValueAt(employer.type.toString(), 0, 2);

            if (employer.type == EmployerType.techer) {
                ui.searchResultTable.setValueAt(((Techer)employer).getSalaryHours(), 0, 3);
            } else if (employer.type == EmployerType.saler) {
                ui.searchResultTable.setValueAt(((Saler)employer).getSaleNum(), 0, 3);
            } else {
                ui.searchResultTable.setValueAt("", 0, 3);
            }

            ui.searchResultTable.setValueAt(employer.getPay(), 0, 4);
        }
    }

    class SearchNameActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainUI ui = MainUI.this;
            String name = ui.name_text_field.getText();

            Employer employer = ui.employerSet.getEmployer(name);
            ui.searchResultTableModel.setRowCount(1);

            ui.searchResultTable.setValueAt(employer.no, 0, 0);
            ui.searchResultTable.setValueAt(employer.name, 0, 1);
            ui.searchResultTable.setValueAt(employer.type.toString(), 0, 2);

            if (employer.type == EmployerType.techer) {
                ui.searchResultTable.setValueAt(((Techer)employer).getSalaryHours(), 0, 3);
            } else if (employer.type == EmployerType.saler) {
                ui.searchResultTable.setValueAt(((Saler)employer).getSaleNum(), 0, 3);
            } else {
                ui.searchResultTable.setValueAt("", 0, 3);
            }

            ui.searchResultTable.setValueAt(employer.getPay(), 0, 4);
        }
    }

    class SearchAllActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainUI ui = MainUI.this;
            List<Employer> employers = ui.employerSet.listAllEmployer();

            ui.searchResultTableModel.setRowCount(employers.size());
            int row = 0;
            for (Employer employer : employers) {
                ui.searchResultTable.setValueAt(employer.no, row, 0);
                ui.searchResultTable.setValueAt(employer.name, row, 1);
                ui.searchResultTable.setValueAt(employer.type.toString(), row, 2);

                if (employer.type == EmployerType.techer) {
                    ui.searchResultTable.setValueAt(((Techer)employer).getSalaryHours(), row, 3);
                } else if (employer.type == EmployerType.saler) {
                    ui.searchResultTable.setValueAt(((Saler)employer).getSaleNum(), row, 3);
                } else {
                    ui.searchResultTable.setValueAt("", row, 3);
                }

                ui.searchResultTable.setValueAt(employer.getPay(), row, 4);

                ++row;
            }
        }
    }

    class AddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new AddEmployerUI(MainUI.this, true, MainUI.this.employerSet);
        }
    }

    class EditActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainUI ui = MainUI.this;
            int row = ui.searchResultTable.getSelectedRow();
            long no = (Long)ui.searchResultTableModel.getValueAt(row, 0);
            new EditEmployerUI(MainUI.this, true, MainUI.this.employerSet, no);
        }
    }

    class DeleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainUI ui = MainUI.this;
            int row = ui.searchResultTable.getSelectedRow();
            long no = (Long)ui.searchResultTableModel.getValueAt(row, 0);
            new DeleteEmployerUI(MainUI.this, true, MainUI.this.employerSet, no);
        }
    }

    class SaveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainUI ui = MainUI.this;
            ui.dataAccess.save(ui.employerSet.listAllEmployer());
        }
    }
    
    class LoadActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainUI ui = MainUI.this;
            ui.dataAccess.load(ui.employerSet);
        }
    }

    class SaveFileActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainUI ui = MainUI.this;

            JFileChooser jfc = new JFileChooser();
            jfc.showSaveDialog(ui);

            File file = jfc.getSelectedFile();
            try {
                FileWriter fileWriter = new FileWriter(file);
                for (Employer employer : ui.employerSet.listAllEmployer()) {
                    fileWriter.write(employer.toString() + "\n");
                }
                fileWriter.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class SearchResultTableMouseAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
            }
        }
    }
}
