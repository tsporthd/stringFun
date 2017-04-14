import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by lpresswood on 4/14/17.
 * Copyright Zillion.com
 */
public class ParsedElementTest {

    String testString = "(id,created,employee(id,firstname,employeeType(id), lastname),location)";

    ParsedElement parser;

    @Before
    public void init(){
        parser = new ParsedElement(testString);
    }


    @Test
    public void testNonSorted(){

        List<ParsedElement> children = parser.getChildren();
        assertNotNull(children);

        assertTrue(children.get(0).getParent().equals("id"));
        assertTrue(children.get(1).getParent().equals("created"));
        assertTrue(children.get(2).getParent().equals("employee"));
        ParsedElement employee = children.get(2);
        assertTrue(employee.getChildren().size() == 4);
        List<ParsedElement> employeeChildren = employee.getChildren();
        assertTrue(employeeChildren.get(0).getParent().equals("id"));
        assertTrue(employeeChildren.get(1).getParent().equals("firstname"));
        assertTrue(employeeChildren.get(2).getParent().equals("employeeType"));

        List<ParsedElement> employeeTypeChildren = employeeChildren.get(2).getChildren();
        assertTrue(employeeTypeChildren.size() == 1);

        assertTrue(employeeChildren.get(0).getParent().equals("id"));
        assertTrue(employeeChildren.get(3).getParent().equals(" lastname"));



        assertTrue(children.get(3).getParent().equals("location"));

    }

}
