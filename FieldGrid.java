import java.lang.Math;
class FieldGrid {

  // All the Fields used within the code
  private String[][] Field;
  private String[][] BField;
  private int[][] NField;
  private String[][] showF;
  // Helps to determine whether the player has won/lost
  private static boolean win = false;
  private static boolean explode = false;
  // The design of the field/board
  private static String square = "🟩";
  private static String Bsquare = "🟫";
  private static String flag = "🚩";
  // Helps to keep track of guesses
  private static int Bcount;
  private static int Mcount;

  public FieldGrid(int size){
    // Sets the size of the every board (displayed field, bomb field, number field, showing field)
    Field = new String[size][size];
    BField = new String[size][size];
    NField = new int[size][size];
    showF = new String[size][size];

    // Fills the displayed board with green squares
    for(int row = 0; row < Field.length; row++){
      for(int col = 0; col < Field[0].length; col++){
        Field[row][col] = square;
        showF[row][col] = square;
      }
    }
    
    generateBombs(size);
    calcValue();
  }

  // Sets the numbers inside the NField (counts the bombs in the area of that cordinate)
  public void calcValue(){
    // Checks the inside of the board and adds the numbers to the number field which become the numbers on the board
    // Inner board [works]
    for(int row = 1; row < Field.length - 1; row++){
      for(int col = 1; col < Field[0].length - 1; col++){
        if(BField[row][col].equals(flag)){
          for(int r = row - 1; r < row + 2; r++){
            for(int c = col - 1; c < col + 2; c++){
              NField[r][c] += 1;
            }
          }
        }
      }
    }
    // Checks the top row/very first row of the board for bombs and adds the number to the area surrounding the bombs into number field which is printed on the board
    // Top row [works]
    for(int col = 1; col < Field[0].length - 1; col++){
      if(BField[0][col].equals(flag)){
        for(int r = 0; r < 2; r++){
          for(int c = col - 1; c < col + 2; c++){
            NField[r][c] += 1;
          }
        }
      }
    }
    // Checks the last row/very last row of the board for bombs and adds the number to the area surrounding the bombs into number field which is printed on the board
    // Bottom row [works]
    for(int col = 1; col < Field.length - 1; col++){
      if(BField[Field.length - 1][col].equals(flag)){
        for(int r = Field.length - 2; r < 8; r++){
          for(int c = col - 1; c < col + 2; c++){
            NField[r][c] += 1;
          }
        }
      }
    }
    // Checks the most left row of the board for bombs and adds the number to the area surrounding the bombs into number field which is printed on the board
    // Left row [works]
    for(int row = 1; row < Field.length - 1; row++){
      if(BField[row][0].equals(flag)){
        for(int r = row - 1; r < row + 2; r++){
          for(int c = 0; c < 2; c++){
            NField[r][c] += 1;
          }
        }
      }
    }
    // Checks the most right row of the board for bombs and adds the number to the area surrounding the bombs into number field which is printed on the board
    // Right row [works]
    for(int row = 1; row < Field.length - 1; row++){
      if(BField[row][Field[0].length - 1].equals(flag)){
        for(int r = row - 1; r < row + 2; r++){
          for(int c = Field.length - 2; c < Field.length; c++){
            NField[r][c] += 1;
          }
        }
      }
    }
    // Checks the top left corner of the board for bombs and adds the number to the area surrounding the bombs into number field which is printed on the board
    // Top left corner [works]
    if(BField[0][0].equals(flag)){
      for(int row = 0; row < 2; row++){
        for(int col = 0; col < 2; col++){
          NField[row][col] += 1;
        }
      }
    }
    // Checks the bottom left row of the board for bombs and adds the number to the area surrounding the bombs into number field which is printed on the board
    // Bottom left corner [works]
    if(BField[Field.length - 1][0].equals(flag)){
      for(int row = Field.length - 2; row < Field.length; row++){
        for(int col = 0; col < 2; col++){
          NField[row][col] += 1;
        }
      }
    }
    // Checks the top right row of the board for bombs and adds the number to the area surrounding the bombs into number field which is printed on the board
    // Top right corner [works]
    if(BField[0][Field.length - 1].equals(flag)){
      for(int row = 0; row < 2; row++){
        for(int col = Field.length - 2; col < Field.length; col++){
          NField[row][col] += 1;
        }
      }
    }
    // Checks the bottom right row of the board for bombs and adds the number to the area surrounding the bombs into number field which is printed on the board
    // Bottom right corner [works]
    if(BField[Field.length - 1][Field.length - 1].equals(flag)){
      for(int row = Field.length - 2; row < Field.length; row++){
        for(int col = Field.length - 2; col < Field.length; col++){
          NField[row][col] += 1;
        }
      }
    }
  }

