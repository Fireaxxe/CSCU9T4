<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:template match="/">
		<html>
			<body>
				<h2>My CD Collection</h2>
				<table border="1">
					<tr bgcolor="green">
						<th style="text-align:left">Title</th>
						<th style="text-align:left">Artist</th>
					</tr>
					<xsl:for-each select="CATALOG/CD">
						<tr>
							<td>
								<xsl:value-of select="TITLE"/>
							</td>
							<td>
								<xsl:value-of select="ARTIST"/>
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>