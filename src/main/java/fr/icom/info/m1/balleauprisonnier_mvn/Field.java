package fr.icom.info.m1.balleauprisonnier_fx;


import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class Field extends Canvas {
	
	/** Joueurs */
	Player [] joueurs = new Player[3];
	Player [] joueurs2 = new Player[3];
	/** Couleurs possibles */
	String[] colorMap = new String[] {"blue", "green", "orange", "purple", "yellow"};
	/** Tableau traçant les evenements */
    ArrayList<String> input = new ArrayList<String>();
    

    final GraphicsContext gc;
    final int width;
    final int height;
    
    /**
     * Canvas dans lequel on va dessiner le jeu.
     * 
     * @param scene Scene principale du jeu a laquelle on va ajouter notre Canvas
     * @param w largeur du canvas
     * @param h hauteur du canvas
     */
	public Field(Scene scene, int w, int h) 
	{
		super(w, h); 
		width = w;
		height = h;
		
		/** permet de capturer le focus et donc les evenements clavier et souris */
		this.setFocusTraversable(true);
		
        gc = this.getGraphicsContext2D();
        
        /** On initialise le terrain de jeu */
    	joueurs[0] = new ManualPlayer(gc, colorMap[0], w/4, 20, "top");
    	joueurs[0].display();

    	joueurs[1] = new StaticPlayer(gc, colorMap[0], w/2, 20, "top");
    	joueurs[1].display();

    	joueurs[2] = new StaticPlayer(gc, colorMap[0], w/3, 20, "top");
    	joueurs[2].display();
    	
    	joueurs2[0] = new ManualPlayer(gc, colorMap[1], w/4, h-50, "bottom");
    	joueurs2[0].display();

    	joueurs2[1] = new StaticPlayer(gc, colorMap[1], w/2, h-50, "bottom");
    	joueurs2[1].display();

    	joueurs2[2] = new StaticPlayer(gc, colorMap[1], w/3, h-50, "bottom");
    	joueurs2[2].display();
	    /** 
	     * Event Listener du clavier 
	     * quand une touche est pressee on la rajoute a la liste d'input
	     *   
	     */
	    this.setOnKeyPressed(
	    		new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            // only add once... prevent duplicates
	    	            if ( !input.contains(code) )
	    	                input.add( code );
	    	        }
	    	    });

	    /** 
	     * Event Listener du clavier 
	     * quand une touche est relachee on l'enleve de la liste d'input
	     *   
	     */
	    this.setOnKeyReleased(
	    	    new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            input.remove( code );
	    	        }
	    	    });
	    
	    /** 
	     * 
	     * Boucle principale du jeu
	     * 
	     * handle() est appelee a chaque rafraichissement de frame
	     * soit environ 60 fois par seconde.
	     * 
	     */
	    new AnimationTimer() 
	    {
	        public void handle(long currentNanoTime)
	        {	 
	            // On nettoie le canvas a chaque frame
	            gc.setFill( Color.LIGHTGRAY);
	            gc.fillRect(0, 0, width, height);
	        	
	            // Deplacement et affichage des joueurs

	    	    
	        		if (input.contains("LEFT"))
	        		{
	        			joueurs[0].moveLeft();
	        		} 
	        		if (input.contains("RIGHT")) 
	        		{
	        			joueurs[0].moveRight();	        			
	        		}
	        		if (input.contains("UP"))
	        		{
	        			joueurs[0].turnLeft();
	        		} 
	        		if (input.contains("DOWN")) 
	        		{
	        			joueurs[0].turnRight();	        			
	        		}
	        		if (input.contains("T")){
	        			joueurs[0].shoot();
					}
	        		joueurs[0].display();
	        		joueurs[1].display();
	        		joueurs[2].display();
	        		
	        		
	        		
	        		
	        		if (input.contains("A"))
	        		{
	        			joueurs2[0].moveLeft();
	        		} 
	        		if (input.contains("D")) 
	        		{
	        			joueurs2[0].moveRight();	        			
	        		}
	        		if (input.contains("W"))
	        		{
	        			joueurs2[0].turnLeft();
	        		} 
	        		if (input.contains("S")) 
	        		{
	        			joueurs2[0].turnRight();	        			
	        		}
	        		if (input.contains("Q"))
	        		{
	        			joueurs2[0].moveLeft();
	        		} 
	        		if (input.contains("Z"))
	        		{
	        			joueurs2[0].turnLeft();
	        		}         		
	        		if (input.contains("B")){
	        			joueurs2[0].shoot();
					}	
	        		
	        		joueurs2[0].display();
	        		joueurs2[1].display();
	        		joueurs2[2].display();
	    	    
	        	/*for (int i = 0; i < joueurs.length; i++) 
	    	    {
	        		if (i==0 && input.contains("LEFT"))
	        		{
	        			joueurs[i].moveLeft();
	        		} 
	        		if (i==0 && input.contains("RIGHT")) 
	        		{
	        			joueurs[i].moveRight();	        			
	        		}
	        		if (i==0 && input.contains("UP"))
	        		{
	        			joueurs[i].turnLeft();
	        		} 
	        		if (i==0 && input.contains("DOWN")) 
	        		{
	        			joueurs[i].turnRight();	        			
	        		}
	        		if (i==1 && input.contains("A"))
	        		{
	        			joueurs[i].moveLeft();
	        		} 
	        		if (i==1 && input.contains("D")) 
	        		{
	        			joueurs[i].moveRight();	        			
	        		}
	        		if (i==1 && input.contains("W"))
	        		{
	        			joueurs[i].turnLeft();
	        		} 
	        		if (i==1 && input.contains("S")) 
	        		{
	        			joueurs[i].turnRight();	        			
	        		}
	        		if (i==1 && input.contains("Q"))
	        		{
	        			joueurs[i].moveLeft();
	        		} 
	        		if (i==1 && input.contains("Z"))
	        		{
	        			joueurs[i].turnLeft();
	        		} 
	        		if (input.contains("T")){
	        			joueurs[i].shoot();
					}	        	
	        		
	        		
	        		joueurs[i].display();
	    	    }*/
	    	}
	     }.start(); // On lance la boucle de rafraichissement 
	     
	}

	public Player[] getJoueurs() {
		return joueurs;
	}
	
	public Player[] getJoueurs2() {
		return joueurs2;
	}
	
}
