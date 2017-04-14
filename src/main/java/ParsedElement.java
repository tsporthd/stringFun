import java.util.ArrayList;
import java.util.Collections;


/**
 * Represents an element and its nested children.
 */
class ParsedElement implements Comparable<ParsedElement> {


    ParsedElement(String input) {
        this(input, "");
    }


    ParsedElement(String input, String parent_input) {
        this.parent = parent_input;
        String element_string = StringFun.EMPTY_STRING;
        String parent_string = StringFun.EMPTY_STRING;
        int open_parenthesis = 0;

        for (char character : input.toCharArray()) {
            // Add top most elements (and their children) to sub_elements
            switch (character) {
                case StringFun.OPEN_PAREN:
                    ++open_parenthesis;
                    if (open_parenthesis > 2) {
                        element_string += character;
                    }
                    break;

                case StringFun.CLOSE_PAREN:
                    --open_parenthesis;
                    if (open_parenthesis > 1) {
                        element_string += character;
                    } else if (open_parenthesis == 0) {
                        // Last element
                        String contents = StringFun.EMPTY_STRING;
                        ParsedElement sub_element = new ParsedElement(contents, parent_string);
                        children.add(sub_element);
                    }
                    break;

                case StringFun.COMMA:
                    if (open_parenthesis == 1) {
                        // Append element to children
                        String contents = StringFun.EMPTY_STRING;
                        if (!element_string.equals(StringFun.EMPTY_STRING)) {
                            contents = StringFun.OPEN_PAREN + element_string + StringFun.CLOSE_PAREN;
                        }

                        ParsedElement sub_element = new ParsedElement(contents, parent_string);
                        children.add(sub_element);
                        element_string = StringFun.EMPTY_STRING;
                        parent_string = StringFun.EMPTY_STRING;
                    } else {
                        element_string += character;
                    }
                    break;

                default:
                    if (open_parenthesis == 1) {
                        parent_string += character;
                    } else {
                        element_string += character;
                    }
            }
        }
    }

    @Override
    public int compareTo(ParsedElement o) {
        return parent.compareTo(o.parent);
    }

    /**
     * Prints elements, including proper indentation for their nested level.
     */
    void PrintElements() {
        PrintElements(0);
    }


    void PrintElements(int level) {
        PrintElementsHelper(level, false);
    }

    void PrintElementsSorted()
    {
        PrintElementsSorted(0);
    }

    void PrintElementsSorted(int level)
    {
        PrintElementsHelper(level, true);
    }

    private String parent;
    private ArrayList<ParsedElement> children = new ArrayList<>();

    // Format and print the parent element and its children
    private void PrintElementsHelper(int level, boolean sorted) {

        if (sorted) {
            Collections.sort(children);
        }


        for (int count = 1; count < level; ++count) {
            System.out.print("-");
        }

        if (level > 1) {
            System.out.print(StringFun.SPACE);
        }

        System.out.print(parent);
        System.out.println(StringFun.EMPTY_STRING);

        // Recursive call for children
        for (ParsedElement element : children) {
            if (sorted) {
                element.PrintElementsSorted(level + 1);
            } else {
                element.PrintElements(level + 1);
            }
        }
    }
}

