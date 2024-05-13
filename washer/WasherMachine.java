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

        JButton normalWasherButton = new JButton("일반 세탁기(세탁+헹굼+탈수)");
        JButton drumWasherButton = new JButton("드럼 세탁기(세탁+헹굼+탈수+건조:추가금 발생)");

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

        JLabel label = new JLabel("세탁물의 무게를 입력하세요:");
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

    // 카운트 다운을 처리하는 메서드
    private static void countDown(int seconds) {
        // 1초씩 카운트 다운
        for (int i = seconds; i > 0; i--) {
            System.out.println("헹굼 완료까지 " + i + "초 남았습니다.");
            try {
                Thread.sleep(1000); // 1초 대기
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    private static void SpinDrycountDown(int min) {
        // 1초씩 카운트 다운
        for (int i = min; i > 0; i-=3) {
            System.out.println("탈수 완료까지 " + i + "분 남았습니다");
            try {
                Thread.sleep(1000); // 1초 대기
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    private static void DrycountDown(int min) {
        // 1초씩 카운트 다운
        for (int i = min; i > 0; i-=30) {
            System.out.println("건조 완료까지 " + i + "분 남았습니다");
            try {
                Thread.sleep(1000); // 1초 대기
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    // 헹굼 선택 창을 표시하고 세탁 온도 선택 기능 적용
    private static void showRinseButtonFrame(Washer washer, double weight) {
        JFrame rinseFrame = new JFrame("온도 선택");
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
        JButton rinseButton = new JButton("헹굼 선택하러 가기");
        rinseButton.setEnabled(false); // 초기에는 비활성화 상태로 설정

        panel.add(label);



        if (washer instanceof NormalWasher) {
            JButton warmWaterButton = new JButton("온수 세탁");
            JButton coldWaterButton = new JButton("냉수 세탁");
            JButton NrinseButton = new JButton("헹굼 선택하러 가기");
            NrinseButton.setEnabled(false); // 초기에는 비활성화 상태로 설정

            warmWaterButton.addActionListener(e -> {
                NomalWashTemperatureStrategy strategy = new WarmWaterStrategy();
                strategy.execute();
                        // 5초 뒤에 헹구러 가기 버튼 활성화
                        Timer timer = new Timer(5000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                NrinseButton.setEnabled(true);
                                ((Timer) e.getSource()).stop(); // 타이머 중지
                            }
                        });
                        timer.start();

                        // 5초 카운트 다운 출력
                        Thread countdownThread = new Thread(() -> {
                            for (int i = 5; i > 0; i--) {
                                System.out.println("세탁 완료까지 "+i + "초 남았습니다.");
                                try {
                                    Thread.sleep(1000); // 1초 대기
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                        countdownThread.start();


            });

            coldWaterButton.addActionListener(e -> {
                NomalWashTemperatureStrategy strategy = new ColdWaterStrategy();
                strategy.execute();
                // 5초 뒤에 헹구러 가기 버튼 활성화
                Timer timer = new Timer(5000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NrinseButton.setEnabled(true);
                        ((Timer) e.getSource()).stop(); // 타이머 중지
                    }
                });
                timer.start();

                // 5초 카운트 다운 출력
                Thread countdownThread = new Thread(() -> {
                    for (int i = 5; i > 0; i--) {
                        System.out.println("세탁 완료까지 "+i + "초 남았습니다.");
                        try {
                            Thread.sleep(1000); // 1초 대기
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                countdownThread.start();


            });

            //일밤 헹굼 창과 버튼
            NrinseButton.addActionListener(e -> {

                JFrame additionalRinseFrame = new JFrame("추가 헹굼 선택");
                additionalRinseFrame.setSize(300, 200);
                additionalRinseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                additionalRinseFrame.setLocationRelativeTo(null);

                JPanel additionalRinsePanel = new JPanel();



                JButton rinse2TimesButton = new JButton("헹굼 2회 (기본)");
                JButton rinse3TimesButton = new JButton("헹굼 3회 (추가비용 +500원)");
                JButton rinse4TimesButton = new JButton("헹굼 4회 (추가비용 +1000원)");
                JButton spindry = new JButton("탈수하러 가기");
                spindry.setEnabled(false); // 초기에는 비활성화 상태로 설정

                rinse2TimesButton.addActionListener(e2 -> {
                    System.out.println("헹굼 2회 선택");

                    // 3초 카운트 다운
                    countDown(4);
                    spindry.setEnabled(true);
                });

                rinse3TimesButton.addActionListener(e3 -> {
                    System.out.println("헹굼 3회 선택");

                    // 6초 카운트 다운
                    countDown(6);
                    spindry.setEnabled(true);
                });

                rinse4TimesButton.addActionListener(e4 -> {
                    System.out.println("헹굼 4회 선택");

                    // 9초 카운트 다운
                    countDown(8);
                    spindry.setEnabled(true);
                });


                additionalRinsePanel.add(rinse2TimesButton);
                additionalRinsePanel.add(rinse3TimesButton);
                additionalRinsePanel.add(rinse4TimesButton);
                additionalRinsePanel.add(spindry);

                additionalRinseFrame.add(additionalRinsePanel);
                additionalRinseFrame.setVisible(true);

                spindry.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame spinDryFrame = new JFrame("탈수 선택");
                        spinDryFrame.setSize(300, 200);
                        spinDryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        spinDryFrame.setLocationRelativeTo(null);

                        JPanel panel = new JPanel();
                        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                        JLabel label = new JLabel("탈수 옵션을 선택하세요:");

                        JButton spinOption10Button = new JButton("9분 탈수");
                        JButton spinOption15Button = new JButton("15분 탈수");
                        JButton spinOption20Button = new JButton("21분 탈수");
                        JButton done = new JButton("끝내기");
                        done.setEnabled(false); // 초기에는 비활성화 상태로 설정
                        JButton dry = new JButton("추가 건조기 사용하러가기(추가비용 + 1000원)");
                        dry.setEnabled(false); // 초기에는 비활성화 상태로 설정

                        spinOption10Button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.out.println("9분 탈수 선택");
                                SpinDrycountDown(9);

                                done.setEnabled(true);
                                dry.setEnabled(true);
                            }
                        });

                        spinOption15Button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.out.println("15분 탈수 선택");

                                SpinDrycountDown(15);
                                done.setEnabled(true);
                                dry.setEnabled(true);
                            }
                        });

                        spinOption20Button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.out.println("21분 탈수 선택");

                                SpinDrycountDown(21);
                                done.setEnabled(true);
                                dry.setEnabled(true);
                            }
                        });
                        done.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // 헹굼 선택 후 최종금액 계산

                                // 최종금액 창 생성
                                JFrame finalPaymentFrame = new JFrame("최종금액");
                                finalPaymentFrame.setSize(300, 150);
                                finalPaymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                finalPaymentFrame.setLocationRelativeTo(null);

                                JPanel panel = new JPanel();


                                finalPaymentFrame.add(panel);
                                finalPaymentFrame.setVisible(true);
                            }
                        });
                        dry.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFrame dryFrame = new JFrame("건조 선택");
                                dryFrame.setSize(300, 200);
                                dryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                dryFrame.setLocationRelativeTo(null);

                                JPanel dryPanel = new JPanel();
                                dryPanel.setLayout(new BoxLayout(dryPanel, BoxLayout.Y_AXIS));

                                JLabel dryLabel = new JLabel("건조 시간을 선택하세요:");

                                JButton dryOneHourButton = new JButton("1시간 건조 (0-2kg 추천)");
                                JButton dryTwoHoursButton = new JButton("2시간 건조 (2-4kg 추천)");
                                JButton dryThreeHoursButton = new JButton("3시간 건조 (4-6kg 추천)");
                                JButton money = new JButton("정산하러 가기");
                                money.setEnabled(false);
                                dryOneHourButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        DryStrategy strategy = new DryOneHourStrategy();
                                        strategy.executeDry();
                                        DrycountDown(60);
                                        money.setEnabled(true);
                                    }
                                });

                                dryTwoHoursButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        DryStrategy strategy = new DryTwoHoursStrategy();
                                        strategy.executeDry();
                                        DrycountDown(120);
                                        money.setEnabled(true);
                                    }
                                });

                                dryThreeHoursButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        DryStrategy strategy = new DryThreeHoursStrategy();
                                        strategy.executeDry();
                                        DrycountDown(180);
                                        money.setEnabled(true);
                                    }
                                });

