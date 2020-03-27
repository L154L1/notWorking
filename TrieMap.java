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
  public void put(TrieMapNode current, String curKey, String value){
    // base case
    if (curKey == ""){
      // add the value to that node
      return;
    } else if (current.getChildren().containsKey(curKey.charAt(0))){            // if the letter is already there?
      put(current.getChildren().get(curKey.charAt(0)), curKey.substring(1), value);
    } else if (curKey.length() != 1){          // if the letter isnt already there, and its not the last letter? add the first letter as a key in the current node's hashmap, and the value is a new node
      current.getChildren().put(curKey.charAt(0), new TrieMapNode());
      current.setChildren(current.getChildren());
      put(current.getChildren().get(curKey.charAt(0)), curKey.substring(1), value);
    } else {    //if the letter isnt already there, and its the last letter, add the letter as a key in the current node's hashmap, and the value is a new node who'se value is the entire curKey
      current.getChildren().put(curKey.charAt(0), new TrieMapNode(value));
      current.setChildren(current.getChildren());
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
    if (!current.getChildren().containsKey(curKey.charAt(0))) {
      return null;
    } else if (current.getChildren().containsKey(curKey.charAt(0)) && curKey.length() == 1) { // base case - first letter of curKey is in the curNode's hashmap and the curKey is only one letter long - value found, return the value of the node one level down
      return current.getChildren().get(curKey.charAt(0)).getValue();
    } else if (current.getChildren().containsKey(curKey.charAt(0)) && curKey.length() > 1) { // recursive case - first letter of curKey is in the curNode's hashmap and the curKey is longer than one letter, keep going
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
    // base case - first letter of curKey isnt in the curNode's hashmap - word not found
    if (!current.getChildren().containsKey(curKey.charAt(0))) {
      return false;
    } else if (current.getChildren().containsKey(curKey.charAt(0)) && curKey.length() == 1) {                // base case - first letter of curKey is in the curNode's hashmap and curkey is only one letter long - return true
      return true;
    } else if (current.getChildren().containsKey(curKey.charAt(0)) && curKey.length() > 1) {        // recursive case - first letter of curKey is in the curNode's hashmap & curKey is more than 1 letter long - go down a level, splice curKey
      return containsKey(current.getChildren().get(curKey.charAt(0)), curKey.substring(1));
    }
  }
  
  //Indirectly recursive method to meet definition of interface
  public ArrayList<String> getKeysForPrefix(String prefix){
    ArrayList<String> answer = new ArrayList<String>();
    answer = getSubtreeKeys(findNode(root, prefix), answer);
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
    } else if (current.getChildren().containsKey(curKey.charAt(0)) && curKey.length() > 1) { // recursive case - first letter of curKey is in the curNode's hashmap and the curKey is longer than one letter, keep going
      return findNode(current.getChildren().get(curKey.charAt(0)), curKey.substring(1));
    }
  }
  
  //Recursive helper function to get all keys in a node's subtree
  //Note: only a suggestion, you may solve the problem is any recursive manner
  public ArrayList<String> getSubtreeKeys(TrieMapNode current, ArrayList<String> a) {    // very similar to print - go through entire tree starting at current prefix node, put all values found inside a
    if (current.getChildren().isEmpty()) {             // base case, if children hashmap is empty, then its the end of a branch and a word - add that node's value to a and leave
      a.add(current.getValue());
      return a;
    } else if (current.getValue() != null) {          // recursive case, children hashmap isnt empty, but the node contains a value - add that node's value to a and keep going
      a.add(current.getValue());
      for (TrieMapNode value : current.getChildren().values()) {
        getSubtreeKeys(value, a);
      }
    } else {        // recursive case, children hashmap isnt empty, but the node's value is null - just keep going
      for (TrieMapNode value : current.getChildren().values()) {
        getSubtreeKeys(value, a);
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
      //You can add some code in here to test out your TrieMap initially
      //The TrieMapTester includes a more detailed test
    }

  }