import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Login.css';

const Login = () => {
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleLogin = async (event) => {
        event.preventDefault();
        setLoading(true);
        setError('');

        try {   
            const response = await axios.post('http://localhost:8080/auth/login', {
                email,
                senha,
            }, {
                headers: {
                    'Content-Type': 'application/json',
                }
            });

            console.log('Login bem-sucedido:', response.data);
            localStorage.setItem('accessToken', response.data.accessToken);
            navigate('/veiculos');
        } catch (error) {
            if (error.response) {
                setError(`Erro: ${error.response.data.message || 'Falha na autenticação.'}`);
            } else {
                setError('Erro ao conectar com o servidor. Tente novamente mais tarde.');
            }
            console.error('Erro de login:', error.response ? error.response.data : error.message);
        } finally {
            setLoading(false);
        }
    };

    const navigateTo = (path) => {
        navigate(path);
    };

    return (
        <div className="login-container">
            <div className="image-section">
                <img src='https://veiculos-images-verzel.s3.us-west-2.amazonaws.com/logo-verzel.png' alt="Logo" className='image-login' />
            </div>
            <div className="form-section-login">
                <h2>Digite o seu e-mail e senha:</h2>
                <form onSubmit={handleLogin}>
                    <div className="form-group-login">
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
                    <div className="form-group-login">
                        <label htmlFor="senha">Senha: </label>
                        <input
                            type="password"
                            id="senha"
                            value={senha}
                            placeholder='password'
                            onChange={(e) => setSenha(e.target.value)}
                            required
                        />
                    </div>
                    <div className="link-buttons">
                        <button type="button" onClick={() => navigateTo('/criar-conta')} className="link-button">Criar conta</button>
                        <button type="button" onClick={() => navigateTo('/forgot-password')} className="link-button">Esqueceu senha?</button>
                    </div>
                    {error && <p className="error-message">{error}</p>}
                    <button type="submit" disabled={loading} className='button-login'>
                        {loading ? 'Carregando...' : 'ENTRAR'}
                    </button>
                </form>
            </div>
        </div>
    );
};

export default Login;