// 드럼 세탁기의 헹굼 선택 후 최종금액을 계산하고 보여주는 로직
                                money.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        // 헹굼 선택 후 최종금액 계산

                                        // 최종금액 창 생성
                                        JFrame finalPaymentFrame = new JFrame("최종금액");
                                        finalPaymentFrame.setSize(300, 150);
                                        finalPaymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                        finalPaymentFrame.setLocationRelativeTo(null);

                                        JPanel panel = new JPanel();


                                        finalPaymentFrame.add(panel);
                                        finalPaymentFrame.setVisible(true);
                                    }
                                });

                                dryPanel.add(dryLabel);
                                dryPanel.add(dryOneHourButton);
                                dryPanel.add(dryTwoHoursButton);
                                dryPanel.add(dryThreeHoursButton);
                                dryPanel.add(money);

                                dryFrame.add(dryPanel);
                                dryFrame.setVisible(true);
                            }
                        });

                        panel.add(label);
                        panel.add(spinOption10Button);
                        panel.add(spinOption15Button);
                        panel.add(spinOption20Button);
                        panel.add(done);
                        panel.add(dry);

                        spinDryFrame.add(panel);
                        spinDryFrame.setVisible(true);
                    }
                });

            });


            panel.add(warmWaterButton);
            panel.add(coldWaterButton);
            panel.add(NrinseButton);
        } else if (washer instanceof DrumWasher) {

            panel.add(temp40Button);
            panel.add(temp60Button);
            panel.add(temp90Button);
            panel.add(rinseButton);
        }


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
                // 5초 뒤에 헹구러 가기 버튼 활성화
                Timer timer = new Timer(5000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        rinseButton.setEnabled(true);
                        ((Timer) e.getSource()).stop(); // 타이머 중지
                    }
                });
                timer.start();

                // 5초 카운트 다운 출력
                Thread countdownThread = new Thread(() -> {
                    for (int i = 5; i > 0; i--) {
                        System.out.println("세탁 완료까지 "+i + "초 남았습니다.");
                        try {
                            Thread.sleep(1000); // 1초 대기
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                countdownThread.start();
            }


        });

        temp60Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WashTemperature washTemperature = new WashTemperature60();
                temperatureSelector.setStrategy(new WashTemperature60Strategy(washTemperature)); // 세탁기 전략 설정
                temperatureSelector.executeStrategy(); // 전략 실행
                // 5초 뒤에 헹구러 가기 버튼 활성화
                Timer timer = new Timer(5000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        rinseButton.setEnabled(true);
                        ((Timer) e.getSource()).stop(); // 타이머 중지
                    }
                });
                timer.start();

                // 5초 카운트 다운 출력
                Thread countdownThread = new Thread(() -> {
                    for (int i = 5; i > 0; i--) {
                        System.out.println("세탁 완료까지 "+i + "초 남았습니다.");
                        try {
                            Thread.sleep(1000); // 1초 대기
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                countdownThread.start();
            }


        });
        temp90Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WashTemperature washTemperature = new WashTemperature90();
                temperatureSelector.setStrategy(new WashTemperature90Strategy(washTemperature)); // 세탁기 전략 설정
                temperatureSelector.executeStrategy(); // 전략 실행
                // 5초 뒤에 헹구러 가기 버튼 활성화
                Timer timer = new Timer(5000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        rinseButton.setEnabled(true);
                        ((Timer) e.getSource()).stop(); // 타이머 중지
                    }
                });
                timer.start();

                // 5초 카운트 다운 출력
                Thread countdownThread = new Thread(() -> {
                    for (int i = 5; i > 0; i--) {
                        System.out.println("세탁 완료까지 "+i + "초 남았습니다.");
                        try {
                            Thread.sleep(1000); // 1초 대기
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                countdownThread.start();
            }


        });




        // 드럼 '헹굼 선택하러 가기' 버튼 액션 리스너
        rinseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame rinseFrequencyFrame = new JFrame("헹굼 빈도 선택");
                rinseFrequencyFrame.setSize(300, 200);
                rinseFrequencyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                rinseFrequencyFrame.setLocationRelativeTo(null);

                JPanel rinsePanel = new JPanel();


                // 헹굼 빈도 선택 버튼 생성
                JButton rinse2TimesButton = new JButton("헹굼 2회(기본)");
                JButton rinse3TimesButton = new JButton("헹굼 3회 (추가비용 +500원)");
                JButton rinse4TimesButton = new JButton("헹굼 4회 (추가비용 +1000원)");
                JButton spindry = new JButton("탈수하러 가기");
                spindry.setEnabled(false);

                // 각 버튼의 ActionListener 설정
                rinse2TimesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        System.out.println("헹굼 2회 선택");
                        countDown(4);
                        spindry.setEnabled(true);
                      //  rinseFrequencyFrame.dispose(); // 창 닫기
                    }
                });

                rinse3TimesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 헹굼 3회 선택 시 처리할 작업
                        // 예: 헹굼 빈도에 따른 동작 수행
                        System.out.println("헹굼 3회 선택");
                        countDown(6);
                        spindry.setEnabled(true);
                    }
                });

                rinse4TimesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 헹굼 4회 선택 시 처리할 작업
                        // 예: 헹굼 빈도에 따른 동작 수행
                        System.out.println("헹굼 4회 선택");
                        countDown(8);
                        spindry.setEnabled(true);

                    }
                });

                // 패널에 버튼 추가
                rinsePanel.add(rinse2TimesButton);
                rinsePanel.add(rinse3TimesButton);
                rinsePanel.add(rinse4TimesButton);
                rinsePanel.add(spindry);

                // 새 창에 패널 추가 후 화면에 표시
                rinseFrequencyFrame.add(rinsePanel);
                rinseFrequencyFrame.setVisible(true);

                spindry.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame spinDryFrame = new JFrame("탈수 선택");
                        spinDryFrame.setSize(300, 200);
                        spinDryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        spinDryFrame.setLocationRelativeTo(null);

                        JPanel panel = new JPanel();
                        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                        JLabel label = new JLabel("탈수 옵션을 선택하세요:");

                        JButton spinOption10Button = new JButton("9분 탈수");
                        JButton spinOption15Button = new JButton("15분 탈수");
                        JButton spinOption20Button = new JButton("21분 탈수");
                        JButton dry = new JButton("건조하러 가기");
                        dry.setEnabled(false);

                        spinOption10Button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.out.println("9분 탈수 선택");

                                SpinDrycountDown(9);
                                dry.setEnabled(true);
                            }
                        });

                        spinOption15Button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.out.println("15분 탈수 선택");

                                SpinDrycountDown(15);
                                dry.setEnabled(true);
                            }
                        });

                        spinOption20Button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.out.println("21분 탈수 선택");

                                SpinDrycountDown(21);
                                dry.setEnabled(true);

                            }
                        });
                        dry.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFrame dryFrame = new JFrame("건조 선택");
                                dryFrame.setSize(300, 200);
                                dryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                dryFrame.setLocationRelativeTo(null);

                                JPanel dryPanel = new JPanel();
                                dryPanel.setLayout(new BoxLayout(dryPanel, BoxLayout.Y_AXIS));

                                JLabel dryLabel = new JLabel("건조 시간을 선택하세요:");

                                JButton dryOneHourButton = new JButton("2시간 건조 (0-2kg 추천");
                                JButton dryTwoHoursButton = new JButton("3시간 건조 (2-4kg 추천)");
                                JButton dryThreeHoursButton = new JButton("4시간 건조 (4-6kg 추천)");
                                JButton money = new JButton("정산하러 가기");
                                money.setEnabled(false);
                                dryOneHourButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        DryStrategy strategy = new DryOneHourStrategy();
                                        strategy.executeDry();
                                        DrycountDown(120);
                                        money.setEnabled(true);

                                    }
                                });

                                dryTwoHoursButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        DryStrategy strategy = new DryTwoHoursStrategy();
                                        strategy.executeDry();
                                        DrycountDown(180);
                                        money.setEnabled(true);
                                    }
                                });

                                dryThreeHoursButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        DryStrategy strategy = new DryThreeHoursStrategy();
                                        strategy.executeDry();
                                        DrycountDown(240);
                                        money.setEnabled(true);
                                    }
                                });
                                money.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        // 헹굼 선택 후 최종금액 계산

                                        // 최종금액 창 생성
                                        JFrame finalPaymentFrame = new JFrame("최종금액");
                                        finalPaymentFrame.setSize(300, 150);
                                        finalPaymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                        finalPaymentFrame.setLocationRelativeTo(null);

                                        JPanel panel = new JPanel();


                                        finalPaymentFrame.add(panel);
                                        finalPaymentFrame.setVisible(true);
                                    }
                                });


                                dryPanel.add(dryLabel);
                                dryPanel.add(dryOneHourButton);
                                dryPanel.add(dryTwoHoursButton);
                                dryPanel.add(dryThreeHoursButton);
                                dryPanel.add(money);

                                dryFrame.add(dryPanel);
                                dryFrame.setVisible(true);
                            }
                        });

                        panel.add(label);
                        panel.add(spinOption10Button);
                        panel.add(spinOption15Button);
                        panel.add(spinOption20Button);
                        panel.add(dry);



                        spinDryFrame.add(panel);
                        spinDryFrame.setVisible(true);
                    }
                });
            }
        });


    }
}
