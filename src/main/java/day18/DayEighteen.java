package day18;

import filereader.FileReader;

import java.util.List;

public class DayEighteen {

    List<String> expressions;

    public DayEighteen(String filename) {
        expressions = FileReader.readFile(filename);
    }

    public long evaluateExpressions(boolean advancedRules) {
        long sumOfResults = 0;
        for (String expression : expressions) {
            sumOfResults += evaluate(expression, advancedRules);
        }
        return sumOfResults;
    }

    private long evaluate(String expression, boolean add_before_mult) {
        while(expression.contains("(")) {
            int idxMostRecentOpeningBracket = 0;
            for (int i = 0; i < expression.length(); i++) {
                if (expression.charAt(i) == '(') {
                    idxMostRecentOpeningBracket = i;
                    continue;
                }
                if (expression.charAt(i) == ')') {

                    long result = evaluate(expression.substring(idxMostRecentOpeningBracket+1, i), add_before_mult);
                    expression = expression.substring(0,idxMostRecentOpeningBracket) + result + expression.substring(i+1);
                    break;
                }
            }
        }
        if(!add_before_mult) return getResultBasicRules(expression);
        else return getResultAdvancedRules(expression);
    }

    private long getResultAdvancedRules(String expression) {
        //System.out.println("Input to result " + expression);
        String[] elementsOfExpression = expression.split(" ");
        long result = 1;
        if (expression.contains("+")) {
            for (int i = 1; i < elementsOfExpression.length-1; i += 2) {
                String curElement = elementsOfExpression[i];
                if (curElement.equals("+")) {
                    long intermediate = Long.parseLong(elementsOfExpression[i - 1]) + Long.parseLong(elementsOfExpression[i + 1]);
                    elementsOfExpression[i + 1] = intermediate + "";
                    elementsOfExpression[i - 1] = "";
                    elementsOfExpression[i] = "";
                }
            }
        }
        String newExpression = String.join("",elementsOfExpression);

        elementsOfExpression = newExpression.replace(" ", "").split("\\*");
        for (String s : elementsOfExpression) {
            result *= Long.parseLong(s);
        }
        return result;
    }

    private long getResultBasicRules(String expression) {
        String[] elementsOfExpression = expression.split(" ");
        long result = Long.parseLong(elementsOfExpression[0]);
        for (int i = 1; i < elementsOfExpression.length-1; i += 2) {
            String curElement = elementsOfExpression[i];
            if (curElement.equals("*")) {
                result *= Long.parseLong(elementsOfExpression[i+1]);
            } else {
                result += Long.parseLong(elementsOfExpression[i+1]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        DayEighteen dayEighteen = new DayEighteen("src/main/resources/dayeighteen.txt");
        System.out.println(dayEighteen.evaluateExpressions(false));
        System.out.println(dayEighteen.evaluateExpressions(true));
    }
}
