import java.util.Objects;

public abstract class Entitys {
    private int attack;         // "атака" - диапазон: 1 - 30
    private int protection;     // "защита" - диапазон: 1 - 30
    private int power;          // "здоровье/сила" - диапазон: 1 - N (N задаём при создании)
    private int damage;         // "урон" - ущерб, который можно нанести противнику - диапазон: 1 - М (М задаём при создании)
    private final int maxPower;       // максимальная величина здоровья (задана при создании персонажа)

    public Entitys(int attack, int protection, int power, int damage) {
        try {
            checkRange(1, 30, attack);
            this.attack = attack;
        } catch (OutsideRangeException e) {
            throw new IllegalArgumentException("attack = " + attack + e.getMessage());
        }
        try {
            checkRange(1, 30, protection);
            this.protection = protection;
        } catch (OutsideRangeException e) {
            throw new IllegalArgumentException("protection = " + protection + e.getMessage());
        }

        if (power > 0) {
            this.maxPower = power;
            this.power = power;
        } else throw new IllegalArgumentException("*** Здоровье = " + power + " недопустимо! ***");

        if (damage <= 0) {
            System.err.println("*** Максимальный ущерб = " + damage + " - этот добряк никого не победит! ***");
            damage = 1;
        }
        this.damage = damage;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public int getPower() {
        return power;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entitys entitys = (Entitys) o;
        return attack == entitys.attack && protection == entitys.protection && power == entitys.power && damage == entitys.damage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attack, protection, power, damage);
    }

    @Override
    public String toString() {
        return " атака = " + attack +
                ", защита = " + protection +
                ", здоровье =" + power +
                ", максимальный ущерб =" + damage;
    }

    protected static void checkRange(int nMin, int nMax, int inputN) throws OutsideRangeException {
        if (inputN < nMin || inputN > nMax)
            throw new OutsideRangeException(" число за пределами допустимого диапазона ");
    }
}
