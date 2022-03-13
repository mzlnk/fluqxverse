import { LoginButtonProps } from '../index'

import { SiFacebook } from 'react-icons/si'
import styles from './FacebookLoginButton.module.scss'

const FacebookLoginButton = ({ clientId, redirectUri }: LoginButtonProps) => {
  return (
    <a
      href={`https://accounts.google.com/o/oauth2/v2/auth?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code&scope=profile`}>
      <div className={styles.container}>
        <SiFacebook className={styles.icon}/>
        <span className={styles.text}>Sign In with Facebook</span>
      </div>
    </a>
  )
}

export default FacebookLoginButton
