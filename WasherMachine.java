package sw_FatroyMethod.washer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 세탁기 선택 및 작동 예제
public class WasherMachine {
    public static void main(String[] args) {
        JFrame frame = new JFrame("세탁기 선택");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JButton normalWasherButton = new JButton("일반 세탁기");
        JButton drumWasherButton = new JButton("드럼 세탁기");

        normalWasherButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        drumWasherButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(normalWasherButton);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(drumWasherButton);
        mainPanel.add(Box.createVerticalGlue());

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // 세탁기 버튼에 대한 액션 리스너 등록
        normalWasherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Washer washer = WasherFactory.createWasher("일반"); // 일반 세탁기 객체 생성
                if (washer != null) {
                    showWeightInputFrame(washer); // 무게 입력 창 표시
                }
            }
        });

        drumWasherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Washer washer = WasherFactory.createWasher("드럼"); // 드럼 세탁기 객체 생성
                if (washer != null) {
                    showWeightInputFrame(washer); // 무게 입력 창 표시
                }
            }
        });
    }

    // 무게 입력 창을 표시하고 무게에 따라 세탁기 작동 메서드 호출
    private static void showWeightInputFrame(Washer washer) {
        JFrame weightFrame = new JFrame("무게 입력");
        weightFrame.setSize(300, 150);
        weightFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        weightFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("물건의 무게를 입력하세요:");
        JTextField weightField = new JTextField(10);
        JButton confirmButton = new JButton("세탁하러 가기");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String weightText = weightField.getText();
                try {
                    double weight = Double.parseDouble(weightText); // 무게를 숫자로 변환
                    if (weight > 6) {
                        JOptionPane.showMessageDialog(null, "6kg 이상은 처리할 수 없습니다.", "무게 오류", JOptionPane.ERROR_MESSAGE);
                        weightField.setText(""); // 입력 필드 초기화
                    } else {
                        showRinseButtonFrame(washer, weight); // 헹굼 버튼을 보여주는 창 호출
                        weightFrame.dispose(); // 무게 입력 창 닫기
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "유효한 숫자를 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(label);
        panel.add(weightField);
        panel.add(confirmButton);

        weightFrame.add(panel);
        weightFrame.setVisible(true);
    }

    // 헹굼 선택 창을 표시하고 세탁 온도 선택 기능 적용
    private static void showRinseButtonFrame(Washer washer, double weight) {
        JFrame rinseFrame = new JFrame("헹굼 선택");
        rinseFrame.setSize(300, 200);
        rinseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rinseFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel();
        if (weight <= 2) {
            label.setText("0-2kg 범위이므로 세탁비는 5천원입니다.");
        } else if (weight <= 4) {
            label.setText("2-4kg 범위이므로 세탁비는 만원입니다.");
        } else if (weight <= 6) {
            label.setText("4-6kg 범위이므로 세탁비는 만 5천원입니다.");
        } else {
            label.setText("6kg 이상은 처리할 수 없습니다.");
        }

        JButton temp40Button = new JButton("40도 세탁");
        JButton temp60Button = new JButton("60도 세탁");
        JButton temp90Button = new JButton("90도 세탁");

        panel.add(label);
        panel.add(temp40Button);
        panel.add(temp60Button);
        panel.add(temp90Button);

        rinseFrame.add(panel);
        rinseFrame.setVisible(true);

        // 세탁 온도 선택 전략 패턴 적용
        WashTemperatureSelector temperatureSelector = new WashTemperatureSelector();
        temp40Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WashTemperature washTemperature = new WashTemperature40();
                temperatureSelector.setStrategy(new WashTemperature40Strategy(washTemperature));
                temperatureSelector.executeStrategy();
            }
        });

        temp60Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WashTemperature washTemperature = new WashTemperature60();
                temperatureSelector.setStrategy(new WashTemperature60Strategy(washTemperature)); // 세탁기 전략 설정
                temperatureSelector.executeStrategy(); // 전략 실행
            }
        });
        temp90Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WashTemperature washTemperature = new WashTemperature90();
                temperatureSelector.setStrategy(new WashTemperature90Strategy(washTemperature)); // 세탁기 전략 설정
                temperatureSelector.executeStrategy(); // 전략 실행
            }
        });
    }
}
