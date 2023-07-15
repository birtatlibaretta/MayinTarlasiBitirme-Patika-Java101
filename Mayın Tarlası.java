import java.util.Scanner;

public class MineSweeper {

    private static final int ROWS = 10;
    private static final int COLUMNS = 10;
    private static final int MINES = 10;

    private static int[][] board = new int[ROWS][COLUMNS];
    private static boolean[][] revealed = new boolean[ROWS][COLUMNS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Tahtayı ayarla
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = -1;
                revealed[i][j] = false;
            }
        }

        // Mayınları yerleştir
        for (int i = 0; i < MINES; i++) {
            int row = (int) (Math.random() * ROWS);
            int column = (int) (Math.random() * COLUMNS);

            while (board[row][column] == 1) {
                row = (int) (Math.random() * ROWS);
                column = (int) (Math.random() * COLUMNS);
            }

            board[row][column] = 1;
        }

        // Oyunu başlat
        System.out.println("Mayın Tarlası'na hoş geldiniz!");

        int row = 0;
        int column = 0;

        while (true) {
            // Kullanıcının girişini al
            System.out.print("Görüntülemek istediğiniz karenin satır ve sütununu girin (0-9): ");
            row = scanner.nextInt();
            column = scanner.nextInt();

            // Kareli görüntülenip görüntülenmediğini kontrol et
            if (revealed[row][column]) {
                System.out.println("Bu kare zaten görüntülenmiştir.");
                continue;
            }

            // Kareyi görüntüle
            revealed[row][column] = true;

            // Kareli mayın içerip içermediğini kontrol et
            if (board[row][column] == 1) {
                System.out.println("Oyun bitti!");
                break;
            } else {
                // Çevredeki kareleri mayın için kontrol et
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (i != 0 || j != 0) {
                            int adjacentRow = row + i;
                            int adjacentColumn = column + j;

                            if (adjacentRow >= 0 && adjacentRow < ROWS && adjacentColumn >= 0 && adjacentColumn < COLUMNS) {
                                if (revealed[adjacentRow][adjacentColumn] && board[adjacentRow][adjacentColumn] == 1) {
                                    board[row][column]++;
                                }
                            }
                        }
                    }
                }

                // Tahtayı yazdır
                for (int i = 0; i < ROWS; i++) {
                    for (int j = 0; j < COLUMNS; j++) {
                        if (revealed[i][j]) {
                            System.out.print(board[i][j]);
                        } else {
                            System.out.print(" ");
                        }
                    }
                    System.out.println();
                }

                // Oyunun bitip bitmediğini kontrol et
                if (allSquaresRevealed()) {
                    System.out.println("Kazandınız!");
                    break;
                }
            }
        }
    }

    private static boolean allSquaresRevealed() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (!revealed[i][j] && board[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
