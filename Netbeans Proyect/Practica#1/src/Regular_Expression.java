
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author abner
 */
class Node
{
    String data;
    boolean defeasible;
    Node left;
    Node right;
    ArrayList<Integer> firsts;
    ArrayList<Integer> lasts;
    int num_child, id;
    
    public Node(String data, int num_child)
    {
        this.data = data;
        this.num_child = num_child;
        this.id = 0;
        this.left = null;
        this.right = null;
        firsts = new ArrayList<>();
        lasts = new ArrayList<>();
        this.defeasible = false;
    }
    
    public Node()
    {
        this.data = null;
        this.num_child = 0;
        this.id = 0;
        this.left = null;
        this.right = null;
        this.defeasible = false;
    }
}

class Terminal
{
    String id;
    ArrayList<Integer> elements;
    
    public Terminal(String id)
    {
        this.id = id;
        this.elements = new ArrayList<>();
    }
}

class Transition
{
    String origin;
    String destination;
    String non_terminal;
    
    public Transition(String origin, String destination, String non_terminal)
    {
        this.origin = origin;
        this.destination = destination;
        this.non_terminal = non_terminal;
    }
    
}

class Leaf {
    String nonterminal;
    int id;
    ArrayList<Integer> follows;

    public Leaf(String nonterminal, int id)    
    {
        this.nonterminal = nonterminal;
        this.id = id;
        this.follows = new ArrayList<>();
    }
}

public class Regular_Expression {
    String pattern;
    String lexical_component;
    Stack<Node> elements ;
    int num_elements = 0;
    Node root;
    ArrayList<Leaf> follows;
    ArrayList<String> nonterminals;
    ArrayList<Terminal> terminals;
    ArrayList<Transition> transitions;
    String txtfile;
    boolean last;
    
    public Regular_Expression(String pattern, String lexical_comp)
    {
        this.lexical_component = lexical_comp;
        this.pattern = pattern;
        this.elements = new Stack();
        this.root = new Node();
        this.follows = new ArrayList<>();
        this.nonterminals = new ArrayList<>();
        this.terminals = new ArrayList<>();
        this.transitions = new ArrayList<>();
    }
    
    public void generate_tree_expression()
    {
        int size_pattern = 0, pointer = pattern.length() - 1, initial_pointer = 0;
        char actual;
        last = false;
        
            elements.addElement(new Node("#", 0));
            while(true)
            {
                actual = pattern.charAt(pointer);
                switch (actual) {
                    case '}':
                        initial_pointer = pointer;
                        while(pattern.charAt(pointer) != '{')
                            pointer--;
                        elements.addElement(new Node(pattern.substring(pointer+1, initial_pointer), 0));
                        break;
                    case '+':
                    case '?':
                    case '*':
                        elements.addElement(new Node(String.valueOf(actual), 1));
                        num_elements = 0;
                        insert_Nodes();
                        break;
                    case '|':
                    case '.':
                        elements.addElement(new Node(String.valueOf(actual), 2));
                        num_elements = 0;
                        insert_Nodes();
                        break;
                    case '"':
                        initial_pointer = pointer--;
                        while(pattern.charAt(pointer) != '"')
                            pointer--;
                        elements.addElement(new Node(pattern.substring(pointer+1 , initial_pointer), 0));
                        break;
                    case ' ':
                        break;
                    default:
                        elements.addElement(new Node(String.valueOf(actual), 0));
                        insert_Nodes();
                }
                pointer--;
                if(pointer < size_pattern)
                {
                    elements.addElement(new Node(String.valueOf('.'), 2));
                    insert_Nodes();
                    this.root = elements.pop();
                    assing_number_leaf();
                    break;
                }
            }
        
        
    }
    
    public void insert_Nodes()
    {
        if(elements.isEmpty() || elements.lastElement().num_child < 1)
            return;
        Node element = elements.lastElement();
        if(element.num_child <= elements.size() && element.num_child == 1)
        {
            element = elements.pop();
            element.left = elements.pop();
        }else if(element.num_child <= elements.size() && element.num_child == 2)
        {
            element = elements.pop();
            element.left = elements.pop();
            element.right = elements.pop();
        }
                
        elements.addElement(element);
    }

    private void assing_number_leaf()
    {
        num_elements = 1;
        assing_number_leaf(root);
        num_elements = 0;
        define_defeasible();
        Set<String> withoutDupli = new LinkedHashSet<>(nonterminals);
        nonterminals.clear();
        nonterminals.addAll(withoutDupli);
        define_follows();
        transition_table();
    }
    
