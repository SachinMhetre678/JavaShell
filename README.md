# JavaShell

Welcome to **JavaShell**, a simple shell implementation in Java! This project aims to replicate basic functionalities of a Unix-like shell, including built-in commands like `cd`, `pwd`, `echo`, and `exit`. The shell supports handling absolute paths, relative paths, and the `~` character for the home directory.

## Features

- **Basic Shell Commands**:
  - `pwd`: Print the current working directory.
  - `echo`: Print text to the terminal.
  - `type`: Check if a command is a shell builtin.
  - `exit`: Exit the shell with a specified exit code.
  
- **Directory Navigation**:
  - `cd /absolute/path`: Change to an absolute path.
  - `cd ../`: Move to the parent directory.
  - `cd ./relative/path`: Navigate using a relative path.
  - `cd ~`: Navigate to the user's home directory.

## Getting Started

### Prerequisites

To run JavaShell, you'll need:

- **Java Development Kit (JDK) 8 or later**

### Installation

1. **Clone the repository**:

   ```sh
   git clone https://github.com/YourUsername/JavaShell.git
   cd JavaShell
   ```

2. **Compile the program**:

   ```sh
   javac Main.java
   ```

3. **Run the shell**:

   ```sh
   java Main
   ```

### Usage

Once the shell is running, you can use it like any basic command-line interface:

```sh
$ pwd
/home/user

$ echo "Welcome to JavaShell!"
Welcome to JavaShell!

$ cd /usr/local
$ pwd
/usr/local

$ type pwd
pwd is a shell builtin

$ exit 0
```

## Contributing

Contributions are welcome! Feel free to fork the repository, create a new branch, and submit a pull request with your improvements.

1. **Fork the project**.
2. **Create your feature branch** (`git checkout -b feature/AmazingFeature`).
3. **Commit your changes** (`git commit -m 'Add some AmazingFeature'`).
4. **Push to the branch** (`git push origin feature/AmazingFeature`).
5. **Open a pull request**.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- Thanks to CodeSmith for the project idea.
- Inspired by Unix/Linux shell functionalities.

---
