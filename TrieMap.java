//Note: All of your TrieMapInterface method implementations must function recursively
//I have left the method signatures from my own solution, which may be useful hints in how to approach the problem
//You are free to change/remove/etc. any of the methods, as long as your class still supports the TrieMapInterface
import java.util.ArrayList;
import java.util.HashMap;

public class TrieMap implements TrieMapInterface{
  TrieMapNode root;
  
  public TrieMap(){
    root = new TrieMapNode();
  }
  
  //Indirectly recursive method to meet definition of interface
  public void put(String key, String value){
    put(root, key, value);
  }
  
  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public void put(TrieMapNode current, String curKey, String value) {
    if (curKey.length() > 1 && !current.getChildren().containsKey(curKey.charAt(0))) {          // curkey > 1, children DOESNT HAVE the first letter of curkey
      HashMap<Character, TrieMapNode> temp = current.getChildren();
      temp.put(curKey.charAt(0), new TrieMapNode());
      current.setChildren(temp);                                                                // added curkey and new triemap node into children of current
      put(current.getChildren().get(curKey.charAt(0)), curKey.substring(1), value);             // RECURSIVE CALL on put again, current is now the node of the 1st letter, curKey is now spliced, value is same
    } else if (curKey.length() > 1 && current.getChildren().containsKey(curKey.charAt(0))) {    // curkey > 1, children HAS the first letter of curkey
      put(current.getChildren().get(curKey.charAt(0)), curKey.substring(1), value);             // RECURSIVE CALL on put again, current is now the node of the 1st letter, curKey is now spliced, value is same
    } else if (curKey.length() == 1 && !current.getChildren().containsKey(curKey.charAt(0))) {  // curkey = 1, children DOESNT HAVE the curkey
      HashMap<Character, TrieMapNode> temp = current.getChildren();
      temp.put(curKey.charAt(0), new TrieMapNode());
      current.setChildren(temp);                                                                // added curkey and new triemap node into children of current
      current.getChildren().get(curKey.charAt(0)).setValue(value);                              // set the value of the added node of the curKey as the value
      return;
    } else if (curKey.length() == 1 && !current.getChildren().containsKey(curKey.charAt(0))) {  // curkey = 1, children HAS the curkey
      current.getChildren().get(curKey.charAt(0)).setValue(value);                              // set the value of the node of the curKey as the value
      return;
    }
  }

  //Indirectly recursive method to meet definition of interface
  public String get(String key){
    return get(root, key);
  }
  
  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public String get(TrieMapNode current, String curKey) {
    // base case - first letter of curKey isnt in the curNode's hashmap - value not found
    if (curKey.length() > 1 && !current.getChildren().containsKey(curKey.charAt(0))) {
      return null;
    } else if (curKey.length() == 1 && current.getChildren().containsKey(curKey.charAt(0)) && current.getChildren().get(curKey.charAt(0)).getValue() == null) { // base case - first letter of curKey is in the curNode's hashmap and the curKey is only one letter long - value found, return the value of the node one level down
      return null;
    } else if (curKey.length() == 1 && current.getChildren().containsKey(curKey.charAt(0)) && current.getChildren().get(curKey.charAt(0)).getValue() != null) { // base case - first letter of curKey is in the curNode's hashmap and the curKey is only one letter long - value found, return the value of the node one level down
      return current.getChildren().get(curKey.charAt(0)).getValue();
    } else { // recursive case - first letter of curKey is in the curNode's hashmap and the curKey is longer than one letter, keep going (current.getChildren().containsKey(curKey.charAt(0)) && curKey.length() > 1)
      return get(current.getChildren().get(curKey.charAt(0)), curKey.substring(1));
    }
  }

  
  //Indirectly recursive method to meet definition of interface
  public boolean containsKey(String key){
    return containsKey(root, key);
  }
  
  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public boolean containsKey(TrieMapNode current, String curKey) {
    if (current.getChildren().isEmpty() && current.getValue() == curKey) {                                          // base case, if children hashmap is empty, then its the end of a branch and a word - if the node's value matches the curkey, then return true
      return true;
    } else if (!current.getChildren().isEmpty() && current.getValue() != null && current.getValue() == curKey) {    // base case, children hashmap isnt empty, but the node contains a value - if the node's value matches the curkey, then return true
      return true;
    } else if (!current.getChildren().isEmpty() && current.getValue() != null && current.getValue() != curKey) {    // RECURSIVE case, children hashmap isnt empty, but the node contains a value - if the node's doesnt matches the curkey, then keep going one level down
      for (TrieMapNode value : current.getChildren().values()) {
        containsKey(value, curKey);
      }
    } else if (!current.getChildren().isEmpty() && current.getValue() == null) {                                    // RECURSIVE case, children hashmap isnt empty, and the node's value is null - keep going one level down
      for (TrieMapNode value : current.getChildren().values()) {
        containsKey(value, curKey);
      }
    }
      return false;                                                                                                   // everything else returns false
  }







  
  //Indirectly recursive method to meet definition of interface
  public ArrayList<String> getKeysForPrefix(String prefix){
    ArrayList<String> answer = new ArrayList<String>();
    getSubtreeKeys(findNode(root, prefix), answer);
    return answer;
  }
  
