/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Mads
 */
public class CandyDTO {
    private int id;
    private int weight;

    public CandyDTO() {
    }

    public CandyDTO(Candy c) {
        this.id = c.getId();
        this.weight = c.getWeight();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    
}
