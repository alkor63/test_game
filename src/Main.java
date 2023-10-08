public class Main {
    public static void main(String[] args) {
        Gamer hero = new Gamer(30, 20, 100, 15);       // создаём Игрока
        Monster zlodey = new Monster(30, 20, 80, 18);  // создаём Монстра
        System.out.println("Игру начинают Герой и Злодей:");
        System.out.println("Герой: " + hero);
        System.out.println("Злодей: " + zlodey);
        System.out.println("Чья атака    Здоровье Героя     Здоровье Злодея");

        int numCureHero = 0;            // количество исцелений Героя
        int numCureZlodey = 0;          // количество исцелений Злодея
        while (hero.getPower() > 0 && zlodey.getPower() > 0) {  //"Смертельная битва" - пока чьё-то здоровье не обнулится
            // Герой и Злодей атакуют друг друга поочерёдно
            if (GameService.attackResult(hero, zlodey)) {
                if (zlodey.getPower() <= 0) {
                    zlodey.setPower(0);     // защита от отрицательных чисел
                    break;                  // Злодей погиб - игра окончена
                }
                System.out.println("атака Героя         " + hero.getPower() + "          " + zlodey.getPower());
                if (zlodey.getPower() < zlodey.getMaxPower() / 2 && numCureZlodey < 4) {
                    // если здоровье опустилось ниже 1/2 от максимального - лечим (+30%) не более 4 раз
                    numCureZlodey++;
                    GameService.hospital(zlodey);
                    System.out.println("Лечим Злодея " + numCureZlodey + "-й раз. Теперь его здоровье = " + zlodey.getPower());
                }
            }
            if (GameService.attackResult(zlodey, hero)) {
                if (hero.getPower() < 0) hero.setPower(0);     // защита от отрицательных чисел
                System.out.println("атака Злодея        " + hero.getPower() + "          " + zlodey.getPower());
                if (hero.getPower() < hero.getMaxPower() / 2 && numCureHero < 4) {
                    // если здоровье опустилось ниже 1/2 от максимального - лечим (+30%) не более 4 раз
                    numCureHero++;
                    GameService.hospital(hero);
                    System.out.println("Лечим Героя " + numCureHero + "-й раз. Теперь его здоровье = " + hero.getPower());
                }
            }
        }
        String s;
        if (hero.getPower() > zlodey.getPower()) s = "Герой";
        else s = "Злодей";  // определяем имя победителя
        System.out.println("Битва окончена! Победитель - " + s);

    }
}