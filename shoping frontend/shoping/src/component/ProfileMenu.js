import React, { useState, useEffect } from 'react'
import style from '../css/nav.module.css'
import {Link} from 'react-router-dom'
import {useAuth} from './auth'
import Axios from 'axios'
function Profilemenu(){
  const {setAuthTokens,authTokens,logData}=useAuth()
  const logOut=(()=>{
      Axios.post("http://localhost:8080/logout").then(()=>{
            setAuthTokens()
      }).catch((error)=>{
        console.log("error in log out"+error);     
      })
  })

  console.log("in profile menu")
  console.log(logData)
  console.log(authTokens)

    return(
         
     <div style={{marginTop:"5px"}} className={style.menuCard} 
    
        >
         { !authTokens&& <div  >
               <h4 >Welcome</h4>
               <span>Login to view account and manage order</span>
               <Link to="/login" className={style.cardTextLogin}>LOGIN/SIGNUP</Link>
           </div>}
           {logData&&<div>
               <h4 >Welcome {logData.name}</h4>
           <span>{logData.email}</span>
             </div>}
           <div style={{border:"none"}}>
           <Link className={style.menuCardLink}>Order</Link>
           <Link className={style.menuCardLink}>Wishlist</Link>
           <Link className={style.menuCardLink}>Address</Link>
           <Link className={style.menuCardLink}>Contact Us</Link>
            {authTokens&&<Link onClick={logOut} className={style.menuCardLink}>logout</Link>} 
           </div>
        </div>
      
    ); 
}

export default Profilemenu;