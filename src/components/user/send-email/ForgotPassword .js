import React, { useState } from 'react';
import axios from 'axios';
import './ForgotPassword.css';
import { useNavigate } from 'react-router-dom';

const ForgotPassword = () => {
    const [email, setEmail] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleSendCode = async (event) => {
        event.preventDefault();
        setLoading(true);
        setError('');
        setSuccess('');

        try {
            const response = await axios.post('http://localhost:8080/register/users/enviar-codigo', {
                email
            }, {
                headers: {
                    'Content-Type': 'application/json',
                }
            });

            setSuccess(response.data);
            setTimeout(() => {
                navigate('/reset-password');
            }, 1000);
        } catch (error) {
            if (error.response) {
                setError(`Erro: ${error.response.data.message || 'Falha ao enviar o código.'}`);
            } else {
                setError('Erro ao conectar com o servidor. Tente novamente mais tarde.');
            }
            console.error('Erro ao enviar código:', error.response ? error.response.data : error.message);
        } finally {
            setLoading(false);
        }
    };

    const handleGoBack = () => {
        navigate('/');
    };

    return (
        <div className="forgot-password-container">
            <div className="image-section">
                <img src='https://veiculos-images-verzel.s3.us-west-2.amazonaws.com/logo-verzel.png' alt="Logo" className='image' />
            </div>
            <div className="form-section">
                <h2>Esqueceu a Senha?</h2>
                <form onSubmit={handleSendCode}>
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
                    {error && <p className="error-message">{error}</p>}
                    {success && <p className="success-message">{success}</p>}
                    <button type="submit" disabled={loading}>
                        {loading ? 'Carregando...' : 'Enviar Código'}
                    </button>
                </form>
                <button onClick={handleGoBack} className="back-button">
                    Voltar
                </button>
            </div>
        </div>
    );
};

export default ForgotPassword;
