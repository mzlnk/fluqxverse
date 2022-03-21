import { LoginButtonProps } from '../index'

import { SiOkta } from 'react-icons/si'
import styles from './OktaLoginButton.module.scss'

const OktaLoginButton = ({ clientId, redirectUri }: LoginButtonProps) => {
  return (
    <a
      href={`https://accounts.google.com/o/oauth2/v2/auth?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code&scope=profile`}>
      <div className={styles.oktaLoginButtonContainer}>
        <SiOkta className={styles.icon}/>
        <span className={styles.text}>Sign In with Okta</span>
      </div>
    </a>
  )
}

export default OktaLoginButton
