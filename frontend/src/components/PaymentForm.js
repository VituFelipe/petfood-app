import React, { useState } from 'react';
     import axios from 'axios';

     const PaymentForm = () => {
         const [orderId, setOrderId] = useState('');
         const [userId, setUserId] = useState('');
         const [amount, setAmount] = useState('');
         const [message, setMessage] = useState('');

         const handleSubmit = async (e) => {
             e.preventDefault();
             try {
                 const response = await axios.post('http://localhost:8084/api/payments', {
                     orderId,
                     userId,
                     amount: parseFloat(amount)
                 });
                 setMessage(`Pagamento processado com sucesso! Payment ID: ${response.data.id}`);
             } catch (error) {
                 setMessage('Erro ao processar pagamento: ' + (error.response?.data?.message || error.message));
             }
         };

         return (
             <div>
                 <h2>Processar Pagamento</h2>
                 <form onSubmit={handleSubmit}>
                     <div>
                         <label>ID do Pedido:</label>
                         <input
                             type="text"
                             value={orderId}
                             onChange={(e) => setOrderId(e.target.value)}
                             required
                         />
                     </div>
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
                         <label>Valor:</label>
                         <input
                             type="number"
                             value={amount}
                             onChange={(e) => setAmount(e.target.value)}
                             step="0.01"
                             required
                         />
                     </div>
                     <button type="submit">Pagar</button>
                 </form>
                 {message && <p>{message}</p>}
             </div>
         );
     };

     export default PaymentForm;