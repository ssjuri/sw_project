package sw_FatroyMethod.washer;

class DryTwoHoursStrategy implements DryStrategy {
    @Override
    public void executeDry() {
        System.out.println("2시간 동안 건조 진행");
        // Implement 2-hour drying logic
    }
}