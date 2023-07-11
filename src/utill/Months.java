package utill;

import java.util.HashMap;
import java.util.Map;

public enum Months {
    Январь,
    Февраль,
    Март,
    Апрель,
    Май,
    Июнь,
    Июль,
    Август,
    Сентябрь,
    Октябрь,
    Ноябрь,
    Декабрь;

    public static Map returnMonths() {
        Map<Integer, String> months = new HashMap<>();
        months.put(0, Months.Январь.name());
        months.put(1, Months.Февраль.name());
        months.put(2, Months.Март.name());
        months.put(3, Months.Апрель.name());
        months.put(4, Months.Май.name());
        months.put(5, Months.Июнь.name());
        months.put(6, Months.Июль.name());
        months.put(7, Months.Август.name());
        months.put(8, Months.Сентябрь.name());
        months.put(9, Months.Октябрь.name());
        months.put(10, Months.Ноябрь.name());
        months.put(11, Months.Декабрь.name());
        return months;
    }
    public static String changeNumberToMonthName(int numberOfMonth) {
        Map <Integer, String> months = Months.returnMonths();
        return months.get(numberOfMonth);
    }
    public static int changeMonthNameToNumber (String monthName) {
        Map <Integer, String> months = Months.returnMonths();
        for (Integer numberMonth : months.keySet()) {
            if (months.get(numberMonth).equals(monthName)) {
                return numberMonth;
            }
        }
        return 0;
    }
}
