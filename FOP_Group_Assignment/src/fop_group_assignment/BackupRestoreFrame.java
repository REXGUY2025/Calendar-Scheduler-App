
package fop_group_assignment;

import javax.swing.*;
import java.awt.*;

public class BackupRestoreFrame extends JFrame {

    public BackupRestoreFrame() {
        setTitle("Backup / Restore");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JCheckBox event = new JCheckBox("Event");
        JCheckBox additional = new JCheckBox("Additional");
        JCheckBox recurrent = new JCheckBox("Recurrent");

        JPanel options = new JPanel(new GridLayout(3, 1));
        options.add(event);
        options.add(additional);
        options.add(recurrent);

        JButton backup = new JButton("Backup");
        JButton restore = new JButton("Restore");

        backup.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        BackupService.backup(
                                event.isSelected(),
                                additional.isSelected(),
                                recurrent.isSelected()
                        )
                )
        );

        restore.addActionListener(e -> {
            JFileChooser fc = new JFileChooser("backups");
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
                BackupService.restore(fc.getSelectedFile().getPath());
        });

        JPanel buttons = new JPanel();
        buttons.add(backup);
        buttons.add(restore);

        add(options, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }
}

