//Christine Lai
//This program reads data from a csv file and prints data onto an outful file

import java.util.Scanner;
import java.io.*;

public class Lab3 {
  public static void main(String [] args) throws IOException {
    
    int rows = 200; //initialize rows to 200 = (max number of songs in csv list)
    int cols = 2; //column 2 of csv list contains name of artists
    int numCount; //will be used to count the number of times artist's song appears
    
    String[][] myList = new String[rows][cols]; //create new 2D array
    
    //loop allows methods to run for each position in 2D array
    for (int i = 0; i < rows; i++) { 
      for (int j = 0; j < cols; j++) {
        myList[i][j] = "";
      }
    }
    
    numCount = readData(myList);
    
    //create new string array to isolate only name of artist
    String[] artistNames = new String[numCount];
    for(int i = 0; i <artistNames.length; i++) {
      artistNames[i] = myList[i][0]; //uses onlly the row of 2D array
    }
    
    sort(artistNames);
    printData(artistNames);
      
  }
    
    //method reads and allocates data from csv file into 2D array
    public static int readData(String[][] array) throws IOException {

      Scanner sc = new Scanner(new File("regional-global-daily-latest.csv"));
      
      String str; //will hold string value taken from csv file
      int i = 0;

      
      //skips first 3 lines from the csv file
      str = sc.nextLine();
      str = sc.nextLine();
      str = sc.nextLine();
      
      //scans next lines of data
      while(sc.hasNextLine()) {
        str = sc.nextLine();
        String artistName;
        
        //array will temporarily hold split string in order to locate artist name
        String[] temp = str.split(","); 
        artistName = temp[temp.length-3]; // name is located on first position of array
        
        // compare method will compare current artist to other artists listed
        if(compare(array, i + 1, artistName) != -1) {
          int row = compare(array, i + 1, artistName);
          int col = Integer.parseInt(array[row][1]) + 1;
          array[row][1] = Integer.toString(col);
        }
        
        //if returns -1, artist name will be added into array
        else { 
          array[i][0] = artistName;
          array[i][1] = "1"; //String 1 allocated for newly added artists
          i++;
        }
      }
      
      sc.close();
      return i; //returns i as total number of songs counted
    }
    
    //method checks if artist name is already in array
    public static int compare(String[][] array, int num, String name){
      
      for(int i = 0; i<num; i++) {
        if(array[i][0].equals(name)) 
          return i; //returns i if current artist's name searched has a match in array
      }
      
      return -1; //returns -1 if artist name is not yet listed
    }
    
    //method sorts the array in alphabetical order
    public static void sort(String[] arr) {
      String temp;
      for(int i = 0; i < arr.length; i++) {
        for (int k = i+1; k < arr.length; k++) {
          if(arr[i].compareToIgnoreCase(arr[k]) > 0) {
            temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;
          }
        }
      }
    }
    
    //method prints data onto an output file 
    public static void printData(String[] arr) throws IOException {
      
      PrintWriter outputText = new PrintWriter("Artists-WeekOf08302020.txt");
      outputText.println("Artist in Regional Global Daily Top 200\n\n");
      
      //loop prints out each data stored in 2D array
      for(int i = 0; i < arr.length; i++) {
      outputText.println((i+1) + ". " + arr[i] + "\n");
      } 
      
      outputText.close();
      
    }
}
    
    
      
      
      
      
      
      