

public class Calculator {

    public int add (int x, int y) {
        int result = x + y;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int result = calculator.add(1,2);
        System.out.println("result= " + result);
    }
}