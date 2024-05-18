package deu.cse.laundry;

public class LargeWashingMachine implements WashingMachine {
    @Override
    public void wash(int weight) {
        System.out.println("대형 세탁기(26kg)로 세탁을 시작합니다.\n무게: " + weight + "kg");
    }
}
