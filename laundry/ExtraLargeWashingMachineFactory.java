package deu.cse.laundry;

public class ExtraLargeWashingMachineFactory extends WashingMachineFactory {
    @Override
    public WashingMachine createWashingMachine() {
        return new ExtraLargeWashingMachine();
    }
}