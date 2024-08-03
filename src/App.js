import React from 'react';
import './App.css';
import Login from './components/login/Login';
import VeiculosList from './components/veiculos/VeiculosList';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';


function App() {
    return (
      <Router>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/veiculos" element={<VeiculosList />} />
        </Routes>
      </Router>
    );
}

export default App;
