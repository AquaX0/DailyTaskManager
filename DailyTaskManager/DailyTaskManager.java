import java.util.Scanner; //the library to use scanner
import java.util.Stack; //the library to use the stack functions
import java.io.IOException; //the library to clear console
import java.util.LinkedList; //the library to use the linkedList functions

public class DailyTaskManager {
    //the main function
    public static void main(String[] args) {
        //initializing the array, stack, linkedList, and scanner
        String[] tasks = {"Do Homework", "Clean Room", "Feed Cats", "Attend Lecture", "Exercise"};
        Stack<String> completedTasks = new Stack<>();
        LinkedList<String> addedTasks = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        //a loop so the user can still do something in the program after they're done with one of the options they chose
        // or if they inputted something other than the options available
        while (true) {
            //all the options printed and the input for the user
            System.out.println("1. Display all tasks");
            System.out.println("2. Change a task");
            System.out.println("3. Show total completed tasks");
            System.out.println("4. Update task status");
            System.out.println("5. Add Task");
            System.out.println("6. Remove a task from the added tasks");
            System.out.println("7. Exit");
            System.out.print("Choose an option (1-7): ");
            String ans = scanner.nextLine();
            //initializing an integer named n
            int n;

            //a switch case based on the user's input
            switch (ans) {
                //if the user inputs 1 it will display all the tasks, unfinished and completed
                case "1":
                    displayTasks(tasks, completedTasks, addedTasks);
                    //clearing the console
                    ClearConsole();
                    break;

                    //if the user inputs 2, the user can change the tasks that they have into anything they want
                case "2":
                    //checks to see if the unfinished tasks are empty or not
                    if (tasks.length == 0 && addedTasks.isEmpty()) {
                        System.out.println("No tasks to change.");
                        break;
                    }
                    //displays all the unfinished tasks from the array and linkedList
                    displayUnfinishedTasks(tasks, addedTasks);

                    //a loop to make sure the user inputs the correct answer for the options
                    while (true) {
                        //asking the user input what task they want to change
                        System.out.print("Choose a task to change (1-" + (tasks.length + addedTasks.size()) + "): ");
                        String input = scanner.nextLine().trim();

                        //checks to see if the user input a string
                        if (!input.matches("\\d+")) {
                            System.out.println("Invalid input. Please enter a number.");
                            continue; // Restart loop
                        }

                        //changes the variable of the input into an integer
                        n = Integer.parseInt(input);

                        // Check if the number is within a valid range
                        if (n < 1 || n > (tasks.length + addedTasks.size())) {
                            System.out.println("Invalid task number. Try again.");
                            //to restart the loop
                            continue;
                        }

                        // if the input is valid, it will continue with the option's purpose to update the task
                        System.out.print("Enter new task: ");
                        String task = scanner.nextLine();

                        //based on the user inputs it will change the task and input it into the everyday tasks or the addedTasks
                        if (n <= tasks.length) {
                            tasks[n - 1] = task;
                        } else if (n <= addedTasks.size()){
                            addedTasks.set(n - tasks.length - 1, task);
                        } else {
                            System.out.println("Invalid input! Please try again");
                        }

                        System.out.println("Task updated successfully!");
                        //to exit the loop after a successful update
                        break;
                    }
                    //clearing the console
                    ClearConsole();
                    break;

                    //if the user inputs a 3 then it will show them how many tasks they have completed, they will get a different answer
                //based on how many completed task they have
                case "3":
                    if (tasks.length == 0 && addedTasks.isEmpty()) {
                        System.out.println("You have completed all the tasks");
                        break;
                    } else {
                        System.out.println("Total tasks done: " + completedTasks.size() + "/" + (tasks.length + addedTasks.size()));
                    }
                    //to clear the console
                    ClearConsole();
                    break;

                    //if the user inputs a 4, then they can change the status of tasks in every list
                case "4":
                    //checks to see if there are still any unfinished tasks
                    if (tasks.length == 0 && addedTasks.isEmpty() && completedTasks.isEmpty()) {
                        System.out.println("No tasks available.");
                        break;
                    }

                    //displaying the unfinished tasks and the last completed task
                    displayUnfinishedTasks(tasks, addedTasks);
                    String top = completedTasks.lastElement();
                    System.out.println("Newest completed task: "  + top);
                    //a variable for the loop to make sure the user's input is valid
                    boolean validInput = false;

                    //a loop to make sure the user's input is valid
                    while (!validInput) {
                        //asking the user what status do they want to change
                        System.out.print("Choose a status (Done/Undo(only the last completed task will be undone): ");
                        String status = scanner.nextLine().trim().toLowerCase();

                        //if the input is done, then the user can input which task they want to move ot the completedTask list
                        if (status.equals("done")) {
                            //checks if there are unfinished tasks
                            if (tasks.length == 0 && addedTasks.isEmpty()) {
                                System.out.println("No unfinished tasks available.");
                                break;
                            }

                            //displays the unfinished tasks
                            displayUnfinishedTasks(tasks, addedTasks);
                            //a loop to make sere the user's input is valid
                            while (true) {
                                //asking the user to choose the task they want to change the status
                                System.out.print("Choose a task to update (1-" + (tasks.length + addedTasks.size()) + "): ");
                                String input = scanner.nextLine().trim();

                                //checks to see if the input is a string or not
                                if (!input.matches("\\d+")) {
                                    System.out.println("Invalid input. Please enter a number.");
                                    //to restart the loop
                                    continue;
                                }

                                //converts the input variable to integer
                                n = Integer.parseInt(input);

                                //based on the user's input, the finished task will be moved to the completed tasks list
                                //and the finished task will be removed from the list they are from
                                if (n >= 1 && n <= tasks.length) {
                                    completedTasks.push(tasks[n - 1]);
                                    tasks = removeFromArray(tasks, n - 1);
                                    System.out.println("Task marked as done!");
                                    validInput = true;
                                    break;
                                } else if (n > tasks.length && n <= (tasks.length + addedTasks.size())) {
                                    completedTasks.push(addedTasks.remove(n - tasks.length - 1));
                                    System.out.println("Task marked as done!");
                                    validInput = true;
                                    break;
                                } else {
                                    System.out.println("Invalid task number. Try again.");
                                }
                            }
                            //if the input is undo then the user will move the finished task to one of the unfinished task list
                        } else if (status.equals("undo")) {
                            if (completedTasks.isEmpty()) {
                                System.out.println("No completed tasks available.");
                                break;
                            }

                            //remove the last completed task from the completed task list
                            String lastCompleted = completedTasks.pop();
                            //a loop to make sure the user's input is valid
                            while (true) {
                                //asking the user for an input
                                System.out.print("Add back to (1) Everyday Tasks or (2) Added Tasks? ");
                                String index = scanner.nextLine().trim();
                                //checks to see if the input is a string or not
                                if (!index.matches("\\d+")) {
                                    System.out.println("Invalid input. Please enter a number.");
                                    continue;
                                }

                                //changed the variable to an integer
                                int choice = Integer.parseInt(index);

                                //based on the user's input it will be added back to the everyday task list or the addedTask list
                                if (choice == 1) {
                                    tasks = addToArray(tasks, lastCompleted);
                                    break;
                                } else if (choice == 2) {
                                    addedTasks.add(lastCompleted);
                                    break;
                                } else {
                                    System.out.println("Invalid choice, task discarded.");
                                }
                                System.out.println("Task moved back to pending.");
                            }
                        } else {
                            System.out.println("Invalid status! Please enter Done or Undo.");
                        }
                    }
                    //to clear the console
                    ClearConsole();
                    break;

                    //if the user inputs 5 then they can a new task into the addedTask list
                case "5":
                    System.out.print("Add a new task: ");
                    String newTask = scanner.nextLine();
                    //to add the new task into the addedTask list
                    addedTasks.add(newTask);
                    System.out.println("Task added successfully!");
                    break;

                    //if the user inputs 6 then they can remove a task they want to remove from the addedTask list
                case "6":
                    //checks to see if the addedTask list is empty or not
                    if (addedTasks.isEmpty()) {
                        System.out.println("No added tasks available.");
                        return;
                    }

                    //displays the added task list
                    displayUnfinishedTasks(new String[0], addedTasks);

                    //a loop to make sure the user's input is valid
                    while (true) {
                        //asking the user which task they want to remove from the addedTask list
                        System.out.print("Choose a task to remove (1-" + addedTasks.size() + "): ");
                        String input = scanner.nextLine().trim();

                        //checks to see if the input is a string or not
                        if (!input.matches("\\d+")) {
                            System.out.println("Invalid input. Please enter a number.");
                            continue;
                        }

                        //changed the variable to an integer
                        int removeIndex = Integer.parseInt(input);

                        //checks to see if the index is a valid number, if it is it will continue the option's function to remove the task
                        //from the addedTask list
                        if (removeIndex < 1 || removeIndex > addedTasks.size()) {
                            System.out.println("Invalid task number. Try again.");
                            continue;
                        }

                        addedTasks.remove(removeIndex - 1);
                        System.out.println("Task removed successfully!");
                        break;
                    }

                    //if the user inputs 7, it will close the program
                case "7":
                    scanner.close();
                    return;

                    //if the user inputs an invalid input then it will print this and restart the loop
                default:
                    System.out.println("Invalid input. Please try again.");
                    ClearConsole();
                    break;
            }
        }
    }

