/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COMPSCI_IA;

/**
 *
 * @author zanemroue
 */
public class Battleship extends Ship {
    public Battleship(){
        super(2,2);
        
    }
        public int Fill(){//Used to fill grid with Battleship
        return super.deployShip();
        }
    
}
