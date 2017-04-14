/**
 * Created by lpresswood on 4/14/17.
 * Copyright Zillion.com
 */
public class PrintDriver
{


    /**
     * Formats and displays string based on level of nested parenthesis.
     */
    static void outputString(String input)
    {
        ParsedElement contents = new ParsedElement(input);
        contents.PrintElements();
        System.out.println("");
        System.out.print("Sorted: ");
        System.out.println("");
        contents.PrintElementsSorted();
    }

    public static void main(String ... args)
    {
        String test_input = "(id,created,employee(id,firstname,employeeType(id),lastname),location)";
        outputString(test_input);

    }
}