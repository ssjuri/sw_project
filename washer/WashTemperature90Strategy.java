package sw_FatroyMethod.washer;

class WashTemperature90Strategy implements WashTemperatureStrategy {
    private WashTemperature temperature;

    public WashTemperature90Strategy(WashTemperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public void selectTemperature() {
        temperature.setTemperature();
    }
}