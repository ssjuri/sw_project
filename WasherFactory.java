package sw_FatroyMethod.washer;

public class WasherFactory {
    public static Washer createWasher(String type) {
        if (type.equalsIgnoreCase("일반")) {
            return new NormalWasher();
        } else if (type.equalsIgnoreCase("드럼")) {
            return new DrumWasher();
        } else {
            return null;
        }
    }
}
