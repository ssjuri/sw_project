package sw_FatroyMethod.washer;


// 세탁 온도 선택 전략을 사용하는 컨텍스트 클래스
class WashTemperatureSelector {
    private WashTemperatureStrategy strategy;

    public void setStrategy(WashTemperatureStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        strategy.selectTemperature();
    }
}