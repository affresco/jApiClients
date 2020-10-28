package deribit.models.expiries;

import commons.models.expiries.BaseExpiry;
import deribit.models.currencies.DeribitCurrency;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class DeribitExpiry extends BaseExpiry {

    // This contains the instances present in the system
    private static HashMap<String, DeribitExpiry> expiries;

    private final Date creationDate;

    protected DeribitExpiry(Builder builder)
    {
        super(builder);
        this.creationDate = builder.creationDate;
    }

    private static DeribitExpiry getInstance(DeribitExpiry.Builder builder){

        // mAke sure we do have an instance of the HashMap
        if (expiries == null) {
            synchronized (expiries) {
                expiries = new HashMap<>();
            }
        }
        // Already present, just return
        String uniqueIdentifier = builder.getUniqueIdentifier();
        if (expiries.containsKey(uniqueIdentifier)){
            return expiries.get(uniqueIdentifier);
        }

        // Otherwise create it
        else {
            DeribitExpiry newExpiry = new DeribitExpiry(builder);
            synchronized (expiries) {
                expiries.put(builder.getUniqueIdentifier(), newExpiry);
            }
            return newExpiry;
        }
    }

    public static class Builder extends BaseExpiry.Builder<Builder>{

        protected Date creationDate;

        @Override
        public String getUniqueIdentifier() {
            return expiryDate.toString();
        }

        @Override
        public DeribitExpiry build() {
            return DeribitExpiry.getInstance(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Builder setCreationDate(Date val){
            this.creationDate = val;
            return self();
        }

    }

}








