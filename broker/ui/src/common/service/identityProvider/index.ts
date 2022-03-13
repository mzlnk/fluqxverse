export enum IdentityProviderType {
  // eslint-disable-next-line no-unused-vars
  FACEBOOK = 'FACEBOOK',
  // eslint-disable-next-line no-unused-vars
  GITHUB = 'GITHUB',
  // eslint-disable-next-line no-unused-vars
  GOOGLE = 'GOOGLE',
  // eslint-disable-next-line no-unused-vars
  KEYCLOAK = 'KEYCLOAK',
  // eslint-disable-next-line no-unused-vars
  MICROSOFT = 'MICROSOFT',
  // eslint-disable-next-line no-unused-vars
  OKTA = 'OKTA'
}

export type IdentityProvider = {
  type: IdentityProviderType;
  clientId: string;
  redirectUri: string;
}

export const listIdentityProviders = (setProviders: (providers: IdentityProvider[]) => void) => {
  const url = `${process.env.REACT_APP_BASE_URL}/api/v1/identity-providers`
  fetch(url)
    .then((response: Response) => response.json())
    .then((json: any) => setProviders(json))
}
