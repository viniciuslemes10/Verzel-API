import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './VeiculosList.css';
import '@fortawesome/fontawesome-free/css/all.min.css';

const VeiculosList = () => {
    const [veiculos, setVeiculos] = useState([]);
    const [error, setError] = useState('');
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(12);
    const [totalPages, setTotalPages] = useState(0);
    const [sortDirection, setSortDirection] = useState('desc');
    const navigate = useNavigate();
    const token = localStorage.getItem('accessToken');

    useEffect(() => {
        const fetchVeiculos = async () => {
            try {
                const response = await axios.get(
                    `http://localhost:8080/api/veiculos/public/v1?page=${page}&size=${size}&direction=${sortDirection}`, {
                        headers: {
                            Authorization: `Bearer ${token}`
                        }
                    }
                );

                const { content, totalPages } = response.data;
                setVeiculos(content);
                setTotalPages(totalPages);
            } catch (error) {
                console.error('Erro ao buscar veículos:', error.response ? error.response.data : error.message);
                setError(`Falha ao buscar veículos: ${error.response ? error.response.data.message : error.message}`);
            }
        };

        fetchVeiculos();
    }, [page, size, sortDirection, token]);

    const handleViewDetails = (id) => {
        navigate(`/veiculos/${id}`);
    };

    const handleEdit = (id) => {
        
        navigate(`/veiculos/editar/${id}`);
    };

    const handleDelete = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/api/veiculos/admin/v1/${id}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            setVeiculos(veiculos.filter(veiculo => veiculo.id !== id));
        } catch (error) {
            console.error('Erro ao deletar veículo:', error.response ? error.response.data : error.message);
            setError(`Falha ao deletar veículo: ${error.response ? error.response.data.message : error.message}`);
        }
    };

    const handlePageChange = (newPage) => {
        setPage(newPage);
    };

    const handleSortChange = () => {
        setSortDirection(prevDirection => prevDirection === 'desc' ? 'asc' : 'desc');
    };

    const renderPagination = () => {
        const pages = [];
        const startPage = Math.max(0, Math.min(page - 1, totalPages - 3));
        const endPage = Math.min(totalPages, startPage + 3);

        for (let i = startPage; i < endPage; i++) {
            pages.push(
                <button
                    key={i}
                    onClick={() => handlePageChange(i)}
                    className={`pagination-button ${i === page ? 'active' : ''}`}
                >
                    {i + 1}
                </button>
            );
        }
        return pages;
    };

    return (
        <div className='veiculos'>
            {error && <p className="error-message">{error}</p>}
            <div className="sort-controls">
                <button onClick={handleSortChange} className='sort-button'>
                    Ordenar por {sortDirection === 'desc' ? 'Ascendente' : 'Descendente'}
                </button>
            </div>
            <div className="container-veiculos">
                {veiculos.length === 0 ? (
                    <p>Nenhum veículo encontrado.</p>
                ) : (
                    veiculos.map(veiculo => (
                        <div className='veiculo' key={veiculo.id}>
                            <img src={veiculo.foto} alt='foto-do-veiculo' />
                            <div className='veiculo-details'>
                                <p className='nome'>{veiculo.marca} {veiculo.nome}</p>
                                <p className='modelo'>{veiculo.modelo}</p>
                                <p className='valor'>R$ {veiculo.valor}</p>
                                <button onClick={() => handleViewDetails(veiculo.id)} className='detalhes'>Ver detalhes</button>
                            </div>
                            <div className='modified-veiculos'>
                                <button onClick={() => handleEdit(veiculo.id)}>
                                    <i className="fas fa-edit"></i> Editar
                                </button>
                                <button onClick={() => handleDelete(veiculo.id)}>
                                    <i className="fas fa-trash"></i> Deletar
                                </button>
                            </div>
                        </div>
                    ))
                )}
            </div>
            <div className="pagination">
                <button
                    onClick={() => handlePageChange(Math.max(0, page - 3))}
                    disabled={page === 0}
                    className="pagination-button"
                >
                    <i className="fas fa-chevron-left"></i>
                </button>
                {renderPagination()}
                <button
                    onClick={() => handlePageChange(Math.min(totalPages - 1, page + 3))}
                    disabled={page >= totalPages - 1}
                    className="pagination-button"
                >
                    <i className="fas fa-chevron-right"></i>
                </button>
            </div>
        </div>
    );
};

export default VeiculosList;
