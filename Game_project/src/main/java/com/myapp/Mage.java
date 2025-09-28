public class Mage extends Character {
    public Mage(String name) {
        super(name, 80, 35);
    }

    @Override
    public void attack(Character target) {
        System.out.println(name + " tung phép vào " + target.getName() + "!");
        target.takeDamage(attackPower);
    }
}