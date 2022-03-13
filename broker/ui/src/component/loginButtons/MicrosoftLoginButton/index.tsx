import { LoginButtonProps } from '../index'

import { ReactComponent as MicrosoftIcon } from './../../../assets/icons/microsoft-icon.svg'
import styles from './MicrosoftLoginButton.module.scss'

const MicrosoftLoginButton = ({ clientId, redirectUri }: LoginButtonProps) => {
  return (
    <a
      href={`https://accounts.Microsoft.com/o/oauth2/v2/auth?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code&scope=profile`}>
      <div className={styles.container}>
        <MicrosoftIcon className={styles.icon}/>
        <span className={styles.text}>Sign In with Microsoft</span>
      </div>
    </a>
  )
}

export default MicrosoftLoginButton
