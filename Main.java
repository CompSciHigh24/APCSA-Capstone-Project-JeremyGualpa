import java.util.Scanner;
class Main {
  private static boolean win = false;
  
  public static void main(String[] args) {
    Scanner myObj = new Scanner(System.in);
    FieldGrid grid = new FieldGrid(8);

    
    // Refreshes the console (for style)
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
    
    // Introduction for the player
    System.out.println("\n\nWelcome to Minesweeper");
    System.out.println("Find all 8 bombs on the board to win");
    System.out.println("Explode one bomb and you lose!");
    System.out.println("Waste all 8 guesses then you lose!");
    System.out.println(" ");

    // Continues the game until the player has won/lost
    while(!win){
      // Displays the grid
      grid.display();
      // Rules
      System.out.println(" ");
      System.out.println("For both row and column guess between 1-8");
      // Guessing row
      System.out.println("What row would you like to guess at?");
      int row = myObj.nextInt() - 1;
      // Guessing column
      System.out.println("What column would you like to guess at?");
      int col = myObj.nextInt() - 1;
      // Guessing if theres a bomb
      System.out.println("Is there a bomb there? true/false *In lowercase*");
      boolean guess = myObj.nextBoolean();
      grid.setDisplay(row, col);

      // Refreshes the console
      System.out.print("\033[H\033[2J");  
      System.out.flush();  

      // Ends the game when the player has lost or won
      if (win != grid.playerGuess(row, col, guess)){
        win = true;
      }

      // Refreshes the console
      System.out.print("\033[H\033[2J");  
      System.out.flush();  
    }
    // Returns the results (win/lose)
    grid.display();
    grid.resultG();
  }
}