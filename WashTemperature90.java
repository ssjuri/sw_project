package sw_FatroyMethod.washer;

// 90도 세탁 온도 구현 클래스
class WashTemperature90 implements WashTemperature {
    @Override
    public void setTemperature() {
        System.out.println("세탁 온도를 90도로 설정합니다.");
    }
}
