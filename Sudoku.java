import java.util.Scanner;

public class Sudoku {
    int[][] board;


    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        sudoku.startGame();
    }

    void startGame() {
        board = new int[][]{//
                {0, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 0}
        };

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("****************");
            System.out.println("*                SUDOKU MENU               *");
            System.out.println("****************");
            System.out.println("SELECIONE:");
            System.out.println("1 - Sudoku");
            System.out.println("2 - Sair");
            int menuOption = sc.nextInt();

            if (menuOption == 2) {
                System.out.println("Voce saiu do jogo!!!");
                System.exit(0);
            } else if (menuOption == 1) {
                break;
            } else {
                System.out.println("Opcao invalida! Escolha [1] ou [2]:");
            }
        }

        printBoard();

        while (hasPositionAvailable()) {
            System.out.println("Informe o indice da linha: ");
            int currentRow = sc.nextInt() - 1;

            System.out.println("Informe o indice da coluna: ");
            int currentCol = sc.nextInt() - 1;

            System.out.println("Informe o numero: ");
            int number = sc.nextInt();

            play(currentRow, currentCol, number);
        }
        System.out.println("Vc ganhou o Jogo!!!!!!!");
        startGame();
    }

    void play(int currentRow, int currentCol, int number) {
        if (board[currentRow][currentCol] != 0) {
            System.out.println("Esta celula ja esta preenchida. Escolha outra celula.");
            printBoard();
            return;
        }
        if (!existsColumn(currentCol, number) && !existsRow(currentRow, number) &&
                !existsQuadrant(currentRow, currentCol, number)) {
            board[currentRow][currentCol] = number;
        } else {
            System.out.println("Numero Invalido.");
        }

        printBoard();
    }

    boolean existsColumn(int col, int number) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][col] == number) {
                return true;
            }
        }

        return false;
    }

    boolean existsRow(int row, int number) {
        for (int col = 0; col < board[row].length; col++) {
            if (board[row][col] == number) {
                return true;
            }
        }
        return false;
    }

    boolean existsQuadrant(int currentRow, int currentCol, int number) {
        boolean found = false;

        if (currentRow <= 2 && currentCol <= 2) {
            found = existsMatrix(0, 2, 0, 2, number);
        } else if (currentRow <= 2 && currentCol <= 5) {
            found = existsMatrix(0, 2, 3, 5, number);
        } else if (currentRow <= 2 && currentCol <= 8) {
            found = existsMatrix(0, 2, 6, 8, number);
        } else if (currentRow <= 5 && currentCol <= 2) {
            found = existsMatrix(3, 5, 0, 2, number);
        } else if (currentRow <= 5 && currentCol <= 5) {
            found = existsMatrix(3, 5, 3, 5, number);
        } else if (currentRow <= 5 && currentCol <= 8) {
            found = existsMatrix(3, 5, 6, 8, number);
        } else if (currentRow <= 8 && currentCol <= 2) {
            found = existsMatrix(6, 8, 0, 2, number);
        } else if (currentRow <= 8 && currentCol <= 5) {
            found = existsMatrix(6, 8, 3, 5, number);
        } else if (currentRow <= 8 && currentCol <= 8) {
            found = existsMatrix(6, 8, 6, 8, number);
        }

        return found;
    }

    boolean existsMatrix(int iniRow, int finalRow, int iniCol, int finalCol, int number) {
        for (int row = iniRow; row <= finalRow; row++) {
            for (int col = iniCol; col < finalCol; col++) {
                if (board[row][col] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    void printBoard() {

        System.out.println("-------------------------");

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (j % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");

                if (j == board[i].length - 1) {
                    System.out.print("|");
                }
            }

            System.out.println();

            if ((i + 1) % 3 == 0) {
                System.out.println("-------------------------");
            }
        }

    }

    boolean hasPositionAvailable() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    return true;
                }
            }
        }

        return false;
    }

}
