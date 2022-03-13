import { LoginButtonProps } from '../index'

import { ReactComponent as KeycloakIcon } from './../../../assets/icons/keycloak-icon.svg'
import styles from './KeycloakLoginButton.module.scss'

const KeycloakLoginButton = ({ clientId, redirectUri }: LoginButtonProps) => {
  return (
    <a
      href={`https://accounts.Keycloak.com/o/oauth2/v2/auth?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code&scope=profile`}>
      <div className={styles.container}>
        <KeycloakIcon className={styles.icon}/>
        <span className={styles.text}>Sign In with Keycloak</span>
      </div>
    </a>
  )
}

export default KeycloakLoginButton
