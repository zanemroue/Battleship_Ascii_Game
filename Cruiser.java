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
public class Cruiser extends Ship {
    public Cruiser(){
        super(3,3);
        
    }
     public int Fill(){//Used to fill grid with Cruiser
        return super.deployShip();
     }
}

