import React from 'react'
import style from '../css/filter.module.css'

function SideNav(props){
    console.log("pyhfhf")
    console.log(props)
    let rows=[]
    for (let i = 0; i < props.brand.length; i++) {
        rows.push(<div key={i}>
            <input  type="checkbox" id={i}/>
            <label  htmlFor={i}>{props.brand[i]}</label>
            </div> );
        
    }

    return(
        <div className={style.sideDiv}>
           <div className={style.Brand}>
               <h4 style={{padding:"0 0 18px"}}>BRAND</h4>     
                {rows}           
           </div>
        </div>
    )
        
    
}

export default SideNav;