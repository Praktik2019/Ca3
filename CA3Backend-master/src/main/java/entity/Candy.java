/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alber
 */
@Entity
@Table(name = "candy")
@NamedQueries({
    @NamedQuery(name = "Candy.findAll", query = "SELECT c FROM Candy c")
    , @NamedQuery(name = "Candy.findById", query = "SELECT c FROM Candy c WHERE c.id = :id")
    , @NamedQuery(name = "Candy.findByName", query = "SELECT c FROM Candy c WHERE c.name = :name")
    , @NamedQuery(name = "Candy.findByStock", query = "SELECT c FROM Candy c WHERE c.stock = :stock")
    , @NamedQuery(name = "Candy.findByFlavour", query = "SELECT c FROM Candy c WHERE c.flavour = :flavour")
    , @NamedQuery(name = "Candy.findByType", query = "SELECT c FROM Candy c WHERE c.type = :type")
    , @NamedQuery(name = "Candy.findByImg", query = "SELECT c FROM Candy c WHERE c.img = :img")})
public class Candy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "stock")
    private int stock;
    @Size(max = 45)
    @Column(name = "flavour")
    private String flavour;
    @Size(max = 45)
    @Column(name = "type")
    private String type;
    @Size(max = 45)
    @Column(name = "img")
    private String img;
    @Transient
    private int weight;
    
    public Candy() {
    }

    public Candy(Integer id) {
        this.id = id;
    }

    public Candy(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Candy(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candy)) {
            return false;
        }
        Candy other = (Candy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Candy[ id=" + id + " ]";
    }
    
}
