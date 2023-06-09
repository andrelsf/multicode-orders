package br.dev.multicode.mcorders.configs.undertow;

import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;

import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UndertowServletWebServerFactoryConfiguration implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

  private static final int BUFFER_SIZE = 4096;

  @Override
  public void customize(UndertowServletWebServerFactory factory) {
    factory.addDeploymentInfoCustomizers(deploymentInfo -> {
      WebSocketDeploymentInfo webSocketDeploymentInfo = new WebSocketDeploymentInfo();
      webSocketDeploymentInfo.setBuffers(new DefaultByteBufferPool(false, BUFFER_SIZE));
      deploymentInfo.addServletContextAttribute(WebSocketDeploymentInfo.ATTRIBUTE_NAME, webSocketDeploymentInfo);
    });
  }
}
