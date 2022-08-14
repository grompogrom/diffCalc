package com.pogrom.difffragments.data.data.tool;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

public class EquationParser {
    String args;
    Function at;
    float calc;

    //пиши в стиле "At(params - буквы которые будут использоввны внутри функции) = функция"
    public EquationParser(String function){
        String f  = "At(x) = ".concat(function)  ;
        at = new Function(f);

    }
    public static boolean EquationCheck(String function){
        return true;
    }
    //пиши в стиле  "At: (2,4)" - под свои параметры
    private void setArgs(float s){
        args = "At(".concat(String.valueOf(s)).concat(")");
    }
    // исполнение функции и возвращение значения
    public double returnCalculate(float s){
        setArgs(s);
        Expression eq = new Expression(args, at);
        calc = (float) eq.calculate();
        return  calc;
    }

}
