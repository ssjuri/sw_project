package sw_FatroyMethod.washer;

// 40도 세탁 온도 구현 클래스
class WashTemperature40 implements WashTemperature {
    @Override
    public void setTemperature() {
        System.out.println("세탁 온도를 40도로 설정합니다.");
    }
}