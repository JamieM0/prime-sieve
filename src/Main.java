import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    /* I want this program to support a few different flags for different features.
    * The standard feature for it will be to find and list the prime numbers up to the given number
    * (prioritising printing so that the user doesn't have a blank screen for too long).
    *
    * I want other features, another one could be to find the largest prime number up
    * to the given number. Each feature must be implemented in the most efficient way possible.
    *
    * This could be activated, for example, by running the program with the -f flag. */
    public static void main(String[] args) {
        Menu();
    }

    public static void Menu()
    {
        clearScreen();
        int choice = 0;
        System.out.println("Welcome to the Prime Number Sieve");
        System.out.println("===================================================");
        System.out.println("1: Find all the prime numbers up to a given number.");
        System.out.println("2: Find the next smallest prime number.");
        System.out.println("3: Prime Number Tester.");
        System.out.println("4: Exit program.");
        System.out.println("===================================================");
        while(true)
        {
            try{
                System.out.print("Enter your choice: ");
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                if(choice>4 || choice<1)
                    throw new Exception("Number not in menu.");
                System.out.println("");
                switch(choice)
                {
                    case 1:
                        PrimeFinder();
                        break;
                    case 2:
                        NextSmallestPrimeFinder();
                        break;
                    case 3:
                        PrimeTester();
                    case 4:
                        System.exit(1);
                        break;
                }
            }
            catch(InputMismatchException ex)
            {
                System.out.println(ANSI_RED + "Unable to get a number. Try again." + ANSI_RESET + "\n");
            }
            catch(Exception ex)
            {
                System.out.println(ANSI_RED + "Sorry, there was an error. Try again.\nMore Details: " + ex + ANSI_RESET + "\n");
            }
        }
    }
    public static void PrimeFinder(){
        String input = "";
        int value = 0;
        while(true)
        {
            System.out.print("Enter value to find prime numbers up to, or enter 'menu' to return to the main menu: ");
            try{
                Scanner scanner = new Scanner(System.in);
                input = scanner.next();
                if(input.toLowerCase().equals("menu"))
                    Menu();
                else
                    value = Integer.parseInt(input);

                //Prime Number Finder
                System.out.println("Here are the prime numbers up to " + value + ":\n");
                int i =2;
                ArrayList<Integer> nums = new ArrayList<Integer>();
                while(i<value)
                {
                    //System.out.print("CHECKER 0: " + i + "\n");
                    nums.add(i);
                    i++;
                }
                int currentNumsSize = nums.size();
                //System.out.println("CHECKER 1: " + currentNumsSize);
                for(int j=nums.get(0); j<currentNumsSize; j++)
                {
                    /* For every number, go through the arraylist and remove every other instance of a multiple of that number.
                    say we get 2 on the 1st loop here, we need another loop to go through the numbers in a second iteration to
                    remove all instances of a multiple of 2. */
                    for(int k=1; k<nums.size(); k++)
                    {
                        if ((nums.get(k) != j) && (nums.get(k) % j == 0))
                        {
                            nums.remove(k);
                            currentNumsSize--;
                            k--;
                        }
                    }
                }
                for(int j=0; j<nums.size(); j++)
                {
                    System.out.print(nums.get(j) + " ");
                }
                System.out.println(ANSI_GREEN + "\n\nDONE.\n" + ANSI_RESET);
            }
            catch(InputMismatchException ex)
            {
                System.out.println(ANSI_RED + "Unable to get a number. Try again." + ANSI_RESET + "\n");
            }
            catch(Exception ex)
            {
                System.out.println(ANSI_RED + "Sorry, there was an error. Try again.\nMore Details: " + ex + ANSI_RESET + "\n");
            }
        }
    }

    public static void NextSmallestPrimeFinder(){
        int value = 0;
        int largestPrime = 0;
        while(true)
        {
            System.out.print("Enter value to find the biggest prime number that's less than (or equal to) the value, or enter 'menu' to return to the main menu: ");
            try{
                Scanner scanner = new Scanner(System.in);
                String input = scanner.next();
                if(input.toLowerCase().equals("menu"))
                    Menu();
                else
                    value = Integer.parseInt(input);

                //Prime Number Finder
                if(isPrime(value))
                {
                    largestPrime=value;
                }
                else
                {
                    for(int i=value; i>0; i--)
                    {
                        if(isPrime(i))
                        {
                            largestPrime=i;
                            break;
                        }
                    }
                }

                System.out.println("Largest prime up to this number is: " + largestPrime);

                System.out.println(ANSI_GREEN + "\nDONE.\n" + ANSI_RESET);
            }
            catch(InputMismatchException ex)
            {
                System.out.println(ANSI_RED + "Unable to get a number. Try again." + ANSI_RESET + "\n");
            }
            catch(Exception ex)
            {
                System.out.println(ANSI_RED + "Sorry, there was an error. Try again.\nMore Details: " + ex + ANSI_RESET + "\n");
            }
        }
    }

    public static void PrimeTester(){
        String input = "";
        int value = 0;
        while(true)
        {
            System.out.print("Enter value to test if it's a prime number or not, or enter 'menu' to return to the main menu: ");
            try{
                Scanner scanner = new Scanner(System.in);
                input = scanner.next();
                if(input.toLowerCase().equals("menu"))
                    Menu();
                else
                    value = Integer.parseInt(input);

                //Prime Number Finder
                if(isPrime(value))
                    System.out.println(value + " is a prime number.\n");
                else
                    System.out.println(value + " is not a prime number.\n");
                int i =2;
                ArrayList<Integer> nums = new ArrayList<Integer>();
                while(i<value)
                {
                    //System.out.print("CHECKER 0: " + i + "\n");
                    nums.add(i);
                    i++;
                }
                int currentNumsSize = nums.size();
                //System.out.println("CHECKER 1: " + currentNumsSize);
                for(int j=nums.get(0); j<currentNumsSize; j++)
                {
                    /* For every number, go through the arraylist and remove every other instance of a multiple of that number.
                    say we get 2 on the 1st loop here, we need another loop to go through the numbers in a second iteration to
                    remove all instances of a multiple of 2. */
                    for(int k=1; k<nums.size(); k++)
                    {
                        if ((nums.get(k) != j) && (nums.get(k) % j == 0))
                        {
                            nums.remove(k);
                            currentNumsSize--;
                            k--;
                        }
                    }
                }
                for(int j=0; j<nums.size(); j++)
                {
                    System.out.print(nums.get(j) + " ");
                }
                System.out.println(ANSI_GREEN + "\n\nDONE.\n" + ANSI_RESET);
            }
            catch(InputMismatchException ex)
            {
                System.out.println(ANSI_RED + "Unable to get a number. Try again." + ANSI_RESET + "\n");
            }
            catch(Exception ex)
            {
                System.out.println(ANSI_RED + "Sorry, there was an error. Try again.\nMore Details: " + ex + ANSI_RESET + "\n");
            }
        }
    }

    public static Boolean isPrime(int num)
    {
        if(num%2==0 || num<=1)
            return false;
        for(int i=3; i<num-1; i++)
        {
            if(num%i==0)
                return false;
        }
        return true;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}