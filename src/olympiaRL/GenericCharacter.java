package olympiaRL;

public class GenericCharacter extends GameEntity{
    private int health;
    private int experience;
    private int level;
    private CharStats stats;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public CharStats getStats() {
        return stats;
    }

    public void setStats(CharStats stats) {
        this.stats = stats;
    }

    private class CharStats{
        private int strength;
        private int dexterity;
        private int constitution;
        private int intelligence;
        private int wisdom;
        private int charisma;
    }




}
