/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COMPSCI_IA;
import java.util.*;
/**
 *
 * @author zanemroue
 */
public class BattleShipGAME{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //new MainPageGUI().setVisible(true);
        int choice = 0, score1 = 0, score2 = 0, turns = 0, previousmoves = 0;
        String playagain = "yes";
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> x0 = new ArrayList<Integer>();
        ArrayList<Integer> x1 = new ArrayList<Integer>();
        ArrayList<Integer> x2 = new ArrayList<Integer>();
        ArrayList<Integer> x3 = new ArrayList<Integer>();
        ArrayList<Integer> x4 = new ArrayList<Integer>();
        ArrayList<Integer> x5 = new ArrayList<Integer>();
        ArrayList<Integer> x6 = new ArrayList<Integer>();
        ArrayList<Integer> x7 = new ArrayList<Integer>();
        ArrayList<Integer> x8 = new ArrayList<Integer>();
        ArrayList<Integer> x9 = new ArrayList<Integer>();
        ArrayList<Integer> x10 = new ArrayList<Integer>();
        ArrayList<Integer> n1 = new ArrayList<Integer>();
        ArrayList<Integer> n2 = new ArrayList<Integer>();
        ArrayList<Integer> chits = new ArrayList<Integer>();
        int [][] map1 = new int[11][11];
        int [][] map2 = new int[11][11];
        int[][] compmap = new int[11][11];
        int[][] opp1map = new int[11][11];//Map to allow opponets to track their hits and misses
        int[][] opp2map = new int[11][11];//Map to allpow opponets to track their hits and misses
        Carrier carrier1 = new Carrier();
        Battleship battleship1 = new Battleship();
        Cruiser cruiser1 = new Cruiser();
        Submarine submarine1 = new Submarine();
        Destroyer destroyer1 = new Destroyer();
        Carrier carrier2 = new Carrier();
        Battleship battleship2 = new Battleship();
        Cruiser cruiser2 = new Cruiser();
        Submarine submarine2 = new Submarine();
        Destroyer destroyer2 = new Destroyer();
        System.out.println("Please select the gamemode you wish to play: " + "\n" + "1- One Player" + "\n" + "2- Two Players"); 
        while (choice != 1 || choice != 2){
        choice = input.nextInt();
        if (choice == 1){
            generateCoord(destroyer1, 5, compmap);
            generateCoord(submarine1, 4, compmap);
            generateCoord(cruiser1, 3, compmap);
            generateCoord(battleship1, 2, compmap);
            deployCarrier(compmap, carrier1);
            System.out.println("Please deploy your ships on the 10x10 grid");
            deployPlayerShips(map1, carrier1, battleship1, cruiser1, submarine1, destroyer1);
            while (score1 < 15 || score2 < 15){
                System.out.println("Your turn!");
                System.out.println(score1);
                score1=scorecounter(opp1map);
                if (question(score1)==true){
            printGrid(opp1map);
            hitShip(compmap, opp1map, score1);
            checkShips(compmap);
            }else{
                System.out.println("Wrong answer!");
            }
            System.out.println("\n"+"\n"+"Computer's turn!");
            AImove(map1, score2, n1, n2, x0, x1,x2,x3,x4,x5,x6,x7,x8,x9,x10,turns, chits, previousmoves);  
            score2=scorecounter(map1);
            }
        }
        else if (choice == 2){
            while (playagain.equalsIgnoreCase("yes")){
            System.out.println("Player 1 deploy ships on the 10x10 grid");
            deployPlayerShips(map1, carrier1, battleship1, cruiser1, submarine1, destroyer1);
            System.out.println("Player 2 deploy ships on the 10x10 grid");
            deployPlayerShips(map2, carrier2, battleship2, cruiser2, submarine2, destroyer2);
            while(score1 != 15 || score2 != 15){
            System.out.print("Player 1's turn ");
            score1=scorecounter(opp1map);
            if(question(score1)==true){
            printGrid(opp1map);
            hitShip(map2, opp1map, score1);
            checkShips(map2);
            if(score1 == 15){
                break;
            }
            }else{
                System.out.println("Wrong answer!");
            }
            System.out.print("Player 2's turn ");
            score1=scorecounter(opp2map);
            if(question(score1)==true){
            printGrid(opp2map);
            hitShip(map1, opp2map, score2);
            checkShips(map1);
            }else{
               System.out.println("Wrong answer!"); 
            }
            }
            if (score1 == 15){
                System.out.println("Player 1 wins!");
            }if(score2 == 15){
                System.out.println("Player 2 wins!");
            }
            System.out.println("Play again?");
            playagain = input.nextLine();
        }   
        }else{
            System.out.println("Please enter '1' or '2'");
        }
        
        }
    }
    public static void printGrid(int[][] grid){ //prints the board grid
    for (int i = 0; i<11; i++){
            for (int j = 0; j<11; j++)
               System.out.print(grid[i][j] + "  ");
            System.out.println();
}

    }
    public static void AImove(int[][]grid, int score, ArrayList<Integer> num1, ArrayList<Integer> num2, ArrayList<Integer> listx0, ArrayList<Integer> listx1, ArrayList<Integer> listx2, ArrayList<Integer> listx3, ArrayList<Integer> listx4, ArrayList<Integer> listx5, ArrayList<Integer> listx6, ArrayList<Integer> listx7, ArrayList<Integer> listx8, ArrayList<Integer> listx9, ArrayList<Integer> listx10, int turn, ArrayList<Integer> comphits, int previous  ){
        int x = 0, y = 0, check = 0,numsran1 = 0,numsran2 = 0;
        ArrayList<Integer> nums = new ArrayList<Integer>();
        nums.add(-1);
        nums.add(0);
        nums.add(1);

               
           while(check == 0){ 
               if(comphits.isEmpty() == false){
                   x = comphits.get(0);
                   y = comphits.get(1);
               }
           if(previous == 1){
              if(grid[comphits.get(turn-1)][comphits.get(turn)] == -1) {
                  previous = 0;
              }
           }if(previous == 2){
              if(grid[comphits.get(turn-1)][comphits.get(turn)] == -2) {
                  previous = 0;
              }
           }if(previous == 3){
              if(grid[comphits.get(turn-1)][comphits.get(turn)] == -3) {
                  previous = 0;
              }
           }if(previous == 4){
              if(grid[comphits.get(turn-1)][comphits.get(turn)] == -4) {
                  previous = 0;
              }
           }if(previous == 5){
              if(grid[comphits.get(turn-1)][comphits.get(turn)] == -5) {
                  previous = 0;
              }
           }
           if (previous == 0){
           x = xran(10); //gets random x coordinate
           y = xran(10); //gets random y coordinate
           }if(previous == 1){
               numsran1 = nums.get((int)xran(2)); //gets random value from -1,0,1 for x to be incrimented by
           if(numsran1 == 0){
               nums.remove(new Integer(0));
               numsran2 = nums.get((int)xran(1));
           }
           else{
               numsran2 = nums.get((int)xran(2));//gets random value from -1,0,1 for y to be incremented by
           } 
           num1.add(numsran1);
           num2.add(numsran2);
           num1.clear();
           num2.clear();
           }if(previous == 2){
               numsran1 = num1.get(0);
               numsran2 = num2.get(0);
           }if(previous > 2){
               numsran1 = -num1.get(0);
               numsran2 = -num1.get(0);
           }
           x = x+numsran1;
           y = y+numsran2;
           if(x == 0){
               if(listx0.contains(y) == false){
                   listx0.add(y);
                   check = 1;
               }
           }
           if(x == 1){
               if(listx1.contains(y) == false){
                   listx1.add(y);
                   check = 1;
               }
           }
           if(x == 2){
               if(listx2.contains(y) == false){
                   listx2.add(y);
                   check = 1;
               }
           }
           if(x == 3){
               if(listx3.contains(y) == false){
                   listx3.add(y);
                   check = 1;
               }
           }
           if(x == 4){
               if(listx4.contains(y) == false){
                   listx4.add(y);
                   check = 1;
               }
           }
           if(x == 5){
               if(listx5.contains(y) == false){
                   listx5.add(y);
                   check = 1;
               }
           }
           if(x == 6){
               if(listx6.contains(y) == false){
                   listx6.add(y);
                   check = 1;
               }
           }
           if(x == 7){
               if(listx7.contains(y) == false){
                   listx7.add(y);
                   check = 1;
               }
           }
           if(x == 8){
               if(listx8.contains(y) == false){
                   listx8.add(y);
                   check = 1;
               }
           }
           if(x == 9){
               if(listx9.contains(y) == false){
                   listx9.add(y);
                   check = 1;
               }
           }
           if(x == 10){
               if(listx10.contains(y) == false){
                   listx10.add(y);
                   check = 1;
               }
           }
           }
           
           if (grid[x][y] != 0){//checks ito see if hit
               System.out.println("Computer has hit your ship at (" + y + ", " + x + ")!");
               grid[x][y] = -grid[x][y];
               score++;
               previous++;
               comphits.add(x);
               comphits.add(y);
           }else{
               System.out.println("Computer has missed at (" + y + ", " + x + ")!");
               if(previous == 1){
                   previous++;
               }else{
               comphits.clear();
               previous = 0;
               }
           }
           turn++;
           
           
           
    }
    public static int yran(int x){//randomizes y value
           int y = (int)(Math.random() * x);
           return y;
    }
    public static int xran(int y){//randimizes x value
           int x = (int)(Math.random() * y);
           return x;
           

    }
    public static void hitShip(int[][]grid, int[][]opp, int s){
        String confirm = "no";
        s=s+1;
        ArrayList<Integer> listxhits = new ArrayList<Integer>();
        ArrayList<Integer> listyhits = new ArrayList<Integer>();
        ArrayList<Integer> listxmiss = new ArrayList<Integer>();
        ArrayList<Integer> listymiss = new ArrayList<Integer>();
        int x = 0,y = 0;
        while(confirm.toLowerCase().equals("no")){
        Scanner input = new Scanner(System.in);
        System.out.println("Please select the x-coordinate you wish to strike: ");
        x = input.nextInt();
        System.out.println("Please select the y-coordinate you wish to strik: ");
        System.out. print("(" + x + ",");
        y = input.nextInt();
        if (y<11 && y>=0 && x<11 && x>=0){
                    System.out.println("Confirm strike on (" + x + "," + y + ")?");
        confirm = input.next();
        }
        }
        y= 10 -y;
        if (grid[y][x] == 0){
            y=-y+10;
            System.out.println("Miss!");
            listxmiss.add(x);
            listymiss.add(y);
            System.out.println("Previous misses:");
            for (int i = 0; i < listxmiss.size(); i++){
            System.out.println("(" + listymiss.get(i) + ", " + listxmiss.get(i) + ")");//shows previous misses
            y=10-y;
            opp[y][x] = 2;
            }
        }else if(grid[y][x] > 0){
            y=-y+10;
            listxhits.add(x);
            listyhits.add(y);
            System.out.println("Hit!");
            //s=s+1;
            System.out.println("Previous hits:");
            for (int i = 0; i < listxhits.size(); i++){
            System.out.println("(" + listxhits.get(i) + ", " + listyhits.get(i) + ")");//shows previous hits
            y=10-y;
            opp[y][x] = 1;
            }
            grid[y][x] = -grid[y][x];
            //printGrid(opp);
        }
        else{
             System.out.println("Please enter valid coordinates");
        
}
        
    
        
       
        
    }
    public static void checkShips(int[][]grid){
      int cruiser = 0, carrier = 0, submarine = 0, battleship = 0, destroyer = 0;
      for (int i = 0; i<11; i++){
        for (int j = 0; j<11; j++){
            if (grid[i][j] == -1){
                carrier++;
            }if (grid[i][j] == -2){
                battleship++;
            }if (grid[i][j] == -3){
                cruiser++;
            }if (grid[i][j] == -4){
                submarine++;
            }if (grid[i][j] == -5){
                destroyer++;
            }       
        }
    }
      if (carrier == 1){
          System.out.println("Opponent's Carrier(1 place) destroyed!");
      }if (battleship == 2){
          System.out.println("Opponent's Battleship(2 places) destroyed!");
      }if (cruiser == 3){
          System.out.println("Opponent's Cruiser(3 places) destroyed!");
      }if (submarine == 4){
          System.out.println("Opponent's Submarine(4 places) destroyed!");
      }if (destroyer == 5){
          System.out.println("Opponent's Destroyer(5 places) destroyed!");
      }
    }
    public static void deployCarrier(int[][]grid, Ship s){
        int x ,y, z = 0;
        while (z == 0){
            x = xran(10); //gets random x coordinate
           y = xran(10); //gets random y coordinate
           if (grid[x][y] == 0){
               grid[x][y] = s.deployShip();
               z = 1;
           }
        }
    }
    public static void generateCoord(Ship s, int multiplier, int[][]grid){
        ArrayList<Integer> nums = new ArrayList<Integer>();
        nums.add(-1);
        nums.add(0);
        nums.add(1);
        int x, y, numsran1, numsran2, x1,y1, z, counter, count, loops = 0, i = 0, cor = 0;
        //int x2,y2,x3,y3,x4,y4;
        ArrayList<Integer> list0 = new ArrayList<Integer>();
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<Integer> list_1 = new ArrayList<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> sub1 = new ArrayList<Integer>();//array list to hold the coordinates of the extended parts of the ship
        ArrayList<Integer> sub2 = new ArrayList<Integer>();
        list0.add(-1);
        list0.add(1);
        list1.add(-1);
        list1.add(0);
        list1.add(1);
        list_1.add(-1);
        list_1.add(0);
        list_1.add(1);
        while (i < multiplier){
               if(loops == 7){
                    list0.add(-1);
                    list0.add(1);
                    list1.add(-1);
                    list1.add(0);
                    list1.add(1);
                    list_1.add(-1);
                    list_1.add(0);
                    list_1.add(1);
                    loops = 0;
               }
           x = xran(10); //gets random x coordinate
           y = xran(10); //gets random y coordinate
           if (grid[x][y] == 0){//checks if initial coordinates of placement are empty
           numsran1 = nums.get((int)xran(2)); //gets random value from -1,0,1 for x to be incrimented by
           if(numsran1 == 0){
               nums.remove(new Integer(0));
               numsran2 = nums.get((int)xran(1));
           }
           else{
               numsran2 = nums.get((int)xran(2));//gets random value from -1,0,1 for y to be incremented by
           } 

            if (numsran1 == 0){
               list0.remove(new Integer(numsran2));
           }
           if (numsran1 == 1){
               list1.remove(new Integer(numsran2));
           }
           if (numsran1 == -1){
               list_1.remove(new Integer(numsran2));
           }
        //}
        //if (extreme == 0){
        list.add(numsran1);
        list.add(numsran2);
        counter = 0;
      

            while (counter < (multiplier-1) && loops != 7){//checks if all spaces between the max end and the intial point are empty and on the grid
            x1 = (x + (multiplier-1)*numsran1) - (list.get(0)*counter);
            y1 = (y + (multiplier-1)*numsran2) - (list.get(1)*counter);
            if (x1 <= 10 && x1 >= 0 && y1 >= 0 && y1 <= 10 && grid[x1][y1] == 0){
                sub1.add(x1);
                sub2.add(y1);
                counter++;
                i++;
            }else{
                list.clear();
                sub1.clear();
                sub2.clear();
                z = 0;
                nums.add(0);
                while (z == 0){
           numsran1 = nums.get((int)xran(2)); //gets random value from -1,0,1 for x to be incrimented by
           if(numsran1 == 0){
               nums.remove(new Integer(0));
               numsran2 = nums.get((int)xran(1));
           }
           else{
               numsran2 = nums.get((int)xran(2));//gets random value from -1,0,1 for y to be incremented by
           } 
                if(numsran1 == 0 && list0.contains(numsran2) == true){
                   list0.remove(new Integer(numsran2));
                   
                }
                if(numsran1 == -1 && list_1.contains(numsran2) == true){
                   list_1.remove(new Integer(numsran2));
                   
                }
                if(numsran1 == 1 && list1.contains(numsran2) == true){
                   list1.remove(new Integer(numsran2));
                   
                }
                z = 1;

                }
                 //makes sure all 9 pairs of -1,0,1 were tried
                loops++;
                counter = 0;
                i = 0;
                list.add(numsran1);
                list.add(numsran2);
                }
                
            }
            
    
            if (i == multiplier-1){
                grid[x][y] = s.deployShip();//Fills those coordinates with a submarine part
            for (count = 0; count < (multiplier-1);){
            grid[sub1.get(cor)][sub2.get(cor)] = s.deployShip();
            cor += 1;
            count++;
            i++;
            }
            }
            //}
        }
    }
    }
    public static void deployPlayerShips(int[][]grid, Carrier ship1, Battleship ship2, Cruiser ship3, Submarine ship4, Destroyer ship5 ){
        Scanner input = new Scanner(System.in);
        int counter = 0;
        int x = 0;
        int y=0;
        while (counter < 5)  {
        System.out.println("Please choose the number of the ship you would like to deploy:" + "\n" + "1- Carrier (1 place)" + "\n" + "2- Battleship (2 places)" + "\n" + "3- Cruiser (3 places)" + "\n" + "4- Submarine (4 places)" + "\n" + "5- Destroyer (5 places}");
        int choice = input.nextInt();
        if (choice == 5){
        for (int i = 0; i < ship5.Fill();){
            for(int z = 0; z < 3;){
            if (z==0){
            System.out.println("Please enter an x coordinate to deploy a part of the ship on:");
             x = input.nextInt() ;    
             if (x<=10 && x >= 0){
               z++;
             }else{
                 System.out.println("Please enter valid coordinates:");
                 }
             if(z==1){
                 System.out.println("Please enter a y coordinate to deploy that part of the ship on:");
                 y = 10-(input.nextInt());
                 if (y<=10 && y>=0){
                     z++;
                 }else{
                       System.out.println("Please enter valid coordinates:");
                 }

             }
             if(z==2){
                 if (grid[y][x]== 0){
                     grid[y][x] = ship5.Fill();
                     i++;
                     System.out.println("Succesfully deployed" + i + "part(s)");
                     z++;
                     printGrid(grid);
                     if (i==ship5.Fill()){
                         counter++;
                         System.out.println("Destroyer deployed");
                         
                     }
                 }else{
                      System.out.println("Please enter an empty coordinate");
                      z=0;
                 }
             }
        }
            }
         
        }
           
        }
                if (choice == 4){
        for (int i = 0; i < ship4.Fill();){
            for(int z = 0; z < 3;){
            if (z==0){
            System.out.println("Please enter an x coordinate to deploy a part of the ship on:");
             x = input.nextInt();  
             if (x<=10 && x >= 0){
               z++;
             }else{
                 System.out.println("Please enter valid coordinates:");
                 }
             if(z==1){
                 System.out.println("Please enter a y coordinate to deploy that part of the ship on:");
                 y = 10 - input.nextInt();
                 if (y<=10 && y>=0){
                     z++;
                 }else{
                       System.out.println("Please enter valid coordinates:");
                 }

             }
             if(z==2){
                 if (grid[y][x]== 0){
                     grid[y][x] = ship4.Fill();
                     i++;
                     System.out.println("Succesfully deployed" + i + "part(s)");
                     z++;
                     printGrid(grid);
                     if (i==ship4.Fill()){
                         counter++;
                         System.out.println("Submarine deployed");
                         
                     }
                 }else{
                    System.out.println("Please enter an empty coordinate");
                    z=0;
                 }
             }
        }
            }

        }
           
        }
                        if (choice == 3){
        for (int i = 0; i < ship3.Fill();){
            for(int z = 0; z < 3;){
            if (z==0){
            System.out.println("Please enter an x coordinate to deploy a part of the ship on:");
             x = input.nextInt();    
             if (x<=10 && x >= 0){
               z++;
             }else{
                 System.out.println("Please enter valid coordinates:");
                 }
             if(z==1){
                 System.out.println("Please enter a y coordinate to deploy that part of the ship on:");
                 y = 10 - input.nextInt();
                 if (y<=10 && y>=0){
                     z++;
                 }else{
                       System.out.println("Please enter valid coordinates:");
                       z=0;
                 }

             }
             if(z==2){
                 if (grid[y][x]== 0){
                     grid[y][x] = ship3.Fill();
                     i++;
                     System.out.println("Succesfully deployed" + i + "part(s)");
                     z++;
                     printGrid(grid);
                     if (i==ship3.Fill()){
                         counter++;
                         System.out.println("Cruiser deployed");
                     }
                 }else{
                     System.out.println("Please enter an empty coordinate");
                     z=0;
                 }
             }
        }
            }
          
        }
           
        }
                                if (choice == 2){
        for (int i = 0; i < ship2.Fill();){
            for(int z = 0; z < 3;){
            if (z==0){
            System.out.println("Please enter an x coordinate to deploy a part of the ship on:");
             x = input.nextInt();    
             if (x<=10 && x >= 0){
               z++;
             }else{
                 System.out.println("Please enter valid coordinates:");
                 }
             if(z==1){
                 System.out.println("Please enter a y coordinate to deploy that part of the ship on:");
                 y = 10 - input.nextInt();
                 if (y<=10 && y>=0){
                     z++;
                 }else{
                       System.out.println("Please enter valid coordinates:");
                 }

             }
             if(z==2){
                 if (grid[y][x]== 0){
                     grid[y][x] = ship2.Fill();
                     i++;
                     System.out.println("Succesfully deployed" + i + "part(s)");
                     z++;
                     printGrid(grid);
                     if (i==ship2.Fill()){
                         counter++;
                         System.out.println("Battleship deployed");
                     }
                 }else{
                     System.out.println("Please enter an empty coordinate");
                     z=0;
                 }
             }
        }
            }
          
        }
           
        }
                                        if (choice == 1){
        for (int i = 0; i < ship1.Fill();){
            for(int z = 0; z < 3;){
            if (z==0){
            System.out.println("Please enter an x coordinate to deploy a part of the ship on:");
             x = input.nextInt();   
             if (x<=10 && x >= 0){
               z++;
             }else{
                 System.out.println("Please enter valid coordinates:");
                 }
             if(z==1){
                 System.out.println("Please enter a y coordinate to deploy that part of the ship on:");
                 y = 10 - input.nextInt();
                 if (y<=10 && y>=0){
                     z++;
                 }else{
                       System.out.println("Please enter valid coordinates:");
                 }

             }
             if(z==2){
                 if (grid[y][x]== 0){
                     grid[y][x] = ship1.Fill();
                     i++;
                     System.out.println("Succesfully deployed" + i + "part(s)");
                     z++;
                     printGrid(grid);
                     if (i==ship1.Fill()){
                         counter++;
                         System.out.println("Carrier deployed");
                     }
                 }else{
                               System.out.println("Please enter an empty coordinate");
                               z=0;
                 }
             }
        }
            }
        }
           
        }
        }
    }
    public static boolean question(int score){
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> coords = new ArrayList<Integer>();
        double solution = -10000, doubleAnswer = -1, solution1, doubleAnswer2;
        int x1,y1,x2,y2,x3,y3,num=-111,den=-111,userNum=-1,userDen=-1,z=0,check=0,userNum2,userDen2,num2,den2;
        String s, stringAnswer, stringAnswer2;

            x1 = xran(11);
            y1 = xran(11);
            while (true){
                x2 = xran(11);
                y2 = xran (11);
                if (x2 != x1 && y2 != y1){
                    break;
                }
            }while (true){
                x3 = xran(11);
                y3 = xran(11);
                    if (x3 != x2 && x3 != x1){
                        if (y3 != y2 & y3 != y1){
                        break;
                        }
                    }
            }
            if(score >= 3 && score < 7){
            num = (y2-y1);
            den = (x2-x1);
            solution = (double)num/den;
            round(solution,2);
            z++;
            System.out.println("Find the GRADIENT between points ("+x1+", "+y1+") and ("+x2+", "+y2+")");
                          
        }
            if(score >= 7 && score < 12){
                System.out.println("Find the DISTANCE between points ("+x1+", "+y1+") and ("+x2+", "+y2+")" +"\n"+ "Please enter the value to the 2nd decimal place");
                num = (y2-y1)*(y2-y1);
                den = (x2-x1)*(x2-x1);
                solution = Math.sqrt(num+den);
                round(solution,2);
                z++;
                
            }
            if(score >= 12){
                System.out.println("Find the equation of the line passing through ("+x1+", "+y1+") and ("+x2+", "+y2+")"+"\n" +"y=mx+c");
                num = (y2-y1);//numerator of gradient
                den = (x2-x1);//denominator of gradient
                solution = (double)num/den;//gradient in decimal form  
            }if(score<3){
                return true;
            }
            solution = round(solution,2);
            if(z==0){
                System.out.print("m= ");
                System.out.println();
            }
            if (input.hasNextDouble()) {
                   doubleAnswer = input.nextDouble();
                   round(doubleAnswer,2);
                } else if (input.hasNextLine()) {
                   stringAnswer = input.nextLine();
                   String[] frac = stringAnswer.split("/");
                   userNum = Integer.parseInt(frac[0]);
                   userDen = Integer.parseInt(frac[1]);
                
            }if(z==0){
                solution1= y1-(solution*x1);//y-intercept in decimal form
                num2 = (y1*den) - (num*x1);//numerator of y-intercept
                den2 = den;//denominator of y-intercept
                System.out.print("c= ");
                if (input.hasNextDouble()) {
                   doubleAnswer2 = input.nextDouble();
                   round(doubleAnswer2,2);
                   if(doubleAnswer2 != solution1){
                       check++;
                   }
                   
                   
                } else if (input.hasNextLine()) {
                   stringAnswer2 = input.nextLine();
                   String[] frac = stringAnswer2.split("/");
                   userNum2 = Integer.parseInt(frac[0]);
                   userDen2 = Integer.parseInt(frac[1]);
                   if (userNum2 != num2 || userDen2 != den2){
                   check++;
                }
                
            }
                
            }
            
            
          if(check == 0){
          if(userNum == num && userDen == den){
                return true;
            }else if(doubleAnswer == solution){
                return true;
            }else{
                return false;
            }
             
    }else{
              return false;
          }
    }
    public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
}
    public static int scorecounter(int grid[][]){
        int score=0;
        for (int i = 0; i<11; i++){
        for (int j = 0; j<11; j++){
            if (grid[i][j] == 1 || grid[i][j] < 0){
                score++;
            
    }
}
     }
         return score;
    }
}



        
  
    


