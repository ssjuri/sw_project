package FactoryMethodPattern;

class WasherFactory {
    WasherFactory() {
    }

    public static Washer createWasher(String type) {
        if (type.equalsIgnoreCase("일반")) {
            return new NormalWasher();
        } else {
            return type.equalsIgnoreCase("드럼") ? new DreamWasher() : null;
        }
    }
}
