import React, { useEffect,useState } from 'react';
import {useAuth} from './auth'
import axios from 'axios'
import style from '../css/cart.module.css'
import WishList from './Wishlist';


function Cart(){
      const {authTokens}=useAuth();
      const [cart,setCart]=useState()
      const [total,setTotal]=useState(0)
      const [actual,setActual]=useState(0)


      const Billing=(cart)=>{
         console.log("Billing called atleast")
            if(cart){
                cart.map(c=>{
                   setActual(prevState=>prevState+c.product.price)
                   const act=Math.round(c.product.price-c.product.discount*c.product.price/100)
                   console.log(actual)
                   console.log(act)
                   setTotal(prevState=>prevState+act)
                })

               }
            else{
               console.log("cart not found")
            }
      }

      const wishlist=(item)=>{
         axios.get("http://localhost:8080/user/logged/wishlist/add/"+item.product.productId+"?auth_token="+authTokens)              
         .then(result=>{
            remove(item)
         }) 
      }

      const sizeChange=(size,item)=>{
         console.log(item)
         console.log(size)
         axios.post("http://localhost:8080/user/logged/cart/update/?auth_token="+authTokens+"&s="+size,item)
         .catch(error=>{
            console.log("error in updating size")
         })              
      
      }

      const itemCountChange=(q,item)=>{
         axios.post("http://localhost:8080/user/logged/cart/update/?auth_token="+authTokens+"&q="+q,item)
         .catch(error=>{
            console.log("error in updating quantity")
         })      
      }
       
      const remove=(item)=>{
         console.log(item)
         axios.post(`http://localhost:8080/user/logged/cart/remove?auth_token=${authTokens}`,item)
         .then(result=>{
             console.log(result)
             
         })
         .catch(error=>{
            console.log(error)
         })
      
         
         
      }

      useEffect(()=>{
         axios.get(`http://localhost:8080/user/logged/cart?auth_token=${authTokens}`)
         .then(result=>{
            console.log(result)
            setCart(result.data)
            Billing(result.data);
         })
         .catch(error=>{
            console.log(error)
         })
         
      },[])
   
   return(
      <div className={style.cart} >
        <div className={style.products}>
        {
        cart&&cart.map(d=>{
          return(
             <div className={style.outer}>
             <div className={style.item}>
                <div className={style.image}>
                <img alt="not found" width="100%" height="150vh" src={require(`../static/images/images/${d.product.gender}/${d.product.type}/${d.product.imageId}.jpg`)}/>
                </div>
                <div className={style.cartDetails}>
                <div className={style.itemText}>
                 <h3>{d.product.brand.toUpperCase()}</h3>
                 <p>{d.product.details}</p>
                 <span style={{fontWeight:"700"}}>Rs.{Math.round( d.product.price-d.product.discount*d.product.price/100)}</span>
                 <span  style={{textDecoration:"line-through"}}>Rs.{d.product.price}</span>
                 <span style={{color:"#ff905a"}}>({d.product.discount}%off)</span>             
                </div>
                  <div className={style.dropdown}>
                  <select  onChange={(e)=>{sizeChange(e.target.value,d)}} defaultValue={d.size} id="size" name="size">
  
                     {
                        d.product.size.trim().split(" ").map(s=>{
                           return(
                           <option  value={s}>Size: {s}</option>
                           )
                        })
                     }
                     </select>
                     <select onChange={(e)=>{itemCountChange(e.target.value,d)}} id="quantity" defaultValue={d.quantity} name="size">
  
                    {
                     [...Array(6)].map((q,i)=>{
                        return(
                        <option  value={i+1}>Quantity: {i+1}</option>
                        )
                         })
                    }
                        </select>
                  </div>
                </div>
             </div>
             <div className={style.btn}> 
                 <button onClick={()=>{remove(d)}} style={{marginRight:"250px"}}>REMOVE</button>
                 <button onClick={()=>{wishlist(d)}} >MOVE TO WISHLIST</button>
             </div>
             </div>
         )
        }
      )
    }
        
        
  </div>
         <div className={style.bill} >
              <h2>BILL DETAILS</h2>
              <h3>Bag Total       <span>Rs.{actual}</span></h3>
              <h3>Discount        <span style={{color:"#03a685"}}>-{actual-total}</span></h3>
              <h3>Order Total     <span>Rs.{total}</span></h3>
              <h3>Delievery Charge<span style={{color:"#03a685"}}><span style={{textDecoration:"line-through",color:"#382c3f"}}>Rs.49 </span> FREE</span>  </h3>
              <h1>Total <span>Rs. {total}</span> </h1>
              <button className={style.cardTextLogin}>PLACE ORDER</button>
         </div>

        
      </div>
   );

   
}

export default Cart;