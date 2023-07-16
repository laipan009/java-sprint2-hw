package utill;

import java.util.HashMap;
import java.util.Map;

public enum Months {
    JANUARY("Январь"),
    FEBRUARY("Февраль"),
    MARCH("Март"),
    APRIL("Апрель"),
    MAY("Май"),
    JUNE("Июнь"),
    JULY("Июль"),
    AUGUST("Август"),
    SEPTEMBER("Сентябрь"),
    OCTOBER("Октябрь"),
    NOVEMBER("Ноябрь"),
    DECEMBER("Декабрь");

    private String month;

    Months(String monthName) {
        this.month = monthName;
    }

    public String getMonthName() {
        return month;
    }

    public static Map returnMonths() {
        Map<Integer, String> months = new HashMap<>();
        months.put(1, Months.JANUARY.getMonthName());
        months.put(2, Months.FEBRUARY.getMonthName());
        months.put(3, Months.MARCH.getMonthName());
        months.put(4, Months.APRIL.getMonthName());
        months.put(5, Months.MAY.getMonthName());
        months.put(6, Months.JUNE.getMonthName());
        months.put(7, Months.JULY.getMonthName());
        months.put(8, Months.AUGUST.getMonthName());
        months.put(9, Months.SEPTEMBER.getMonthName());
        months.put(10, Months.OCTOBER.getMonthName());
        months.put(11, Months.NOVEMBER.getMonthName());
        months.put(12, Months.DECEMBER.getMonthName());
        return months;
    }

    public static String changeNumberToMonthName(int numberOfMonth) {
        Map<Integer, String> months = Months.returnMonths();
        return months.get(numberOfMonth);
    }

    public static int changeMonthNameToNumber(String monthName) {
        Map<Integer, String> months = Months.returnMonths();
        for (Integer numberMonth : months.keySet()) {
            if (months.get(numberMonth).equals(monthName)) {
                return numberMonth;
            }
        }
        return 0;
    }
}
