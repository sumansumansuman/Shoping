import React from 'react';
import SimpleImageSlider from "react-simple-image-slider";
import '../App.css';
import style from '../css/nav.module.css'
import {Link} from 'react-router-dom'

function Home(){
    const images=[]
    for(let i=1;i<=4;i++){
        images.push({url:require(`../static/images/homeslider${i}.webp`)})
    }
 
    
   
   return(
      <div>
          <div className="div-space"/>
          <div className="div-space"/>
          <div style={{zIndex:"0"}} >
        <SimpleImageSlider
                    width={1080}
                    height={250}
                    images={images}
                />
            </div>
        <div className="div-space"/>
        <div className={style.gridContainer}>
          <Link className={style.links} to="/">
           <img src={require('../static/images/499Store.jpg')} width="100%" height="110px"/>
          </Link>   
          <Link className={style.links} to="/">
           <img src={require('../static/images/999Store.jpg')} width="100%" height="110px"/>
          </Link>
           <Link className={style.links} to="/">
           <img src={require('../static/images/1499Store.jpg')} width="100%" height="110px"/>
          </Link>
        </div>
        <div className="div-space"/>
        <div className="div-space"/>
        <h2 className={style.homeP}>#Categories</h2>
        <div className={style.gridContainer1}>
        <div className={style.card}>
            <Link className={style.links} to="/category/kurti?f=Women">
            <img src={require("../static/images/kurti.jpg")} height="160px" width="100%"/>
            <div><h4 style={{backgroundColor:"#f1f1f1"}}>Kurti</h4></div>
            </Link>
        </div>
        <div className={style.card}>
            <Link className={style.links} to="/category/shirt?f=Men">
            <img src={require("../static/images/Shirts.jpg")} height="160px" width="100%"/>
            <div ><h4 style={{backgroundColor:"#f1f1f1"}}>Shirts</h4></div>
            </Link>
        </div><div className={style.card}> 
            <Link className={style.links} to="/category/Tshirt?f=Men">
            <img src={require("../static/images/Tshirts.jpg")} height="160px" width="100%"/>
            <h4 style={{backgroundColor:"#f1f1f1"}}>Tshirts</h4>
            </Link>
        </div><div className={style.card}>
            <Link className={style.links} to="/category/shoes?f=Men">
            <img src={require("../static/images/Men's Shoes.jpg")} height="160px" width="100%"/>
            <div><h4 style={{backgroundColor:"#f1f1f1"}}>Shoes Men </h4></div>
            </Link>
        </div><div className={style.card}>
            <Link className={style.links} to="/category/shoes?f=Women">
            <img src={require("../static/images/Women's shoes.jpg")} height="160px" width="100%"/>
            <div><h4 style={{backgroundColor:"#f1f1f1"}}>Shoes Women</h4></div>
            </Link>
        </div><div className={style.card}>
            <Link className={style.links} to="/category/saree">
            <img src={require("../static/images/sarees.webp")} height="160px" width="100%"/>
            <div><h4 style={{backgroundColor:"#f1f1f1"}}>Saree</h4></div>
            </Link>
        </div><div className={style.card}>
            <Link className={style.links} to="/category/jeans?f=Men">
            <img src={require("../static/images/Men's Jeans.jpg")} height="160px" width="100%"/>
            <div><h4 style={{backgroundColor:"#f1f1f1"}}>Jeans Men</h4></div>
            </Link>
         </div><div className={style.card}>
            <Link className={style.links} to="/category/jeans?f=Women">
            <img src={require("../static/images/Women's jeans.webp")} height="160px" width="100%"/>
            <div><h4 style={{backgroundColor:"#f1f1f1"}}>Jeans Women</h4></div>
            </Link>
        </div>
        </div>
      </div>
      

                
    );

   
}

export default Home;