  // A method that generates the bombs
  public void generateBombs(int size) {
    // Fill the bomb field with empty strings for interation
    for(int row = 0; row < size; row++){
      for(int col = 0; col < size; col++){
        BField[row][col] = "";
      }
    }
    
    // Places 8 red flags randomly on the board
    for(int x = 0; x < 8; x++){
      int row = (int) (Math.random() * size);
      int col = (int) (Math.random() * size);
      
      if(BField[row][col].equals(flag)){
        x--;
      }
      
      BField[row][col] = flag;
    }
  }

  // Changes the board with every input from the player
  public void setDisplay(int row, int col){
    if(BField[row][col].length() > 0){
      showF[row][col] = BField[row][col];
    } else if (NField[row][col] > 0) {
      showF[row][col] = (String.valueOf(NField[row][col])+ " ");
    } else {
      showF[row][col] = Bsquare;
    }
  }

  // Shows/prints the current board
  public void display(){
    for(int r = 0; r < Field.length; r++){
      for(int c = 0; c < Field[0].length; c++){
        System.out.print(showF[r][c]);
      }
      System.out.println(" ");
    }
  }

  // A method that takes in a users input and returns there input on the board
  public boolean playerGuess(int row, int col, boolean guess){
    // Used to count the limit if 64 actions
    int counter = 0;
    // If the player guess theres a bomb and there is, then the game continues
    if((BField[row][col].equals(flag) == true) && (guess == true)){
      System.out.println("There was a bomb there!");
      Bcount++;
      // Decides whether the player has won/lost
      if((Bcount + Mcount) == 8 && (Mcount == 0)){
        win = true;
        return true;
      } else if((Bcount + Mcount) == 8 && (Mcount > 0)){
        win = false;
        return true;
      }
      //continues the game
      return false;
    } 
    // else if the player guesses there isnt a bomb and there is, then the game ends
    else if((BField[row][col].equals(flag) == true) && (guess == false)){
      System.out.println("There was a bomb there.");
      // The player has lost and the final message to the player losing is decided with win and explode
      win = false;
      explode = true;
      return true;
    } 
    // else if the player guesses there is a bomb and there isnt, then then a number of bombs in the area is printed or a brown square/dirt is printed
    else if((BField[row][col].equals(flag) == false) && (guess == true)){
      System.out.println("There wasnt a bomb there.");
      // Wastes a guess of the player chooses wrong
      counter++;
      Mcount++;
      return false;
    }
    // else if there player guesses there is a bomb and there isnt, then then a number of bombs in the area is printed or a brown square/dirt is printed
    else if((BField[row][col].equals(flag) == false) && (guess == false)){
      counter++;
      System.out.println("There wasnt a bomb there.");
      return false;
    }
    if(counter == 64){
      return true;
    }
    // If everything goes through then the game ends
    return true;
  }

  // Returns the players results
  public void resultG(){
    // If the player wins
    if(win == true){
      System.out.println("You have won the game!");
      // The only way to win
      if(Bcount == 8){
        System.out.println("You found all the bombs!");
      }
    }
    // If the player loses
    if(win == false){
      System.out.println("You have lost the game!");
      // If the player guesses too many times wrong then they lose
      if(Bcount + Mcount == 8 && Mcount > 0){
        System.out.println("You guessed to many times");
      }
      // If the player explodes a bomb then they lose
      if(explode){
        System.out.println("You exploded!");
      }
    }
  }
  
}