    //the method to display all the lists
    public static void displayTasks(String[] tasks, Stack<String> completedTasks, LinkedList<String> addedTasks) {
        displayUnfinishedTasks(tasks, addedTasks);
        displayCompletedTasks(completedTasks);
    }

    //the method to display all the unfinished tasks
    public static void displayUnfinishedTasks(String[] tasks, LinkedList<String> addedTasks) {
        System.out.println("\nUnfinished Tasks:");

        //checks if the everyday tasks list and the addedTask list are not empty
        //if not it will then print the tasks from the lists that are not empty
        if (tasks.length == 0) {
            System.out.println("No unfinished tasks.");
        } else {
            System.out.println("Everyday Tasks:");
            for (int i = 0; i < tasks.length; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
            if (!addedTasks.isEmpty()) {
                System.out.println("Added Tasks");
                for (int i = 0; i < addedTasks.size(); i++) {
                    System.out.println((i + tasks.length + 1) + ". " + addedTasks.get(i));
                }
            }
        }
    }

    //the method to display the completed tasks
    public static void displayCompletedTasks(Stack<String> completedTasks) {
        System.out.println("\nCompleted Tasks:");
        if (completedTasks.isEmpty()) {
            System.out.println("No completed tasks.");
        } else {
            int count = 1;
            for (String completed : completedTasks) {
                System.out.println(count + ". " + completed);
                count++;
            }
        }
    }

    //the method to add the finished task back to the everyday tasks list by making a new array then adding the task back
    public static String[] addToArray(String[] array, String newElement) {
        String[] newArray = new String[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = newElement;
        return newArray;
    }

    //the method to remove the finished task from the everyday tasks list by making a new array with less length
    public static String[] removeFromArray(String[] array, int index) {
        if (array.length == 0 || index < 0 || index >= array.length) {
            return array;
        }
        String[] newArray = new String[array.length - 1];
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (i != index) {
                newArray[j++] = array[i];
            }
        }
        return newArray;
    }

    //the method to clear the console
    public static void ClearConsole() {
        System.out.println("\nPress Enter to continue");
        new Scanner(System.in).nextLine();
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Could not clear console.");
        }
    }
}
