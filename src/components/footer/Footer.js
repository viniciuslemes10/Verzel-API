import React from 'react';
import './Footer.css';

const Footer = () => {
    return (
        <footer className="footer">
            <div className="footer-content">
                <div className="footer-section">
                    <h4>Sobre Nós</h4>
                    <p>Somos uma empresa dedicada a oferecer as melhores soluções para você.</p>
                </div>
                <div className="footer-section">
                    <h4>Contato</h4>
                    <p>Email: vinikjhgfds@gmail.com</p>
                    <p>Telefone: (11) 910747135</p>
                    <p>Linkedin: <a href='https://www.linkedin.com/in/vinicius-desenvolvedor/' target='_blank' rel='noopener noreferrer'>linkedin</a></p>
                </div>
            </div>
            <div className="footer-bottom">
                <p>&copy; 2024 Verzel Cars. Todos os direitos reservados.</p>
            </div>
        </footer>
    );
};

export default Footer;
