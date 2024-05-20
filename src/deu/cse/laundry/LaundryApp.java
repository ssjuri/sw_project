package deu.cse.laundry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaundryApp extends JFrame {
    private JButton extraLargeButton;
    private JButton largeButton1;
    private JButton largeButton2;
    private int laundryWeight; // Added to store the laundry weight

    public LaundryApp() {
        setTitle("셀프 세탁방");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        Dimension buttonSize = new Dimension(150, 30);

        extraLargeButton = new JButton("초대형 세탁기(30kg)");
        extraLargeButton.setPreferredSize(buttonSize);

        largeButton1 = new JButton("대형 세탁기(26kg)-1");
        largeButton1.setPreferredSize(buttonSize);

        largeButton2 = new JButton("대형 세탁기(26kg)-2");
        largeButton2.setPreferredSize(buttonSize);

        extraLargeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWashOptionsDialog(30);
            }
        });

        largeButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWashOptionsDialog(26);
            }
        });

        largeButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWashOptionsDialog(26);
            }
        });

        add(extraLargeButton);
        add(largeButton1);
        add(largeButton2);
    }

    private void openWashOptionsDialog(int maxWeight) {
        int weight = getWeightFromUser();
        if (weight != -1) {
            if (weight > maxWeight) {
                JOptionPane.showMessageDialog(LaundryApp.this, "세탁기의 최대 무게를 초과했습니다.", "무게 초과", JOptionPane.WARNING_MESSAGE);
            } else {
                laundryWeight = weight; // Store the laundry weight
                // 대형 또는 초대형 세탁기를 선택하여 인스턴스화합니다.
                WashingMachine washingMachine;
                if (maxWeight == 30) {
                    washingMachine = new ExtraLargeWashingMachine();
                } else {
                    washingMachine = new LargeWashingMachine();
                }
                washingMachine.wash(weight); // 선택한 세탁기로 세탁을 시작합니다.
                showWashOptionsDialog();
            }
        }
    }

    private int getWeightFromUser() {
        String input = JOptionPane.showInputDialog(this, "세탁물의 무게를 입력하세요 (kg):", "무게 입력", JOptionPane.PLAIN_MESSAGE);
        if (input != null) {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "유효한 숫자를 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
            }
        }
        return -1;
    }

    private void openWashOptionsDialog(WashStrategy strategy) {
        strategy.wash();
        strategy.rinse();
        strategy.spin();
        int time = strategy.getTime();
        JOptionPane.showMessageDialog(this, "세탁 완료 소요 시간: " + time + "분", "완료", JOptionPane.INFORMATION_MESSAGE);
        showDryerAndExitDialog();
    }

    private void showWashOptionsDialog() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JButton standardButton = new JButton("표준 코스");
        JButton expressButton = new JButton("쾌속 코스");
        JButton cleanButton = new JButton("청정 코스");

        standardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWashOptionsDialog(new StandardWashStrategy());
            }
        });

        expressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWashOptionsDialog(new ExpressWashStrategy());
            }
        });

        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWashOptionsDialog(new CleanWashStrategy());
            }
        });

        panel.add(standardButton);
        panel.add(expressButton);
        panel.add(cleanButton);

        JOptionPane.showOptionDialog(this, panel, "세탁 옵션", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
    }

    private void showDryerAndExitDialog() {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JButton extraLargeDryerButton = new JButton("초대형 건조기(30kg)");
        JButton largeDryerButton1 = new JButton("대형 건조기(26kg)-1");
        JButton largeDryerButton2 = new JButton("대형 건조기(26kg)-2");
        JButton exitButton = new JButton("끝내기");

        // Enable/Disable buttons based on the laundry weight
        extraLargeDryerButton.setEnabled(laundryWeight <= 30);
        largeDryerButton1.setEnabled(laundryWeight <= 26);
        largeDryerButton2.setEnabled(laundryWeight <= 26);

        extraLargeDryerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to operate the extra large dryer
                showDryerTimeDialog(30); // 버튼 생성 메서드 호출
            }
        });

        largeDryerButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to operate the first large dryer
                showDryerTimeDialog(26); // 버튼 생성 메서드 호출
            }
        });

        largeDryerButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to operate the second large dryer
                showDryerTimeDialog(26); // 버튼 생성 메서드 호출
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(extraLargeDryerButton);
        panel.add(largeDryerButton1);
        panel.add(largeDryerButton2);
        panel.add(exitButton);

        JOptionPane.showOptionDialog(this, panel, "건조기 옵션", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
    }

    private void showDryerTimeDialog(int maxWeight) {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JButton button1 = new JButton("16분");
        JButton button2 = new JButton("20분");
        JButton button3 = new JButton("32분");
        JButton button4 = new JButton("끝내기");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DryerStrategy strategy = new Dryer16MinutesStrategy();
                strategy.dry();
                JOptionPane.showMessageDialog(LaundryApp.this, "건조 완료!", "완료", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DryerStrategy strategy = new Dryer20MinutesStrategy();
                strategy.dry();
                JOptionPane.showMessageDialog(LaundryApp.this, "건조 완료!", "완료", JOptionPane.INFORMATION_MESSAGE);

            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DryerStrategy strategy = new Dryer32MinutesStrategy();
                strategy.dry();
                JOptionPane.showMessageDialog(LaundryApp.this, "건조 완료!", "완료", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        JOptionPane.showOptionDialog(this, panel, "건조 시간 선택", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LaundryApp app = new LaundryApp();
                app.setVisible(true);
            }
        });
    }
}
