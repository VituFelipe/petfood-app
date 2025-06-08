import React, { useEffect, useState } from 'react';
     import axios from 'axios';

     const ProductList = () => {
         const [products, setProducts] = useState([]);
         const [error, setError] = useState(null);

         useEffect(() => {
             axios.get('http://localhost:8081/api/products')
                 .then(response => setProducts(response.data))
                 .catch(error => {
                     console.error('Erro ao buscar produtos:', error);
                     setError('Falha ao carregar produtos.');
                 });
         }, []);

         if (error) {
             return <div>{error}</div>;
         }

         return (
             <div>
                 <h2>Produtos</h2>
                 <ul>
                     {products.map(product => (
                         <li key={product.id}>
                             {product.name} - R${product.price.toFixed(2)} ({product.type})
                         </li>
                     ))}
                 </ul>
             </div>
         );
     };

     export default ProductList;