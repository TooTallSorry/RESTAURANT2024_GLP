package engine.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


import config.GameConfiguration;
import engine.map.Block;
import engine.map.Map;
import engine.menu.Dessert;
import engine.menu.Entree;
import engine.menu.Ingredient;
import engine.menu.Nourriture;
import engine.menu.ListeIngredient;
import engine.menu.Menu;
import engine.menu.Plat;
import engine.mobile.*;
import engine.outil.Chaise;
import engine.outil.Stock;
import engine.outil.Table;
import player.Player;
import system.Calendrier;
import system.Reputation;
import system.Satisfaction;
import system.Temps;


public class MobileElementManager {
	private static final int MAX_CLIENTS = 8;

	private Map map;
	private Table table;
	
	List<Direction> movements2 = new ArrayList<>();
	
	private List<Client> clients = new ArrayList<Client>();
	private List<Cuisinier> cuisiniers = new ArrayList<Cuisinier>();
	private List<Block> posOfClients = new ArrayList<Block>();
	private int clientCount = 0;
	private int spawnCount = 0;
	private int spawnDelay = GameConfiguration.SPAWN_DELAY;
	private int journeyTime = GameConfiguration.PARTY_DURATION;
	private int idealTime = GameConfiguration.CLIENT_PATIENCE;
	private int clientServed = 0;
	
	private Block positionInit = new Block(1,1);
	private Block positionClient = new Block(5,5);
	
	private Block posServeur2 = new Block(4,7);
    private Block posServeur3 = new Block(11,7);
    
    private Serveur serveur2;

	private Chaise placeOccupee;
	private Player player;

	private int tempsAttente;
	private List<Serveur> serveurs = new ArrayList<Serveur>();

	private Menu menu = new Menu();

	
	/////////////////Cuisinier	
	
	private Cuisinier cuisinier;
	private Block posCuisinier = new Block(6,4);
	
	/////////////////Serveur
	
	private Block posServeur1 = new Block(7,7);
	private Serveur serveur;
	private HashMap<Serveur, List<Direction>> serveurMovements = new HashMap<>();
	List<Direction> movements = new ArrayList<>();

	
	/////////////////Table
	
	private List<Table> tables = new ArrayList<Table>();

    private Block posTable1 = new Block(6,9);
    private Block posTable2 = new Block(9,9);
    private Block posTable3 = new Block(6,14);
    private Block posTable4 = new Block(9,14);

    private Table table1 = new Table(posTable1);
    private Table table2 = new Table(posTable2);
    private Table table3 = new Table(posTable3);
    private Table table4 = new Table(posTable4);
    
    //////////////CHAISE
    private List<Chaise> chaises = new ArrayList<Chaise>();
    
    private Block posChaise1 = new Block(6, 8);
    private Block posChaise2 = new Block(9, 8);
    private Block posChaise3 = new Block(6, 13);
    private Block posChaise4 = new Block(9, 13);
    private Block posChaise5 = new Block(6, 10);
    private Block posChaise6 = new Block(9, 10);
    private Block posChaise7 = new Block(6, 15);
    private Block posChaise8 = new Block(9, 15);
    private Block posChaise9 = new Block(3, 8);
    private Block posChaise10 = new Block(3, 10);
    private Block posChaise11 = new Block(3, 13);
    private Block posChaise12 = new Block(3, 15);
    private Block posChaise13 = new Block(1, 8);
    private Block posChaise14 = new Block(1, 13);
    private Block posChaise15 = new Block(1, 19);
    private Block posChaise16 = new Block(3, 19);
    private Block posChaise17 = new Block(6, 19);
    private Block posChaise18 = new Block(9, 19);
    private Block posChaise19 = new Block(12, 19);
    private Block posChaise20 = new Block(12, 13);
    private Block posChaise21 = new Block(12, 8);
    private Block posChaise22 = new Block(1, 10);
    private Block posChaise23 = new Block(1, 15);
    private Block posChaise24 = new Block(1, 21);
    private Block posChaise25 = new Block(3, 21);
    private Block posChaise26 = new Block(6, 21);
    private Block posChaise27 = new Block(9, 21);
    private Block posChaise28 = new Block(12, 21);
    private Block posChaise29 = new Block(12, 15);
    private Block posChaise30 = new Block(12, 10);

