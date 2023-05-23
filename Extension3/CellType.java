public enum CellType {
    FREE, // Regular cell type
    OBSTACLE, // Obstacle cell type
    MUD, // Mud cell type (slow movement)
    ICE, // Ice cell type (fast movement)
    TURN // Turn cell type (to simulate the cost of a turn)
}
