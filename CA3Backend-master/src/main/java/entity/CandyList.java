/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author Mads
 */
public class CandyList {
    private ArrayList<Candy> candy;
    
    public CandyList() {
    }

    public ArrayList<Candy> getCandy() {
        return candy;
    }

    public void setCandy(ArrayList<Candy> candy) {
        this.candy = candy;
    }
  
}
