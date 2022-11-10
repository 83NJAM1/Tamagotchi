package app.model;

/*
 * Salles
 * 
 * La forme en enum est pratique car il faut souvent switch différents comportements en fonction de la salle.
 * 
 * Une forme en classes est aussi possible. Je ne l'ai pas fait car ça alourdirait le modèle en terme de lisibilité.
 * En revanche ça permettrait d'instancier les salles, et donc de leur mettre un état "abimé" par exemple si le Pet s'ennuie et fait ses griffes sur le canapé.
 *
 */

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



