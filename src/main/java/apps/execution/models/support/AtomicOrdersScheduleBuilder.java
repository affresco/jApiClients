package apps.execution.models.support;


import java.util.ArrayList;
import java.util.HashMap;

public class AtomicOrdersScheduleBuilder {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private static final double AMOUNT_TOLERANCE = 1e-6;

    // ##################################################################
    // CONSTRUCTOR
    // ##################################################################

    private AtomicOrdersScheduleBuilder() {
    }

    // ##################################################################
    // INTERFACE
    // ##################################################################

    public static HashMap<Integer, Double> buildSchedule(double amount, double minimumTradeAmount, double durationInSeconds, double minimumTimeSpacingInSeconds) throws Exception {

        // Total number of units to be traded
        int totalTradingUnits = getNumberOfTradeUnits(amount, minimumTradeAmount);

        // No real schedule to be made here
        if (totalTradingUnits == 1) {
            HashMap<Integer, Double> schedule = new HashMap<>();
            schedule.put(0, minimumTradeAmount);
            return schedule;
        }

        // Compute the time step in between orders (in seconds, as float)
        double timeStepInSeconds = getTimeSpacingInBetweenOrders(durationInSeconds, totalTradingUnits, minimumTimeSpacingInSeconds);

        // Create a list of time steps for trading (keys = time in millis, as integers)
        ArrayList<Integer> tradingTimes = getTradingTimeSchedule(durationInSeconds, timeStepInSeconds);

        // Make a first pass at the schedule
        HashMap<Integer, Integer> baseUnitSchedule = buildUnitSchedule(tradingTimes, totalTradingUnits);

        int residualUnits = totalTradingUnits - computeTotalNumberOfUnits(baseUnitSchedule);

        if (residualUnits == 0) {
            return finalizeSchedule(baseUnitSchedule, minimumTradeAmount);
        }

        if (residualUnits == 1) {
            baseUnitSchedule.put(0, 1);
            return finalizeSchedule(baseUnitSchedule, minimumTradeAmount);
        }

        // Compute a time step separating the residual units
        double residualTimeSpacing = getTimeSpacingInBetweenOrders(durationInSeconds, residualUnits, minimumTimeSpacingInSeconds);

        // Make a time schedule for residuals
        ArrayList<Integer> residualTradingTimes = getTradingTimeSchedule(durationInSeconds, residualTimeSpacing);

        // Increment the base schedule
        HashMap<Integer, Integer> schedule  = overlayResidualUnitSchedule(baseUnitSchedule, residualTradingTimes, residualUnits);

        if (computeTotalNumberOfUnits(schedule) != totalTradingUnits){
            throw new Exception();
        }

        return finalizeSchedule(baseUnitSchedule, minimumTradeAmount);
    }

    // ##################################################################
    // STATIC PRIVATE METHODS
    // ##################################################################

    private static int getNumberOfTradeUnits(double amount, double minimumTradeAmount) {

        if (amount < AMOUNT_TOLERANCE) {
            throw new IllegalArgumentException("Trade amount must be a positive number.");
        }

        if (minimumTradeAmount < AMOUNT_TOLERANCE) {
            throw new IllegalArgumentException("Minimum trade amount (clip) cannot be negative or zero.");
        }

        if (amount <= minimumTradeAmount) {
            return 1;
        }

        return (int) Math.ceil(amount / minimumTradeAmount);
    }

    private static double getTimeSpacingInBetweenOrders(double durationInSeconds, int numberOfTradeUnits, double minimumTimeSpacingInSeconds) {

        if (numberOfTradeUnits < 1) {
            throw new IllegalArgumentException("Trade units must be greater or equal to one.");
        }

        if (numberOfTradeUnits == 1) {
            return -1.0;
        }

        // The time in between successive trades
        return Math.max(durationInSeconds / (numberOfTradeUnits - 1), minimumTimeSpacingInSeconds);

    }

