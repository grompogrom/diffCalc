package com.pogrom.difffragments.data.data.solver;

public class Reg {
    public static String deleteZero(double d) {
        String text = String.valueOf(d);
        text = !text.contains(".") ? text : text.replaceAll("0*$", "").replaceAll("\\.$", "");
        text = text.contains(".") ? text.replaceAll("0*$","").replaceAll("\\.$","") : text;

        return text;
    }
}