    private void assing_number_leaf(Node pivot)
    {
        if(pivot == null)
            return;
        assing_number_leaf(pivot.left);
        assing_number_leaf(pivot.right);
        
        if(pivot.num_child == 0)
        {
            pivot.id = num_elements;
            pivot.firsts.add(num_elements);
            pivot.lasts.add(num_elements);
            this.follows.add(new Leaf(pivot.data, num_elements));
            this.nonterminals.add(pivot.data);
            num_elements++;    
        }
    }
    
    private void define_defeasible()
    {
        define_defeasible(root);
    }
    
    private void define_defeasible(Node pivot)
    {
        if(pivot == null)
            return;
        define_defeasible(pivot.left);
        define_defeasible(pivot.right);
        
        if(pivot.num_child > 0)
        {
            char operator = pivot.data.charAt(0);
            switch (operator) {
                case '|':
                    if(pivot.left.defeasible || pivot.right.defeasible)
                        pivot.defeasible = true;
                    
                    //define firsts
                    for (int i = 0; i < pivot.left.firsts.size(); i++) {
                        pivot.firsts.add(pivot.left.firsts.get(i));
                    }
                    
                    for (int i = 0; i < pivot.right.firsts.size(); i++) {
                        pivot.firsts.add(pivot.right.firsts.get(i));
                    }

                    //define lasts
                    for (int i = 0; i < pivot.left.lasts.size(); i++) {
                        pivot.lasts.add(pivot.left.lasts.get(i));
                    }
                    
                    for (int i = 0; i < pivot.right.lasts.size(); i++) {
                        pivot.lasts.add(pivot.right.lasts.get(i));
                    }
                    
                    Collections.sort(pivot.firsts);
                    Collections.sort(pivot.lasts);
                    
                    break;
                case '.':
                    if(pivot.left.defeasible && pivot.right.defeasible)
                        pivot.defeasible = true;
                    
                    //define firsts
                    
                    if(pivot.left.defeasible == true)
                    {
                        for (int i = 0; i < pivot.left.firsts.size(); i++) {
                            pivot.firsts.add(pivot.left.firsts.get(i));
                        }

                        for (int i = 0; i < pivot.right.firsts.size(); i++) {
                            pivot.firsts.add(pivot.right.firsts.get(i));
                        }    
                    }else
                    {
                        for (int i = 0; i < pivot.left.firsts.size(); i++) {
                            pivot.firsts.add(pivot.left.firsts.get(i));
                        }
                    }
                    
                    //define lasts
                    if(pivot.right.defeasible == true)
                    {
                        for (int i = 0; i < pivot.left.lasts.size(); i++) {
                            pivot.lasts.add(pivot.left.lasts.get(i));
                        }

                        for (int i = 0; i < pivot.right.lasts.size(); i++) {
                            pivot.lasts.add(pivot.right.lasts.get(i));
                        }    
                    }else
                    {
                        for (int i = 0; i < pivot.right.lasts.size(); i++) {
                            pivot.lasts.add(pivot.right.lasts.get(i));
                        }
                    }
                    
                    Collections.sort(pivot.firsts);
                    Collections.sort(pivot.lasts);
                    
                    break;
                case '?':
                    pivot.defeasible = true;
                    
                    //define firsts
                    for (int i = 0; i < pivot.left.firsts.size(); i++) {
                        pivot.firsts.add(pivot.left.firsts.get(i));
                    }

                    //define lasts
                    for (int i = 0; i < pivot.left.lasts.size(); i++) {
                        pivot.lasts.add(pivot.left.lasts.get(i));
                    }
                    
                    Collections.sort(pivot.firsts);
                    Collections.sort(pivot.lasts);
                    
                    break;
                case '+':
                    if(pivot.left.defeasible)
                        pivot.defeasible = true;
                    
                    //define firsts
                    for (int i = 0; i < pivot.left.firsts.size(); i++) {
                        pivot.firsts.add(pivot.left.firsts.get(i));
                    }

                    //define lasts
                    for (int i = 0; i < pivot.left.lasts.size(); i++) {
                        pivot.lasts.add(pivot.left.lasts.get(i));
                    }
                    
                    Collections.sort(pivot.firsts);
                    Collections.sort(pivot.lasts);
                    
                    break;
                case '*':
                    pivot.defeasible = true;
                    
                    //define firsts
                    for (int i = 0; i < pivot.left.firsts.size(); i++) {
                        pivot.firsts.add(pivot.left.firsts.get(i));
                    }
                    
                    //define lasts
                    for (int i = 0; i < pivot.left.lasts.size(); i++) {
                        pivot.lasts.add(pivot.left.lasts.get(i));
                    }
                    
                    Collections.sort(pivot.firsts);
                    Collections.sort(pivot.lasts);
                    
                    break;
            }
        }
    }

