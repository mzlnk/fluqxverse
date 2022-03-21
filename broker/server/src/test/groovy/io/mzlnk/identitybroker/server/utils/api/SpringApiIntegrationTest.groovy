package io.mzlnk.identitybroker.server.utils.api

import io.mzlnk.identitybroker.server.utils.config.ApiTestConfiguration
import io.mzlnk.identitybroker.server.utils.config.SecurityTestConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@WebMvcTest
@Import([SecurityTestConfiguration, ApiTestConfiguration])
@ActiveProfiles('test')
@interface SpringApiIntegrationTest {



}
