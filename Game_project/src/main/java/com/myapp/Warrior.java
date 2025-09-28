public class Warrior extends Character {
    public Warrior(String name) {
        super(name, 150, 25);
    }

    @Override
    public void attack(Character target) {
        System.out.println(name + " chém " + target.getName() + "!");
        target.takeDamage(attackPower);
    }
}