import React, { useState } from 'react';
     import axios from 'axios';

     const OrderForm = () => {
         const [userId, setUserId] = useState('');
         const [productId, setProductId] = useState('');
         const [quantity, setQuantity] = useState(1);
         const [message, setMessage] = useState('');

         const handleSubmit = async (e) => {
             e.preventDefault();
             try {
                 const response = await axios.post('http://localhost:8083/api/orders', {
                     userId,
                     items: [{ productId, quantity }]
                 });
                 setMessage(`Pedido criado com sucesso! Order ID: ${response.data.id}`);
             } catch (error) {
                 setMessage('Erro ao criar pedido: ' + (error.response?.data?.message || error.message));
             }
         };

         return (
             <div>
                 <h2>Criar Pedido</h2>
                 <form onSubmit={handleSubmit}>
                     <div>
                         <label>ID do Usuário:</label>
                         <input
                             type="text"
                             value={userId}
                             onChange={(e) => setUserId(e.target.value)}
                             required
                         />
                     </div>
                     <div>
                         <label>ID do Produto:</label>
                         <input
                             type="text"
                             value={productId}
                             onChange={(e) => setProductId(e.target.value)}
                             required
                         />
                     </div>
                     <div>
                         <label>Quantidade:</label>
                         <input
                             type="number"
                             value={quantity}
                             onChange={(e) => setQuantity(parseInt(e.target.value))}
                             min="1"
                             required
                         />
                     </div>
                     <button type="submit">Criar Pedido</button>
                 </form>
                 {message && <p>{message}</p>}
             </div>
         );
     };

     export default OrderForm;