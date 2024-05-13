package sw_FatroyMethod.washer;

public class WasherFactory {
    // 팩토리 메소드: 세탁기 객체를 생성하여 반환하는 메소드
    public static Washer createWasher(String type) {
        if (type.equalsIgnoreCase("일반")) {
            return new NormalWasher(); // "일반" 타입의 세탁기 객체 생성
        } else if (type.equalsIgnoreCase("드럼")) {

            return new DrumWasher(); // "드럼" 타입의 세탁기 객체 생성
        } else {
            return null; // 알 수 없는 타입인 경우 null 반환
        }
    }
}
