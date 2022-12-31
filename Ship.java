/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COMPSCI_IA;
import java.util.ArrayList;
/**
 *
 * @author zanemroue
 */
public class Ship {
   private int[] size;
   private int num;
    public Ship(int s, int n){
    num = n;
    size = new int[s];
    for (int i = 0; i < s; i ++){
        size[i] = n;
    }
    
    
}

public int Hit(){//Hits part of the coordinates of the ship
return -num;
}
public void Fillup(int z){
    for (int i = 0; i < size.length;i++){
        size[i] = z;
    }
}
public int deployShip(){//Used to place digit of ship on board
    return num;
}

}