    private static ArrayList<Integer> getTradingTimeSchedule(double durationInSeconds, double timeSpacingInSeconds) {

        // Init time counter
        int t = 0;

        // Init empty schedule
        ArrayList<Integer> tradingTimes = new ArrayList<>();

        // Loop to fill all times
        while (t <= durationInSeconds) {
            if (t > durationInSeconds) {
                break;
            } else {
                tradingTimes.add((int) (t * 1000.0));
                t += timeSpacingInSeconds;
            }
        }
        return tradingTimes;
    }

    private static HashMap<Integer, Integer> buildUnitSchedule(ArrayList<Integer> tradingTimes, int totalTradingUnits) {

        // How many trading times
        int numberOfSteps = tradingTimes.size();

        // Basic size for every time step
        int unitsPerTradingTime = totalTradingUnits / numberOfSteps;

        while (numberOfSteps * unitsPerTradingTime > totalTradingUnits){
            unitsPerTradingTime -= 1;
            if (unitsPerTradingTime < 0) {
                throw new IllegalArgumentException();
            }
        }

        // Output result
        return getUnitSchedule(tradingTimes, totalTradingUnits, unitsPerTradingTime);
    }

    private static HashMap<Integer, Integer> overlayResidualUnitSchedule(HashMap<Integer, Integer> schedule, ArrayList<Integer> residualTradingTimes, int residualUnits) {

        // How many trading times
        int numberOfSteps = residualTradingTimes.size();

        int totalUnits = 0;
        int residualUnitsPerStep = Math.max((int) (residualUnits / numberOfSteps), 1);

        for (int t : residualTradingTimes) {

            if (totalUnits + residualUnitsPerStep > residualUnits) {
                break;
            }

            int current = 0;

            if (schedule.containsKey(t)) {
                current = schedule.get(t);
            }
            schedule.put(t, current + residualUnitsPerStep);
            totalUnits += residualUnitsPerStep;
        }
        return schedule;
    }

    private static HashMap<Integer, Integer> removeExcessUnitsFromSchedule(HashMap<Integer, Integer> schedule, int mismatch) {
        for (int t : schedule.keySet()) {
            int current = schedule.get(t);
            int reduceBy = Math.min(mismatch, schedule.get(t));
            schedule.put(t, current - reduceBy);
            mismatch -= reduceBy;
            if (mismatch <= 0) {
                break;
            }
        }
        return schedule;
    }

    private static HashMap<Integer, Integer> addMissingUnitsToSchedule(HashMap<Integer, Integer> schedule, int mismatch) {
        int current = schedule.get(0);
        schedule.put(0, current + Math.abs(mismatch));
        return schedule;
    }

    private static int computeTotalNumberOfUnits(HashMap<Integer, Integer> schedule) {
        int totalUnits = 0;
        for (int t : schedule.keySet()) {
            totalUnits += schedule.get(t);
        }
        return totalUnits;
    }

    private static HashMap<Integer, Integer> getUnitSchedule(ArrayList<Integer> tradingTimes, int totalTradingUnits, int unitsPerTradingTime) {

        // Output
        HashMap<Integer, Integer> schedule = new HashMap<>();

        // Keep track of all units allocated so far (total)
        int allocatedUnits = 0;

        for (int t : tradingTimes) {
            schedule.put(t, unitsPerTradingTime);
            allocatedUnits += unitsPerTradingTime;
            if (allocatedUnits == totalTradingUnits) {
                break;
            }
        }
        return schedule;
    }

    private static HashMap<Integer, Double> finalizeSchedule(HashMap<Integer, Integer> schedule, double minimumTradeAmount) {
        HashMap<Integer, Double> amountSchedule = new HashMap<>();
        for (int t : schedule.keySet()) {
            amountSchedule.put(t, minimumTradeAmount * schedule.get(t));
        }
        return amountSchedule;
    }

    public static void main(String[] args) {

        try {
            var schedule = AtomicOrdersScheduleBuilder.buildSchedule(240.0, 10.0, 60.0, 5.0);
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

