    private Chaise chaise22 = new Chaise(false, posChaise22);
    private Chaise chaise23 = new Chaise(false, posChaise23);
    private Chaise chaise24 = new Chaise(false, posChaise24);
    private Chaise chaise25 = new Chaise(false, posChaise25);
    private Chaise chaise26 = new Chaise(false, posChaise26);
    private Chaise chaise27 = new Chaise(false, posChaise27);
    private Chaise chaise28 = new Chaise(false, posChaise28);
    private Chaise chaise29 = new Chaise(false, posChaise29);
    private Chaise chaise30 = new Chaise(false, posChaise30);
    private Chaise chaise13 = new Chaise(false, posChaise13);
    private Chaise chaise14 = new Chaise(false, posChaise14);
    private Chaise chaise15 = new Chaise(false, posChaise15);
    private Chaise chaise16 = new Chaise(false, posChaise16);
    private Chaise chaise17 = new Chaise(false, posChaise17);
    private Chaise chaise18 = new Chaise(false, posChaise18);
    private Chaise chaise19 = new Chaise(false, posChaise19);
    private Chaise chaise20 = new Chaise(false, posChaise20);
    private Chaise chaise21 = new Chaise(false, posChaise21);
    private Chaise chaise9 = new Chaise(false, posChaise9);
    private Chaise chaise10 = new Chaise(false, posChaise10);
    private Chaise chaise11 = new Chaise(false, posChaise11);
    private Chaise chaise12 = new Chaise(false, posChaise12);
    private Chaise chaise1 = new Chaise(false,posChaise1);
    private Chaise chaise2 = new Chaise(false, posChaise2);
    private Chaise chaise3 = new Chaise(false, posChaise3);
    private Chaise chaise4 = new Chaise(false, posChaise4);
    private Chaise chaise5 = new Chaise(false, posChaise5);
    private Chaise chaise6 = new Chaise(false, posChaise6);
    private Chaise chaise7 = new Chaise(false, posChaise7);
    private Chaise chaise8 = new Chaise(false, posChaise8);
    private ListeIngredient listeIngredient;
    private Stock stock;
    private MenuElementManager menuManager;
    
    private Reputation reputation;
    
    private Calendrier calendrier;
    private Temps temps;
  
  
	public void setCuisinier(Cuisinier cuisinier) {
	    this.cuisinier = cuisinier;
	}
	public MobileElementManager(Map map, Player player, Serveur serveur, Client client, Stock stock) {
		this.map = map;
		this.player = new Player(100000, clientCount, clientCount, 1, menu, serveurs, null, tables, stock, chaises, 0); // Utilisez le player passé en paramètre au lieu de créer un nouveau
		this.stock = new Stock(); // Utilisez le stock passé en paramètre
		this.serveur = serveur; // Utilisez le serveur passé en paramètre
		reputation = new Reputation(null);
		calendrier = new Calendrier(1, journeyTime);
		ListeIngredient listeIngredient = new ListeIngredient();

		this.menuManager = new MenuElementManager(this.stock, listeIngredient); 
		this.menuManager.initialiserStockAvecIngrédientsDeDépart(); // Initialisez le stock avec les ingrédients

	    // Assurez-vous que cela est conforme à votre conception
	    this.cuisinier = new Cuisinier(1,1,posCuisinier);
	    this.serveur = new Serveur(1,1,posServeur1);; // Utilisez directement l'instance fournie
	    this.serveurs.add(this.serveur); // Cela suppose que 'serveur' est déjà correctement initialisé
	    this.posOfClients.add(client.getPosition());
	    this.table = new Table(new Block(100, 100));
        this.tables.add(table1);
        this.tables.add(table2);
        this.tables.add(table3);
        this.tables.add(table4);
        this.chaises.add(chaise1);
        this.chaises.add(chaise2);
        this.chaises.add(chaise3);
        this.chaises.add(chaise4);
        this.chaises.add(chaise5);
        this.chaises.add(chaise6);
        this.chaises.add(chaise7);
        this.chaises.add(chaise8);
        this.chaises.add(chaise9);
        this.chaises.add(chaise10);
        this.chaises.add(chaise11);
        this.chaises.add(chaise12);
        this.chaises.add(chaise13);
        this.chaises.add(chaise14);
        this.chaises.add(chaise15);
        this.chaises.add(chaise16);
        this.chaises.add(chaise17);
        this.chaises.add(chaise18);
        this.chaises.add(chaise19);
        this.chaises.add(chaise20);
        this.chaises.add(chaise21);
        this.chaises.add(chaise22);
        this.chaises.add(chaise23);
        this.chaises.add(chaise24);
        this.chaises.add(chaise25);
        this.chaises.add(chaise26);
        this.chaises.add(chaise27);
        this.chaises.add(chaise28);
        this.chaises.add(chaise29);
        this.chaises.add(chaise30);
        
        
		this.menuManager = new MenuElementManager(stock, listeIngredient);
		
		cuisiniers.add(cuisinier);
	}

