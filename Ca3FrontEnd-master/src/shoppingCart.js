class ShoppingCart {

    constructor(){
        this.totalWeight = 0;
        this.cart = [];
    }
    getCart = () => {
        return this.cart;
    }
    getWeight = () => {
        return this.totalWeight;
    }
    getPrice = () => {
        return (this.totalWeight / 100) * 15;
    }
    /*
    addToCart = (id, name, weight) => {
        this.cart.push({id, name, weight});
        this.totalWeight += parseInt(weight);
        this.printCart();
    }*/
    addToCart2 = (id, name, weight) => {
        let candy = null;
        for (var i = 0; i < this.cart.length; i++){
            if(this.cart[i].id === id){
                this.cart[i].weight = parseInt(weight) + parseInt(this.cart[i].weight);
                candy = this.cart[i];
            }
        }
        if(candy === null){
            this.cart.push({id, name, weight});
        }
        this.totalWeight += parseInt(weight);
        console.log("Candy added to cart: " + candy);
        return candy;
    }/*
    addToCart3 = (id, name, weight) => {
        if(this.containsItem()){
            this.updateItem(id, 'weight', weight);
        }
        else{
            this.cart.push({id, name, weight});
        }
        return true;
    }
    updateItem = (id, property, value) => {
        let candy = this.cart.find((candy) => {
            return candy.id === id;
        });
        if (candy && candy[property]){
            candy[property] += value;
            return true;
        }
        return false;
    }
    containsItem = (id) => {
        for (var i = 0; i < this.cart.length; i++){
            if(this.cart[i].id === id){
                return true;
            }
        }
        return false;
    }*/
    removeItem = (id) => {
        for(let i = 0; i < this.cart.length; i++){
            if(this.cart[i].id === id){
                this.cart.splice(i);
                console.log("Item removed from cart: " + this.cart[i].id);
            }
        }
    }
    emptyCart = () => {
        this.cart = [];
        this.totalWeight = 0;
    }
    isEmpty = () => {
        if(this.cart.length === 0){
            return true;
        }
        else {
            return false;
        }
    }
    printCart = () => {
        console.log("Total weight: " + this.totalWeight);
        for(let i = 0; i < this.cart.length; i++){
            console.log("Cart Item: " + i + ", ID: " + this.cart[i].id + ", Name: " + this.cart[i].name + ", Weight: " + this.cart[i].weight);
        }
    }
}
const cart = new ShoppingCart();
export default cart;