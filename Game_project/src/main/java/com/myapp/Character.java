

public abstract class Character {
    protected String name;
    protected int hp;
    protected int attackPower;

    public Character(String name, int hp, int attackPower) {
        this.name = name;
        this.hp = hp;
        this.attackPower = attackPower;
    }

    public abstract void attack(Character target);

    public void displayStatus() {
        System.out.println(name + " - HP: " + hp);
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        System.out.println(name + " nhận " + damage + " sát thương!");
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }
}