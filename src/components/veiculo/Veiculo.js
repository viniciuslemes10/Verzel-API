import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import { FaCar } from 'react-icons/fa';
import { MdCheckCircle } from 'react-icons/md';
import { AiOutlineArrowLeft, AiOutlineArrowRight } from 'react-icons/ai';
import './Veiculo.css';
import logoVerzel from './logo-verzel.ico'; 

const Veiculo = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [veiculo, setVeiculo] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const token = localStorage.getItem('accessToken');

    useEffect(() => {
        const fetchVeiculo = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/veiculos/public/v1/${id}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    }
                });
                setVeiculo(response.data);
                setLoading(false);
            } catch (err) {
                setError(err);
                setLoading(false);
            }
        };

        fetchVeiculo();
    }, [id, token]);

    if (loading) return <p>Carregando...</p>;
    if (error) return <p>Erro ao carregar os detalhes do veículo.</p>;

    return (
        <div className='veiculo-container'>
            <div className='container'>
                <div className='detalhes-carro'>
                    <div className='img-veiculo'>
                        <img src={veiculo.foto} alt={`Foto do veículo ${veiculo.nome}`} />
                    </div>
                    <div className='detalhes'>
                        <div className='info-item'>
                            <MdCheckCircle color="white" size={24} />
                            <p className='nome'>{veiculo.nome}</p>
                        </div>
                        <div className='info-item'>
                            <FaCar color="white" size={24} />
                            <p className='marca'>{veiculo.marca}</p>
                        </div>
                        <div className='info-item'>
                            <img src={logoVerzel} alt="Logo" width={24} />
                            <p className='modelo'>{veiculo.modelo}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div className='nav-container'>
                <div className='valor-container'>
                    <div className='valor'>
                        <p className='anuncio-valor' >Valor anunciado:</p>
                        <p className='veiculo-valor'>R$ {veiculo.valor}</p>
                    </div>
                    <div>
                        <AiOutlineArrowLeft color="white" size={20} /> 
                        <button onClick={() => navigate('/veiculos')} className="button voltar">Voltar</button>
                    </div>
                    <div>
                        <AiOutlineArrowRight color="white"size={20} />
                        <button onClick={() => navigate(`/veiculos/editar/${veiculo.id}`)} className="button voltar">Editar</button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Veiculo;
