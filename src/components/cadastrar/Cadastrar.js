import React, { useState } from 'react';
import axios from 'axios';
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { useNavigate } from 'react-router-dom';
import './../editar/Editar.css';

const Cadastrar = () => {
    const [nome, setNome] = useState('');
    const [marca, setMarca] = useState('');
    const [modelo, setModelo] = useState('');
    const [valor, setValor] = useState('');
    const [foto, setFoto] = useState(null);
    const [fotoPreview, setFotoPreview] = useState(null);
    const navigate = useNavigate();
    const token = localStorage.getItem('accessToken');

    const handleFileChange = (e) => {
        const file = e.target.files[0];
        setFoto(file);

        if (file) {
            setFotoPreview(URL.createObjectURL(file));
        } else {
            setFotoPreview(null);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append('nome', nome);
        formData.append('marca', marca);
        formData.append('modelo', modelo);
        formData.append('valor', valor);
        if (foto) formData.append('foto', foto);

        try {
            await axios.post('http://localhost:8080/api/veiculos/admin/v1', formData, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'multipart/form-data'
                }
            });
            navigate('/veiculos');
        } catch (error) {
            console.error('Erro ao cadastrar veículo', error);
        }
    };

    return (
        <div className="editar">
            <h1>Cadastrar Veículo</h1>
            <div className="container">
                <div className="info">
                    <div className="foto-preview">
                        {fotoPreview && <img src={fotoPreview} alt="Pré-visualização" />}
                    </div>
                    <div className="info-details">
                        <p className='nome'><strong></strong> {nome}</p>
                        <p className='marca'><strong></strong> {marca}</p>
                        <p className='modelo'><strong></strong> {modelo}</p>
                        <p className='valor'><strong></strong> R$ {valor}</p>
                    </div>
                    <div className='nav-buttons'>
                        <AiOutlineArrowLeft color='white' size={26} /> 
                        <button onClick={() => navigate('/veiculos')} className='edit-button'>Voltar</button>
                    </div>
                </div>
                <form onSubmit={handleSubmit} className="form">
                    <label>
                        Nome:
                        <input
                            type="text"
                            value={nome}
                            onChange={(e) => setNome(e.target.value)}
                            required
                        />
                    </label>
                    <label>
                        Marca:
                        <input
                            type="text"
                            value={marca}
                            onChange={(e) => setMarca(e.target.value)}
                            required
                        />
                    </label>
                    <label>
                        Modelo:
                        <input
                            type="text"
                            value={modelo}
                            onChange={(e) => setModelo(e.target.value)}
                            required
                        />
                    </label>
                    <label>
                        Valor:
                        <input
                            type="number"
                            value={valor}
                            onChange={(e) => setValor(e.target.value)}
                            required
                        />
                    </label>
                    <label>
                        Foto:
                        <input
                            type="file"
                            accept="image/*"
                            onChange={handleFileChange}
                        />
                    </label>
                    <button type="submit" className='edit-salvar'>Cadastrar</button>
                </form>
            </div>
        </div>
    );
};

export default Cadastrar;