    private void define_follows()
    {
        define_follows(root);
        for (Leaf follow : follows) {
            Collections.sort(follow.follows);
            Set<Integer> withoutDupli = new LinkedHashSet<>(follow.follows);
            follow.follows.clear();
            follow.follows.addAll(withoutDupli);
        }
    }
    
    private void define_follows(Node pivot)
    {
        if(pivot == null)
            return;
        define_follows(pivot.left);
        define_follows(pivot.right);
        
        if(pivot.num_child > 0)
        {
            char actual = pivot.data.charAt(0);
            switch (actual) {
                case '.':
                    for (int i = 0; i < pivot.left.lasts.size(); i++) {
                        for (int j = 0; j < follows.size(); j++) {
                            if(follows.get(j).id == pivot.left.lasts.get(i))
                                for (int k = 0; k < pivot.right.firsts.size(); k++)
                                    follows.get(j).follows.add(pivot.right.firsts.get(k));
                        }
                    }
                    break;
                case '*':
                case '+':
                    for (int i = 0; i < pivot.left.lasts.size(); i++) {
                        for (int j = 0; j < follows.size(); j++) {
                            if(follows.get(j).id == pivot.left.lasts.get(i))
                                for (int k = 0; k < pivot.left.firsts.size(); k++)
                                    follows.get(j).follows.add(pivot.left.firsts.get(k));
                        }
                    }
                    break;
            }
        }
    }

    private void transition_table()
    {
        terminals.add(new Terminal("S" + num_elements));
        num_elements++;
        terminals.get(0).elements.addAll(root.firsts);
        for (int i = 0; i < terminals.size(); i++)
            get_transition(terminals.get(i));
    }
    
    private void get_transition(Terminal pivot)
    {
        for (int i = 0 ; i < nonterminals.size() - 1 ; i++) {
            String nterminal = nonterminals.get(i);
            boolean entry = false;
            ArrayList<Integer> elements = new ArrayList<>();
            String nonterminal = "";
            for (Integer element : pivot.elements) {
                if(follows.get(element - 1).nonterminal.equals(nterminal))
                {
                    entry = true;
                    elements.addAll(follows.get(element-1).follows);
                    nonterminal = follows.get(element-1).nonterminal;
                }
            }
            
            if(entry)
            {
                if(verificate_elements(elements))
                    transitions.add(new Transition(pivot.id, terminals.get(get_index_terminal(elements)).id, nonterminal));
                else
                {
                    Terminal newt = new Terminal("S" + num_elements);
                    num_elements++;
                    newt.elements.addAll(elements);
                    terminals.add(newt);
                    transitions.add(new Transition(pivot.id, newt.id, nonterminal));
                }
            }
        }
    }
    
    private boolean verificate_elements(ArrayList<Integer> elements)
    {
        for (Terminal terminal : terminals) {
            if(elements.equals(terminal.elements))
                return true;
        }
        return false;
    }
    
    private int get_index_terminal(ArrayList<Integer> elements)
    {
        for (int i = 0 ; i < terminals.size() ; i++) {
            if(elements.equals(terminals.get(i).elements))
                return i;
        }
        return -1;
    }
    
    private String get_firts(Node node)
    {
        String firsts = "";
        firsts += node.firsts.get(0);
        for (int i = 1; i < node.firsts.size(); i++) {
            firsts += "," + node.firsts.get(i);
        }
        return firsts;
    }

    private String get_lasts(Node node)
    {
        String lasts = "";
        lasts += node.lasts.get(0);
        for (int i = 1; i < node.lasts.size(); i++) {
            lasts += "," + node.lasts.get(i);
        }
        return lasts;
    }
    
