
import java.util.ArrayList;

/**
 * @author abner
 */

class Interval
{
    char origin;
    char destiny;
    public Interval(char origin, char destiny)
    {
        this.origin = origin;
        this.destiny = destiny;
    }
}

public class Set {
    String pattern;
    String lexical_component;
    ArrayList<Character> elements1;
    Interval elements2;
    
    public Set(String pattern, String lexical_component)
    {
        this.pattern = pattern;
        this.lexical_component = lexical_component;
    }
    
    public boolean check_element(int element)
    {
        boolean state = false;
        if(elements1 != null)
        {
            for (char data : elements1) {
                //if(element == integer)
                    //state = true;
            }
        }
        if(elements2 != null)
        {
            if(elements2.origin <= element && elements2.destiny >= element)
                state = true;
        }
        return state;
    }
    
    public void analize_pattern()
    {
        char character;
        elements1 = new ArrayList<>();
        for (int i = 0; i < pattern.length(); i++) {
            character = pattern.charAt(i);
            if(character == ',')
            {
                elements1.add(pattern.charAt(i-1));
            }else if(character == '~')
                elements2 = new Interval(pattern.charAt(i-1), pattern.charAt(i+1));
        }
    }
}
