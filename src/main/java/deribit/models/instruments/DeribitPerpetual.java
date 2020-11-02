package deribit.models.instruments;


public class DeribitPerpetual extends DeribitInstrument {

    protected DeribitPerpetual(Builder builder) {
        super(builder);
    }

    public static class Builder extends DeribitInstrument.Builder {

        @Override
        protected DeribitPerpetual.Builder self() {
            return this;
        }

        @Override
        protected DeribitInstrument build() {
            DeribitInstrument t = DeribitInstrument.getInstance(self());
            return t;
        }
    }

}
