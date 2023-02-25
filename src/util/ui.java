package util;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;


public class ui {


    //main frame
    private JFrame main_menu;
    private JLabel CCC;
    private JButton new_acc;
    private JButton return_of_product;
    private JButton purchase;
    private JButton delete_acc;
    private JButton payment;
    private JButton good_clients;
    private JButton init_database;
    private JButton bad_clients;
    private JButton questions;
    private JButton dealer_of_the_month;
    private JLabel logo;


    //registration frame
    private JFrame register_frame;
    private JLabel greeting;
    private JLabel The_you_are;
    private JRadioButton company_button;
    private JRadioButton individual_button;
    private JLabel the_balance;
    private JLabel the_dept;
    private JLabel label4;
    private JTextField textField_balance;
    private JTextField textField_dept;
    private JTextField textField_credit;
    private JLabel name;
    private JTextField textField_name;
    private JSpinner date;
    private JLabel exp_date;
    private JLabel exp_date2;
    private JButton register_button;
    private JRadioButton merchant_button;
    private JLabel our_logo;
    private boolean merchant = false; //indicates if the user is a merchant
    private boolean individual = true;
    private char date_array[] = new char[11];
    private String name_clientName;
    private String balance_CompanyName_Profit;
    private float dept;
    private int account_id = 0;
    private String expire_date;
    private int transID = 0;
    private JLabel employee_name;
    private JTextField textField_employee_name;
    private String employee_name_str = new String();
    private boolean is_employee = false;
    private int employee_id;
    private JButton register_cancel;

    //Init Database
    private JFrame init_database_frame;
    private JLabel label1_init_database;
    private JButton create_new_database;
    private JButton cancel;


    //Delete account
    private JFrame delete_acc_fr;
    private JLabel main_label_delete_acc;
    private JLabel label_delete_acc;
    private JTextField textfield_delete_acc;
    private JButton delete_button;
    private JLabel label2_icon;
    private JButton delete_cancel_button;
    private JTextField textfield_delete_acc_id;
    private JLabel label_delete_acc_id;

    //Purchase
    private JFrame purchase_frame;
    private JLabel label1_purchase;
    private JLabel label2_purchase;
    private JLabel label3_purchase;
    private JLabel label4_purchase;
    private JTextField purchase_client_id;
    private JLabel label5_purchase;
    private JButton purchase_button;
    private JButton cancel_button_purchase;
    private JLabel label7_purchase;
    private JTextField purchase_amount;
    private JLabel label6_purchase;
    private JLabel label2_purchase2;
    private JTextField purchase_client_name;
    private JLabel label3_purchase2;
    private JTextField purchase_dealer_id;
    private JTextField purchase_dealer_name;
    private JCheckBox checkBox1;
    private JLabel label_employee_id;
    private JTextField textField_employee_id;
    private JLabel label_employee_name;
    private JTextField textField_employee_name_purchase;

    //Return
    private JFrame return_frame;
    private JLabel label1_title;
    private JLabel label1;
    private JLabel return_name;
    private JLabel return_id;
    private JTextField return_id_textfield;
    private JTextField return_name_textfield;
    private JButton return_button;
    private JButton cancel_return;
    private JLabel transaction_id;
    private JTextField return_tr_id_textfield2;

    //Payment
    private JFrame payment_frame;
    private JLabel label1_title_payment;
    private JLabel label1_payment;
    private JLabel payment_name;
    private JLabel payment_id;
    private JTextField payment_id_textfield;
    private JTextField payment_name_textfield;
    private JButton button1;
    private JButton button2;
    private JLabel payment_amount;
    private JTextField payment_amount_textfield;

    //Questions
    private JFrame questions_frame;
    private JLabel label1_questions;
    private JLabel questions_name;
    private JLabel questions_id;
    private JTextField questions_id_textfield;
    private JTextField questions_name_textfield;
    private JCheckBox questions_checkBox;
    private JRadioButton questions_all;
    private JRadioButton questions_employee;
    private JLabel questions_name_employee;
    private JLabel questions_id_employee;
    private JTextField questions_id_employee_textfield;
    private JTextField questions_name_employee_textfield;
    private JLabel label_from;
    private JSpinner spinner1_questions_from;
    private JSpinner spinner2_questions_to;
    private JLabel questions_time;
    private JButton questions_results;
    private JButton questions_cancel;
    private char date_array_from[] = new char[11];
    private char date_array_to[] = new char[11];
    private JLabel questions_icon;
    //Extra Employee
    private JFrame extra_employee;

    ;
    private JLabel label1_extra;
    private JLabel label2_extra_employee_name;
    private JTextField textField1;
    private JButton add_employee;
    private JButton cancel_button;

