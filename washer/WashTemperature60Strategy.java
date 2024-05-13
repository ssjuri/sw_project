package sw_FatroyMethod.washer;

class WashTemperature60Strategy implements WashTemperatureStrategy {
    private WashTemperature temperature;

    public WashTemperature60Strategy(WashTemperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public void selectTemperature() {
        temperature.setTemperature();
    }
}