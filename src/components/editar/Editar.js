import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import './Editar.css';

const Editar = () => {
    const { id } = useParams();
    const [veiculo, setVeiculo] = useState(null);
    const [nome, setNome] = useState('');
    const [marca, setMarca] = useState('');
    const [modelo, setModelo] = useState('');
    const [valor, setValor] = useState('');
    const [foto, setFoto] = useState(null);
    const [fotoPreview, setFotoPreview] = useState(null);
    const navigate = useNavigate();
    const token = localStorage.getItem('accessToken');

    useEffect(() => {
        const fetchVeiculo = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/veiculos/public/v1/${id}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    }
                });
                const { nome, marca, modelo, valor, foto } = response.data;
                setVeiculo(response.data);
                setNome(nome);
                setMarca(marca);
                setModelo(modelo);
                setValor(valor);
                setFotoPreview(foto);
            } catch (error) {
                console.error('Erro ao carregar detalhes do veículo', error);
            }
        };

        fetchVeiculo();
    }, [id, token]);

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
        formData.append('id', id);
        formData.append('nome', nome);
        formData.append('marca', marca);
        formData.append('modelo', modelo);
        formData.append('valor', valor);
        if (foto) formData.append('foto', foto);

        try {
            await axios.put('http://localhost:8080/api/veiculos/admin/v1', formData, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'multipart/form-data'
                }
            });
            navigate('/veiculos');
        } catch (error) {
            console.error('Erro ao atualizar veículo', error);
        }
    };

    const handleBackClick = () => {
        navigate(`/veiculos/${id}`);
    };

    if (!veiculo) return <p>Carregando...</p>;

    return (
        <div className="editar">
            <h1>Editar Veículo</h1>
            <div className="container">
                <div className="info">
                    <div className="foto-preview">
                        {fotoPreview && <img src={fotoPreview} alt="Pré-visualização" />}
                    </div>
                    <div className="info-details">
                        <p><strong>Nome:</strong> {nome}</p>
                        <p><strong>Marca:</strong> {marca}</p>
                        <p><strong>Modelo:</strong> {modelo}</p>
                        <p><strong>Valor:</strong> {valor}</p>
                        <button onClick={handleBackClick}>Voltar</button>
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
                    <button type="submit">Salvar</button>
                </form>
            </div>
        </div>
    );
};

export default Editar;
