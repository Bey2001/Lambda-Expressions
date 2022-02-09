import java.util.ArrayList;
import java.util.function.Consumer;

/*******************************************************************************
 * Functional Interfaces
 * Group of interfaces for use in Lambda Expressions
 *  Lambda Expressions need these to formalize their parameters
 ******************************************************************************/

/**
 * Specific void Functional Interface
 * 
 * Designed only to run a zero-parameter Lambda function with a void return
 * value
 */
interface EmptyFunction {
    void run();
}

/**
 * Specific String Functional Interface
 * 
 * Designed only to run a one-parameter Lambda function with a String return
 * value
 */
interface StringFunction {
    String run(String str);
}

/**
 * Specific void Functional Interface
 * 
 * Designed only to run a two-parameter Lambda function with an int return
 * value
 */
interface DualFunction {
    int run(int a, int b);
}

/**
 * Instance of a multi-functional Functional Interface
 * 10/10 do not recommend
 * 
 * Not a true functional interface since multiple methods are present
 * Can still be used, but strongly discouraged.
 * 
 * Functional Interfaces should be limited to just 1 function
 */
interface MessyFunction {
    String run(String str);

    void run();
}

/*******************************************************************************
 * End of Functional Interfaces
 ******************************************************************************/

/**
 * Class to demonstrate some of the cool things one can do in Java 1.8's version
 * of Lambda Expressions. This is by no means an exhaustive demonstration, but
 * showcases some of the brief ways that Java can be used to imitate functional
 * programming.
 * 
 * Main method uses args as a switch for different examples of using Lambda
 * expressions.
 * 
 * Some more information can be found from
 * https://www.w3schools.com/java/java_lambda.asp
 */
public class Lambda {

    /**
     * Static StringFunction to append an '!' character to any String
     */
    private static StringFunction exclaim = (n) -> {
        return n + "!";
    };

    /**
     * Static StringFunction to append a '?' characer to any String
     */
    private static StringFunction ask = (n) -> {
        return n + "?";
    };

    public static void main(String[] args) {

        // Simple case of a void-returning zero-parameter Lambda Expression
        EmptyFunction empty = () -> {
            System.out.println("No parameters here!");
        };

        //Simple case of a String-returning one-parameter Lambda Expression
        StringFunction writeMessage = (String message) -> {
            return "The message is as follows:\n" + message;
        };

        //Simple case of a two-parameter Lambda Expression
        DualFunction multiplication = (int a, int b) -> {
            return a * b;
        };

        // Switch statement for various outcomes based on the first command line
        // argument
        // Above functions are defined where they are because the linter will give
        // syntax errors if placed in switch statement
        switch (args[0]) {

            //Use of zero-parameter Lambda Expression
            case "0":
                empty.run();
                break;

            //Use of single-parameter Lambda Expression
            case "1":
                String message = writeMessage.run("Tada!  This is the result of another Lambda Expression");
                message = exclaim.run(message);
                System.out.println(message);
                break;

            //Use of two-parameter Lambda Expression
            case "2":
                int a = 99;
                int b = 101;
                int product = multiplication.run(a, b);
                System.out.println("The product of " + a + " and " + b + " is " + product);
                break;

            //Example using ArrayList.forEach() method
            case "3":
                exList();
                break;

            //Example using Consumer for ArrayList.forEach() method
            case "4":
                exConsumerList();
                break;

            //Example using a method call to a locally-defined Lambda Expression
            case "5":
                exMethod();
                break;

            //Example using a method call to a statically-defined Lambda Expression
            case "6":
                exStaticMethod();
                break;

            default:
                empty.run();
                break;
        }
        // Finish statement common to all calls
        printFormatted("All done", exclaim);
    }

    /** 
     * Method showcasing the use of the forEach() method of the ArrayList class
     */
    public static void exList() {
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(719.210);
        list.add(719.768);
        list.add(719.771);
        list.add(719.77125);
        // Note the type for the parameter:
        // Consumer <? super Double> action
        list.forEach((n) -> {
            System.out.println("Next guess is: " + n);
        });
    }

    /**
     * Method showcasing the use of a Consumer object in the forEach() method 
     *  of the ArrayList class
     */
    public static void exConsumerList() {
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(719.210);
        list.add(719.768);
        list.add(719.771);
        list.add(719.77125);
        // Note the type for the parameter:
        // Consumer <? super Double> action
        Consumer<Double> method = (n) -> {
            System.out.println("Consumer says the next guess is: " + n);
        };
        list.forEach(method);
    }

    /**
     * Method showcasing the use of locally-defined Lambda Expressions
     */
    public static void exMethod() {
        // Local StringFunction that behaves like the static "exclaim"
        StringFunction exclamatory = (n) -> {
            return n + "!";
        };
        // Local StringFunction that behaves like the static "ask"
        StringFunction interrogative = (n) -> {
            return n + "?";
        };
        printFormatted("Local hello", exclamatory);
        printFormatted("Local hello", interrogative);
    }

    /**
     * Method showcasing the use of statically-defined Lambda Expressions
     */
    public static void exStaticMethod() {
        printFormatted("Static hello", Lambda.exclaim);
        printFormatted("Static hello", Lambda.ask);
    }

    /**
     * Helper method to run a StringFunction Lambda Expression
     */
    private static void printFormatted(String str, StringFunction format) {
        String result = format.run(str);
        System.out.println(result);
    }
}