package app.model;

public enum Room {
	livingRoom,
	garden,
	bathroom,
	kitchen;
	
	public boolean isClose(Room room) {
		
		if(room==null || this==room) return false;
		
		switch(this) {
			case livingRoom:{switch(room) {
				case kitchen:return true;
				default:return false;
				}
			}
		
			case garden:{switch(room) {
				case kitchen:return true;
				default:return false;
				}
			}
		
			case bathroom:{switch(room) {
				case kitchen:return true;
				default:return false;
				}
			}
		
			case kitchen:return true;
		}
		
		return false;
	}
	
	public boolean isOutside() {
		return this==garden;
	}
	
	
}



