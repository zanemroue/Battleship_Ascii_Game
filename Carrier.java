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
public class Carrier extends Ship {
    public Carrier(){
        super(1,1);
        
    }
    public int Fill(){//Used to fill grid with Carrier
        return super.deployShip();
    }
    
}
