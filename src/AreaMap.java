public class AreaMap extends GameMap {
    private int[][] tileLayer;
    private GameCharacter[][] characterLayer;
    private Mapper mapper;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
//
    public AreaMap(){}

    public int[][] getTileLayer(){return tileLayer;}
    public GameCharacter[][] getCharacterLayer(){return characterLayer;}
    public int getStartX(){return this.startX;}
    public int getStartY(){return this.startY;}
    public void loadTestMap(){
        this.tileLayer = new int[][]{
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, -1},
                {-1, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, -1},
                {-1, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, -1},
                {-1, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, -1},
                {-1, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, -1},
                {-1, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, -1},
                {-1, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, -1},
                {-1, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, -1},
                {-1, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, -1},
                {-1, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, -1},
                {-1, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, -1},
                {-1, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, -1},
                {-1, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}
        };
        this.characterLayer = new GameCharacter[this.tileLayer.length][this.tileLayer[0].length];
        for(int y = 0; y < this.characterLayer.length; y++){
            for(int x = 0; x < this.characterLayer[y].length; x++){
                this.characterLayer[y][x] = null;
            }
        }
        this.startX = 7;
        this.startY = 7;

    }
    public void loadRandomMap(){
        loadRandomMap(15, 15);
    }
    public void loadRandomMap(int mapHeight, int mapWidth){
        if(mapHeight < 15){
            System.out.println("mapHeight below acceptable parameter value: 15. Correcting...");
            mapHeight = 15;
        }
        if(mapWidth < 15){
            System.out.println("mapWidth below acceptable parameter value: 15. Correcting...");
            mapWidth = 15;
        }
        this.tileLayer = new int[mapHeight][mapWidth];
        Mapper mapper = new Mapper();
        mapper.map(true);
    }
    public void addEnemy(GameCharacter...enemy){
        for(GameCharacter ec : enemy){
            characterLayer[ec.getPosY()][ec.getPosX()] = ec;
        }
    }
    public int yBoundary(){return this.tileLayer.length;}
    public int xBoundary(){return this.tileLayer[0].length;}

    private class Mapper{
        int yPosition;
        int xPosition;

        int tileType;

        int lastX;
        int lastY;

        Mapper(){}
        private void map(){
            map(false);
        }
        public void map(boolean nullTiles){
            this.tileType = 1;
            boolean moveTaken;

            genStartAndEnd();
            AreaMap.this.tileLayer[yPosition][xPosition] = tileType;
            lastX = xPosition;
            lastY = yPosition;

            while(this.yPosition != AreaMap.this.endY || this.xPosition != AreaMap.this.endX){
                //Can only move in one direction per iteration
                moveTaken = false;

                if(this.yPosition != AreaMap.this.endY){
                    //Checks to see if the distance between the current y position and the end y position is greater than the distance between the current x position and the end x position
                    if(((AreaMap.this.endY > this.yPosition) ? AreaMap.this.endY - this.yPosition : this.yPosition - AreaMap.this.endY) > ((AreaMap.this.endX > this.xPosition) ? AreaMap.this.endX - this.xPosition : this.xPosition - AreaMap.this.endX)) {
                        if (this.yPosition > AreaMap.this.endY) {
                            moveTaken = moveUp();
                        } else moveTaken = moveDown();
                    }
                }
                if(this.xPosition != AreaMap.this.endX && !moveTaken){
                    if(this.xPosition > AreaMap.this.endX){
                        moveLeft();
                    }
                    else moveRight();
                }
                if(this.yPosition == AreaMap.this.endY && this.xPosition == AreaMap.this.endX){
                    this.tileType = 11;
                }
                AreaMap.this.tileLayer[this.yPosition][this.xPosition] = this.tileType;

                if(nullTiles) AreaMap.this.tileLayer[this.yPosition][this.xPosition] = 1;

                System.out.println("["+xPosition+","+yPosition+"]");
//                this.lastX = xPosition;
//                this.lastY = yPosition;

            }
            AreaMap.this.tileLayer[AreaMap.this.startY][AreaMap.this.startX] = 1;
            AreaMap.this.tileLayer[AreaMap.this.endY][AreaMap.this.endX] = 1;
            System.out.println("Map generation complete");
        }

        private void genStartAndEnd(){
            int x = -1;
            int y = -1;
            int lastQuadrant = randomQuadrant();
            switch(lastQuadrant){
                case 1: x = randomHighX(); y = randomLowXY(); break; //First Quadrant
                case 2: x = randomLowXY(); y = randomLowXY(); break; //Second Quadrant
                case 3: x = randomLowXY(); y = randomHighY(); break; //Third Quadrant
                case 4: x = randomHighX(); y = randomHighY(); break; //Fourth Quadrant
            }
            AreaMap.this.startX = x; this.xPosition = x;
            AreaMap.this.startY = y; this.yPosition = y;
            System.out.println("Start:["+x+","+y+"]");
            x = -1;
            y = -1;
            //TODO: Reevaluate opposite positioning of the start and end
            //Selects the opposite quadrant of the one selected
            switch(lastQuadrant){
                case 1: x = randomLowXY(); y = randomHighY(); break;
                case 2: x = randomHighX(); y = randomHighY(); break;
                case 3: x = randomHighX(); y = randomLowXY(); break;
                case 4: x = randomLowXY(); y = randomLowXY(); break;
            }
            AreaMap.this.endX = x;
            AreaMap.this.endY = y;
            System.out.println("End:["+x+","+y+"]");
        }
        private void tileValue(int nextX, int nextY){
            //Compares three things: the last position (lastX, lastY),
            //the current position (this.xPosition, this.yPosition) and
            //the next position (nextX, nextY) while also taking into
            //consideration the starting quadrant and end quadrant when
            //selecting tiles

            /*
                  V
            Quad2 | Quad1
            ----- | -----
            Quad3 | Quad4
             */


            System.out.println("{}");
            System.out.println("Last:["+this.lastX+","+this.lastY+"]");
            System.out.println("Current:["+this.xPosition+","+this.yPosition+"]");
            System.out.println("Next:["+nextX+","+nextY+"]");
            System.out.println("{}");


            if(lastX < this.xPosition && nextY > this.yPosition){
                //System.out.println("Beep15");
                tileType = 15;
                return;
            }
            if(lastX < this.xPosition && nextY < this.yPosition){
                tileType = 12;
                return;
            }
            if(lastX > this.xPosition && nextY > this.yPosition){
                //System.out.println("Beep14");
                tileType = 14;
                return;
            }
            if(lastX > this.xPosition && nextY < this.yPosition){
                tileType = 13;
                return;
            }
            //if the y of each position is equal to one another, then the tile value would be 2 (tile_hall_h)
            if(lastY == nextY && lastY == this.yPosition){
                //System.out.println("Beep2");
                tileType = 2;
                return;}
            //if the x of each position is equal to one another, then the tile value would be 3 (tile_hall_v)
            if(lastX == nextX && lastX == this.xPosition){
                //System.out.println("Beep3");
                tileType = 3;
                return;
            }
            tileType = 1;


        }

        private int randomQuadrant(){
            return ((int)(Math.random() * 4) + 1);
        }
        private int randomLowXY(){
            return ((int)(Math.random() * 5) + 1);
        }
        private int randomHighY(){
            return ((AreaMap.this.tileLayer.length - 7) + (randomLowXY()));
        }
        private int randomHighX(){
            return((AreaMap.this.tileLayer[0].length - 7) + (randomLowXY()));
        }
        private boolean moveUp(){
            //Checks if the position above is out of bounds
            if(((this.yPosition) - 1) > 0) {
                //Checks if another tile has been laid
                if(AreaMap.this.tileLayer[this.yPosition - 1][this.xPosition] < 1) {
                    tileValue(this.xPosition, this.yPosition - 1);
                    this.lastX = this.xPosition;
                    this.lastY = this.yPosition;
                    this.yPosition--;
                    return true;
                }
            }
            return false;
        }
        private boolean moveDown(){
            //Checks if the position below is out of bounds
            if(((this.yPosition) + 1) < AreaMap.this.tileLayer.length) {
                //Checks if another tile has been laid
                if(AreaMap.this.tileLayer[this.yPosition + 1][this.xPosition] < 1) {
                    tileValue(this.xPosition, this.yPosition + 1);
                    this.lastX = this.xPosition;
                    this.lastY = this.yPosition;
                    this.yPosition++;
                    return true;
                }
            }
            return false;
        }
        private boolean moveRight(){
            //Checks if the right position is out of bounds
            if(((this.xPosition) + 1) < AreaMap.this.tileLayer.length) {
                //Checks if another tile has been laid
                if(AreaMap.this.tileLayer[this.yPosition][this.xPosition + 1] < 1) {
                    tileValue(this.xPosition + 1, this.yPosition);
                    this.lastX = this.xPosition;
                    this.lastY = this.yPosition;
                    this.xPosition++;
                    return true;
                }
            }
            return false;
        }
        private boolean moveLeft(){
            //Checks if the position above is out of bounds
            if(((this.xPosition) - 1) > 0) {
                //Checks if another tile has been laid
                if(AreaMap.this.tileLayer[this.yPosition][this.xPosition - 1] < 1) {
                    tileValue(this.xPosition - 1, this.yPosition);
                    this.lastX = this.xPosition;
                    this.lastY = this.yPosition;
                    this.xPosition--;
                    return true;
                }
            }
            return false;
        }
    }
}
