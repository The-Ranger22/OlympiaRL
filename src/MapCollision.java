public interface MapCollision {
//    int[] TILE_CODES =
//            {
//                    0, //Blocker tile//
//                    1, //Four-way
//                    2, //Horizontal Hall
//                    3, //vertical hall
//                    4, //t-junction _|_
//                    5, //t-junction -.-
//                    6, //t-junction |-
//                    7, //t-junction -|
//                    8, //end cap right -.
//                    9, //end cap up '
//                    10, //end cap down
//                    11, //end cap left
//                    12, //bend up right
//                    13, //bend up left
//                    14, //bend down right
//                    15, //bend down left
//                    16 //floor
//            };
    int[] INACCESSIBLE_ABOVE_TILES = {2, 5, 8, 9, 11, 14, 15};
    int[] INACCESSIBLE_BELOW_TILES = {2, 4, 8, 10, 11, 12, 13};
    int[] INACCESSIBLE_LEFT_TILES = {3, 6, 9, 10, 11, 12, 14};
    int[] INACCESSIBLE_RIGHT_TILES = {3, 7, 9, 10, 8, 13, 15};
    static boolean aboveTileAccessible(int currentTile, int aboveTile) {
        if(aboveTile <= 0) return false;
        if(containedIn(currentTile, INACCESSIBLE_ABOVE_TILES)) return false;
        return true;
    }
    static boolean belowTileAccessible(int currentTile, int belowTile) {
        if(belowTile <= 0) return false;
        if(containedIn(currentTile, INACCESSIBLE_BELOW_TILES)) return false;
        return true;
    }
    static boolean leftTileAccessible(int currentTile, int leftTile) {
        if(leftTile <= 0) return false;
        if(containedIn(currentTile, INACCESSIBLE_LEFT_TILES)) return false;
        return true;
    }
    static boolean rightTileAccessible(int currentTile, int rightTile) {
        if(rightTile <= 0) return false;
        if(containedIn(currentTile, INACCESSIBLE_RIGHT_TILES)) return false;
        return true;
    }
    private static boolean containedIn(int currentTile, int[] tileSet){
        for(int i = 0; i < tileSet.length; i++){
            if(currentTile == tileSet[i]){
                return true;
            }
        }
        return false;
    }
}
