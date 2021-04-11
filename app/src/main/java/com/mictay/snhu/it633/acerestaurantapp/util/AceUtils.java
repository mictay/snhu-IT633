package com.mictay.snhu.it633.acerestaurantapp.util;

import java.text.NumberFormat;
import java.util.Locale;

public abstract class AceUtils {

    public static String formatCurrency(double input) {
        return NumberFormat.getCurrencyInstance( new Locale("en", "US")).format(input);
    }

}
