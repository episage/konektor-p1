import com.cloudbees.syslog.SyslogMessage
import com.cloudbees.syslog.sender.TcpSyslogMessageSender
import com.cloudbees.syslog.SDElement
import com.cloudbees.syslog.SDParam

import javax.annotation.Nonnull
import javax.net.SocketFactory
import javax.net.ssl.SSLSocketFactory


class TcpMessageSender extends TcpSyslogMessageSender {

    private Socket socket

    private String response

    private int delayAfterWriting = 250
    private int delayBeforeClosingInMillis = 500
	private String pid, messageId
	private Date timestamp
	private Set<SDElement> sdElements
	private String syslogMsg
    private String postfix = '\r\n'

    void sendMessage(CharSequence message) throws IOException {
        CharArrayWriter writer = new CharArrayWriter()
        def inlineMessage = message.replaceAll('>\\s+<', '><').replaceAll('\\s+', ' ')
        writer.append(inlineMessage)
        this.sendMessage(writer)
    }

    @Override
    synchronized void sendMessage(@Nonnull SyslogMessage message) throws IOException {
	
		message.withProcId(this.pid)
				.withMsgId(this.messageId)
				.withTimestamp(this.timestamp)
				.setSDElements(sdElements)

		this.syslogMsg = message.toRfc5425SyslogMessage()
		
        this.response = null
        try {
            Writer writer = connect()
            message.toSyslogMessage(this.messageFormat, writer)
            writer.write(this.postfix)
            writer.flush()
            Thread.sleep(delayAfterWriting)
            readResponse()
        } finally {
            Thread.sleep(delayBeforeClosingInMillis)
            closeQuietlySocket()
        }
    }

    private Writer connect() throws IOException {
        InetAddress inetAddress = this.syslogServerHostnameReference.get()
        if (this.socket != null && !Objects.equals(this.socket.getInetAddress(), inetAddress)) {
            closeQuietlySocket()
        }

        boolean socketIsValid
        try {
            socketIsValid = this.socket != null && this.socket.isConnected() && this.socket.isBound() && !this.socket.isClosed() && !this.socket.isInputShutdown() && !this.socket.isOutputShutdown()
        } catch (Exception e) {
            socketIsValid = false
        }

        if (!socketIsValid) {
            if (this.isSsl()) {
                this.socket = (this.getSSLContext() != null ? this.getSSLContext().getSocketFactory() : SSLSocketFactory.getDefault()).createSocket()
            } else {
                this.socket = SocketFactory.getDefault().createSocket()
            }
            this.socket.setKeepAlive(true)
            this.socket.connect(new InetSocketAddress(inetAddress, this.syslogServerPort), this.getSocketConnectTimeoutInMillis())
        }
        new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream(), UTF_8))
    }

    private void readResponse() throws IOException {
        char endDelimiter = 0x03
        Reader reader = new InputStreamReader(socket.getInputStream())
        ByteArrayOutputStream bos = new ByteArrayOutputStream()
        int next
        while ((next = reader.read()) > -1) {
            if (next == endDelimiter) {
                this.response = bos.toString()
                break
            }
            bos.write(next)
        }
    }

    String getResponse() {
        response
    }

    synchronized void setPostfix(String postfix) {
        this.postfix = postfix
    }

    void setDelayAfterWriting(int delayAfterWriting) {
        this.delayAfterWriting = delayAfterWriting
    }

    void setDelayBeforeClosingInMillis(int delayBeforeClosingInMillis) {
        this.delayBeforeClosingInMillis = delayBeforeClosingInMillis
    }
	
	void setPid(String pid) {
        this.pid = pid
    }
	
	void setMessageId(String messageId) {
        this.messageId = messageId
    }
	
	void setTimestamp(String timestamp) {
        this.timestamp = Date.parse("yyyy-MM-dd'T'HH:mm:ss.SSSZ", timestamp)
    }

	void setSDElements(String sd_id, String sd_param, String sd_value) {
		if (this.sdElements == null) {
            this.sdElements = new HashSet<SDElement>()
		}
			
		sdElements.add(new SDElement(sd_id, new SDParam(sd_param, sd_value)))
    }
	
	String getSyslogMsg() {
        return this.syslogMsg
    }	

    @Override
    void close() throws IOException {
        if (socket) {
            socket.close()
        }
    }

    void closeQuietlySocket() {
        try {
            if (socket && !socket.isClosed()) {
                socket.close()
            }
        } catch (Exception e) {
            // nic nie rób
        }
        socket = null
    }

}
