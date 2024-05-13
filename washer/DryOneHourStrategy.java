package sw_FatroyMethod.washer;

class DryOneHourStrategy implements DryStrategy {
    @Override
    public void executeDry() {
        System.out.println("1시간 동안 건조 진행");
        // Implement 1-hour drying logic
    }
}