    public static void buildTableModel(ResultSet rs) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(dtm);
        JOptionPane.showMessageDialog(null, new JScrollPane(table));
    }

    private void create_db() throws SQLException, ClassNotFoundException {
        Connection connect = Connect_db.get_connection(false);
        Statement statement = connect.createStatement();
        statement.executeUpdate("CREATE DATABASE cccDB");
        statement.close();
        connect.close();
        connect = Connect_db.get_connection(true);
        statement = connect.createStatement();
        statement.executeUpdate("CREATE TABLE users (\n" + "accountID INT(30) PRIMARY KEY,\n" + "username VARCHAR (30) NOT NULL\n" + ")");
        statement.executeUpdate("CREATE TABLE consumers(\n" + "accountID INT(30) NOT NULL PRIMARY KEY,\n" + "consumer_name VARCHAR (30) NOT NULL,\n" + "debt FLOAT(10),\n" + "credit_limit FLOAT(10),\n" + "exp_date VARCHAR(40),\n" + "balance FLOAT(10)\n" + ")");
        statement.executeUpdate("CREATE TABLE merchants (\n" + "accountID INT(30) NOT NULL PRIMARY KEY,\n" + "merchant_name VARCHAR (30) NOT NULL,\n" + "commission FLOAT(10),\n" + "debt FLOAT(10),\n" + "profit FLOAT(10)\n" + ")");
        statement.executeUpdate("CREATE TABLE corporates (\n" + "accountID INT (30) NOT NULL PRIMARY KEY\n" + ")");
        statement.executeUpdate("CREATE TABLE employees (\n" + "accountID INT (30) NOT NULL,\n" + "emp_id INT (30) NOT NULL PRIMARY KEY,\n" + "emp_name VARCHAR (30) NOT NULL\n" + ")");
        statement.executeUpdate("CREATE TABLE emp_transactions (\n" + "transID INT (30) NOT NULL PRIMARY KEY,\n" + "emp_id INT (30) NOT NULL\n)");
        statement.executeUpdate("CREATE TABLE transactions (\n" + "transID INT(30) PRIMARY KEY,\n" + "consumer_acc_id INT(30) NOT NULL,\n" + "consumer_name VARCHAR (30) NOT NULL,\n" + "merchant_acc_id INT(30) NOT NULL,\n" + "merchant_name VARCHAR (30) NOT NULL,\n" + "amount FLOAT(10),\n" + "date VARCHAR(40),\n" + "transType INT(10)\n" + ")");
        statement.executeUpdate("INSERT INTO consumers VALUES(0,'Jumbo', 60000,100000,'2030/01/23 16:44:40',40000)");
        statement.executeUpdate("INSERT INTO consumers VALUES(1,'Panagiotis Panagiotidis', 1000,500,'2025/08/23 16:44:40',-500)");
        statement.executeUpdate("INSERT INTO consumers VALUES(2,'Eleutherios Eleutheriou', 0,0,'2025/10/23 16:44:40',0)");
        statement.executeUpdate("INSERT INTO consumers VALUES(3,'Nerotsoulithres', 4000,5000,'2030/01/23 16:44:40',1000)");
        statement.executeUpdate("INSERT INTO consumers VALUES(4,'Kritikos', 15000,50000,'2030/01/23 16:44:40',35000)");
        statement.executeUpdate("INSERT INTO consumers VALUES(5,'Geusinous', 1000,5000,'2030/01/23 16:44:40',4000)");
        statement.executeUpdate("INSERT INTO consumers VALUES(6,'Fournos', 10,100,'2030/01/23 16:44:40',90)");
        statement.executeUpdate("INSERT INTO merchants VALUES(7,'Tasos', 50,100,50)");
        statement.executeUpdate("INSERT INTO merchants VALUES(8,'Boula', 0,1000,1000)");
        statement.executeUpdate("INSERT INTO merchants VALUES(9,'Panagiota', 0,2000,2000)");
        statement.executeUpdate("INSERT INTO merchants VALUES(10,'Marinakis', 20,5000,242500000)");
        statement.executeUpdate("INSERT INTO merchants VALUES(11,'Xristoforos', 10,0,-10)");
        statement.executeUpdate("INSERT INTO corporates VALUES(0)");
        statement.executeUpdate("INSERT INTO corporates VALUES(3)");
        statement.executeUpdate("INSERT INTO corporates VALUES(4)");
        statement.executeUpdate("INSERT INTO corporates VALUES(5)");
        statement.executeUpdate("INSERT INTO corporates VALUES(6)");
        statement.executeUpdate("INSERT INTO employees VALUES(0, 0, 'Giannakis')");
        statement.executeUpdate("INSERT INTO employees VALUES(3, 1, 'Andreas')");
        statement.executeUpdate("INSERT INTO employees VALUES(4, 2, 'Toula')");
        statement.executeUpdate("INSERT INTO employees VALUES(5, 3, 'Maria')");
        statement.executeUpdate("INSERT INTO employees VALUES(6, 4, 'Mitsotakis')");
        statement.executeUpdate("INSERT INTO users VALUES(0,'Jumbo')");
        statement.executeUpdate("INSERT INTO users VALUES(1,'Panagiotis Panagiotidis')");
        statement.executeUpdate("INSERT INTO users VALUES(2,'Eleutherios Eleutheriou')");
        statement.executeUpdate("INSERT INTO users VALUES(3,'Nerotsoulithres')");
        statement.executeUpdate("INSERT INTO users VALUES(4,'Kritikos')");
        statement.executeUpdate("INSERT INTO users VALUES(5,'Geusinous')");
        statement.executeUpdate("INSERT INTO users VALUES(6,'Fournos')");
        statement.executeUpdate("INSERT INTO users VALUES(7,'Tasos')");
        statement.executeUpdate("INSERT INTO users VALUES(8,'Boula')");
        statement.executeUpdate("INSERT INTO users VALUES(9,'Panagiota')");
        statement.executeUpdate("INSERT INTO users VALUES(10,'Marinakis')");
        statement.executeUpdate("INSERT INTO users VALUES(11,'Xristoforos')");
        statement.executeUpdate("INSERT INTO transactions VALUES(0,1,'Panagiotis Panagiotidis',11,'Xristoforos',100, '2022/01/20 16:44:40',1)");
        statement.executeUpdate("INSERT INTO transactions VALUES(1,0,'Jumbo',10,'Marinakis',1000, '2022/01/21 16:44:40',1)");
        statement.executeUpdate("INSERT INTO emp_transactions VALUES(1,0)");
        statement.executeUpdate("INSERT INTO transactions VALUES(2,1,'Panagiotis Panagiotidis',11,'Xristoforos',100, '2022/01/22 16:44:40',0)");
        statement.executeUpdate("INSERT INTO transactions VALUES(3,4,'Kritikos',8,'Boula',2000, '2022/01/23 16:44:40',1)");
        statement.executeUpdate("INSERT INTO emp_transactions VALUES(3,2)");
        statement.executeUpdate("INSERT INTO transactions VALUES(4,6,'Fournos',7,'Tasos',70, '2022/01/24 16:44:40',1)");
        statement.executeUpdate("INSERT INTO emp_transactions VALUES(4,4)");
        statement.executeUpdate("INSERT INTO transactions VALUES(5,3,'Nerotsoulithres',9,'Panagiota',500, '2022/01/25 16:44:40',1)");
        statement.executeUpdate("INSERT INTO emp_transactions VALUES(5,1)");
        statement.executeUpdate("INSERT INTO transactions VALUES(6,5,'Geusinous',7,'Tasos',300, '2022/01/26 16:44:40',1)");
        statement.executeUpdate("INSERT INTO emp_transactions VALUES(6,3)");
        account_id = 12;
        transID = 7;
        employee_id = 7;
    }

    public void start() throws SQLException, ClassNotFoundException {

        initComponents();
        main_menu.setVisible(true);
        try {
            Connection connect = Connect_db.get_connection(true);
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(accountID) FROM users");
            rs.next();
            int max = rs.getInt(1);
            account_id = max + 1;

            rs = st.executeQuery("SELECT MAX(transID) FROM transactions");
            rs.next();
            max = rs.getInt(1);
            transID = max;

            rs = st.executeQuery("SELECT MAX(emp_id) FROM employees");
            rs.next();
            max = rs.getInt(1);
            employee_id = max + 1;

        } catch (SQLException e) {
            pop_up_warning("No database detected, please start a new database", "Fatal Error", null);
        }
    }

    private void pop_up_warning(String message, String title, JFrame panel) {
        JOptionPane.showMessageDialog(panel, message, title, JOptionPane.WARNING_MESSAGE);

    }

    private void pop_up_info(String message, String title, JFrame panel) {
        JOptionPane.showMessageDialog(panel, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    //Action listeners for main frame
    private void new_acc(ActionEvent e) {
        textField_name.setBackground(Color.white);
        textField_credit.setBackground(Color.white);
        textField_dept.setBackground(Color.white);
        textField_balance.setBackground(Color.white);
        textField_employee_name.setBackground(Color.white);

        the_balance.setVisible(false);
        textField_balance.setVisible(false);

        register_frame.setVisible(true);
    }

    private void delete_acc(ActionEvent e) throws SQLException, ClassNotFoundException {

        textfield_delete_acc.setBackground(Color.white);
        textfield_delete_acc_id.setBackground(Color.white);
        delete_acc_fr.setVisible(true);
    }

    private void purchase(ActionEvent e) {
        purchase_client_name.setBackground(Color.white);
        purchase_client_id.setBackground(Color.white);
        purchase_dealer_name.setBackground(Color.white);
        purchase_dealer_id.setBackground(Color.white);
        purchase_amount.setBackground(Color.white);

        checkBox1.setSelected(false);
        label_employee_id.setVisible(false);
        textField_employee_id.setVisible(false);
        textField_employee_name_purchase.setVisible(false);
        label_employee_name.setVisible(false);

        purchase_frame.setVisible(true);
    }

    private void payment(ActionEvent e) {
        payment_amount_textfield.setBackground(Color.white);
        payment_id_textfield.setBackground(Color.white);
        payment_name_textfield.setBackground(Color.white);
        payment_frame.setVisible(true);
    }

    private void return_of_product(ActionEvent e) {
        return_name_textfield.setBackground(Color.white);
        return_id_textfield.setBackground(Color.white);
        return_tr_id_textfield2.setBackground(Color.white);
        return_frame.setVisible(true);
    }

    private void good_clients(ActionEvent e) throws SQLException, ClassNotFoundException {

        Connection connect = Connect_db.get_connection(true);
        Statement st = connect.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT consumer_name FROM consumers WHERE debt=0");

        if (resultSet.isBeforeFirst()) {
            buildTableModel(resultSet);
        } else {
            pop_up_info("There aren't any 'Good Clients' in the company", "F", null);
        }
    }

    private void bad_clients(ActionEvent e) throws SQLException, ClassNotFoundException {

        Connection connect = Connect_db.get_connection(true);
        Statement st = connect.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT consumer_name FROM consumers WHERE debt>0");

        if (resultSet.isBeforeFirst()) {
            buildTableModel(resultSet);
        } else {
            pop_up_info("There aren't any 'Bad Clients' in the company", "Pog", null);
        }
    }

    private void questions(ActionEvent e) {
        questions_all.setVisible(false);
        questions_employee.setVisible(false);
        questions_name_employee.setVisible(false);
        questions_id_employee.setVisible(false);
        questions_name_employee_textfield.setVisible(false);
        questions_id_employee_textfield.setVisible(false);
        questions_checkBox.setSelected(false);
        questions_frame.setVisible(true);
    }

    private void dealer_of_the_month(ActionEvent e) throws SQLException, ClassNotFoundException {

        Connection connect = Connect_db.get_connection(true);
        Statement st = connect.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT merchant_name, MAX(profit) FROM merchants");
        resultSet.next();
        String temp_string = "SELECT merchant_name, profit FROM merchants where profit = ?";
        PreparedStatement prepared_statement = connect.prepareStatement(temp_string);
        prepared_statement.setInt(1, resultSet.getInt("MAX(profit)"));
        resultSet = prepared_statement.executeQuery();

        if (resultSet.isBeforeFirst()) {
            buildTableModel(resultSet);
        } else {
            pop_up_info("There aren't any merchants in the company", "What?", null);
        }
    }

    private void init_database(ActionEvent e) throws SQLException, ClassNotFoundException {

        try {
            Connection connect = Connect_db.get_connection(true);
            init_database_frame.setVisible(true);
        } catch (SQLException exception) {
            create_db();
        }
    }

    //Action listeners for register frame
    private void company_button(ActionEvent e) {
        individual = false;
        merchant = false;

        date.setVisible(true);
        exp_date.setVisible(true);
        exp_date2.setVisible(true);

        the_balance.setVisible(false);
        textField_balance.setVisible(false);

        is_employee = true;

        textField_employee_name.setVisible(true);
        employee_name.setVisible(true);

    }

    private void individual_button(ActionEvent e) {
        individual = true;
        merchant = false;

        date.setVisible(true);
        exp_date.setVisible(true);
        exp_date2.setVisible(true);

        the_balance.setVisible(false);
        textField_balance.setVisible(false);

        textField_dept.setVisible(true);
        the_dept.setVisible(true);

        textField_employee_name.setText("");

        textField_employee_name.setVisible(false);
        employee_name.setVisible(false);

        is_employee = false;

        label4.setText("Credit Limit");
        the_balance.setText("The Balance");

    }

    private void spinner1StateChanged(ChangeEvent e) {

        String value = date.getValue().toString();
        date_array[0] = value.charAt(8);
        date_array[1] = value.charAt(9);
        date_array[2] = '/';
        date_array[3] = value.charAt(4);
        date_array[4] = value.charAt(5);
        date_array[5] = value.charAt(6);
        date_array[6] = '/';
        date_array[7] = value.charAt(value.length() - 4);
        date_array[8] = value.charAt(value.length() - 3);
        date_array[9] = value.charAt(value.length() - 2);
        date_array[10] = value.charAt(value.length() - 1);
    }

    private void merchant_button(ActionEvent e) {
        individual = true;
        merchant = true;

        date.setVisible(false);
        exp_date.setVisible(false);
        exp_date2.setVisible(false);

        the_balance.setVisible(true);
        textField_balance.setVisible(true);

        textField_dept.setVisible(true);
        the_dept.setVisible(true);

        textField_employee_name.setVisible(false);
        employee_name.setVisible(false);
        textField_employee_name.setText("");

        is_employee = false;

        label4.setText("Commission %");
        the_balance.setText("Profit");
    }

    private void register_button(ActionEvent e) throws SQLException, ClassNotFoundException {

        int error = 0;
        float balance_profit = 0, commission = 0, creditLimit = 0;

        textField_name.setBackground(Color.white);
        textField_credit.setBackground(Color.white);
        textField_dept.setBackground(Color.white);
        textField_balance.setBackground(Color.white);
        textField_employee_name.setBackground(Color.white);

        employee_name_str = textField_employee_name.getText();
        name_clientName = textField_name.getText();
        //test
        if (merchant_button.isSelected())
            merchant = true;
        else if (individual_button.isSelected() && !(company_button.isSelected()))
            individual = true;
        //test
        if (name_clientName.isEmpty()) {
            error = 1;
        }
        //Debt get
        try {
            dept = Float.parseFloat(textField_dept.getText());
        } catch (NumberFormatException exception) {
            error = 4;
        }
        //Credit limit/commission get
        try {
            if (merchant) {
                commission = Float.parseFloat(textField_credit.getText());
            } else {
                creditLimit = Float.parseFloat(textField_credit.getText());
            }
        } catch (NumberFormatException exception) {
            error = 2;
        }
        //Balance get
        balance_CompanyName_Profit = textField_balance.getText();
        if (merchant) {
            try {
                balance_profit = Float.parseFloat(textField_balance.getText());
                // System.out.println("BP -> "+balance_profit);//  ::Debug
            } catch (NumberFormatException exception) {
                error = 3;
            }
        } else
            balance_profit = creditLimit - dept;

        if (employee_name_str.isEmpty() && company_button.isSelected())
            error = 5;

        //Date adjusted to DB format
        String inp_date = date.getValue().toString();
        int inp_month = months.valueOf(inp_date.substring(4, 7)).ordinal() + 1;
        String edate = inp_date.split("T ")[1] + "/" + String.format("%02d", inp_month) + "/" + inp_date.substring(8, 10);
        String etime = DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
        expire_date = edate + " " + etime;
        //System.out.println(inp_date);
        //System.out.println("Date ->" + expire_date);

        if (error == 1) {
            textField_name.setBackground(new Color(255, 153, 153));
            pop_up_warning("You must fill the \"Name\" text field!", "Warning", register_frame);
        }
        if (error == 2) {
            textField_credit.setBackground(new Color(255, 153, 153));
            pop_up_warning("You must fill the \"" + label4.getText() + "\" text field!", "Warning", register_frame);
        }
        if (error == 3) {
            textField_balance.setBackground(new Color(255, 153, 153));
            pop_up_warning("You must fill the \"" + the_balance.getText() + "\" text field!", "Warning", register_frame);
        }
        if (error == 4) {
            textField_dept.setBackground(new Color(255, 153, 153));
            pop_up_warning("You must fill the \"" + the_dept.getText() + "\" text field!", "Warning", register_frame);
        }
        if (error == 5) {
            textField_employee_name.setBackground(new Color(255, 153, 153));
            pop_up_warning("You must fill the \"Employee\" text field!", "Warning", register_frame);
        }


        if (error == 0) {
            Connection connect = Connect_db.get_connection(true);

            if (merchant) {
                String temp_string = "INSERT INTO merchants VALUES(?, ?, ?, ?, ?)";
                PreparedStatement prepared_statement = connect.prepareStatement(temp_string);
                prepared_statement.setInt(1, account_id);
                prepared_statement.setString(2, name_clientName);
                prepared_statement.setFloat(3, commission);
                prepared_statement.setFloat(4, dept);
                prepared_statement.setFloat(5, balance_profit);
                prepared_statement.executeUpdate();
            } else {
                String temp_string = "INSERT INTO consumers VALUES(?, ?, ?, ?, ?, ?)";
                PreparedStatement prepared_statement = connect.prepareStatement(temp_string);
                prepared_statement.setInt(1, account_id);
                prepared_statement.setString(2, name_clientName);
                prepared_statement.setFloat(3, dept);
                prepared_statement.setFloat(4, creditLimit);
                prepared_statement.setString(5, expire_date);
                prepared_statement.setFloat(6, balance_profit);
                prepared_statement.executeUpdate();
                if (!individual) {
                    temp_string = "INSERT INTO corporates VALUES(?)";
                    prepared_statement = connect.prepareStatement(temp_string);
                    prepared_statement.setInt(1, account_id);
                    prepared_statement.executeUpdate();
                    if (is_employee) {
                        temp_string = "INSERT INTO employees VALUES(?, ?, ?)";
                        prepared_statement = connect.prepareStatement(temp_string);
                        prepared_statement.setInt(1, account_id);
                        prepared_statement.setInt(2, employee_id);
                        prepared_statement.setString(3, employee_name_str);
                        prepared_statement.executeUpdate();
                        employee_id++;

                        extra_employee.setVisible(true);

                    }
                }
            }

            String temp_string = "INSERT INTO users VALUES(?, ?)";
            PreparedStatement prepared_statement = connect.prepareStatement(temp_string);
            prepared_statement.setInt(1, account_id);
            prepared_statement.setString(2, name_clientName);
            prepared_statement.executeUpdate();
            if (is_employee) {
                pop_up_info("Account created successfully, account ID: " + account_id + " employee ID: " + (employee_id - 1) + ".", "Success", null);
            } else {
                pop_up_info("Account created successfully, account ID: " + account_id + ".", "Success", null);
            }
            account_id += 1;
            merchant = false;
            individual = false;
        }
    }

    //Purchase
    private void register_cancel(ActionEvent e) {
        register_frame.dispose();
    }

    private void create_new_database(ActionEvent e) throws SQLException, ClassNotFoundException {
        Connection connect = Connect_db.get_connection(true);
        Statement statement = connect.createStatement();
        statement.executeUpdate("DROP DATABASE cccdb");
        statement.close();
        connect.close();
        create_db();
        pop_up_info("Database created successfully", "Success", init_database_frame);
        init_database_frame.setVisible(false);
    }

    private void cancel(ActionEvent e) {
        init_database_frame.dispose();
    }

    private void delete_button(ActionEvent e) throws SQLException, ClassNotFoundException {

        String name;
        int id = -1, error = 0;
        Connection connect = Connect_db.get_connection(true);
        String temp_string;

        textfield_delete_acc.setBackground(Color.white);
        textfield_delete_acc_id.setBackground(Color.white);

        name = textfield_delete_acc.getText();

        if (name.isEmpty()) {

            pop_up_warning("Incorrect account name input!", "Error", null);
            textfield_delete_acc.setBackground(new Color(255, 153, 153));
            error = 1;
        }

        try {
            id = Integer.parseInt(textfield_delete_acc_id.getText());
        } catch (NumberFormatException exception) {
            pop_up_warning("Incorrect id input!", "Error", null);
            textfield_delete_acc_id.setBackground(new Color(255, 153, 153));
            error = 2;
        }

        if (error == 0) {
            temp_string = ("SELECT * FROM users WHERE username=? and accountID = ?");
            PreparedStatement prepared_statement = connect.prepareStatement(temp_string);
            prepared_statement.setString(1, name);
            prepared_statement.setInt(2, id);
            ResultSet resultSet = prepared_statement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                //Diagrafi apo to user table

                temp_string = ("DELETE FROM users WHERE accountID=?");
                prepared_statement = connect.prepareStatement(temp_string);
                prepared_statement.setInt(1, id);
                prepared_statement.executeUpdate();

                //Diagrafi apo to merchant table (if merchant)
                temp_string = ("SELECT * FROM merchants WHERE accountID=?");
                prepared_statement = connect.prepareStatement(temp_string);
                prepared_statement.setInt(1, id);
                resultSet = prepared_statement.executeQuery();

                if (resultSet.isBeforeFirst()) {
                    temp_string = ("DELETE FROM merchants WHERE accountID=?");
                    prepared_statement = connect.prepareStatement(temp_string);
                    prepared_statement.setInt(1, id);
                    prepared_statement.executeUpdate();
                } else {

                    //Diagrafi apo to consumer table
                    temp_string = ("SELECT * FROM consumers WHERE accountID=?");
                    prepared_statement = connect.prepareStatement(temp_string);
                    prepared_statement.setInt(1, id);
                    resultSet = prepared_statement.executeQuery();

                    if (resultSet.isBeforeFirst()) {
                        temp_string = ("DELETE FROM consumers WHERE accountID=?");
                        prepared_statement = connect.prepareStatement(temp_string);
                        prepared_statement.setInt(1, id);
                        prepared_statement.executeUpdate();
                    }

                    //kai ean einai corporate
                    temp_string = ("SELECT * FROM corporates WHERE accountID=?");
                    prepared_statement = connect.prepareStatement(temp_string);
                    prepared_statement.setInt(1, id);
                    resultSet = prepared_statement.executeQuery();

                    if (resultSet.isBeforeFirst()) {
                        temp_string = ("DELETE FROM corporates WHERE accountID=?");
                        prepared_statement = connect.prepareStatement(temp_string);
                        prepared_statement.setInt(1, id);
                        prepared_statement.executeUpdate();
                    }

                    //kai olous tous employees
                    temp_string = ("SELECT * FROM employees WHERE accountID=?");
                    prepared_statement = connect.prepareStatement(temp_string);
                    prepared_statement.setInt(1, id);
                    resultSet = prepared_statement.executeQuery();

                    if (resultSet.isBeforeFirst()) {
                        temp_string = ("DELETE FROM employees WHERE accountID=?");
                        prepared_statement = connect.prepareStatement(temp_string);
                        prepared_statement.setInt(1, id);
                        prepared_statement.executeUpdate();
                    }
                }
                account_id--;
                pop_up_info("Account deleted successfully", "Success!", null);
                delete_acc_fr.dispose();
            } else {
                pop_up_warning("There is no user with username = " + name + " and accountID = " + id, "Error", null);
            }
        }
    }

    private void delete_cancel_button(ActionEvent e) {
        delete_acc_fr.dispose();
    }

    private void purchase_button(ActionEvent e) throws SQLException, ClassNotFoundException {

        purchase_client_name.setBackground(Color.white);
        purchase_client_id.setBackground(Color.white);
        purchase_dealer_name.setBackground(Color.white);
        purchase_dealer_id.setBackground(Color.white);
        purchase_amount.setBackground(Color.white);
        textField_employee_name_purchase.setBackground(Color.white);
        textField_employee_id.setBackground(Color.white);

        boolean inperr = false, emptrans = true; //Input error handle
        Color mildred = new Color(255, 153, 153); //Error box color

        int dealer_id, client_id;
        int emp_id = -1;

        float amount, debt, credlim, balance, commission, profit;
        dealer_id = client_id = -1;
        amount = credlim = debt = balance = profit = commission = -1;
        //String con_date;   :::Debug
        String curr_date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()); //current date
        String c_exp_date = "";//Consumer expiration date
        String strform = "^[a-zA-Z _]*$"; //client & dealer name format, alphabetic plus spaces & underscores
        String dealer_name = new String(purchase_dealer_name.getText());
        String client_name = new String(purchase_client_name.getText());
        String emp_name = new String(textField_employee_name_purchase.getText());
        String emp_id_s = new String(textField_employee_id.getText());

        if ((emp_id_s.isEmpty() || emp_name.isEmpty()) && checkBox1.isSelected()) {
            textField_employee_name_purchase.setBackground(mildred);
            textField_employee_id.setBackground(mildred);
            pop_up_info("The Employee ID / Name fields must be non-empty.", "Invalid Employee", null);
            emptrans = false;
            inperr = true;
        }  else if (checkBox1.isSelected())  {
            try {
                emp_id = Integer.parseInt(emp_id_s);
            } catch (NumberFormatException ex) {
                textField_employee_id.setBackground(mildred);
                pop_up_info("The Employee ID must be an integer & non-empty.", "Invalid Employee ID", null);
                inperr = true;
            }
            if (!emp_name.matches(strform)) {
                pop_up_info("The employee's name can only contain letters of the alphabet, spaces & underscores.", "Invalid Employee", null);
                textField_employee_name_purchase.setBackground(mildred);
                inperr = true;
            }
        }
        else {
            emptrans = false;
        }
        if (dealer_name.isEmpty()) {
            pop_up_info("The merchant's name can't be empty.", "Invalid Merchant", null);
            purchase_dealer_name.setBackground(mildred);
            inperr = true;
        } else if (!dealer_name.matches(strform)) {
            pop_up_info("The merchant's name can only contain letters of the alphabet, spaces & underscores.", "Invalid Merchant", null);
            purchase_dealer_name.setBackground(mildred);
            inperr = true;
        }
        if (client_name.isEmpty()) {
            pop_up_info("The consumer's name can't be empty.", "Invalid Consumer", null);
            purchase_client_name.setBackground(mildred);
            inperr = true;
        } else if (!client_name.matches(strform)) {
            pop_up_info("The consumer's name can only contain letters of the alphabet, spaces & underscores.", "Invalid Consumer", null);
            purchase_client_name.setBackground(mildred);
            inperr = true;
        }

        try {
            client_id = Integer.parseInt(purchase_client_id.getText());
        } catch (NumberFormatException ex) {
            purchase_client_id.setBackground(mildred);
            pop_up_info("The Account ID must be an integer & non-empty.", "Invalid Consumer account", null);
            inperr = true;
        }
        try {
            dealer_id = Integer.parseInt(purchase_dealer_id.getText());
        } catch (NumberFormatException ex) {
            purchase_dealer_id.setBackground(mildred);
            pop_up_info("The Account ID must be an integer & non-empty.", "Invalid Merchant account", null);
            inperr = true;
        }

        try {
            amount = Float.parseFloat(purchase_amount.getText());
        } catch (NumberFormatException ex) {
            purchase_amount.setBackground(mildred);
            pop_up_info("Transaction amount must be a Float & non-empty.", "Invalid Transaction", null);
            inperr = true;
        }
        if (amount <= 0) {
            purchase_amount.setBackground(mildred);
            pop_up_info("Transaction amount must be greater than zero.", "Invalid Transaction", null);
            inperr = true;
        }

        if (inperr) {
            return;
        }

        Connection conn = Connect_db.get_connection(true);
        String sqlQuery = ("SELECT * FROM consumers WHERE accountID=? AND consumer_name=?");
        PreparedStatement prepstat = conn.prepareStatement(sqlQuery);

        prepstat.setInt(1, client_id);
        prepstat.setString(2, client_name);
        ResultSet rs = prepstat.executeQuery();
        //Consumer data retrieve :::
        if (rs.next()) {
            // Retrieve by column name
            //con_date=rs.getString("exp_date")                         //:::Debug
            //System.out.println("expiry date:" + con_date              //:::Debug
            debt = rs.getFloat("debt");
            //System.out.println("Debt ->:" + debt);                     //:::Debug
            credlim = rs.getFloat("credit_limit");
            //System.out.println("CreditLimit ->:" + credlim);           //:::Debug
            balance = rs.getFloat("balance");
            //System.out.println("Balance ->:" + balance);              //:::Debug
            c_exp_date = rs.getString("exp_date");
        } else { //ERROR ::: den yparxei to zeugari client id & name
            purchase_client_name.setBackground(mildred);
            purchase_client_id.setBackground(mildred);
            pop_up_info("Incompatible Client ID/Name pair", "Invalid Credentials", null);
            inperr = true;
        }

        sqlQuery = ("SELECT * FROM merchants WHERE accountID=? AND merchant_name=?");
        prepstat = conn.prepareStatement(sqlQuery);
        prepstat.setInt(1, dealer_id);
        prepstat.setString(2, dealer_name);
        rs = prepstat.executeQuery();
        //Merchant data retrieve :::
        if (rs.next()) {
            // Retrieve by column name
            commission = rs.getFloat("commission");
            //System.out.println("Comission ->:" + commission);              //:::Debug
            profit = rs.getFloat("profit");
            //System.out.println("Profit ->:" + profit);              //:::Debug
        } else { //ERROR ::: den yparxei to zeugari merchant id & name
            purchase_dealer_name.setBackground(mildred);
            purchase_dealer_id.setBackground(mildred);
            pop_up_info("Incompatible Merchant ID/Name pair", "Invalid Credentials", null);
            inperr = true;
        }
        if (inperr) {
            return;
        }
        if ((credlim - debt) < amount && balance < amount) { //If consumer funds insufficient, cancel transaction
            pop_up_info("Consumer funds insufficient to conduct this transaction.", "Invalid Transaction", null);
            inperr = true;
        }
        if (sys_date_compare(c_exp_date, curr_date) < 1) { //If consumer account is expired, sys_date_compare gives 0 or -1 (only for consumers)
            purchase_client_name.setBackground(mildred);
            purchase_client_id.setBackground(mildred);
            pop_up_info("Consumer account is expired.", "Invalid Consumer account", null);
            inperr = true;
        }
        if (inperr) {
            return;
        }
        //Corporation modifications

        if (emptrans) {
            sqlQuery = ("SELECT * FROM employees WHERE accountID=? AND emp_id=? AND emp_name=?");
            prepstat = conn.prepareStatement(sqlQuery);
            prepstat.setInt(1, client_id);
            prepstat.setInt(2, emp_id);
            prepstat.setString(3, emp_name);
            rs = prepstat.executeQuery();
            if (!rs.next()) {
                textField_employee_id.setBackground(mildred);
                textField_employee_name_purchase.setBackground(mildred);
                pop_up_info("Employee: [" + emp_id + "] doesn't belong in account. Transaction cancelled.", "Employee Invalid", null);
                return;
            } else {
                sqlQuery = ("INSERT INTO emp_transactions VALUES(?,?)");
                prepstat = conn.prepareStatement(sqlQuery);
                prepstat.setInt(1, (transID + 1));
                prepstat.setInt(2, emp_id);
                prepstat.executeUpdate();
            }
        }

        //Consumer modifications (debt rises on purchase, credlim remains unchanged)
        //                         (balance<0 isn't handled, as it's checked above)
        debt += amount;
        balance = credlim - debt;

        sqlQuery = ("UPDATE consumers SET balance = ?, credit_limit = ?, debt = ? WHERE accountID = ?");
        prepstat = conn.prepareStatement(sqlQuery);
        prepstat.setFloat(1, balance);
        prepstat.setFloat(2, credlim);
        prepstat.setFloat(3, debt);
        prepstat.setInt(4, client_id);

        prepstat.executeUpdate();

        //Merchant modifications
        profit += amount - (commission / 100) * amount;

        sqlQuery = ("UPDATE merchants SET profit = ? WHERE accountID = ?");
        prepstat = conn.prepareStatement(sqlQuery);
        prepstat.setFloat(1, profit);
        prepstat.setInt(2, dealer_id);

        prepstat.executeUpdate();

        //Transaction modifications
        transID++;

        sqlQuery = ("INSERT INTO transactions VALUES (?, ?, ?, ?, ?, ?, ?, 1)");
        prepstat = conn.prepareStatement(sqlQuery);
        prepstat.setInt(1, transID);
        prepstat.setInt(2, client_id);
        prepstat.setString(3, client_name);
        prepstat.setInt(4, dealer_id);
        prepstat.setString(5, dealer_name);
        prepstat.setFloat(6, amount);
        prepstat.setString(7, curr_date);

        prepstat.executeUpdate();
        prepstat.close();
        conn.close();

        pop_up_info("Purchase transaction successful. Receipt ID: *[" + transID + "]*", "Success!", null);

    }

    private void cancel_button_purchase(ActionEvent e) {
        purchase_frame.dispose();
    }

    private void Employee_checkbox(ActionEvent e) {

        if (checkBox1.isSelected()) {
            label_employee_id.setVisible(true);
            textField_employee_id.setVisible(true);
            textField_employee_name_purchase.setVisible(true);
            label_employee_name.setVisible(true);

        } else {
            label_employee_id.setVisible(false);
            textField_employee_id.setVisible(false);
            textField_employee_name_purchase.setVisible(false);
            label_employee_name.setVisible(false);
        }

    }

    //Return
    private void cancel_return(ActionEvent e) {
        return_frame.dispose();
    }

    private void return_button(ActionEvent e) throws SQLException, ClassNotFoundException {

        String name = return_name_textfield.getText(), temp_string, consumer_name = null, merchant_name = null, new_date = null;
        int id = -1, trans_id = -1, error = 0, merchant_id = -1;
        float profit = 0, dept = 0, trans_amount = 0, balance = 0, credit_limit = 0, commission = 0, new_trans_amount = 0;
        PreparedStatement prepared_statement;
        ResultSet resultSet;
        Connection connect = Connect_db.get_connection(true);

        return_name_textfield.setBackground(Color.white);
        return_id_textfield.setBackground(Color.white);
        return_tr_id_textfield2.setBackground(Color.white);

        if (name.isEmpty()) {

            pop_up_warning("Incorrect account name input!", "Error", null);
            return_name_textfield.setBackground(new Color(255, 153, 153));
            error = 1;
        }

        try {
            id = Integer.parseInt(return_id_textfield.getText());
        } catch (NumberFormatException exception) {
            pop_up_warning("Incorrect account id input!", "Error", null);
            return_id_textfield.setBackground(new Color(255, 153, 153));
            error = 2;
        }

        try {
            trans_id = Integer.parseInt((return_tr_id_textfield2.getText()));
        } catch (NumberFormatException exception) {
            pop_up_warning("Incorrect account id input!", "Error", null);
            return_tr_id_textfield2.setBackground(new Color(255, 153, 153));
            error = 3;
        }

        if (error == 0) {
            temp_string = ("SELECT * FROM users WHERE username=? and accountID = ?");
            prepared_statement = connect.prepareStatement(temp_string);
            prepared_statement.setString(1, name);
            prepared_statement.setInt(2, id);
            resultSet = prepared_statement.executeQuery();

            if (resultSet.isBeforeFirst()) {

                temp_string = ("SELECT * FROM transactions WHERE consumer_acc_id = ? and transID = ?");
                prepared_statement = connect.prepareStatement(temp_string);
                prepared_statement.setInt(1, id);
                prepared_statement.setInt(2, trans_id);
                resultSet = prepared_statement.executeQuery();

                if (resultSet.isBeforeFirst()) {

                    resultSet.next();
                    merchant_id = resultSet.getInt("merchant_acc_id");
                    trans_amount = resultSet.getFloat("amount");
                    consumer_name = resultSet.getString("consumer_name");
                    merchant_name = resultSet.getString("merchant_name");
                    new_date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());

                    temp_string = ("SELECT * FROM merchants WHERE accountID = ?");
                    prepared_statement = connect.prepareStatement(temp_string);
                    prepared_statement.setInt(1, merchant_id);
                    resultSet = prepared_statement.executeQuery();

                    if (resultSet.isBeforeFirst()) {

                        resultSet.next();
                        profit = resultSet.getFloat("profit");
                        commission = resultSet.getFloat("commission");
                        dept = resultSet.getFloat("debt");
                        new_trans_amount = trans_amount - (commission / 100) * trans_amount;

                        profit -= new_trans_amount;

                        if (profit < 0) {

                            dept = dept - profit;
                            profit = 0;

                            temp_string = ("UPDATE merchants SET profit = ?, debt = ? WHERE accountID = ?");
                            prepared_statement = connect.prepareStatement(temp_string);
                            prepared_statement.setFloat(1, profit);
                            prepared_statement.setFloat(2, dept);
                            prepared_statement.setInt(3, merchant_id);
                            prepared_statement.executeUpdate();
                        } else {

                            temp_string = ("UPDATE merchants SET profit = ? WHERE accountID = ?");
                            prepared_statement = connect.prepareStatement(temp_string);
                            prepared_statement.setFloat(1, profit);
                            prepared_statement.setInt(2, merchant_id);
                            prepared_statement.executeUpdate();
                        }
                    }

                    temp_string = ("SELECT * FROM consumers WHERE accountID = ?");
                    prepared_statement = connect.prepareStatement(temp_string);
                    prepared_statement.setInt(1, id);
                    resultSet = prepared_statement.executeQuery();

                    if (resultSet.isBeforeFirst()) {

                        resultSet.next();
                        credit_limit = resultSet.getFloat("credit_limit");
                        dept = resultSet.getFloat("debt");
                        credit_limit = credit_limit + trans_amount;

                        temp_string = ("UPDATE consumers SET credit_limit = ? WHERE accountID = ?");
                        prepared_statement = connect.prepareStatement(temp_string);
                        prepared_statement.setFloat(1, credit_limit);
                        prepared_statement.setInt(2, id);
                        prepared_statement.executeUpdate();

                        temp_string = ("UPDATE consumers SET balance = ? WHERE accountID = ?");
                        prepared_statement = connect.prepareStatement(temp_string);
                        prepared_statement.setFloat(1, (credit_limit - dept));
                        prepared_statement.setInt(2, id);
                        prepared_statement.executeUpdate();
                    }

                    temp_string = ("INSERT INTO transactions VALUES (?, ?, ?, ?, ?, ?, ?, 0)");
                    prepared_statement = connect.prepareStatement(temp_string);
                    transID++;
                    prepared_statement.setInt(1, transID);
                    prepared_statement.setInt(2, id);
                    prepared_statement.setString(3, consumer_name);
                    prepared_statement.setInt(4, merchant_id);
                    prepared_statement.setString(5, merchant_name);
                    prepared_statement.setFloat(6, trans_amount);
                    prepared_statement.setString(7, new_date);
                    prepared_statement.executeUpdate();

                    pop_up_info("Returned product successfully", "Success!", null);
                } else {
                    pop_up_warning("There is no transaction between user with accountID = " + id + " and merchant with accountID = " + trans_id, "Error", null);
                }
            } else {
                pop_up_warning("There is no user with username = " + name + " and accountID = " + id, "Error", null);
            }
        }
    }

    //Payment
    private void pay_button(ActionEvent e) throws SQLException, ClassNotFoundException {

        int error = 0, id = -1;
        float old_dept = 0, amount = 0, new_dept = 0, profit = 0, credit_limit = 0, balance = 0;
        String name, temp_string;
        Connection connect = Connect_db.get_connection(true);
        PreparedStatement prepared_statement;
        ResultSet resultSet;

        payment_amount_textfield.setBackground(Color.white);
        payment_id_textfield.setBackground(Color.white);
        payment_name_textfield.setBackground(Color.white);

        name = payment_name_textfield.getText();

        if (name.isEmpty()) {

            pop_up_warning("Incorrect account name input!", "Error", null);
            payment_name_textfield.setBackground(new Color(255, 153, 153));
            error = 1;
        }

        try {
            id = Integer.parseInt(payment_id_textfield.getText());
        } catch (NumberFormatException exception) {
            pop_up_warning("Incorrect account id input!", "Error", null);
            payment_id_textfield.setBackground(new Color(255, 153, 153));
            error = 2;
        }

        try {
            amount = Float.parseFloat(payment_amount_textfield.getText());
        } catch (NumberFormatException exception) {
            pop_up_warning("Incorrect amount input!", "Error", null);
            payment_amount_textfield.setBackground(new Color(255, 153, 153));
            error = 3;
        }

        if (error == 0) {

            temp_string = ("SELECT * FROM users WHERE accountID=? and username = ?");
            prepared_statement = connect.prepareStatement(temp_string);
            prepared_statement.setInt(1, id);
            prepared_statement.setString(2, name);
            resultSet = prepared_statement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                temp_string = ("SELECT * FROM merchants WHERE accountID=?");
                prepared_statement = connect.prepareStatement(temp_string);
                prepared_statement.setInt(1, id);
                resultSet = prepared_statement.executeQuery();

                if (resultSet.isBeforeFirst()) {

                    resultSet.next();
                    old_dept = resultSet.getFloat("debt");

                    new_dept = old_dept - amount;

                    if (new_dept < 0) {

                        profit = resultSet.getFloat("profit") - new_dept;
                        new_dept = 0;

                        temp_string = ("UPDATE merchants SET debt = ?, profit = ? WHERE accountID = ?");
                        prepared_statement = connect.prepareStatement(temp_string);
                        prepared_statement.setFloat(1, (new_dept));
                        prepared_statement.setFloat(2, (profit));
                        prepared_statement.setInt(3, id);
                        prepared_statement.executeUpdate();
                    } else {
                        temp_string = ("UPDATE merchants SET debt = ? WHERE accountID = ?");
                        prepared_statement = connect.prepareStatement(temp_string);
                        prepared_statement.setFloat(1, (new_dept));
                        prepared_statement.setInt(2, id);
                        prepared_statement.executeUpdate();
                    }

                } else {
                    temp_string = ("SELECT * FROM consumers WHERE accountID=?");
                    prepared_statement = connect.prepareStatement(temp_string);
                    prepared_statement.setInt(1, id);
                    resultSet = prepared_statement.executeQuery();

                    if (resultSet.isBeforeFirst()) {

                        resultSet.next();
                        old_dept = resultSet.getFloat("debt");
                        credit_limit = resultSet.getFloat("credit_limit");
                        balance = resultSet.getFloat("balance");

                        old_dept = old_dept - amount;

                        if (old_dept < 0) {

                            credit_limit = credit_limit - old_dept;
                            old_dept = 0;

                            temp_string = ("UPDATE consumers SET debt = ?, credit_limit = ? WHERE accountID = ?");
                            prepared_statement = connect.prepareStatement(temp_string);
                            prepared_statement.setFloat(1, old_dept);
                            prepared_statement.setFloat(2, credit_limit);
                            prepared_statement.setInt(3, id);
                            prepared_statement.executeUpdate();
                        } else {
                            temp_string = ("UPDATE consumers SET debt = ? WHERE accountID = ?");
                            prepared_statement = connect.prepareStatement(temp_string);
                            prepared_statement.setFloat(1, old_dept);
                            prepared_statement.setInt(2, id);
                            prepared_statement.executeUpdate();
                        }

                        temp_string = ("UPDATE consumers SET balance = ? WHERE accountID = ?");
                        prepared_statement = connect.prepareStatement(temp_string);
                        prepared_statement.setFloat(1, (credit_limit - old_dept));
                        prepared_statement.setInt(2, id);
                        prepared_statement.executeUpdate();
                    }
                }
                pop_up_info("Updated dept successfully", "Success", null);
            } else {
                pop_up_warning("There is no user with username = " + name + " and accountID = " + id, "Error", null);
            }
        }
    }

    private void cancel_pay(ActionEvent e) {
        payment_frame.dispose();
    }

    //Questions
    private void questions_company_button(ActionEvent e) {

        if (questions_checkBox.isSelected()) {

            questions_all.setVisible(true);
            questions_employee.setVisible(true);
            questions_all.setSelected(true);

        } else {

            questions_all.setVisible(false);
            questions_employee.setVisible(false);
            questions_name_employee.setVisible(false);
            questions_id_employee.setVisible(false);
            questions_name_employee_textfield.setVisible(false);
            questions_id_employee_textfield.setVisible(false);
        }
    }

    private void questions_all(ActionEvent e) {
        questions_name_employee.setVisible(false);
        questions_id_employee.setVisible(false);
        questions_name_employee_textfield.setVisible(false);
        questions_id_employee_textfield.setVisible(false);
    }

    private void questions_employee(ActionEvent e) {
        questions_name_employee.setVisible(true);
        questions_id_employee.setVisible(true);
        questions_name_employee_textfield.setVisible(true);
        questions_id_employee_textfield.setVisible(true);
    }

    private void questions_results(ActionEvent e) throws SQLException, ClassNotFoundException {

        questions_id_textfield.setBackground(Color.white);
        questions_name_textfield.setBackground(Color.white);
        questions_id_employee_textfield.setBackground(Color.white);
        questions_name_employee_textfield.setBackground(Color.white);

        String acc_id_s = questions_id_textfield.getText();
        String acc_name = questions_name_textfield.getText();
        int acc_id = -1;

        String emp_id_s = questions_id_employee_textfield.getText();
        String emp_name = questions_name_employee_textfield.getText();
        int emp_id = -1;

        String q_from = spinner1_questions_from.getValue().toString();
        String q_to = spinner2_questions_to.getValue().toString();

        JFrame f;

        //Transaction row variables
        int transid, consid, mercid, transtype;
        String consname, mercname, date;
        float amount;

        //Error & special case handling
        boolean inperr = false, emptbl = false, notfound = true;
        String strform = "^[a-zA-Z _]*$"; //client & dealer name format, alphabetic plus spaces & underscores
        Color mildred = new Color(255, 153, 153); //Error box color

        //Account exceptions ::
        if (acc_name.isEmpty()) {
            questions_name_textfield.setBackground(mildred);
            pop_up_info("The account's name can't be empty.", "Invalid account name", null);
            inperr = true;
        } else if (!acc_name.matches(strform)) {
            questions_name_textfield.setBackground(mildred);
            pop_up_info("The account's name can only contain letters of the alphabet, spaces & underscores.", "Invalid account name", null);
            inperr = true;
        }

        if (acc_id_s.isEmpty()) {
            questions_id_textfield.setBackground(mildred);
            pop_up_info("The Account ID must not be empty.", "Invalid account", null);
            inperr = true;
        } else {
            try {
                acc_id = Integer.parseInt(acc_id_s);
            } catch (NumberFormatException ex) {
                questions_id_textfield.setBackground(mildred);
                pop_up_info("The Account ID must be an integer.", "Invalid account", null);
                inperr = true;
            }
        }
        //Employee exceptions ::
        if (questions_employee.isSelected() && questions_checkBox.isSelected()) {
            emptbl = true;    //Signifies that results must be filtered by employee

            if (emp_name.isEmpty()) {
                questions_name_employee_textfield.setBackground(mildred);
                pop_up_info("The employee's name can't be empty.", "Invalid Employee name", null);
                inperr = true;
            } else if (!emp_name.matches(strform)) {
                questions_name_employee_textfield.setBackground(mildred);
                pop_up_info("The employee's name can only contain letters of the alphabet, spaces & underscores.", "Invalid Employee name", null);
                inperr = true;
            }

            if (emp_id_s.isEmpty()) {
                questions_id_employee_textfield.setBackground(mildred);
                pop_up_info("The employee's ID must not be empty.", "Invalid Employee ID", null);
                inperr = true;
            } else {
                try {
                    emp_id = Integer.parseInt(emp_id_s);
                } catch (NumberFormatException ex) {
                    questions_id_employee_textfield.setBackground(mildred);
                    pop_up_info("The employee's ID must be an integer.", "Invalid Employee ID", null);
                    inperr = true;
                }
            }

        }

        if (inperr)
            return;
        else
            inperr = false;

        Connection conn = Connect_db.get_connection(true);
        Statement stmt = conn.createStatement();
        Statement qstmt = conn.createStatement();
        Statement qqstmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM transactions");
        ResultSet qrs = qstmt.executeQuery("SELECT * FROM emp_transactions");
        ResultSet qqrs = qqstmt.executeQuery("SELECT * FROM employees");

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);

        tableModel.addColumn("transID");
        tableModel.addColumn("consumer_acc_id");
        tableModel.addColumn("consumer_name");
        tableModel.addColumn("merchant_acc_id");
        tableModel.addColumn("merchant_name");
        tableModel.addColumn("amount");
        tableModel.addColumn("date");
        tableModel.addColumn("transType");

        while (rs.next()) {
            transid = rs.getInt("transID");
            consid = rs.getInt("consumer_acc_id");
            consname = rs.getString("consumer_name");
            mercid = rs.getInt("merchant_acc_id");
            mercname = rs.getString("merchant_name");
            amount = rs.getFloat("amount");
            date = rs.getString("date");
            transtype = rs.getInt("transType");
            if ((db_date_compare(date, q_from) == 1 || db_date_compare(date, q_from) == 0) && ((db_date_compare(date, q_to) == -1 || db_date_compare(date, q_to) == 0))) {//Date check
                if ((mercname.equals(acc_name) && mercid == acc_id) || (consname.equals(acc_name) && consid == acc_id)) {//Account check
                    if (emptbl) {//Employee check
                        qrs = qstmt.executeQuery("SELECT * FROM emp_transactions WHERE emp_id=" + emp_id + " AND transID=" + transid);
                        qqrs = qqstmt.executeQuery("SELECT * FROM employees WHERE emp_id=" + emp_id + " AND emp_name='" + emp_name + "'");
                        if (qrs.next() && qqrs.next()) {
                            tableModel.insertRow(0, new Object[]{transid, consid, consname, mercid, mercname, amount, date, transtype});
                            notfound = false;
                        }
                    } else {
                        tableModel.insertRow(0, new Object[]{transid, consid, consname, mercid, mercname, amount, date, transtype});
                        notfound = false;
                    }
                }
            }
        }

        if (notfound) {
            pop_up_info("No transactions for those credentials or date range were found.", "Invalid credentials", null);
        } else {
            f = new JFrame();
            f.setSize(1750, 350);
            f.add(new JScrollPane(table));
            f.setVisible(true);
        }
        rs.close();
        qrs.close();
        qqrs.close();
        conn.close();
    }

    private int db_date_compare(String db_date, String input_date) {
        /*Compares DB format && input dates
         *Returns: {1 for >, -1 for <, 0 for ==}
         */

        StringBuilder sb;
        int inp_dd, inp_mm, inp_yy, db_dd, db_mm, db_yy;

        sb = new StringBuilder();
        sb.append(input_date.charAt(8));
        sb.append(input_date.charAt(9));
        inp_dd = Integer.parseInt(sb.toString());

        sb.setLength(0);
        sb.append(input_date.charAt(4));
        sb.append(input_date.charAt(5));
        sb.append(input_date.charAt(6));
        inp_mm = months.valueOf(sb.toString()).ordinal() + 1;

        sb.setLength(0);
        sb.append(input_date.charAt(input_date.length() - 4));
        sb.append(input_date.charAt(input_date.length() - 3));
        sb.append(input_date.charAt(input_date.length() - 2));
        sb.append(input_date.charAt(input_date.length() - 1));
        inp_yy = Integer.parseInt(sb.toString());

        sb.setLength(0);
        sb.append(db_date.charAt(8));
        sb.append(db_date.charAt(9));
        db_dd = Integer.parseInt(sb.toString());

        sb.setLength(0);
        sb.append(db_date.charAt(5));
        sb.append(db_date.charAt(6));
        db_mm = Integer.parseInt(sb.toString());

        sb.setLength(0);
        sb.append(db_date.charAt(0));
        sb.append(db_date.charAt(1));
        sb.append(db_date.charAt(2));
        sb.append(db_date.charAt(3));
        db_yy = Integer.parseInt(sb.toString());

        if ((db_yy * 2000 + db_mm * 100 + db_dd) < (inp_yy * 2000 + inp_mm * 100 + inp_dd))
            return -1;  //DB_DATE < INPUT_DATE
        else if ((db_yy * 2000 + db_mm * 100 + db_dd) > (inp_yy * 2000 + inp_mm * 100 + inp_dd))
            return 1;  //DB_DATE > INPUT_DATE
        else
            return 0;  //DB_DATE == INPUT_DATE
    }

    private int sys_date_compare(String date1, String date2) {
        /*Compares system dates
         *Equivalent to "DB dates"
         *Returns: {1 for 1>2, -1 for 1<2, 0 for 1==2}
         */

        StringBuilder sb;
        int date1_dd, date1_mm, date1_yy, date2_dd, date2_mm, date2_yy;

        sb = new StringBuilder();
        sb.append(date1.charAt(8));
        sb.append(date1.charAt(9));
        date1_dd = Integer.parseInt(sb.toString());

        sb.setLength(0);
        sb.append(date1.charAt(5));
        sb.append(date1.charAt(6));
        date1_mm = Integer.parseInt(sb.toString());

        sb.setLength(0);
        sb.append(date1.charAt(0));
        sb.append(date1.charAt(1));
        sb.append(date1.charAt(2));
        sb.append(date1.charAt(3));
        date1_yy = Integer.parseInt(sb.toString());

        sb.setLength(0);
        sb.append(date2.charAt(8));
        sb.append(date2.charAt(9));
        date2_dd = Integer.parseInt(sb.toString());

        sb.setLength(0);
        sb.append(date2.charAt(5));
        sb.append(date2.charAt(6));
        date2_mm = Integer.parseInt(sb.toString());

        sb.setLength(0);
        sb.append(date2.charAt(0));
        sb.append(date2.charAt(1));
        sb.append(date2.charAt(2));
        sb.append(date2.charAt(3));
        date2_yy = Integer.parseInt(sb.toString());

        if ((date1_yy * 2000 + date1_mm * 100 + date1_dd) < (date2_yy * 2000 + date2_mm * 100 + date2_dd))
            return -1;  //date1 < INPUT_DATE
        else if ((date1_yy * 2000 + date1_mm * 100 + date1_dd) > (date2_yy * 2000 + date2_mm * 100 + date2_dd))
            return 1;  //date1 > INPUT_DATE
        else
            return 0;  //date1 == INPUT_DATE
    }

    private void questions_cancel(ActionEvent e) {
        questions_frame.dispose();
    }

    private void spinner1_questions_fromStateChanged(ChangeEvent e) {
        String value = spinner1_questions_from.getValue().toString();
        date_array_from[0] = value.charAt(8);
        date_array_from[1] = value.charAt(9);
        date_array_from[2] = '/';
        date_array_from[3] = value.charAt(4);
        date_array_from[4] = value.charAt(5);
        date_array_from[5] = value.charAt(6);
        date_array_from[6] = '/';
        date_array_from[7] = value.charAt(value.length() - 4);
        date_array_from[8] = value.charAt(value.length() - 3);
        date_array_from[9] = value.charAt(value.length() - 2);
        date_array_from[10] = value.charAt(value.length() - 1);
    }

    private void spinner2_questions_toStateChanged(ChangeEvent e) {
        String value = spinner2_questions_to.getValue().toString();
        date_array_to[0] = value.charAt(8);
        date_array_to[1] = value.charAt(9);
        date_array_to[2] = '/';
        date_array_to[3] = value.charAt(4);
        date_array_to[4] = value.charAt(5);
        date_array_to[5] = value.charAt(6);
        date_array_to[6] = '/';
        date_array_to[7] = value.charAt(value.length() - 4);
        date_array_to[8] = value.charAt(value.length() - 3);
        date_array_to[9] = value.charAt(value.length() - 2);
        date_array_to[10] = value.charAt(value.length() - 1);
    }

    //Extra Employee
    private void add_employee(ActionEvent e) throws SQLException, ClassNotFoundException {

        String employee_name = new String(textField1.getText());
        String strform = "^[a-zA-Z _]*$"; //Employee name format, alphabetic plus spaces & underscores
        Color mildred = new Color(255, 153, 153); //Error box color

        //Employee name exceptions
        if (employee_name.isEmpty()) {
            textField1.setBackground(mildred);
            pop_up_info("The employee's name cannot be empty.", "Invalid Employee name", null);
            textField1.setBackground(Color.white);
            return;
        }
        if (!employee_name.matches(strform)) {
            textField1.setBackground(mildred); //Error box color);
            pop_up_info("The employee's name can only contain letters of the alphabet, spaces & underscores.", "Invalid Employee name", null);
            textField1.setBackground(Color.white);
            return;
        }

        extra_employee.dispose();
        textField1.setBackground(Color.white);
        textField1.setText("");
        extra_employee.setVisible(true);

        Connection connect = Connect_db.get_connection(true);
        String temp_string = "INSERT INTO employees VALUES(?, ?, ?)";
        PreparedStatement prepared_statement = connect.prepareStatement(temp_string);
        prepared_statement.setInt(1, (account_id - 1));
        /*When account is registered, account_id += 1
         *Therefore, extra employee belongs to (account_id-1)*/
        prepared_statement.setInt(2, employee_id);
        prepared_statement.setString(3, employee_name);
        prepared_statement.executeUpdate();
        pop_up_info("Extra employee [" + employee_id + "] was added successfully.", "Success", null);
        employee_id++;
        connect.close();
        prepared_statement.close();
    }

    private void cancel_extra_employee(ActionEvent e) {
        extra_employee.dispose();
        register_frame.dispose();
    }

    private void initComponents() {

        //Extra Employee
        extra_employee = new JFrame();
        label1_extra = new JLabel();
        label2_extra_employee_name = new JLabel();
        textField1 = new JTextField();
        add_employee = new JButton();
        cancel_button = new JButton();


        //======== extra_employee ========
        {
            extra_employee.setTitle("Add an Employee");
            extra_employee.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            var extra_employeeContentPane = extra_employee.getContentPane();
            extra_employeeContentPane.setLayout(null);

            //---- label1_extra ----
            label1_extra.setText("Would you like to add more Employees?");
            label1_extra.setHorizontalAlignment(SwingConstants.CENTER);
            label1_extra.setFont(label1_extra.getFont().deriveFont(label1_extra.getFont().getSize() + 7f));
            extra_employeeContentPane.add(label1_extra);
            label1_extra.setBounds(95, 35, 565, 80);

            //---- label2_extra_employee_name ----
            label2_extra_employee_name.setText("Employee Name");
            extra_employeeContentPane.add(label2_extra_employee_name);
            label2_extra_employee_name.setBounds(110, 150, 105, 25);
            extra_employeeContentPane.add(textField1);
            textField1.setBounds(110, 180, 105, textField1.getPreferredSize().height);

            //---- add_employee ----
            add_employee.setText("ADD!");
            add_employee.addActionListener(e -> {
                try {
                    add_employee(e);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            extra_employeeContentPane.add(add_employee);
            add_employee.setBounds(215, 315, 110, 50);

            //---- cancel_button ----
            cancel_button.setText("CANCEL");
            cancel_button.addActionListener(e -> cancel_extra_employee(e));
            extra_employeeContentPane.add(cancel_button);
            cancel_button.setBounds(410, 315, 110, 50);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < extra_employeeContentPane.getComponentCount(); i++) {
                    Rectangle bounds = extra_employeeContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = extra_employeeContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                extra_employeeContentPane.setMinimumSize(preferredSize);
                extra_employeeContentPane.setPreferredSize(preferredSize);
            }
            extra_employee.setSize(760, 465);
            extra_employee.setLocationRelativeTo(extra_employee.getOwner());
        }


        //Questions
        questions_frame = new JFrame();
        label1_questions = new JLabel();
        questions_name = new JLabel();
        questions_id = new JLabel();
        questions_id_textfield = new JTextField();
        questions_name_textfield = new JTextField();
        questions_checkBox = new JCheckBox();
        questions_all = new JRadioButton();
        questions_employee = new JRadioButton();
        questions_name_employee = new JLabel();
        questions_id_employee = new JLabel();
        questions_id_employee_textfield = new JTextField();
        questions_name_employee_textfield = new JTextField();
        label_from = new JLabel();
        spinner1_questions_from = new JSpinner();
        spinner2_questions_to = new JSpinner();
        questions_time = new JLabel();
        questions_results = new JButton();
        questions_cancel = new JButton();
        questions_icon = new JLabel();


        //======== questions_frame ========
        {
            questions_frame.setTitle("Questions");
            questions_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            var questions_frameContentPane = questions_frame.getContentPane();
            questions_frameContentPane.setLayout(null);

            //---- label1_questions ----
            label1_questions.setText("Questions?");
            label1_questions.setHorizontalAlignment(SwingConstants.CENTER);
            label1_questions.setFont(label1_questions.getFont().deriveFont(label1_questions.getFont().getSize() + 10f));
            questions_frameContentPane.add(label1_questions);
            label1_questions.setBounds(425, 25, 180, 55);

            //---- questions_name ----
            questions_name.setText("Account Name");
            questions_frameContentPane.add(questions_name);
            questions_name.setBounds(155, 175, 90, 30);

            //---- questions_id ----
            questions_id.setText("Accout ID");
            questions_frameContentPane.add(questions_id);
            questions_id.setBounds(245, 175, 70, 30);
            questions_frameContentPane.add(questions_id_textfield);
            questions_id_textfield.setBounds(245, 210, 100, 35);
            questions_frameContentPane.add(questions_name_textfield);
            questions_name_textfield.setBounds(150, 210, 100, 35);

            //---- questions_checkBox ----
            questions_checkBox.setText("Company");
            questions_checkBox.addActionListener(e -> questions_company_button(e));
            questions_frameContentPane.add(questions_checkBox);
            questions_checkBox.setBounds(165, 345, 90, questions_checkBox.getPreferredSize().height);

            //---- questions_all ----
            questions_all.setText("All");
            questions_all.addActionListener(e -> questions_all(e));
            questions_frameContentPane.add(questions_all);
            questions_all.setBounds(335, 350, 65, questions_all.getPreferredSize().height);

            //---- questions_employee ----
            questions_employee.setText("Employee");
            questions_employee.addActionListener(e -> questions_employee(e));
            questions_frameContentPane.add(questions_employee);
            questions_employee.setBounds(330, 445, 95, questions_employee.getPreferredSize().height);

            //---- questions_name_employee ----
            questions_name_employee.setText("Employee Name");
            questions_frameContentPane.add(questions_name_employee);
            questions_name_employee.setBounds(500, 425, 100, 30);

            //---- questions_id_employee ----
            questions_id_employee.setText("Employee ID");
            questions_frameContentPane.add(questions_id_employee);
            questions_id_employee.setBounds(600, 425, 90, 30);
            questions_frameContentPane.add(questions_id_employee_textfield);
            questions_id_employee_textfield.setBounds(590, 460, 100, 35);
            questions_frameContentPane.add(questions_name_employee_textfield);
            questions_name_employee_textfield.setBounds(495, 460, 100, 35);

            //---- label_from ----
            label_from.setText("From ----------------------------------------------> To");
            questions_frameContentPane.add(label_from);
            label_from.setBounds(645, 200, 295, 26);

            //---- spinner1_questions_from ----
            spinner1_questions_from.setModel(new SpinnerDateModel(new java.util.Date(1642875840000L), null, null, java.util.Calendar.DAY_OF_MONTH));
            spinner1_questions_from.addChangeListener(e -> spinner1_questions_fromStateChanged(e));
            questions_frameContentPane.add(spinner1_questions_from);
            spinner1_questions_from.setBounds(new Rectangle(new Point(600, 235), spinner1_questions_from.getPreferredSize()));

            //---- spinner2_questions_to ----
            spinner2_questions_to.setModel(new SpinnerDateModel(new java.util.Date(1642875840000L), null, null, java.util.Calendar.DAY_OF_MONTH));
            spinner2_questions_to.addChangeListener(e -> spinner2_questions_toStateChanged(e));
            questions_frameContentPane.add(spinner2_questions_to);
            spinner2_questions_to.setBounds(850, 235, 141, 30);

            //---- questions_time ----
            questions_time.setText("*The time here does not matter");
            questions_time.setFont(questions_time.getFont().deriveFont(questions_time.getFont().getSize() - 2f));
            questions_frameContentPane.add(questions_time);
            questions_time.setBounds(730, 265, 185, 16);

            //---- questions_results ----
            questions_results.setText("RESULTS");
            questions_results.setFont(questions_results.getFont().deriveFont(questions_results.getFont().getSize() + 4f));
            questions_results.setBackground(new Color(255, 255, 204));
            questions_results.addActionListener(e -> {
                try {
                    questions_results(e);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            questions_frameContentPane.add(questions_results);
            questions_results.setBounds(425, 615, 180, 70);

            //---- questions_cancel ----
            questions_cancel.setText("CANCEL");
            questions_cancel.setFont(questions_cancel.getFont().deriveFont(questions_cancel.getFont().getSize() + 2f));
            questions_cancel.addActionListener(e -> questions_cancel(e));
            questions_frameContentPane.add(questions_cancel);
            questions_cancel.setBounds(460, 690, 110, 30);

            //---- questions_icon ----
            questions_icon.setIcon(new ImageIcon(getClass().getResource("/util/download.png")));
            questions_frameContentPane.add(questions_icon);
            questions_icon.setBounds(715, 600, 290, 116);

            //Button Group
            ButtonGroup group = new ButtonGroup();
            group.add(questions_all);
            group.add(questions_employee);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < questions_frameContentPane.getComponentCount(); i++) {
                    Rectangle bounds = questions_frameContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = questions_frameContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                questions_frameContentPane.setMinimumSize(preferredSize);
                questions_frameContentPane.setPreferredSize(preferredSize);
            }
            questions_frame.setSize(1030, 795);
            questions_frame.setLocationRelativeTo(questions_frame.getOwner());
        }


        //Payment
        payment_frame = new JFrame();
        label1_title_payment = new JLabel();
        label1_payment = new JLabel();
        payment_name = new JLabel();
        payment_id = new JLabel();
        payment_id_textfield = new JTextField();
        payment_name_textfield = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        payment_amount = new JLabel();
        payment_amount_textfield = new JTextField();

        //======== payment_frame ========
        {
            payment_frame.setTitle("Payment");
            payment_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            var payment_frameContentPane = payment_frame.getContentPane();
            payment_frameContentPane.setLayout(null);

            //---- label1_title_payment ----
            label1_title_payment.setText("Payment");
            label1_title_payment.setHorizontalAlignment(SwingConstants.CENTER);
            label1_title_payment.setFont(label1_title_payment.getFont().deriveFont(label1_title_payment.getFont().getSize() + 15f));
            label1_title_payment.setForeground(Color.black);
            payment_frameContentPane.add(label1_title_payment);
            label1_title_payment.setBounds(375, 20, 280, 80);

            //---- label1_payment ----
            label1_payment.setIcon(new ImageIcon(getClass().getResource("/util/download.png")));
            payment_frameContentPane.add(label1_payment);
            label1_payment.setBounds(690, 605, 305, 116);

            //---- payment_name ----
            payment_name.setText("Name");
            payment_frameContentPane.add(payment_name);
            payment_name.setBounds(430, 215, 75, 30);

            //---- payment_id ----
            payment_id.setText("ID");
            payment_frameContentPane.add(payment_id);
            payment_id.setBounds(520, 215, 70, 30);
            payment_frameContentPane.add(payment_id_textfield);
            payment_id_textfield.setBounds(520, 250, 100, 35);
            payment_frameContentPane.add(payment_name_textfield);
            payment_name_textfield.setBounds(425, 250, 100, 35);

            //---- button1 ----
            button1.setText("PAY");
            button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 5f));
            button1.setBackground(new Color(204, 255, 153));
            button1.setForeground(Color.black);
            button1.addActionListener(e -> {
                try {
                    pay_button(e);
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            payment_frameContentPane.add(button1);
            button1.setBounds(440, 500, 160, 65);

            //---- button2 ----
            button2.setText("CANCEL");
            button2.addActionListener(e -> cancel_pay(e));
            payment_frameContentPane.add(button2);
            button2.setBounds(470, 585, 103, button2.getPreferredSize().height);

            //---- payment_amount ----
            payment_amount.setText("$ Amount $");
            payment_frameContentPane.add(payment_amount);
            payment_amount.setBounds(485, 300, 80, 30);
            payment_frameContentPane.add(payment_amount_textfield);
            payment_amount_textfield.setBounds(475, 330, 100, 35);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < payment_frameContentPane.getComponentCount(); i++) {
                    Rectangle bounds = payment_frameContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = payment_frameContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                payment_frameContentPane.setMinimumSize(preferredSize);
                payment_frameContentPane.setPreferredSize(preferredSize);
            }
            payment_frame.setSize(1030, 795);
            payment_frame.setLocationRelativeTo(payment_frame.getOwner());
        }


        //Return
        return_frame = new JFrame();
        label1_title = new JLabel();
        label1 = new JLabel();
        return_name = new JLabel();
        return_id = new JLabel();
        return_id_textfield = new JTextField();
        return_name_textfield = new JTextField();
        return_button = new JButton();
        cancel_return = new JButton();
        transaction_id = new JLabel();
        return_tr_id_textfield2 = new JTextField();

        //======== return_frame ========
        {
            return_frame.setTitle("Return");
            return_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            var return_frameContentPane = return_frame.getContentPane();
            return_frameContentPane.setLayout(null);

            //---- label1_title ----
            label1_title.setText("Return");
            label1_title.setHorizontalAlignment(SwingConstants.CENTER);
            label1_title.setFont(label1_title.getFont().deriveFont(label1_title.getFont().getSize() + 15f));
            label1_title.setForeground(new Color(204, 204, 0));
            return_frameContentPane.add(label1_title);
            label1_title.setBounds(375, 20, 280, 80);

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/util/download.png")));
            return_frameContentPane.add(label1);
            label1.setBounds(690, 605, 305, 116);

            //---- return_name ----
            return_name.setText("Name");
            return_frameContentPane.add(return_name);
            return_name.setBounds(430, 215, 75, 30);

            //---- return_id ----
            return_id.setText("ID");
            return_frameContentPane.add(return_id);
            return_id.setBounds(520, 215, 70, 30);
            return_frameContentPane.add(return_id_textfield);
            return_id_textfield.setBounds(520, 250, 100, 35);
            return_frameContentPane.add(return_name_textfield);
            return_name_textfield.setBounds(425, 250, 100, 35);

            //---- return_button ----
            return_button.setText("RETURN");
            return_button.setForeground(Color.black);
            return_button.setBackground(new Color(204, 204, 0));
            return_button.setFont(return_button.getFont().deriveFont(return_button.getFont().getSize() + 5f));
            return_button.addActionListener(e -> {
                try {
                    return_button(e);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            return_frameContentPane.add(return_button);
            return_button.setBounds(440, 500, 160, 65);

            //---- cancel_return ----
            cancel_return.setText("CANCEL");
            cancel_return.addActionListener(e -> cancel_return(e));
            return_frameContentPane.add(cancel_return);
            cancel_return.setBounds(470, 585, 103, cancel_return.getPreferredSize().height);

            //---- transaction_id ----
            transaction_id.setText("Transaction ID");
            return_frameContentPane.add(transaction_id);
            transaction_id.setBounds(480, 295, 110, 30);
            return_frameContentPane.add(return_tr_id_textfield2);
            return_tr_id_textfield2.setBounds(470, 325, 100, 35);


            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < return_frameContentPane.getComponentCount(); i++) {
                    Rectangle bounds = return_frameContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = return_frameContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                return_frameContentPane.setMinimumSize(preferredSize);
                return_frameContentPane.setPreferredSize(preferredSize);
            }
            return_frame.setSize(1030, 795);
            return_frame.setLocationRelativeTo(return_frame.getOwner());
        }


        //Purchase
        purchase_frame = new JFrame();
        label1_purchase = new JLabel();
        label2_purchase = new JLabel();
        label3_purchase = new JLabel();
        label4_purchase = new JLabel();
        purchase_client_id = new JTextField();
        label5_purchase = new JLabel();
        purchase_button = new JButton();
        cancel_button_purchase = new JButton();
        label7_purchase = new JLabel();
        purchase_amount = new JTextField();
        label6_purchase = new JLabel();
        label2_purchase2 = new JLabel();
        purchase_client_name = new JTextField();
        label3_purchase2 = new JLabel();
        purchase_dealer_id = new JTextField();
        purchase_dealer_name = new JTextField();
        checkBox1 = new JCheckBox();
        label_employee_id = new JLabel();
        textField_employee_id = new JTextField();
        label_employee_name = new JLabel();
        textField_employee_name_purchase = new JTextField();


        //======== purchase_frame ========
        {
            purchase_frame.setTitle("Purchase");
            var purchase_frameContentPane = purchase_frame.getContentPane();
            purchase_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            purchase_frameContentPane.setLayout(null);

            //---- label1_purchase ----
            label1_purchase.setText("Purchase");
            label1_purchase.setHorizontalAlignment(SwingConstants.CENTER);
            label1_purchase.setFont(label1_purchase.getFont().deriveFont(label1_purchase.getFont().getSize() + 15f));
            label1_purchase.setForeground(new Color(0, 204, 0));
            purchase_frameContentPane.add(label1_purchase);
            label1_purchase.setBounds(295, 15, 440, 66);

            //---- label2_purchase ----
            label2_purchase.setText("Client ID");
            purchase_frameContentPane.add(label2_purchase);
            label2_purchase.setBounds(240, 180, 100, 35);

            //---- label3_purchase ----
            label3_purchase.setText("Dealer Name");
            purchase_frameContentPane.add(label3_purchase);
            label3_purchase.setBounds(675, 180, 100, 35);

            //---- label4_purchase ----
            label4_purchase.setText("--------------------BUYS FROM----------------->");
            purchase_frameContentPane.add(label4_purchase);
            label4_purchase.setBounds(380, 190, 270, label4_purchase.getPreferredSize().height);
            purchase_frameContentPane.add(purchase_client_id);
            purchase_client_id.setBounds(235, 215, 100, 35);

            //---- label5_purchase ----
            label5_purchase.setIcon(new ImageIcon(getClass().getResource("/util/download.png")));
            purchase_frameContentPane.add(label5_purchase);
            label5_purchase.setBounds(705, 550, 290, 111);

            //---- purchase_button ----
            purchase_button.setText("PURCHASE");
            purchase_button.setBackground(new Color(0, 204, 0));
            purchase_button.setForeground(Color.black);
            purchase_button.addActionListener(e -> {
                try {
                    purchase_button(e);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            purchase_frameContentPane.add(purchase_button);
            purchase_button.setBounds(440, 565, 130, 55);

            //---- cancel_button_purchase ----
            cancel_button_purchase.setText("CANCEL");
            cancel_button_purchase.addActionListener(e -> cancel_button_purchase(e));
            purchase_frameContentPane.add(cancel_button_purchase);
            cancel_button_purchase.setBounds(455, 635, 100, cancel_button_purchase.getPreferredSize().height);

            //---- label7_purchase ----
            label7_purchase.setText("$ Amount $");
            label7_purchase.setHorizontalAlignment(SwingConstants.CENTER);
            purchase_frameContentPane.add(label7_purchase);
            label7_purchase.setBounds(460, 220, 75, label7_purchase.getPreferredSize().height);
            purchase_frameContentPane.add(purchase_amount);
            purchase_amount.setBounds(450, 245, 100, 35);

            //---- label2_purchase2 ----
            label2_purchase2.setText("Client Name");
            purchase_frameContentPane.add(label2_purchase2);
            label2_purchase2.setBounds(145, 180, 95, 35);
            purchase_frameContentPane.add(purchase_client_name);
            purchase_client_name.setBounds(140, 215, 100, 35);

            //---- label3_purchase2 ----
            label3_purchase2.setText("Dealer ID");
            purchase_frameContentPane.add(label3_purchase2);
            label3_purchase2.setBounds(775, 180, 95, 35);
            purchase_frameContentPane.add(purchase_dealer_id);
            purchase_dealer_id.setBounds(765, 220, 100, 35);
            purchase_frameContentPane.add(purchase_dealer_name);
            purchase_dealer_name.setBounds(670, 220, 100, 35);

            //---- checkBox1 ----
            checkBox1.setText("Employee");
            checkBox1.addActionListener(e -> Employee_checkbox(e));
            purchase_frameContentPane.add(checkBox1);
            checkBox1.setBounds(145, 290, 100, 26);


            //---- label_employee_id ----
            label_employee_id.setText("Employee ID");
            purchase_frameContentPane.add(label_employee_id);
            label_employee_id.setBounds(235, 330, 85, 25);
            purchase_frameContentPane.add(textField_employee_id);
            textField_employee_id.setBounds(235, 355, 70, textField_employee_id.getPreferredSize().height);

            //---- label_employee_name ----
            label_employee_name.setText("Employee Name");
            purchase_frameContentPane.add(label_employee_name);
            label_employee_name.setBounds(125, 330, 100, 25);

            //---- textField_employee_name_purchase ----
            purchase_frameContentPane.add(textField_employee_name_purchase);
            textField_employee_name_purchase.setBounds(130, 355, 85, 30);


            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < purchase_frameContentPane.getComponentCount(); i++) {
                    Rectangle bounds = purchase_frameContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = purchase_frameContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                purchase_frameContentPane.setMinimumSize(preferredSize);
                purchase_frameContentPane.setPreferredSize(preferredSize);
            }
            purchase_frame.setSize(1030, 795);
            purchase_frame.setLocationRelativeTo(purchase_frame.getOwner());
        }


        //init delete account
        delete_acc_fr = new JFrame();
        main_label_delete_acc = new JLabel();
        label_delete_acc = new JLabel();
        textfield_delete_acc = new JTextField();
        delete_button = new JButton();
        label2_icon = new JLabel();
        delete_cancel_button = new JButton();
        textfield_delete_acc_id = new JTextField();
        label_delete_acc_id = new JLabel();


        //======== delete_acc_fr ========
        {
            delete_acc_fr.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            delete_acc_fr.setTitle("Delete Account");
            var delete_acc_frContentPane = delete_acc_fr.getContentPane();
            delete_acc_frContentPane.setLayout(null);

            //---- main_label_delete_acc ----
            main_label_delete_acc.setText("Delete an Account");
            main_label_delete_acc.setHorizontalAlignment(SwingConstants.CENTER);
            main_label_delete_acc.setFont(main_label_delete_acc.getFont().deriveFont(main_label_delete_acc.getFont().getSize() + 10f));
            main_label_delete_acc.setForeground(new Color(255, 51, 51));
            delete_acc_frContentPane.add(main_label_delete_acc);
            main_label_delete_acc.setBounds(185, 25, 385, 66);

            //---- label_delete_acc ----
            label_delete_acc.setText("Name");
            delete_acc_frContentPane.add(label_delete_acc);
            label_delete_acc.setBounds(300, 150, 75, 30);

            //---- delete_button ----
            delete_button.setText("DELETE");
            delete_button.setForeground(Color.black);
            delete_button.setBackground(new Color(255, 51, 51));
            delete_button.addActionListener(e -> {
                try {
                    delete_button(e);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            delete_acc_frContentPane.add(delete_button);
            delete_button.setBounds(325, 280, 110, 50);

            //---- label2_icon ----
            label2_icon.setIcon(new ImageIcon(getClass().getResource("/util/download.png")));
            delete_acc_frContentPane.add(label2_icon);
            label2_icon.setBounds(475, 320, 260, 86);

            //---- delete_cancel_button ----
            delete_cancel_button.setText("CANCEL");
            delete_cancel_button.addActionListener(e -> delete_cancel_button(e));
            delete_acc_frContentPane.add(delete_cancel_button);
            delete_cancel_button.setBounds(330, 370, 100, delete_cancel_button.getPreferredSize().height);

            //textFields
            delete_acc_frContentPane.add(textfield_delete_acc_id);
            textfield_delete_acc_id.setBounds(390, 185, 100, 35);
            delete_acc_frContentPane.add(textfield_delete_acc);
            textfield_delete_acc.setBounds(295, 185, 100, 35);


            //---- label_delete_acc_id ----
            label_delete_acc_id.setText("ID");
            delete_acc_frContentPane.add(label_delete_acc_id);
            label_delete_acc_id.setBounds(390, 150, 70, 30);


            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < delete_acc_frContentPane.getComponentCount(); i++) {
                    Rectangle bounds = delete_acc_frContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = delete_acc_frContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                delete_acc_frContentPane.setMinimumSize(preferredSize);
                delete_acc_frContentPane.setPreferredSize(preferredSize);
            }
            delete_acc_fr.setSize(760, 465);
            delete_acc_fr.setLocationRelativeTo(null);
        }


        //Init Database
        init_database_frame = new JFrame();
        label1_init_database = new JLabel();
        create_new_database = new JButton();
        cancel = new JButton();

        //======== init_database_frame ========
        {
            init_database_frame.setTitle("Are you sure about that?");
            init_database_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            init_database_frame.setBackground(SystemColor.inactiveCaption);
            init_database_frame.setAlwaysOnTop(true);
            var init_database_frameContentPane = init_database_frame.getContentPane();
            init_database_frameContentPane.setLayout(null);

            //---- label1_init_database ----
            label1_init_database.setText("There is already a database. Do you want to make a new one?");
            label1_init_database.setVerticalAlignment(SwingConstants.TOP);
            label1_init_database.setFont(label1_init_database.getFont().deriveFont(label1_init_database.getFont().getSize() + 1f));
            init_database_frameContentPane.add(label1_init_database);
            label1_init_database.setBounds(25, 20, 435, 65);

            //---- create_new_database ----
            create_new_database.setText("Create new");
            create_new_database.addActionListener(e -> {
                try {
                    create_new_database(e);
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            init_database_frameContentPane.add(create_new_database);
            create_new_database.setBounds(120, 165, 105, 50);

            //---- cancel ----
            cancel.setText("Cancel");
            cancel.addActionListener(this::cancel);
            init_database_frameContentPane.add(cancel);
            cancel.setBounds(290, 165, 105, 50);


            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < init_database_frameContentPane.getComponentCount(); i++) {
                    Rectangle bounds = init_database_frameContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = init_database_frameContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                init_database_frameContentPane.setMinimumSize(preferredSize);
                init_database_frameContentPane.setPreferredSize(preferredSize);
            }
            init_database_frame.setSize(500, 320);
            init_database_frame.setLocationRelativeTo(null);
        }


        //main frame
        main_menu = new JFrame();
        CCC = new JLabel();
        new_acc = new JButton();
        return_of_product = new JButton();
        purchase = new JButton();
        delete_acc = new JButton();
        payment = new JButton();
        good_clients = new JButton();
        init_database = new JButton();
        bad_clients = new JButton();
        questions = new JButton();
        dealer_of_the_month = new JButton();
        logo = new JLabel();


        //======== main_menu ========
        {
            main_menu.setBackground(SystemColor.inactiveCaption);
            main_menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            main_menu.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
            main_menu.setResizable(false);
            main_menu.setTitle("Credit Card Company panel");
            var main_menuContentPane = main_menu.getContentPane();
            main_menuContentPane.setLayout(null);

            //---- CCC ----
            CCC.setText("Credit Card Company");
            CCC.setFont(CCC.getFont().deriveFont(Font.BOLD, CCC.getFont().getSize() + 20f));
            CCC.setForeground(new Color(0, 102, 102));
            CCC.setBackground(new Color(204, 204, 255));
            main_menuContentPane.add(CCC);
            CCC.setBounds(340, 25, 440, 90);

            //---- new_acc ----
            new_acc.setText("NEW ACCOUNT");
            new_acc.setBackground(new Color(51, 51, 255));
            new_acc.setForeground(Color.black);
            new_acc.setFont(new_acc.getFont().deriveFont(new_acc.getFont().getSize() + 1f));
            new_acc.addActionListener(e -> new_acc(e));
            main_menuContentPane.add(new_acc);
            new_acc.setBounds(275, 180, 135, 65);

            //---- return_of_product ----
            return_of_product.setText("RETURN");
            return_of_product.setForeground(Color.black);
            return_of_product.setBackground(new Color(255, 255, 102));
            return_of_product.setFont(return_of_product.getFont().deriveFont(return_of_product.getFont().getSize() + 1f));
            return_of_product.addActionListener(this::return_of_product);
            main_menuContentPane.add(return_of_product);
            return_of_product.setBounds(600, 265, 135, 65);

            //---- purchase ----
            purchase.setText("PURCHASE");
            purchase.setBackground(new Color(0, 204, 0));
            purchase.setForeground(Color.black);
            purchase.setFont(purchase.getFont().deriveFont(purchase.getFont().getSize() + 1f));
            purchase.addActionListener(this::purchase);
            main_menuContentPane.add(purchase);
            purchase.setBounds(275, 265, 135, 65);

            //---- delete_acc ----
            delete_acc.setText("DELETE ACCOUNT");
            delete_acc.setBackground(new Color(255, 102, 102));
            delete_acc.setForeground(Color.black);
            delete_acc.setFont(delete_acc.getFont().deriveFont(delete_acc.getFont().getSize() - 1f));
            delete_acc.addActionListener(e -> {
                try {
                    delete_acc(e);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            main_menuContentPane.add(delete_acc);
            delete_acc.setBounds(600, 180, 135, 65);

            //---- payment ----
            payment.setText("PAYMENT");
            payment.setFont(payment.getFont().deriveFont(payment.getFont().getSize() + 1f));
            payment.setBackground(new Color(204, 255, 153));
            payment.setForeground(Color.black);
            payment.addActionListener(e -> payment(e));
            main_menuContentPane.add(payment);
            payment.setBounds(440, 265, 135, 65);

            //---- good_clients ----
            good_clients.setText("GOOD CLIENTS");
            good_clients.setBackground(new Color(102, 255, 204));
            good_clients.setForeground(Color.black);
            good_clients.setFont(good_clients.getFont().deriveFont(good_clients.getFont().getSize() + 1f));
            good_clients.addActionListener(e -> {
                try {
                    good_clients(e);
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            main_menuContentPane.add(good_clients);
            good_clients.setBounds(275, 345, 135, 65);

            //---- init_database ----
            init_database.setText("START DB");
            init_database.setForeground(Color.white);
            init_database.setBackground(new Color(102, 0, 51));
            init_database.addActionListener(e -> {
                try {
                    init_database(e);
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            main_menuContentPane.add(init_database);
            init_database.setBounds(20, 690, 123, 40);

            //---- bad_clients ----
            bad_clients.setText("BAD CLIENTS");
            bad_clients.setBackground(new Color(102, 255, 204));
            bad_clients.setForeground(Color.black);
            bad_clients.setFont(bad_clients.getFont().deriveFont(bad_clients.getFont().getSize() + 1f));
            bad_clients.addActionListener(e -> {
                try {
                    bad_clients(e);
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            main_menuContentPane.add(bad_clients);
            bad_clients.setBounds(600, 345, 135, 65);

            //---- questions ----
            questions.setText("QUESTIONS");
            questions.setForeground(Color.black);
            questions.setFont(questions.getFont().deriveFont(questions.getFont().getSize() + 1f));
            questions.setBackground(new Color(255, 255, 204));
            questions.addActionListener(e -> questions(e));
            main_menuContentPane.add(questions);
            questions.setBounds(275, 430, 135, 65);

            //---- dealer_of_the_month ----
            dealer_of_the_month.setText("TOP# DEALER");
            dealer_of_the_month.setForeground(Color.black);
            dealer_of_the_month.setFont(dealer_of_the_month.getFont().deriveFont(dealer_of_the_month.getFont().getSize() + 1f));
            dealer_of_the_month.setBackground(new Color(255, 255, 204));
            dealer_of_the_month.addActionListener(e -> {
                try {
                    dealer_of_the_month(e);
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            main_menuContentPane.add(dealer_of_the_month);
            dealer_of_the_month.setBounds(600, 430, 135, 65);

            //---- logo ----
            logo.setIcon(new ImageIcon(getClass().getResource("/util/download.png")));
            main_menuContentPane.add(logo);
            logo.setBounds(345, 595, 350, 101);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < main_menuContentPane.getComponentCount(); i++) {
                    Rectangle bounds = main_menuContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = main_menuContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                main_menuContentPane.setMinimumSize(preferredSize);
                main_menuContentPane.setPreferredSize(preferredSize);
            }
            main_menu.setSize(1030, 795);
            main_menu.setLocationRelativeTo(null);
        }


        //registration frame
        register_frame = new JFrame();
        greeting = new JLabel();
        The_you_are = new JLabel();
        company_button = new JRadioButton();
        individual_button = new JRadioButton();
        the_balance = new JLabel();
        the_dept = new JLabel();
        label4 = new JLabel();
        textField_balance = new JTextField();
        textField_dept = new JTextField();
        textField_credit = new JTextField();
        name = new JLabel();
        textField_name = new JTextField();
        date = new JSpinner();
        exp_date = new JLabel();
        exp_date2 = new JLabel();
        register_button = new JButton();
        merchant_button = new JRadioButton();
        our_logo = new JLabel();
        employee_name = new JLabel();
        textField_employee_name = new JTextField();
        register_cancel = new JButton();

        //======== register_frame ========
        {
            register_frame.setBackground(SystemColor.inactiveCaption);
            register_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            register_frame.setTitle("Registration panel");
            register_frame.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
            var register_frameContentPane = register_frame.getContentPane();
            register_frameContentPane.setLayout(null);

            //---- greeting ----
            greeting.setText("Register yourself or your company here");
            greeting.setHorizontalAlignment(SwingConstants.CENTER);
            greeting.setFont(greeting.getFont().deriveFont(greeting.getFont().getSize() + 13f));
            greeting.setForeground(new Color(102, 204, 255));
            register_frameContentPane.add(greeting);
            greeting.setBounds(215, 25, 615, 71);

            //---- The_you_are ----
            The_you_are.setText("You are:");
            The_you_are.setFont(The_you_are.getFont().deriveFont(The_you_are.getFont().getSize() + 2f));
            register_frameContentPane.add(The_you_are);
            The_you_are.setBounds(255, 130, 100, The_you_are.getPreferredSize().height);

            //---- company_button ----
            company_button.setText("A company");
            company_button.setFont(company_button.getFont().deriveFont(company_button.getFont().getSize() + 1f));
            company_button.addActionListener(e -> company_button(e));
            register_frameContentPane.add(company_button);
            company_button.setBounds(385, 130, 115, company_button.getPreferredSize().height);

            //---- individual_button ----
            individual_button.setText("An individual");
            individual_button.setFont(individual_button.getFont().deriveFont(individual_button.getFont().getSize() + 1f));
            individual_button.addActionListener(e -> individual_button(e));
            register_frameContentPane.add(individual_button);
            individual_button.setSelected(true);
            individual_button.setBounds(525, 130, 110, 22);

            //---- the_balance ----
            the_balance.setText("The Balance");
            register_frameContentPane.add(the_balance);
            the_balance.setBounds(185, 340, 120, the_balance.getPreferredSize().height);

            //---- the_dept ----
            the_dept.setText("Your Dept");
            register_frameContentPane.add(the_dept);
            the_dept.setBounds(395, 335, 75, 10);

            //---- label4 ----
            label4.setText("Credit Limit");
            register_frameContentPane.add(label4);
            label4.setBounds(395, 265, 100, 16);

            //---- textField_balance ----
            register_frameContentPane.add(textField_balance);
            textField_balance.setBounds(175, 365, 95, 35);

            //---- textField_dept ----
            register_frameContentPane.add(textField_dept);
            textField_dept.setBounds(385, 360, 95, 35);

            //---- textField_credit ----
            register_frameContentPane.add(textField_credit);
            textField_credit.setBounds(385, 290, 95, 35);

            //---- name ----
            name.setText("Name");
            register_frameContentPane.add(name);
            name.setBounds(190, 260, 75, 16);

            //---- textField_name ----
            register_frameContentPane.add(textField_name);
            textField_name.setBounds(175, 290, 95, 35);

            //---- date ----
            date.setModel(new SpinnerDateModel(new java.util.Date(1642505520000L), null, null, java.util.Calendar.DAY_OF_MONTH));
            date.addChangeListener(e -> spinner1StateChanged(e));
            register_frameContentPane.add(date);
            date.setBounds(new Rectangle(new Point(650, 295), date.getPreferredSize()));

            //---- exp_date ----
            exp_date.setText("Expiration Date");
            register_frameContentPane.add(exp_date);
            exp_date.setBounds(665, 275, 130, 16);

            //---- exp_date2 ----
            exp_date2.setText("*The time here does not matter");
            exp_date2.setFont(exp_date2.getFont().deriveFont(exp_date2.getFont().getSize() - 2f));
            register_frameContentPane.add(exp_date2);
            exp_date2.setBounds(650, 325, 185, 16);

            //---- register_button ----
            register_button.setText("Register");
            register_button.setForeground(Color.black);
            register_button.setFont(register_button.getFont().deriveFont(register_button.getFont().getSize() + 5f));
            register_button.setBackground(new Color(102, 204, 255));
            register_button.addActionListener(e -> {
                try {
                    register_button(e);
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            register_frameContentPane.add(register_button);
            register_button.setBounds(150, 645, 235, 50);

            //---- merchant_button ----
            merchant_button.setText("A Merchant");
            merchant_button.setFont(merchant_button.getFont().deriveFont(merchant_button.getFont().getSize() + 1f));
            merchant_button.addActionListener(e -> merchant_button(e));
            register_frameContentPane.add(merchant_button);
            merchant_button.setBounds(690, 130, 110, 22);


            //Button Group
            ButtonGroup group = new ButtonGroup();
            group.add(company_button);
            group.add(individual_button);
            group.add(merchant_button);

            //---- our_logo ----
            our_logo.setIcon(new ImageIcon(getClass().getResource("/util/download.png")));
            register_frameContentPane.add(our_logo);
            our_logo.setBounds(695, 555, 305, 136);


            //---- employee_name ----
            employee_name.setText("Employee Name");
            register_frameContentPane.add(employee_name);
            employee_name.setBounds(185, 490, 110, 16);
            employee_name.setVisible(false);

            //---- textField_employee_name ----
            register_frameContentPane.add(textField_employee_name);
            textField_employee_name.setBounds(175, 520, 95, 35);
            textField_employee_name.setVisible(false);

            //---- register_cancel ----
            register_cancel.setText("CANCEL");
            register_cancel.addActionListener(e -> register_cancel(e));
            register_frameContentPane.add(register_cancel);
            register_cancel.setBounds(210, 710, 110, 25);


            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < register_frameContentPane.getComponentCount(); i++) {
                    Rectangle bounds = register_frameContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = register_frameContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                register_frameContentPane.setMinimumSize(preferredSize);
                register_frameContentPane.setPreferredSize(preferredSize);
            }
            register_frame.setSize(1030, 795);
            register_frame.setLocationRelativeTo(null);
        }


    }


    enum months {Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec}
}
