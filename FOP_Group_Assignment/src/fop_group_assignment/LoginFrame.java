package fop_group_assignment;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Calendar App - Login");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("LOGIN", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(126, 87, 194));

        JTextField user = new JTextField();
        JPasswordField pass = new JPasswordField();

        JButton login = new JButton("Login");
        login.setBackground(new Color(126, 87, 194));
        login.setForeground(Color.WHITE);

        login.addActionListener(e -> {
            if (AuthService.login(user.getText(), new String(pass.getPassword()))) {
                new MainMenuFrame().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Login");
            }
        });

        panel.add(title);
        panel.add(user);
        panel.add(pass);
        panel.add(login);

        add(panel);
    }
}