    public void initiateServeurMovement(Serveur serveur, List<Direction> movements) {
        serveurMovements.put(serveur, movements);
    }

    
    public void checkerServeur2() {
    	
    }

	
	public Block currentClientPos() {
	    Block position = null;
	    
	    for (Client client : clients) {
	        if (client != null) { // Ajoutez cette vérification pour éviter NullPointerException
	            position = client.getPosition();
	        }
	    }
	    
	    return position;
	}

	public void moveLeftServeur() {
		Block position = serveur.getPosition();

		if (position.getColumn() > 0) {
			Block newPosition = map.getBlock(position.getLine(), position.getColumn() - 1);
			serveur.setPosition(newPosition);
		}

	}

	public void moveRightServeur() {
		Block position = serveur.getPosition();

		if (position.getColumn() < GameConfiguration.COLUMN_COUNT - 1) {
			Block newPosition = map.getBlock(position.getLine(), position.getColumn() + 1);
			serveur.setPosition(newPosition);
		}
	}
	
	public void moveUpServeur() {
		Block position = serveur.getPosition();

		if (position.getLine() < GameConfiguration.LINE_COUNT - 1) {
			Block newPosition = map.getBlock(position.getLine() - 1, position.getColumn());
			serveur.setPosition(newPosition);
		}
	}
	
	public void moveDownServeur() {
		Block position = serveur.getPosition();

		if (position.getLine() > 0) {
			Block newPosition = map.getBlock(position.getLine() + 1, position.getColumn());
			serveur.setPosition(newPosition);
		}
	}
	
	public void getAndSetRep(Client client) {
		int tp = client.setAndGetSat();
		setRep(getRep()+tp);
	}

	public void nextRound() {
			generateClient(chaises);		
			chooseClient (clients);
			cyclicTime();
			executeServeurMovement(serveur,movements);
			getCommande(clients);
			handleTheEating();
			clientCleaner();
			executeServeurMovement(serveur2,movements2);
	}
	

 
    
    ///////
    ///////
    ///////
    ///////FONCTION SERVEUR
    ///////
    ///////
    ///////
    
	public List<Serveur> getServeurs() {
        return serveurs;
    }
	
	public List<Table> getTables(){
		return tables;
	}
	public List<Chaise> getChaises(){
		return chaises;
	}
	public Serveur getServeur() {
		return serveur;
	}
	public void set(Serveur serveur) {
		this.serveur = serveur;
	}


	public void add(Serveur serveur) {
		serveurs.add(serveur);
	}
	
	public void addServeur2() {
		serveur2 = new Serveur(1,1,posServeur2);
        serveurs.add(serveur2);
    }
    public void addServeur3() {
        serveurs.add(new Serveur(1,1,posServeur3));
    }
	
