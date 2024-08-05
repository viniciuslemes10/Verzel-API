import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './ResetPassword.css';

const ResetPassword = () => {
    const [email, setEmail] = useState('');
    const [code, setCode] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleResetPassword = async (event) => {
        event.preventDefault();
        setLoading(true);
        setError('');
        setSuccess('');

        try {
            const response = await axios.post('http://localhost:8080/register/users/redefinir-senha', {
                email,
                newPassword,
                code,
            }, {
                headers: {
                    'Content-Type': 'application/json',
                }
            });

            setSuccess('Senha redefinida com sucesso!');
            navigate('/')
        } catch (error) {
            if (error.response) {
                setError(`Erro: ${error.response.data.message || 'Falha ao redefinir a senha.'}`);
            } else {
                setError('Erro ao conectar com o servidor. Tente novamente mais tarde.');
            }
            console.error('Erro ao redefinir senha:', error.response ? error.response.data : error.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="reset-password-container">
            <div className="image-section">
                <img src='https://veiculos-images-verzel.s3.us-west-2.amazonaws.com/logo-verzel.png' alt="Logo" className='image' />
            </div>
            <div className="form-section">
                <h2>Redefinir Senha</h2>
                <form onSubmit={handleResetPassword}>
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
                        <label htmlFor="code">Código: </label>
                        <input
                            type="text"
                            id="code"
                            placeholder='Código de recuperação'
                            value={code}
                            onChange={(e) => setCode(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="newPassword">Senha: </label>
                        <input
                            type="password"
                            id="newPassword"
                            placeholder='Nova senha'
                            value={newPassword}
                            onChange={(e) => setNewPassword(e.target.value)}
                            required
                        />
                    </div>
                    {error && <p className="error-message">{error}</p>}
                    {success && <p className="success-message">{success}</p>}
                    <button type="submit" disabled={loading}>
                        {loading ? 'Carregando...' : 'Redefinir Senha'}
                    </button>
                </form>
            </div>
        </div>
    );
};

export default ResetPassword;
