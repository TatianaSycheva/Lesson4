import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static void start(){
        System.out.println("Enter the preferred size of the playing field n*n ...");
        System.out.print("n = ");
        int n = getTheFieldSize();
        char[][] field = getEmptyField(n);
        drawField(field);
        while (true) {
            doPlayerMove(field,n);
           if (checkWinner(field, 'X')){
               System.out.println("Your are winner :)");
               break;
           }
           if (isDraw(field)){
               System.out.println("This is draw. Try again.");
               break;
           }
            doAIMove(field);
            if (checkWinner(field, '0')){
                System.out.println("Sorry, your are loser :(");
                break;
            }
            if (isDraw(field)){
                System.out.println("This is draw. Try again.");
                break;
            }
        }

    }

    static boolean isDraw(char[][] field){
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (isEmptyCell(field, i, j)){
                    return false;
                }
            }
        }
        return true;
    }

    static boolean checkWinner (char[][] field, char sign) {
       //vertical
       for (int i = 0; i < field.length; i++) {
           for (int j = 0; j < field.length; j++) {
               if (field[j][i] != sign) {
                   break;
               } else {
                   if (j == field.length - 1) {
                       return true;
                   }
               }
           }
       }

       //horisontal
       for (int i = 0; i < field.length; i++) {
           for (int j = 0; j < field.length; j++) {
               if (field[i][j] != sign) {
                   break;
               } else {
                   if (j == field.length - 1) {
                       return true;
                   }
               }
           }
       }

       //diagonals
       for (int i = 0; i < field.length; i++) {
           if (field[i][i] != sign) {
               break;
           } else {
               if (i == field.length - 1) {
                   return true;
               }
           }
       }
       for (int i = 0; i < field.length; i++) {
           if (field[i][field.length - 1 - i] != sign) {
               break;
           } else {
               if (i == field.length - 1) {
                   return true;
               }
           }
       }
       return false;
   }



    static void doAIMove(char[][] field){
        Random random = new Random();
        int vertical, horisontal;
        do {
            vertical = random.nextInt(field.length);
            horisontal = random.nextInt(field.length);
        }while (isNotEmptyCell(field, vertical, horisontal));
        field[vertical][horisontal] = '0';
        drawField(field);
    }


    static boolean isNotEmptyCell(char[][] field, int vertical, int horisontal){
        return !isEmptyCell(field, vertical, horisontal);
    }

    static boolean isEmptyCell(char[][] field, int vertical, int horisontal){
        return field[vertical][horisontal] == '-';
    }



    static void drawField(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();



    }
    static int getCoordinate(char position, int n){
        Scanner scanner = new Scanner(System.in);
        int coordinate;
        do {
            System.out.printf("Pleas enter %s-coordinate (range 1...%s) ...%n", position, n);
            coordinate = scanner.nextInt() - 1;
        }while (coordinate < 0 || coordinate > n -1);
        return coordinate;
    }

    static int getTheFieldSize(){
        Scanner cons = new Scanner(System.in);
        return cons.nextInt();
    }

    static char[][] getEmptyField(int n){
        char[][] field = new char[n][n];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = '-';
            }
        }
        return field;
    }

    static void doPlayerMove(char[][] field, int n){
        int vertical, horisontal;
        do {
            System.out.println("Your chance. Pleas enter coordinates");
            vertical = getCoordinate('V',n);
            horisontal = getCoordinate('H', n);
        } while (isNotEmptyCell(field, vertical, horisontal));
        field[vertical][horisontal] = 'X';
        drawField(field);
    }
}