	  public void getCommande(List<Client> clients) {
	        for (Client client : clients) {
	            if (client.getEtat() == 1) {
	               
	                
	                if (client.getPosition() == posChaise1) {
	                    movements.add(Direction.RIGHT);
	                    movements.add(Direction.LEFT);
	                    client.incrementEtat();
	                    break;
	                } else if (client.getPosition() == posChaise2) {
	                    movements.add(Direction.DOWN);
	                    movements.add(Direction.RIGHT);
	                    movements.add(Direction.LEFT);
	                    movements.add(Direction.UP);
	                    client.incrementEtat();
	                    break;
	                } else if (client.getPosition() == posChaise3) {
	                    for (int i = 0; i < 6; i++) {
	                        movements.add(Direction.RIGHT);
	                    }
	                    for (int i = 0; i < 6; i++) {
	                        movements.add(Direction.LEFT);
	                    }
	                    client.incrementEtat();
	                    break;
	                } else if (client.getPosition() == posChaise4) {
	                    movements.add(Direction.DOWN);
	                    for (int i = 0; i < 6; i++) {
	                        movements.add(Direction.RIGHT);
	                    }
	                    for (int i = 0; i < 6; i++) {
	                        movements.add(Direction.LEFT);
	                    }
	                    movements.add(Direction.UP);
	                    client.incrementEtat();
	                    break;
	                } else if (client.getPosition() == posChaise5) {
	                    movements.add(Direction.RIGHT);
	                    movements.add(Direction.RIGHT);
	                    movements.add(Direction.RIGHT);
	                    movements.add(Direction.LEFT);
	                    movements.add(Direction.LEFT);
	                    movements.add(Direction.LEFT);
	                    client.incrementEtat();
	                    break;
	                } else if (client.getPosition() == posChaise6) {
	                    movements.add(Direction.DOWN);
	                    movements.add(Direction.RIGHT);
	                    movements.add(Direction.RIGHT);
	                    movements.add(Direction.RIGHT);
	                    movements.add(Direction.LEFT);
	                    movements.add(Direction.LEFT);
	                    movements.add(Direction.LEFT);
	                    movements.add(Direction.UP);
	                    client.incrementEtat();
	                    break;
	                } else if (client.getPosition() == posChaise7) {
	                    for (int i = 0; i < 8; i++) {
	                        movements.add(Direction.RIGHT);
	                    }
	                    for (int i = 0; i < 8; i++) {
	                        movements.add(Direction.LEFT);
	                    }
	                    client.incrementEtat();
	                    break;
	                } else if (client.getPosition() == posChaise8) {
	                    movements.add(Direction.DOWN);
	                    
	                    for (int i = 0; i < 8; i++) {
	                        movements.add(Direction.RIGHT);
	                    }
	                    for (int i = 0; i < 8; i++) {
	                        movements.add(Direction.LEFT);
	                    }
	                    movements.add(Direction.UP);
	                    client.incrementEtat();
	                    break;
	                    
	                }
	                
	                else if (client.getPosition() == posChaise9) {
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements.add(Direction.RIGHT);
	                    // Ajouter les mouvements inverses
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements.add(Direction.LEFT);
	                    client.incrementEtat();
	                    break;
	                }
	                else if (client.getPosition() == posChaise10) {
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    // Ajouter les mouvements inverses
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    client.incrementEtat();
	                    break;
	                }
	                else if (client.getPosition() == posChaise11) {
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    // Ajouter les mouvements inverses
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    client.incrementEtat();
	                    break;
	                }
	                else if (client.getPosition() == posChaise12) {
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    // Ajouter les mouvements inverses
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    client.incrementEtat();
	                    break;
	                }
	                else if (client.getPosition() == posChaise13) {
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.RIGHT);
	                    // Ajouter les mouvements inverses
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.LEFT);
	                    client.incrementEtat();
	                    break;
	                }

	                else if (client.getPosition() == posChaise14) {
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    // Ajouter les mouvements inverses
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    client.incrementEtat();
	                    break;
	                }

	                else if (client.getPosition() == posChaise15) {
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.UP);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    movements2.add(Direction.RIGHT);
	                    // Ajouter les mouvements inverses
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.DOWN);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    movements2.add(Direction.LEFT);
	                    client.incrementEtat();
	                    break;
	                }
	                
	              
	                
	                // Incrémenter l'état du client
	                
	            }
	        }
	    }
	    
	    // Autres fonctions de la classe

	  private void executeServeurMovement(Serveur serveur, List<Direction> directions) {
		    if (!directions.isEmpty()) { // Vérifiez d'abord si la liste de directions n'est pas vide
		        Direction direction = directions.get(0);

		        // Utilisez un switch pour exécuter le mouvement correspondant
		        switch (direction) {
		            case UP:
		                moveUpServeur();
		                break;
		            case DOWN:
		                moveDownServeur();
		                break;
		            case LEFT:
		                moveLeftServeur();
		                break;
		            case RIGHT:
		                moveRightServeur();
		                break;
		            // Gérer d'autres directions si nécessaire
		        }
		        
		        // Supprimez la direction seulement après avoir effectué le déplacement
		        directions.remove(0);
		    }
		}
	

	public void eraseClient(List<Client> client) {
		for(Client client1 : client) {
		
	        if (client1.getEtat()==5){	        	
	        	
	        	clients.remove(clients.get(0));
	        	player.setArgent(player.getArgent()+10);
	        	//payFood(client1);
	        	for (Chaise chaise : chaises) {
	                if (chaise.getPosition().equals(client1.getPosition())) {
	                	chaise.setState(false);
	                }
	        	}
	            
	        	
	        	
	        	
	        	// Commenté pour ne pas générer immédiatement un nouveau client
	            // generateClient();
	        }
	    }
	}
	
	public void payFood(Client client) {
			List<Nourriture> choixFinal;
			choixFinal = client.getChoixFinal();
			int moneyPay = 0;
			
			for (Nourriture plat : choixFinal) {
				moneyPay += plat.getPrix();
			}
			
			player.setArgent(player.getArgent()+moneyPay);
	}
	
	

	

	
	public void setPlaceOccupee(Chaise placeOccupee) {
		this.placeOccupee = placeOccupee;
	}
	
	public Table getTable() {
		return table;
	}

	public Player getPlayer() {
		return player;
	}
	
	private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}	
	
    ///////
    ///////
    ///////    	
	///////FONCTION TEMPS
    ///////
    ///////
    ///////
    
	
	
	
	public int getTempsAttente() {
		return tempsAttente;
	}

	public void setTempsAttente(int tempsAttente) {
		//Faire le setTempsAttente modifié.
	}
	
	public void temps() {
		this.tempsAttente++;
	}	
	
	
	private void cyclicTime() {
		this.journeyTime -= 1;
		Iterator<Client> itr = clients.iterator();
		while (itr.hasNext()) {
			Client client = itr.next();
			if (client.getEtat() == 1) {
				client.attenteNourriturePlus();
			}
		}
	}	
	
	
	public int getJourneyTime() {
		return journeyTime;
	}
 
	public void resetJourneyTime() {
		journeyTime = GameConfiguration.PARTY_DURATION;
	}
	
	
	
    ///////
    ///////
    ///////     
	///////FONCTION CLIENT
    ///////
    ///////
    ///////
    
		public void add(Client client) {
			clients.add(client);
		}
		
		public List<Client> getClients() {
			return clients;
		}
		
	    private void generateClient(List<Chaise> chaises) {
	    	if (spawnDelay == 0) {
	    		
	    		Block posClient = placeLibre(chaises);
	    	 
	         	if ((posClient != null)) {         
	        	
	         		Client newClient = new Client(posClient, 20, 0, serveur, player); // Assumez une signature de constructeur Client(Block, int)
	         		clients.add(newClient);
	         		posOfClients.add(posClient);
	         		spawnDelay = GameConfiguration.SPAWN_DELAY;
	         	}
	    	}
	        else {
	        	 spawnDelay -= 1;
	         }
	    }
	    
	    private void handleTheEating() {
	    	for (Client client : clients) {
	    		if (client != null) {
		            if ((client.getTempsAttente() < idealTime) && (client.getEtat() == 2)) {
		            	client.temps();
		            }
		            else {
		            	if ((client.getTempsAttente() == idealTime) && (client.getEtat() == 2)) {
		            		payFood(client);
		            		client.incrementEtat();
		            		
		            	}
		            }
	    		}
	    	}
	    }
	    
	    public void clientCleaner() {
	    	Iterator<Client> itr = clients.iterator();            
	    	while(itr.hasNext()){
	    	    Client client = itr.next();
	    	    if(client.getEtat() == 3){
	    		    Iterator<Chaise> itrChaise = chaises.iterator();  
	    		    Iterator<Block> itrPos = posOfClients.iterator();
	    		    while (itrChaise.hasNext()) {
	    		    	Chaise chaise = itrChaise.next();
	    		    	while (itrPos.hasNext()) {
	    		    		Block pos = itrPos.next();
	    		    		if ((pos == client.getPosition()) && (chaise.getPosition() == client.getPosition())) {
	    		    			chaise.setState(false);
	    		    			itrPos.remove();
	    		    		}
	    		    	}
	    		    }
	    	        itr.remove();
	    	        clientServedAndOut();
	    	    }
	    	}
	    }
	    
	    public static Block placeLibre(List<Chaise> chaises) {
	        for (Chaise chaise : chaises) {
	            if (chaise.getState() == false) {
	                // Si une chaise est libre, retourne ses coordonnées
	            	chaise.setState(true);
	                return chaise.getPosition();
	            }
	        }
	        // Si aucune chaise n'est libre, retourne null
	        return null;
	    }

	public List<Nourriture> choose(Menu menu,Client client) {
	    List<Nourriture> choixFinal = new ArrayList<>();
	    Random random = new Random();
	    int lvlCuistot = cuisinier.getNiveau();
	    if(menuManager.getStock()!=null) {
	    // Sélection aléatoire d'une entrée en fonction du niveau du cuisinier
	    switch (lvlCuistot) {
	        case 1:
	            menuManager.ajouterEntreeNiveau1(choixFinal, menu);
	            break;
	        case 2:
	            if (random.nextBoolean()) { // Choix aléatoire entre niveau 1 et 2
	                menuManager.ajouterEntreeNiveau1(choixFinal, menu);
	            } else {
	                menuManager.ajouterEntreeNiveau2(choixFinal, menu);
	            }
	            break;
	        case 3:
	            int choixEntree = random.nextInt(3); // Choix aléatoire entre niveau 1, 2 et 3
	            if (choixEntree == 0) {
	                menuManager.ajouterEntreeNiveau1(choixFinal, menu);
	            } else if (choixEntree == 1) {
	                menuManager.ajouterEntreeNiveau2(choixFinal, menu);
	            } else {
	                menuManager.ajouterEntreeNiveau3(choixFinal, menu);
	            }
	            break;
	            
	    }
	    
	    switch (lvlCuistot) {
        case 1:
            menuManager.ajouterPlatNiveau1(choixFinal, menu);
            break;
        case 2:
            if (random.nextBoolean()) { // Choix aléatoire entre niveau 1 et 2
                menuManager.ajouterPlatNiveau1(choixFinal, menu);
            } else {
                menuManager.ajouterPlatNiveau2(choixFinal, menu);
            }
            break;
        case 3:
            int choixEntree = random.nextInt(3); // Choix aléatoire entre niveau 1, 2 et 3
            if (choixEntree == 0) {
                menuManager.ajouterPlatNiveau1(choixFinal, menu);
            } else if (choixEntree == 1) {
                menuManager.ajouterPlatNiveau2(choixFinal, menu);
            } else {
                menuManager.ajouterPlatNiveau3(choixFinal, menu);
            }
            break;
    }
	    
	    switch (lvlCuistot) {
        case 1:
            menuManager.ajouterDessertNiveau1(choixFinal, menu);
            break;
        case 2:
            if (random.nextBoolean()) { // Choix aléatoire entre niveau 1 et 2
                menuManager.ajouterDessertNiveau1(choixFinal, menu);
            } else {
                menuManager.ajouterDessertNiveau2(choixFinal, menu);
            }
            break;
        case 3:
            int choixEntree = random.nextInt(3); // Choix aléatoire entre niveau 1, 2 et 3
            if (choixEntree == 0) {
                menuManager.ajouterDessertNiveau1(choixFinal, menu);
            } else if (choixEntree == 1) {
                menuManager.ajouterDessertNiveau2(choixFinal, menu);
            } else {
                menuManager.ajouterDessertNiveau3(choixFinal, menu);
            }
            break;
    }
	    
	    for (Nourriture nourriture : choixFinal) {
	    	for (Ingredient ingredient : nourriture.getIngredients()) {
	    		stock.utiliserIngredient(ingredient, 1);
	    	}
	    }
	    getAndSetRep(client);
	    client.incrementEtat();
        return choixFinal;
	    }else {
	    	client.incrementEtat();
	    	return choixFinal;
	    	
	    }
    }
	
	public int getClientServed() {
		return clientServed;
	}
	
	public void clientServedAndOut() {
		clientServed++;
	}
	
	private void chooseClient (List<Client> client){
	
		for(Client client1 : clients) {
			if(client1.getEtat()==0) {
				client1.setChoixFinal(choose(menu,client1));
			}
		}
	}
	
	public void resetAll() {
		clients.removeAll(clients);
		posOfClients.removeAll(posOfClients);
		Iterator<Chaise> itr = chaises.iterator();
		while (itr.hasNext()) {
			Chaise chaise = itr.next();
			chaise.setState(false);
		}
	}
	
	public Cuisinier getCuisinier() {

		return cuisinier;
	}
	public MenuElementManager getMenuManager() {

		return this.menuManager;
	}
    public Stock getStock() {
        return this.stock;
    }
	
	public int getRep() {
		return player.getReputation();
	}
	
	public void setRep(int rep) {
		player.setReputation(rep);
	}
	public Calendrier getCalendrier() {
		return calendrier;
	}
}