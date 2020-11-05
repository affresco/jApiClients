package deribit.models.instruments;


import java.util.HashMap;

public class DeribitPerpetual extends DeribitInstrument {

    // This contains the instances present in the system
    private static HashMap<String, DeribitPerpetual> instances;

    protected DeribitPerpetual(Builder builder) {
        super(builder);
    }

    public static class Builder extends DeribitInstrument.Builder {

        @Override
        protected DeribitPerpetual.Builder self() {
            return this;
        }

        @Override
        protected DeribitPerpetual build() {
            return DeribitPerpetual.getInstance(this);
        }
    }

    public static DeribitPerpetual getInstance(DeribitPerpetual.Builder builder){

        // Make sure we do have an instance of the HashMap
        if (instances == null) {
            synchronized (DeribitPerpetual.class) {
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
            DeribitPerpetual newInstrument = new DeribitPerpetual(builder);
            synchronized (DeribitPerpetual.class) {
                instances.put(builder.getUniqueIdentifier(), newInstrument);
            }
            return newInstrument;
        }
    }

}

