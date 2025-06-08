import React, { useState } from 'react';
     import axios from 'axios';

     const Login = () => {
         const [email, setEmail] = useState('');
         const [password, setPassword] = useState('');
         const [message, setMessage] = useState('');

         const handleSubmit = async (e) => {
             e.preventDefault();
             try {
                 const response = await axios.post('http://localhost:8082/api/users/login', {
                     email,
                     password
                 });
                 setMessage(`Login bem-sucedido! User ID: ${response.data.userId}`);
             } catch (error) {
                 setMessage('Erro ao fazer login: ' + (error.response?.data?.message || error.message));
             }
         };

         return (
             <div>
                 <h2>Login</h2>
                 <form onSubmit={handleSubmit}>
                     <div>
                         <label>Email:</label>
                         <input
                             type="email"
                             value={email}
                             onChange={(e) => setEmail(e.target.value)}
                             required
                         />
                     </div>
                     <div>
                         <label>Senha:</label>
                         <input
                             type="password"
                             value={password}
                             onChange={(e) => setPassword(e.target.value)}
                             required
                         />
                     </div>
                     <button type="submit">Entrar</button>
                 </form>
                 {message && <p>{message}</p>}
             </div>
         );
     };

     export default Login;