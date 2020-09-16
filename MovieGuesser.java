import java.util.Scanner;
import java.io.File;

public class MovieGuesser
{

    public static void main(String [] args) throws Exception
    {
        try {
            File file = new File("movies.txt");
            Scanner scanner = new Scanner(file);
        } catch (FileNotFoundException e){
            System.out.println("File missing!");
            System.exit(1);
        }           
        
        
        String[] movies = new String[25];
        int i=0;

        while (scanner.hasNextLine()) {
            movies[i] = (scanner.nextLine());
            i++;
        }
        
        int randNum = (int) (Math.random() * 25);
        if (randNum == 25)
            randNum--;
        String chosenMovie = movies[randNum];
        
        System.out.println("Type a letter to guess the movie. 10 wrong letters means GAME OVER");
        
        String hidden = "";
        for (int l = 0; l < chosenMovie.length(); l++) {
            hidden += "_";
        }
        System.out.println(hidden);
        
        Scanner letterScanner = new Scanner(System.in);
        String letter;
        int index;
        int guess = 0;
        String check;
        while (guess < 10) {
            
            letter = letterScanner.next();
            if (chosenMovie.contains(letter)) {
                System.out.println("correct");
                index = chosenMovie.indexOf(letter);
                while (index>=0) {
                    hidden = hidden.substring(0, index) + letter + hidden.substring(index + 1);
                    index = chosenMovie.indexOf(letter, index + 1);
                }                    
                System.out.println(hidden);
                check = hidden.replace("_"," ");
                if (check.contains(chosenMovie)) {
                    System.out.println("You did it! Well done!");
                    guess = 10;
                }
            } else {
                guess++;
                System.out.println("wrong letter, this is try " + guess + " out of 10");
            }
        }   
        
        System.out.println("this movie was chosen: " + chosenMovie);
    }
}
