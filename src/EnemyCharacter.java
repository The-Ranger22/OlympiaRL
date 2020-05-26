public class EnemyCharacter extends GameCharacter{

    EnemyCharacter(){}
    EnemyCharacter(int startX, int startY){
        this.setPosX(startX);
        this.setPosY(startY);
        this.setCharacterImage(AssetLoader.loadPlayerToken());
    }

    @Override
    String ConvertToString() {
        return null;
    }
}