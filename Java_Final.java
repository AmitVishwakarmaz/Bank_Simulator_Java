import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.text.DecimalFormat;

public class Java_Final {
    public JPanel createAccountPanel;
    public JTextField usernameField; // Updated field name
    public JPasswordField passwordField;
    public JPanel loginPanel;
    public JTextField loginUsernameField;
    public JPasswordField loginPasswordField;
    public JPanel deleteAccountPanel;
    public ArrayList<String[]> userCredentials = new ArrayList<>();
    public JFrame frame;
    public JPanel welcomePanel;
    public JPanel PerformActions;
    public JPanel adminOptionsPanel;
    public int currentState = 0;
    public double accountBalance = 0.0;

    public Java_Final() {
        frame = new JFrame();
        frame.setTitle("Welcome to Wizard's Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        welcomePanel = createWelcomePanel();
        adminOptionsPanel = createAdminOptionsPanel();

        frame.add(welcomePanel);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 0, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setBackground(new Color(230, 230, 250));

        JLabel welcomeLabel = createStyledLabel("Welcome to Wizard's Bank");
        JButton adminButton = createStyledButton("Admin");
        JButton customerButton = createStyledButton("Customer Login");
        JButton exitButton = createStyledButton("Exit");

        adminButton.addActionListener(e -> changeState(1));

        customerButton.addActionListener(e -> {
            frame.remove(welcomePanel);
            loginPanel = createLoginPanel();
            frame.add(loginPanel);
            frame.setTitle("Customer Login");
            frame.revalidate();
            frame.repaint();
        });
        exitButton.addActionListener(e -> frame.dispose());

        panel.add(welcomeLabel);
        panel.add(adminButton);
        panel.add(customerButton);
        panel.add(exitButton);

        return panel;
    }

    public JPanel createAdminOptionsPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 0, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setBackground(new Color(230, 230, 250));

        JLabel adminOptionsLabel = createStyledLabel("Admin Options");
        JButton createAccountButton = createStyledButton("Create Account");
        JButton deleteAccountButton = createStyledButton("Delete Account");
        JButton backButton = createStyledButton("Back");

        createAccountButton.addActionListener(e -> {
            frame.remove(adminOptionsPanel);
            createAccountPanel = createCreateAccountPanel();
            frame.add(createAccountPanel);
            frame.setTitle("Create Account");
            frame.revalidate();
            frame.repaint();
        });

        deleteAccountButton.addActionListener(e -> {
            frame.remove(adminOptionsPanel);
            deleteAccountPanel = createDeleteAccountPanel();
            frame.add(deleteAccountPanel);
            frame.setTitle("Delete Account");
            frame.revalidate();
            frame.repaint();
        });
        //(e -> deleteAccountLogic());
        backButton.addActionListener(e -> changeState(0));

        panel.add(adminOptionsLabel);
        panel.add(createAccountButton);
        panel.add(deleteAccountButton);
        panel.add(backButton);

        return panel;
    }

