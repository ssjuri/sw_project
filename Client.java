package sw_FatroyMethod;

public class Client {
    public static void main(String[] args) {
        // 일반 세탁기 생성
        WashingMachineFactory regularFactory = new RegularWashingMachineFactory();
        WashingMachine regularWasher = regularFactory.createWashingMachine();
        regularWasher.wash(); // "일반 세탁기로 세탁 중..." 출력

        // 드럼 세탁기 생성
        WashingMachineFactory drumFactory = new DrumWashingMachineFactory();
        WashingMachine drumWasher = drumFactory.createWashingMachine();
        drumWasher.wash(); // "드럼 세탁기로 세탁 중..." 출력
    }
}