    public void tree_graph()
    {
        if (this.root != null)
        {
            txtfile = "";
            txtfile = "digraph Mass{\n";
            txtfile += "node[shape = record, height = 0.5, width = 1]; \n";
            txtfile += "graph[nodesep = 0.5]; \n";

            txtfile += String.valueOf(this.root).replaceAll("@", "_") + "[label= \"  <A0> F:" + get_firts(root) + "\\n";
            if(root.defeasible)
                txtfile += "A|";
            else
                txtfile += "N|";
            txtfile +=  this.root.data + "|";
            txtfile += " <A1> L:" + get_lasts(root);
            if(root.id > 0)
                txtfile += "\\nH#" + root.id;
            txtfile += " \"];\n";
            this.tree_graph(root);
            txtfile += "}";
            
            save_file(txtfile, lexical_component + "_tree");

        }
    }
    
    private void tree_graph(Node node)
    {
        if (node != null)
        {
            if(node.num_child == 1)
            {
                txtfile += String.valueOf(node.left).replaceAll("@", "_")  + "[label= \"  <A0> F:" + get_firts(node.left) + "\\n";
                if(node.left.defeasible)
                    txtfile += "A|";
                else
                    txtfile += "N|";
                txtfile +=  node.left.data + "|";
                txtfile += " <A1> L:" + get_lasts(node.left);
                if(node.left.id > 0)
                    txtfile += "\\nH#" + node.left.id;
                txtfile += " \"];\n";
                txtfile += String.valueOf(node).replaceAll("@", "_") +" -> "+ String.valueOf(node.left).replaceAll("@", "_")  +";\n";
            }else
            {
                if(node.left != null)
                {
                    txtfile += String.valueOf(node.left).replaceAll("@", "_")  + "[label= \"  <A0> F:" + get_firts(node.left) + "\\n";
                    if(node.left.defeasible)
                        txtfile += "A|";
                    else
                        txtfile += "N|";
                    txtfile +=  node.left.data + "|";
                    txtfile += " <A1> L:" + get_lasts(node.left);
                    if(node.left.id > 0)
                        txtfile += "\\nH#" + node.left.id;
                    txtfile += " \"];\n";
                    txtfile += String.valueOf(node).replaceAll("@", "_") +":A0 -> "+ String.valueOf(node.left).replaceAll("@", "_")  +";\n";
                }
                if(node.right != null)
                {
                    txtfile += String.valueOf(node.right).replaceAll("@", "_")  + "[label= \"  <A0> F:" + get_firts(node.right) + "\\n";
                    if(node.right.defeasible)
                        txtfile += "A|";
                    else
                        txtfile += "N|";
                    txtfile +=  node.right.data + "|";
                    txtfile += " <A1> L:" + get_lasts(node.right);
                    if(node.right.id > 0)
                        txtfile += "\\nH#" + node.right.id;
                    txtfile += " \"];\n";
                    txtfile += String.valueOf(node).replaceAll("@", "_") +":A1 -> "+ String.valueOf(node.right).replaceAll("@", "_")  +";\n";
                }    
            }
            
            
            this.tree_graph(node.left);
            this.tree_graph(node.right);
        }
    }
    
    public void graph_table_follows()
    {
        txtfile = "digraph Mass{\n";
        txtfile += "aHtmlTable [\nshape=plaintext\ncolor=black\n";
        txtfile += "label=<\n";
        
        txtfile += "<table border='1' cellborder='1'>\n";
        txtfile += "<tr><td colspan= '2'>Leaf</td><td>Follows</td></tr>\n";
        for (int i = 0 ; i < this.follows.size() ; i++) {
            String next = get_String_array(follows.get(i).follows);
            txtfile += "<tr><td>" + follows.get(i).nonterminal + "</td><td>" + String.valueOf(follows.get(i).id) + "</td><td>" + next + "</td></tr>\n"; 
        }
        txtfile += "</table>\n";
        txtfile += "\n>\n];\n}";
        save_file(txtfile, lexical_component + "_follows");

    }
    
