package deu.cse.laundry;

class Dryer20MinutesStrategy implements DryerStrategy {
    @Override
    public void dry() {
        System.out.println("건조 20분을 선택하였습니다");
        try {
            Thread.sleep(2000); // 3초 딜레이
            System.out.println("건조 중...");
            Thread.sleep(2000); // 10초 딜레이
            System.out.println("건조 완료");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
