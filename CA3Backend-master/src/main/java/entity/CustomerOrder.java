/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author alber
 */
@Entity
@Table(name = "orders")
@NamedQueries({
    @NamedQuery(name = "CustomerOrder.findAll", query = "SELECT c FROM CustomerOrder c")
    , @NamedQuery(name = "CustomerOrder.findByIdorders", query = "SELECT c FROM CustomerOrder c WHERE c.idorders = :idorders")
    , @NamedQuery(name = "CustomerOrder.findByOrderdate", query = "SELECT c FROM CustomerOrder c WHERE c.orderdate = :orderdate")})
public class CustomerOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idorders")
    private Integer idorders;
    @Column(name = "orderdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderdate;
    @JoinColumn(name = "userid", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User user;
//    @OneToMany
//    private List<Candy> candy;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderLine> orderlines = new ArrayList();
    
    public CustomerOrder() {
    }

    public CustomerOrder(Integer idorders) {
        this.idorders = idorders;
    }

    public Integer getIdorders() {
        return idorders;
    }

    public void setIdorders(Integer idorders) {
        this.idorders = idorders;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderLine> getOrderlines() {
        return orderlines;
    }

//    public void addOrderline(Candy candy, int weight) {
//        OrderLine orderline = new OrderLine(weight, candy);
//        orderlines.add(orderline);
//    }
    public void addOrderLine(OrderLine ol) {
        this.orderlines.add(ol);
    }
    public void setOrderlines(List<OrderLine> orderlines) {
        this.orderlines = orderlines;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idorders != null ? idorders.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerOrder)) {
            return false;
        }
        CustomerOrder other = (CustomerOrder) object;
        if ((this.idorders == null && other.idorders != null) || (this.idorders != null && !this.idorders.equals(other.idorders))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerOrder[ idorders=" + idorders + " ]";
    }
    
}
