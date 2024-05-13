package sw_FatroyMethod.washer;

public class DrumWasher implements Washer {

    public DrumWasher() {
        System.out.println("DrumWasher 객체가 생성되었습니다.");
    }

    @Override
    public void wash() {
        System.out.println("드럼 세탁기가 작동합니다.");
    }
}
