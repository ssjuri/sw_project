package deu.cse.laundry;

public class LargeWashingMachineFactory extends WashingMachineFactory {
    @Override
    public WashingMachine createWashingMachine() {
        return new LargeWashingMachine();
    }
}
