import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Register.css';

const Register = () => {
    const [email, setEmail] = useState('');
    const [nomeCompleto, setNomeCompleto] = useState('');
    const [senha, setSenha] = useState('');
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleRegister = async (event) => {
        event.preventDefault();
        setLoading(true);
        setError('');

        try {
            const response = await axios.post('http://localhost:8080/register/users', {
                email,
                nomeCompleto,
                senha,
            }, {
                headers: {
                    'Content-Type': 'application/json',
                }
            });

            console.log('Registro bem-sucedido:', response.data);
            navigate('/');
        } catch (error) {
            if (error.response) {
                setError(`Erro: ${error.response.data.message || 'Falha no registro.'}`);
            } else {
                setError('Erro ao conectar com o servidor. Tente novamente mais tarde.');
            }
            console.error('Erro de registro:', error.response ? error.response.data : error.message);
        } finally {
            setLoading(false);
        }
    };

    const handleGoBack = () => {
        navigate('/');
    };

    return (
        <div className="register-container">
            <div className="image-section">
                <img src='https://veiculos-images-verzel.s3.us-west-2.amazonaws.com/logo-verzel.png' alt="Logo" className='image' />
            </div>
            <div className="form-section">
                <h2>Registrar-se</h2>
                <form onSubmit={handleRegister}>
                    <div className="form-group">
                        <label htmlFor="nomeCompleto">Nome: </label>
                        <input
                            type="text"
                            id="nomeCompleto"
                            placeholder='Seu nome completo'
                            value={nomeCompleto}
                            onChange={(e) => setNomeCompleto(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="email">Email: </label>
                        <input
                            type="email"
                            id="email"
                            placeholder='exemplo@gmail.com'
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="senha">Senha: </label>
                        <input
                            type="password"
                            id="senha"
                            placeholder='password'
                            value={senha}
                            onChange={(e) => setSenha(e.target.value)}
                            required
                        />
                    </div>
                    {error && <p className="error-message">{error}</p>}
                    <button type="submit" disabled={loading}>
                        {loading ? 'Carregando...' : 'REGISTRAR'}
                    </button>
                </form>
                <button onClick={handleGoBack} className="back-button">
                    Voltar
                </button>
            </div>
        </div>
    );
};

export default Register;
