import React from 'react';
     import ProductList from './components/ProductList';
     import Login from './components/Login';
     import OrderForm from './components/OrderForm';
     import PaymentForm from './components/PaymentForm';
     import './App.css';

     function App() {
         return (
             <div className="App">
                 <h1>Pet Food App</h1>
                 <Login />
                 <ProductList />
                 <OrderForm />
                 <PaymentForm />
             </div>
         );
     }

     export default App;