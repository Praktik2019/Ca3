import React from "react"

  export const ShowError = (props) => {
        return (
            <div className="alert alert-warning" role="alert">
                <strong>Error! </strong>
                {props.message}
            </div>
        )
    }
   export const ShowNeutral = (props) => {
        return (
            <div className="alert alert-info" role="alert">
                <strong>Error! </strong>
                {props.message}
            </div>
        )
    }
   export const ShowSuccess = (props) => {
        return (
            <div className="alert alert-success" role="alert">
                <strong>Success! </strong>
                {props.message}
            </div>
        )
    }
