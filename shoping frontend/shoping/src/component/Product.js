import React, { useState,useEffect } from 'react';
import style from '../css/product.module.css'
import {Link, Redirect} from 'react-router-dom'
import { useAuth } from './auth';
import axios from 'axios'

function Product(props){
   const [clicked,setClicked]=useState()
   const [message,setMessage]=useState()
   const [bag,setBag]=useState(false)
   const [gotoBag,setGotoBag]=useState(false)
   const [checked,setChecked]=useState(false)
   const {authTokens} = useAuth();
   const [logged,setLogged]=useState(true)
   const size=props.location.state.item.d.size.split(" ")
   const d=props.location.state.item.d

  useEffect(() => { 
    setLogged(true)
    setBag(false)
    setGotoBag(false)
  },[]);
   //console.log(props)
   console.log(bag)
  // console.log(clicked)
   const wishClick=(param)=>{
       if(checked){
        axios.get("http://localhost:8080/user/logged/wishlist/remove/"+param+"?auth_token="+authTokens)              
        setChecked(false)   
    }
        else if(!authTokens){
           setLogged(false)
        }
        else{
           axios.get("http://localhost:8080/user/logged/wishlist/add/"+param+"?auth_token="+authTokens)
                .then(result=>{
                    setMessage()
                    setChecked(true)
                    console.log("added to wishlist")
                    console.log(result)
                    console.log(param)
                })               
                .catch(error=>{
                    console.log("something went wrong")
                    console.log(param)
                    console.log(error)
                })
                
        }
   }

   const cartClick=(param)=>{
      if(bag){
          setGotoBag(true)
      }

      else if(!clicked){
           setMessage("Please select size")
       }
       else if(!authTokens){
           setLogged(false)
       }
       else{
        axios.get(`http://localhost:8080/user/logged/cart/add/${param}?auth_token=${authTokens}&q=1&s=${clicked}`)
        .then(result=>{
            setBag(true)
            setMessage()
            console.log("added to cart")
        })               
        .catch(error=>{
            console.log("something went wrong")
            console.log(error)
        })
       
       }
   }

   if(gotoBag)
   return (<Redirect to="/cart"/>)

   if(!logged)
   return (<Redirect to={{ pathname: "/login", state: { referer: props.location }}} />)

   return(

     <div className={style.product}>
        <div className={style.imag}>        
            <img key={d.imageId} width="100%" alt="image not loaded" height="360px" src={require(`../static/images/images/${d.gender}/${d.type}/${d.imageId}.jpg`)}/>
        </div>
        <div className={style.imag} >        
            <img key={d.imageId} width="100%" alt="image not loaded" height="360px" src={require(`../static/images/images/${d.gender}/${d.type}/${d.imageId}.jpg`)}/>
        </div>
        <div className={style.details}> 
          {message&&<span className={style.error}>{message}</span>}
             <h1>{d.brand.toUpperCase()}</h1>
             <div className={style.wish} onClick={()=>wishClick(d.productId)}>
                 <button className={` ${style.heart} ${checked? style.checked:""}`}/>
             </div>
             <h3>{d.details}</h3>
             <span style={{fontWeight:"700"}}>Rs.{Math.round( d.price-d.discount*d.price/100)}</span>
             <span  style={{textDecoration:"line-through"}}>Rs.{d.price}</span>
             <span style={{color:"#ff905a"}}>({d.discount}%off)</span>    
             <h4>SELECT SIZE</h4>
             {size.map(d=>{
                 return(
                     
                 <div className={`${style.size} ${d===clicked?style.clicked:""}`} 
                 onClick={()=>{setClicked(d)}}>
                     {d}
                     </div>
                     
               ) })}
               <button onClick={()=>{cartClick(d.productId)}} className={style.bag}>{bag?"GO TO BAG":"ADD TO BAG"}</button>
    
        </div>   
        
        </div>
    
   );

   
}

export default Product;