/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animal_game_group1;

/**
 *
 * @author Luky
 */
public class Animal_Game_Group1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AGView agview=new AGView("AnimalChess");
        AGModel agmodel=new AGModel();
        AGControl agcontrol=new AGControl(agview, agmodel);
        agmodel.addObserver(agview);
        agview.setVisible(true);
        
    }
    
}
