import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<Word> words = List.of(
                new Word("large",-3),
                new Word("phone",-6),
                new Word("newspaper",2),
                new Word("chocolate",-10),
                new Word("connection",13),
                new Word("engineering",10)
        );
        System.out.println("WordList = "+words);
//        List<Word> words2 = new ArrayList<>();
 //             String w = words.stream()
              String w = words.stream()
                        .filter(Predicate.not(Word::isBad))
                        .max(Comparator.comparing(Word::getPriority))
                        .map(Word::getValue).get().toString();
        System.out.println("after stream: "+w);

int i1 = 5;
int i2 = 89;
int maxI = Math.max(i1, i2);
        System.out.println(maxI);
/*
Есть 3 таблицы: Водители, машины табл., где указано какой водитель (его id)
может сесть за руль авто (id авто).
У 1 авто м.б. неск водителей, 1 водитель может брать неск.авто
Задача: список фамилий водителей, за которыми закреплено 2 и более автомобилей "Toyota"
-----------------------------------------------
CREATE TABLE car(
id BIGSERIAL NOT NULL PRIMARY KEY,
brand TEXT,
manufacturing_year INT NOT NULL,
mileage INT NOT NULL);
---------------------
CREATE TABLE driver(
id BIGSERIAL NOT NULL PRIMARY KEY,
first_name TEXT NOT NULL,
last_name TEXT NOT NULL,
driving_experience INT NOT NULL,
birth_date DATE NOT NULL);
----------------------
CREATE TABLE car_driver(
id BIGSERIAL NOT NULL PRIMARY KEY,
car_id INT NOT NULL,
driver_id INT NOT NULL,
FOREIGN KEY (car_id) REFERENCES car (id),
FOREIGN KEY (driver_id) REFERENCES driver (id));
========================================
SELECT driver.last_name FROM car_driver
INNER JOIN car ON car_driver.car_id = car.id
INNER JOIN driver ON car_driver.driver_id = driver.id
GROUP BY driver.last_name
HAVING COUNT(car.brand = 'Toyota') >= 2;
 */

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
//    @Getter
    @NoArgsConstructor
    public static class Word {
        private  String value;
        private  int priority;

    public Word(String value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    public boolean isBad() {
            return this.value.contains("la");
        }

    public String getValue() {
        return value;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Word{" +
                "value='" + value + '\'' +
                ", priority=" + priority +
                '}';
    }
}
}