package deu.cse.laundry;

class Dryer16MinutesStrategy implements DryerStrategy {
    @Override
    public void dry() {
        System.out.println("건조 16분을 선택하였습니다");
        try {
            Thread.sleep(2000); // 3초 딜레이
            System.out.println("건조 중...");
            Thread.sleep(2000); // 5초 딜레이
            System.out.println("건조 완료");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
