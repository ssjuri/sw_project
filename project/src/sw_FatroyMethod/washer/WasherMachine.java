package sw_FatroyMethod.washer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// 팩토리 클래스

public class WasherMachine {
    public static void main(String[] args) {
        // JFrame 객체 생성
        JFrame frame = new JFrame("세탁기 선택");

        // 프레임 크기 설정
        frame.setSize(400, 300);

        // 프레임이 닫힐 때 프로그램 종료 설정
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 두 개의 버튼 생성
        JButton normalWasherButton = new JButton("일반 세탁기");
        JButton dreamWasherButton = new JButton("드럼 세탁기");

        // 버튼을 추가할 패널 생성
        JPanel mainPanel = new JPanel();
        JPanel buttonPanel = new JPanel(); // 기능 버튼을 담을 패널

        // 패널에 레이아웃 매니저 설정 (BoxLayout 사용)
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new FlowLayout());

        // 수평 가운데 정렬을 위해 각 버튼을 패널에 추가할 때 수평 정렬(Box.CENTER) 설정
        normalWasherButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        dreamWasherButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 버튼을 패널에 추가
        mainPanel.add(Box.createVerticalGlue()); // 패널의 위쪽 여백 추가
        mainPanel.add(normalWasherButton);
        mainPanel.add(Box.createVerticalStrut(20)); // 버튼 사이에 간격 추가
        mainPanel.add(dreamWasherButton);
        mainPanel.add(Box.createVerticalGlue()); // 패널의 아래쪽 여백 추가

        // 프레임에 패널 추가
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // 화면 가운데로 프레임 위치 설정
        frame.setLocationRelativeTo(null);

        // 프레임을 화면에 표시
        frame.setVisible(true);

        // 일반 세탁기 버튼에 대한 액션 리스너 등록
        normalWasherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 패널 초기화 후 세탁, 헹굼, 탈수 버튼 추가
                buttonPanel.removeAll();
                JButton washButton = new JButton("세탁");
                JButton rinseButton = new JButton("헹굼");
                JButton spinButton = new JButton("탈수");
                buttonPanel.add(washButton);
                buttonPanel.add(rinseButton);
                buttonPanel.add(spinButton);
                frame.revalidate();
                frame.repaint();

                // 각 버튼에 대한 액션 리스너 등록
                washButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("세탁 중...");
                    }
                });

                rinseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("헹굼 중...");
                    }
                });

                spinButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("탈수 중...");
                    }
                });

                // 버튼 패널 제거
                mainPanel.remove(normalWasherButton);
                mainPanel.remove(dreamWasherButton);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        // 드럼 세탁기 버튼에 대한 액션 리스너 등록
        dreamWasherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 패널 초기화 후 세탁, 헹굼, 탈수 버튼 추가
                buttonPanel.removeAll();
                JButton washButton = new JButton("세탁");
                JButton rinseButton = new JButton("헹굼");
                JButton spinButton = new JButton("탈수");
                JButton DryButton = new JButton("건조");
                buttonPanel.add(washButton);
                buttonPanel.add(rinseButton);
                buttonPanel.add(spinButton);
                buttonPanel.add(DryButton);
                frame.revalidate();
                frame.repaint();

                // 각 버튼에 대한 액션 리스너 등록
                washButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("세탁 중...");
                    }
                });

                rinseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("헹굼 중...");
                    }
                });

                spinButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("탈수 중...");
                    }
                });
                DryButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("건조 중...");
                    }
                });

                // 버튼 패널 제거
                mainPanel.remove(normalWasherButton);
                mainPanel.remove(dreamWasherButton);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
    }


}
