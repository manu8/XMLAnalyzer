<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<ul>
					<xsl:for-each select="contacts/contact">
						<li>
							<span margin="5px" padding="5px">
								<strong><xsl:value-of select="firstname"/></strong>
								<br></br>
								<strong><xsl:value-of select="lastname"/></strong>
							</span>
						</li>
					</xsl:for-each>
				</ul>		
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>