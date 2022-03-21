package io.mzlnk.identitybroker.server.api.callback

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonSlurper
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class AuthCallbackControllerFixture {

    private static final JsonSlurper JSON_PARSER = new JsonSlurper()
    private static final String CONTROLLER_BASE_PATH = '/idb/api/v1/auth'

    private MockMvc mockMvc
    private ObjectMapper mapper

    def handleGoogleCallback(String code, AuthCallbackContext callbackContext,
                             @DelegatesTo(ResultActions) Closure resultMatchers = { andExpect(status().isOk()) }) {
        def state = "ruri=${callbackContext.redirectUri()},nonce=${callbackContext.nonce()}"

        def responseBody = mockMvc.perform(
                get("$CONTROLLER_BASE_PATH/callback/google")
                        .param('code', code)
                        .param('state', state))
                .tap(resultMatchers)
                .andReturn().getResponse().getContentAsByteArray()

        return JSON_PARSER.parse(responseBody) as Map
    }

    def handleGitHubCallback(String code, AuthCallbackContext callbackContext,
                             @DelegatesTo(ResultActions) Closure resultMatchers = { andExpect(status().isOk()) }) {
        def state = "ruri=${callbackContext.redirectUri()},nonce=${callbackContext.nonce()}"

        def responseBody = mockMvc.perform(
                get("$CONTROLLER_BASE_PATH/callback/github")
                        .param('code', code)
                        .param('state', state))
                .tap(resultMatchers)
                .andReturn().getResponse().getContentAsByteArray()

        return JSON_PARSER.parse(responseBody) as Map
    }



}
