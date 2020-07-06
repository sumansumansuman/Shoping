import React,{useState,useEffect} from 'react';
import axios from 'axios'
import {useAuth} from './auth'

function WishList(){
   
   const {authTokens}=useAuth()
   const [data,setData]=useState([])

   useEffect(() => { 
      getResponse();
    },[]);

   
   const getResponse= async ()=>{
      const result = await axios(
        'http://localhost:8080/user/logged/wishlist?auth_token='+authTokens).catch(error=>{
               console.log("error:"+error)
        }).then(result=>{
         setData(result.data);
         console.log(result.data);
         } ).catch(error=>{
            console.error();
            
         } )   
       
       
    };

   return(
      <div>
         {data&&data.map(d=>{
         return   <h1 key={`${d.productId}${d.quantity}`}>{d.productId}</h1>
         })
      }
      </div>
   );

   
}

export default WishList;