import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gamer hero = new Gamer(30,20,100,15);       // создаём Игрока
        Monster zlodey = new Monster(30,20,80,18);  // создаём Монстра
        System.out.println("Игру начинают Герой и Злодей:");
        System.out.println("Герой: " + hero);
        System.out.println("Злодей: " + zlodey);
        System.out.println("Чья атака    Здоровье Героя     Здоровье Злодея");

        int numCureHero = 0;
        int numCureZlodey = 0;
        while (hero.getPower() > 0 && zlodey.getPower() > 0)
        {
            if (GameService.attackResult(hero,zlodey)){
                if (zlodey.getPower() <= 0) {zlodey.setPower(0); break;}
                System.out.println("атака Героя         "+ hero.getPower()+"          "+zlodey.getPower());
                if(zlodey.getPower() < zlodey.getMaxPower()/2 && numCureZlodey < 4){
                numCureZlodey ++;
                GameService.hospital(zlodey);
                System.out.println("Лечим Злодея "+numCureZlodey+"-й раз. Теперь его здоровье = "+zlodey.getPower());
            }
            }
            if (GameService.attackResult(zlodey, hero)) {
                if (hero.getPower() < 0) hero.setPower(0);
                System.out.println("атака Злодея        " + hero.getPower() + "          " + zlodey.getPower());
                if (hero.getPower() < hero.getMaxPower() / 2 && numCureHero < 4) {
                    numCureHero++;
                    GameService.hospital(hero);
                    System.out.println("Лечим Героя " + numCureHero + "-й раз. Теперь его здоровье = " + hero.getPower());
                }
            }
        }
        String s;
        if (hero.getPower() > zlodey.getPower()) s = "Герой";
                else s = "Злодей";
        System.out.println("Битва окончена! Победитель - " + s);
/*
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask();
                            break;
                        case 2:
                            removeId();
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.nextLine();
                    scanner.nextInt();
                    System.out.println("+++++ Выберите пункт меню из списка (введите ЦИФРУ от 0 до 6) ! +++++");
                }
            }
        }

        scanner.close();

    }

    private static void printMenu() {
        System.out.println("\n1. Добавить задачу\n2. Удалить задачу\n3. \n0. Выход");
    }
    */
    }
}