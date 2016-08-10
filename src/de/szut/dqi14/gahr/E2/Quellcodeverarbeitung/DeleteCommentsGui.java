package de.szut.dqi14.gahr.E2.Quellcodeverarbeitung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DeleteCommentsGui extends JFrame implements ActionListener {

    // declare global variables
    final JTextArea tArea = new JTextArea();
    private SwingWorker worker;

    public DeleteCommentsGui() {
        // declare variables
        JFrame frame = new JFrame("DeleteComments");
        Container container = frame.getContentPane();
        JScrollPane pane = new JScrollPane(tArea);
        container.add(pane);

        // set textarea options
        tArea.setEditable(false);
        tArea.setLineWrap(true);
        tArea.setWrapStyleWord(true);
        tArea.setForeground(Color.GREEN);
        tArea.setBackground(Color.BLACK);


        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 300);
        frame.setMinimumSize(frame.getSize());
        frame.setVisible(true);

        System.out.println("GUI started");
    }

    void write(String s) {
        if (worker!=null) {
            worker.cancel(true);
        }

        worker = new SwingWorker() {
            @Override
            protected Integer doInBackground() {
                try {
                    for(int i = 0;i<s.length();i++) {
                        tArea.append(String.valueOf(s.charAt(i)));
                        Thread.sleep(1);
                        tArea.setCaretPosition(tArea.getDocument().getLength());
                    }
                } catch(Exception ignored){}
                return 0;
            }
        };
        worker.execute();//Schedules this SwingWorker for execution on a worker thread.
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}