package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private final static Comparator<String> BYDAYS = new SortByDays();
    private final static Comparator<String> BYORDER = new SortByMonthOrder();

    @Override
    public Comparator<String> sortByDays() {
        return BYDAYS;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return BYORDER;
    }

    public enum Month {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        Month(int days) {
            this.days = days;
        }

        public int getDays() {
            return this.days;
        }

        public static Month fromString(final String month) {
            final String m = month.toUpperCase();
            Month match = null;
            for(final Month mo : Month.values()) {
                if(mo.name().startsWith(m)) {
                    if( match != null) {
                        throw new IllegalArgumentException(m + "matches to multiple names");
                    }
                    match = mo;
                }
            }
            if(match == null) {
                throw new IllegalArgumentException("No matches founded for " + m);
            }
            return match;
        }    
    }

    private static final class SortByMonthOrder implements Comparator<String> {
        @Override
        public int compare(final String o1, final String o2) {
            return Month.fromString(o1).compareTo(Month.fromString(o2));
        }
    }

    private static final class SortByDays implements Comparator<String> {
        @Override
        public int compare(final String o1, final String o2) {
            Month m1 = Month.fromString(o1);
            Month m2 = Month.fromString(o2);
            return Integer.compare(m1.getDays(), m2.getDays());
        }
    }
}
