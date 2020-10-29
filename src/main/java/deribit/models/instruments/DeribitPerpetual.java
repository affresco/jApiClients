package deribit.models.instruments;


public class DeribitPerpetual extends DeribitInstrument {

    protected DeribitPerpetual(Builder builder) {
        super(builder);
    }

    public static class Builder extends DeribitInstrument.Builder {
        @Override
        protected DeribitPerpetual build() {
            return (DeribitPerpetual) super.build();
        }
    }

}
