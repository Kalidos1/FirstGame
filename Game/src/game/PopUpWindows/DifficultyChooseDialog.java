package game.PopUpWindows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyChooseDialog implements ActionListener {

    private JDialog dialog = new JDialog();

    public DifficultyChooseDialog(JFrame frame) {
        super();
        JButton buttonEin = new JButton("Einfach");
        JButton buttonMit = new JButton("Mittel");
        JButton buttonSch = new JButton("Schwer");
        JButton buttonRechenart = new JButton("Ich will was anderes üben");


        JPanel panelNew = new JPanel();
        panelNew.setLayout(new BoxLayout(panelNew, BoxLayout.LINE_AXIS));
        panelNew.add(buttonEin);
        panelNew.add(buttonMit);
        panelNew.add(buttonSch);
        panelNew.add(buttonRechenart);


        panelNew.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        dialog.add(panelNew, BorderLayout.CENTER);

        buttonEin.addActionListener(this);
        buttonMit.addActionListener(this);
        buttonSch.addActionListener(this);
        buttonRechenart.addActionListener(this);
        dialog.pack();

    }


    public void actionPerformed(ActionEvent e) {

    }
    public void showDialog() {
        dialog.setVisible(true);
    }
    public void requestFocus() {
        dialog.requestFocus();
    }
}
