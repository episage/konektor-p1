import com.cloudbees.syslog.Facility
import com.cloudbees.syslog.MessageFormat
import com.cloudbees.syslog.Severity

import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.KeyStoreBuilderParameters
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import java.security.KeyStore

def createKeyManager = {
    keystorePath, keystorePassword ->
        List<KeyStore.Builder> builderList = Collections.singletonList(
                KeyStore.Builder.newInstance('PKCS12', null, new File(keystorePath), new KeyStore.PasswordProtection(keystorePassword.toCharArray()))
        )

        KeyManagerFactory kmf = KeyManagerFactory.getInstance('NewSunX509')
        kmf.init(new KeyStoreBuilderParameters(builderList))

        kmf.getKeyManagers()
}

def createTrustManager = {
    TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
    tmf.init((KeyStore) null)

    tmf.getTrustManagers()
}

def createSslContext = {
    keystorePath, keystorePassword ->
        def keysManager = createKeyManager.call(keystorePath, keystorePassword)
        def trustManager = createTrustManager.call()

        SSLContext sslContext = SSLContext.getInstance("TLS")
        sslContext.init(keysManager, trustManager, null)

        sslContext
}

def keystorePath = binding.getVariable('service_tlsKeystorePath') as String
def keystorePassword = binding.getVariable('service_tlsKeystorePassword') as String
def messageHostname = binding.getVariable('message_hostname') as String
def messageAppName = binding.getVariable('message_appName') as String
def messageFacility = Facility.(binding.getVariable('message_facility') as String)
def messageSeverity = Severity.(binding.getVariable('message_severity') as String)
def messagePid = binding.getVariable('message_pid') as String
def messageId = binding.getVariable('message_messageId') as String
def messageTimestamp = binding.getVariable('message_timestamp') as String
def message_structuredData_id1 = binding.getVariable('message_structuredData_id1') as String
def message_structuredData_param1 = binding.getVariable('message_structuredData_param1') as String
def message_structuredData_value1 = binding.getVariable('message_structuredData_value1') as String
def message = binding.getVariable('message') as String

def severHostname = binding.getVariable('service_hostname') as String
def serverPort = binding.getVariable('service_port') as int

//log.info keystorePath
//log.info keystorePassword
//log.info useTls
//log.info messageHostname
//log.info messageAppName
//log.info messageFacility
//log.info messageSeverity
//log.info message
//log.info severHostname
//log.info serverPort

TcpMessageSender messageSender = new TcpMessageSender()

messageSender.setDefaultMessageHostname(messageHostname)
messageSender.setDefaultAppName(messageAppName)
messageSender.setDefaultFacility(messageFacility)
messageSender.setDefaultSeverity(messageSeverity)
messageSender.setPid(messagePid)
messageSender.setMessageId(messageId)
messageSender.setTimestamp(messageTimestamp)
messageSender.setSDElements(message_structuredData_id1, message_structuredData_param1, message_structuredData_value1)
messageSender.setSyslogServerHostname(severHostname)
messageSender.setSyslogServerPort(serverPort)
messageSender.setMessageFormat(MessageFormat.RFC_5425)
messageSender.setSsl(true)
messageSender.setDelayAfterWriting(0)
messageSender.setDelayBeforeClosingInMillis(0)
messageSender.setSSLContext(createSslContext.call(keystorePath, keystorePassword))
messageSender.sendMessage(message)

binding.setVariable('message_syslogMsg', messageSender.getSyslogMsg()) 
binding.setVariable('message_response', messageSender.getResponse())