    public JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground(new Color(0, 0, 128));
        return label;
    }

    public JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(65, 105, 225));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128), 2));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 60));
        return button;
    }

    public void changeState(int newState) {
        currentState = newState;
        if (newState == 0) {
            frame.remove(adminOptionsPanel);
            frame.add(welcomePanel);
            frame.setTitle("Welcome to Wizard's Bank");
        } else if (newState == 1) {
            frame.remove(welcomePanel);
            frame.add(adminOptionsPanel);
            frame.setTitle("Admin Options");
        }
        frame.revalidate();
        frame.repaint();
    }

    public JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setBackground(new Color(230, 230, 250));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel loginLabel = createStyledLabel("Customer Login");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginLabel, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(usernameLabel, gbc);

        loginUsernameField = new JTextField();
        loginUsernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(loginUsernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(passwordLabel, gbc);

        loginPasswordField = new JPasswordField();
        loginPasswordField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(loginPasswordField, gbc);

        JButton loginButton = createStyledButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        JButton clearButton = createStyledButton("Clear");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(clearButton, gbc);

        JButton backButton = createStyledButton("Back");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(backButton, gbc);

        loginButton.addActionListener(e -> customerLoginLogic());
        clearButton.addActionListener(e -> clearLoginForm());
        backButton.addActionListener(e -> {
            frame.remove(loginPanel);
            frame.add(welcomePanel);
            frame.setTitle("Welcome to Wizard's Bank");
            frame.revalidate();
            frame.repaint();
        });

        return panel;
    }

    public void customerLoginLogic() {
        String username = loginUsernameField.getText();
        char[] passwordChars = loginPasswordField.getPassword();
        String password = new String(passwordChars);

        if (authenticateUser(username, password)) {
            JOptionPane.showMessageDialog(frame, "Successfully logged in!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Login successful!");
            frame.remove(loginPanel);
            PerformActions = createActionPanel();
            frame.add(PerformActions);
            frame.setTitle("Welcome To Wizards Bank");
            frame.revalidate();
            frame.repaint();
            
        } else {
            JOptionPane.showMessageDialog(frame, "Login failed.!\nInvalid username or password.!", "Login Failed", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    public JPanel createActionPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 0, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setBackground(new Color(230, 230, 250));

        JLabel actionLabel = createStyledLabel("Wizards Bank");
        JButton withdrawButton = createStyledButton("Withdraw");
        JButton depositButton = createStyledButton("Deposit");
        JButton checkBalanceButton = createStyledButton("Check Balance");
        JButton backButton = createStyledButton("Log Out");

        depositButton.addActionListener(e -> depositLogic());
        withdrawButton.addActionListener(e -> withdrawLogic());
        checkBalanceButton.addActionListener(e -> displayCurrentBalance());
        
        backButton.addActionListener(e -> {
            frame.remove(PerformActions);
            frame.add(welcomePanel);
            frame.revalidate();
            frame.repaint();
        });
        panel.add(actionLabel);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(checkBalanceButton);
        panel.add(backButton);

        return panel;
    }


    public void depositLogic() {
        String amountStr = JOptionPane.showInputDialog(frame, "Enter the deposit amount:", "Deposit", JOptionPane.QUESTION_MESSAGE);
        if (amountStr != null && !amountStr.isEmpty()) {
            double depositAmount = Double.parseDouble(amountStr);
            accountBalance += depositAmount;
            JOptionPane.showMessageDialog(frame, depositAmount + "$ deposited. \nCurrent Balance: $" + accountBalance, " Deposit Successful", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Withdraw logic
    public void withdrawLogic() {
        String amountStr = JOptionPane.showInputDialog(frame, "Enter the withdrawal amount:", "Withdraw", JOptionPane.QUESTION_MESSAGE);
        if (amountStr != null && !amountStr.isEmpty()) {
            double withdrawalAmount = Double.parseDouble(amountStr);
            if (withdrawalAmount <= accountBalance) {
                accountBalance -= withdrawalAmount;
                JOptionPane.showMessageDialog(frame, withdrawalAmount + "$ withdrawn. \nCurrent Balance: $" + accountBalance, " Withdrawal Successful", JOptionPane.INFORMATION_MESSAGE);    
            } else {
                JOptionPane.showMessageDialog(frame, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Display current balance
    private void displayCurrentBalance() {
        DecimalFormat df = new DecimalFormat("0.00");
        JOptionPane.showMessageDialog(frame, "Current Balance: $" + df.format(accountBalance), "Balance Inquiry", JOptionPane.INFORMATION_MESSAGE);
    }


    public void clearLoginForm() {
        loginUsernameField.setText("");
        loginPasswordField.setText("");
    }

    public boolean authenticateUser(String username, String password) {
        for (String[] credentials : userCredentials) {
            if (credentials[0].equals(username) && credentials[1].equals(password)) {
                return true;
            }
        }
        return false;
    }

    public JPanel createCreateAccountPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setBackground(new Color(230, 230, 250));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel createAccountLabel = createStyledLabel("Create Account");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(createAccountLabel, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField(); // Updated field name
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(passwordField, gbc);

        JButton createAccountButton = createStyledButton("Create Account");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(createAccountButton, gbc);

        JButton clearButton = createStyledButton("Clear");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(clearButton, gbc);

        JButton backButton = createStyledButton("Back");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(backButton, gbc);

        createAccountButton.addActionListener(e -> createAccountLogic());
        clearButton.addActionListener(e -> clearFields());
        backButton.addActionListener(e -> {
            frame.remove(createAccountPanel);
            frame.add(adminOptionsPanel);
            frame.setTitle("Admin Options");
            frame.revalidate();
            frame.repaint();
        });

        return panel;
    }

    public JPanel createDeleteAccountPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setBackground(new Color(230, 230, 250));
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
    
        JLabel deleteAccountLabel = createStyledLabel("Delete Account");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(deleteAccountLabel, gbc);
    
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(usernameLabel, gbc);
    
        JTextField usernameField = new JTextField(); // Use a different field name
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(usernameField, gbc);
    
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(passwordLabel, gbc);
    
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(passwordField, gbc);
    
        JButton deleteAccountButton = createStyledButton("Delete Account");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(deleteAccountButton, gbc);
    
        JButton clearButton = createStyledButton("Clear");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(clearButton, gbc);
    
        JButton backButton = createStyledButton("Back");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(backButton, gbc);
    
        deleteAccountButton.addActionListener(e -> deleteAccountLogic(usernameField.getText(), new String(passwordField.getPassword())));
        clearButton.addActionListener(e -> clearDeleteAccountForm(usernameField, passwordField));
        backButton.addActionListener(e -> {
            frame.remove(panel);
            frame.add(adminOptionsPanel);
            frame.setTitle("Admin Options");
            frame.revalidate();
            frame.repaint();
        });
    
        return panel;
    }
    

    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    public void createAccountLogic() {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        // Store the credentials in the userCredentials ArrayList
        String[] credentials = {username, password};
        userCredentials.add(credentials);

        JOptionPane.showMessageDialog(frame, "Account Created :\nUsername: " + username + "\nPassword: " + password, "Account Created", JOptionPane.INFORMATION_MESSAGE);

        System.out.println("Login successful!");
        System.out.println("Creating account with Username: " + username + " and Password: " + password);
    }

    public void deleteAccountLogic(String username, String password) {
        // Create a custom dialog to confirm account deletion
        int option = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this account?", "Confirm Account Deletion", JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) {
            performAccountDeletion(username, password);
        }
    }

    public void performAccountDeletion(String username, String password) {
        boolean accountDeleted = false;
    
        for (int i = 0; i < userCredentials.size(); i++) {
            String[] credentials = userCredentials.get(i);
            if (credentials[0].equals(username) && credentials[1].equals(password)) {
                // Match found, remove the account
                userCredentials.remove(i);
                accountDeleted = true;
                break; // No need to continue searching
            }
        }
    
        if (accountDeleted) {
            JOptionPane.showMessageDialog(frame, "Account Deleted", "Account Deleted", JOptionPane.INFORMATION_MESSAGE);
            // Instead of displaying a message dialog, you can simply go back to the admin options panel
            showAdminOptionsPanel();
        } else {
            // If no match was found, display an error message
            JOptionPane.showMessageDialog(frame, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showAdminOptionsPanel() {
        // Switch back to the admin options panel
        frame.remove(deleteAccountPanel);
        frame.add(adminOptionsPanel);
        frame.setTitle("Admin Options");
        frame.revalidate();
        frame.repaint();
    }

    public void clearDeleteAccountForm(JTextField usernameField, JPasswordField passwordField) {
        usernameField.setText("");
        passwordField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Java_Final());
    }
}


