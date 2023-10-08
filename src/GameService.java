public class GameService {
    static java.util.Random random = new java.util.Random();

    public static int randomCubeSide() {
        return random.nextInt(6) + 1;
    } // генератор случайных чисел стороны кубика

    public static int randomInRange(int n) {
        return random.nextInt(n) + 1;
    } // генератор случайных чисел в диапазоне от 1 до n

    public static int attackModifier(Entitys attacker, Entitys protector) {
        return (attacker.getAttack() - protector.getProtection() + 1);
    }  // модификатор атаки

    public static boolean attackIsOk(int n) {
    //    Бросаем кубик. Если выпало 5 или 6 ==> атака успешна
        int i = 0;
        do {
            if (randomCubeSide() > 5) return true;
            i++;
        } while (i < n);
        return false;
    }

    public static boolean attackResult(Entitys attacker, Entitys protector) {
        if (attackIsOk(attackModifier(attacker, protector))) {  // если атака успешна
            int demage = randomInRange(attacker.getDamage());   // генерируем случайное число урона противнику
            protector.setPower(protector.getPower() - demage);  // уменьшаем Здоровье защищающегося на величину урона
            return true;
        }
        return false;
    }

    public static void hospital(Entitys iNeedHelp) {
        // Лечим пациента - увеличиваем Здоровье на 30% от максимального
        int healthPlus = (int) (iNeedHelp.getMaxPower() * 0.3);
        int newHealth = healthPlus + iNeedHelp.getPower();
        if (newHealth > iNeedHelp.getMaxPower()) newHealth = iNeedHelp.getMaxPower();
        iNeedHelp.setPower(newHealth);
    }
}
