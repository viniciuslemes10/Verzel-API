import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { FaTimes } from 'react-icons/fa'; // Importa o ícone de "X"
import './Header.css';

const Header = () => {
    const [isMenuOpen, setIsMenuOpen] = useState(false);
    const navigate = useNavigate();

    const handleNavigation = (path) => {
        navigate(path);
    };

    const handleRemoveAndNavigation = () => {
        localStorage.removeItem('accessToken');
        navigate('/');
    };

    const toggleMenu = () => {
        setIsMenuOpen(!isMenuOpen);
    };

    return (
        <header className="header">
            <div className="header-content">
                <img src='https://veiculos-images-verzel.s3.us-west-2.amazonaws.com/logo-verzel.png' alt="Logo" className='image-logo' />
                <div className={`menu-toggle ${isMenuOpen ? 'open' : ''}`} onClick={toggleMenu}>
                    {isMenuOpen ? <FaTimes size={32} color="#fff" /> : (
                        <>
                            <div className="bar"></div>
                            <div className="bar"></div>
                            <div className="bar"></div>
                        </>
                    )}
                </div>
                
                <nav className={`nav-menu ${isMenuOpen ? 'mobile-menu' : ''}`}>
                    <ul>
                        <li><button onClick={() => handleNavigation('/cadastrar')}>Cadastrar</button></li>
                        <li><button onClick={() => handleNavigation('/veiculos')}>Veículos</button></li>
                        <li><button onClick={() => handleNavigation('/')}>Login</button></li>
                        <li><button onClick={() => handleRemoveAndNavigation()}>Sair</button></li>
                    </ul>
                </nav>

                <nav className={'nav-menu-desktop'}>
                    <ul>
                        <li><button onClick={() => handleNavigation('/cadastrar')}>Cadastrar</button></li>
                        <li><button onClick={() => handleNavigation('/veiculos')}>Veículos</button></li>
                        <li><button onClick={() => handleNavigation('/')}>Login</button></li>
                        <li><button onClick={() => handleRemoveAndNavigation()}>Sair</button></li>
                    </ul>
                </nav>
            </div>
        </header>
    );
};

export default Header;
