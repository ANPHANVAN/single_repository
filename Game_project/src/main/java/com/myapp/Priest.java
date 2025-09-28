



public class Priest extends Character implements Healable {
    public Priest(String name) {
        super(name, 100, 10);
    }

    @Override
    public void attack(Character target) {
        System.out.println(name + " gõ nhẹ vào " + target.getName() + " 😅");
        target.takeDamage(attackPower);
    }

    @Override
    public void heal(Character target) {
        int amount = 20;
        System.out.println(name + " hồi máu cho " + target.getName() + " +" + amount + " HP");
        target.hp += amount;
    }
}