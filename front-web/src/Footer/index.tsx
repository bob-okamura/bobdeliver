import './styles.css';
import { ReactComponent as YouTubeIcon } from './youtube.svg'
import { ReactComponent as LinkedInIcon } from './linkedin.svg'

function Footer() {
    return(
        <footer className='main-footer'>
            App replicado do evento Semana DevSuperior para fins de estudo.
            <div className='footer-icons'>
                <a href='https://github.com/bob-okamura/bobdeliver' target='_new'>
                    <YouTubeIcon/>
                </a>
                <a href='https://www.linkedin.com/in/roberto-okamura-6a9b59b4/' target='_new'>
                    <LinkedInIcon/>
                </a>
            </div>
        </footer>
    )

}

export default Footer;