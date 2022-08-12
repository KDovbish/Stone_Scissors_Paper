package hillel.homeworks.game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //  Создаем объект игры и включаем логирование
        GameWithLogging game = new GameWithLogging();
        game.openLogging();

        //  Будущее количество игр, которые закажет второй игрок
        int gamesNumber;

        Player player1 = new Player();
        Player player2 = new Player();

        //  Первый игрок - компьютер
        player1.setName("COMPUTER");

        //  Второй игрок - человек
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        player2.setName(scanner.next());

        //  Сколько игр будем играть?
        for(;;) {
            System.out.print("Enter number of games: ");
            if (scanner.hasNextInt()) {
                gamesNumber = scanner.nextInt();
                break;
            } else {
                scanner.next();
                System.out.println("Invalid enter! Repeat, please...");
            }
        }

        //  Логирование предварительных данных перед игрой
        System.out.println();
        game.printlnLogging("Player1: " + player1.getName());
        game.printlnLogging("Player2: " + player2.getName());
        game.printlnLogging("Estimated number of games: " + gamesNumber);

        //  Процесс игры
        int choicePlayer1, choicePlayer2;
        String printStr;
        for (int i = 1; i <= gamesNumber; i++) {
            choicePlayer1 = Game.generateRandomResult();    //  случайный выбор компьютера
            choicePlayer2 = Game.askPlayer();               //  выбор человека
            if (choicePlayer2 != Game.BREAK) {

                printStr = player1.getName() + ": " + Game.translateChoice(choicePlayer1) + "  " +
                               player2.getName() + ": " + Game.translateChoice(choicePlayer2);

                player1.setGamesPlayed(player1.getGamesPlayed() + 1);
                player2.setGamesPlayed(player2.getGamesPlayed() + 1);

                switch (Game.getGameResult(choicePlayer1, choicePlayer2)) {
                    case Game.DRAW :
                        game.printlnLogging(printStr + "  DRAW");
                        player1.setDraw(player1.getDraw() + 1);
                        player2.setDraw(player2.getDraw() + 1);
                        break;
                    case Game.PLAYER1:
                        game.printlnLogging(printStr + "  " + player1.getName() + " is win");
                        player1.setWin(player1.getWin() + 1);
                        player2.setLoss(player2.getLoss() + 1);
                        break;
                    case Game.PLAYER2:
                        game.printlnLogging(printStr + "  " + player2.getName() + " is win");
                        player1.setLoss(player1.getLoss() + 1);
                        player2.setWin(player2.getWin() + 1);
                        break;
                }
            } else {
                break;
            }
        }

        //  Вывод сводных результатов игры
        System.out.println();
        game.printlnLogging(player1.toString());
        game.printlnLogging(player2.toString());

        game.closeLogging();
    }
}
