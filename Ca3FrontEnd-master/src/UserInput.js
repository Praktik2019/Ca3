class InputValidation{

   validateInput = (input) => {
        if(input <= 0 || input === ""){
            return false
        } else{
        return true;
        }
    }

}

const inputValidation = new InputValidation();
export default inputValidation;