package deu.cse.laundry;

class CleanWashStrategy implements WashStrategy {
    @Override
    public void wash() {
        try {
            System.out.println("청정 코스를 선택하였습니다.");
            Thread.sleep(3000); // 3초 딜레이
            System.out.println("세탁 중...");
            Thread.sleep(5000); // 5초 딜레이
            System.out.println("세탁 1회 완료");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rinse() {
        try {
            Thread.sleep(3000); // 3초 딜레이
            System.out.println("헹굼 중...");
            Thread.sleep(30000); // 30초 딜레이
            System.out.println("헹굼 4회 완료");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void spin() {
        try {
            Thread.sleep(3000); // 3초 딜레이
            System.out.println("탈수 중...");
            Thread.sleep(5000); // 5초 딜레이
            System.out.println("탈수 완료");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getTime() {
        return 40;
    }
}