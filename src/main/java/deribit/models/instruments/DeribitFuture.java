package deribit.models.instruments;

import java.util.HashMap;

public class DeribitFuture extends DeribitInstrument {

    // This contains the instances present in the system
    private static HashMap<String, DeribitFuture> instances;

    protected DeribitFuture(Builder builder) {
        super(builder);
    }

    public static class Builder extends DeribitInstrument.Builder {

        @Override
        protected DeribitFuture.Builder self() {
            return this;
        }

        @Override
        protected DeribitFuture build() {
            return DeribitFuture.getInstance(this);
        }
    }

    public static DeribitFuture getInstance(DeribitFuture.Builder builder){

        // Make sure we do have an instance of the HashMap
        if (instances == null) {
            synchronized (DeribitFuture.class) {
                instances = new HashMap<>();
            }
        }

        // Already present, just return
        String uniqueIdentifier = builder.getUniqueIdentifier();
        if (instances.containsKey(uniqueIdentifier)){
            return instances.get(uniqueIdentifier);
        }

        // Otherwise create it
        else {
            DeribitFuture newInstrument = new DeribitFuture(builder);
            synchronized (DeribitFuture.class) {
                instances.put(builder.getUniqueIdentifier(), newInstrument);
            }
            return newInstrument;
        }
    }

}
