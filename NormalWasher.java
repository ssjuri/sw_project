package FactoryMethodPattern;

class NormalWasher implements Washer {
    NormalWasher() {
    }

    public void wash() {
        System.out.println("일반 세탁기가 작동합니다.");
    }
}
