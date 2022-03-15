import { LoginButtonProps } from '../index'

import { FcGoogle } from 'react-icons/fc'
import styles from './GoogleLoginButton.module.scss'

const GoogleLoginButton = ({ clientId, redirectUri }: LoginButtonProps) => {
  return (
    <a href={`https://accounts.google.com/o/oauth2/v2/auth?client_id=${clientId}&redirect_uri=http://localhost:8080/api/v1/auth/callback/google&state=ruri=${redirectUri}&response_type=code&scope=profile email&`}>
      <div className={styles.googleLoginButtonContainer}>
        <FcGoogle className={styles.icon}/>
        <span className={styles.text}>Sign In with Google</span>
      </div>
    </a>
  )
}

export default GoogleLoginButton