  //Recursive helper function to find node that matches a prefix
  //Note: only a suggestion, you may solve the problem is any recursive manner
  public TrieMapNode findNode(TrieMapNode current, String curKey){          // very similar to get method
    // base case - first letter of curKey isnt in the curNode's hashmap - value not found
    if (!current.getChildren().containsKey(curKey.charAt(0))) {
      return null;
    } else if (current.getChildren().containsKey(curKey.charAt(0)) && curKey.length() == 1) { // base case - first letter of curKey is in the curNode's hashmap and the curKey is only one letter long - node found, return node one level down
      return current.getChildren().get(curKey.charAt(0));
    } else { // recursive case - first letter of curKey is in the curNode's hashmap and the curKey is longer than one letter, keep going  if (current.getChildren().containsKey(curKey.charAt(0)) && curKey.length() > 1)
      return findNode(current.getChildren().get(curKey.charAt(0)), curKey.substring(1));
    }
  }
  
  //Recursive helper function to get all keys in a node's subtree
  //Note: only a suggestion, you may solve the problem is any recursive manner
  public void getSubtreeKeys(TrieMapNode current, ArrayList<String> a) {    // very similar to print - go through entire tree starting at current prefix node, put all values found inside a
    if (current.getChildren().isEmpty()) {             // base case, if children hashmap is empty, then its the end of a branch and a word - add that node's value to a and leave
      a.add(current.getValue());
    } else if (current.getValue() != null) {          // recursive case, children hashmap isnt empty, but the node contains a value - add that node's value to a and keep going
      a.add(current.getValue());
      for (TrieMapNode value : current.getChildren().values()) {
        getSubtreeKeys(value, a);
      }
    } else {        // recursive case, children hashmap isnt empty, but the node's value is null - just keep going
      for (TrieMapNode value : current.getChildren().values()) {
      }
    }
  }

    //Indirectly recursive method to meet definition of interface
    public void print () {
      print(root);
    }

    //Recursive method to print values in tree
    public void print (TrieMapNode current){
      // base case, if children hashmap is empty, then its the end of a branch and a word - print the node's value and leave
      if (current.getChildren().isEmpty()) {
        System.out.println(current.getValue());
      } else if (current.getValue() != null) {          // recursive case, children hashmap isnt empty, but the node contains a value - print the node's value and keep going
        System.out.println(current.getValue());
        for (TrieMapNode value : current.getChildren().values()) {
          print(value);
        }
      } else {        // recursive case, children hashmap isnt empty, but the node's value is null - just keep going
        for (TrieMapNode value : current.getChildren().values()) {
          print(value);
        }
      }
    }

    public static void main (String[]args){
      TrieMap tester = new TrieMap();
      tester.put("car","car");
      tester.put("card","card");
      System.out.println(tester.get("car"));
      tester.print();
      System.out.println(tester.containsKey("ca"));
      System.out.println(tester.containsKey("car"));



    }

  }