import React from 'react';
import './App.css';
import Login from './components/login/Login';
import VeiculosList from './components/veiculos/VeiculosList';
import Header from './components/header/Header';
import Footer from './components/footer/Footer';
import Veiculo from './components/veiculo/Veiculo';
import Editar from './components/editar/Editar';
import Cadastrar from './components/cadastrar/Cadastrar';
import ForgotPassword from './components/user/send-email/ForgotPassword ';
import ResetPassword from './components/user/register-password/ResetPassword';
import Register from './components/user/conta/Registrer';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';


function App() {
    return (
      <Router>
        <Header />
        <div className="App">
            <Routes>
                <Route path='/cadastrar' element={<Cadastrar />} />
                <Route path="/" element={<Login />} />
                <Route path="/veiculos" element={<VeiculosList />} />
                <Route path="/veiculos/:id" element={<Veiculo />} />
                <Route path='/veiculos/editar/:id' element={<Editar />} />
                <Route path='/criar-conta/' element={<Register />} />
                <Route path="/forgot-password" element={<ForgotPassword />} />
                <Route path="/reset-password" element={<ResetPassword />} />
            </Routes>
        </div>
        <Footer />
      </Router>
    );
}

export default App;
