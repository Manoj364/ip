import duke.Deadline;
import duke.Event;
import duke.Todo;

import java.util.Scanner;
import java.util.Vector;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("    Hello from\n" + logo);
        System.out.println("    _________________________________________");

        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    _________________________________________");
        System.out.println("     ");

        //Read in input from user
        String inputString;
        Scanner in;

        //Set up list to store user inputs
        Vector<Todo> tasks = new Vector<Todo>();
        int counter = 0;

        //setup of exit flag
        boolean exit = false;

        while (!exit) {
            in = new Scanner(System.in);
            inputString = in.nextLine();

            //switch cases for all specified input types
            switch (inputString) {
            case "bye":
                exit = true;
                break;

            case "list":
                System.out.println("    _________________________________________");
                printListContents(tasks, counter);
                break;

            case "mark":
                System.out.println("    Please specify task number: ");
                int taskNumber = getTaskNumber();
                tasks.get(taskNumber - 1).setDone(true);
                printMarkedAcknowledgement(tasks, taskNumber);
                break;

            case "unmark":
                System.out.println("    Please specify task number: ");
                taskNumber = getTaskNumber();
                tasks.get(taskNumber - 1).setDone(false);
                printUnmarkedAcknowledgement(tasks, taskNumber);
                break;

            case "todo":
                System.out.println("    _________________________________________");
                System.out.println("    Please specify task: ");
                inputString = getInputString();
                if (inputString.equals("")) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    break;
                } else {
                    tasks.add(new Todo(inputString));
                    tasks.get(counter).print();
                    counter++;
                    System.out.println("    Now you have " + counter + " tasks in your list!");
                    break;
                }

            case "deadline":
                System.out.println("    _________________________________________");
                System.out.println("    Please specify task: ");
                inputString = getInputString();
                inputString += '\0';
                int deadlinePosition = inputString.indexOf("/");
                int endPosition = inputString.indexOf("\0");
                String taskName = inputString.substring(0, deadlinePosition);
                String deadline = inputString.substring(deadlinePosition + 1, endPosition);
                tasks.add(new Deadline(taskName, deadline));
                tasks.get(counter).print();
                counter++;
                break;

            case "event":
                System.out.println("    _________________________________________");
                System.out.println("    Please specify task: ");
                inputString = getInputString();
                inputString += '\0';
                int deadlineStartPosition = inputString.indexOf("/");
                int deadlineEndPosition = inputString.indexOf("|");
                endPosition = inputString.indexOf("\0");
                taskName = inputString.substring(0, deadlineStartPosition);
                String deadlineStart = inputString.substring(deadlineStartPosition + 1, deadlineEndPosition);
                String deadlineEnd = inputString.substring(deadlineEndPosition + 1, endPosition);
                tasks.add(new Event(taskName, deadlineStart, deadlineEnd));
                tasks.get(counter).print();
                counter++;
                break;

            case "delete":
                System.out.println("    Please specify task number: ");
                taskNumber = getTaskNumber() - 1;
                System.out.println("    _________________________________________");
                tasks.get(taskNumber).printInList();
                System.out.println("    _________________________________________");
                System.out.println("    ");
                tasks.removeElement(tasks.get(taskNumber));
                counter--;
                break;

            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
    }

        //When user types "bye"
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _________________________________________");
        System.out.println("     ");
    }

    private static String getInputString() {
        Scanner in;
        String inputString;
        in = new Scanner(System.in);
        inputString = in.nextLine();
        return inputString;
    }

    private static void printUnmarkedAcknowledgement(Vector<Todo> tasks, int taskNumber) {
        System.out.println("    _________________________________________");
        System.out.println("    " + taskNumber + "." + "[ ] " + tasks.get(taskNumber - 1).getDescription());
        System.out.println("    _________________________________________");
    }

    private static void printMarkedAcknowledgement(Vector<Todo> tasks, int taskNumber) {
        System.out.println("    _________________________________________");
        System.out.println("    " + taskNumber + "." + "[X] " + tasks.get(taskNumber - 1).getDescription());
        System.out.println("    _________________________________________");
    }

    private static int getTaskNumber() {
        String inputString = getInputString();
        return Integer.parseInt(inputString);
    }

    private static void printListContents(Vector<Todo> tasks, int counter) {
        for (int i = 0; i < counter; ++i) {
            if (tasks.get(i).isDone) {
                System.out.print("    " + (i + 1) + ".");
                tasks.get(i).printInList();
            } else {
                System.out.print("    " + (i + 1) + ".");
                tasks.get(i).printInList();
            }
        }
        System.out.println("    _________________________________________");
        System.out.println("     ");
    }
}