    public void graph_table_transitions()
    {
        txtfile = "digraph Mass{\n";
        txtfile += "aHtmlTable [\nshape=plaintext\ncolor=black\n";
        txtfile += "label=<\n";
        
        txtfile += "<table border='1' cellborder='1'>\n";
        txtfile += "<tr><td>Estado</td><td colspan='"+ String.valueOf(nonterminals.size() - 1) +"'>Terminales</td></tr>\n";
        
        txtfile += "<tr><td></td>";
        for (int i = 0; i < nonterminals.size() - 1; i++) 
            txtfile += "<td>"+ nonterminals.get(i)  +"</td>";
        txtfile += "</tr>\n";

        for (int i = 0 ; i < this.terminals.size() ; i++) {
            
            txtfile += "<tr><td>" + terminals.get(i).id + "</td>";
            for (int j = 0; j < nonterminals.size() - 1; j++) 
            {
                boolean exist = false;
                for (Transition transition : transitions) {
                    if(transition.origin.equals(terminals.get(i).id) && nonterminals.get(j).equals(transition.non_terminal))
                    {
                        exist = true;
                        txtfile += "<td>" + transition.destination + "</td>"; 
                    }
                }
                if(!exist)
                    txtfile += "<td> - </td>"; 
            }
            txtfile += "</tr>\n"; 
        }
        txtfile += "</table>\n";
        txtfile += "\n>\n];\n}";
        save_file(txtfile, lexical_component + "_transitions");
    }
    
    public void graph_automaton()
    {
        if (!transitions.isEmpty())
        {
            txtfile = "";
            txtfile = "digraph Mass{\n";
            txtfile += "node[height = 1, width = 1]; \n";
            for (Terminal terminal : terminals) 
                txtfile += terminal.id + "[label= \""+ terminal.id +"\"];\n";
            for (Transition transition : transitions) {
                txtfile += transition.origin +" -> "+ transition.destination  +"[label=\"  "+ transition.non_terminal +"\" ];\n";
            }
        txtfile += "\n}";
            save_file(txtfile, lexical_component + "_automaton");
        }
    }
    
    private String get_String_array(ArrayList<Integer> elements)
    {
        String next = "";
        if(!elements.isEmpty())
        {
            next += String.valueOf(elements.get(0));
            for (int j = 1; j < elements.size() ; j++)
                next += ", " + String.valueOf(elements.get(j));
        }
        return next;
    }
    
    public static void save_file(String cadena,String name)
    {
        try
        {

            FileWriter fichero = new FileWriter(Init.folder + "\\" + name +".dot");
            PrintWriter pw = new PrintWriter(fichero);
            pw.print(cadena);
            fichero.close(); 
            String instruction = "dot -Tjpg \"" + Init.folder + "\\" + name +".dot\" -o \""+ Init.folder + "\\" + name +".jpg\"";
            Runtime.getRuntime().exec(String.format(instruction));
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
            }
            
            
        } catch (IOException e) {}
    }  
    
    public void analize_lexeme(String lexeme)
    {
        String actual = transitions.get(0).origin;
        int ingreso = 0;
        for (int i = 0; i < lexeme.length(); i++) {
            ingreso = i;
            for (int j = 0; j < transitions.size(); j++) {
                if(transitions.get(j).origin.equals(actual))
                {
                    
                    String data2 = transitions.get(j).non_terminal;
                    boolean set = verificate_sets(data2);
                    if(set)
                    {
                        set = verificate_char(lexeme.charAt(i), data2);
                        if(set)
                        {
                            i++;
                            actual = transitions.get(j).destination;
                            break;
                        }
                    }else
                    {
                        String data = lexeme.substring(i, i + transitions.get(j).non_terminal.length());
                        if(data2.equals(data))
                        {
                            i = i + transitions.get(j).non_terminal.length();
                            actual = transitions.get(j).destination;
                            break;
                        }
                    }
                }

            }
            if(ingreso == i)
            {
                Init.panel.write_console("Error en la validacion del lexema: \""+ lexeme + "\" con la expresion regular: \"" + lexical_component + "\"");
                return;
            }
        }
        if(aceptation(actual))
            Init.panel.write_console("La validacion del lexema: \""+ lexeme + "\" fue exitosa con la expresion regular: \"" + lexical_component + "\"");
    }
    
    private boolean verificate_sets(String lexical_c)
    {
        return Init.panel.verificate_if_set(lexical_c);
    }
    
    private boolean verificate_char(char actual, String nonterminal)
    {
        return Init.panel.verificate_char_in_set(nonterminal, actual);
    }
    
    private boolean aceptation(String origin)
    {
        for (Terminal terminal : terminals) {
            
            if(terminal.id.equals(origin))
            {
                for (Integer element : terminal.elements) {
                    if(root.right.id == element)
                        return true;
                }    
            }
        }
        return false;
    }
    
}
