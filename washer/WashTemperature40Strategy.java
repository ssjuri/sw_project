package sw_FatroyMethod.washer;


// 각 세탁 온도에 대한 전략 클래스 구현
class WashTemperature40Strategy implements WashTemperatureStrategy {
    private WashTemperature temperature;

    public WashTemperature40Strategy(WashTemperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public void selectTemperature() {
        temperature.setTemperature();
    }
}
