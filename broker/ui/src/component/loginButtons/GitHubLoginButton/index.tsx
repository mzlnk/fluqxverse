import { LoginButtonProps } from '../index'

import { SiGithub } from 'react-icons/si'
import styles from './GitHubLoginButton.module.scss'

const GitHubLoginButton = ({ clientId, redirectUri }: LoginButtonProps) => {
  return (
    <a
      href={`https://github.com/login/oauth/authorize?client_id=${clientId}&redirect_uri=${redirectUri}&state=ruri=http://localhost:12000,nce=test&response_type=code&scope=user:email`}>
      <div className={styles.gitHubLoginContainer}>
        <SiGithub className={styles.icon}/>
        <span className={styles.text}>Sign In with GitHub</span>
      </div>
    </a>
  )
}

export default GitHubLoginButton
