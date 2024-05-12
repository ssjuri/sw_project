package sw_FatroyMethod.washer;

// 60도 세탁 온도 구현 클래스
class WashTemperature60 implements WashTemperature {
    @Override
    public void setTemperature() {
        System.out.println("세탁 온도를 60도로 설정합니다.");
    }
}