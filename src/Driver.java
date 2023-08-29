import java.util.*;

public class Driver {
    public static void main(String[] args) {
        OrderList restaurantOrders = new OrderList();
        int mainMenuChoice;
        do {
            System.out.println(mainMenu);
            mainMenuChoice = getMenuChoice(fromOne, toFive, exitValue, "Choice: ");
            switch (mainMenuChoice) {
                case new_order -> {
                    String[] orderItems = new String[3];
                    getItemsInput(orderItems);
                    Order newOrder = new Order(orderItems);
                    restaurantOrders.addOrder(newOrder);
                    System.out.println("New order has been added.");
                }
                case remove_order -> {
                    int IDChoice = getIntAboveZero("Enter order ID [type \"cancel\" to return]: ");
                    if (IDChoice == userCancelled) break;
                    restaurantOrders.removeOrder(IDChoice);
                    System.out.println("The order has been removed");
                }
                case ready_order -> {
                    int IDChoice = getIntAboveZero("Enter order ID [type \"cancel\" to return]: ");
                    if (IDChoice == userCancelled) break;
                    restaurantOrders.readyOrder(IDChoice);
                    System.out.println("The order has been set to \"Ready\".");
                }
                case print_order -> {
                    int IDChoice = getIntAboveZero("Enter order ID [type \"cancel\" to return]: ");
                    if (IDChoice == userCancelled) break;
                    if (restaurantOrders.printOrder(IDChoice).isEmpty()) {
                        System.out.println("No order with such ID.");
                    } else System.out.println(restaurantOrders.printOrder(IDChoice));
                }
                case print_all_orders ->
                    System.out.println(restaurantOrders.printOrders());
                case exitValue ->
                    System.out.println("Shutting down...");
                default -> {}
            }
        } while (mainMenuChoice != 6);
    }

    /**
     * Used to fill the provided array with menu items in the form of a String which is taken from the user via the Scanner on System.in.
     * Prints out a prompt asking the user to "Enter Item 1", then "Enter Item 2", and so on until the end of the array.
     *
     * @param itemsArray The provided string array which will be filled with user input one at a time.
     */
    public static void getItemsInput(String[] itemsArray) {
        for (int i = 0; i < itemsArray.length; i++) {
            System.out.print("Enter item " + (i + 1) + ": ");
            itemsArray[i] = scanner.nextLine();
        }
    }

    /**
     * Get an integer input from the user using the Scanner on System.in.
     * Intended for use with simple numbered menus in coding assignments with an option to exit.
     * Prompts the user until a valid input is given.
     *
     * @param lowValue     Lowest numbered menu option.
     * @param highValue    Highest numbered menu option.
     * @param exitValue    Number option to exit the menu.
     * @param choicePrompt Prompt to display for user input.
     * @return Selected menu option or exit value.
     */
    public static int getMenuChoice(int lowValue, int highValue, int exitValue, String choicePrompt) {
        boolean isInputValid;
        int userInput = exitValue;

        do {
            System.out.print(choicePrompt);
            isInputValid = true;

            try {
                userInput = scanner.nextInt();
                scanner.nextLine();

                if (!isValidInput(userInput, lowValue, highValue, exitValue)) {
                    isInputValid = false;
                }
            } catch (InputMismatchException e) {
                isInputValid = false;
                scanner.nextLine();
            }

            if (!isInputValid) {
                System.out.println("Invalid input. Enter a choice between " + lowValue + " and " + highValue + ", or " + exitValue + " to exit.");
            }
        } while (!isInputValid);

        return userInput;
    }

    /**
     * Used for getting any non-zero positive integer from the user via the Scanner on System.in.
     * User is continually prompted until a valid input is given, with the option typing "cancel" to return -1.
     *
     * @param choicePrompt Prompt to display for user input.
     * @return Either a non-zero positive int or -1.
     */
    public static int getIntAboveZero(String choicePrompt) {
        int value;

        while (true) {
            System.out.print(choicePrompt);
            String input = scanner.nextLine();

            try {
                value = Integer.parseInt(input);
                if (value <= 0) {
                    System.out.println("Please enter a positive non-zero value or \"cancel\" to return.");
                    continue;
                }
                break;
            } catch (InputMismatchException err) {
                if ("cancel".equalsIgnoreCase(input)) {
                    return -1;
                }
                System.out.println("Invalid input. Please enter a number or \"cancel\" to return.");
            }
        }

        return value;
    }

    private static boolean isValidInput(int input, int lowValue, int highValue, int exitValue) {
        return (input >= lowValue && input <= highValue) || input == exitValue;
    }

    static Scanner scanner = new Scanner(System.in);
    static final int new_order = 1;
    static final int remove_order = 2;
    static final int ready_order = 3;
    static final int print_order = 4;
    static final int print_all_orders = 5;
    static final int userCancelled = -1;
    static final int fromOne = 1;
    static final int toFive = 5;
    static final int exitValue = 6;
    static String mainMenu = """
                            
            Please select a menu option:
            1-	Create order
            2-	Delete order
            3-	Ready order
            4-	Print order
            5-	Print all orders
            6-	Exit""";
}
