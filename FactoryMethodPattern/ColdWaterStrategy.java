package FactoryMethodPattern;

public class ColdWaterStrategy implements NomalWashTemperatureStrategy {
    @Override
    public void execute() {
        // Code for cold water washing strategy
        System.out.println("일반 세탁기 냉수 선택 ");
    }
}
