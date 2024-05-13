package sw_FatroyMethod.washer;

class DryThreeHoursStrategy implements DryStrategy {
    @Override
    public void executeDry() {
        System.out.println("3시간 동안 건조 진행");
        // Implement 3-hour drying logic
    }
}