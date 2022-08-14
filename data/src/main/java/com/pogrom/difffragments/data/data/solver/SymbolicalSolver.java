package com.pogrom.difffragments.data.data.solver;

import com.pogrom.difffragments.domain.model.EquationData;
import com.pogrom.difffragments.domain.model.linearSecondOrderEquation.Coefs;

import org.jetbrains.annotations.NotNull;

public class SymbolicalSolver extends SymSolver {
    int a;
    int b;
    int c;
    double d;
    double k1;
    double k2;
    String answer1,answer2;
    String result;

    private void chekDiscremenant() {
        d = Math.pow(-b, 2) - (4 * a * c);
    }


    private void discPositive(){
        if(d < 0){
            solveComplex();
        }else{
            solveStandart();
        }
    }

    private void solveStandart(){
        k1 = (b - Math.sqrt(d)) / (2 * a);
        k2 = (b + Math.sqrt(d)) / (2 * a);
        answer1 = Reg.deleteZero(k1);
        answer2 = Reg.deleteZero(k2);
    }

    private void solveComplex(){
        d = d * (-1);
        k1 = (b  / (2 * a)) * (-1);
        k2 = (Math.sqrt(d) / (2 * a));
        answer1 = Reg.deleteZero(k1);
        answer2 = Reg.deleteZero(k2);
        d = d * (-1);
    }


    private String getSymbolSolutionForNumbersRoots() {
        if ((k1 == k2) && (d < 0)) {
            result = String.format("(A₁ + A₂x)(e^%sx)cos%sx + (B₁ + B₂x)(e^%sx)sin%sx ", answer1,answer2,answer1,answer2);
        } else if(k1 == k2){
            result = String.format("(C₁ + С₂x)e^%sx", answer1);
        }else  if(d < 0){
            result = String.format("e^%sx(C₁cos%sx + С₂sin%sx)", answer1,answer2,answer2);
        } else {
            result = String.format("C₁e^%sx + С₂e^%sx", answer1, answer2);
        }
        return result;
    }


    @Override
    public boolean validate(@NotNull EquationData equationData) {
        try {
            Coefs coefs = (Coefs)equationData.getCoefs();
            a = Integer.parseInt(coefs.getA());
            b = Integer.parseInt(coefs.getB());
            c = Integer.parseInt(coefs.getC());
        }catch (NumberFormatException ex){
            return false;
        }
        return  a != 0;
    }


    @NotNull
    @Override
    public String solve() {
        String solution = "";
            chekDiscremenant();
            discPositive();
            solution = getSymbolSolutionForNumbersRoots();
            return solution;
    }
}




