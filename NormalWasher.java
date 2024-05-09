package sw_FatroyMethod.washer;

// 일반 세탁기
class NormalWasher implements Washer{
    @Override
    public void wash() {
        System.out.println("일반 세탁기가 작동합니다.");
    }
}