package sw_FatroyMethod.washer;

public class NormalWasher implements Washer {

    public NormalWasher() {
        System.out.println("NormalWasher 객체가 생성되었습니다.");
    }

    @Override
    public void wash() {
        System.out.println("일반 세탁기가 작동합니다.");
    }
}
