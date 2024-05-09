package FactoryMethodPattern;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WashingMachine {
    public WashingMachine() {
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame("세탁기 버튼");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(3);
        final JButton normalWasherButton = new JButton("일반 세탁기");
        final JButton dreamWasherButton = new JButton("드럼 세탁기");
        final JPanel mainPanel = new JPanel();
        final JPanel buttonPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, 1));
        buttonPanel.setLayout(new FlowLayout());
        normalWasherButton.setAlignmentX(0.5F);
        dreamWasherButton.setAlignmentX(0.5F);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(normalWasherButton);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(dreamWasherButton);
        mainPanel.add(Box.createVerticalGlue());
        frame.add(mainPanel, "Center");
        frame.add(buttonPanel, "South");
        frame.setLocationRelativeTo((Component)null);
        frame.setVisible(true);
        normalWasherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPanel.removeAll();
                Washer washer = WasherFactory.createWasher("일반");
                JButton washButton = new JButton("세탁");
                JButton rinseButton = new JButton("헹굼");
                JButton spinButton = new JButton("탈수");
                buttonPanel.add(washButton);
                buttonPanel.add(rinseButton);
                buttonPanel.add(spinButton);
                frame.revalidate();
                frame.repaint();
                washButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("세탁 중...");
                    }
                });
                rinseButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("헹굼 중...");
                    }
                });
                spinButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("탈수 중...");
                    }
                });
                mainPanel.remove(normalWasherButton);
                mainPanel.remove(dreamWasherButton);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        dreamWasherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPanel.removeAll();
                Washer washer = WasherFactory.createWasher("드럼");
                JButton washButton = new JButton("세탁");
                JButton rinseButton = new JButton("헹굼");
                JButton spinButton = new JButton("탈수");
                JButton dryButton = new JButton("건조");
                buttonPanel.add(washButton);
                buttonPanel.add(rinseButton);
                buttonPanel.add(spinButton);
                buttonPanel.add(dryButton);
                frame.revalidate();
                frame.repaint();
                washButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("세탁 중...");
                    }
                });
                rinseButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("헹굼 중...");
                    }
                });
                spinButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("탈수 중...");
                    }
                });
                dryButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("건조 중...");
                    }
                });
                mainPanel.remove(normalWasherButton);
                mainPanel.remove(dreamWasherButton);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
    }
}