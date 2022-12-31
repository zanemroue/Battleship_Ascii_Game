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
public class Destroyer extends Ship {
    public Destroyer(){
        super(5,5);
    }
        public int Fill(){//Used to fill grid with Destroyer
        return super.deployShip();
        }
}
