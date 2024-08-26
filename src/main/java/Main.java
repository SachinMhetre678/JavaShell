import java.nio.file.*;
import java.util.*;

public class Main {
    // The current directory starts as the directory where the program was run
    private static Path currentDirectory = Path.of(System.getProperty("user.dir"));

    public static void main(String[] args) throws Exception {
        // These are the built-in commands our shell can recognize
        Set<String> commands = Set.of("echo", "exit", "type", "pwd", "cd");
        Scanner scanner = new Scanner(System.in); // Reading user input

        while (true) { // The REPL loop: Read-Eval-Print Loop
            System.out.print("$ "); // Display the prompt
            String input = scanner.nextLine(); // Get the user's input

            if (input.equals("exit 0")) { // If the user types "exit 0", exit the shell
                System.exit(0);
            } else if (input.startsWith("echo ")) { // If the input starts with "echo ", print what's after it
                System.out.println(input.substring(5));
            } else if (input.startsWith("type ")) { // If the input starts with "type ", check if it's a built-in command
                String arg = input.substring(5);
                if (commands.contains(arg)) {
                    System.out.printf("%s is a shell builtin%n", arg);
                } else { // If it's not a built-in, check if it's a command available in the system
                    String path = getPath(arg);
                    if (path == null) {
                        System.out.printf("%s: not found%n", arg);
                    } else {
                        System.out.printf("%s is %s%n", arg, path);
                    }
                }
            } else if (input.equals("pwd")) { // If the user types "pwd", print the current directory
                System.out.println(currentDirectory.toString());
            } else if (input.startsWith("cd ")) { // If the input starts with "cd ", change the directory
                String path = input.substring(3).trim(); // Get the path after "cd "
                Path targetDirectory;

                if (path.equals("~")) { // If the path is "~", go to the user's home directory
                    String home = System.getenv("HOME");
                    if (home != null) {
                        targetDirectory = Path.of(home);
                    } else {
                        System.out.println("cd: HOME environment variable not set");
                        continue;
                    }
                } else if (path.startsWith("/")) { // If the path starts with "/", it's an absolute path
                    targetDirectory = Path.of(path);
                } else { // Otherwise, it's a relative path
                    targetDirectory = currentDirectory.resolve(path).normalize(); // Resolve it relative to the current directory
                }

                if (Files.isDirectory(targetDirectory)) { // If the target is a valid directory, switch to it
                    currentDirectory = targetDirectory.toAbsolutePath();
                    System.setProperty("user.dir", currentDirectory.toString()); // Update the current directory
                } else { // If the target is not a directory, show an error
                    System.out.printf("cd: %s: No such file or directory%n", path);
                }
            } else { // If the command isn't recognized, check if it's an external command
                String command = input.split(" ")[0]; // Get the command part (before any spaces)
                String commandPath = getPath(command); // Find the command in the system
                if (commandPath == null) {
                    System.out.printf("%s: command not found%n", command);
                } else { // Run the command if it's found
                    String fullPath = commandPath + input.substring(command.length());
                    Process p = Runtime.getRuntime().exec(fullPath.split(" "));
                    p.getInputStream().transferTo(System.out);
                }
            }
        }
    }

    // This method searches for a command in the system's PATH
    private static String getPath(String command) {
        for (String path : System.getenv("PATH").split(":")) { // Go through each directory in PATH
            Path fullPath = Path.of(path, command); // Build the full path to the command
            if (Files.isRegularFile(fullPath)) { // If the command exists as a file, return its path
                return fullPath.toString();
            }
        }
        return null; // Return null if the command wasn't found
    }
}
