public enum Direction {
	NORTH("north"), SOUTH("south"), EAST("east"), WEST("west");
	
	private final String displayName;
	
	Direction(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public static boolean isValidDirection(String direction) {
		for (Direction value : values()) {
			if (value.getDisplayName().equalsIgnoreCase(direction)) {
				return true;
			}
		}
		return false;
	}
	
	public static Direction fromString(String direction) {
		for (Direction value : values()) {
			if (value.getDisplayName().equalsIgnoreCase(direction)) {
				return value;
			}
		}
		return null;
	}
}
