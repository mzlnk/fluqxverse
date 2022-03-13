import { LoginButtonProps } from '../index'

import { SiGithub } from 'react-icons/si'
import styles from './GitHubLoginButton.module.scss'

const GitHubLoginButton = ({ clientId, redirectUri }: LoginButtonProps) => {
  return (
    <a
      href={`https://accounts.google.com/o/oauth2/v2/auth?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code&scope=profile`}>
      <div className={styles.container}>
        <SiGithub className={styles.icon}/>
        <span className={styles.text}>Sign In with GitHub</span>
      </div>
    </a>
  )
}

export default GitHubLoginButton
