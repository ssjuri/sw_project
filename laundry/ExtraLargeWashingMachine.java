package deu.cse.laundry;

public class ExtraLargeWashingMachine implements WashingMachine {
    @Override
    public void wash(int weight) {
        System.out.println("초대형 세탁기(30kg)로 세탁을 시작합니다.\n무게: " + weight + "kg");
    }
}

