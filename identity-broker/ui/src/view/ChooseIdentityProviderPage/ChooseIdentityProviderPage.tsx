import { useEffect, useState } from 'react'
import { IdentityProvider, IdentityProviderType, listIdentityProviders } from '../../common/service/identityProvider'
import GoogleLoginButton from '../../component/loginButtons/GoogleLoginButton'
import styles from './ChooseIdentityProvider.module.scss'
import GitHubLoginButton from '../../component/loginButtons/GitHubLoginButton'
import FacebookLoginButton from '../../component/loginButtons/FacebookLoginButton'
import MicrosoftLoginButton from '../../component/loginButtons/MicrosoftLoginButton'
import KeycloakLoginButton from '../../component/loginButtons/KeycloakLoginButton'
import OktaLoginButton from '../../component/loginButtons/OktaLoginButton'

const ChooseIdentityProviderPage = () => {
  const [identityProviders, setIdentityProviders] = useState<IdentityProvider[]>([])

  const createLoginButton = (provider: IdentityProvider) => {
    switch (provider.type) {
      case IdentityProviderType.FACEBOOK:
        return <FacebookLoginButton clientId={provider.clientId} redirectUri={provider.redirectUri} />
      case IdentityProviderType.GITHUB:
        return <GitHubLoginButton clientId={provider.clientId} redirectUri={provider.redirectUri} />
      case IdentityProviderType.GOOGLE:
        return <GoogleLoginButton clientId={provider.clientId} redirectUri={provider.redirectUri} />
      case IdentityProviderType.KEYCLOAK:
        return <KeycloakLoginButton clientId={provider.clientId} redirectUri={provider.redirectUri} />
      case IdentityProviderType.MICROSOFT:
        return <MicrosoftLoginButton clientId={provider.clientId} redirectUri={provider.redirectUri} />
      case IdentityProviderType.OKTA:
        return <OktaLoginButton clientId={provider.clientId} redirectUri={provider.redirectUri} />
      default:
        return ''
    }
  }

  useEffect(() => {
    listIdentityProviders(setIdentityProviders)
  }, [])

  return (
    <div className={styles.container}>
      <h3>Choose provider</h3>
      {identityProviders.map((provider: IdentityProvider, idx: number) => createLoginButton(provider))}
    </div>
  )
}

export default ChooseIdentityProviderPage
