package FactoryMethodPattern;

class DreamWasher implements Washer {
    DreamWasher() {
    }

    public void wash() {
        System.out.println("드럼 세탁기가 작동합니다.");
    }
}