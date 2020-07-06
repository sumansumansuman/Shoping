import React,{useState, useEffect} from "react"
import axios from 'axios'
import SideNav from './SideNav'
import HeartCheckbox from 'react-heart-checkbox'
import style from '../css/nav.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHeart } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom"


function Category(props){   
  const [data, setData] = useState([]);
  const [brand,setBrand]=useState([]);
  const[profile,setProfile]=useState();
  const item=props.match.params.name
  const search=props.location.search
  console.log(search+"  ***")
  
  console.log(props.match.params.name)
  useEffect(() => { 
    getResponse();
  },[]);
    
  const wishEvent=((event)=>{
     event.target.className={}
  })

    const getResponse= async ()=>{
      const result = await axios(
        'http://localhost:8080/api/product/clothing/'+item+search)
        setData(result.data)
        const brandFetch=await axios('http://localhost:8080/api/product/brand/'+item)
        setBrand(brandFetch.data)
         axios.get('http://localhost:8080/user/logged/profile?auth_token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhQGMuY29tIiwic2NvcGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpc3MiOiJodHRwczovL2RldmdsYW4uY29tIiwiaWF0IjoxNTkxNjQ2NTg2LCJleHAiOjE1OTE2NjQ1ODZ9.k-mGD4dY0kqYrBEgtt_15PtMQc1fsZ7b_xxOjATDJF4')
         .then(result=>{setProfile(result); console.log(result);})
         .catch(error=>{console.error()}
        )
    };

 
    

 console.log(props)
 console.log(brand)

  return (
    <div className={style.gridContainer3}>
  
    <SideNav brand={brand}/>
    <div className={style.gridContainer} style={{borderTop:"1px solid  rgba(0,0,0,0.15)"}}>
    
    {
        data&&data.map(d=>{
          return(
            <div className={style.card}>
             <Link to=
             {{pathname:`/product/${d.productId}`,state:{item:{d}}}}
              style={{textDecoration:"none"}} >
            <img key={d.imageId} width="100%" alt="image not loaded" height="260px" src={require(`../static/images/images/${d.gender}/${d.type}/${d.imageId}.jpg`)}/>
             <div className={style.cardText}> 
             <h4>{d.brand}</h4>
             <h6>{d.details}</h6>
             <span  style={{textDecoration:"line-through"}}>Rs.{d.price}</span>
             <span style={{color:"#ff905a"}}>({d.discount}%off)</span>
             <div className={style.gridContainer2}>     
             <h5>Rs.{Math.round( d.price-d.discount*d.price/100)}</h5>
             </div>    
        </div>
       </Link>
        </div>
         )
        }
      )
    }
    </div>
    </div>
  )
}
export default Category;