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
public class Submarine extends Ship {
    public Submarine(){
        super(4,4);
        
    }
        public int Fill(){//Used to fill grid with Submarine
        return super.deployShip();
        }
    
}
